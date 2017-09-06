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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByMiddlename", query = "SELECT e FROM Employee e WHERE e.middlename = :middlename"),
    @NamedQuery(name = "Employee.findByNamesuffix", query = "SELECT e FROM Employee e WHERE e.namesuffix = :namesuffix"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findBySex", query = "SELECT e FROM Employee e WHERE e.sex = :sex"),
    @NamedQuery(name = "Employee.findByNumber", query = "SELECT e FROM Employee e WHERE e.number = :number"),
    @NamedQuery(name = "Employee.findByBillingrate", query = "SELECT e FROM Employee e WHERE e.billingrate = :billingrate"),
    @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username"),
    @NamedQuery(name = "Employee.findByTitle", query = "SELECT e FROM Employee e WHERE e.title = :title"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findByActive", query = "SELECT e FROM Employee e WHERE e.active = :active"),
    @NamedQuery(name = "Employee.findByBirthdate", query = "SELECT e FROM Employee e WHERE e.birthdate = :birthdate"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByNotes", query = "SELECT e FROM Employee e WHERE e.notes = :notes"),
    @NamedQuery(name = "Employee.findByDatehired", query = "SELECT e FROM Employee e WHERE e.datehired = :datehired"),
    @NamedQuery(name = "Employee.findByEmploymenttype", query = "SELECT e FROM Employee e WHERE e.employmenttype = :employmenttype"),
    @NamedQuery(name = "Employee.findByPost", query = "SELECT e FROM Employee e WHERE e.post = :post")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "MIDDLENAME")
    private String middlename;
    @Size(max = 255)
    @Column(name = "NAMESUFFIX")
    private String namesuffix;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 255)
    @Column(name = "SEX")
    private String sex;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BILLINGRATE")
    private Double billingrate;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 255)
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "DATEHIRED")
    @Temporal(TemporalType.DATE)
    private Date datehired;
    @Size(max = 255)
    @Column(name = "EMPLOYMENTTYPE")
    private String employmenttype;
    @Size(max = 255)
    @Column(name = "POST")
    private String post;
    @ManyToMany(mappedBy = "employeeList")
    private List<Department> departmentList;
    @JoinTable(name = "employee_phonenumber", joinColumns = {
        @JoinColumn(name = "Employee_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "phoneNumbers_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Phonenumber> phonenumberList;
    @JoinTable(name = "employee_address", joinColumns = {
        @JoinColumn(name = "Employee_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "addresses_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Address> addressList;
    @OneToMany(mappedBy = "calibrationdonebyId")
    private List<Petrolpumpcalibration> petrolpumpcalibrationList;
    @JoinColumn(name = "BUSINESSOFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Businessoffice businessofficeId;
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentId;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;
    @JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Business businessId;
    @JoinColumn(name = "SIGNATURE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature signatureId;
    @OneToMany(mappedBy = "inspectorId")
    private List<Productinspection> productinspectionList;
    @OneToMany(mappedBy = "inspectorId")
    private List<Samplerequest> samplerequestList;
    @OneToMany(mappedBy = "inspectorId")
    private List<Marketproductsurvey> marketproductsurveyList;
    @OneToMany(mappedBy = "employeeId")
    private List<JobManagerUser> jobmanageruserList;
    @OneToMany(mappedBy = "inspectorId")
    private List<Documentinspection> documentinspectionList;
    @OneToMany(mappedBy = "employeeresponsibleId")
    private List<Jobtask> jobtaskList;
    @OneToMany(mappedBy = "editedbyId")
    private List<Jobstatusandtracking> jobstatusandtrackingList;
    @OneToMany(mappedBy = "enteredbyId")
    private List<Jobstatusandtracking> jobstatusandtrackingList1;
    @OneToMany(mappedBy = "transferredtoId")
    private List<Jobstatusandtracking> jobstatusandtrackingList2;
    @OneToMany(mappedBy = "editedbyId")
    private List<Compliancesurvey> compliancesurveyList;
    @OneToMany(mappedBy = "authempfornoticeofreleasefromdentiondmId")
    private List<Compliancesurvey> compliancesurveyList1;
    @OneToMany(mappedBy = "inspectorId")
    private List<Compliancesurvey> compliancesurveyList2;
    @OneToMany(mappedBy = "approvedbyemployeeforreleaserequestpoeId")
    private List<Compliancesurvey> compliancesurveyList3;
    @OneToMany(mappedBy = "authemployeefornoticeofdentiondmId")
    private List<Compliancesurvey> compliancesurveyList4;
    @OneToMany(mappedBy = "preparedbyemployeeforreleaserequestpoeId")
    private List<Compliancesurvey> compliancesurveyList5;
    @OneToMany(mappedBy = "authemployeefordetentionrequestpoeId")
    private List<Compliancesurvey> compliancesurveyList6;
    @OneToMany(mappedBy = "responsibleofficerId")
    private List<Legaldocument> legaldocumentList;
    @OneToMany(mappedBy = "submittedbyId")
    private List<Legaldocument> legaldocumentList1;
    @OneToMany(mappedBy = "editedbyId")
    private List<Legaldocument> legaldocumentList2;
    @OneToMany(mappedBy = "testdonebyId")
    private List<Producttest> producttestList;
    @OneToMany(mappedBy = "editedbyId")
    private List<Documentstandard> documentstandardList;
    @OneToMany(mappedBy = "certificatesignedbyId")
    private List<Certification> certificationList;
    @OneToMany(mappedBy = "assignedtoId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "editedbyId")
    private List<Servicerequest> servicerequestList1;
    @OneToMany(mappedBy = "enteredbyId")
    private List<Servicerequest> servicerequestList2;
    @OneToMany(mappedBy = "lastassigneeId")
    private List<Petrolstation> petrolstationList;
    @OneToMany(mappedBy = "assignedtoId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "editedbyId")
    private List<Documenttracking> documenttrackingList;
    @OneToMany(mappedBy = "responsibleofficerId")
    private List<Documenttracking> documenttrackingList1;
    @OneToMany(mappedBy = "submittedbyId")
    private List<Documenttracking> documenttrackingList2;
    @OneToMany(mappedBy = "receivedbyId")
    private List<Foodsample> foodsampleList;
    @OneToMany(mappedBy = "sampledbyId")
    private List<Foodsample> foodsampleList1;
    @OneToMany(mappedBy = "assignedinspectorId")
    private List<Factoryinspection> factoryinspectionList;
    @OneToMany(mappedBy = "receivedbyId")
    private List<Jobsample> jobsampleList;
    @OneToMany(mappedBy = "sampledbyId")
    private List<Jobsample> jobsampleList1;
    @OneToMany(mappedBy = "assigneeId")
    private List<Seal> sealList;
    @OneToMany(mappedBy = "inspectorId")
    private List<Portofentrydetention> portofentrydetentionList;
    @OneToMany(mappedBy = "recipientId")
    private List<Alert> alertList;
    @OneToMany(mappedBy = "enteredbyId")
    private List<Client> clientList;
    @OneToMany(mappedBy = "recordedbyId")
    private List<Petrolpumpcalibrationvalue> petrolpumpcalibrationvalueList;
    @OneToMany(mappedBy = "testdonebyId")
    private List<Foodtest> foodtestList;
    @OneToMany(mappedBy = "managerId")
    private List<Department> departmentList1;
    @OneToMany(mappedBy = "teamleaderId")
    private List<Department> departmentList2;
    @OneToMany(mappedBy = "actingheadId")
    private List<Department> departmentList3;
    @OneToMany(mappedBy = "headId")
    private List<Department> departmentList4;
    @OneToMany(mappedBy = "assignedinspectorId")
    private List<Foodfactory> foodfactoryList;
    @OneToMany(mappedBy = "editedbyId")
    private List<Foodfactory> foodfactoryList1;
    @OneToMany(mappedBy = "assigneeId")
    private List<Sticker> stickerList;
    @OneToMany(mappedBy = "calibrationdonebyId")
    private List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList;
    @OneToMany(mappedBy = "costingapprovedbyId")
    private List<Jobcostingandpayment> jobcostingandpaymentList;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getNamesuffix() {
        return namesuffix;
    }

    public void setNamesuffix(String namesuffix) {
        this.namesuffix = namesuffix;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getBillingrate() {
        return billingrate;
    }

    public void setBillingrate(Double billingrate) {
        this.billingrate = billingrate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDatehired() {
        return datehired;
    }

    public void setDatehired(Date datehired) {
        this.datehired = datehired;
    }

    public String getEmploymenttype() {
        return employmenttype;
    }

    public void setEmploymenttype(String employmenttype) {
        this.employmenttype = employmenttype;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Phonenumber> getPhonenumberList() {
        return phonenumberList;
    }

    public void setPhonenumberList(List<Phonenumber> phonenumberList) {
        this.phonenumberList = phonenumberList;
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
    public List<Petrolpumpcalibration> getPetrolpumpcalibrationList() {
        return petrolpumpcalibrationList;
    }

    public void setPetrolpumpcalibrationList(List<Petrolpumpcalibration> petrolpumpcalibrationList) {
        this.petrolpumpcalibrationList = petrolpumpcalibrationList;
    }

    public Businessoffice getBusinessofficeId() {
        return businessofficeId;
    }

    public void setBusinessofficeId(Businessoffice businessofficeId) {
        this.businessofficeId = businessofficeId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
    }

    public Business getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Business businessId) {
        this.businessId = businessId;
    }

    public Signature getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(Signature signatureId) {
        this.signatureId = signatureId;
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
    public List<JobManagerUser> getJobmanageruserList() {
        return jobmanageruserList;
    }

    public void setJobmanageruserList(List<JobManagerUser> jobmanageruserList) {
        this.jobmanageruserList = jobmanageruserList;
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
    public List<Jobtask> getJobtaskList() {
        return jobtaskList;
    }

    public void setJobtaskList(List<Jobtask> jobtaskList) {
        this.jobtaskList = jobtaskList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobstatusandtracking> getJobstatusandtrackingList() {
        return jobstatusandtrackingList;
    }

    public void setJobstatusandtrackingList(List<Jobstatusandtracking> jobstatusandtrackingList) {
        this.jobstatusandtrackingList = jobstatusandtrackingList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobstatusandtracking> getJobstatusandtrackingList1() {
        return jobstatusandtrackingList1;
    }

    public void setJobstatusandtrackingList1(List<Jobstatusandtracking> jobstatusandtrackingList1) {
        this.jobstatusandtrackingList1 = jobstatusandtrackingList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobstatusandtracking> getJobstatusandtrackingList2() {
        return jobstatusandtrackingList2;
    }

    public void setJobstatusandtrackingList2(List<Jobstatusandtracking> jobstatusandtrackingList2) {
        this.jobstatusandtrackingList2 = jobstatusandtrackingList2;
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
    public List<Compliancesurvey> getCompliancesurveyList3() {
        return compliancesurveyList3;
    }

    public void setCompliancesurveyList3(List<Compliancesurvey> compliancesurveyList3) {
        this.compliancesurveyList3 = compliancesurveyList3;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList4() {
        return compliancesurveyList4;
    }

    public void setCompliancesurveyList4(List<Compliancesurvey> compliancesurveyList4) {
        this.compliancesurveyList4 = compliancesurveyList4;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList5() {
        return compliancesurveyList5;
    }

    public void setCompliancesurveyList5(List<Compliancesurvey> compliancesurveyList5) {
        this.compliancesurveyList5 = compliancesurveyList5;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList6() {
        return compliancesurveyList6;
    }

    public void setCompliancesurveyList6(List<Compliancesurvey> compliancesurveyList6) {
        this.compliancesurveyList6 = compliancesurveyList6;
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
    public List<Legaldocument> getLegaldocumentList1() {
        return legaldocumentList1;
    }

    public void setLegaldocumentList1(List<Legaldocument> legaldocumentList1) {
        this.legaldocumentList1 = legaldocumentList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Legaldocument> getLegaldocumentList2() {
        return legaldocumentList2;
    }

    public void setLegaldocumentList2(List<Legaldocument> legaldocumentList2) {
        this.legaldocumentList2 = legaldocumentList2;
    }

    @XmlTransient
    @JsonIgnore
    public List<Producttest> getProducttestList() {
        return producttestList;
    }

    public void setProducttestList(List<Producttest> producttestList) {
        this.producttestList = producttestList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Documentstandard> getDocumentstandardList() {
        return documentstandardList;
    }

    public void setDocumentstandardList(List<Documentstandard> documentstandardList) {
        this.documentstandardList = documentstandardList;
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
    public List<Servicerequest> getServicerequestList1() {
        return servicerequestList1;
    }

    public void setServicerequestList1(List<Servicerequest> servicerequestList1) {
        this.servicerequestList1 = servicerequestList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Servicerequest> getServicerequestList2() {
        return servicerequestList2;
    }

    public void setServicerequestList2(List<Servicerequest> servicerequestList2) {
        this.servicerequestList2 = servicerequestList2;
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
    public List<Documenttracking> getDocumenttrackingList1() {
        return documenttrackingList1;
    }

    public void setDocumenttrackingList1(List<Documenttracking> documenttrackingList1) {
        this.documenttrackingList1 = documenttrackingList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Documenttracking> getDocumenttrackingList2() {
        return documenttrackingList2;
    }

    public void setDocumenttrackingList2(List<Documenttracking> documenttrackingList2) {
        this.documenttrackingList2 = documenttrackingList2;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodsample> getFoodsampleList() {
        return foodsampleList;
    }

    public void setFoodsampleList(List<Foodsample> foodsampleList) {
        this.foodsampleList = foodsampleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodsample> getFoodsampleList1() {
        return foodsampleList1;
    }

    public void setFoodsampleList1(List<Foodsample> foodsampleList1) {
        this.foodsampleList1 = foodsampleList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Factoryinspection> getFactoryinspectionList() {
        return factoryinspectionList;
    }

    public void setFactoryinspectionList(List<Factoryinspection> factoryinspectionList) {
        this.factoryinspectionList = factoryinspectionList;
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
    public List<Jobsample> getJobsampleList1() {
        return jobsampleList1;
    }

    public void setJobsampleList1(List<Jobsample> jobsampleList1) {
        this.jobsampleList1 = jobsampleList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Seal> getSealList() {
        return sealList;
    }

    public void setSealList(List<Seal> sealList) {
        this.sealList = sealList;
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
    public List<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(List<Alert> alertList) {
        this.alertList = alertList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpcalibrationvalue> getPetrolpumpcalibrationvalueList() {
        return petrolpumpcalibrationvalueList;
    }

    public void setPetrolpumpcalibrationvalueList(List<Petrolpumpcalibrationvalue> petrolpumpcalibrationvalueList) {
        this.petrolpumpcalibrationvalueList = petrolpumpcalibrationvalueList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodtest> getFoodtestList() {
        return foodtestList;
    }

    public void setFoodtestList(List<Foodtest> foodtestList) {
        this.foodtestList = foodtestList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList1() {
        return departmentList1;
    }

    public void setDepartmentList1(List<Department> departmentList1) {
        this.departmentList1 = departmentList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList2() {
        return departmentList2;
    }

    public void setDepartmentList2(List<Department> departmentList2) {
        this.departmentList2 = departmentList2;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList3() {
        return departmentList3;
    }

    public void setDepartmentList3(List<Department> departmentList3) {
        this.departmentList3 = departmentList3;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList4() {
        return departmentList4;
    }

    public void setDepartmentList4(List<Department> departmentList4) {
        this.departmentList4 = departmentList4;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList1() {
        return foodfactoryList1;
    }

    public void setFoodfactoryList1(List<Foodfactory> foodfactoryList1) {
        this.foodfactoryList1 = foodfactoryList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzlecalibration> getPetrolpumpnozzlecalibrationList() {
        return petrolpumpnozzlecalibrationList;
    }

    public void setPetrolpumpnozzlecalibrationList(List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList) {
        this.petrolpumpnozzlecalibrationList = petrolpumpnozzlecalibrationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobcostingandpayment> getJobcostingandpaymentList() {
        return jobcostingandpaymentList;
    }

    public void setJobcostingandpaymentList(List<Jobcostingandpayment> jobcostingandpaymentList) {
        this.jobcostingandpaymentList = jobcostingandpaymentList;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Employee[ id=" + id + " ]";
    }
    
}
