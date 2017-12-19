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
import jm.com.dpbennett.business.entity.Address;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("addressValidator")
public class AddressValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        validate(context, component.getId(), (Address) value);

    }
    
    public void validate(FacesContext context, String componentId, Address address) throws ValidatorException {
      
        if (address != null) {
            if (!BusinessEntityUtils.validateName(address.getAddressLine1())) {
                throw new ValidatorException(getMessage(componentId));
            } 
        }
        else {
            throw new ValidatorException(getMessage("invalidBillingAddress"));
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {            
            case "billingAddress":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Billing Address", "Please ensure that all billing address fields are entered and contain valid characters. The characters \" ' and ; are NOT allowed.");
            case "invalidBillingAddress":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Billing Address", "Please enter a valid Billing Address.");    
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
