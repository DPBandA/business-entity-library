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
@Table(name = "jobstatusandtracking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobstatusandtracking.findAll", query = "SELECT j FROM Jobstatusandtracking j"),
    @NamedQuery(name = "Jobstatusandtracking.findById", query = "SELECT j FROM Jobstatusandtracking j WHERE j.id = :id"),
    @NamedQuery(name = "Jobstatusandtracking.findByDateandtimeentered", query = "SELECT j FROM Jobstatusandtracking j WHERE j.dateandtimeentered = :dateandtimeentered"),
    @NamedQuery(name = "Jobstatusandtracking.findByStatusnote", query = "SELECT j FROM Jobstatusandtracking j WHERE j.statusnote = :statusnote"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatetransfered", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datetransfered = :datetransfered"),
    @NamedQuery(name = "Jobstatusandtracking.findByExpecteddateofcompletion", query = "SELECT j FROM Jobstatusandtracking j WHERE j.expecteddateofcompletion = :expecteddateofcompletion"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatestatusedited", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datestatusedited = :datestatusedited"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatesamplescollected", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datesamplescollected = :datesamplescollected"),
    @NamedQuery(name = "Jobstatusandtracking.findByDocumentcollected", query = "SELECT j FROM Jobstatusandtracking j WHERE j.documentcollected = :documentcollected"),
    @NamedQuery(name = "Jobstatusandtracking.findBySamplescollectedby", query = "SELECT j FROM Jobstatusandtracking j WHERE j.samplescollectedby = :samplescollectedby"),
    @NamedQuery(name = "Jobstatusandtracking.findByDocumentcollectedby", query = "SELECT j FROM Jobstatusandtracking j WHERE j.documentcollectedby = :documentcollectedby"),
    @NamedQuery(name = "Jobstatusandtracking.findByJobtransferedto", query = "SELECT j FROM Jobstatusandtracking j WHERE j.jobtransferedto = :jobtransferedto"),
    @NamedQuery(name = "Jobstatusandtracking.findByCompleted", query = "SELECT j FROM Jobstatusandtracking j WHERE j.completed = :completed"),
    @NamedQuery(name = "Jobstatusandtracking.findBySamplescollected", query = "SELECT j FROM Jobstatusandtracking j WHERE j.samplescollected = :samplescollected"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatesubmitted", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datesubmitted = :datesubmitted"),
    @NamedQuery(name = "Jobstatusandtracking.findByJobenteredby", query = "SELECT j FROM Jobstatusandtracking j WHERE j.jobenteredby = :jobenteredby"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatedocumentcollected", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datedocumentcollected = :datedocumentcollected"),
    @NamedQuery(name = "Jobstatusandtracking.findByWorkprogress", query = "SELECT j FROM Jobstatusandtracking j WHERE j.workprogress = :workprogress"),
    @NamedQuery(name = "Jobstatusandtracking.findByJobemailfrequency", query = "SELECT j FROM Jobstatusandtracking j WHERE j.jobemailfrequency = :jobemailfrequency"),
    @NamedQuery(name = "Jobstatusandtracking.findByDateproductorsamplereceived", query = "SELECT j FROM Jobstatusandtracking j WHERE j.dateproductorsamplereceived = :dateproductorsamplereceived"),
    @NamedQuery(name = "Jobstatusandtracking.findByProductorsamplereceivedby", query = "SELECT j FROM Jobstatusandtracking j WHERE j.productorsamplereceivedby = :productorsamplereceivedby"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatejobemailwassent", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datejobemailwassent = :datejobemailwassent"),
    @NamedQuery(name = "Jobstatusandtracking.findByDateofcompletion", query = "SELECT j FROM Jobstatusandtracking j WHERE j.dateofcompletion = :dateofcompletion"),
    @NamedQuery(name = "Jobstatusandtracking.findByAlertdate", query = "SELECT j FROM Jobstatusandtracking j WHERE j.alertdate = :alertdate"),
    @NamedQuery(name = "Jobstatusandtracking.findByDateoflastpayment", query = "SELECT j FROM Jobstatusandtracking j WHERE j.dateoflastpayment = :dateoflastpayment"),
    @NamedQuery(name = "Jobstatusandtracking.findByDepositdate", query = "SELECT j FROM Jobstatusandtracking j WHERE j.depositdate = :depositdate"),
    @NamedQuery(name = "Jobstatusandtracking.findByCostingdate", query = "SELECT j FROM Jobstatusandtracking j WHERE j.costingdate = :costingdate"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatecostingapproved", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datecostingapproved = :datecostingapproved"),
    @NamedQuery(name = "Jobstatusandtracking.findByDatecostingcompleted", query = "SELECT j FROM Jobstatusandtracking j WHERE j.datecostingcompleted = :datecostingcompleted"),
    @NamedQuery(name = "Jobstatusandtracking.findByExpectedstartdate", query = "SELECT j FROM Jobstatusandtracking j WHERE j.expectedstartdate = :expectedstartdate"),
    @NamedQuery(name = "Jobstatusandtracking.findByStartdate", query = "SELECT j FROM Jobstatusandtracking j WHERE j.startdate = :startdate")})
