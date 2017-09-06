/*
 * To change this template, choose Tools | Templates
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
@Table(name = "jobmanageruser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobmanageruser.findAll", query = "SELECT j FROM Jobmanageruser j"),
    @NamedQuery(name = "Jobmanageruser.findById", query = "SELECT j FROM Jobmanageruser j WHERE j.id = :id"),
    @NamedQuery(name = "Jobmanageruser.findByUserlastname", query = "SELECT j FROM Jobmanageruser j WHERE j.userlastname = :userlastname"),
    @NamedQuery(name = "Jobmanageruser.findByUserfirstname", query = "SELECT j FROM Jobmanageruser j WHERE j.userfirstname = :userfirstname"),
    @NamedQuery(name = "Jobmanageruser.findByCandeletejob", query = "SELECT j FROM Jobmanageruser j WHERE j.candeletejob = :candeletejob"),
    @NamedQuery(name = "Jobmanageruser.findByCanaddclient", query = "SELECT j FROM Jobmanageruser j WHERE j.canaddclient = :canaddclient"),
    @NamedQuery(name = "Jobmanageruser.findByCandeletedepartment", query = "SELECT j FROM Jobmanageruser j WHERE j.candeletedepartment = :candeletedepartment"),
    @NamedQuery(name = "Jobmanageruser.findByCanadddepartment", query = "SELECT j FROM Jobmanageruser j WHERE j.canadddepartment = :canadddepartment"),
    @NamedQuery(name = "Jobmanageruser.findByCanaddemployee", query = "SELECT j FROM Jobmanageruser j WHERE j.canaddemployee = :canaddemployee"),
    @NamedQuery(name = "Jobmanageruser.findByCaneditdepartmentaljob", query = "SELECT j FROM Jobmanageruser j WHERE j.caneditdepartmentaljob = :caneditdepartmentaljob"),
    @NamedQuery(name = "Jobmanageruser.findByPassword", query = "SELECT j FROM Jobmanageruser j WHERE j.password = :password"),
    @NamedQuery(name = "Jobmanageruser.findByCandeleteclient", query = "SELECT j FROM Jobmanageruser j WHERE j.candeleteclient = :candeleteclient"),
    @NamedQuery(name = "Jobmanageruser.findByCaneditownjob", query = "SELECT j FROM Jobmanageruser j WHERE j.caneditownjob = :caneditownjob"),
    @NamedQuery(name = "Jobmanageruser.findByUsername", query = "SELECT j FROM Jobmanageruser j WHERE j.username = :username"),
    @NamedQuery(name = "Jobmanageruser.findByPolltime", query = "SELECT j FROM Jobmanageruser j WHERE j.polltime = :polltime"),
    @NamedQuery(name = "Jobmanageruser.findByCaneditjob", query = "SELECT j FROM Jobmanageruser j WHERE j.caneditjob = :caneditjob"),
    @NamedQuery(name = "Jobmanageruser.findByCandeleteemployee", query = "SELECT j FROM Jobmanageruser j WHERE j.candeleteemployee = :candeleteemployee"),
    @NamedQuery(name = "Jobmanageruser.findByCanenterjob", query = "SELECT j FROM Jobmanageruser j WHERE j.canenterjob = :canenterjob"),
    @NamedQuery(name = "Jobmanageruser.findByCanbejmtsadministrator", query = "SELECT j FROM Jobmanageruser j WHERE j.canbejmtsadministrator = :canbejmtsadministrator"),
    @NamedQuery(name = "Jobmanageruser.findByLegalmetrologyunit", query = "SELECT j FROM Jobmanageruser j WHERE j.legalmetrologyunit = :legalmetrologyunit"),
    @NamedQuery(name = "Jobmanageruser.findByJobmanagementandtrackingunit", query = "SELECT j FROM Jobmanageruser j WHERE j.jobmanagementandtrackingunit = :jobmanagementandtrackingunit"),
    @NamedQuery(name = "Jobmanageruser.findByLastdatabasemoduleid", query = "SELECT j FROM Jobmanageruser j WHERE j.lastdatabasemoduleid = :lastdatabasemoduleid"),
    @NamedQuery(name = "Jobmanageruser.findByComplianceunit", query = "SELECT j FROM Jobmanageruser j WHERE j.complianceunit = :complianceunit"),
    @NamedQuery(name = "Jobmanageruser.findByStandardsandcertificationunit", query = "SELECT j FROM Jobmanageruser j WHERE j.standardsandcertificationunit = :standardsandcertificationunit"),
    @NamedQuery(name = "Jobmanageruser.findByFoodsunit", query = "SELECT j FROM Jobmanageruser j WHERE j.foodsunit = :foodsunit"),
    @NamedQuery(name = "Jobmanageruser.findByServicerequestunit", query = "SELECT j FROM Jobmanageruser j WHERE j.servicerequestunit = :servicerequestunit"),
    @NamedQuery(name = "Jobmanageruser.findByUserinterfacethemename", query = "SELECT j FROM Jobmanageruser j WHERE j.userinterfacethemename = :userinterfacethemename"),
    @NamedQuery(name = "Jobmanageruser.findByJobtableviewpreference", query = "SELECT j FROM Jobmanageruser j WHERE j.jobtableviewpreference = :jobtableviewpreference"),
    @NamedQuery(name = "Jobmanageruser.findByAuthenticate", query = "SELECT j FROM Jobmanageruser j WHERE j.authenticate = :authenticate"),
    @NamedQuery(name = "Jobmanageruser.findByAdminunit", query = "SELECT j FROM Jobmanageruser j WHERE j.adminunit = :adminunit"),
    @NamedQuery(name = "Jobmanageruser.findByCanenterownjob", query = "SELECT j FROM Jobmanageruser j WHERE j.canenterownjob = :canenterownjob"),
    @NamedQuery(name = "Jobmanageruser.findByCanenterdepartmentjob", query = "SELECT j FROM Jobmanageruser j WHERE j.canenterdepartmentjob = :canenterdepartmentjob"),
    @NamedQuery(name = "Jobmanageruser.findByCanbesuperuser", query = "SELECT j FROM Jobmanageruser j WHERE j.canbesuperuser = :canbesuperuser"),
    @NamedQuery(name = "Jobmanageruser.findByFinancialadminunit", query = "SELECT j FROM Jobmanageruser j WHERE j.financialadminunit = :financialadminunit")})
public class JobManagerUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "USERLASTNAME")
    private String userlastname;
    @Size(max = 255)
    @Column(name = "USERFIRSTNAME")
    private String userfirstname;
    @Column(name = "CANDELETEJOB")
    private Boolean candeletejob;
    @Column(name = "CANADDCLIENT")
    private Boolean canaddclient;
    @Column(name = "CANDELETEDEPARTMENT")
    private Boolean candeletedepartment;
    @Column(name = "CANADDDEPARTMENT")
    private Boolean canadddepartment;
    @Column(name = "CANADDEMPLOYEE")
    private Boolean canaddemployee;
    @Column(name = "CANEDITDEPARTMENTALJOB")
    private Boolean caneditdepartmentaljob;
    @Size(max = 1024)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CANDELETECLIENT")
    private Boolean candeleteclient;
    @Column(name = "CANEDITOWNJOB")
    private Boolean caneditownjob;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "POLLTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date polltime;
    @Column(name = "CANEDITJOB")
    private Boolean caneditjob;
    @Column(name = "CANDELETEEMPLOYEE")
    private Boolean candeleteemployee;
    @Column(name = "CANENTERJOB")
    private Boolean canenterjob;
    @Column(name = "CANBEJMTSADMINISTRATOR")
    private Boolean canbejmtsadministrator;
    @Column(name = "LEGALMETROLOGYUNIT")
    private Short legalmetrologyunit;
    @Column(name = "JOBMANAGEMENTANDTRACKINGUNIT")
    private Short jobmanagementandtrackingunit;
    @Column(name = "LASTDATABASEMODULEID")
    private BigInteger lastdatabasemoduleid;
    @Column(name = "COMPLIANCEUNIT")
    private Short complianceunit;
    @Column(name = "STANDARDSANDCERTIFICATIONUNIT")
    private Short standardsandcertificationunit;
    @Column(name = "FOODSUNIT")
    private Short foodsunit;
    @Column(name = "SERVICEREQUESTUNIT")
    private Short servicerequestunit;
    @Size(max = 255)
    @Column(name = "USERINTERFACETHEMENAME")
    private String userinterfacethemename;
    @Size(max = 255)
    @Column(name = "JOBTABLEVIEWPREFERENCE")
    private String jobtableviewpreference;
    @Column(name = "AUTHENTICATE")
    private Boolean authenticate;
    @Column(name = "ADMINUNIT")
    private Boolean adminunit;
    @Column(name = "CANENTEROWNJOB")
    private Boolean canenterownjob;
    @Column(name = "CANENTERDEPARTMENTJOB")
    private Boolean canenterdepartmentjob;
    @Column(name = "CANBESUPERUSER")
    private Boolean canbesuperuser;
    @Column(name = "FINANCIALADMINUNIT")
    private Boolean financialadminunit;
    @JoinTable(name = "jobmanageruser_privilege", joinColumns = {
        @JoinColumn(name = "JobManagerUser_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "privileges_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Privilege> privilegeList;
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentId;
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee employeeId;
    @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Privilege privilegeId;

    public JobManagerUser() {
    }

    public JobManagerUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public Boolean getCandeletejob() {
        return candeletejob;
    }

    public void setCandeletejob(Boolean candeletejob) {
        this.candeletejob = candeletejob;
    }

    public Boolean getCanaddclient() {
        return canaddclient;
    }

    public void setCanaddclient(Boolean canaddclient) {
        this.canaddclient = canaddclient;
    }

    public Boolean getCandeletedepartment() {
        return candeletedepartment;
    }

    public void setCandeletedepartment(Boolean candeletedepartment) {
        this.candeletedepartment = candeletedepartment;
    }

    public Boolean getCanadddepartment() {
        return canadddepartment;
    }

    public void setCanadddepartment(Boolean canadddepartment) {
        this.canadddepartment = canadddepartment;
    }

    public Boolean getCanaddemployee() {
        return canaddemployee;
    }

    public void setCanaddemployee(Boolean canaddemployee) {
        this.canaddemployee = canaddemployee;
    }

    public Boolean getCaneditdepartmentaljob() {
        return caneditdepartmentaljob;
    }

    public void setCaneditdepartmentaljob(Boolean caneditdepartmentaljob) {
        this.caneditdepartmentaljob = caneditdepartmentaljob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCandeleteclient() {
        return candeleteclient;
    }

    public void setCandeleteclient(Boolean candeleteclient) {
        this.candeleteclient = candeleteclient;
    }

    public Boolean getCaneditownjob() {
        return caneditownjob;
    }

    public void setCaneditownjob(Boolean caneditownjob) {
        this.caneditownjob = caneditownjob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getPolltime() {
        return polltime;
    }

    public void setPolltime(Date polltime) {
        this.polltime = polltime;
    }

    public Boolean getCaneditjob() {
        return caneditjob;
    }

    public void setCaneditjob(Boolean caneditjob) {
        this.caneditjob = caneditjob;
    }

    public Boolean getCandeleteemployee() {
        return candeleteemployee;
    }

    public void setCandeleteemployee(Boolean candeleteemployee) {
        this.candeleteemployee = candeleteemployee;
    }

    public Boolean getCanenterjob() {
        return canenterjob;
    }

    public void setCanenterjob(Boolean canenterjob) {
        this.canenterjob = canenterjob;
    }

    public Boolean getCanbejmtsadministrator() {
        return canbejmtsadministrator;
    }

    public void setCanbejmtsadministrator(Boolean canbejmtsadministrator) {
        this.canbejmtsadministrator = canbejmtsadministrator;
    }

    public Short getLegalmetrologyunit() {
        return legalmetrologyunit;
    }

    public void setLegalmetrologyunit(Short legalmetrologyunit) {
        this.legalmetrologyunit = legalmetrologyunit;
    }

    public Short getJobmanagementandtrackingunit() {
        return jobmanagementandtrackingunit;
    }

    public void setJobmanagementandtrackingunit(Short jobmanagementandtrackingunit) {
        this.jobmanagementandtrackingunit = jobmanagementandtrackingunit;
    }

    public BigInteger getLastdatabasemoduleid() {
        return lastdatabasemoduleid;
    }

    public void setLastdatabasemoduleid(BigInteger lastdatabasemoduleid) {
        this.lastdatabasemoduleid = lastdatabasemoduleid;
    }

    public Short getComplianceunit() {
        return complianceunit;
    }

    public void setComplianceunit(Short complianceunit) {
        this.complianceunit = complianceunit;
    }

    public Short getStandardsandcertificationunit() {
        return standardsandcertificationunit;
    }

    public void setStandardsandcertificationunit(Short standardsandcertificationunit) {
        this.standardsandcertificationunit = standardsandcertificationunit;
    }

    public Short getFoodsunit() {
        return foodsunit;
    }

    public void setFoodsunit(Short foodsunit) {
        this.foodsunit = foodsunit;
    }

    public Short getServicerequestunit() {
        return servicerequestunit;
    }

    public void setServicerequestunit(Short servicerequestunit) {
        this.servicerequestunit = servicerequestunit;
    }

    public String getUserinterfacethemename() {
        return userinterfacethemename;
    }

    public void setUserinterfacethemename(String userinterfacethemename) {
        this.userinterfacethemename = userinterfacethemename;
    }

    public String getJobtableviewpreference() {
        return jobtableviewpreference;
    }

    public void setJobtableviewpreference(String jobtableviewpreference) {
        this.jobtableviewpreference = jobtableviewpreference;
    }

    public Boolean getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Boolean authenticate) {
        this.authenticate = authenticate;
    }

    public Boolean getAdminunit() {
        return adminunit;
    }

    public void setAdminunit(Boolean adminunit) {
        this.adminunit = adminunit;
    }

    public Boolean getCanenterownjob() {
        return canenterownjob;
    }

    public void setCanenterownjob(Boolean canenterownjob) {
        this.canenterownjob = canenterownjob;
    }

    public Boolean getCanenterdepartmentjob() {
        return canenterdepartmentjob;
    }

    public void setCanenterdepartmentjob(Boolean canenterdepartmentjob) {
        this.canenterdepartmentjob = canenterdepartmentjob;
    }

    public Boolean getCanbesuperuser() {
        return canbesuperuser;
    }

    public void setCanbesuperuser(Boolean canbesuperuser) {
        this.canbesuperuser = canbesuperuser;
    }

    public Boolean getFinancialadminunit() {
        return financialadminunit;
    }

    public void setFinancialadminunit(Boolean financialadminunit) {
        this.financialadminunit = financialadminunit;
    }

    @XmlTransient
    @JsonIgnore
    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof JobManagerUser)) {
            return false;
        }
        JobManagerUser other = (JobManagerUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobmanageruser[ id=" + id + " ]";
    }
    
}
