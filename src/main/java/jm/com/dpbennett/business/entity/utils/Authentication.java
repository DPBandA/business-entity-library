/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.utils;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.InitialLdapContext;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.JobManagerUser;
import jm.com.dpbennett.business.entity.SystemOption;

/**
 *
 * @author dbennett
 */
public class Authentication {

    private JobManagerUser user;

    public Authentication() {
    }

    public Authentication(JobManagerUser user) {
        this.user = user;
    }

    public JobManagerUser getUser() {
        return user;
    }

    public void setUser(JobManagerUser user) {
        this.user = user;
    }

    public static Boolean checkForLDAPUser(EntityManager em, String username, javax.naming.ldap.LdapContext ctx) {

        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = {"displayName"};

            constraints.setReturningAttributes(attrIDs);

            String name = (String) SystemOption.getOptionValueObject(em, "ldapContextName");
            NamingEnumeration answer = ctx.search(name, "SAMAccountName=" + username, constraints);

            if (!answer.hasMore()) { // Assuming only one match
                // LDAP user not found!
                return Boolean.FALSE;
            }
        } catch (NamingException ex) {
            System.out.println(ex);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public Boolean validateAndAssociateUser(EntityManager em, String username, String password) {
        Boolean userValidated = false;
        InitialLdapContext ctx;

        try {
            List<jm.com.dpbennett.business.entity.LdapContext> ctxs = jm.com.dpbennett.business.entity.LdapContext.findAllActiveLdapContexts(em);

            for (jm.com.dpbennett.business.entity.LdapContext ldapContext : ctxs) {
                ctx = ldapContext.getInitialLDAPContext(username, password);

                if (checkForLDAPUser(em, username, ctx)) {
                    // user exists in LDAP                    
                    userValidated = true;
                    break;
                }
            }

            // get the user if one exists
            if (userValidated) {
                System.out.println("User validated.");

                return true;

            } else {
                System.out.println("User NOT validated!");
                
                return false;
            }

        } catch (Exception e) {
            System.err.println("Problem connecting to directory: " + e);
        }

        return false;
    }

    public ReturnMessage login(EntityManager em, String username, String password) {

        try {
            // Find user and determin if authentication is required for this user
            user = JobManagerUser.findActiveJobManagerUserByUsername(em, username);
            if (user != null) {
                em.refresh(user);
                if (!user.getAuthenticate()) {
//                    PrimeFacesUtils.addMessage("NOT Authenticated!",
//                            "Authentication is not activated. Please contact your System Administrator",
//                            FacesMessage.SEVERITY_WARN);
                } else if (validateAndAssociateUser(em, username, password)) {
                   
                } else {
//                    checkLoginAttemps();
//                    logonMessage = "Please enter a valid username.";
                }
            } else {
//                logonMessage = "Please enter a registered username.";
            }

            // wrap up
            if (user != null) {
                user.logActivity("Logged in", em);
               
//                loginAttempts = 0;

                user.save(em);

            } else {
//                logonMessage = "Login error occured! Please try again or contact the System Administrator";
            }

        } catch (Exception e) {
            System.out.println(e);
//            logonMessage = "Login error occured! Please try again or contact the System Administrator";
        }

        return new ReturnMessage();
    }
}
