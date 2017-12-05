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

package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "ldapcontext")
@NamedQueries({
    @NamedQuery(name = "findAllLdapContexts", query = "SELECT e FROM LdapContext e ORDER BY e.name")
})
public class LdapContext implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String domainName;
    private String initialContextFactory;
    private String securityAuthentication;
    private String securityPrincipal;
    private String securityCredentials;
    private String providerUrl;

    public LdapContext() {
        name = "unknown";
    }

    public LdapContext(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getInitialContextFactory() {
        return initialContextFactory;
    }

    public void setInitialContextFactory(String initialContextFactory) {
        this.initialContextFactory = initialContextFactory;
    }

    public String getSecurityAuthentication() {
        return securityAuthentication;
    }

    public void setSecurityAuthentication(String securityAuthentication) {
        this.securityAuthentication = securityAuthentication;
    }

    public String getSecurityPrincipal() {
        return securityPrincipal;
    }

    public void setSecurityPrincipal(String securityPrincipal) {
        this.securityPrincipal = securityPrincipal;
    }

    public String getSecurityCredentials() {
        return securityCredentials;
    }

    public void setSecurityCredentials(String securityCredentials) {
        this.securityCredentials = securityCredentials;
    }

    public String getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LdapContext)) {
            return false;
        }
        LdapContext other = (LdapContext) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static List<LdapContext> findAllLdapContexts(EntityManager em) {

        try {
            return em.createNamedQuery("findAllLdapContexts", LdapContext.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public InitialLdapContext getInitialLDAPContext(String username, String password) {

        try {
            Hashtable env = new Hashtable();
            
            String principal = username + "@" + this.domainName;            
            env.put(Context.INITIAL_CONTEXT_FACTORY, this.initialContextFactory);
            env.put(Context.SECURITY_AUTHENTICATION, this.securityAuthentication);
            env.put(Context.SECURITY_PRINCIPAL, principal);
            env.put(Context.SECURITY_CREDENTIALS, password);
            env.put(Context.PROVIDER_URL, this.providerUrl);

            return new InitialLdapContext(env, null);
            
        } catch (Exception e) {
            System.out.println("Error getting InitialLdapContext: " + e);
        }

        return null;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
