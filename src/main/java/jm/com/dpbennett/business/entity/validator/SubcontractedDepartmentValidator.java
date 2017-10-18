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
import jm.com.dpbennett.business.entity.Department;
import jm.com.dpbennett.business.entity.Job;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

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
