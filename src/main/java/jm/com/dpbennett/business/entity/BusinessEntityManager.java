/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

/**
 *
 * @author dbennett
 */
public interface BusinessEntityManager {
     public void setDirty(Boolean dirty);
     public Boolean isDirty();     
}
