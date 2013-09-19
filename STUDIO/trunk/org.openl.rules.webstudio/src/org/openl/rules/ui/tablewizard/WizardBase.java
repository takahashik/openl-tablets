package org.openl.rules.ui.tablewizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.validator.constraints.NotBlank;
import org.openl.rules.lang.xls.XlsSheetSourceCodeModule;
import org.openl.rules.lang.xls.XlsWorkbookSourceCodeModule;
import org.openl.rules.lang.xls.syntax.WorkbookSyntaxNode;

import static org.openl.rules.ui.tablewizard.WizardUtils.getMetaInfo;

import org.openl.rules.ui.ProjectModel;
import org.openl.rules.ui.WebStudio;
import org.openl.rules.ui.tablewizard.jsf.BaseWizardBean;
import org.openl.rules.ui.tree.ProjectTreeNode;
import org.openl.rules.webstudio.web.util.WebStudioUtils;

/**
 * @author Aliaksandr Antonik.
 */
public abstract class WizardBase extends BaseWizardBean {
    private static final String SHEET_EXSISTING = "existing";
    private static final String SHEET_NEW = "new";
    private String workbook;
    private Integer worksheetIndex;
    private Map<String, XlsWorkbookSourceCodeModule> workbooks;
    private boolean newWorksheet;
    private boolean wizardFinised;

    @NotBlank(message="Can not be empty")
    private String newWorksheetName;

    /** New table identifier */
    private String newTableUri;

    private final Log log = LogFactory.getLog(WizardBase.class);

    protected XlsSheetSourceCodeModule getDestinationSheet() {
        XlsSheetSourceCodeModule sourceCodeModule;
        XlsWorkbookSourceCodeModule module = workbooks.get(workbook);
        if (newWorksheet) {
            Sheet sheet = module.getWorkbook().createSheet(getNewWorksheetName());
            sourceCodeModule = new XlsSheetSourceCodeModule(sheet, getNewWorksheetName(), module);
        } else {
            Sheet sheet = module.getWorkbook().getSheetAt(getWorksheetIndex());
            sourceCodeModule = new XlsSheetSourceCodeModule(sheet, module.getWorkbook().getSheetName(
                    getWorksheetIndex()), module);
        }
        return sourceCodeModule;
    }

    public String getNewWorksheet() {
        return newWorksheet ? SHEET_NEW : SHEET_EXSISTING;
    }

    public void setNewWorksheet(String value) {
        newWorksheet = SHEET_NEW.equals(value);
    }

    public String getNewWorksheetName() {
        return newWorksheetName;
    }

    public void setNewWorksheetName(String newWorksheetName) {
        this.newWorksheetName = newWorksheetName;
    }

    public String getWorkbook() {
        return workbook;
    }

    public void setWorkbook(String workbook) {
        this.workbook = workbook;
    }

    public String getWorkbookName() {
        String[] parts = workbook.split("/");
        return parts[parts.length - 1];
    }

    public void setWorksheetIndex(Integer worksheetIndex) {
        this.worksheetIndex = worksheetIndex;
    }

    public List<SelectItem> getWorkbooks() {
        List<SelectItem> items = new ArrayList<SelectItem>(workbooks.size());
        for (String wbURI : workbooks.keySet()) {
            String[] parts = wbURI.split("/");
            items.add(new SelectItem(wbURI, parts[parts.length - 1]));
        }

        return items;
    }

    public Integer getWorksheetIndex() {
        return worksheetIndex;
    }

    public String getWorksheetName() {
        Workbook currentWorkbook = workbooks.get(workbook).getWorkbook();
        return currentWorkbook.getSheetName(worksheetIndex);
    }

    public List<SelectItem> getWorksheets() {
        if (workbook == null || workbooks == null) {
            return Collections.emptyList();
        }

        XlsWorkbookSourceCodeModule currentSheet = workbooks.get(workbook);
        if (currentSheet == null) {
            return Collections.emptyList();
        }

        Workbook workbook = currentSheet.getWorkbook();
        List<SelectItem> items = new ArrayList<SelectItem>(workbook.getNumberOfSheets());
        for (int i = 0; i < workbook.getNumberOfSheets(); ++i) {
            items.add(new SelectItem(i, workbook.getSheetName(i)));
        }

        Collections.sort(items, new Comparator<SelectItem>() {
            @Override
            public int compare(SelectItem item1, SelectItem item2) {
                return item1.getLabel().compareToIgnoreCase(item2.getLabel());
            }
        });

        return items;
    }

    protected void initWorkbooks() {
        workbooks = new HashMap<String, XlsWorkbookSourceCodeModule>();

        WorkbookSyntaxNode[] syntaxNodes = getMetaInfo().getXlsModuleNode().getWorkbookSyntaxNodes();
        for (WorkbookSyntaxNode node : syntaxNodes) {
            XlsWorkbookSourceCodeModule module = node.getWorkbookSourceCodeModule();
            workbooks.put(module.getUri(), module);
        }

        if (workbooks.size() > 0) {
            workbook = workbooks.keySet().iterator().next();
        }
    }

    public String getNewTableUri() {
        return newTableUri;
    }

    public void setNewTableUri(String newTableUri) {
        this.newTableUri = newTableUri;
    }

    protected void reset() {
        worksheetIndex = 0;
        workbooks = null;
        newWorksheet = false;
        wizardFinised = false;
        newWorksheetName = StringUtils.EMPTY;
        getModifiedWorkbooks().clear();
    }

    @Override
    public String finish() throws Exception {
        boolean success = false;
        try {
            if (!wizardFinised) {
                wizardFinised = true;
                onFinish();
            }
            doSave();
            success = true;
        } catch (Exception e) {
            log.error("Could not save table: ", e);
            throw e;
        }
        if (success) {
            resetStudio();
        }
        return null;
    }

    private void resetStudio() {
        final WebStudio studio = WebStudioUtils.getWebStudio();
        studio.rebuildModel();
    }
    
    /**
     * Validation for technical name
     * */
    public void validateTechnicalName(FacesContext context, UIComponent toValidate, Object value) {
		FacesMessage message = new FacesMessage();   
		ValidatorException validEx= null;  
		
		try {  
			String name = ((String) value).toUpperCase();
	        
	        if(!this.checkNames(name)){
	        	message.setDetail("Table with such name already exists");
	        	validEx = new ValidatorException(message);  
		        throw validEx;  
	        }    
		  }  
		   catch (Exception e) {                      
		      throw new ValidatorException(message);   
		  }
    }
    
    private boolean checkNames(String techName) {
    	WebStudio studio = WebStudioUtils.getWebStudio();
        ProjectModel model = studio.getModel();
        
        for(ProjectTreeNode node : (Collection<ProjectTreeNode>) model.getAllTreeNodes().getAllNodes()){
        	try{
	        	if(node.getTableSyntaxNode().getMember().getName().equalsIgnoreCase(techName)){
	        		return false;
	        	}
        	}catch(Exception e){
        		
        	}
        	
        }

		return true;
	}

}