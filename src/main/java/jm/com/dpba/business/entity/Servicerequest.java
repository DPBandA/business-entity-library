/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "servicerequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicerequest.findAll", query = "SELECT s FROM Servicerequest s")
    , @NamedQuery(name = "Servicerequest.findById", query = "SELECT s FROM Servicerequest s WHERE s.id = :id")
    , @NamedQuery(name = "Servicerequest.findByAutogenerateservicerequestnumber", query = "SELECT s FROM Servicerequest s WHERE s.autogenerateservicerequestnumber = :autogenerateservicerequestnumber")
    , @NamedQuery(name = "Servicerequest.findByComment", query = "SELECT s FROM Servicerequest s WHERE s.comment = :comment")
    , @NamedQuery(name = "Servicerequest.findByDateandtimeentered", query = "SELECT s FROM Servicerequest s WHERE s.dateandtimeentered = :dateandtimeentered")
    , @NamedQuery(name = "Servicerequest.findByDateofcompletion", query = "SELECT s FROM Servicerequest s WHERE s.dateofcompletion = :dateofcompletion")
    , @NamedQuery(name = "Servicerequest.findByDatestatusedited", query = "SELECT s FROM Servicerequest s WHERE s.datestatusedited = :datestatusedited")
    , @NamedQuery(name = "Servicerequest.findByDatesubmitted", query = "SELECT s FROM Servicerequest s WHERE s.datesubmitted = :datesubmitted")
    , @NamedQuery(name = "Servicerequest.findByEstimatedturnaroundtimeindays", query = "SELECT s FROM Servicerequest s WHERE s.estimatedturnaroundtimeindays = :estimatedturnaroundtimeindays")
    , @NamedQuery(name = "Servicerequest.findByExpecteddateofcompletion", query = "SELECT s FROM Servicerequest s WHERE s.expecteddateofcompletion = :expecteddateofcompletion")
    , @NamedQuery(name = "Servicerequest.findByJobdescription", query = "SELECT s FROM Servicerequest s WHERE s.jobdescription = :jobdescription")
    , @NamedQuery(name = "Servicerequest.findByName", query = "SELECT s FROM Servicerequest s WHERE s.name = :name")
    , @NamedQuery(name = "Servicerequest.findByPurpose", query = "SELECT s FROM Servicerequest s WHERE s.purpose = :purpose")
    , @NamedQuery(name = "Servicerequest.findByServicerequestnumber", query = "SELECT s FROM Servicerequest s WHERE s.servicerequestnumber = :servicerequestnumber")
    , @NamedQuery(name = "Servicerequest.findByServicerequestsequencenumber", query = "SELECT s FROM Servicerequest s WHERE s.servicerequestsequencenumber = :servicerequestsequencenumber")
    , @NamedQuery(name = "Servicerequest.findByStatusnote", query = "SELECT s FROM Servicerequest s WHERE s.statusnote = :statusnote")})
