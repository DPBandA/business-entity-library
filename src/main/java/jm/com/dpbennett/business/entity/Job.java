/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "job")
@NamedQueries({
    @NamedQuery(name = "findAllJobs", query = "SELECT j FROM Job j ORDER BY j.jobNumber")
    ,
    @NamedQuery(name = "findByJobNumber", query = "SELECT j FROM Job j WHERE j.jobNumber = :jobNumber")
})
@XmlRootElement
public class Job implements Serializable, BusinessEntity, ClientOwner {

    private static final Long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobNumber;
    private Boolean autoGenerateJobNumber;
    private Long jobSequenceNumber;
    private String reportNumber;
    @Column(length = 1024)
    private String comment;
    private Long numberOfSamples;
    private Integer estimatedTurnAroundTimeInDays;
    private Boolean estimatedTurnAroundTimeRequired;
    private Boolean locked;
    private Boolean isEarningJob;
    private Boolean newClient;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Classification classification;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Sector sector;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department subContractedDepartment;
    private Integer yearReceived;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Client client;
    @OneToOne(cascade = CascadeType.REFRESH)
    private JobCategory jobCategory;
    @OneToOne(cascade = CascadeType.REFRESH)
    private JobSubCategory jobSubCategory;
    @OneToMany(cascade = CascadeType.ALL)
    private List<JobSample> jobSamples;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedTo;
    @OneToOne(cascade = CascadeType.ALL)
    private JobCostingAndPayment jobCostingAndPayment;
    @OneToOne(cascade = CascadeType.ALL)
    private ServiceContract serviceContract;
    @OneToOne(cascade = CascadeType.ALL)
    private JobStatusAndTracking jobStatusAndTracking;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;
    @Column(length = 1024)
    private String jobDescription;
    @Column(length = 1024)
    private String instructions;
    // Results summary
    private Integer noOfTests;
    private Integer noOfCalibrations;
    private Integer noOfTestsOrCalibrations;
    @Transient
    private Boolean isClientDirty;
    @Transient
    private Boolean isToBeSubcontracted;
    @Transient
    private Boolean isToBeCopied;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address billingAddress;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact contact;
    @Transient
    private Boolean isDirty;

    public Job() {
        this.isToBeSubcontracted = false;
        this.isToBeCopied = false;
        isClientDirty = false;
        jobSamples = new ArrayList<>();
    }

    public Boolean getIsToBeCopied() {
        return isToBeCopied;
    }

