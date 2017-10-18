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
@FacesValidator("contactValidator")
public class ContactValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {
            if (!BusinessEntityUtils.validateText(value.toString().trim())) {
                throw new ValidatorException(getMessage(component.getId()));
            }
        }
        else {
             throw new ValidatorException(getMessage(component.getId()));
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "contactType":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contact Type Required", "Please enter a valid type. The characters \" ' and ; are NOT allowed.");
            case "contactFirstname":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Firstname Required", "Please enter a valid firstname. The characters \" ' and ; are NOT allowed.");
            case "contactLastname":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lastname Required", "Please enter a valid lastname. The characters \" ' and ; are NOT allowed.");
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
