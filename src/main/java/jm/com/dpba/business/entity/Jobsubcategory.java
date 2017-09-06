/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "jobsubcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobsubcategory.findAll", query = "SELECT j FROM Jobsubcategory j")
    , @NamedQuery(name = "Jobsubcategory.findById", query = "SELECT j FROM Jobsubcategory j WHERE j.id = :id")
    , @NamedQuery(name = "Jobsubcategory.findByIsEarning", query = "SELECT j FROM Jobsubcategory j WHERE j.isEarning = :isEarning")
    , @NamedQuery(name = "Jobsubcategory.findBySubCategory", query = "SELECT j FROM Jobsubcategory j WHERE j.subCategory = :subCategory")
    , @NamedQuery(name = "Jobsubcategory.findByCategoryId", query = "SELECT j FROM Jobsubcategory j WHERE j.categoryId = :categoryId")
    , @NamedQuery(name = "Jobsubcategory.findByActive", query = "SELECT j FROM Jobsubcategory j WHERE j.active = :active")
    , @NamedQuery(name = "Jobsubcategory.findByDescription", query = "SELECT j FROM Jobsubcategory j WHERE j.description = :description")})
public class Jobsubcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "IsEarning")
    private Boolean isEarning;
    @Column(name = "SubCategory")
    private String subCategory;
    @Column(name = "CategoryId")
    private BigInteger categoryId;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinTable(name = "jobsubcategory_department", joinColumns = {
        @JoinColumn(name = "JobSubCategory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "departments_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Department> departmentList;
    @OneToMany(mappedBy = "jobsubcategoryId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "jobsubcategoryId")
    private List<Job> jobList;

    public Jobsubcategory() {
    }

    public Jobsubcategory(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsEarning() {
        return isEarning;
    }

    public void setIsEarning(Boolean isEarning) {
        this.isEarning = isEarning;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @XmlTransient
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
    }

    @XmlTransient
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
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
        if (!(object instanceof Jobsubcategory)) {
            return false;
        }
        Jobsubcategory other = (Jobsubcategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Jobsubcategory[ id=" + id + " ]";
    }
    
}
