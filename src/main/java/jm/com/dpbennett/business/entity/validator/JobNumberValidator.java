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
        Boolean autoGenerateJobNumber = (Boolean) component.getAttributes().get("autoGenerateJobNumber");

        // Check for valid names
        if (!BusinessEntityUtils.validateName(value.toString().trim())) {
            throw new ValidatorException(getMessage(component.getId()));
        }

        // Validate job number text 
        if (autoGenerateJobNumber) {
            if (!validateJobNumber(currentJobNumber, autoGenerateJobNumber)) {
                throw new ValidatorException(getMessage("invalid"));
            }
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
            case "invalid":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "This job cannot be saved because a valid job number was not entered.", null);
            case "exist":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "This job cannot be saved because the job number is not unique.", null);
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter all required (*) fields.", null);
        }
    }

    public Boolean validateJobNumber(String jobNumber, Boolean auto) {
        Integer departmentCode = 0;
        Integer year = 0;
        Long sequenceNumber = 0L;

        String parts[] = jobNumber.split("/");
        if (parts != null) {
            // check for correct number of parts
            if ((parts.length >= 3)
                    && (parts.length <= 5)) {
                // are subgroup code, year and sequence number valid integers/long?
                try {
                    if (auto && parts[0].equals("?")) {
                        // This means the complete job number has not yet
                        // been generate. Ignore for now.
                    } else {
                        departmentCode = Integer.parseInt(parts[0]);
                    }
                    year = Integer.parseInt(parts[1]);
                    if (auto && parts[2].equals("?")) {
                        // This means the complete job number has not yet
                        // been generate. Ignore for now.
                    } else {
                        sequenceNumber = Long.parseLong(parts[2]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    return false;
                }
                // subgroup code, year and deparment code have valid ranges?
                if (auto && parts[0].equals("?")) {
                    // This means the complete job number has not yet
                    // been generate. Ignore for now.
                } else if (departmentCode < 0) {
                    return false;
                }
                if (year < 1970) {
                    return false;
                }
                if (auto && parts[2].equals("?")) {
                    // This means the complete job number has not yet
                    // been generate. Ignore for now.
                } else if (sequenceNumber < 1L) {
                    return false;
                }
                // validate 4th part that can be an integer for a department
                // code or a sample reference(s)
                if (parts.length > 3) {
                    try {
                        departmentCode = Integer.parseInt(parts[3]);
                        if (departmentCode < 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        // this means 4th part is not a department code
                        // and that's ok for now.
                        System.out.println("Job number validation error: This means 4th part is not a department code.: " + e);
                    }
                }
                // all is well here
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
