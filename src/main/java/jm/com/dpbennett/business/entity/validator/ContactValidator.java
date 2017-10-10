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
