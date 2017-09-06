/*
 * To change this template, choose Tools | Templates
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
@Table(name = "petrolcompany")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolcompany.findAll", query = "SELECT p FROM Petrolcompany p"),
    @NamedQuery(name = "Petrolcompany.findById", query = "SELECT p FROM Petrolcompany p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolcompany.findByDtype", query = "SELECT p FROM Petrolcompany p WHERE p.dtype = :dtype"),
    @NamedQuery(name = "Petrolcompany.findByTaxregistrationnumber", query = "SELECT p FROM Petrolcompany p WHERE p.taxregistrationnumber = :taxregistrationnumber"),
    @NamedQuery(name = "Petrolcompany.findByNumber", query = "SELECT p FROM Petrolcompany p WHERE p.number = :number"),
    @NamedQuery(name = "Petrolcompany.findByType", query = "SELECT p FROM Petrolcompany p WHERE p.type = :type"),
    @NamedQuery(name = "Petrolcompany.findByDatelastaccessed", query = "SELECT p FROM Petrolcompany p WHERE p.datelastaccessed = :datelastaccessed"),
    @NamedQuery(name = "Petrolcompany.findByDatefirstreceived", query = "SELECT p FROM Petrolcompany p WHERE p.datefirstreceived = :datefirstreceived"),
    @NamedQuery(name = "Petrolcompany.findByName", query = "SELECT p FROM Petrolcompany p WHERE p.name = :name"),
    @NamedQuery(name = "Petrolcompany.findByNotes", query = "SELECT p FROM Petrolcompany p WHERE p.notes = :notes")})
public class Petrolcompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 31)
    @Column(name = "DTYPE")
    private String dtype;
    @Size(max = 255)
    @Column(name = "TAXREGISTRATIONNUMBER")
    private String taxregistrationnumber;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DATELASTACCESSED")
    @Temporal(TemporalType.DATE)
    private Date datelastaccessed;
    @Column(name = "DATEFIRSTRECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datefirstreceived;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "NOTES")
    private String notes;
    @JoinTable(name = "petrolcompany_businessoffice", joinColumns = {
        @JoinColumn(name = "PetrolCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "businessOffices_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Businessoffice> businessofficeList;
    @JoinTable(name = "petrolcompany_contact", joinColumns = {
        @JoinColumn(name = "PetrolCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "contacts_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Contact> contactList;
    @JoinTable(name = "petrolcompany_petrolstation", joinColumns = {
        @JoinColumn(name = "PetrolCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "petrolStations_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Petrolstation> petrolstationList;
    @JoinTable(name = "petrolcompany_address", joinColumns = {
        @JoinColumn(name = "PetrolCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;

    public Petrolcompany() {
    }

    public Petrolcompany(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Petrolcompany)) {
            return false;
        }
        Petrolcompany other = (Petrolcompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolcompany[ id=" + id + " ]";
    }
    
}
