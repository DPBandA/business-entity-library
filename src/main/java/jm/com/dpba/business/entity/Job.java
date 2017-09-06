/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findById", query = "SELECT j FROM Job j WHERE j.id = :id"),
    @NamedQuery(name = "Job.findByYearreceived", query = "SELECT j FROM Job j WHERE j.yearreceived = :yearreceived"),
    @NamedQuery(name = "Job.findByJobdescription", query = "SELECT j FROM Job j WHERE j.jobdescription = :jobdescription"),
    @NamedQuery(name = "Job.findByEstimatedturnaroundtimeindays", query = "SELECT j FROM Job j WHERE j.estimatedturnaroundtimeindays = :estimatedturnaroundtimeindays"),
    @NamedQuery(name = "Job.findByJobsequencenumber", query = "SELECT j FROM Job j WHERE j.jobsequencenumber = :jobsequencenumber"),
    @NamedQuery(name = "Job.findByReportnumber", query = "SELECT j FROM Job j WHERE j.reportnumber = :reportnumber"),
    @NamedQuery(name = "Job.findByNooftestsorcalibrations", query = "SELECT j FROM Job j WHERE j.nooftestsorcalibrations = :nooftestsorcalibrations"),
    @NamedQuery(name = "Job.findByNumberofsamples", query = "SELECT j FROM Job j WHERE j.numberofsamples = :numberofsamples"),
    @NamedQuery(name = "Job.findByIsearningjob", query = "SELECT j FROM Job j WHERE j.isearningjob = :isearningjob"),
    @NamedQuery(name = "Job.findByAutogeneratejobnumber", query = "SELECT j FROM Job j WHERE j.autogeneratejobnumber = :autogeneratejobnumber"),
    @NamedQuery(name = "Job.findByJobnumber", query = "SELECT j FROM Job j WHERE j.jobnumber = :jobnumber"),
    @NamedQuery(name = "Job.findByNewclient", query = "SELECT j FROM Job j WHERE j.newclient = :newclient"),
    @NamedQuery(name = "Job.findByLocked", query = "SELECT j FROM Job j WHERE j.locked = :locked"),
    @NamedQuery(name = "Job.findByComment", query = "SELECT j FROM Job j WHERE j.comment = :comment"),
    @NamedQuery(name = "Job.findByNooftests", query = "SELECT j FROM Job j WHERE j.nooftests = :nooftests"),
    @NamedQuery(name = "Job.findByNoofcalibrations", query = "SELECT j FROM Job j WHERE j.noofcalibrations = :noofcalibrations"),
    @NamedQuery(name = "Job.findByInstructions", query = "SELECT j FROM Job j WHERE j.instructions = :instructions"),
    @NamedQuery(name = "Job.findByEstimatedturnaroundtimerequired", query = "SELECT j FROM Job j WHERE j.estimatedturnaroundtimerequired = :estimatedturnaroundtimerequired")})
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "YEARRECEIVED")
    private Integer yearreceived;
    @Size(max = 1024)
    @Column(name = "JOBDESCRIPTION")
    private String jobdescription;
    @Column(name = "ESTIMATEDTURNAROUNDTIMEINDAYS")
    private Integer estimatedturnaroundtimeindays;
    @Column(name = "JOBSEQUENCENUMBER")
    private BigInteger jobsequencenumber;
    @Size(max = 255)
    @Column(name = "REPORTNUMBER")
    private String reportnumber;
    @Column(name = "NOOFTESTSORCALIBRATIONS")
    private Integer nooftestsorcalibrations;
    @Column(name = "NUMBEROFSAMPLES")
    private BigInteger numberofsamples;
    @Column(name = "ISEARNINGJOB")
    private Boolean isearningjob;
    @Column(name = "AUTOGENERATEJOBNUMBER")
    private Boolean autogeneratejobnumber;
    @Size(max = 255)
    @Column(name = "JOBNUMBER")
    private String jobnumber;
    @Column(name = "NEWCLIENT")
    private Boolean newclient;
    @Column(name = "LOCKED")
    private Boolean locked;
    @Size(max = 255)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "NOOFTESTS")
    private Integer nooftests;
    @Column(name = "NOOFCALIBRATIONS")
    private Integer noofcalibrations;
    @Size(max = 1024)
    @Column(name = "INSTRUCTIONS")
    private String instructions;
    @Column(name = "ESTIMATEDTURNAROUNDTIMEREQUIRED")
    private Boolean estimatedturnaroundtimerequired;
    @ManyToMany(mappedBy = "jobList")
    private List<Jobcostingbatch> jobcostingbatchList;
    @JoinTable(name = "job_jobsample", joinColumns = {
        @JoinColumn(name = "Job_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "jobSamples_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Jobsample> jobsampleList;
    @OneToMany(mappedBy = "jobId")
    private List<Registration> registrationList;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "ASSIGNEDTO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee assignedtoId;
    @JoinColumn(name = "BUSINESSOFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Businessoffice businessofficeId;
    @JoinColumn(name = "SUBCONTRACTEDDEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department subcontracteddepartmentId;
    @JoinColumn(name = "CLASSIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Classification classificationId;
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentId;
    @JoinColumn(name = "JOBCATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jobcategory jobcategoryId;
    @JoinColumn(name = "JOBCOSTINGANDPAYMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jobcostingandpayment jobcostingandpaymentId;
    @JoinColumn(name = "JOBSTATUSANDTRACKING_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jobstatusandtracking jobstatusandtrackingId;
    @JoinColumn(name = "JOBSUBCATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jobsubcategory jobsubcategoryId;
    @JoinColumn(name = "SECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Sector sectorId;
    @JoinColumn(name = "SERVICECONTRACT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Servicecontract servicecontractId;
    @OneToMany(mappedBy = "jobId")
    private List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList;

    public Job() {
    }

    public Job(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYearreceived() {
        return yearreceived;
    }

    public void setYearreceived(Integer yearreceived) {
        this.yearreceived = yearreceived;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public Integer getEstimatedturnaroundtimeindays() {
        return estimatedturnaroundtimeindays;
    }

    public void setEstimatedturnaroundtimeindays(Integer estimatedturnaroundtimeindays) {
        this.estimatedturnaroundtimeindays = estimatedturnaroundtimeindays;
    }

    public BigInteger getJobsequencenumber() {
        return jobsequencenumber;
    }

    public void setJobsequencenumber(BigInteger jobsequencenumber) {
        this.jobsequencenumber = jobsequencenumber;
    }

    public String getReportnumber() {
        return reportnumber;
    }

    public void setReportnumber(String reportnumber) {
        this.reportnumber = reportnumber;
    }

    public Integer getNooftestsorcalibrations() {
        return nooftestsorcalibrations;
    }

    public void setNooftestsorcalibrations(Integer nooftestsorcalibrations) {
        this.nooftestsorcalibrations = nooftestsorcalibrations;
    }

    public BigInteger getNumberofsamples() {
        return numberofsamples;
    }

    public void setNumberofsamples(BigInteger numberofsamples) {
        this.numberofsamples = numberofsamples;
    }

    public Boolean getIsearningjob() {
        return isearningjob;
    }

    public void setIsearningjob(Boolean isearningjob) {
        this.isearningjob = isearningjob;
    }

    public Boolean getAutogeneratejobnumber() {
        return autogeneratejobnumber;
    }

    public void setAutogeneratejobnumber(Boolean autogeneratejobnumber) {
        this.autogeneratejobnumber = autogeneratejobnumber;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public Boolean getNewclient() {
        return newclient;
    }

    public void setNewclient(Boolean newclient) {
        this.newclient = newclient;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNooftests() {
        return nooftests;
    }

    public void setNooftests(Integer nooftests) {
        this.nooftests = nooftests;
    }

    public Integer getNoofcalibrations() {
        return noofcalibrations;
    }

    public void setNoofcalibrations(Integer noofcalibrations) {
        this.noofcalibrations = noofcalibrations;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean getEstimatedturnaroundtimerequired() {
        return estimatedturnaroundtimerequired;
    }

    public void setEstimatedturnaroundtimerequired(Boolean estimatedturnaroundtimerequired) {
        this.estimatedturnaroundtimerequired = estimatedturnaroundtimerequired;
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
    public List<Jobsample> getJobsampleList() {
        return jobsampleList;
    }

    public void setJobsampleList(List<Jobsample> jobsampleList) {
        this.jobsampleList = jobsampleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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

    public Department getSubcontracteddepartmentId() {
        return subcontracteddepartmentId;
    }

    public void setSubcontracteddepartmentId(Department subcontracteddepartmentId) {
        this.subcontracteddepartmentId = subcontracteddepartmentId;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Jobcategory getJobcategoryId() {
        return jobcategoryId;
    }

    public void setJobcategoryId(Jobcategory jobcategoryId) {
        this.jobcategoryId = jobcategoryId;
    }

    public Jobcostingandpayment getJobcostingandpaymentId() {
        return jobcostingandpaymentId;
    }

    public void setJobcostingandpaymentId(Jobcostingandpayment jobcostingandpaymentId) {
        this.jobcostingandpaymentId = jobcostingandpaymentId;
    }

    public Jobstatusandtracking getJobstatusandtrackingId() {
        return jobstatusandtrackingId;
    }

    public void setJobstatusandtrackingId(Jobstatusandtracking jobstatusandtrackingId) {
        this.jobstatusandtrackingId = jobstatusandtrackingId;
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

    public Servicecontract getServicecontractId() {
        return servicecontractId;
    }

    public void setServicecontractId(Servicecontract servicecontractId) {
        this.servicecontractId = servicecontractId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzlecalibration> getPetrolpumpnozzlecalibrationList() {
        return petrolpumpnozzlecalibrationList;
    }

    public void setPetrolpumpnozzlecalibrationList(List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList) {
        this.petrolpumpnozzlecalibrationList = petrolpumpnozzlecalibrationList;
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Job[ id=" + id + " ]";
    }
    
}