    public void setIsToBeCopied(Boolean isToBeCopied) {
        this.isToBeCopied = isToBeCopied;
    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public Job(String jobNumber) {
        this.isToBeSubcontracted = false;
        this.jobNumber = jobNumber;
        jobSamples = new ArrayList<>();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public Address getBillingAddress() {
        return billingAddress;
    }

    @Override
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Boolean getIsToBeSubcontracted() {
        return isToBeSubcontracted;
    }

    public void setIsToBeSubcontracted(Boolean isToBeSubcontracted) {
        this.isToBeSubcontracted = isToBeSubcontracted;
    }

    public Boolean getEstimatedTurnAroundTimeRequired() {
        if (estimatedTurnAroundTimeRequired == null) {
            estimatedTurnAroundTimeRequired = true;
        }
        return estimatedTurnAroundTimeRequired;
    }

    public void setEstimatedTurnAroundTimeRequired(Boolean estimatedTurnAroundTimeRequired) {
        this.estimatedTurnAroundTimeRequired = estimatedTurnAroundTimeRequired;
    }

    public String getInstructions() {
        if (instructions == null) {
            instructions = "";
        }
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean getIsSubContracted() {
        if (getSubContractedDepartment().getName().equals("")
                || getSubContractedDepartment().getName().equals("--")) {
            return false;
        }

        return true;
    }

    public Integer getNoOfTests() {
        if (noOfTests == null) {
            noOfTests = 0;
        }
        return noOfTests;
    }

    public void setNoOfTests(Integer noOfTests) {
        this.noOfTests = noOfTests;
    }

    public Integer getNoOfCalibrations() {
        if (noOfCalibrations == null) {
            noOfCalibrations = 0;
        }
        return noOfCalibrations;
    }

    public void setNoOfCalibrations(Integer noOfCalibrations) {
        this.noOfCalibrations = noOfCalibrations;
    }

    public Job(JobSubCategory jobSubCategory, Double finalCost) {
        this.isToBeSubcontracted = false;
        this.jobCostingAndPayment = new JobCostingAndPayment();
        this.jobCostingAndPayment.setFinalCost(finalCost);
        this.jobSubCategory = jobSubCategory;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getJobDescription() {
        if (jobDescription == null) {
            jobDescription = getDefaultJobDescription();
        } else if (jobDescription.trim().equals("")) {
            jobDescription = getDefaultJobDescription();
        }

        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getDefaultJobDescription() {
        //if (getJobDescription().trim().equals("")) {
        return this.instructions;
        /* NB: Getting this form description can be made an option
         for (JobSample jobSample : getJobSamples()) {
         if (jobDescription.equals("")) {
         jobDescription = jobSample.toString();
         } else {
         jobDescription = jobDescription + ", " + jobSample.toString();
         }
         }
         */
        //}

        //return jobDescription;
    }

    public Boolean getNewClient() {
        return newClient;
    }

    public void setNewClient(Boolean newClient) {
        this.newClient = newClient;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public JobStatusAndTracking getJobStatusAndTracking() {
        return jobStatusAndTracking;
    }

    public void setJobStatusAndTracking(JobStatusAndTracking jobStatusAndTracking) {
        this.jobStatusAndTracking = jobStatusAndTracking;
    }

    public JobCostingAndPayment getJobCostingAndPayment() {
        return jobCostingAndPayment;
    }

    public void setJobCostingAndPayment(JobCostingAndPayment jobCostingAndPayment) {
        this.jobCostingAndPayment = jobCostingAndPayment;
    }

    public Integer getYearReceived() {
        return yearReceived;
    }

    public void setYearReceived(Integer yearReceived) {
        this.yearReceived = yearReceived;
    }

    public String getJobSampleDescriptions() {
        String description = "";

        if (getJobSamples().isEmpty() || hasOnlyDefaultJobSample()) {
            return "None";
        } else {
            for (JobSample jobSample : getJobSamples()) {
                if (description.equals("")) {
                    description = jobSample.toString();
                } else {
                    description = description + ", " + jobSample.toString();
                }
            }
        }

        return description;
    }

    public Boolean hasOnlyDefaultJobSample() {
        if ((getJobSamples().size() == 1) && (getJobSamples().get(0).getDescription().trim().equals("--"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return empty list if the only job sample is the default sample
     *
     * @return
     */
    public List<JobSample> getFilteredJobSamples() {
        if (hasOnlyDefaultJobSample()) {
            return new ArrayList<>();
        } else {
            return getJobSamples();
        }
    }

    @XmlTransient
    @JsonIgnore
    public List<JobSample> getJobSamples() {
        if (jobSamples != null) {
            Collections.sort(jobSamples);
        } else {
            jobSamples = new ArrayList<>();
        }

        return jobSamples;
    }

    public BusinessOffice getBusinessOffice() {
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    public ServiceContract getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContract serviceContract) {
        this.serviceContract = serviceContract;
    }

    public Boolean getAutoGenerateJobNumber() {
        if (autoGenerateJobNumber == null) {
            autoGenerateJobNumber = true;
        }
        return autoGenerateJobNumber;
    }

    public void setAutoGenerateJobNumber(Boolean autoGenerateJobNumber) {
        this.autoGenerateJobNumber = autoGenerateJobNumber;
    }

    @Override
    public void setIsClientDirty(Boolean dirty) {
        isClientDirty = dirty;
    }

    @Override
    public Boolean getIsClientDirty() {
        return isClientDirty;
    }

    @Override
    public Client getClient() {
        if (client == null) {
            return new Client("");
        }
        return client;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    public Department getDepartment() {
        if (department == null) {
            return new Department("");
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartmentAssignedToJob() {

        if (getSubContractedDepartment().getName().equals("--")) {
            // This is not a subcontracted job see return to parent department            
            return getDepartment();
        } else {
            return getSubContractedDepartment();
        }
    }

    public Employee getAssignedTo() {
        if (assignedTo == null) {
            return new Employee();
        }
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public JobSubCategory getJobSubCategory() {
        return jobSubCategory;
    }

    public void setJobSubCategory(JobSubCategory jobSubCategory) {
        this.jobSubCategory = jobSubCategory;
    }

    public Department getSubContractedDepartment() {
        if (subContractedDepartment == null) {
            return new Department("");
        }
        return subContractedDepartment;
    }

    public void setSubContractedDepartment(Department subContractedDepartment) {
        this.subContractedDepartment = subContractedDepartment;
    }

    public String getComment() {
        if (comment == null) {
            comment = "";
        }
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEstimatedTurnAroundTimeInDays() {
        if (estimatedTurnAroundTimeInDays == null) {
            estimatedTurnAroundTimeInDays = 0;
        }
        return estimatedTurnAroundTimeInDays;
    }

    public void setEstimatedTurnAroundTimeInDays(Integer estimatedTurnAroundTimeInDays) {
        this.estimatedTurnAroundTimeInDays = estimatedTurnAroundTimeInDays;
    }

    public Boolean getIsEarningJob() {
        return isEarningJob;
    }

    public void setIsEarningJob(Boolean isEarningJob) {
        this.isEarningJob = isEarningJob;
    }

    public Long getJobSequenceNumber() {
        return jobSequenceNumber;
    }

    public void setJobSequenceNumber(Long jobSequenceNumber) {
        this.jobSequenceNumber = jobSequenceNumber;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getNoOfTestsOrCalibrations() {
        if (noOfTestsOrCalibrations == null) {
            noOfTestsOrCalibrations = 0;
        }
        return noOfTestsOrCalibrations;
    }

    public void setNoOfTestsOrCalibrations(Integer noOfTestsOrCalibrations) {
        this.noOfTestsOrCalibrations = noOfTestsOrCalibrations;
    }

    public Long getNumberOfSamples() {
        if (numberOfSamples == null) {
            numberOfSamples = 0L;
        }
        return numberOfSamples;
    }

    public Long getNumberOfSampleProducts() {
        Long total = 0L;

        for (JobSample jobSample : getJobSamples()) {
            total = total + jobSample.getQuantity();
        }

        return total;
    }

    public void setNumberOfSamples(Long numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getJobNumber() {
        if (jobNumber == null) {
            jobNumber = "";
        }
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return getJobNumber();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {
    }

    public static Job findLastClientJob(EntityManager em, Client client) {
        Job lastJob = null;
        String searchQuery;

        // build query based on id or name
        if (client.getId() != null) {
            searchQuery
                    = "SELECT job FROM Job job"
                    + " JOIN job.client client"
                    + " WHERE client.id = " + client.getId()
                    + " ORDER BY client.name";
        } else if (client.getName() != null) {
            searchQuery
                    = "SELECT job FROM Job job"
                    + " JOIN job.client client"
                    + " WHERE client.name = " + client.getName()
                    + " ORDER BY client.name";
        } else {
            return lastJob;
        }
        // find last job if any
        List<Job> jobs = em.createQuery(searchQuery, Job.class).getResultList();
        if (jobs != null) {
            if (jobs.size() > 0) {
                lastJob = jobs.get(jobs.size() - 1);
            }
        }

        return lastJob;
    }

    public static List<Job> findJobsByDateSearchField(
            EntityManager em,
            JobManagerUser user,
            String dateSearchField,
            String jobType,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate,
            Boolean includeSampleSearch) {

        List<Job> foundJobs;
        String searchQuery = null;
        String searchText;
        String searchTextAndClause;
        String sampleSearchWhereClause = "";
        String sampleSearchJoinClause = "";

        // get rid of any single quotes from text and ensure
        // that it is not null
        if (originalSearchText != null) {
            searchText = originalSearchText.trim().replaceAll("'", "''");
        } else {
            searchText = "";
        }

        // include the search for samples?
        if (includeSampleSearch) {
            sampleSearchWhereClause
                    = " OR UPPER(jobSamples.reference) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.description) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productBrand) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productModel) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productSerialNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productCode) LIKE '%" + searchText.toUpperCase() + "%'";

            sampleSearchJoinClause = " JOIN job.jobSamples jobSamples";
        }
        switch (searchType) {
            case "Parent jobs only":
                searchTextAndClause
                        = " AND subContractedDepartment.name = '--' AND ("
                        + " UPPER(businessOffice.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        //+ " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + sampleSearchWhereClause
                        + " OR UPPER(job.reportNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobStatusAndTracking.statusNote) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.instructions) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.invoiceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + sampleSearchJoinClause
                        + " JOIN job.businessOffice businessOffice"
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " JOIN job.classification classification"
                        + " JOIN job.sector sector"
                        + " JOIN job.client client"
                        + " JOIN job.jobCategory jobCategory"
                        + " JOIN job.jobSubCategory jobSubCategory"
                        + " JOIN job.assignedTo assignedTo"
                        + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "General":
                searchTextAndClause
                        = " AND ("
                        + " UPPER(businessOffice.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + sampleSearchWhereClause
                        + " OR UPPER(job.reportNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobStatusAndTracking.statusNote) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.instructions) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.invoiceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + sampleSearchJoinClause
                        + " JOIN job.businessOffice businessOffice"
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " JOIN job.classification classification"
                        + " JOIN job.sector sector"
                        + " JOIN job.client client"
                        + " JOIN job.jobCategory jobCategory"
                        + " JOIN job.jobSubCategory jobSubCategory"
                        + " JOIN job.assignedTo assignedTo"
                        + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Jobs in period":
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + " ORDER BY job.id DESC";
                break;
            case "Monthly report":
                System.out.println("search text: " + searchText);
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + " AND ( UPPER(department.name) = '" + searchText.toUpperCase() + "'"
                        + " OR UPPER(subContractedDepartment.name) = '" + searchText.toUpperCase() + "'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "My department's jobs":
                searchTextAndClause
                        = " AND ("
                        + " UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + sampleSearchWhereClause
                        + " OR UPPER(job.reportNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobStatusAndTracking.statusNote) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.instructions) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.invoiceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + sampleSearchJoinClause
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " JOIN job.classification classification"
                        + " JOIN job.sector sector"
                        + " JOIN job.client client"
                        + " JOIN job.jobCategory jobCategory"
                        + " JOIN job.jobSubCategory jobSubCategory"
                        + " JOIN job.assignedTo assignedTo"
                        + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " AND ( UPPER(department.name) LIKE '%" + user.getEmployee().getDepartment().getName().toUpperCase() + "%'"
                        + " OR UPPER(subContractedDepartment.name) LIKE '%" + user.getEmployee().getDepartment().getName().toUpperCase() + "%'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "My jobs":
                searchTextAndClause
                        = " AND ("
                        + " UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + sampleSearchWhereClause
                        + " OR UPPER(job.reportNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobStatusAndTracking.statusNote) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(job.instructions) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.invoiceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCostingAndPayment.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + sampleSearchJoinClause
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " JOIN job.classification classification"
                        + " JOIN job.sector sector"
                        + " JOIN job.client client"
                        + " JOIN job.jobCategory jobCategory"
                        + " JOIN job.jobSubCategory jobSubCategory"
                        + " JOIN job.assignedTo assignedTo"
                        + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " AND ( UPPER(assignedTo.lastName) LIKE '%" + user.getEmployee().getLastName().toUpperCase() + "%'"
                        + " AND UPPER(assignedTo.firstName) LIKE '%" + user.getEmployee().getFirstName().toUpperCase() + "%'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "Jobs for my department":
                searchText = user.getEmployee().getDepartment().getName().replaceAll("'", "''");
                searchQuery
                        = "SELECT job FROM Job job"
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + " AND ( UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            default:
                System.out.println("Default search to be implemented");

                break;
        }

        try {
            foundJobs = em.createQuery(searchQuery, Job.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundJobs;
    }

    /**
     * Gets all jobs that are considered to be new. Presently all jobs without
     * an alert date set is considered new.
     *
     * @param em
     * @return
     */
    public static List<Job> findAllNewJobs(EntityManager em, DatePeriod datePeriod) {
        try {
            List<Job> jobs = em.createQuery(
                    "SELECT j FROM Job j"
                    + " JOIN j.jobStatusAndTracking t"
                    + " WHERE (t.dateSubmitted >= " + BusinessEntityUtils.getDateString(datePeriod.getStartDate(), "'", "YMD", "-")
                    + " AND t.dateSubmitted <= " + BusinessEntityUtils.getDateString(datePeriod.getEndDate(), "'", "YMD", "-") + ")"
                    + " AND t.alertDate IS NULL", Job.class).getResultList();

            return jobs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Gets all jobs that have been updated. Presently all jobs without a job
     * email date set is considered updated.
     *
     * @param em
     * @return
     */
    public static List<Job> findAllUpdatedJobs(EntityManager em, DatePeriod datePeriod) {
        try {
            List<Job> jobs = em.createQuery(
                    "SELECT j FROM Job j"
                    + " JOIN j.jobStatusAndTracking t"
                    + " WHERE (t.dateSubmitted >= " + BusinessEntityUtils.getDateString(datePeriod.getStartDate(), "'", "YMD", "-")
                    + " AND t.dateSubmitted <= " + BusinessEntityUtils.getDateString(datePeriod.getEndDate(), "'", "YMD", "-") + ")"
                    + " AND t.dateJobEmailWasSent IS NULL", Job.class).getResultList();

            return jobs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Job> findAllJobs(EntityManager em) {

        try {
            List<Job> jobs = em.createNamedQuery("findAllJobs", Job.class).getResultList();

            return jobs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Job> findJobsByBusinessOfficeId(EntityManager em, Long businessOfficeId) {

        try {

            List<Job> jobs = em.createQuery(
                    "SELECT j FROM Job j"
                    + " JOIN j.businessOffice businessOffice"
                    + " WHERE businessOffice.id = " + businessOfficeId, Job.class).getResultList();

            return jobs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Return the first job found with the matching job number.
     *
     * @param em
     * @param jobNumber
     * @return
     */
    public static Job findJobByJobNumber(EntityManager em, String jobNumber) {

        try {
            String newJobNumber = jobNumber.trim().replaceAll("'", "''");

            List<Job> jobs = em.createQuery("SELECT j FROM Job j "
                    + "WHERE UPPER(j.jobNumber) "
                    + "= '" + newJobNumber.toUpperCase() + "'", Job.class).getResultList();

            if (!jobs.isEmpty()) {
                return jobs.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Job findParentJob(EntityManager em, Integer yearReceived, Long jobSequenceNumber) {
        return null;
    }

    public static Job findJobByYearReceivedAndJobSequence(EntityManager em, Integer yearReceived, Long jobSequenceNumber) {

        try {

            List<Job> jobs = em.createQuery("SELECT j FROM Job j "
                    + "WHERE j.yearReceived = "
                    + yearReceived.toString() + " AND j.jobSequenceNumber = "
                    + jobSequenceNumber.toString(), Job.class).getResultList();

            if (!jobs.isEmpty()) {
                return jobs.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Job findJobById(EntityManager em, Long id) {

        try {
            Job job = em.find(Job.class, id);

            return job;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Job> findIncompleteSubcontracts(EntityManager em, Job job) {
        List<Job> foundJobs;
        ArrayList<Job> incompleteSubcontracts = new ArrayList<>();

        foundJobs = findJobsByYearReceivedAndJobSequenceNumber(em, job.yearReceived, job.jobSequenceNumber);
        for (Job foundJob : foundJobs) {
            if (foundJob.getIsSubContracted() && !foundJob.getJobStatusAndTracking().getCompleted()) {
                incompleteSubcontracts.add(foundJob);
            }
        }

        return incompleteSubcontracts;
    }

    public static List<Job> findJobsByYearReceivedAndJobSequenceNumber(
            EntityManager em,
            Integer yearReceived,
            Long jobSequenceNumber) {
        try {

            List<Job> jobs = em.createQuery("SELECT j FROM Job j "
                    + "WHERE j.yearReceived = "
                    + yearReceived.toString() + " AND j.jobSequenceNumber = "
                    + jobSequenceNumber.toString(), Job.class).getResultList();

            if (!jobs.isEmpty()) {
                return jobs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<String> findJobNumbers(EntityManager em, String query) {

        try {
            String newName = query.replaceAll("'", "''");

            List<String> numbers
                    = em.createQuery("SELECT j FROM Job j WHERE UPPER(j.jobNumber) like '"
                            + newName.toUpperCase().trim() + "%'"
                            + " ORDER BY j.jobNumber", String.class).getResultList();
            return numbers;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Job> findJobsWithJobCosting(
            EntityManager em,
            String originalDepartmentName,
            String originalSearchText) {

        List<Job> foundJobs;
        String searchQuery;
        String searchTextAndClause = "";
        String joinClause;
        String searchText;
        String departmentName;

        // NB: replace ' with '' to avoid SQL query error
        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''");
        } else {
            searchText = "";
        }

        // NB: replace ' with '' to avoid SQL query error
        if (originalDepartmentName != null) {
            departmentName = originalDepartmentName.replaceAll("'", "''");
        } else {
            departmentName = "";
        }

        joinClause
                = " JOIN job.department department"
                + " JOIN job.subContractedDepartment subContractedDepartment"
                + " JOIN job.jobCostingAndPayment jobCostingAndPayment";

        if (!searchText.equals("") && !departmentName.equals("")) {
            searchTextAndClause
                    = " AND ("
                    + " UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(job.jobDescription) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(department.name) LIKE '%" + departmentName.toUpperCase() + "%'"
                    + " OR UPPER(subContractedDepartment.name) LIKE '%" + departmentName.toUpperCase() + "%'"
                    + " )";
        } else if (searchText.equals("") && !departmentName.equals("")) {
            searchTextAndClause
                    = " AND ("
                    + " UPPER(department.name) LIKE '%" + departmentName.toUpperCase() + "%'"
                    + " OR UPPER(subContractedDepartment.name) LIKE '%" + departmentName.toUpperCase() + "%'"
                    + " )";
        } else if (!searchText.equals("") && departmentName.equals("")) {
            searchTextAndClause
                    = " AND ("
                    + " UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(job.jobDescription) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " )";
        }

        searchQuery
                = "SELECT job FROM Job job"
                + joinClause
                + " WHERE (jobCostingAndPayment.costingCompleted = 1 OR jobCostingAndPayment.costingApproved = 1)" // used as place holder
                + searchTextAndClause
                + " ORDER BY job.id DESC";
        try {
            foundJobs = em.createQuery(searchQuery, Job.class).getResultList();
            if (foundJobs == null) {
                foundJobs = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

        return foundJobs;
    }

    public static List<Object[]> getJobReportRecords(
            EntityManager em,
            String startDate,
            String endDate,
            Long departmentId) {

        String reportSQL = "SELECT"
                + "     GROUP_CONCAT(jobsample.`DESCRIPTION` SEPARATOR ', ') AS samples," // 0
                + "     GROUP_CONCAT(jobsample.`PRODUCTBRAND` SEPARATOR ', ') AS sampleBrands," // 1
                + "     GROUP_CONCAT(jobsample.`PRODUCTMODEL` SEPARATOR ', ') AS sampleModels," // 2    
                + "     job.`JOBDESCRIPTION` AS job_JOBDESCRIPTION," // 3   
                + "     job.`NOOFTESTSORCALIBRATIONS` AS job_NOOFTESTSORCALIBRATIONS," // 4
                + "     job.`NUMBEROFSAMPLES` AS job_NUMBEROFSAMPLES," // 5    
                + "     job.`JOBNUMBER` AS job_JOBNUMBER," // 6             
                + "     job.`COMMENT` AS job_COMMENT," // 7               
                + "     client.`NAME` AS client_NAME," // 8                        
                + "     department.`NAME` AS department_NAME," // 9              
                + "     department_A.`NAME` AS department_A_NAME," // 10
                + "     businessoffice.`NAME` AS businessoffice_NAME," // 11
                + "     jobstatusandtracking.`WORKPROGRESS` AS jobstatusandtracking_WORKPROGRESS," // 12
                + "     classification.`NAME` AS classification_NAME," // 13              
                + "     jobcategory.`CATEGORY` AS jobcategory_CATEGORY," // 14
                + "     jobsubcategory.`SubCategory` AS jobsubcategory_SubCategory," // 15             
                + "     sector.`NAME` AS sector_NAME," // 16
                + "     jobstatusandtracking.`EXPECTEDDATEOFCOMPLETION` AS jobstatusandtracking_EXPECTEDDATEOFCOMPLETION," // 17
                + "     jobstatusandtracking.`ENTEREDBY_ID` AS jobstatusandtracking_ENTEREDBY_ID," // 18
                + "     jobstatusandtracking.`DATEOFCOMPLETION` AS jobstatusandtracking_DATEOFCOMPLETION," // 19
                + "     jobstatusandtracking.`DATEANDTIMEENTERED` AS jobstatusandtracking_DATEANDTIMEENTERED," // 20                
                + "     employee.`FIRSTNAME` AS employee_FIRSTNAME," // 21
                + "     employee.`LASTNAME` AS employee_LASTNAME," // 22               
                + "     employee_A.`ID` AS employee_A_ID," // 23
                + "     employee_A.`FIRSTNAME` AS employee_A_FIRSTNAME," // 24
                + "     employee_A.`LASTNAME` AS employee_A_LASTNAME," // 25               
                + "     jobcostingandpayment.`DEPOSIT` AS jobcostingandpayment_DEPOSIT," // 26
                + "     jobcostingandpayment.`FINALCOST` AS jobcostingandpayment_FINALCOST," // 27
                + "     jobcostingandpayment.`ESTIMATEDCOST` AS jobcostingandpayment_ESTIMATEDCOST," // 28
                + "     jobstatusandtracking.`DATESUBMITTED` AS jobstatusandtracking_DATESUBMITTED," // 29
                + "     job.`COMMENT` AS job_COMMENT" // 30
                + " FROM"
                + "     `jobstatusandtracking` jobstatusandtracking INNER JOIN `job` job ON jobstatusandtracking.`ID` = job.`JOBSTATUSANDTRACKING_ID`"
                + "     INNER JOIN `client` client ON job.`CLIENT_ID` = client.`ID`"
                + "     INNER JOIN `job_jobsample` job_jobsample ON job.`ID` = job_jobsample.`Job_ID`"
                + "     INNER JOIN `department` department ON job.`DEPARTMENT_ID` = department.`ID`"
                + "     INNER JOIN `department` department_A ON job.`SUBCONTRACTEDDEPARTMENT_ID` = department_A.`ID`"
                + "     INNER JOIN `businessoffice` businessoffice ON job.`BUSINESSOFFICE_ID` = businessoffice.`ID`"
                + "     INNER JOIN `classification` classification ON job.`CLASSIFICATION_ID` = classification.`ID`"
                + "     INNER JOIN `jobcategory` jobcategory ON job.`JOBCATEGORY_ID` = jobcategory.`ID`"
                + "     INNER JOIN `jobsubcategory` jobsubcategory ON job.`JOBSUBCATEGORY_ID` = jobsubcategory.`ID`"
                + "     INNER JOIN `sector` sector ON job.`SECTOR_ID` = sector.`ID`"
                + "     INNER JOIN `employee` employee ON job.`ASSIGNEDTO_ID` = employee.`ID`"
                + "     INNER JOIN `jobcostingandpayment` jobcostingandpayment ON job.`JOBCOSTINGANDPAYMENT_ID` = jobcostingandpayment.`ID`"
                + "     INNER JOIN `jobsample` jobsample ON job_jobsample.`jobSamples_ID` = jobsample.`ID`"
                + "     INNER JOIN `employee` employee_A ON jobstatusandtracking.`ENTEREDBY_ID` = employee_A.`ID`"
                + " WHERE"
                + "     ((jobstatusandtracking.`DATESUBMITTED` >= " + startDate
                + " AND jobstatusandtracking.`DATESUBMITTED` <= " + endDate + ")"
                + "  OR (jobstatusandtracking.`DATEOFCOMPLETION` >= " + startDate
                + " AND jobstatusandtracking.`DATEOFCOMPLETION` <= " + endDate + ")"
                + "  OR (jobstatusandtracking.`EXPECTEDDATEOFCOMPLETION` >= " + startDate
                + " AND jobstatusandtracking.`EXPECTEDDATEOFCOMPLETION` <= " + endDate + ")"
                + "  OR (jobstatusandtracking.`DATEANDTIMEENTERED` >= " + startDate
                + " AND jobstatusandtracking.`DATEANDTIMEENTERED` <= " + endDate + "))"
                + " AND (department.`ID` = " + departmentId
                + "  OR department_A.`ID` = " + departmentId + ")"
                + " GROUP BY"
                + "     job.`ID`"
                + " ORDER BY"
                + "     job.`ID` DESC,"
                + "     employee.`LASTNAME` ASC";

        try {
            return em.createNativeQuery(reportSQL).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    public static List<Object[]> getCompletedJobRecords(
            EntityManager em,
            String startDate,
            String endDate,
            Long departmentId) {

        String reportSQL = "SELECT\n"
                + "     GROUP_CONCAT(jobsample.`DESCRIPTION` SEPARATOR ', ') AS samples,\n" //0
                + "     job.`ID` AS job_ID,\n" //1
                + "     jobstatusandtracking.`ID` AS jobstatusandtracking_ID,\n" //2
                + "     jobsample.`NAME` AS jobsample_NAME,\n" //3
                + "     department.`NAME` AS department_NAME,\n" //4
                + "     department_A.`NAME` AS department_A_NAME,\n" //5
                + "     jobstatusandtracking.`DATEOFCOMPLETION` AS jobstatusandtracking_DATEOFCOMPLETION,\n" //6
                + "     employee.`NAME` AS employee_NAME,\n" //7
                + "     jobcostingandpayment.`FINALCOST` AS jobcostingandpayment_FINALCOST,\n" //8
                + "     job.`NUMBEROFSAMPLES` AS job_NUMBEROFSAMPLES,\n" //9
                + "     job.`NOOFTESTSORCALIBRATIONS` AS job_NOOFTESTSORCALIBRATIONS,\n" //10
                + "     job.`NOOFTESTS` AS job_NOOFTESTS,\n" //11
                + "     job.`NOOFCALIBRATIONS` AS job_NOOFCALIBRATIONS,\n" //12
                + "     jobstatusandtracking.`EXPECTEDDATEOFCOMPLETION` AS jobstatusandtracking_EXPECTEDDATEOFCOMPLETION,\n" //13
                + "     job.`JOBNUMBER` AS job_JOBNUMBER,\n" //14
                + "     client.`NAME` AS client_NAME,\n" //15
                + "     jobstatusandtracking.`DATESUBMITTED` AS jobstatusandtracking_DATESUBMITTED,\n" //16
                + "     sector.`NAME` AS sector_NAME,\n" //17
                + "     classification.`NAME` AS classification_NAME,\n" //18
                + "     jobcategory.`CATEGORY` AS jobcategory_CATEGORY,\n" //19
                + "     jobsubcategory.`SubCategory` AS jobsubcategory_SubCategory\n" // 20
                + "FROM\n"
                + "     `jobstatusandtracking` jobstatusandtracking INNER JOIN `job` job ON jobstatusandtracking.`ID` = job.`JOBSTATUSANDTRACKING_ID`\n"
                + "     INNER JOIN `job_jobsample` job_jobsample ON job.`ID` = job_jobsample.`Job_ID`\n"
                + "     INNER JOIN `department` department ON job.`DEPARTMENT_ID` = department.`ID`\n"
                + "     INNER JOIN `department` department_A ON job.`SUBCONTRACTEDDEPARTMENT_ID` = department_A.`ID`\n"
                + "     INNER JOIN `employee` employee ON job.`ASSIGNEDTO_ID` = employee.`ID`\n"
                + "     INNER JOIN `jobcostingandpayment` jobcostingandpayment ON job.`JOBCOSTINGANDPAYMENT_ID` = jobcostingandpayment.`ID`\n"
                + "     INNER JOIN `client` client ON job.`CLIENT_ID` = client.`ID`\n"
                + "     INNER JOIN `sector` sector ON job.`SECTOR_ID` = sector.`ID`\n"
                + "     INNER JOIN `classification` classification ON job.`CLASSIFICATION_ID` = classification.`ID`\n"
                + "     INNER JOIN `jobcategory` jobcategory ON job.`JOBCATEGORY_ID` = jobcategory.`ID`\n"
                + "     INNER JOIN `jobsubcategory` jobsubcategory ON job.`JOBSUBCATEGORY_ID` = jobsubcategory.`ID`\n"
                + "     RIGHT OUTER JOIN `jobsample` jobsample ON job_jobsample.`jobSamples_ID` = jobsample.`ID`\n"
                + "WHERE\n"
                + "     ((jobstatusandtracking.`DATEOFCOMPLETION` >= " + startDate
                + " AND jobstatusandtracking.`DATEOFCOMPLETION` <= " + endDate + "))"
                + " AND ((department.`ID` = " + departmentId
                + " AND department_A.`NAME` = \"--\")"
                + "  OR department_A.`ID` = " + departmentId + ")"
                + " GROUP BY"
                + "     job.`ID`"
                + " ORDER BY"
                + "     employee.`NAME` ASC";

        try {
            return em.createNativeQuery(reportSQL).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    /*
     public static List<Object[]> getAnalyticalServicesJobRecords(
     EntityManager em,
     String startDate,
     String endDate,
     Long departmentId) {

     String reportSQL = "SELECT\n"
     + "     GROUP_CONCAT(jobsample.`DESCRIPTION` SEPARATOR ', ') AS samples,\n" //0
     + "     job.`ID` AS job_ID,\n" //1
     + "     jobstatusandtracking.`ID` AS jobstatusandtracking_ID,\n" //2
     + "     jobsample.`NAME` AS jobsample_NAME,\n" //3
     + "     department.`NAME` AS department_NAME,\n" //4
     + "     department_A.`NAME` AS department_A_NAME,\n" //5
     + "     jobstatusandtracking.`DATEOFCOMPLETION` AS jobstatusandtracking_DATEOFCOMPLETION,\n" //6
     + "     employee.`NAME` AS employee_NAME,\n" //7
     + "     jobcostingandpayment.`FINALCOST` AS jobcostingandpayment_FINALCOST,\n" //8
     + "     job.`NUMBEROFSAMPLES` AS job_NUMBEROFSAMPLES,\n" //9
     + "     job.`NOOFTESTSORCALIBRATIONS` AS job_NOOFTESTSORCALIBRATIONS,\n" //10
     + "     job.`NOOFTESTS` AS job_NOOFTESTS,\n" //11
     + "     job.`NOOFCALIBRATIONS` AS job_NOOFCALIBRATIONS,\n" //12
     + "     jobstatusandtracking.`EXPECTEDDATEOFCOMPLETION` AS jobstatusandtracking_EXPECTEDDATEOFCOMPLETION,\n" //13
     + "     job.`JOBNUMBER` AS job_JOBNUMBER,\n" //14
     + "     client.`NAME` AS client_NAME,\n" //15
     + "     jobstatusandtracking.`DATESUBMITTED` AS jobstatusandtracking_DATESUBMITTED,\n" //16
     + "     sector.`NAME` AS sector_NAME\n" //17
     + "FROM\n"
     + "     `jobstatusandtracking` jobstatusandtracking INNER JOIN `job` job ON jobstatusandtracking.`ID` = job.`JOBSTATUSANDTRACKING_ID`\n"
     + "     INNER JOIN `job_jobsample` job_jobsample ON job.`ID` = job_jobsample.`Job_ID`\n"
     + "     INNER JOIN `department` department ON job.`DEPARTMENT_ID` = department.`ID`\n"
     + "     INNER JOIN `department` department_A ON job.`SUBCONTRACTEDDEPARTMENT_ID` = department_A.`ID`\n"
     + "     INNER JOIN `employee` employee ON job.`ASSIGNEDTO_ID` = employee.`ID`\n"
     + "     INNER JOIN `jobcostingandpayment` jobcostingandpayment ON job.`JOBCOSTINGANDPAYMENT_ID` = jobcostingandpayment.`ID`\n"
     + "     INNER JOIN `client` client ON job.`CLIENT_ID` = client.`ID`\n"
     + "     INNER JOIN `sector` sector ON job.`SECTOR_ID` = sector.`ID`\n"
     + "     RIGHT OUTER JOIN `jobsample` jobsample ON job_jobsample.`jobSamples_ID` = jobsample.`ID`\n"
     + " WHERE"
     + "     ((jobstatusandtracking.`DATEOFCOMPLETION` >= " + startDate
     + " AND jobstatusandtracking.`DATEOFCOMPLETION` <= " + endDate + "))"
     + " AND (department.`ID` = " + departmentId
     + " OR department_A.`ID` = " + departmentId + ")"
     + " GROUP BY"
     + " job.`ID`"
     + " ORDER BY"
     + " employee.`NAME` ASC";
        
     try {
     return em.createNativeQuery(reportSQL).getResultList();
     } catch (Exception e) {
     System.out.println(e);
     return new ArrayList<>();
     }

     }
    
     */
    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        Job currentlySavedJob = null;
        Job currentJob = this;

        // Get currently saved job for later use in validation
        if (currentJob.getId() != null) {
            currentlySavedJob = Job.findJobById(em, currentJob.getId());
        }

        if (!BusinessEntityUtils.validateName(currentJob.getBusinessOffice().getName())) {

            return new MethodResult(false, "This job cannot be saved because a valid business office was not entered.");
        }

        BusinessOffice Office = BusinessOffice.findBusinessOfficeByName(em, currentJob.getBusinessOffice().getName());
        if (Office != null) {
            em.refresh(Office);
            currentJob.setBusinessOffice(Office);
        } else {
            return new MethodResult(false, "This job cannot be saved because a valid business office was not entered.");
        }

        // Check if job nunmber is already associated with a job        
        Job existingJob = Job.findJobByJobNumber(em, currentJob.getJobNumber());
        if (existingJob != null) {
            //System.out.println("exist id: " + existingJob.getId());
            long current_jobid = currentJob.getId() != null ? currentJob.getId() : -1L;
            if (existingJob.getId() != current_jobid) {
                return new MethodResult(false, "This job cannot be saved because the job number is not unique.");
            }
        }

        // get  job number if auto is on
        if (currentJob.getAutoGenerateJobNumber()) {
            if (!validateJobNumber(currentJob.getJobNumber(), currentJob.getAutoGenerateJobNumber())) {
                return new MethodResult(false, "This job cannot be saved because a valid job number was not entered.");
            }
        }

        // Validate client
        if (!BusinessEntityUtils.validateName(currentJob.getClient().getName())) {
            return new MethodResult(false,
                    "This job cannot be saved. Please select a valid client from the list."
                    + "You may create a new client if you have the privilege and the client's name does not appear in the list.");

        } else if (currentJob.getClient().getId() != null) {
            currentJob.setClient(Client.getClientById(em, currentJob.getClient().getId()));
        }

        // Department        
        Department dept = Department.findDepartmentByName(em, currentJob.getDepartment().getName());
        if (dept == null) {
            return new MethodResult(false, "This job cannot be saved because a valid department was not entered.");
        } else {
            em.refresh(dept);
            currentJob.setDepartment(dept);
        }
        // Subcontracted department   
        Department subContractedDept = Department.findDepartmentByName(em, currentJob.getSubContractedDepartment().getName());
        if (subContractedDept == null) {
            currentJob.setSubContractedDepartment(Department.findDefaultDepartment(em, "--"));
        }

        // Check for valid subcontracted department
        // tk impl isToBeSubcontracted in Job use it as it is used in JobManager
        if (!currentJob.getIsSubContracted() && getIsToBeSubcontracted()) {
            return new MethodResult(false, "Please enter a valid subcontracted department.");
        } else if ((currentlySavedJob != null)
                && !currentJob.getIsSubContracted()
                && currentlySavedJob.getIsSubContracted()) {

            // Reset current subcontracted department
            currentJob.setSubContractedDepartment(currentlySavedJob.getSubContractedDepartment());

            return new MethodResult(false, "Please enter a valid subcontracted department.");
        }

        // Check for self contracts        
        if (currentJob.getDepartment().getName().equals(currentJob.getSubContractedDepartment().getName())) {
            return new MethodResult(false, "The main and subcontracted departments cannot be the same.");
        }

        // TAT
        if ((currentJob.getEstimatedTurnAroundTimeInDays() == 0) && currentJob.getEstimatedTurnAroundTimeRequired()) {
            return new MethodResult(false, "A valid estimated turnaround time (TAT) is required and must be provided.");
        }

        // Assignee       
        Employee assignee = Employee.findEmployeeByName(em, currentJob.getAssignedTo().getName());
        if (assignee != null) {
            if (assignee.getName().equals("--, --")
                    || assignee.getFirstName().trim().equals("")
                    || assignee.getLastName().trim().equals("")) {
                return new MethodResult(false, "This job cannot be saved because a valid assignee/department representative was not entered.");
            }
            em.refresh(assignee);
            currentJob.setAssignedTo(assignee);
        } else {
            currentJob.setAssignedTo(Employee.findDefaultEmployee(em, "--", "--", true));

            return new MethodResult(false, "This job cannot be saved because a valid assignee/department representative was not entered.");
        }

        // Validate Instructions
        if (currentJob.getInstructions().trim().equals("")) {
            return new MethodResult(false, "Please enter instructions for this job.");
        }

        // Classification objects
        Classification classn = Classification.findClassificationByName(em, currentJob.getClassification().getName());
        if (classn == null) {
            return new MethodResult(false, "Please select/enter a job classification.");
        } else if (classn.getName().equals("--") || classn.getName().trim().equals("")) {
            return new MethodResult(false, "Please select/enter a job classification.");
        } else {
            currentJob.setClassification(classn);
        }

        Sector sect = Sector.findSectorById(em, currentJob.getSector().getId());
        if (sect == null) {
            return new MethodResult(false, "Please select/enter a sector.\"");
        } else {
            currentJob.setSector(sect);
        }

        JobCategory category = JobCategory.findJobCategoryById(em, currentJob.getJobCategory().getId());
        if (category == null) {
            return new MethodResult(false, "Please select/enter a job category.");
        } else {
            currentJob.setJobCategory(category);
        }

        JobSubCategory subCategory = JobSubCategory.findJobSubCategoryById(em, currentJob.getJobSubCategory().getId());
        if (subCategory == null) {
            return new MethodResult(false, "Please select/enter a job subcategory.");
        } else {
            currentJob.setJobSubCategory(subCategory);
        }

        // Check for valid creation of sub contracts
        if (currentJob.getIsSubContracted() && currentJob.getJobSequenceNumber() == null) {
            return new MethodResult(false, "A main/parent job must be created before creating a subcontracted job.");
        }

        // Check if job as previously saved as parent job and prevent saving 
        // suubconttacted job if so
        if (currentJob.getId() != null) {
            Job jobFound = Job.findJobById(em, currentJob.getId());
            if (jobFound != null) {
                if (!jobFound.getIsSubContracted() && currentJob.getIsSubContracted()) {
                    return new MethodResult(false, "A main/parent job cannot be converted to a subcontracted job.\n"
                            + "Create a copy of this job instead then convert the copied job to a subcontract.");
                }
            }
        }

        if (currentJob.getJobStatusAndTracking().getCompleted()
                && currentJob.getJobCostingAndPayment().getFinalCost() == 0.0) {

            return new MethodResult(false, "A job cannot have a completed 'Work progress' without a final cost.");
        }

        return new MethodResult();
    }

    public Boolean validateJobNumber(String jobNumber, Boolean auto) {
        Integer departmentCode = 0;
        Integer year = 0;
        Long sequenceNumber = 0L;

        String parts[] = jobNumber.split("/");
        if (parts != null) {
            // check for correct number of parts
            if ((parts.length >= 3)
                    && (parts.length <= 5)) {
                // are subgroup code, year and sequence number valid integers/long?
                try {
                    if (auto && parts[0].equals("?")) {
                        // This means the complete job number has not yet
                        // been generate. Ignore for now.
                    } else {
                        departmentCode = Integer.parseInt(parts[0]);
                    }
                    year = Integer.parseInt(parts[1]);
                    if (auto && parts[2].equals("?")) {
                        // This means the complete job number has not yet
                        // been generate. Ignore for now.
                    } else {
                        sequenceNumber = Long.parseLong(parts[2]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    return false;
                }
                // subgroup code, year and deparment code have valid ranges?
                if (auto && parts[0].equals("?")) {
                    // This means the complete job number has not yet
                    // been generate. Ignore for now.
                } else if (departmentCode < 0) {
                    return false;
                }
                if (year < 1970) {
                    return false;
                }
                if (auto && parts[2].equals("?")) {
                    // This means the complete job number has not yet
                    // been generate. Ignore for now.
                } else if (sequenceNumber < 1L) {
                    return false;
                }
                // validate 4th part that can be an integer for a department
                // code or a sample reference(s)
                if (parts.length > 3) {
                    try {
                        departmentCode = Integer.parseInt(parts[3]);
                        if (departmentCode < 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        // this means 4th part is not a department code
                        // and that's ok for now.
                        System.out.println("Job number validation error: This means 4th part is not a department code.: " + e);
                    }
                }
                // all is well here
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
