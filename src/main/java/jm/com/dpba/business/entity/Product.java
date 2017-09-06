/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpba.business.entity;

/**
 *
 * @author dbennett
 */
public interface Product {
    public String getName();
    public void setName(String name);
    public String getType();
    public void setType(String type);
    public Manufacturer getManufacturer();
    public void setManufacturer(Manufacturer manufacturer);
}
