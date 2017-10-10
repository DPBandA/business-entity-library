/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

/**
 *
 * @author desbenn
 */
public interface ClientOwner {

    public Client getClient();

    public void setClient(Client client);

    public void setIsClientDirty(Boolean dirty);

    public Boolean getIsClientDirty();
    
    public void setBillingAddress(Address billingAddress);
    
    public Address getBillingAddress();
    
    public void setContact(Contact contact);
    
    public Contact getContact();
    
    public Boolean getIsDirty();

    public void setIsDirty(Boolean dirty);

}
