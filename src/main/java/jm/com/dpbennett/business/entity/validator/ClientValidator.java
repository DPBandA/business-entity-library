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
@FacesValidator("clientValidator")
public class ClientValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        // Check that name does not already exist if this is a new client
        Boolean isNewClient = (Boolean) component.getAttributes().get("isNewClient");
        
        // Check for valid names
        if (!BusinessEntityUtils.validateText(value.toString().trim())) {
            throw new ValidatorException(getMessage(component.getId()));
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "clientName":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Name", "Please enter a valid name.");
            case "trn":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Taxpayer Registration Number", "Please enter a valid Taxpayer Registration Number or N/A.");            
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
