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
        if (!BusinessEntityUtils.validateName(value.toString().trim())) {
            throw new ValidatorException(getMessage(component.getId()));
        }

    }

    private FacesMessage getMessage(String componentId) {
        //switch (componentId) {
        //    case "clientName":
        //        return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter a valid name.", null);
        //    case "trn":
        //        return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter a valid Taxpayer Registration Number or N/A.", null);            
        //    default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter all required (*) fields.", null);
        //}
    }
}
