/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "ldapcontext")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ldapcontext.findAll", query = "SELECT l FROM Ldapcontext l"),
    @NamedQuery(name = "Ldapcontext.findById", query = "SELECT l FROM Ldapcontext l WHERE l.id = :id"),
    @NamedQuery(name = "Ldapcontext.findByDomainname", query = "SELECT l FROM Ldapcontext l WHERE l.domainname = :domainname"),
    @NamedQuery(name = "Ldapcontext.findByInitialcontextfactory", query = "SELECT l FROM Ldapcontext l WHERE l.initialcontextfactory = :initialcontextfactory"),
    @NamedQuery(name = "Ldapcontext.findByName", query = "SELECT l FROM Ldapcontext l WHERE l.name = :name"),
    @NamedQuery(name = "Ldapcontext.findByProviderurl", query = "SELECT l FROM Ldapcontext l WHERE l.providerurl = :providerurl"),
    @NamedQuery(name = "Ldapcontext.findBySecurityauthentication", query = "SELECT l FROM Ldapcontext l WHERE l.securityauthentication = :securityauthentication"),
    @NamedQuery(name = "Ldapcontext.findBySecuritycredentials", query = "SELECT l FROM Ldapcontext l WHERE l.securitycredentials = :securitycredentials"),
    @NamedQuery(name = "Ldapcontext.findBySecurityprincipal", query = "SELECT l FROM Ldapcontext l WHERE l.securityprincipal = :securityprincipal")})
public class Ldapcontext implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "DOMAINNAME")
    private String domainname;
    @Size(max = 255)
    @Column(name = "INITIALCONTEXTFACTORY")
    private String initialcontextfactory;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "PROVIDERURL")
    private String providerurl;
    @Size(max = 255)
    @Column(name = "SECURITYAUTHENTICATION")
    private String securityauthentication;
    @Size(max = 255)
    @Column(name = "SECURITYCREDENTIALS")
    private String securitycredentials;
    @Size(max = 255)
    @Column(name = "SECURITYPRINCIPAL")
    private String securityprincipal;

    public Ldapcontext() {
    }

    public Ldapcontext(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public String getInitialcontextfactory() {
        return initialcontextfactory;
    }

    public void setInitialcontextfactory(String initialcontextfactory) {
        this.initialcontextfactory = initialcontextfactory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderurl() {
        return providerurl;
    }

    public void setProviderurl(String providerurl) {
        this.providerurl = providerurl;
    }

    public String getSecurityauthentication() {
        return securityauthentication;
    }

    public void setSecurityauthentication(String securityauthentication) {
        this.securityauthentication = securityauthentication;
    }

    public String getSecuritycredentials() {
        return securitycredentials;
    }

    public void setSecuritycredentials(String securitycredentials) {
        this.securitycredentials = securitycredentials;
    }

    public String getSecurityprincipal() {
        return securityprincipal;
    }

    public void setSecurityprincipal(String securityprincipal) {
        this.securityprincipal = securityprincipal;
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
        if (!(object instanceof Ldapcontext)) {
            return false;
        }
        Ldapcontext other = (Ldapcontext) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Ldapcontext[ id=" + id + " ]";
    }
    
}
