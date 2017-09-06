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
import javax.persistence.OneToMany;
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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByTag", query = "SELECT c FROM Client c WHERE c.tag = :tag"),
    @NamedQuery(name = "Client.findByTaxregistrationnumber", query = "SELECT c FROM Client c WHERE c.taxregistrationnumber = :taxregistrationnumber"),
    @NamedQuery(name = "Client.findByNumber", query = "SELECT c FROM Client c WHERE c.number = :number"),
    @NamedQuery(name = "Client.findByType", query = "SELECT c FROM Client c WHERE c.type = :type"),
    @NamedQuery(name = "Client.findByDatelastaccessed", query = "SELECT c FROM Client c WHERE c.datelastaccessed = :datelastaccessed"),
    @NamedQuery(name = "Client.findByDatefirstreceived", query = "SELECT c FROM Client c WHERE c.datefirstreceived = :datefirstreceived"),
    @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
    @NamedQuery(name = "Client.findByActive", query = "SELECT c FROM Client c WHERE c.active = :active"),
    @NamedQuery(name = "Client.findByNotes", query = "SELECT c FROM Client c WHERE c.notes = :notes"),
    @NamedQuery(name = "Client.findByInternal", query = "SELECT c FROM Client c WHERE c.internal = :internal"),
    @NamedQuery(name = "Client.findByDateentered", query = "SELECT c FROM Client c WHERE c.dateentered = :dateentered"),
    @NamedQuery(name = "Client.findByInternational", query = "SELECT c FROM Client c WHERE c.international = :international")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "TAG")
    private Boolean tag;
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
    @Column(name = "ACTIVE")
    private Boolean active;
    @Size(max = 255)
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
    @OneToMany(mappedBy = "clientId")
    private List<Jobcostingbatch> jobcostingbatchList;
    @OneToMany(mappedBy = "clientId")
    private List<Marketproduct> marketproductList;
    @OneToMany(mappedBy = "clientId")
    private List<Productinspection> productinspectionList;
    @OneToMany(mappedBy = "receivedfromId")
    private List<Samplerequest> samplerequestList;
    @OneToMany(mappedBy = "retailoutletId")
    private List<Marketproductsurvey> marketproductsurveyList;
    @OneToMany(mappedBy = "consigneeId")
    private List<Documentinspection> documentinspectionList;
    @OneToMany(mappedBy = "consigneeId")
    private List<Compliancesurvey> compliancesurveyList;
    @OneToMany(mappedBy = "brokerId")
    private List<Compliancesurvey> compliancesurveyList1;
    @OneToMany(mappedBy = "retailoutletId")
    private List<Compliancesurvey> compliancesurveyList2;
    @OneToMany(mappedBy = "externalclientId")
    private List<Legaldocument> legaldocumentList;
    @OneToMany(mappedBy = "applicantId")
    private List<Certification> certificationList;
    @OneToMany(mappedBy = "clientId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "clientId")
    private List<Petrolstation> petrolstationList;
    @OneToMany(mappedBy = "clientId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "externalclientId")
    private List<Documenttracking> documenttrackingList;
    @OneToMany(mappedBy = "clientId")
    private List<Jobsample> jobsampleList;
    @OneToMany(mappedBy = "clientId")
    private List<Scale> scaleList;
    @OneToMany(mappedBy = "brokerId")
    private List<Portofentrydetention> portofentrydetentionList;
    @OneToMany(mappedBy = "consigneeId")
    private List<Portofentrydetention> portofentrydetentionList1;
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

    @XmlTransient
    @JsonIgnore
    public List<Jobcostingbatch> getJobcostingbatchList() {
        return jobcostingbatchList;
    }

    public void setJobcostingbatchList(List<Jobcostingbatch> jobcostingbatchList) {
        this.jobcostingbatchList = jobcostingbatchList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproduct> getMarketproductList() {
        return marketproductList;
    }

    public void setMarketproductList(List<Marketproduct> marketproductList) {
        this.marketproductList = marketproductList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Productinspection> getProductinspectionList() {
        return productinspectionList;
    }

    public void setProductinspectionList(List<Productinspection> productinspectionList) {
        this.productinspectionList = productinspectionList;
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

    @XmlTransient
    @JsonIgnore
    public List<Documentinspection> getDocumentinspectionList() {
        return documentinspectionList;
    }

    public void setDocumentinspectionList(List<Documentinspection> documentinspectionList) {
        this.documentinspectionList = documentinspectionList;
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
    public List<Legaldocument> getLegaldocumentList() {
        return legaldocumentList;
    }

    public void setLegaldocumentList(List<Legaldocument> legaldocumentList) {
        this.legaldocumentList = legaldocumentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
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
    public List<Petrolstation> getPetrolstationList() {
        return petrolstationList;
    }

    public void setPetrolstationList(List<Petrolstation> petrolstationList) {
        this.petrolstationList = petrolstationList;
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
    public List<Documenttracking> getDocumenttrackingList() {
        return documenttrackingList;
    }

    public void setDocumenttrackingList(List<Documenttracking> documenttrackingList) {
        this.documenttrackingList = documenttrackingList;
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
    public List<Scale> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<Scale> scaleList) {
        this.scaleList = scaleList;
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
        return "jm.com.dpba.business.entity.utils.Client[ id=" + id + " ]";
    }
    
}
