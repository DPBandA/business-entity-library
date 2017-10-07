/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import jm.com.dpbennett.business.entity.Address;
import jm.com.dpbennett.business.entity.Contact;

/**
 *
 * @author desbenn
 */
@FacesConverter("clientContactConverter")
public class ClientContactConverter extends ConverterAdapter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Contact contact = null;//Contact.findContactByName(getEntityManager(), value);
        
        if (contact == null) {
           contact = new Contact(value);
        }
       
        return contact;
    }
}
