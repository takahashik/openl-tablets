package org.openl.rules.project.abstraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openl.rules.common.ArtefactPath;
import org.openl.rules.common.CommonUser;
import org.openl.rules.common.ProjectException;
import org.openl.rules.common.impl.ArtefactPathImpl;
import org.openl.rules.project.impl.local.LocalRepository;
import org.openl.rules.repository.api.FileData;
import org.openl.rules.repository.api.Repository;
import org.openl.util.IOUtils;
import org.openl.util.RuntimeExceptionWrapper;

public class AProjectFolder extends AProjectArtefact {
    private Map<String, AProjectArtefact> artefacts;
    private ResourceTransformer resourceTransformer;
    private String folderPath;
    private String historyVersion;

    public AProjectFolder(AProject project, Repository repository, String folderPath, String historyVersion) {
        super(project, repository, null);
        if (folderPath.startsWith("/")) {
            folderPath = folderPath.substring(1);
        }
        this.folderPath = folderPath;
        this.historyVersion = historyVersion;
    }

    /**
     * Create a folder with pre-initialised content
     *
     * @param artefacts pre-initialized artefact collection
     */
    public AProjectFolder(Map<String, AProjectArtefact> artefacts, AProject project, Repository repository, String folderPath) {
        this(project, repository, folderPath, null);
        this.artefacts = artefacts;
    }

    @Override
    public String getName() {
        return folderPath.substring(folderPath.lastIndexOf("/") + 1);
    }

    public AProjectArtefact getArtefact(String name) throws ProjectException {
        AProjectArtefact artefact = getArtefactsInternal().get(name);
        if (artefact == null) {
            throw new ProjectException("Cannot find project artefact ''{0}''", null, name);
        }

        return artefact;
    }

    public void deleteArtefact(String name) throws ProjectException {
        getProject().lock();

        getArtefact(name).delete();
        getArtefactsInternal().remove(name);
    }

    public boolean hasArtefact(String name) {
        return getArtefactsInternal().containsKey(name);
    }

    public AProjectFolder addFolder(String name) throws ProjectException {
        getProject().lock();

        AProjectFolder createdFolder = new AProjectFolder(getProject(), getRepository(), folderPath + "/" + name, null);
        getArtefactsInternal().put(name, createdFolder);
        createdFolder.setResourceTransformer(resourceTransformer);
        return createdFolder;
    }

    public AProjectResource addResource(String name, AProjectResource resource) throws ProjectException {
        InputStream content = resourceTransformer != null ? resourceTransformer.transform(resource) : resource.getContent();
        AProjectResource addedResource = addResource(name, content);
        addedResource.setResourceTransformer(resourceTransformer);
        return addedResource;
    }

    public AProjectResource addResource(String name, InputStream content) throws ProjectException {
        try {
            getProject().lock();

            FileData fileData = new FileData();
            String fullName = folderPath + "/" + name;
            fileData.setName(fullName);
            Repository repository = getRepository();
            if (repository.check(fullName) != null) {
                throw new ProjectException(String.format("The file '%s' exists in the folder.", name), new IOException());
            }
            fileData = repository.save(fileData, content);
            AProjectResource createdResource = new AProjectResource(getProject(), repository, fileData);
            getArtefactsInternal().put(name, createdResource);
            return createdResource;
        } catch (IOException ex) {
            throw new ProjectException("Cannot add a resource", ex);
        } finally {
            IOUtils.closeQuietly(content);
        }
    }

    /**
     * Adds artefact to the folder creating needed subfolders (parent folders for artefact)
     *
     * @param artefact artefact to add
     */
    public void addArtefact(AProjectArtefact artefact) {
        Map<String, AProjectArtefact> artefacts = getArtefactsInternal();
        if (artefact instanceof AProjectFolder) {
            // Add folders as is. They are split to sub-folders already.
            artefacts.put(artefact.getName(), artefact);
            return;
        }

        String folderPath = getFolderPath();
        String artefactPath = artefact.getFileData().getName();

        int subFolderNameStart = folderPath.length() + 1;
        int subFolderNameEnd = artefactPath.indexOf('/', subFolderNameStart);
        if (subFolderNameEnd > -1) {
            // Has subfolder
            String name = artefactPath.substring(subFolderNameStart, subFolderNameEnd);
            AProjectFolder folder = (AProjectFolder) artefacts.get(name);
            if (folder == null) {
                folder = new AProjectFolder(new HashMap<String, AProjectArtefact>(),
                        artefact.getProject(),
                        artefact.getRepository(),
                        folderPath + "/" + name);
                artefacts.put(name, folder);
            }
            folder.addArtefact(artefact);
        } else {
            artefacts.put(artefact.getName(), artefact);
        }
    }

