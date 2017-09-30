/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author desbenn
 */
@FacesConverter("selectItemConverter")
public class SelectItemConverter extends ConverterAdapter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
        SelectItem selectItem = new SelectItem(value);

        return selectItem;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((SelectItem) value).getValue().toString();
    }
    
    

}
