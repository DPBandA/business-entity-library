/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "unitcost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unitcost.findAll", query = "SELECT u FROM Unitcost u")
    , @NamedQuery(name = "Unitcost.findById", query = "SELECT u FROM Unitcost u WHERE u.id = :id")
    , @NamedQuery(name = "Unitcost.findByCost", query = "SELECT u FROM Unitcost u WHERE u.cost = :cost")
    , @NamedQuery(name = "Unitcost.findByDescription", query = "SELECT u FROM Unitcost u WHERE u.description = :description")
    , @NamedQuery(name = "Unitcost.findByEffective", query = "SELECT u FROM Unitcost u WHERE u.effective = :effective")
    , @NamedQuery(name = "Unitcost.findByEffectivedate", query = "SELECT u FROM Unitcost u WHERE u.effectivedate = :effectivedate")
    , @NamedQuery(name = "Unitcost.findByName", query = "SELECT u FROM Unitcost u WHERE u.name = :name")
    , @NamedQuery(name = "Unitcost.findByService", query = "SELECT u FROM Unitcost u WHERE u.service = :service")
    , @NamedQuery(name = "Unitcost.findByUnit", query = "SELECT u FROM Unitcost u WHERE u.unit = :unit")})
public class Unitcost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COST")
    private Double cost;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EFFECTIVE")
    private Boolean effective;
    @Column(name = "EFFECTIVEDATE")
    @Temporal(TemporalType.DATE)
    private Date effectivedate;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SERVICE")
    private String service;
    @Column(name = "UNIT")
    private String unit;
    @JoinColumn(name = "DEPARTMENTUNIT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Departmentunit departmentunitId;
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentId;
    @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Laboratory laboratoryId;

    public Unitcost() {
    }

    public Unitcost(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Departmentunit getDepartmentunitId() {
        return departmentunitId;
    }

    public void setDepartmentunitId(Departmentunit departmentunitId) {
        this.departmentunitId = departmentunitId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Laboratory getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(Laboratory laboratoryId) {
        this.laboratoryId = laboratoryId;
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
        if (!(object instanceof Unitcost)) {
            return false;
        }
        Unitcost other = (Unitcost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Unitcost[ id=" + id + " ]";
    }
    
}
