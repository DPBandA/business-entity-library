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
@Table(name = "distributor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributor.findAll", query = "SELECT d FROM Distributor d")
    , @NamedQuery(name = "Distributor.findById", query = "SELECT d FROM Distributor d WHERE d.id = :id")
    , @NamedQuery(name = "Distributor.findByPhone", query = "SELECT d FROM Distributor d WHERE d.phone = :phone")
    , @NamedQuery(name = "Distributor.findByFax", query = "SELECT d FROM Distributor d WHERE d.fax = :fax")
    , @NamedQuery(name = "Distributor.findByEmail", query = "SELECT d FROM Distributor d WHERE d.email = :email")
    , @NamedQuery(name = "Distributor.findByName", query = "SELECT d FROM Distributor d WHERE d.name = :name")
    , @NamedQuery(name = "Distributor.findByStreet", query = "SELECT d FROM Distributor d WHERE d.street = :street")
    , @NamedQuery(name = "Distributor.findByPo", query = "SELECT d FROM Distributor d WHERE d.po = :po")
    , @NamedQuery(name = "Distributor.findByCity", query = "SELECT d FROM Distributor d WHERE d.city = :city")
    , @NamedQuery(name = "Distributor.findByCountry", query = "SELECT d FROM Distributor d WHERE d.country = :country")})
public class Distributor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STREET")
    private String street;
    @Column(name = "PO")
    private String po;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;

    public Distributor() {
    }

    public Distributor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(object instanceof Distributor)) {
            return false;
        }
        Distributor other = (Distributor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Distributor[ id=" + id + " ]";
    }
    
}
