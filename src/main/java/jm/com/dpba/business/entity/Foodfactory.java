/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "foodfactory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodfactory.findAll", query = "SELECT f FROM Foodfactory f")
    , @NamedQuery(name = "Foodfactory.findById", query = "SELECT f FROM Foodfactory f WHERE f.id = :id")
    , @NamedQuery(name = "Foodfactory.findByTaxregistrationnumber", query = "SELECT f FROM Foodfactory f WHERE f.taxregistrationnumber = :taxregistrationnumber")
    , @NamedQuery(name = "Foodfactory.findByNumber", query = "SELECT f FROM Foodfactory f WHERE f.number = :number")
    , @NamedQuery(name = "Foodfactory.findByPurpose", query = "SELECT f FROM Foodfactory f WHERE f.purpose = :purpose")
    , @NamedQuery(name = "Foodfactory.findByCode", query = "SELECT f FROM Foodfactory f WHERE f.code = :code")
    , @NamedQuery(name = "Foodfactory.findByType", query = "SELECT f FROM Foodfactory f WHERE f.type = :type")
    , @NamedQuery(name = "Foodfactory.findByDatelastaccessed", query = "SELECT f FROM Foodfactory f WHERE f.datelastaccessed = :datelastaccessed")
    , @NamedQuery(name = "Foodfactory.findByDateregistered", query = "SELECT f FROM Foodfactory f WHERE f.dateregistered = :dateregistered")
    , @NamedQuery(name = "Foodfactory.findByDatefirstreceived", query = "SELECT f FROM Foodfactory f WHERE f.datefirstreceived = :datefirstreceived")
    , @NamedQuery(name = "Foodfactory.findByName", query = "SELECT f FROM Foodfactory f WHERE f.name = :name")
    , @NamedQuery(name = "Foodfactory.findByNotes", query = "SELECT f FROM Foodfactory f WHERE f.notes = :notes")
    , @NamedQuery(name = "Foodfactory.findByDatelastvisited", query = "SELECT f FROM Foodfactory f WHERE f.datelastvisited = :datelastvisited")})
public class Foodfactory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TAXREGISTRATIONNUMBER")
    private String taxregistrationnumber;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "PURPOSE")
    private String purpose;
    @Column(name = "CODE")
    private String code;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DATELASTACCESSED")
    @Temporal(TemporalType.DATE)
    private Date datelastaccessed;
    @Column(name = "DATEREGISTERED")
    @Temporal(TemporalType.DATE)
    private Date dateregistered;
    @Column(name = "DATEFIRSTRECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datefirstreceived;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "DATELASTVISITED")
    @Temporal(TemporalType.DATE)
    private Date datelastvisited;
    @JoinTable(name = "foodfactory_factoryinspection", joinColumns = {
        @JoinColumn(name = "FoodFactory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "inspections_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Factoryinspection> factoryinspectionList;
    @JoinTable(name = "foodfactory_certification", joinColumns = {
        @JoinColumn(name = "FoodFactory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "certifications_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Certification> certificationList;
    @JoinTable(name = "foodfactory_foodfactory", joinColumns = {
        @JoinColumn(name = "FoodFactory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Foodfactory> foodfactoryList;
    @ManyToMany(mappedBy = "foodfactoryList")
    private List<Foodfactory> foodfactoryList1;
    @JoinTable(name = "foodfactory_category", joinColumns = {
        @JoinColumn(name = "FoodProduct_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "categories_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Category> categoryList;
    @JoinTable(name = "foodfactory_contact", joinColumns = {
        @JoinColumn(name = "FoodFactory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "contacts_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Contact> contactList;
    @JoinTable(name = "foodfactory_foodproduct", joinColumns = {
        @JoinColumn(name = "FoodFactory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Foodproduct> foodproductList;
    @JoinTable(name = "foodfactory_address", joinColumns = {
        @JoinColumn(name = "FoodFactory_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @JoinColumn(name = "ASSIGNEDBUSINESSOFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Businessoffice assignedbusinessofficeId;
    @JoinColumn(name = "ASSIGNEDINSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee assignedinspectorId;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;

    public Foodfactory() {
    }

    public Foodfactory(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxregistrationnumber() {
        return taxregistrationnumber;
    }

    public void setTaxregistrationnumber(String taxregistrationnumber) {
        this.taxregistrationnumber = taxregistrationnumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatelastaccessed() {
        return datelastaccessed;
    }

    public void setDatelastaccessed(Date datelastaccessed) {
        this.datelastaccessed = datelastaccessed;
    }

    public Date getDateregistered() {
        return dateregistered;
    }

    public void setDateregistered(Date dateregistered) {
        this.dateregistered = dateregistered;
    }

    public Date getDatefirstreceived() {
        return datefirstreceived;
    }

    public void setDatefirstreceived(Date datefirstreceived) {
        this.datefirstreceived = datefirstreceived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDatelastvisited() {
        return datelastvisited;
    }

    public void setDatelastvisited(Date datelastvisited) {
        this.datelastvisited = datelastvisited;
    }

    @XmlTransient
    public List<Factoryinspection> getFactoryinspectionList() {
        return factoryinspectionList;
    }

    public void setFactoryinspectionList(List<Factoryinspection> factoryinspectionList) {
        this.factoryinspectionList = factoryinspectionList;
    }

    @XmlTransient
    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
    }

    @XmlTransient
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    @XmlTransient
    public List<Foodfactory> getFoodfactoryList1() {
        return foodfactoryList1;
    }

    public void setFoodfactoryList1(List<Foodfactory> foodfactoryList1) {
        this.foodfactoryList1 = foodfactoryList1;
    }

    @XmlTransient
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlTransient
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @XmlTransient
    public List<Foodproduct> getFoodproductList() {
        return foodproductList;
    }

    public void setFoodproductList(List<Foodproduct> foodproductList) {
        this.foodproductList = foodproductList;
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Businessoffice getAssignedbusinessofficeId() {
        return assignedbusinessofficeId;
    }

    public void setAssignedbusinessofficeId(Businessoffice assignedbusinessofficeId) {
        this.assignedbusinessofficeId = assignedbusinessofficeId;
    }

    public Employee getAssignedinspectorId() {
        return assignedinspectorId;
    }

    public void setAssignedinspectorId(Employee assignedinspectorId) {
        this.assignedinspectorId = assignedinspectorId;
    }

    public Employee getEditedbyId() {
        return editedbyId;
    }

    public void setEditedbyId(Employee editedbyId) {
        this.editedbyId = editedbyId;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
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
        if (!(object instanceof Foodfactory)) {
            return false;
        }
        Foodfactory other = (Foodfactory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Foodfactory[ id=" + id + " ]";
    }
    
}
