/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "companyregistration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Companyregistration.findAll", query = "SELECT c FROM Companyregistration c")
    , @NamedQuery(name = "Companyregistration.findById", query = "SELECT c FROM Companyregistration c WHERE c.id = :id")
    , @NamedQuery(name = "Companyregistration.findByName", query = "SELECT c FROM Companyregistration c WHERE c.name = :name")})
public class Companyregistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    public Companyregistration() {
    }

    public Companyregistration(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Companyregistration)) {
            return false;
        }
        Companyregistration other = (Companyregistration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Companyregistration[ id=" + id + " ]";
    }
    
}
