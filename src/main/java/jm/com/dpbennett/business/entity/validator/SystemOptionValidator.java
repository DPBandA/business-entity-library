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
@FacesValidator("systemOptionValidator")
public class SystemOptionValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!BusinessEntityUtils.validateText(value.toString().trim())) {
            throw new ValidatorException(getMessage(component.getId()));
        }
    }
    
    private FacesMessage getMessage(String componentId) {
        switch(componentId) {
            case "systemOptionName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name Required", "Please enter an option name.");
            case "systemOptionValue":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value Required", "Please enter an option value.");    
            case "systemOptionValueType":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type Required", "Please enter an option value type.");   
            case "systemOptionCategory":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category Required", "Please enter an option category.");  
            case "systemOptionComment":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Comment Required", "Please enter an option comment.");      
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
