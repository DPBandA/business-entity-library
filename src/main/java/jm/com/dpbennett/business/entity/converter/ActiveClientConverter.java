/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import jm.com.dpbennett.business.entity.Client;

/**
 *
 * @author desbenn
 */
@FacesConverter("clientConverter")
public class ActiveClientConverter extends ConverterAdapter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
      
       Client client = Client.findActiveClientByName(getEntityManager(), submittedValue, Boolean.FALSE);

        if (client == null) {
            client = new Client(submittedValue);
            client.setName(submittedValue);
        } 
        
        return client;
    }   
}
