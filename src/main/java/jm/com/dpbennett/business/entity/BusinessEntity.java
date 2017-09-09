/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import javax.persistence.EntityManager;

/**
 *
 * @author Desmond
 */
public interface BusinessEntity {
    public Long getId();
    public void setId(Long id);
    public String getName();
    public void setName(String name);
    public boolean save(EntityManager em);
}
