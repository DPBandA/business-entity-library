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
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByPosition", query = "SELECT c FROM Contact c WHERE c.position = :position"),
    @NamedQuery(name = "Contact.findByMiddlename", query = "SELECT c FROM Contact c WHERE c.middlename = :middlename"),
    @NamedQuery(name = "Contact.findByNamesuffix", query = "SELECT c FROM Contact c WHERE c.namesuffix = :namesuffix"),
    @NamedQuery(name = "Contact.findByLastname", query = "SELECT c FROM Contact c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Contact.findBySex", query = "SELECT c FROM Contact c WHERE c.sex = :sex"),
    @NamedQuery(name = "Contact.findByType", query = "SELECT c FROM Contact c WHERE c.type = :type"),
    @NamedQuery(name = "Contact.findByTitle", query = "SELECT c FROM Contact c WHERE c.title = :title"),
    @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
    @NamedQuery(name = "Contact.findByNotes", query = "SELECT c FROM Contact c WHERE c.notes = :notes"),
    @NamedQuery(name = "Contact.findBySalutation", query = "SELECT c FROM Contact c WHERE c.salutation = :salutation"),
    @NamedQuery(name = "Contact.findByFirstname", query = "SELECT c FROM Contact c WHERE c.firstname = :firstname")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "POSITION")
    private String position;
    @Size(max = 255)
    @Column(name = "MIDDLENAME")
    private String middlename;
    @Size(max = 255)
    @Column(name = "NAMESUFFIX")
    private String namesuffix;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 255)
    @Column(name = "SEX")
    private String sex;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 255)
    @Column(name = "SALUTATION")
    private String salutation;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @ManyToMany(mappedBy = "contactList")
    private List<Foodfactory> foodfactoryList;
    @ManyToMany(mappedBy = "contactList")
    private List<Client> clientList;
    @ManyToMany(mappedBy = "contactList")
    private List<Petrolstation> petrolstationList;
    @ManyToMany(mappedBy = "contactList")
    private List<Petrolcompany> petrolcompanyList;
    @JoinTable(name = "contact_phonenumber", joinColumns = {
        @JoinColumn(name = "Contact_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "phoneNumbers_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Phonenumber> phonenumberList;
    @ManyToMany(mappedBy = "contactList")
    private List<Scalecompany> scalecompanyList;
    @ManyToMany(mappedBy = "contactList")
    private List<Business> businessList;
    @JoinTable(name = "contact_address", joinColumns = {
        @JoinColumn(name = "Contact_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @OneToMany(mappedBy = "representativeId")
    private List<Samplerequest> samplerequestList;
    @OneToMany(mappedBy = "retailrepresentativeId")
    private List<Marketproductsurvey> marketproductsurveyList;
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
    @OneToMany(mappedBy = "authorizedbyId")
    private List<Portofentrydetention> portofentrydetentionList;
    @OneToMany(mappedBy = "brokerrepresentativeId")
    private List<Portofentrydetention> portofentrydetentionList1;
    @OneToMany(mappedBy = "consigneerepresentativeId")
    private List<Portofentrydetention> portofentrydetentionList2;

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
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolstation> getPetrolstationList() {
        return petrolstationList;
    }

    public void setPetrolstationList(List<Petrolstation> petrolstationList) {
        this.petrolstationList = petrolstationList;
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
    public List<Phonenumber> getPhonenumberList() {
        return phonenumberList;
    }

    public void setPhonenumberList(List<Phonenumber> phonenumberList) {
        this.phonenumberList = phonenumberList;
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
    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Samplerequest> getSamplerequestList() {
        return samplerequestList;
    }

    public void setSamplerequestList(List<Samplerequest> samplerequestList) {
        this.samplerequestList = samplerequestList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproductsurvey> getMarketproductsurveyList() {
        return marketproductsurveyList;
    }

    public void setMarketproductsurveyList(List<Marketproductsurvey> marketproductsurveyList) {
        this.marketproductsurveyList = marketproductsurveyList;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList() {
        return compliancesurveyList;
    }

    public void setCompliancesurveyList(List<Compliancesurvey> compliancesurveyList) {
        this.compliancesurveyList = compliancesurveyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList1() {
        return compliancesurveyList1;
    }

    public void setCompliancesurveyList1(List<Compliancesurvey> compliancesurveyList1) {
        this.compliancesurveyList1 = compliancesurveyList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList2() {
        return compliancesurveyList2;
    }

    public void setCompliancesurveyList2(List<Compliancesurvey> compliancesurveyList2) {
        this.compliancesurveyList2 = compliancesurveyList2;
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
    public List<Factoryinspection> getFactoryinspectionList() {
        return factoryinspectionList;
    }

    public void setFactoryinspectionList(List<Factoryinspection> factoryinspectionList) {
        this.factoryinspectionList = factoryinspectionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Portofentrydetention> getPortofentrydetentionList() {
        return portofentrydetentionList;
    }

    public void setPortofentrydetentionList(List<Portofentrydetention> portofentrydetentionList) {
        this.portofentrydetentionList = portofentrydetentionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Portofentrydetention> getPortofentrydetentionList1() {
        return portofentrydetentionList1;
    }

    public void setPortofentrydetentionList1(List<Portofentrydetention> portofentrydetentionList1) {
        this.portofentrydetentionList1 = portofentrydetentionList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Portofentrydetention> getPortofentrydetentionList2() {
        return portofentrydetentionList2;
    }

    public void setPortofentrydetentionList2(List<Portofentrydetention> portofentrydetentionList2) {
        this.portofentrydetentionList2 = portofentrydetentionList2;
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
        return "jm.com.dpba.business.entity.utils.Contact[ id=" + id + " ]";
    }
    
}
