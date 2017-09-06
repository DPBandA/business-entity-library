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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id")
    , @NamedQuery(name = "Client.findByTag", query = "SELECT c FROM Client c WHERE c.tag = :tag")
    , @NamedQuery(name = "Client.findByTaxregistrationnumber", query = "SELECT c FROM Client c WHERE c.taxregistrationnumber = :taxregistrationnumber")
    , @NamedQuery(name = "Client.findByNumber", query = "SELECT c FROM Client c WHERE c.number = :number")
    , @NamedQuery(name = "Client.findByType", query = "SELECT c FROM Client c WHERE c.type = :type")
    , @NamedQuery(name = "Client.findByDatelastaccessed", query = "SELECT c FROM Client c WHERE c.datelastaccessed = :datelastaccessed")
    , @NamedQuery(name = "Client.findByDatefirstreceived", query = "SELECT c FROM Client c WHERE c.datefirstreceived = :datefirstreceived")
    , @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name")
    , @NamedQuery(name = "Client.findByActive", query = "SELECT c FROM Client c WHERE c.active = :active")
    , @NamedQuery(name = "Client.findByNotes", query = "SELECT c FROM Client c WHERE c.notes = :notes")
    , @NamedQuery(name = "Client.findByInternal", query = "SELECT c FROM Client c WHERE c.internal = :internal")
    , @NamedQuery(name = "Client.findByDateentered", query = "SELECT c FROM Client c WHERE c.dateentered = :dateentered")
    , @NamedQuery(name = "Client.findByInternational", query = "SELECT c FROM Client c WHERE c.international = :international")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TAG")
    private Boolean tag;
    @Column(name = "TAXREGISTRATIONNUMBER")
    private String taxregistrationnumber;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DATELASTACCESSED")
    @Temporal(TemporalType.DATE)
    private Date datelastaccessed;
    @Column(name = "DATEFIRSTRECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datefirstreceived;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "INTERNAL")
    private Boolean internal;
    @Column(name = "DATEENTERED")
    @Temporal(TemporalType.DATE)
    private Date dateentered;
    @Column(name = "INTERNATIONAL")
    private Boolean international;
    @JoinTable(name = "client_contact", joinColumns = {
        @JoinColumn(name = "Client_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "contacts_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Contact> contactList;
    @JoinTable(name = "client_address", joinColumns = {
        @JoinColumn(name = "Client_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @OneToMany(mappedBy = "consigneeId")
    private List<Documentinspection> documentinspectionList;
    @OneToMany(mappedBy = "brokerId")
    private List<Compliancesurvey> compliancesurveyList;
    @OneToMany(mappedBy = "consigneeId")
    private List<Compliancesurvey> compliancesurveyList1;
    @OneToMany(mappedBy = "retailoutletId")
    private List<Compliancesurvey> compliancesurveyList2;
    @OneToMany(mappedBy = "applicantId")
    private List<Certification> certificationList;
    @OneToMany(mappedBy = "clientId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "clientId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "externalclientId")
    private List<Documenttracking> documenttrackingList;
    @OneToMany(mappedBy = "clientId")
    private List<Scale> scaleList;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;
    @JoinColumn(name = "ENTEREDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee enteredbyId;

    public Client() {
    }

    public Client(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Date getDateentered() {
        return dateentered;
    }

    public void setDateentered(Date dateentered) {
        this.dateentered = dateentered;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    @XmlTransient
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @XmlTransient
    public List<Documentinspection> getDocumentinspectionList() {
        return documentinspectionList;
    }

    public void setDocumentinspectionList(List<Documentinspection> documentinspectionList) {
        this.documentinspectionList = documentinspectionList;
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
    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
    }

    @XmlTransient
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
    }

    @XmlTransient
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    @XmlTransient
    public List<Documenttracking> getDocumenttrackingList() {
        return documenttrackingList;
    }

    public void setDocumenttrackingList(List<Documenttracking> documenttrackingList) {
        this.documenttrackingList = documenttrackingList;
    }

    @XmlTransient
    public List<Scale> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<Scale> scaleList) {
        this.scaleList = scaleList;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
    }

    public Employee getEnteredbyId() {
        return enteredbyId;
    }

    public void setEnteredbyId(Employee enteredbyId) {
        this.enteredbyId = enteredbyId;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Client[ id=" + id + " ]";
    }
    
}
