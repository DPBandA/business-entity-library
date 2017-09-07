/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

/**
 *
 * @author dbennett
 */
public interface Person {

    public String getSex();

    public void setSex(String sex);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getMiddleName();

    public void setMiddleName(String middleName);

    public String getNotes();

    public void setNotes(String notes);

    public String getTitle();

    public void setTitle(String title);

    public String getNameSuffix();

    public void setNameSuffix(String nameSuffix);

}
