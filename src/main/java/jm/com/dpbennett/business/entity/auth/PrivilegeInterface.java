/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jm.com.dpbennett.business.entity.auth;

import jm.com.dpbennett.business.entity.BusinessEntity;

/**
 *
 * @author Desmond Bennett
 */
public interface PrivilegeInterface extends BusinessEntity {

    String getRoles();

    String getType();

    void setRoles(String roles);

    void setType(String type);

    public String getDescription();

    public void setDescription(String description);

    public String getCategory();

    public void setCategory(String category);

}
