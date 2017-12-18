/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
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
