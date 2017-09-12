/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.management;

import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.JobManagerUser;

/**
 *
 * @author dbennett
 */
public interface UserManagement {

    public JobManagerUser getUser();

    public void setUser(JobManagerUser user);

    public Boolean getUserLoggedIn();

    public void setUserLoggedIn(Boolean userLoggedIn);

    public String getLogonMessage();

    public void setLogonMessage(String logonMessage);

    public Boolean getShowLogin();

    public void setShowLogin(Boolean showLogin);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);
    
    public Boolean validateAndAssociateUser(EntityManager em, String username, String password);
}