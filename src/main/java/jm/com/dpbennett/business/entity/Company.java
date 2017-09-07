/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.util.List;

/**
 *
 * @author DBennett
 */
public interface Company {

    public String getName();

    public void setName(String name);

    public String getType();

    public void setType(String type);

    public void setNumber(String number);

    public String getNumber();

    public List<BusinessOffice> getBusinessOffices();

    public void setBusinessOffices(List<BusinessOffice> businessOffices);
}
