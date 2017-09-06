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
@Table(name = "jobcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobcategory.findAll", query = "SELECT j FROM Jobcategory j"),
    @NamedQuery(name = "Jobcategory.findById", query = "SELECT j FROM Jobcategory j WHERE j.id = :id"),
    @NamedQuery(name = "Jobcategory.findByCategory", query = "SELECT j FROM Jobcategory j WHERE j.category = :category"),
    @NamedQuery(name = "Jobcategory.findByIsearning", query = "SELECT j FROM Jobcategory j WHERE j.isearning = :isearning"),
    @NamedQuery(name = "Jobcategory.findByClassification", query = "SELECT j FROM Jobcategory j WHERE j.classification = :classification"),
    @NamedQuery(name = "Jobcategory.findByActive", query = "SELECT j FROM Jobcategory j WHERE j.active = :active"),
    @NamedQuery(name = "Jobcategory.findByDescription", query = "SELECT j FROM Jobcategory j WHERE j.description = :description")})
public class Jobcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "ISEARNING")
    private Boolean isearning;
    @Size(max = 255)
    @Column(name = "CLASSIFICATION")
    private String classification;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany(mappedBy = "jobcategoryList")
    private List<Department> departmentList;
    @JoinTable(name = "jobcategory_department", joinColumns = {
        @JoinColumn(name = "JobCategory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "departments_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Department> departmentList1;
    @OneToMany(mappedBy = "jobcategoryId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "jobcategoryId")
    private List<Job> jobList;

    public Jobcategory() {
    }

    public Jobcategory(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsearning() {
        return isearning;
    }

    public void setIsearning(Boolean isearning) {
        this.isearning = isearning;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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
    @JsonIgnore
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList1() {
        return departmentList1;
    }

    public void setDepartmentList1(List<Department> departmentList1) {
        this.departmentList1 = departmentList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
    }

    @XmlTransient
    @JsonIgnore
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
        if (!(object instanceof Jobcategory)) {
            return false;
        }
        Jobcategory other = (Jobcategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobcategory[ id=" + id + " ]";
    }
    
}
