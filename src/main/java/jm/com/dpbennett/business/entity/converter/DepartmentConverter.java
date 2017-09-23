/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import jm.com.dpbennett.business.entity.Department;

/**
 *
 * @author desbenn
 */
@FacesConverter("departmentConverter")
public class DepartmentConverter extends ConverterAdapter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        
        Department department = Department.findDepartmentByName(getEntityManager(), value);

        if (department == null) {
            department = new Department(value);
        }

        return department;
    }

}
