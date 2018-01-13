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
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("requiredFieldValidator")
public class RequiredFieldValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        // If the custom attribute fieldRequired is set then use it.
        Boolean fieldRequired = (Boolean) component.getAttributes().get("fieldRequired");

        // Check for valid names
        if (fieldRequired == null || fieldRequired == true) {
            if (value != null) {
                if (!BusinessEntityUtils.validateText(value.toString().trim())) {
                    throw new ValidatorException(getMessage(component.getId()));
                }
            } else {
                throw new ValidatorException(getMessage(component.getId()));
            }
        }
        
    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "firstName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "First name Required", "Please enter the first name");
            case "lastName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Last name Required", "Please enter the last name");
            case "user":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username Required", "Please enter a username");
            case "jobDescription":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Job Description Required", "Please enter a job description");
            case "businessOffice":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Business Office", "Please enter a valid business office");
            case "departmentName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department Name Required", "Please enter a department name");
            case "departmentCode":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department Code Required", "Please enter a department code");
            case "departmentHead":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department Head Required", "Please enter the name of the department's head");
            case "departmentActingHead":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department Acting/Deputy Head Required", "Please enter the name of the department's acting/deputy head");
            case "trn":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Taxpayer Registration Number", "Please enter a valid Taxpayer Registration Number or N/A");
            case "instructions":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Instructions Required", "Please enter instructions for this job");
            case "classificationName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Classification Name Required", "Please enter a classification name");
            case "classificationDescription":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Classification Description Required", "Please enter a classification description");
            case "jobCategoryName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Job Category Name Required", "Please enter a job category name");
            case "jobCategoryDescription":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Job Category Description Required", "Please enter a job category description");
            case "jobSubcategoryName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Job Subcategory Name Required", "Please enter a job subcategory name");
            case "jobSubcategoryDescription":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Job Subcategory Description Required", "Please enter a job subcategory description");
            case "sectorName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sector Name Required", "Please enter a sector name");
            case "sectorDescription":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sector Description Required", "Please enter a sector description");
            case "ldapName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "LDAP Name Required", "Please enter an LDAP name");
            case "ldapDomainName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "LDAP Domain Name Required", "Please enter an LDAP domain name");
            case "ldapProviderUrl":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "LDAP URL Required", "Please enter an LDAP URL");
            case "costComponentName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cost Component Name Required", "Please enter a cost component name");
            case "costComponentHoursOrQuantity":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Cost Component Hours/Quantity", "Please enter valid hours/quantity");
            case "costComponentRate":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Cost Component Rate", "Please enter a valid rate");
            case "costComponentCost":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Cost Component Cost", "Please enter a valid cost");
            case "paymentAmount":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Payment Amount", "Please enter a valid payment amount");
            case "discountAmount":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Discount Amount", "Please enter a valid discount amount");
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required fields");
        }
    }
}
