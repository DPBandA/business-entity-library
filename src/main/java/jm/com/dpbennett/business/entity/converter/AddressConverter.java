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
@FacesConverter("addressConverter")
public class AddressConverter extends ConverterAdapter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Address address = Address.findClientAddress(getEntityManager(), value);
        
        if (address == null) {
           address = new Address(value);
        }
       
        return address;
    }
}
