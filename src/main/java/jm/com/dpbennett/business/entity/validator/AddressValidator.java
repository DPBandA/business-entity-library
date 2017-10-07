/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

        Address address = (Address) value;
        if (address != null) {
            if (!BusinessEntityUtils.validateName(address.getAddressLine1())) {
                throw new ValidatorException(getMessage(component.getId()));
            } 
        }
        else {
            throw new ValidatorException(getMessage("invalidBillingAddress"));
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "billingAddress":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Billing Address", "Please ensure that all billing address fields are entered and contain valid characters.");
            case "invalidBillingAddress":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Billing Address", "Please enter a valid Billing Address.");    
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
