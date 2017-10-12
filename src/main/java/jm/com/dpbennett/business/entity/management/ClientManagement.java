/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.management;

import jm.com.dpbennett.business.entity.Client;

/**
 *
 * @author dbennett
 */
public interface ClientManagement {

    public Client getCurrentClient();

    public void setCurrentClient(Client client);

    public void createNewClient(Boolean active);

    public void setIsToBeSaved(Boolean save);

}
