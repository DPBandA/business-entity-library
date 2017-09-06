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
@Table(name = "scalecompany")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scalecompany.findAll", query = "SELECT s FROM Scalecompany s"),
    @NamedQuery(name = "Scalecompany.findById", query = "SELECT s FROM Scalecompany s WHERE s.id = :id"),
    @NamedQuery(name = "Scalecompany.findByTaxregistrationnumber", query = "SELECT s FROM Scalecompany s WHERE s.taxregistrationnumber = :taxregistrationnumber"),
    @NamedQuery(name = "Scalecompany.findByNumber", query = "SELECT s FROM Scalecompany s WHERE s.number = :number"),
    @NamedQuery(name = "Scalecompany.findByType", query = "SELECT s FROM Scalecompany s WHERE s.type = :type"),
    @NamedQuery(name = "Scalecompany.findByDatelastaccessed", query = "SELECT s FROM Scalecompany s WHERE s.datelastaccessed = :datelastaccessed"),
    @NamedQuery(name = "Scalecompany.findByDatefirstreceived", query = "SELECT s FROM Scalecompany s WHERE s.datefirstreceived = :datefirstreceived"),
    @NamedQuery(name = "Scalecompany.findByName", query = "SELECT s FROM Scalecompany s WHERE s.name = :name"),
    @NamedQuery(name = "Scalecompany.findByNotes", query = "SELECT s FROM Scalecompany s WHERE s.notes = :notes")})
public class Scalecompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
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
    @JoinTable(name = "scalecompany_scale", joinColumns = {
        @JoinColumn(name = "ScaleCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "scales_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Scale> scaleList;
    @JoinTable(name = "scalecompany_businessoffice", joinColumns = {
        @JoinColumn(name = "ScaleCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "businessOffices_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Businessoffice> businessofficeList;
    @JoinTable(name = "scalecompany_contact", joinColumns = {
        @JoinColumn(name = "ScaleCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "contacts_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Contact> contactList;
    @JoinTable(name = "scalecompany_address", joinColumns = {
        @JoinColumn(name = "ScaleCompany_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;

    public Scalecompany() {
    }

    public Scalecompany(Long id) {
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
    public List<Scale> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<Scale> scaleList) {
        this.scaleList = scaleList;
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
        if (!(object instanceof Scalecompany)) {
            return false;
        }
        Scalecompany other = (Scalecompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Scalecompany[ id=" + id + " ]";
    }
    
}
