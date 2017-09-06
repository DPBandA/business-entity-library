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
import javax.persistence.ManyToOne;
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
@Table(name = "businessoffice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Businessoffice.findAll", query = "SELECT b FROM Businessoffice b"),
    @NamedQuery(name = "Businessoffice.findById", query = "SELECT b FROM Businessoffice b WHERE b.id = :id"),
    @NamedQuery(name = "Businessoffice.findByName", query = "SELECT b FROM Businessoffice b WHERE b.name = :name"),
    @NamedQuery(name = "Businessoffice.findByCode", query = "SELECT b FROM Businessoffice b WHERE b.code = :code"),
    @NamedQuery(name = "Businessoffice.findByActive", query = "SELECT b FROM Businessoffice b WHERE b.active = :active")})
public class Businessoffice implements Serializable {
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
    @Column(name = "CODE")
    private String code;
    @Column(name = "ACTIVE")
    private Boolean active;
    @ManyToMany(mappedBy = "businessofficeList")
    private List<Laboratory> laboratoryList;
    @ManyToMany(mappedBy = "businessofficeList")
    private List<Petrolcompany> petrolcompanyList;
    @ManyToMany(mappedBy = "businessofficeList")
    private List<Scalecompany> scalecompanyList;
    @JoinTable(name = "standardsorganization_businessoffice", joinColumns = {
        @JoinColumn(name = "businessOffices_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "StandardsOrganization_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Standardsorganization> standardsorganizationList;
    @OneToMany(mappedBy = "businessofficeId")
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "businessofficeId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "businessofficeId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "regulatoryofficeId")
    private List<Foodsample> foodsampleList;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Address addressId;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;
    @OneToMany(mappedBy = "businessofficeId")
    private List<Factoryinspection> factoryinspectionList;
    @OneToMany(mappedBy = "regulatoryofficeId")
    private List<Jobsample> jobsampleList;
    @OneToMany(mappedBy = "assignedbusinessofficeId")
    private List<Foodfactory> foodfactoryList;

    public Businessoffice() {
    }

    public Businessoffice(Long id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    @JsonIgnore
    public List<Laboratory> getLaboratoryList() {
        return laboratoryList;
    }

    public void setLaboratoryList(List<Laboratory> laboratoryList) {
        this.laboratoryList = laboratoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolcompany> getPetrolcompanyList() {
        return petrolcompanyList;
    }

    public void setPetrolcompanyList(List<Petrolcompany> petrolcompanyList) {
        this.petrolcompanyList = petrolcompanyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Scalecompany> getScalecompanyList() {
        return scalecompanyList;
    }

    public void setScalecompanyList(List<Scalecompany> scalecompanyList) {
        this.scalecompanyList = scalecompanyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Standardsorganization> getStandardsorganizationList() {
        return standardsorganizationList;
    }

    public void setStandardsorganizationList(List<Standardsorganization> standardsorganizationList) {
        this.standardsorganizationList = standardsorganizationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
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

    @XmlTransient
    @JsonIgnore
    public List<Foodsample> getFoodsampleList() {
        return foodsampleList;
    }

    public void setFoodsampleList(List<Foodsample> foodsampleList) {
        this.foodsampleList = foodsampleList;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Factoryinspection> getFactoryinspectionList() {
        return factoryinspectionList;
    }

    public void setFactoryinspectionList(List<Factoryinspection> factoryinspectionList) {
        this.factoryinspectionList = factoryinspectionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobsample> getJobsampleList() {
        return jobsampleList;
    }

    public void setJobsampleList(List<Jobsample> jobsampleList) {
        this.jobsampleList = jobsampleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
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
        if (!(object instanceof Businessoffice)) {
            return false;
        }
        Businessoffice other = (Businessoffice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Businessoffice[ id=" + id + " ]";
    }
    
}
