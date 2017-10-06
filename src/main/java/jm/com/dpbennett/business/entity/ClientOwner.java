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

    public void setClientDirty(Boolean dirty);

    public Boolean isClientDirty();
    
    public void setBillingAddress(Address billingAddress);
    
    public Address getBillingAddress();
    
    public Boolean getDirty();

    public void setDirty(Boolean dirty);

}