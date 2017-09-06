/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "laboratory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laboratory.findAll", query = "SELECT l FROM Laboratory l"),
    @NamedQuery(name = "Laboratory.findById", query = "SELECT l FROM Laboratory l WHERE l.id = :id"),
    @NamedQuery(name = "Laboratory.findByName", query = "SELECT l FROM Laboratory l WHERE l.name = :name"),
    @NamedQuery(name = "Laboratory.findByNumber", query = "SELECT l FROM Laboratory l WHERE l.number = :number"),
    @NamedQuery(name = "Laboratory.findByType", query = "SELECT l FROM Laboratory l WHERE l.type = :type"),
    @NamedQuery(name = "Laboratory.findByActive", query = "SELECT l FROM Laboratory l WHERE l.active = :active")})
public class Laboratory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ACTIVE")
    private Boolean active;
    @ManyToMany(mappedBy = "laboratoryList")
    private List<Department> departmentList;
    @JoinTable(name = "laboratory_businessoffice", joinColumns = {
        @JoinColumn(name = "Laboratory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "businessOffices_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Businessoffice> businessofficeList;
    @OneToMany(mappedBy = "assignedlabId")
    private List<Foodsample> foodsampleList;
    @OneToMany(mappedBy = "laboratoryId")
    private List<Unitcost> unitcostList;

    public Laboratory() {
    }

    public Laboratory(Long id) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Businessoffice> getBusinessofficeList() {
        return businessofficeList;
    }

    public void setBusinessofficeList(List<Businessoffice> businessofficeList) {
        this.businessofficeList = businessofficeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodsample> getFoodsampleList() {
        return foodsampleList;
    }

    public void setFoodsampleList(List<Foodsample> foodsampleList) {
        this.foodsampleList = foodsampleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Unitcost> getUnitcostList() {
        return unitcostList;
    }

    public void setUnitcostList(List<Unitcost> unitcostList) {
        this.unitcostList = unitcostList;
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
        if (!(object instanceof Laboratory)) {
            return false;
        }
        Laboratory other = (Laboratory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Laboratory[ id=" + id + " ]";
    }
    
}
