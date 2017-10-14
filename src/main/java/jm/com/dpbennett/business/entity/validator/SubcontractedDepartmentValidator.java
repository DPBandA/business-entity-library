/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import jm.com.dpbennett.business.entity.Department;
import jm.com.dpbennett.business.entity.Job;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@FacesValidator("subcontractedDepartmentValidator")
public class SubcontractedDepartmentValidator extends ValidatorAdapter {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Department subContractedDepartment = (Department) value;
        if (subContractedDepartment != null) {
            if (!BusinessEntityUtils.validateText(subContractedDepartment.getName())) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Subcontracted Department", "Please enter a valid Subcontracted Department."));
            }
        }
        else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Subcontracted Department", "Please enter a valid Subcontracted Department."));
        }

        Long currentJobId = (Long) component.getAttributes().get("currentJobId");
        Boolean isSubContracted = (Boolean) component.getAttributes().get("isSubContracted");
        Boolean isToBeSubcontracted = (Boolean) component.getAttributes().get("isToBeSubcontracted");
        String departmentName = (String) component.getAttributes().get("departmentName");

        if (currentJobId != null) {
            Job currentlySavedJob = Job.findJobById(getEntityManager(), currentJobId);
            if (currentlySavedJob != null) {
                if (isSubContracted && isToBeSubcontracted) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Subcontracted Department", "Please enter a valid Subcontracted Department."));
                }
                if (!isSubContracted && currentlySavedJob.getIsSubContracted()) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Subcontracted Department", "Please enter a valid Subcontracted Department."));
                }
            }
        }

        // Check for self contracts    
        if (subContractedDepartment != null) {
            if (subContractedDepartment.getName().equals(departmentName)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Same Departments", "The main and subcontracted departments cannot be the same."));
            }
        }

    }
}
