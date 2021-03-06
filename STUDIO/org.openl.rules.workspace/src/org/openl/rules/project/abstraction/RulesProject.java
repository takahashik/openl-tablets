package org.openl.rules.project.abstraction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openl.rules.common.*;
import org.openl.rules.common.impl.ArtefactPathImpl;
import org.openl.rules.project.impl.local.LocalRepository;
import org.openl.rules.repository.api.FileData;
import org.openl.rules.repository.api.Repository;
import org.openl.rules.workspace.uw.UserWorkspace;
import org.openl.util.RuntimeExceptionWrapper;

public class RulesProject extends UserWorkspaceProject {
    private LocalRepository localRepository;
    private String localFolderName;
    private Repository designRepository;
    private String designFolderName;
    private final LockEngine lockEngine;

    public RulesProject(UserWorkspace userWorkspace,
            LocalRepository localRepository, FileData localFileData,
            Repository designRepository, FileData designFileData, LockEngine lockEngine) {
        super(userWorkspace.getUser(), localFileData != null ? localRepository : designRepository,
                localFileData != null ? localFileData : designFileData, localFileData != null);
        this.localRepository = localRepository;
        this.localFolderName = localFileData == null ? null : localFileData.getName();
        this.designRepository = designRepository;
        this.designFolderName = designFileData == null ? null : designFileData.getName();
        this.lockEngine = lockEngine;

        FileData fullLocalFileData;
        if (localFileData != null && designFileData != null) {
            String localVersion = localFileData.getVersion();
            if (localVersion == null || designFileData.getVersion().equals(localVersion)) {
                // Set the path for local repository, other properties are equal to design repository properties
                fullLocalFileData = new FileData();
                fullLocalFileData.setName(localFileData.getName());
                fullLocalFileData.setVersion(designFileData.getVersion());
                fullLocalFileData.setSize(designFileData.getSize());
                fullLocalFileData.setAuthor(designFileData.getAuthor());
                fullLocalFileData.setModifiedAt(designFileData.getModifiedAt());
                fullLocalFileData.setComment(designFileData.getComment());
                fullLocalFileData.setDeleted(designFileData.isDeleted());
                setFileData(fullLocalFileData);
            } else {
                if (localFileData.getAuthor() == null || localFileData.getModifiedAt() == null) {
                    // Lazy load properties
                    setFileData(null);
                } else {
                    setFileData(localFileData);
                }
            }
        }

        if (designFileData != null) {
            setLastHistoryVersion(designFileData.getVersion());
        }
    }

    @Override
    public void save(CommonUser user) throws ProjectException {
        AProject designProject = new AProject(designRepository, designFolderName, false);
        AProject localProject = new AProject(localRepository, localFolderName, true);
        designProject.getFileData().setComment(getFileData().getComment());
        designProject.update(localProject, user);
        String version = designProject.getFileData().getVersion();
        setLastHistoryVersion(version);
        setHistoryVersion(version);
        if (!isRepositoryOnly()) {
            localRepository.getProjectState(localFolderName).setProjectVersion(null);
        }
        clearModifyStatus();
        unlock();
        refresh();
    }

    @Override
    public void delete(CommonUser user) throws ProjectException {
        if (isLocalOnly()) {
            // If for some reason the project is locked we must unlock it.
            unlock();
            erase();
        } else {
            super.delete(user);
        }
    }

    public void close(CommonUser user) throws ProjectException {
        try {
            if (localFolderName != null) {
                deleteFromLocalRepository();
            }
            if (isLockedByUser(user)) {
                unlock();
            }
            if (!isLocalOnly()) {
                setRepository(designRepository);
                setFolderPath(designFolderName);
                setHistoryVersion(null);
                if (!isRepositoryOnly()) {
                    localRepository.getProjectState(localFolderName).setProjectVersion(null);
                }
                setFolderStructure(false);
            }
        } finally {
            refresh();
        }
    }

    private void deleteFromLocalRepository() throws ProjectException {
        try {
            for (FileData fileData : localRepository.list(localFolderName)) {
                if (!localRepository.delete(fileData)) {
                    if (localRepository.check(fileData.getName()) != null) {
                        throw new ProjectException("Can't close project because resource '" + fileData.getName() + "' is used");
                    }
                }
            }
            // Delete properties folder. Workaround for broken empty projects that failed to delete properties folder last time
            localRepository.getProjectState(localFolderName).notifyModified();
        } catch (IOException e) {
            throw new ProjectException("Not possible to read the directory", e);
        }
    }

