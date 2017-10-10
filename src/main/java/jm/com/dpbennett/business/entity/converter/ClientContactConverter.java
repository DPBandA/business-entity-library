/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import jm.com.dpbennett.business.entity.Contact;

/**
 *
 * @author desbenn
 */
@FacesConverter("clientContactConverter")
public class ClientContactConverter extends ConverterAdapter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Contact contact = null;

        try {
            Long currentJobClientId = (Long) component.getAttributes().get("currentJobClientId");
            
            if (currentJobClientId != null) {                
                contact = Contact.findClientContactById(getEntityManager(), value, currentJobClientId);
            }
           
            if (contact == null) {
                contact = new Contact(value);
            }

        } catch (Exception e) {
            System.out.println(e);
            contact = new Contact(value);
        }

        return contact;
    }
}
