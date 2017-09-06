/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
    , @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id")
    , @NamedQuery(name = "Contact.findByPosition", query = "SELECT c FROM Contact c WHERE c.position = :position")
    , @NamedQuery(name = "Contact.findByMiddlename", query = "SELECT c FROM Contact c WHERE c.middlename = :middlename")
    , @NamedQuery(name = "Contact.findByNamesuffix", query = "SELECT c FROM Contact c WHERE c.namesuffix = :namesuffix")
    , @NamedQuery(name = "Contact.findByLastname", query = "SELECT c FROM Contact c WHERE c.lastname = :lastname")
    , @NamedQuery(name = "Contact.findBySex", query = "SELECT c FROM Contact c WHERE c.sex = :sex")
    , @NamedQuery(name = "Contact.findByType", query = "SELECT c FROM Contact c WHERE c.type = :type")
    , @NamedQuery(name = "Contact.findByTitle", query = "SELECT c FROM Contact c WHERE c.title = :title")
    , @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name")
    , @NamedQuery(name = "Contact.findByNotes", query = "SELECT c FROM Contact c WHERE c.notes = :notes")
    , @NamedQuery(name = "Contact.findBySalutation", query = "SELECT c FROM Contact c WHERE c.salutation = :salutation")
    , @NamedQuery(name = "Contact.findByFirstname", query = "SELECT c FROM Contact c WHERE c.firstname = :firstname")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "MIDDLENAME")
    private String middlename;
    @Column(name = "NAMESUFFIX")
    private String namesuffix;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "SALUTATION")
    private String salutation;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @ManyToMany(mappedBy = "contactList")
    private List<Business> businessList;
    @ManyToMany(mappedBy = "contactList")
    private List<Foodfactory> foodfactoryList;
    @ManyToMany(mappedBy = "contactList")
    private List<Client> clientList;
    @JoinTable(name = "contact_address", joinColumns = {
        @JoinColumn(name = "Contact_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;
    @OneToMany(mappedBy = "brokerrepresentativeId")
    private List<Compliancesurvey> compliancesurveyList;
    @OneToMany(mappedBy = "consigneerepresentativeId")
    private List<Compliancesurvey> compliancesurveyList1;
    @OneToMany(mappedBy = "retailrepresentativeId")
    private List<Compliancesurvey> compliancesurveyList2;
    @OneToMany(mappedBy = "contactId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "factoryrepresentativeId")
    private List<Factoryinspection> factoryinspectionList;

    public Contact() {
    }

    public Contact(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getNamesuffix() {
        return namesuffix;
    }

    public void setNamesuffix(String namesuffix) {
        this.namesuffix = namesuffix;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlTransient
    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    @XmlTransient
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
    }

    @XmlTransient
    public List<Compliancesurvey> getCompliancesurveyList() {
        return compliancesurveyList;
    }

    public void setCompliancesurveyList(List<Compliancesurvey> compliancesurveyList) {
        this.compliancesurveyList = compliancesurveyList;
    }

    @XmlTransient
    public List<Compliancesurvey> getCompliancesurveyList1() {
        return compliancesurveyList1;
    }

    public void setCompliancesurveyList1(List<Compliancesurvey> compliancesurveyList1) {
        this.compliancesurveyList1 = compliancesurveyList1;
    }

    @XmlTransient
    public List<Compliancesurvey> getCompliancesurveyList2() {
        return compliancesurveyList2;
    }

    public void setCompliancesurveyList2(List<Compliancesurvey> compliancesurveyList2) {
        this.compliancesurveyList2 = compliancesurveyList2;
    }

    @XmlTransient
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
    }

    @XmlTransient
    public List<Factoryinspection> getFactoryinspectionList() {
        return factoryinspectionList;
    }

    public void setFactoryinspectionList(List<Factoryinspection> factoryinspectionList) {
        this.factoryinspectionList = factoryinspectionList;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Contact[ id=" + id + " ]";
    }
    
}
