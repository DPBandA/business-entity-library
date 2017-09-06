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
@Table(name = "petrolstation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolstation.findAll", query = "SELECT p FROM Petrolstation p"),
    @NamedQuery(name = "Petrolstation.findById", query = "SELECT p FROM Petrolstation p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolstation.findByDatelastaccessed", query = "SELECT p FROM Petrolstation p WHERE p.datelastaccessed = :datelastaccessed"),
    @NamedQuery(name = "Petrolstation.findByDatefirstreceived", query = "SELECT p FROM Petrolstation p WHERE p.datefirstreceived = :datefirstreceived"),
    @NamedQuery(name = "Petrolstation.findByName", query = "SELECT p FROM Petrolstation p WHERE p.name = :name"),
    @NamedQuery(name = "Petrolstation.findByTaxregistrationnumber", query = "SELECT p FROM Petrolstation p WHERE p.taxregistrationnumber = :taxregistrationnumber"),
    @NamedQuery(name = "Petrolstation.findByNumber", query = "SELECT p FROM Petrolstation p WHERE p.number = :number"),
    @NamedQuery(name = "Petrolstation.findByType", query = "SELECT p FROM Petrolstation p WHERE p.type = :type"),
    @NamedQuery(name = "Petrolstation.findByNotes", query = "SELECT p FROM Petrolstation p WHERE p.notes = :notes")})
public class Petrolstation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
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
    @Column(name = "TAXREGISTRATIONNUMBER")
    private String taxregistrationnumber;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "NOTES")
    private String notes;
    @JoinTable(name = "petrolstation_address", joinColumns = {
        @JoinColumn(name = "PetrolStation_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @JoinTable(name = "petrolstation_contact", joinColumns = {
        @JoinColumn(name = "PetrolStation_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "contacts_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Contact> contactList;
    @JoinTable(name = "petrolstation_petrolpump", joinColumns = {
        @JoinColumn(name = "PetrolStation_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "petrolPumps_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Petrolpump> petrolpumpList;
    @ManyToMany(mappedBy = "petrolstationList")
    private List<Petrolcompany> petrolcompanyList;
    @JoinColumn(name = "CERTIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Certification certificationId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "LASTASSIGNEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee lastassigneeId;

    public Petrolstation() {
    }

    public Petrolstation(Long id) {
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
    @JsonIgnore
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
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
    public List<Petrolpump> getPetrolpumpList() {
        return petrolpumpList;
    }

    public void setPetrolpumpList(List<Petrolpump> petrolpumpList) {
        this.petrolpumpList = petrolpumpList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolcompany> getPetrolcompanyList() {
        return petrolcompanyList;
    }

    public void setPetrolcompanyList(List<Petrolcompany> petrolcompanyList) {
        this.petrolcompanyList = petrolcompanyList;
    }

    public Certification getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(Certification certificationId) {
        this.certificationId = certificationId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Employee getLastassigneeId() {
        return lastassigneeId;
    }

    public void setLastassigneeId(Employee lastassigneeId) {
        this.lastassigneeId = lastassigneeId;
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
        if (!(object instanceof Petrolstation)) {
            return false;
        }
        Petrolstation other = (Petrolstation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolstation[ id=" + id + " ]";
    }
    
}
