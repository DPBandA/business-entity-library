/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
    , @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id")
    , @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name")
    , @NamedQuery(name = "Department.findByActive", query = "SELECT d FROM Department d WHERE d.active = :active")
    , @NamedQuery(name = "Department.findBySubgroupcode", query = "SELECT d FROM Department d WHERE d.subgroupcode = :subgroupcode")
    , @NamedQuery(name = "Department.findByActingheadactive", query = "SELECT d FROM Department d WHERE d.actingheadactive = :actingheadactive")
    , @NamedQuery(name = "Department.findByJobcostingtype", query = "SELECT d FROM Department d WHERE d.jobcostingtype = :jobcostingtype")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "SUBGROUPCODE")
    private String subgroupcode;
    @Column(name = "ACTINGHEADACTIVE")
    private Boolean actingheadactive;
    @Column(name = "JOBCOSTINGTYPE")
    private String jobcostingtype;
    @JoinTable(name = "department_laboratory", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "laboratories_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Laboratory> laboratoryList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Jobsubcategory> jobsubcategoryList;
    @JoinTable(name = "department_employee", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "staff_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Employee> employeeList;
    @JoinTable(name = "department_jobcategory", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "jobCategories_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Jobcategory> jobcategoryList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Division> divisionList;
    @JoinTable(name = "department_departmentunit", joinColumns = {
        @JoinColumn(name = "Department_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "departmentUnits_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Departmentunit> departmentunitList;
    @ManyToMany(mappedBy = "departmentList")
    private List<Sector> sectorList;
    @ManyToMany(mappedBy = "departmentList1")
    private List<Jobcategory> jobcategoryList1;
    @ManyToMany(mappedBy = "departmentList")
    private List<Service> serviceList;
    @OneToMany(mappedBy = "departmentId")
    private List<Employee> employeeList1;
    @OneToMany(mappedBy = "departmentId")
    private List<Departmentreport> departmentreportList;
    @OneToMany(mappedBy = "departmentId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "departmentId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "subcontracteddepartmentId")
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
    public List<Laboratory> getLaboratoryList() {
        return laboratoryList;
    }

    public void setLaboratoryList(List<Laboratory> laboratoryList) {
        this.laboratoryList = laboratoryList;
    }

    @XmlTransient
    public List<Jobsubcategory> getJobsubcategoryList() {
        return jobsubcategoryList;
    }

    public void setJobsubcategoryList(List<Jobsubcategory> jobsubcategoryList) {
        this.jobsubcategoryList = jobsubcategoryList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<Jobcategory> getJobcategoryList() {
        return jobcategoryList;
    }

    public void setJobcategoryList(List<Jobcategory> jobcategoryList) {
        this.jobcategoryList = jobcategoryList;
    }

    @XmlTransient
    public List<Division> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }

    @XmlTransient
    public List<Departmentunit> getDepartmentunitList() {
        return departmentunitList;
    }

    public void setDepartmentunitList(List<Departmentunit> departmentunitList) {
        this.departmentunitList = departmentunitList;
    }

    @XmlTransient
    public List<Sector> getSectorList() {
        return sectorList;
    }

    public void setSectorList(List<Sector> sectorList) {
        this.sectorList = sectorList;
    }

    @XmlTransient
    public List<Jobcategory> getJobcategoryList1() {
        return jobcategoryList1;
    }

    public void setJobcategoryList1(List<Jobcategory> jobcategoryList1) {
        this.jobcategoryList1 = jobcategoryList1;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList1() {
        return employeeList1;
    }

    public void setEmployeeList1(List<Employee> employeeList1) {
        this.employeeList1 = employeeList1;
    }

    @XmlTransient
    public List<Departmentreport> getDepartmentreportList() {
        return departmentreportList;
    }

    public void setDepartmentreportList(List<Departmentreport> departmentreportList) {
        this.departmentreportList = departmentreportList;
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
    public List<Job> getJobList1() {
        return jobList1;
    }

    public void setJobList1(List<Job> jobList1) {
        this.jobList1 = jobList1;
    }

    @XmlTransient
    public List<Documenttracking> getDocumenttrackingList() {
        return documenttrackingList;
    }

    public void setDocumenttrackingList(List<Documenttracking> documenttrackingList) {
        this.documenttrackingList = documenttrackingList;
    }

    @XmlTransient
    public List<Documenttracking> getDocumenttrackingList1() {
        return documenttrackingList1;
    }

    public void setDocumenttrackingList1(List<Documenttracking> documenttrackingList1) {
        this.documenttrackingList1 = documenttrackingList1;
    }

    @XmlTransient
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
        return "jm.com.dpba.business.entity.Department[ id=" + id + " ]";
    }
    
}
