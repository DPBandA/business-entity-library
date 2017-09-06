/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;



/**
 *
 * @author Desmond
 */
public class JobConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Job job = new Job();

        if (value != null) {            
            job.setJobNumber(value);
        }
        else {
            job.setJobNumber("");
        }

        return job;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Job) value).getJobNumber();
    }
}
