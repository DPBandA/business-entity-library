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

/**
 *
 * @author desbenn
 */
@FacesConverter("clientAddressConverter")
public class ClientAddressConverter extends ConverterAdapter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Address address = null;

        try {
                            
            Long currentJobClientId = (Long) component.getAttributes().get("currentJobClientId");
           
            if (currentJobClientId != null) {
                
                address = Address.findClientAddressById(getEntityManager(), value, currentJobClientId);
            }

            if (address == null) {
                address = new Address(value);
            }
            
        } catch (Exception e) {
            System.out.println(e);
            address = new Address(value);
        }

        return address;
    }

}
