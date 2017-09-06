/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
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
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name"),
    @NamedQuery(name = "Department.findByActive", query = "SELECT d FROM Department d WHERE d.active = :active"),
    @NamedQuery(name = "Department.findBySubgroupcode", query = "SELECT d FROM Department d WHERE d.subgroupcode = :subgroupcode"),
    @NamedQuery(name = "Department.findByActingheadactive", query = "SELECT d FROM Department d WHERE d.actingheadactive = :actingheadactive"),
    @NamedQuery(name = "Department.findByJobcostingtype", query = "SELECT d FROM Department d WHERE d.jobcostingtype = :jobcostingtype")})
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "SUBGROUPCODE")
    private String subgroupcode;
    @Column(name = "ACTINGHEADACTIVE")
    private Boolean actingheadactive;
    @Size(max = 255)
    @Column(name = "JOBCOSTINGTYPE")
    private String jobcostingtype;
    @JoinTable(name = "department_laboratory", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "laboratories_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Laboratory> laboratoryList;
    @JoinTable(name = "department_employee", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "staff_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Employee> employeeList;
    @JoinTable(name = "department_departmentunit", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "departmentUnits_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Departmentunit> departmentunitList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Sector> sectorList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Jobsubcategory> jobsubcategoryList;
    @JoinTable(name = "department_jobcategory", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "jobCategories_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Jobcategory> jobcategoryList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Division> divisionList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Jobreportitem> jobreportitemList;
    @ManyToMany(mappedBy = "departmentList1")
    private List<Jobcategory> jobcategoryList1;
    @ManyToMany(mappedBy = "departmentList")
    private List<Service> serviceList;
    @OneToMany(mappedBy = "departmentId")
    private List<Employee> employeeList1;
    @OneToMany(mappedBy = "departmentId")
    private List<JobManagerUser> jobmanageruserList;
    @OneToMany(mappedBy = "departmentresponsibleId")
    private List<Jobtask> jobtaskList;
    @OneToMany(mappedBy = "departmentId")
    private List<Departmentreport> departmentreportList;
    @OneToMany(mappedBy = "requestingdepartmentId")
    private List<Legaldocument> legaldocumentList;
    @OneToMany(mappedBy = "responsibledepartmentId")
    private List<Legaldocument> legaldocumentList1;
    @OneToMany(mappedBy = "departmentId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "subcontracteddepartmentId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "departmentId")
    private List<Job> jobList1;
    @OneToMany(mappedBy = "requestingdepartmentId")
    private List<Documenttracking> documenttrackingList;
    @OneToMany(mappedBy = "responsibledepartmentId")
    private List<Documenttracking> documenttrackingList1;
    @OneToMany(mappedBy = "departmentId")
    private List<Unitcost> unitcostList;
    @JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Division divisionId;
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee managerId;
    @JoinColumn(name = "TEAMLEADER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee teamleaderId;
    @JoinColumn(name = "ACTINGHEAD_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee actingheadId;
    @JoinColumn(name = "HEAD_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee headId;
    @JoinColumn(name = "INTERNET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Internet internetId;
    @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Privilege privilegeId;

    public Department() {
    }

    public Department(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubgroupcode() {
        return subgroupcode;
    }

    public void setSubgroupcode(String subgroupcode) {
        this.subgroupcode = subgroupcode;
    }

    public Boolean getActingheadactive() {
        return actingheadactive;
    }

    public void setActingheadactive(Boolean actingheadactive) {
        this.actingheadactive = actingheadactive;
    }

    public String getJobcostingtype() {
        return jobcostingtype;
    }

    public void setJobcostingtype(String jobcostingtype) {
        this.jobcostingtype = jobcostingtype;
    }

    @XmlTransient
    @JsonIgnore
    public List<Laboratory> getLaboratoryList() {
        return laboratoryList;
    }

    public void setLaboratoryList(List<Laboratory> laboratoryList) {
        this.laboratoryList = laboratoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Departmentunit> getDepartmentunitList() {
        return departmentunitList;
    }

    public void setDepartmentunitList(List<Departmentunit> departmentunitList) {
        this.departmentunitList = departmentunitList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Sector> getSectorList() {
        return sectorList;
    }

    public void setSectorList(List<Sector> sectorList) {
        this.sectorList = sectorList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobsubcategory> getJobsubcategoryList() {
        return jobsubcategoryList;
    }

    public void setJobsubcategoryList(List<Jobsubcategory> jobsubcategoryList) {
        this.jobsubcategoryList = jobsubcategoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobcategory> getJobcategoryList() {
        return jobcategoryList;
    }

    public void setJobcategoryList(List<Jobcategory> jobcategoryList) {
        this.jobcategoryList = jobcategoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Division> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobreportitem> getJobreportitemList() {
        return jobreportitemList;
    }

    public void setJobreportitemList(List<Jobreportitem> jobreportitemList) {
        this.jobreportitemList = jobreportitemList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobcategory> getJobcategoryList1() {
        return jobcategoryList1;
    }

    public void setJobcategoryList1(List<Jobcategory> jobcategoryList1) {
        this.jobcategoryList1 = jobcategoryList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Employee> getEmployeeList1() {
        return employeeList1;
    }

    public void setEmployeeList1(List<Employee> employeeList1) {
        this.employeeList1 = employeeList1;
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
    public List<Jobtask> getJobtaskList() {
        return jobtaskList;
    }

    public void setJobtaskList(List<Jobtask> jobtaskList) {
        this.jobtaskList = jobtaskList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Departmentreport> getDepartmentreportList() {
        return departmentreportList;
    }

    public void setDepartmentreportList(List<Departmentreport> departmentreportList) {
        this.departmentreportList = departmentreportList;
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
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
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
    public List<Job> getJobList1() {
        return jobList1;
    }

    public void setJobList1(List<Job> jobList1) {
        this.jobList1 = jobList1;
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
    public List<Unitcost> getUnitcostList() {
        return unitcostList;
    }

    public void setUnitcostList(List<Unitcost> unitcostList) {
        this.unitcostList = unitcostList;
    }

    public Division getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Division divisionId) {
        this.divisionId = divisionId;
    }

    public Employee getManagerId() {
        return managerId;
    }

    public void setManagerId(Employee managerId) {
        this.managerId = managerId;
    }

    public Employee getTeamleaderId() {
        return teamleaderId;
    }

    public void setTeamleaderId(Employee teamleaderId) {
        this.teamleaderId = teamleaderId;
    }

    public Employee getActingheadId() {
        return actingheadId;
    }

    public void setActingheadId(Employee actingheadId) {
        this.actingheadId = actingheadId;
    }

    public Employee getHeadId() {
        return headId;
    }

    public void setHeadId(Employee headId) {
        this.headId = headId;
    }

    public Internet getInternetId() {
        return internetId;
    }

    public void setInternetId(Internet internetId) {
        this.internetId = internetId;
    }

    public Privilege getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Privilege privilegeId) {
        this.privilegeId = privilegeId;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Department[ id=" + id + " ]";
    }
    
}
