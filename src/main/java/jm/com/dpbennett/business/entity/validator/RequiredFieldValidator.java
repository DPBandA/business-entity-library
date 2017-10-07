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
@FacesValidator("requiredFieldValidator")
public class RequiredFieldValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        // Check for valid names
        if (value != null) {
            if (!BusinessEntityUtils.validateName(value.toString().trim())) {
                throw new ValidatorException(getMessage(component.getId()));
            }
        }
        else {
             throw new ValidatorException(getMessage(component.getId()));
        }

    }

    private FacesMessage getMessage(String componentId) {
        switch (componentId) {
            case "businessOffice":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Business Office", "Please enter a valid Business Office.");
            case "trn":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Taxpayer Registration Number", "Please enter a valid Taxpayer Registration Number or N/A.");
            case "instructions":
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Instuctions Required", "Please enter instructions for this job.");    
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
