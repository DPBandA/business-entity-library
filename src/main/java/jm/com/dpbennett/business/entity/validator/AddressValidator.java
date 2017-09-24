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
//            else if (!BusinessEntityUtils.validateName(address.getAddressLine2().trim())) {
//                throw new ValidatorException(getMessage(component.getId()));
//            } else if (!BusinessEntityUtils.validateName(address.getCity().trim())) {
//                throw new ValidatorException(getMessage(component.getId()));
//            } else if (!BusinessEntityUtils.validateName(address.getStateOrProvince().trim())) {
//                throw new ValidatorException(getMessage(component.getId()));
//            }
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "billingAddress":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please ensure that all billing address fields are entered and contain valid characters.", null);
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter all required (*) fields.", null);
        }
    }
}
