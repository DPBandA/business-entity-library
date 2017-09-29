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
import jm.com.dpbennett.business.entity.Job;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author desbenn
 */
@FacesValidator("jobNumberValidator")
public class JobNumberValidator extends ValidatorAdapter {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       
        String currentJobNumber = (String) component.getAttributes().get("currentJobNumber");
        Long currentJobId = (Long) component.getAttributes().get("currentJobId");
        
         // Check for valid names
        if (!BusinessEntityUtils.validateName(value.toString().trim())) {
            throw new ValidatorException(getMessage(component.getId()));
        }

        // Check if job number is unique
        Job existingJob = Job.findJobByJobNumber(getEntityManager(), currentJobNumber);
        if (existingJob != null) {            
            long current_jobid = currentJobId != null ? currentJobId : -1L;
            if (existingJob.getId() != current_jobid) {
                throw new ValidatorException(getMessage("exist"));
            }
        }          

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "jobNumber":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter a valid Job Number.", null);
            case "exist":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "This job cannot be saved because the job number is not unique.", null);            
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter all required (*) fields.", null);
        }
    }
}