    @Override
    public void erase() throws ProjectException {
        try {
            if (designFolderName != null) {
                if (!designRepository.deleteHistory(designFolderName, null)) {
                    throw new ProjectException("Can't erase project because it is absent or can't be deleted");
                }
            } else {
                deleteFromLocalRepository();
            }
        } finally {
          refresh();
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        setFileData(null);
    }

    @Override
    public LockInfo getLockInfo() {
        synchronized (lockEngine) {
            return lockEngine.getLockInfo(getName());
        }
    }

    @Override
    public void lock() throws ProjectException {
        synchronized (lockEngine) {
            // No need to lock local only projects. Other users don't see it.
            if (!isLocalOnly()) {
                lockEngine.lock(getName(), getUser().getUserName());
            }
        }
    }

    @Override
    public void unlock() throws ProjectException {
        synchronized (lockEngine) {
            lockEngine.unlock(getName());
        }
    }

    /**
     * Try to lock the project if it's not locked already.
     * Doesn't overwrite lock info if the user was locked already.
     *
     * @return false if the project was locked by other user. true if project wasn't locked before or was locked by me.
     * @throws ProjectException if can't lock the project
     */
    public boolean tryLock() throws ProjectException {
        synchronized (lockEngine) {
            if (isLocalOnly()) {
                // No need to lock local only projects. Other users don't see it.
                return true;
            }
            LockInfo lockInfo = getLockInfo();
            if (lockInfo.isLocked()) {
                return isLockedByMe(lockInfo);
            }

            lockEngine.lock(getName(), getUser().getUserName());

            return true;
        }
    }

    public String getLockedUserName() {
        LockInfo lockInfo = getLockInfo();
        return lockInfo.isLocked() ? lockInfo.getLockedBy().getUserName() : "";
    }

    public ProjectVersion getVersion() {
        String historyVersion = getHistoryVersion();
        if (historyVersion == null) {
            return getLastVersion();
        }
        return super.getVersion();
    }

    @Override
    protected List<FileData> getHistoryFileDatas() {
        if (historyFileDatas == null) {
            try {
                if (designFolderName != null) {
                    historyFileDatas = designRepository.listHistory(designFolderName);
                } else {
                    // Local repository doesn't have versions
                    historyFileDatas = Collections.emptyList();
                }
            } catch (IOException ex) {
                throw RuntimeExceptionWrapper.wrap(ex);
            }
        }
        return historyFileDatas;
    }

    public List<ProjectVersion> getArtefactVersions(ArtefactPath artefactPath) {
        String subPath = artefactPath.getStringValue();
        if (subPath.isEmpty() || subPath.equals("/")) {
            return getVersions();
        }
        if (!subPath.startsWith("/")) {
            subPath += "/";
        }
        String fullPath = getFolderPath() + subPath;
        Collection<FileData> fileDatas;
        try {
            fileDatas = getRepository().listHistory(fullPath);
        } catch (IOException ex) {
            throw RuntimeExceptionWrapper.wrap(ex);
        }
        List<ProjectVersion> versions = new ArrayList<ProjectVersion>();
        for (FileData data : fileDatas) {
            versions.add(createProjectVersion(data));
        }
        return versions;
    }

    public boolean isLocalOnly() {
        return designFolderName == null;
    }

    public boolean isRepositoryOnly() {
        return localFolderName == null;
    }

    public boolean isOpened() {
        return getRepository() == localRepository;
    }

    public void openVersion(String version) throws ProjectException {
        AProject designProject = new AProject(designRepository, designFolderName, version, false);

        if (localFolderName == null) {
            localFolderName = designProject.getName();
        }
        new AProject(localRepository, localFolderName, true).update(designProject, getUser());
        setRepository(localRepository);
        setFolderPath(localFolderName);
        setFolderStructure(true);

        setHistoryVersion(version);

        refresh();

        localRepository.getProjectState(localFolderName).clearModifyStatus();
        if (!isLastVersion()) {
            localRepository.getProjectState(localFolderName).saveFileData(getFileData());
        }
    }

    // Is Opened for Editing by me? -- in LW + locked by me
    public boolean isOpenedForEditing() {
        return !isLocalOnly() && super.isOpenedForEditing() && !isRepositoryOnly();
    }

    @Override
    public boolean isModified() {
        return !isRepositoryOnly() && localRepository.getProjectState(localFolderName).isModified();

    }

    private void clearModifyStatus() {
        if (!isRepositoryOnly()) {
            localRepository.getProjectState(localFolderName).clearModifyStatus();
        }
    }

    public void setModified() {
        if (!isRepositoryOnly()) {
            localRepository.getProjectState(localFolderName).notifyModified();
        }
    }

    @Override
    public ArtefactPath getArtefactPath() {
        // Return artefact name inside the project including project name. In the case of project it's just project name.
        if (isOpened()) {
            return super.getArtefactPath();
        } else {
            return new ArtefactPathImpl(getName());
        }
    }

    public String getDesignFolderName() {
        return designFolderName;
    }
}