public class Jobstatusandtracking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATEANDTIMEENTERED")
    @Temporal(TemporalType.DATE)
    private Date dateandtimeentered;
    @Size(max = 1024)
    @Column(name = "STATUSNOTE")
    private String statusnote;
    @Column(name = "DATETRANSFERED")
    @Temporal(TemporalType.DATE)
    private Date datetransfered;
    @Column(name = "EXPECTEDDATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date expecteddateofcompletion;
    @Column(name = "DATESTATUSEDITED")
    @Temporal(TemporalType.DATE)
    private Date datestatusedited;
    @Column(name = "DATESAMPLESCOLLECTED")
    @Temporal(TemporalType.DATE)
    private Date datesamplescollected;
    @Column(name = "DOCUMENTCOLLECTED")
    private Boolean documentcollected;
    @Size(max = 255)
    @Column(name = "SAMPLESCOLLECTEDBY")
    private String samplescollectedby;
    @Size(max = 255)
    @Column(name = "DOCUMENTCOLLECTEDBY")
    private String documentcollectedby;
    @Size(max = 255)
    @Column(name = "JOBTRANSFEREDTO")
    private String jobtransferedto;
    @Column(name = "COMPLETED")
    private Boolean completed;
    @Column(name = "SAMPLESCOLLECTED")
    private Boolean samplescollected;
    @Column(name = "DATESUBMITTED")
    @Temporal(TemporalType.DATE)
    private Date datesubmitted;
    @Size(max = 255)
    @Column(name = "JOBENTEREDBY")
    private String jobenteredby;
    @Column(name = "DATEDOCUMENTCOLLECTED")
    @Temporal(TemporalType.DATE)
    private Date datedocumentcollected;
    @Size(max = 255)
    @Column(name = "WORKPROGRESS")
    private String workprogress;
    @Column(name = "JOBEMAILFREQUENCY")
    private Integer jobemailfrequency;
    @Column(name = "DATEPRODUCTORSAMPLERECEIVED")
    @Temporal(TemporalType.DATE)
    private Date dateproductorsamplereceived;
    @Size(max = 255)
    @Column(name = "PRODUCTORSAMPLERECEIVEDBY")
    private String productorsamplereceivedby;
    @Column(name = "DATEJOBEMAILWASSENT")
    @Temporal(TemporalType.DATE)
    private Date datejobemailwassent;
    @Column(name = "DATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date dateofcompletion;
    @Column(name = "ALERTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date alertdate;
    @Column(name = "DATEOFLASTPAYMENT")
    @Temporal(TemporalType.DATE)
    private Date dateoflastpayment;
    @Column(name = "DEPOSITDATE")
    @Temporal(TemporalType.DATE)
    private Date depositdate;
    @Column(name = "COSTINGDATE")
    @Temporal(TemporalType.DATE)
    private Date costingdate;
    @Column(name = "DATECOSTINGAPPROVED")
    @Temporal(TemporalType.DATE)
    private Date datecostingapproved;
    @Column(name = "DATECOSTINGCOMPLETED")
    @Temporal(TemporalType.DATE)
    private Date datecostingcompleted;
    @Column(name = "EXPECTEDSTARTDATE")
    @Temporal(TemporalType.DATE)
    private Date expectedstartdate;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;
    @JoinColumn(name = "ENTEREDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee enteredbyId;
    @JoinColumn(name = "TRANSFERREDTO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee transferredtoId;
    @OneToMany(mappedBy = "jobstatusandtrackingId")
    private List<Job> jobList;

    public Jobstatusandtracking() {
    }

    public Jobstatusandtracking(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateandtimeentered() {
        return dateandtimeentered;
    }

    public void setDateandtimeentered(Date dateandtimeentered) {
        this.dateandtimeentered = dateandtimeentered;
    }

    public String getStatusnote() {
        return statusnote;
    }

    public void setStatusnote(String statusnote) {
        this.statusnote = statusnote;
    }

    public Date getDatetransfered() {
        return datetransfered;
    }

    public void setDatetransfered(Date datetransfered) {
        this.datetransfered = datetransfered;
    }

    public Date getExpecteddateofcompletion() {
        return expecteddateofcompletion;
    }

    public void setExpecteddateofcompletion(Date expecteddateofcompletion) {
        this.expecteddateofcompletion = expecteddateofcompletion;
    }

    public Date getDatestatusedited() {
        return datestatusedited;
    }

    public void setDatestatusedited(Date datestatusedited) {
        this.datestatusedited = datestatusedited;
    }

    public Date getDatesamplescollected() {
        return datesamplescollected;
    }

    public void setDatesamplescollected(Date datesamplescollected) {
        this.datesamplescollected = datesamplescollected;
    }

    public Boolean getDocumentcollected() {
        return documentcollected;
    }

    public void setDocumentcollected(Boolean documentcollected) {
        this.documentcollected = documentcollected;
    }

    public String getSamplescollectedby() {
        return samplescollectedby;
    }

    public void setSamplescollectedby(String samplescollectedby) {
        this.samplescollectedby = samplescollectedby;
    }

    public String getDocumentcollectedby() {
        return documentcollectedby;
    }

    public void setDocumentcollectedby(String documentcollectedby) {
        this.documentcollectedby = documentcollectedby;
    }

    public String getJobtransferedto() {
        return jobtransferedto;
    }

    public void setJobtransferedto(String jobtransferedto) {
        this.jobtransferedto = jobtransferedto;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getSamplescollected() {
        return samplescollected;
    }

    public void setSamplescollected(Boolean samplescollected) {
        this.samplescollected = samplescollected;
    }

    public Date getDatesubmitted() {
        return datesubmitted;
    }

    public void setDatesubmitted(Date datesubmitted) {
        this.datesubmitted = datesubmitted;
    }

    public String getJobenteredby() {
        return jobenteredby;
    }

    public void setJobenteredby(String jobenteredby) {
        this.jobenteredby = jobenteredby;
    }

    public Date getDatedocumentcollected() {
        return datedocumentcollected;
    }

    public void setDatedocumentcollected(Date datedocumentcollected) {
        this.datedocumentcollected = datedocumentcollected;
    }

    public String getWorkprogress() {
        return workprogress;
    }

    public void setWorkprogress(String workprogress) {
        this.workprogress = workprogress;
    }

    public Integer getJobemailfrequency() {
        return jobemailfrequency;
    }

    public void setJobemailfrequency(Integer jobemailfrequency) {
        this.jobemailfrequency = jobemailfrequency;
    }

    public Date getDateproductorsamplereceived() {
        return dateproductorsamplereceived;
    }

    public void setDateproductorsamplereceived(Date dateproductorsamplereceived) {
        this.dateproductorsamplereceived = dateproductorsamplereceived;
    }

    public String getProductorsamplereceivedby() {
        return productorsamplereceivedby;
    }

    public void setProductorsamplereceivedby(String productorsamplereceivedby) {
        this.productorsamplereceivedby = productorsamplereceivedby;
    }

    public Date getDatejobemailwassent() {
        return datejobemailwassent;
    }

    public void setDatejobemailwassent(Date datejobemailwassent) {
        this.datejobemailwassent = datejobemailwassent;
    }

    public Date getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(Date dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }

    public Date getAlertdate() {
        return alertdate;
    }

    public void setAlertdate(Date alertdate) {
        this.alertdate = alertdate;
    }

    public Date getDateoflastpayment() {
        return dateoflastpayment;
    }

    public void setDateoflastpayment(Date dateoflastpayment) {
        this.dateoflastpayment = dateoflastpayment;
    }

    public Date getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(Date depositdate) {
        this.depositdate = depositdate;
    }

    public Date getCostingdate() {
        return costingdate;
    }

    public void setCostingdate(Date costingdate) {
        this.costingdate = costingdate;
    }

    public Date getDatecostingapproved() {
        return datecostingapproved;
    }

    public void setDatecostingapproved(Date datecostingapproved) {
        this.datecostingapproved = datecostingapproved;
    }

    public Date getDatecostingcompleted() {
        return datecostingcompleted;
    }

    public void setDatecostingcompleted(Date datecostingcompleted) {
        this.datecostingcompleted = datecostingcompleted;
    }

    public Date getExpectedstartdate() {
        return expectedstartdate;
    }

    public void setExpectedstartdate(Date expectedstartdate) {
        this.expectedstartdate = expectedstartdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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

    public Employee getTransferredtoId() {
        return transferredtoId;
    }

    public void setTransferredtoId(Employee transferredtoId) {
        this.transferredtoId = transferredtoId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
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
        if (!(object instanceof Jobstatusandtracking)) {
            return false;
        }
        Jobstatusandtracking other = (Jobstatusandtracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobstatusandtracking[ id=" + id + " ]";
    }
    
}
