/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.InitialLdapContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;
import jm.com.dpbennett.business.entity.util.Security;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "ldapcontext")
@NamedQueries({
    @NamedQuery(name = "findAllLdapContexts", query = "SELECT e FROM LdapContext e ORDER BY e.name"),
    @NamedQuery(name = "findAllActiveLdapContexts", query = "SELECT e FROM LdapContext e WHERE e.active = 1 ORDER BY e.name")
})

public class LdapContext implements BusinessEntity {

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
    private Boolean active;
    @Transient
    private Boolean isDirty;

    public LdapContext() {
        this.name = "";
        this.domainName = "";
        this.initialContextFactory = "";
        this.securityAuthentication = "";
        this.securityPrincipal = "";
        this.securityCredentials = "";
        this.providerUrl = "";
    }

    public LdapContext(String name) {
        this.name = name;
        this.domainName = "";
        this.initialContextFactory = "";
        this.securityAuthentication = "";
        this.securityPrincipal = "";
        this.securityCredentials = "";
        this.providerUrl = "";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }

    @Override
    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
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

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

    public static List<LdapContext> findAllActiveLdapContexts(EntityManager em) {

        try {
            return em.createNamedQuery("findAllActiveLdapContexts", LdapContext.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<LdapContext> findActiveLdapContexts(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<LdapContext> ldapContexts
                    = em.createQuery("SELECT l FROM LdapContext l WHERE "
                            + "( UPPER(l.name) LIKE '%" + value + "%'"
                            + " OR UPPER(l.domainName) like '%" + value + "%'"
                            + " OR UPPER(l.initialContextFactory) like '%" + value + "%'"
                            + " OR UPPER(l.providerUrl) LIKE '%" + value + "%'"
                            + ") AND l.active = 1 ORDER BY l.name", LdapContext.class).getResultList();
            
            return ldapContexts;
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<LdapContext> findLdapContexts(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<LdapContext> ldapContexts
                    = em.createQuery("SELECT l FROM LdapContext l WHERE "
                            + "UPPER(l.name) LIKE '%" + value + "%'"
                            + " OR UPPER(l.domainName) like '%" + value + "%'"
                            + " OR UPPER(l.initialContextFactory) like '%" + value + "%'"
                            + " OR UPPER(l.providerUrl) LIKE '%" + value + "%'"
                            + " ORDER BY l.name", LdapContext.class).getResultList();
            
            return ldapContexts;
            
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

        } catch (NamingException e) {
            System.out.println("Error getting InitialLdapContext: " + e);
        }

        return null;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "LDAP not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    public static LdapContext findActiveLdapContextByName(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<LdapContext> ldapContexts = em.createQuery("SELECT l FROM LdapContext l "
                    + "WHERE l.active = 1 AND UPPER(l.name) "
                    + "= '" + value.toUpperCase() + "'", LdapContext.class).getResultList();
            if (!ldapContexts.isEmpty()) {
                return ldapContexts.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static DirContext getConnection(EntityManager em, String name) {
        LdapContext context = LdapContext.findActiveLdapContextByName(em, name);
        Properties env = new Properties();

        env.put(Context.INITIAL_CONTEXT_FACTORY, context.initialContextFactory);
        env.put(Context.PROVIDER_URL, context.providerUrl);
        env.put(Context.SECURITY_PRINCIPAL, context.securityPrincipal);
        env.put(Context.SECURITY_CREDENTIALS, context.securityCredentials);

        try {
            return new InitialDirContext(env);

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        return null;
    }

    public static DirContext getConnection(LdapContext context) {

        Properties env = new Properties();

        env.put(Context.INITIAL_CONTEXT_FACTORY, context.initialContextFactory);
        env.put(Context.PROVIDER_URL, context.providerUrl);
        env.put(Context.SECURITY_PRINCIPAL, context.securityPrincipal);
        env.put(Context.SECURITY_CREDENTIALS, context.securityCredentials);

        try {
            return new InitialDirContext(env);

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        return null;
    }

    public static boolean addUser(
            EntityManager em,
            LdapContext context,
            User user) {

        String securityKey = SystemOption.getString(em, "securityKey");
        Attributes attributes = new BasicAttributes();
        Attribute inetOrgPerson = new BasicAttribute("objectClass");

        inetOrgPerson.add("inetOrgPerson");

        attributes.put(inetOrgPerson);
        attributes.put("uid", user.getUsername());
        attributes.put("userPassword", Security.encrypt(securityKey, user.getPassword()));
        attributes.put("cn", user.getEmployeeFirstname());
        attributes.put("sn", user.getEmployeeLastname());

        try {
            DirContext connection = getConnection(context);

            if (connection != null) {
                connection.createSubcontext(
                        "uid=" + user.getUsername() + "," + context.domainName,
                        attributes);
                return true;
            }

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        return false;

    }

    public static boolean authenticateUser(
            EntityManager em,
            LdapContext context,
            String userName,
            String userPassword) {

        try {

            String securityKey = SystemOption.getString(em, "securityKey");
            Properties env = new Properties();

            env.put(Context.INITIAL_CONTEXT_FACTORY, context.initialContextFactory);
            env.put(Context.PROVIDER_URL, context.providerUrl);
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + userName + ","
                    + context.domainName);
            env.put(Context.SECURITY_CREDENTIALS,
                    Security.encrypt(securityKey, userPassword));

            DirContext con = new InitialDirContext(env);
            con.close();

            return true;

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        return false;

    }

    public static boolean updateUserPassword(
            EntityManager em,
            LdapContext context,
            String userName,
            String password) {

        String securityKey = SystemOption.getString(em, "securityKey");
        ModificationItem[] mods = new ModificationItem[1];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("userPassword", Security.encrypt(securityKey, password)));

        try {
            DirContext connection = getConnection(context);

            if (connection != null) {
                connection.modifyAttributes(
                        "uid=" + userName + "," + context.domainName,
                        mods);

                return true;
            }

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        return false;

    }

    public static boolean updateUser(
            LdapContext context,
            User user) {

        ModificationItem[] mods = new ModificationItem[2];

        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("cn", user.getEmployeeFirstname()));
        mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("sn", user.getEmployeeLastname()));

        try {
            DirContext connection = getConnection(context);

            if (connection != null) {
                connection.modifyAttributes(
                        "uid=" + user.getUsername() + "," + context.domainName,
                        mods);

                return true;
            }

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        return false;

    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
