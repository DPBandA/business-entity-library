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
@Table(name = "costcode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costcode.findAll", query = "SELECT c FROM Costcode c")
    , @NamedQuery(name = "Costcode.findById", query = "SELECT c FROM Costcode c WHERE c.id = :id")
    , @NamedQuery(name = "Costcode.findByRate", query = "SELECT c FROM Costcode c WHERE c.rate = :rate")
    , @NamedQuery(name = "Costcode.findByDescription", query = "SELECT c FROM Costcode c WHERE c.description = :description")
    , @NamedQuery(name = "Costcode.findByName", query = "SELECT c FROM Costcode c WHERE c.name = :name")
    , @NamedQuery(name = "Costcode.findByCode", query = "SELECT c FROM Costcode c WHERE c.code = :code")
    , @NamedQuery(name = "Costcode.findByCost", query = "SELECT c FROM Costcode c WHERE c.cost = :cost")})
public class Costcode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATE")
    private Double rate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CODE")
    private String code;
    @Column(name = "COST")
    private Double cost;

    public Costcode() {
    }

    public Costcode(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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
        if (!(object instanceof Costcode)) {
            return false;
        }
        Costcode other = (Costcode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Costcode[ id=" + id + " ]";
    }
    
}
