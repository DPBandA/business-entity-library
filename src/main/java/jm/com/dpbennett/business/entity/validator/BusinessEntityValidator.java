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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author desbenn
 */
@FacesValidator("businessEntityValidator")
public class BusinessEntityValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        BusinessEntity entity = (BusinessEntity) value;
        if (entity != null) {
            if (!BusinessEntityUtils.validateText(entity.getName())) {
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
            case "jobClassification": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Classification", "Please enter a valid Classification.");    
            case "client": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Client", "Please enter a valid Client.");    
            case "billingAddress": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Billing Address", "Please enter a valid Billing Address.");           
            case "clientContact": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Contact", "Please enter a valid Contact.");               
            case "department": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Department", "Please enter a valid Department.");    
            case "subContractedDepartment": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Subcontracted Department", "Please enter a valid Subcontracted Department.");      
            case "assignee": 
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Assignee/Department Representative", "This job cannot be saved because a valid assignee/department representative was not entered.");      
            default:
                return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field Value Required", "Please enter all required (*) fields.");
        }
    }
}
