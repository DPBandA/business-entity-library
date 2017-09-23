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
import jm.com.dpbennett.business.entity.Department;

/**
 *
 * @author dbennett
 */
@FacesValidator("subcontractedDepartmentValidator")
public class SubcontractedDepartmentValidator extends ValidatorAdapter {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
      
        if ( value != null) {
            Department subContractedDepartment = Department.findDepartmentByName(getEntityManager(), ((Department) value).getName());
            if (subContractedDepartment == null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department not valid!", null));
            } 
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department not valid!", null));
        }
    }
}
