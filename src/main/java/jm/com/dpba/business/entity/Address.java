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
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id")
    , @NamedQuery(name = "Address.findByPostalcode", query = "SELECT a FROM Address a WHERE a.postalcode = :postalcode")
    , @NamedQuery(name = "Address.findByName", query = "SELECT a FROM Address a WHERE a.name = :name")
    , @NamedQuery(name = "Address.findByStateorprovince", query = "SELECT a FROM Address a WHERE a.stateorprovince = :stateorprovince")
    , @NamedQuery(name = "Address.findByType", query = "SELECT a FROM Address a WHERE a.type = :type")
    , @NamedQuery(name = "Address.findByAddressline2", query = "SELECT a FROM Address a WHERE a.addressline2 = :addressline2")
    , @NamedQuery(name = "Address.findByAddressline1", query = "SELECT a FROM Address a WHERE a.addressline1 = :addressline1")
    , @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city")
    , @NamedQuery(name = "Address.findByCountry", query = "SELECT a FROM Address a WHERE a.country = :country")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "POSTALCODE")
    private String postalcode;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATEORPROVINCE")
    private String stateorprovince;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ADDRESSLINE2")
    private String addressline2;
    @Column(name = "ADDRESSLINE1")
    private String addressline1;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;
    @ManyToMany(mappedBy = "addressList")
    private List<Business> businessList;
    @ManyToMany(mappedBy = "addressList")
    private List<Client> clientList;
    @ManyToMany(mappedBy = "addressList")
    private List<Contact> contactList;
    @ManyToMany(mappedBy = "addressList")
    private List<Foodfactory> foodfactoryList;
    @ManyToMany(mappedBy = "addressList")
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "inspectionaddressId")
    private List<Compliancesurvey> compliancesurveyList;
    @OneToMany(mappedBy = "specifiedreleaselocationId")
    private List<Compliancesurvey> compliancesurveyList1;
    @OneToMany(mappedBy = "specifiedreleaselocationdomesticmarketId")
    private List<Compliancesurvey> compliancesurveyList2;
    @OneToMany(mappedBy = "locationofdetainedproductdomesticmarketId")
    private List<Compliancesurvey> compliancesurveyList3;
    @OneToMany(mappedBy = "addressId")
    private List<Businessoffice> businessofficeList;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateorprovince() {
        return stateorprovince;
    }

    public void setStateorprovince(String stateorprovince) {
        this.stateorprovince = stateorprovince;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
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

    @XmlTransient
    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @XmlTransient
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
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
    public List<Compliancesurvey> getCompliancesurveyList3() {
        return compliancesurveyList3;
    }

    public void setCompliancesurveyList3(List<Compliancesurvey> compliancesurveyList3) {
        this.compliancesurveyList3 = compliancesurveyList3;
    }

    @XmlTransient
    public List<Businessoffice> getBusinessofficeList() {
        return businessofficeList;
    }

    public void setBusinessofficeList(List<Businessoffice> businessofficeList) {
        this.businessofficeList = businessofficeList;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Address[ id=" + id + " ]";
    }
    
}