    public synchronized Collection<AProjectArtefact> getArtefacts() {
        return getArtefactsInternal().values();
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public void update(AProjectArtefact newFolder, CommonUser user) throws ProjectException {
        super.update(newFolder, user);
        if (this.isFolder()) {

            AProjectFolder folder = (AProjectFolder) newFolder;
            // remove absent
            for (AProjectArtefact artefact : getArtefacts()) {
                String name = artefact.getInternalPath();

                if (!folder.hasArtefact(name)) {
                    // was deleted
                    artefact.delete();
                } else {
                    AProjectArtefact newArtefact = folder.getArtefact(name);

                    if (newArtefact.isFolder() == artefact.isFolder()) {
                        // update existing
                        artefact.update(newArtefact, user);
                    } else {
                        // the same name but other type
                        artefact.delete();
                    }
                }
            }

            // add new
            for (AProjectArtefact artefact : folder.getArtefacts()) {
                String name = artefact.getInternalPath();
                if (!hasArtefact(name)) {
                    if (artefact.isFolder()) {
                        addFolder(name).update(artefact, user);
                    } else {
                        addResource(name, (AProjectResource) artefact);
                    }
                }
            }
        }
    }

    private final Object lock = new Object();

    protected Map<String, AProjectArtefact> getArtefactsInternal() {
        synchronized (lock) {
            if (artefacts == null) {
                this.artefacts = createInternalArtefacts();
            }
        }
        return artefacts;
    }

    protected Map<String, AProjectArtefact> createInternalArtefacts() {
        HashMap<String, AProjectArtefact> internalArtefacts = new HashMap<String, AProjectArtefact>();
        Collection<FileData> fileDatas;
        try {
            String folderPath = getFolderPath();
            if (isHistoric()) {
                fileDatas = getRepository().listHistory(folderPath);
            } else {
                if (!folderPath.isEmpty() && !folderPath.endsWith("/")) {
                    folderPath += "/";
                }
                fileDatas = getRepository().list(folderPath);
            }
        } catch (IOException ex) {
            throw RuntimeExceptionWrapper.wrap(ex);
        }
        for (FileData fileData : fileDatas) {
            if (!fileData.getName().equals(folderPath)) {
                String artefactName = fileData.getName().substring(folderPath.length() + 1);
                internalArtefacts.put(artefactName, new AProjectResource(getProject(), getRepository(), fileData));
            }
        }
        return internalArtefacts;
    }

    @Override
    public void refresh() {
        super.refresh();
        synchronized (lock) {
            artefacts = null;
        }
    }

    public void setResourceTransformer(ResourceTransformer resourceTransformer) {
        this.resourceTransformer = resourceTransformer;

        if (artefacts != null) {
            for (AProjectArtefact artefact : artefacts.values()) {
                if (artefact instanceof AProjectFolder) {
                    ((AProjectFolder) artefact).setResourceTransformer(resourceTransformer);
                } else if (artefact instanceof AProjectResource) {
                    ((AProjectResource) artefact).setResourceTransformer(resourceTransformer);
                }
            }
        }
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public boolean isHistoric() {
        return historyVersion != null && !(getRepository() instanceof LocalRepository);
    }

    public String getHistoryVersion() {
        return historyVersion;
    }

    public void setHistoryVersion(String historyVersion) {
        this.historyVersion = historyVersion;
    }

    @Override
    public void setFileData(FileData fileData) {
        super.setFileData(fileData);
        if (fileData != null) {
            setFolderPath(fileData.getName());
            setHistoryVersion(fileData.getVersion());
        }
    }

    @Override
    public ArtefactPath getArtefactPath() {
        return new ArtefactPathImpl(getFolderPath());
    }

    @Override
    public String getInternalPath() {
        String projectPath = getProject().getFileData().getName();
        return folderPath.startsWith(projectPath + "/") ? folderPath.substring(projectPath.length() + 1) : folderPath;
    }

    @Override
    public void delete() throws ProjectException {
        for (AProjectArtefact artefact : getArtefacts()) {
            artefact.delete();
        }
        refresh();
    }

    public boolean hasArtefacts() {
        return !getArtefacts().isEmpty();
    }

    public ResourceTransformer getResourceTransformer() {
        return resourceTransformer;
    }
}
