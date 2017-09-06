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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "business")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Business.findAll", query = "SELECT b FROM Business b")
    , @NamedQuery(name = "Business.findById", query = "SELECT b FROM Business b WHERE b.id = :id")
    , @NamedQuery(name = "Business.findByDatelastaccessed", query = "SELECT b FROM Business b WHERE b.datelastaccessed = :datelastaccessed")
    , @NamedQuery(name = "Business.findByDatefirstreceived", query = "SELECT b FROM Business b WHERE b.datefirstreceived = :datefirstreceived")
    , @NamedQuery(name = "Business.findByName", query = "SELECT b FROM Business b WHERE b.name = :name")
    , @NamedQuery(name = "Business.findByTaxregistrationnumber", query = "SELECT b FROM Business b WHERE b.taxregistrationnumber = :taxregistrationnumber")
    , @NamedQuery(name = "Business.findByNumber", query = "SELECT b FROM Business b WHERE b.number = :number")
    , @NamedQuery(name = "Business.findByType", query = "SELECT b FROM Business b WHERE b.type = :type")
    , @NamedQuery(name = "Business.findByNotes", query = "SELECT b FROM Business b WHERE b.notes = :notes")})
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATELASTACCESSED")
    @Temporal(TemporalType.DATE)
    private Date datelastaccessed;
    @Column(name = "DATEFIRSTRECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datefirstreceived;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TAXREGISTRATIONNUMBER")
    private String taxregistrationnumber;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "NOTES")
    private String notes;
    @JoinTable(name = "business_address", joinColumns = {
        @JoinColumn(name = "Business_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @JoinTable(name = "business_contact", joinColumns = {
        @JoinColumn(name = "Business_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "contacts_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Contact> contactList;
    @OneToMany(mappedBy = "businessId")
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "grantedtoId")
    private List<Certification> certificationList;

    public Business() {
    }

    public Business(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatelastaccessed() {
        return datelastaccessed;
    }

    public void setDatelastaccessed(Date datelastaccessed) {
        this.datelastaccessed = datelastaccessed;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @XmlTransient
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
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
        if (!(object instanceof Business)) {
            return false;
        }
        Business other = (Business) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Business[ id=" + id + " ]";
    }
    
}
