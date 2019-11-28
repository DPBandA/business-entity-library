/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.jmts;

import jm.com.dpbennett.business.entity.hrm.User;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import jm.com.dpbennett.business.entity.fm.CashPayment;
import jm.com.dpbennett.business.entity.fm.Classification;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.rm.DatePeriod;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.fm.JobCategory;
import jm.com.dpbennett.business.entity.fm.JobCostingAndPayment;
import jm.com.dpbennett.business.entity.fm.JobSubCategory;
import jm.com.dpbennett.business.entity.fm.Sector;
import jm.com.dpbennett.business.entity.fm.Service;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "job")
@NamedQueries({
    @NamedQuery(name = "findAllJobs", query = "SELECT j FROM Job j ORDER BY j.jobNumber")
    ,
    @NamedQuery(name = "findByJobNumber", query = "SELECT j FROM Job j WHERE j.jobNumber = :jobNumber")
})
@XmlRootElement
public class Job implements Serializable, BusinessEntity {

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
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client client;
    @OneToOne(cascade = CascadeType.REFRESH)
    private JobCategory jobCategory;
    @OneToOne(cascade = CascadeType.REFRESH)
    private JobSubCategory jobSubCategory;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<JobSample> jobSamples;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedTo;
    @OneToOne(cascade = CascadeType.REFRESH)
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
    // Service quantities
    private Integer noOfTests;
    private Integer noOfCalibrations;
    private Integer noOfTestsOrCalibrations;
    private Integer noOfInspections;
    private Integer noOfTrainings;
    private Integer noOfLabelAssessments;
    private Integer noOfCertifications;
    private Integer noOfConsultations;
    private Integer noOfOtherAssessments;
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
    @OneToOne(cascade = CascadeType.REFRESH)
    private Job parent;
    @Transient
    private String name;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> representatives;
    @Transient
    private Boolean visited;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Service> services;
    private String serviceLocation;

    public Job() {
        this.name = "";
        this.jobNumber = "";
        this.isToBeSubcontracted = false;
        this.isToBeCopied = false;
        this.jobSamples = new ArrayList<>();
    }

    public Job(String name) {
        this.name = name;
        this.jobNumber = name;
        this.isToBeSubcontracted = false;
        this.isToBeCopied = false;
        this.jobSamples = new ArrayList<>();
    }

    public Job(Long id) {
        this.id = id;
        this.name = "";
        this.jobNumber = name;
        this.isToBeSubcontracted = false;
        this.isToBeCopied = false;
        this.jobSamples = new ArrayList<>();
    }

    public Integer getNoOfInspections() {
        if (noOfInspections == null) {
            noOfInspections = 0;
        }
        return noOfInspections;
    }

    public void setNoOfInspections(Integer noOfInspections) {
        this.noOfInspections = noOfInspections;
    }

    public Integer getNoOfTrainings() {
        if (noOfTrainings == null) {
            noOfTrainings = 0;
        }
        return noOfTrainings;
    }

    public void setNoOfTrainings(Integer noOfTrainings) {
        this.noOfTrainings = noOfTrainings;
    }

    public Integer getNoOfLabelAssessments() {
        if (noOfLabelAssessments == null) {
            noOfLabelAssessments = 0;
        }
        return noOfLabelAssessments;
    }

    public void setNoOfLabelAssessments(Integer noOfLabelAssessments) {
        this.noOfLabelAssessments = noOfLabelAssessments;
    }

    public Integer getNoOfCertifications() {
        if (noOfCertifications == null) {
            noOfCertifications = 0;
        }
        return noOfCertifications;
    }

    public void setNoOfCertifications(Integer noOfCertifications) {
        this.noOfCertifications = noOfCertifications;
    }

    public Integer getNoOfConsultations() {
        if (noOfConsultations == null) {
            noOfConsultations = 0;
        }
        return noOfConsultations;
    }

    public void setNoOfConsultations(Integer noOfConsultations) {
        this.noOfConsultations = noOfConsultations;
    }

    public Integer getNoOfOtherAssessments() {
        if (noOfOtherAssessments == null) {
            noOfOtherAssessments = 0;
        }
        return noOfOtherAssessments;
    }

