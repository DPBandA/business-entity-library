/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import jm.com.dpbennett.business.entity.Job;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("turnaroundTimeValidator")
public class TurnaroundTimeValidator extends ValidatorAdapter {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Number tat = (Number) value;
        Boolean tatRequired = (Boolean) component.getAttributes().get("tatRequired");

        if ((tat.intValue() <= 0) && tatRequired) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Estimated Turnaround Time", "A valid estimated turnaround time (TAT) is required and must be provided."));
        }
        else if (tat.intValue() < 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Estimated Turnaround Time", "A valid estimated turnaround time (TAT) is required and must be provided."));
        }

    }  

}
