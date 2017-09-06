/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author dbennett
 */
public interface Customer {

    public String getName();

    public void setName(String name);

    public List<Address> getAddresses();

    public void setAddresses(List<Address> addresses);

    public List<Contact> getContacts();

    public void setContacts(List<Contact> contacts);

    public Address getBillingAddress();

    public void setBillingAddress(Address billingAddress);

    public Date getDateLastAccessed();

    public void setDateLastAccessed(Date dateLastAccessed);

    public String getNumber();

    public void setNumber(String number);

    public String getType();

    public void setType(String type);

    public Date getDateFirstReceived();

    public void setDateFirstReceived(Date dateFirstReceived);

    public String getNotes();

    public void setNotes(String notes);

    public String getTaxRegistrationNumber();

    public void setTaxRegistrationNumber(String taxRegistrationNumber);
}