    public void setNoOfOtherAssessments(Integer noOfOtherAssessments) {
        this.noOfOtherAssessments = noOfOtherAssessments;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public List<CashPayment> getCashPayments() {

        return getJobCostingAndPayment().getCashPayments();
    }

    public void setCashPayments(List<CashPayment> cashPayments) {
        getJobCostingAndPayment().setCashPayments(cashPayments);
    }

    public String getRowStyle() {

        if (getVisited()) {
            visited = false;
            return "lightgreybg";
        } else if (getJobStatusAndTracking().getCompleted()) {
            return "lightgreenbg";
        } else if (getJobStatusAndTracking().getExpectedDateOfCompletion() != null) {
            if (BusinessEntityUtils.getNow().compareTo(getJobStatusAndTracking().getExpectedDateOfCompletion()) >= 0) {
                // Due or overdue
                return "orangeredbg";
            } else if (BusinessEntityUtils.getNow().compareTo(BusinessEntityUtils.adjustDate(getJobStatusAndTracking().
                    getExpectedDateOfCompletion(), Calendar.DATE, -3)) >= 0) {
                // Soon due 
                return "yellowbg";
            } else {
                // It's all good!
                return "";
            }

        } else { // EDOC possibly not set so warn
            return "lightyellowbg";
        }
    }

    public Boolean getVisited() {
        if (visited == null) {
            visited = false;
        }
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public List<Employee> getRepresentatives() {
        if (representatives == null) {
            representatives = new ArrayList<>();
        }
        return representatives;
    }

    public void setRepresentatives(List<Employee> representatives) {
        this.representatives = representatives;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = new ArrayList<>();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getJobNumberWithCostLabel() {
        if (getId() != null) {
            return getJobNumber() + " (" + getJobCostingAndPayment().getFormattedFinalCost() + ")";
        } else {
            return name;
        }
    }

    public Job getParent() {
        return parent;
    }

    public void setParent(Job parent) {
        this.parent = parent;
    }

    public ReturnMessage prepareAndSave(EntityManager em, User user) {

        Date now = new Date();
        JobSequenceNumber nextJobSequenceNumber = null;

        try {
            // Get employee for later use
            Employee employee = user.getEmployee();

            // Do not save changed job if it's already marked as completed in the database
            // However, saving is allowed if the user belongs to the "Invoicing department"
            // or is a system administrator
            if (this.getId() != null) {
                Job job = Job.findJobById(em, this.getId());
                if (job.getJobStatusAndTracking().getWorkProgress().equals("Completed")
                        && !user.getEmployee().isMemberOf(Department.findDepartmentBySystemOptionDeptId("invoicingDepartmentId", em))
                        && !user.getPrivilege().getCanBeJMTSAdministrator()
                        && !User.isUserDepartmentSupervisor(this, user, em)) {

                    this.setIsDirty(false);

                    return new ReturnMessage(false,
                            "Job Cannot Be Saved",
                            "This job is marked as completed so changes cannot be saved. You may contact your department's supervisor or a system administrator",
                            Message.SEVERITY_ERROR_NAME);
                }
            }

            // Set date entered
            if (this.getJobStatusAndTracking().getDateAndTimeEntered() == null) {
                this.getJobStatusAndTracking().setDateAndTimeEntered(now);
            }

            // Set "entered by" and "edited by" if this is a new job           
            if (this.getJobStatusAndTracking().getEnteredBy().getId() == null) {
                // This means this this is a new job so set user and person who entered the job
                this.getJobStatusAndTracking().setEnteredBy(employee);
                this.getJobStatusAndTracking().setEditedBy(employee);
            }

            // Update re the person who last edited/entered the job etc.
            if (this.getIsDirty()) {
                this.getJobStatusAndTracking().setDateStatusEdited(now);
                this.getJobStatusAndTracking().setEditedBy(employee);
            }

            // Modify job number with sequence number if required
            if (this.getAutoGenerateJobNumber()) {
                if ((this.getJobSequenceNumber() == null)) {
                    nextJobSequenceNumber = JobSequenceNumber.findNextJobSequenceNumber(em, this.getYearReceived());
                    this.setJobSequenceNumber(nextJobSequenceNumber.getSequentialNumber());
                    this.setJobNumber(Job.getJobNumber(this, em));
                } else {
                    this.setJobNumber(Job.getJobNumber(this, em));
                }
            }

            // Finally..save the job
            ReturnMessage returnMessage = this.save(em);

            if (returnMessage.isSuccess()) {
                // Save job sequence number since it was used by this job
                if (nextJobSequenceNumber != null) {
                    nextJobSequenceNumber.save(em);
                }

                this.clean();
            } else {

                return new ReturnMessage(false,
                        "Undefined Error!",
                        "An undefined error occurred while saving job "
                        + this.getJobNumber() + ":\n"
                        + returnMessage.getDetail(),
                        Message.SEVERITY_ERROR_NAME);
            }

        } catch (Exception e) {

            return new ReturnMessage(false,
                    "Undefined Error!",
                    "An undefined error occurred while saving job "
                    + this.getJobNumber() + ":\n"
                    + e,
                    Message.SEVERITY_ERROR_NAME);
        }

        return new ReturnMessage();

    }

    public void clean() {
        this.setIsToBeCopied(false);
        this.setIsToBeSubcontracted(false);
        this.setIsDirty(false);
    }

    public static Job copy(EntityManager em,
            Job currentJob,
            User user,
            Boolean autoGenerateJobNumber,
            Boolean copySamples) {
        Job job = new Job();

        job.setClient(new Client("", false));
        job.setIsToBeCopied(true);

        job.setReportNumber("");
        job.setJobDescription("");

        job.setBusinessOffice(currentJob.getBusinessOffice());
        job.setDepartment(currentJob.getDepartment());
        job.setSubContractedDepartment(Department.findDefaultDepartment(em, "--"));
        job.setClassification(Classification.findClassificationByName(em, "--"));
        job.setSector(Sector.findSectorByName(em, "--"));
        job.setJobCategory(JobCategory.findJobCategoryByName(em, "--"));
        job.setJobSubCategory(JobSubCategory.findJobSubCategoryByName(em, "--"));
        // service contract
        job.setServiceContract(ServiceContract.create());
        // set default values
        job.setAutoGenerateJobNumber(autoGenerateJobNumber);
        job.setIsEarningJob(Boolean.TRUE);
        job.setYearReceived(Calendar.getInstance().get(Calendar.YEAR));
        // job status and tracking
        job.setJobStatusAndTracking(new JobStatusAndTracking());
        job.getJobStatusAndTracking().setDateAndTimeEntered(new Date());
        job.getJobStatusAndTracking().setDateSubmitted(new Date());
        job.getJobStatusAndTracking().setAlertDate(null);
        job.getJobStatusAndTracking().setDateJobEmailWasSent(null);
        // job costing and payment 
        job.setJobCostingAndPayment(JobCostingAndPayment.create(em));
        // this is done here because job number is dependent on business office, department/subcontracted department

        // copy samples
        if (copySamples) {
            List<JobSample> samples = currentJob.getJobSamples();
            job.setNumberOfSamples(currentJob.getNumberOfSamples());
            for (JobSample jobSample : samples) {
                job.getJobSamples().add(new JobSample(jobSample));
            }
        }

        // Set sequence number
        job.setJobSequenceNumber(currentJob.getJobSequenceNumber());

        // Set job number
        if (job.getAutoGenerateJobNumber()) {
            job.setJobNumber(Job.getJobNumber(job, em));
        }

        return job;
    }

    public static Job create(EntityManager em,
            String name,
            User user,
            Boolean autoGenerateJobNumber) {

        Job job = Job.create(em, user, autoGenerateJobNumber);
        job.name = name;

        return job;
    }

    public static Job create(EntityManager em,
            User user,
            Boolean autoGenerateJobNumber) {

        Job job = new Job();
        job.setClient(new Client("", false));
        job.setBillingAddress(job.getClient().getDefaultAddress());
        job.setContact(job.getClient().getDefaultContact());
        job.setReportNumber("");
        job.setJobDescription("");
        job.setSubContractedDepartment(Department.findDefaultDepartment(em, "--"));
        job.setBusinessOffice(BusinessOffice.findDefaultBusinessOffice(em, "Head Office"));
        job.setClassification(new Classification());
        job.setSector(Sector.findSectorByName(em, "--"));
        job.setJobCategory(JobCategory.findJobCategoryByName(em, "--"));
        job.setJobSubCategory(JobSubCategory.findJobSubCategoryByName(em, "--"));
        // service contract
        job.setServiceContract(ServiceContract.create());
        // set default values
        job.setAutoGenerateJobNumber(autoGenerateJobNumber);
        job.setIsEarningJob(Boolean.TRUE);
        job.setYearReceived(Calendar.getInstance().get(Calendar.YEAR));
        // job status and tracking
        job.setJobStatusAndTracking(new JobStatusAndTracking());
        job.getJobStatusAndTracking().setDateAndTimeEntered(new Date());
        job.getJobStatusAndTracking().setDateSubmitted(new Date());
        job.getJobStatusAndTracking().setWorkProgress("Not started");
        // job costing and payment
        job.setJobCostingAndPayment(JobCostingAndPayment.create(em));
        // this is done here because job number is dependent on business office, department/subcontracted department
        job.setNumberOfSamples(0L);
        if (job.getAutoGenerateJobNumber()) {
            job.setJobNumber(Job.getJobNumber(job, em));
        }

        return job;
    }

    public static String getJobNumber(Job job, EntityManager em) {
        Calendar c = Calendar.getInstance();
        String departmentOrCompanyCode;
        String year = "?";
        String sequenceNumber;
        String subContractedDepartmenyOrCompanyCode;

        departmentOrCompanyCode = job.getDepartment().getCode().equals("") ? "?" : job.getDepartment().getCode();
        subContractedDepartmenyOrCompanyCode = job.getSubContractedDepartment().getCode().equals("") ? "?" : job.getSubContractedDepartment().getCode();

        // Use the date entered to get the year if it is valid
        // and only if this is not a subcontracted job
        if ((job.getJobStatusAndTracking().getDateAndTimeEntered() != null)
                && (subContractedDepartmenyOrCompanyCode.equals("?"))) {
            c.setTime(job.getJobStatusAndTracking().getDateAndTimeEntered());
            year = "" + c.get(Calendar.YEAR);
        } else if (job.getYearReceived() != null) {
            year = job.getYearReceived().toString();
        }
        // include the sequence number if it is valid
        if (job.getJobSequenceNumber() != null) {
            sequenceNumber = BusinessEntityUtils.getFourDigitString(job.getJobSequenceNumber());
        } else {
            sequenceNumber = "?";
        }
        // set base job number
        job.setJobNumber(departmentOrCompanyCode + "/" + year + "/" + sequenceNumber);
        // append subcontracted code if valid
        if (!subContractedDepartmenyOrCompanyCode.equals("?")) {
            job.setJobNumber(job.getJobNumber() + "/" + subContractedDepartmenyOrCompanyCode);
        }

        Boolean includeRef = (Boolean) SystemOption.getOptionValueObject(em,
                "includeSampleReference");

        // Append sample codes if any
        if (includeRef) {
            if ((job.getNumberOfSamples() != null) && (job.getNumberOfSamples() > 1)) {
                job.setJobNumber(job.getJobNumber() + "/"
                        + BusinessEntityUtils.getAlphaCode(0) + "-"
                        + BusinessEntityUtils.getAlphaCode(job.getNumberOfSamples() - 1));
            }
        }

        return job.getJobNumber();
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

        isDirty = isDirty || getJobCostingAndPayment().getIsDirty();

        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public Contact getContact() {
        if (contact == null) {
            if (client != null) {
                setContact(client.getMainContact());
            } else {
                return new Contact();
            }
        }
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getBillingAddress() {
        if (billingAddress == null) {
            if (client != null) {
                setBillingAddress(client.getDefaultAddress());
            } else {
                return new Address();
            }
        }
        return billingAddress;
    }

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

    public Boolean getIsSubContract() {
        return !(getSubContractedDepartment().getName().equals("")
                || getSubContractedDepartment().getName().equals("--"));
    }

    public List<Job> getSubcontracts(EntityManager em) {

        return findSubcontracts(em);
    }

    public List<Job> getPossibleSubcontracts(EntityManager em) {

        return findPossibleSubcontracts(em);
    }

// tk for use to create other methods dealing with subcontracts
//    public Boolean hasSubcontracts(EntityManager em) {
//        List<Job> jobs = Job.findJobsByYearReceivedAndJobSequenceNumber(em,
//                this.getYearReceived(),
//                this.getJobSequenceNumber());
//
//        if (jobs != null) {
//            for (Job job : jobs) {
//                // If this job is subcontracted and is a child of this job
//                // then it's a subcontract
//                if (job.getIsSubContract() && !this.getIsSubContract()) {
//
//                    String ccName = "Subcontract to " + job.getSubContractedDepartment().getName() + " (" + job.getJobNumber() + ")";
//                    // Check that this cost component does not already exist.
//                    // The assumption is that only one component will be found if any
//                    if (!CostComponent.findCostComponentsByName(ccName,
//                            currentJob.getJobCostingAndPayment().getCostComponents()).isEmpty()) {
//                        deleteCostComponentByName(ccName);
//                    }
//                    CostComponent cc
//                            = new CostComponent(
//                                    ccName,
//                                    job.getJobCostingAndPayment().getFinalCost(),
//                                    true, false);
//
//                    currentJob.getJobCostingAndPayment().getCostComponents().add(cc);
//                    setDirty(true);
//                }
//            }
//        }
//
//        return false;
//    }
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

        return this.instructions;
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
        return (getJobSamples().size() == 1) && (getJobSamples().get(0).getDescription().trim().equals("--"));
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
    //@JsonIgnore
    public List<JobSample> getJobSamples() {
        if (jobSamples != null) {
            Collections.sort(jobSamples);
        } else {
            jobSamples = new ArrayList<>();
        }

        return jobSamples;
    }

    public void setJobSamples(List<JobSample> jobSamples) {
        this.jobSamples = jobSamples;
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

    public Client getClient() {
        if (client == null) {
            return new Client("");
        }
        return client;
    }

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

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        if (id == null) {
            return "null";
        }

        return id.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
            User user,
            DatePeriod dateSearchPeriod,
            String searchType,
            String searchText,
            Boolean includeSampleSearch) {

        List<Job> foundJobs;
        String searchQuery = null;
        String searchTextAndClause;
        String sampleSearchWhereClause = "";
        String sampleSearchJoinClause = "";
        String selectClause = "SELECT DISTINCT job FROM Job job";

        if (searchText != null) {
            searchText = searchText.trim().replaceAll("'", "''");
        } else {
            searchText = "";
        }

        // include the search for samples?
        if (includeSampleSearch) {

            sampleSearchWhereClause
                    = " OR UPPER(jobSamples.reference) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.description) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productBrand) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productModel) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productSerialNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(jobSamples.productCode) LIKE '%" + searchText.toUpperCase() + "%'";

            sampleSearchJoinClause = " JOIN job.jobSamples jobSamples";
        }

        switch (searchType) {
            case "Appr'd & uninv'd jobs":
                searchTextAndClause
                        = " AND subContractedDepartment.name = '--' AND ("
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'"
                        + " AND (jobCostingAndPayment.costingApproved = 1)"
                        + " AND (classification.isEarning = 1)"
                        + " AND (jobCostingAndPayment.invoiced IS NULL OR jobCostingAndPayment.invoiced = 0)" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Unapproved job costings":
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'"
                        + " AND (jobCostingAndPayment.costingApproved IS NULL OR jobCostingAndPayment.costingApproved = 0)" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Incomplete jobs":
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Completed' AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Jobs in period":
                searchQuery
                        = selectClause
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
                        + " ORDER BY job.id DESC";
                break;
            case "Monthly report":
                searchQuery
                        = selectClause
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
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
                        = selectClause
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
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " AND ( UPPER(assignedTo.lastName) LIKE '%" + user.getEmployee().getLastName().toUpperCase() + "%'"
                        + " AND UPPER(assignedTo.firstName) LIKE '%" + user.getEmployee().getFirstName().toUpperCase() + "%'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "Jobs for my department":
                searchText = user.getEmployee().getDepartment().getName().replaceAll("'", "''");
                searchQuery
                        = selectClause
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " JOIN job.department department"
                        + " JOIN job.subContractedDepartment subContractedDepartment"
                        + " WHERE (jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                        + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-") + ")"
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
     * @param datePeriod
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
     * @param datePeriod
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
            if (foundJob.getIsSubContract() && !foundJob.getJobStatusAndTracking().getCompleted()) {
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

            return jobs;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param em
     * @return
     */
    public List<Job> findSubcontracts(EntityManager em) {
        try {

            List<Job> jobs = em.createQuery("SELECT j FROM Job j "
                    + "WHERE (j.parent.id = " + this.id + " AND j.id <> " + this.id + ")", Job.class).getResultList();

            return jobs;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param em
     * @return
     */
    public List<Job> findPossibleSubcontracts(EntityManager em) {
        List<Job> possibleSubcontracts = new ArrayList<>();

        List<Job> jobs = Job.findJobsByYearReceivedAndJobSequenceNumber(em,
                this.getYearReceived(),
                this.getJobSequenceNumber());
        if (jobs != null) {
            for (Job job : jobs) {
                if (job.getIsSubContract() && !this.getIsSubContract()) {
                    possibleSubcontracts.add(job);
                }
            }
        }

        return possibleSubcontracts;
    }

    public static List<String> getJobNumbersWithCosts(List<Job> jobs) {
        List<String> jobNumbersWithCosts = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("$#,##0.00");

        for (Job job : jobs) {
            jobNumbersWithCosts.add(job.getJobNumber()
                    + " (" + formatter.format(job.getJobCostingAndPayment().getFinalCost()) + ")");
        }

        return jobNumbersWithCosts;
    }

//    public void findPossibleSubcontracts(EntityManager em) {
//        
//            List<Job> jobs = Job.findJobsByYearReceivedAndJobSequenceNumber(em,
//                    this.getYearReceived(),
//                    this.getJobSequenceNumber());
//            if (jobs != null) {
//                for (Job job : jobs) {
//                    if (job.getIsSubContract() && !this.getIsSubContract()
//                            && (job.getJobStatusAndTracking().getWorkProgress().equals("Completed"))) {
//                        String ccName = "Subcontract to " + job.getSubContractedDepartment().getName() + " (" + job.getJobNumber() + ")";
//                        // Check that this cost component does not already exist.
//                        // The assumption is that only one component will be found if any
//                        if (!CostComponent.findCostComponentsByName(ccName,
//                                this.getJobCostingAndPayment().getCostComponents()).isEmpty()) {
//                            //deleteCostComponentByName(ccName);
//                        }
//                        CostComponent cc
//                                = new CostComponent(
//                                        ccName,
//                                        job.getJobCostingAndPayment().getFinalCost(),
//                                        true, false);
//
//                        this.getJobCostingAndPayment().getCostComponents().add(cc);
//                        //setDirty(true);
//                        this.setIsDirty(true);
//                    }
//                }
//            }
//        //}
//        // NB: Ensure that amount due is recalc. in case something affects
//        // taxes was changed
//        this.getJobCostingAndPayment().calculateAmountDue();
//    }
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
    
    /*
    (SELECT SUM(cashpayment.PAYMENT) FROM cashpayment
      INNER JOIN `jobcostingandpayment_cashpayment` jobcostingandpayment_cashpayment ON 
            cashpayment.ID = jobcostingandpayment_cashpayment.cashPayments_ID 
      INNER JOIN `jobcostingandpayment` jobcostingandpayment ON 
            jobcostingandpayment.ID = jobcostingandpayment_cashpayment.JobCostingAndPayment_ID 
      WHERE jobcostingandpayment.ID = job.JOBCOSTINGANDPAYMENT_ID  
     ) 
    */

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

//    public static List<Object[]> getCompletedJobRecords(
//            EntityManager em,
//            String startDate,
//            String endDate,
//            Long departmentId) {
//
//        String reportSQL = "SELECT\n"
//                + "     GROUP_CONCAT(jobsample.`DESCRIPTION` SEPARATOR ', ') AS samples,\n" //0
//                + "     job.`ID` AS job_ID,\n" //1
//                + "     jobstatusandtracking.`ID` AS jobstatusandtracking_ID,\n" //2
//                + "     jobsample.`NAME` AS jobsample_NAME,\n" //3
//                + "     department.`NAME` AS department_NAME,\n" //4
//                + "     department_A.`NAME` AS department_A_NAME,\n" //5
//                + "     jobstatusandtracking.`DATEOFCOMPLETION` AS jobstatusandtracking_DATEOFCOMPLETION,\n" //6
//                + "     employee.`NAME` AS employee_NAME,\n" //7
//                + "     jobcostingandpayment.`FINALCOST` AS jobcostingandpayment_FINALCOST,\n" //8
//                + "     job.`NUMBEROFSAMPLES` AS job_NUMBEROFSAMPLES,\n" //9
//                + "     job.`NOOFTESTSORCALIBRATIONS` AS job_NOOFTESTSORCALIBRATIONS,\n" //10
//                + "     job.`NOOFTESTS` AS job_NOOFTESTS,\n" //11
//                + "     job.`NOOFCALIBRATIONS` AS job_NOOFCALIBRATIONS,\n" //12
//                + "     jobstatusandtracking.`EXPECTEDDATEOFCOMPLETION` AS jobstatusandtracking_EXPECTEDDATEOFCOMPLETION,\n" //13
//                + "     job.`JOBNUMBER` AS job_JOBNUMBER,\n" //14
//                + "     client.`NAME` AS client_NAME,\n" //15
//                + "     jobstatusandtracking.`DATESUBMITTED` AS jobstatusandtracking_DATESUBMITTED,\n" //16
//                + "     sector.`NAME` AS sector_NAME,\n" //17
//                + "     classification.`NAME` AS classification_NAME,\n" //18
//                + "     jobcategory.`CATEGORY` AS jobcategory_CATEGORY,\n" //19
//                + "     jobsubcategory.`SubCategory` AS jobsubcategory_SubCategory\n" // 20
//                + "FROM\n"
//                + "     `jobstatusandtracking` jobstatusandtracking INNER JOIN `job` job ON jobstatusandtracking.`ID` = job.`JOBSTATUSANDTRACKING_ID`\n"
//                + "     INNER JOIN `job_jobsample` job_jobsample ON job.`ID` = job_jobsample.`Job_ID`\n"
//                + "     INNER JOIN `department` department ON job.`DEPARTMENT_ID` = department.`ID`\n"
//                + "     INNER JOIN `department` department_A ON job.`SUBCONTRACTEDDEPARTMENT_ID` = department_A.`ID`\n"
//                + "     INNER JOIN `employee` employee ON job.`ASSIGNEDTO_ID` = employee.`ID`\n"
//                + "     INNER JOIN `jobcostingandpayment` jobcostingandpayment ON job.`JOBCOSTINGANDPAYMENT_ID` = jobcostingandpayment.`ID`\n"
//                + "     INNER JOIN `client` client ON job.`CLIENT_ID` = client.`ID`\n"
//                + "     INNER JOIN `sector` sector ON job.`SECTOR_ID` = sector.`ID`\n"
//                + "     INNER JOIN `classification` classification ON job.`CLASSIFICATION_ID` = classification.`ID`\n"
//                + "     INNER JOIN `jobcategory` jobcategory ON job.`JOBCATEGORY_ID` = jobcategory.`ID`\n"
//                + "     INNER JOIN `jobsubcategory` jobsubcategory ON job.`JOBSUBCATEGORY_ID` = jobsubcategory.`ID`\n"
//                + "     RIGHT OUTER JOIN `jobsample` jobsample ON job_jobsample.`jobSamples_ID` = jobsample.`ID`\n"
//                + "WHERE\n"
//                + "     ((jobstatusandtracking.`dateOfCompletion` >= " + startDate
//                + " AND jobstatusandtracking.`dateOfCompletion` <= " + endDate + "))"
//                + " AND ((department.`ID` = " + departmentId
//                + " AND department_A.`NAME` = \"--\")"
//                + "  OR department_A.`ID` = " + departmentId + ")"
//                + " GROUP BY"
//                + "     job.`ID`"
//                + " ORDER BY"
//                + "     employee.`NAME` ASC";
//
//        try {
//            return em.createNativeQuery(reportSQL).getResultList();
//        } catch (Exception e) {
//            System.out.println(e);
//            return new ArrayList<>();
//        }
//
//    }
    // tk  job records based on jobstatusandtracking date
    public static List<Object[]> getJobRecordsByTrackingDate(
            EntityManager em,
            String dateField,
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

    @Override
    public ReturnMessage save(EntityManager em) {
        ReturnMessage returnMessage;

        try {
            // Save samples
            if (!this.getJobSamples().isEmpty()) {
                for (JobSample jobSample : this.getJobSamples()) {
                    // Save newly entered samples 
                    returnMessage = jobSample.save(em);

                    if ((jobSample.getIsDirty() || jobSample.getId() == null)
                            && !returnMessage.isSuccess()) {

                        return new ReturnMessage(false,
                                "Job sample save error occurred",
                                "An error occurred while saving job sample"
                                + jobSample.getReference()
                                + "\nDetails: " + returnMessage.getDetail(),
                                Message.SEVERITY_ERROR_NAME);

                    }
                    // "Clean" sample
                    jobSample.setIsDirty(false);
                }
            }

            // Save job costing and payment
            returnMessage = jobCostingAndPayment.save(em);
            if (!returnMessage.isSuccess()) {
                return returnMessage;
            }

            // Save job    
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();

        } catch (Exception e) {
            return new ReturnMessage(false,
                    "Job save error occurred!",
                    "An error occurred while saving job " + this.getJobNumber()
                    + "\n" + e,
                    Message.SEVERITY_ERROR_NAME);
        }

    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        Job currentlySavedJob = null;
        Job currentJob = this;

        // Get currently saved job for later use in validation
        if (currentJob.getId() != null) {
            currentlySavedJob = Job.findJobById(em, currentJob.getId());
        }

        if (!BusinessEntityUtils.validateName(currentJob.getBusinessOffice().getName())) {

            return new ReturnMessage(false, "This job cannot be saved because a valid business office was not entered.");
        }

        BusinessOffice Office = BusinessOffice.findBusinessOfficeByName(em, currentJob.getBusinessOffice().getName());
        if (Office != null) {
            em.refresh(Office);
            currentJob.setBusinessOffice(Office);
        } else {
            return new ReturnMessage(false, "This job cannot be saved because a valid business office was not entered.");
        }

        // Check if job nunmber is already associated with a job        
        Job existingJob = Job.findJobByJobNumber(em, currentJob.getJobNumber());
        if (existingJob != null) {
            long current_jobid = currentJob.getId() != null ? currentJob.getId() : -1L;
            if (existingJob.getId() != current_jobid) {
                return new ReturnMessage(false, "This job cannot be saved because the job number is not unique.");
            }
        }

        // get  job number if auto is on
        if (currentJob.getAutoGenerateJobNumber()) {
            if (!validateJobNumber(currentJob.getJobNumber(), currentJob.getAutoGenerateJobNumber())) {
                return new ReturnMessage(false, "This job cannot be saved because a valid job number was not entered.");
            }
        }

        // Validate client
        if (!BusinessEntityUtils.validateName(currentJob.getClient().getName())) {
            return new ReturnMessage(false,
                    "This job cannot be saved. Please select a valid client from the list."
                    + "You may create a new client if you have the privilege and the client's name does not appear in the list.");

        } else if (currentJob.getClient().getId() != null) {
            currentJob.setClient(Client.getClientById(em, currentJob.getClient().getId()));
        }

        // Department        
        Department dept = Department.findDepartmentByName(em, currentJob.getDepartment().getName());
        if (dept == null) {
            return new ReturnMessage(false, "This job cannot be saved because a valid department was not entered.");
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
        if (!currentJob.getIsSubContract() && getIsToBeSubcontracted()) {
            return new ReturnMessage(false, "Please enter a valid subcontracted department.");
        } else if ((currentlySavedJob != null)
                && !currentJob.getIsSubContract()
                && currentlySavedJob.getIsSubContract()) {

            // Reset current subcontracted department
            currentJob.setSubContractedDepartment(currentlySavedJob.getSubContractedDepartment());

            return new ReturnMessage(false, "Please enter a valid subcontracted department.");
        }

        // Check for self contracts        
        if (currentJob.getDepartment().getName().equals(currentJob.getSubContractedDepartment().getName())) {
            return new ReturnMessage(false, "The main and subcontracted departments cannot be the same.");
        }

        // TAT
        if ((currentJob.getEstimatedTurnAroundTimeInDays() == 0) && currentJob.getEstimatedTurnAroundTimeRequired()) {
            return new ReturnMessage(false, "A valid estimated turnaround time (TAT) is required and must be provided.");
        }

        // Assignee       
        Employee assignee = Employee.findEmployeeByName(em, currentJob.getAssignedTo().getName());
        if (assignee != null) {
            if (assignee.getName().equals("--, --")
                    || assignee.getFirstName().trim().equals("")
                    || assignee.getLastName().trim().equals("")) {
                return new ReturnMessage(false, "This job cannot be saved because a valid assignee/department representative was not entered.");
            }
            em.refresh(assignee);
            currentJob.setAssignedTo(assignee);
        } else {
            currentJob.setAssignedTo(Employee.findDefaultEmployee(em, "--", "--", true));

            return new ReturnMessage(false, "This job cannot be saved because a valid assignee/department representative was not entered.");
        }

        // Validate Instructions
        if (currentJob.getInstructions().trim().equals("")) {
            return new ReturnMessage(false, "Please enter instructions for this job.");
        }

        // Classification objects
        Classification classn = Classification.findClassificationByName(em, currentJob.getClassification().getName());
        if (classn == null) {
            return new ReturnMessage(false, "Please select/enter a job classification.");
        } else if (classn.getName().equals("--") || classn.getName().trim().equals("")) {
            return new ReturnMessage(false, "Please select/enter a job classification.");
        } else {
            currentJob.setClassification(classn);
        }

        Sector sect = Sector.findSectorById(em, currentJob.getSector().getId());
        if (sect == null) {
            return new ReturnMessage(false, "Please select/enter a sector.\"");
        } else {
            currentJob.setSector(sect);
        }

        JobCategory category = JobCategory.findJobCategoryById(em, currentJob.getJobCategory().getId());
        if (category == null) {
            return new ReturnMessage(false, "Please select/enter a job category.");
        } else {
            currentJob.setJobCategory(category);
        }

        JobSubCategory subCategory = JobSubCategory.findJobSubCategoryById(em, currentJob.getJobSubCategory().getId());
        if (subCategory == null) {
            return new ReturnMessage(false, "Please select/enter a job subcategory.");
        } else {
            currentJob.setJobSubCategory(subCategory);
        }

        // Check for valid creation of sub contracts
        if (currentJob.getIsSubContract() && currentJob.getJobSequenceNumber() == null) {
            return new ReturnMessage(false, "A main/parent job must be created before creating a subcontracted job.");
        }

        // Check if job as previously saved as parent job and prevent saving 
        // suubconttacted job if so
        if (currentJob.getId() != null) {
            Job jobFound = Job.findJobById(em, currentJob.getId());
            if (jobFound != null) {
                if (!jobFound.getIsSubContract() && currentJob.getIsSubContract()) {
                    return new ReturnMessage(false, "A main/parent job cannot be converted to a subcontracted job.\n"
                            + "Create a copy of this job instead then convert the copied job to a subcontract.");
                }
            }
        }

        if (currentJob.getJobStatusAndTracking().getCompleted()
                && currentJob.getJobCostingAndPayment().getFinalCost() == 0.0) {

            return new ReturnMessage(false, "A job cannot have a completed 'Work progress' without a final cost.");
        }

        return new ReturnMessage();
    }

    public Boolean validateJobNumber(String jobNumber, Boolean auto) {
        Integer departmentCode = 0;
        Integer year;
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
                        // This means 4th part is not a department code
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