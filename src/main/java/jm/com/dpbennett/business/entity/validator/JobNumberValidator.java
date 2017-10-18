/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import jm.com.dpbennett.business.entity.Job;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("jobNumberValidator")
public class JobNumberValidator extends ValidatorAdapter {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String currentJobNumber = (String) value;
        Long currentJobId = (Long) component.getAttributes().get("currentJobId");
        Boolean autoGenerateJobNumber = (Boolean) component.getAttributes().get("autoGenerateJobNumber");

        // Check for valid job number
        if (!BusinessEntityUtils.validateText(currentJobNumber.trim())) {
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

        // Validate job number text 
        if (autoGenerateJobNumber) {
            if (!validateJobNumber(currentJobNumber, autoGenerateJobNumber)) {
                throw new ValidatorException(getMessage("invalid"));
            }
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "jobNumber":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Job Number", "Please enter a valid Job Number.");
            case "invalid":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Job Number", "This job cannot be saved because a valid job number was not entered.");
            case "exist":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Job Number Not Unique", "This job cannot be saved because the job number is not unique.");
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
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
