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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Client;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("jobClientValidator")
public class JobClientValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        BusinessEntity entity = (BusinessEntity) value;
        if (entity != null) {
           
            if (!BusinessEntityUtils.validateText(entity.getName())) {
                throw new ValidatorException(getMessage(component.getId()));
            }
            
        } else {
            throw new ValidatorException(getMessage(component.getId()));
        }
        
        // NB: Validation of billing address and contact may be implemented here

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "client":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Client", "Please enter a valid Client.");
            case "invalidBillingAddress":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Billing Address", "Please enter a valid Billing Address.");        
            case "contact": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Contact", "Please enter a valid Contact.");                   
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
