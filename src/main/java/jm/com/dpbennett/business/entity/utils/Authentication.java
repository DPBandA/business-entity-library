/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.utils;

import java.util.List;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.InitialLdapContext;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.JobManagerUser;
import jm.com.dpbennett.business.entity.SystemOption;

/**
 *
 * @author Desmond Bennett
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
