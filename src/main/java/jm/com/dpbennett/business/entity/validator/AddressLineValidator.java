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
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("addressLineValidator")
public class AddressLineValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {
            if (!BusinessEntityUtils.validateAddressLine(value.toString().trim())) {
                throw new ValidatorException(getMessage(component.getId()));
            }
        }
        else {
             throw new ValidatorException(getMessage(component.getId()));
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "addressType":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Address Type Required", "Please enter a valid type. The characters \" ' and ; are NOT allowed.");
            case "addressLine1":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Address Line 1 Required", "Please enter a valid address line. The characters \" ' and ; are NOT allowed.");
            case "addressCity":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "City/Town Required", "Please enter a valid city. The characters \" ' and ; are NOT allowed.");
            case "parishStateProvince":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Parish/State/Province Required", "Please enter a valid parish/state/province. The characters \" ' and ; are NOT allowed.");    
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