public class Servicerequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "AUTOGENERATESERVICEREQUESTNUMBER")
    private Boolean autogenerateservicerequestnumber;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "DATEANDTIMEENTERED")
    @Temporal(TemporalType.DATE)
    private Date dateandtimeentered;
    @Column(name = "DATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date dateofcompletion;
    @Column(name = "DATESTATUSEDITED")
    @Temporal(TemporalType.DATE)
    private Date datestatusedited;
    @Column(name = "DATESUBMITTED")
    @Temporal(TemporalType.DATE)
    private Date datesubmitted;
    @Column(name = "ESTIMATEDTURNAROUNDTIMEINDAYS")
    private Integer estimatedturnaroundtimeindays;
    @Column(name = "EXPECTEDDATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date expecteddateofcompletion;
    @Column(name = "JOBDESCRIPTION")
    private String jobdescription;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PURPOSE")
    private String purpose;
    @Column(name = "SERVICEREQUESTNUMBER")
    private String servicerequestnumber;
    @Column(name = "SERVICEREQUESTSEQUENCENUMBER")
    private BigInteger servicerequestsequencenumber;
    @Column(name = "STATUSNOTE")
    private String statusnote;
    @JoinTable(name = "servicerequest_service", joinColumns = {
        @JoinColumn(name = "ServiceRequest_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "services_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Service> serviceList;
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentId;
    @JoinColumn(name = "ASSIGNEDTO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee assignedtoId;
    @JoinColumn(name = "BUSINESSOFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Businessoffice businessofficeId;
    @JoinColumn(name = "CLASSIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Classification classificationId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "SERVICECONTRACT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Servicecontract servicecontractId;
    @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact contactId;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;
    @JoinColumn(name = "ENTEREDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee enteredbyId;
    @JoinColumn(name = "JOBCATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jobcategory jobcategoryId;
    @JoinColumn(name = "JOBSUBCATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jobsubcategory jobsubcategoryId;
    @JoinColumn(name = "SECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Sector sectorId;

    public Servicerequest() {
    }

    public Servicerequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAutogenerateservicerequestnumber() {
        return autogenerateservicerequestnumber;
    }

    public void setAutogenerateservicerequestnumber(Boolean autogenerateservicerequestnumber) {
        this.autogenerateservicerequestnumber = autogenerateservicerequestnumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateandtimeentered() {
        return dateandtimeentered;
    }

    public void setDateandtimeentered(Date dateandtimeentered) {
        this.dateandtimeentered = dateandtimeentered;
    }

    public Date getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(Date dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }

    public Date getDatestatusedited() {
        return datestatusedited;
    }

    public void setDatestatusedited(Date datestatusedited) {
        this.datestatusedited = datestatusedited;
    }

    public Date getDatesubmitted() {
        return datesubmitted;
    }

    public void setDatesubmitted(Date datesubmitted) {
        this.datesubmitted = datesubmitted;
    }

    public Integer getEstimatedturnaroundtimeindays() {
        return estimatedturnaroundtimeindays;
    }

    public void setEstimatedturnaroundtimeindays(Integer estimatedturnaroundtimeindays) {
        this.estimatedturnaroundtimeindays = estimatedturnaroundtimeindays;
    }

    public Date getExpecteddateofcompletion() {
        return expecteddateofcompletion;
    }

    public void setExpecteddateofcompletion(Date expecteddateofcompletion) {
        this.expecteddateofcompletion = expecteddateofcompletion;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getServicerequestnumber() {
        return servicerequestnumber;
    }

    public void setServicerequestnumber(String servicerequestnumber) {
        this.servicerequestnumber = servicerequestnumber;
    }

    public BigInteger getServicerequestsequencenumber() {
        return servicerequestsequencenumber;
    }

    public void setServicerequestsequencenumber(BigInteger servicerequestsequencenumber) {
        this.servicerequestsequencenumber = servicerequestsequencenumber;
    }

    public String getStatusnote() {
        return statusnote;
    }

    public void setStatusnote(String statusnote) {
        this.statusnote = statusnote;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Employee getAssignedtoId() {
        return assignedtoId;
    }

    public void setAssignedtoId(Employee assignedtoId) {
        this.assignedtoId = assignedtoId;
    }

    public Businessoffice getBusinessofficeId() {
        return businessofficeId;
    }

    public void setBusinessofficeId(Businessoffice businessofficeId) {
        this.businessofficeId = businessofficeId;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Servicecontract getServicecontractId() {
        return servicecontractId;
    }

    public void setServicecontractId(Servicecontract servicecontractId) {
        this.servicecontractId = servicecontractId;
    }

    public Contact getContactId() {
        return contactId;
    }

    public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

    public Employee getEditedbyId() {
        return editedbyId;
    }

    public void setEditedbyId(Employee editedbyId) {
        this.editedbyId = editedbyId;
    }

    public Employee getEnteredbyId() {
        return enteredbyId;
    }

    public void setEnteredbyId(Employee enteredbyId) {
        this.enteredbyId = enteredbyId;
    }

    public Jobcategory getJobcategoryId() {
        return jobcategoryId;
    }

    public void setJobcategoryId(Jobcategory jobcategoryId) {
        this.jobcategoryId = jobcategoryId;
    }

    public Jobsubcategory getJobsubcategoryId() {
        return jobsubcategoryId;
    }

    public void setJobsubcategoryId(Jobsubcategory jobsubcategoryId) {
        this.jobsubcategoryId = jobsubcategoryId;
    }

    public Sector getSectorId() {
        return sectorId;
    }

    public void setSectorId(Sector sectorId) {
        this.sectorId = sectorId;
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
        if (!(object instanceof Servicerequest)) {
            return false;
        }
        Servicerequest other = (Servicerequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Servicerequest[ id=" + id + " ]";
    }
    
}
