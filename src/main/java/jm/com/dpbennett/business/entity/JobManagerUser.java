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
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobmanageruser")
@NamedQueries({
    @NamedQuery(name = "findAllJobManagerUsers", query = "SELECT e FROM JobManagerUser e ORDER BY e.username")
    ,
    @NamedQuery(name = "findByJobManagerUsername", query = "SELECT e FROM JobManagerUser e WHERE UPPER(e.username) = :username")
})
@XmlRootElement
public class JobManagerUser implements Serializable, BusinessEntity {

    public static final int CANENTERJOB = 0;
    public static final int CANEDITJOB = 1;
    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee employee;
    private String userFirstname;
    private String userLastname;
    private String username;
    @Column(length = 1024)
    private String password;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Privilege privilege;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date pollTime;
    private Long lastDatabaseModuleId;
    // Units
    private Boolean legalMetrologyUnit;
    private Boolean jobManagementAndTrackingUnit;
    private Boolean complianceUnit;
    private Boolean foodsUnit;
    private Boolean standardsAndCertificationUnit;
    private Boolean serviceRequestUnit;
    private Boolean adminUnit;
    private Boolean financialAdminUnit;
    //////////////////////////////////////////////
    private String userInterfaceThemeName;
    private String jobTableViewPreference;
    private Boolean authenticate;

    public JobManagerUser() {
        employee = new Employee();
        username = "";
        userFirstname = "";
        userLastname = "";
    }

    public static Boolean isUserDepartmentSupervisor(Job job, JobManagerUser user, EntityManager em) {

        Job foundJob = Job.findJobById(em, job.getId());

        if (Department.findDepartmentAssignedToJob(foundJob, em).getHead().getId().longValue() == user.getEmployee().getId().longValue()) {
            return true;
        } else if ((Department.findDepartmentAssignedToJob(foundJob, em).getActingHead().getId().longValue() == user.getEmployee().getId().longValue())
                && Department.findDepartmentAssignedToJob(foundJob, em).getActingHeadActive()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean getFinancialAdminUnit() {
        if (financialAdminUnit == null) {
            financialAdminUnit = false;
        }
        return financialAdminUnit;
    }

    public void setFinancialAdminUnit(Boolean financialAdminUnit) {
        this.financialAdminUnit = financialAdminUnit;
    }

    public Boolean checkEffectiveJobPrivilege(int privilege, Job job) {
        switch (privilege) {
            case CANENTERJOB:
                // NB: Aspects of the job are to be considered before 
                // privilege can be determined 
                if (getPrivilege().getCanEnterJob()) {
                    return true;
                }
                break;
        }
        return false;
    }

    public Privilege getPrivilege() {
        if (privilege == null) {
            privilege = new Privilege(username);
        }

        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Boolean getAdminUnit() {
        if (adminUnit == null) {
            adminUnit = false;
        }
        if (!getPrivilege().getCanBeJMTSAdministrator()) {
            adminUnit = false;
        }
        return adminUnit;
    }

    public Boolean getRenderAdminUnit() {
        if (getAdminUnit() && getPrivilege().getCanBeJMTSAdministrator()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAdminUnit(Boolean adminUnit) {
        this.adminUnit = adminUnit;
    }

    public Boolean getAuthenticate() {
        if (authenticate == null) {
            authenticate = true;
        }
        return authenticate;
    }

    public void setAuthenticate(Boolean authenticate) {
        this.authenticate = authenticate;
    }

    public String getJobTableViewPreference() {
        if (jobTableViewPreference == null) {
            jobTableViewPreference = "Jobs";
        }
        return jobTableViewPreference;
    }

    public void setJobTableViewPreference(String jobTableViewPreference) {
        this.jobTableViewPreference = jobTableViewPreference;
    }

    public String getUserInterfaceThemeName() {
        if (userInterfaceThemeName == null) {
            userInterfaceThemeName = "redmond";
        }

        return userInterfaceThemeName;
    }

    public void setUserInterfaceThemeName(String userInterfaceThemeName) {
        this.userInterfaceThemeName = userInterfaceThemeName;
    }

    public Boolean getServiceRequestUnit() {
        if (serviceRequestUnit == null) {
            return false;
        }
        return serviceRequestUnit;
    }

    public void setServiceRequestUnit(Boolean serviceRequestUnit) {
        this.serviceRequestUnit = serviceRequestUnit;
    }

    public Boolean getComplianceUnit() {
        if (complianceUnit == null) {
            return false;
        }
        return complianceUnit;
    }

    public void setComplianceUnit(Boolean complianceUnit) {
        this.complianceUnit = complianceUnit;
    }

    public Boolean getFoodsUnit() {
        if (foodsUnit == null) {
            return false;
        }
        return foodsUnit;

    }

    public void setFoodsUnit(Boolean foodsUnit) {
        this.foodsUnit = foodsUnit;
    }

    public Boolean getStandardsAndCertificationUnit() {
        if (standardsAndCertificationUnit == null) {
            return false;
        }
        return standardsAndCertificationUnit;
    }

    public void setStandardsAndCertificationUnit(Boolean standardsAndCertificationUnit) {
        this.standardsAndCertificationUnit = standardsAndCertificationUnit;
    }

    public Long getLastDatabaseModuleId() {
        return lastDatabaseModuleId;
    }

    public void setLastDatabaseModuleId(Long lastDatabaseModuleId) {
        this.lastDatabaseModuleId = lastDatabaseModuleId;
    }

    public Boolean getJobManagementAndTrackingUnit() {
        if ((jobManagementAndTrackingUnit == null) && (id != null)) {
            jobManagementAndTrackingUnit = true;
        }
        return jobManagementAndTrackingUnit;
    }

    public void setJobManagementAndTrackingUnit(Boolean jobManagementAndTrackingUnit) {
        this.jobManagementAndTrackingUnit = jobManagementAndTrackingUnit;
    }

    public Boolean getLegalMetrologyUnit() {
        return legalMetrologyUnit;
    }

    public void setLegalMetrologyUnit(Boolean legalMetrologyUnit) {
        this.legalMetrologyUnit = legalMetrologyUnit;
    }

    public Boolean isLoggedIn() {
        if (getPollTime() != null) {
            Long currentTime = new Date().getTime();
            Long diff = currentTime - getPollTime().getTime();

            if (diff <= 240000) { // tk get from system option
                return true;
            } else {

                return false;
            }
        } else {
            return false;
        }

    }

    public String getStatus() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");

        if (isLoggedIn()) {
            return "Checked in (" + formatter.format(getPollTime()) + ")";
        } else {
            return "Checked out (" + formatter.format(getPollTime()) + ")";
        }

    }

    public String getEmployeeFirstname() {

        if (getEmployee() != null) {
            return getEmployee().getFirstName();
        } else {
            return "";
        }
    }

    public String getEmployeeLastname() {

        if (getEmployee() != null) {
            return getEmployee().getLastName();
        } else {
            return "";
        }
    }

    public Date getPollTime() {
        return pollTime;
    }

    public void setPollTime(Date pollTime) {
        this.pollTime = pollTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        if (employee == null) {
            employee = new Employee();
        }
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @XmlTransient
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUsername() {
        if (username == null) {
            username = "";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return userFirstname + " " + userLastname + " (" + username + ")";
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public void setName(String name) {
        username = name;
    }

    public static List<JobManagerUser> findJobManagerUsersByUsername(EntityManager em, String username) {

        String newUsername = username.replaceAll("'", "''");

        try {
            List<JobManagerUser> users
                    = em.createQuery("SELECT j FROM JobManagerUser j where UPPER(j.username) like '%"
                            + newUsername.toUpperCase().trim() + "%' ORDER BY j.username", JobManagerUser.class).getResultList();
            return users;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static JobManagerUser findJobManagerUserById(EntityManager em, Long Id) {
        return em.find(JobManagerUser.class, Id);
    }

    public static JobManagerUser findJobManagerUserByUsername(EntityManager em, String username) {

        String newUsername = username.replaceAll("'", "''");
        try {
            List<JobManagerUser> users
                    = em.createNamedQuery("findByJobManagerUsername",
                            JobManagerUser.class).
                            setParameter("username", newUsername.toUpperCase()).getResultList();

            if (users.size() > 0) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static JobManagerUser findJobManagerUserByEmployeeId(EntityManager em, Long employeeId) {
        try {
            List<JobManagerUser> users = em.createQuery(
                    "SELECT j FROM JobManagerUser j"
                    + " JOIN j.employee e"
                    + " WHERE e.id = " + employeeId, JobManagerUser.class).getResultList();

            if (!users.isEmpty()) {
                return users.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobManagerUser> findJobManagerUserByName(EntityManager em, String name) {
        try {
            String newName = name.toUpperCase().trim().replaceAll("'", "''");

            List<JobManagerUser> users = em.createQuery(
                    "SELECT j FROM JobManagerUser j"
                    + " JOIN j.employee e"
                    + " WHERE UPPER(e.firstName) like '%"
                    + newName + "%'" + " OR UPPER(e.lastName) like '%"
                    + newName + "%'" + " OR UPPER(j.username) like '%"
                    + newName + "%'", JobManagerUser.class).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobManagerUser> findAllJobManagerUsers(EntityManager em) {

        try {
            return em.createNamedQuery("findAllJobManagerUsers", JobManagerUser.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean save(EntityManager em, JobManagerUser user) {
        // validate fields
        // username
        if (!BusinessEntityUtils.validateName(user.getUsername())) {
            return false;
        }

        // Get needed objects
        Employee employee = Employee.findEmployeeByName(em, user.getEmployee().getName());
        if (employee != null) {
            user.setEmployee(employee);
        } else {
            user.setEmployee(Employee.findDefaultEmployee(em, "--", "--", false));
        }

        em.getTransaction().begin();
        BusinessEntityUtils.saveBusinessEntity(em, user.getPrivilege());
        BusinessEntityUtils.saveBusinessEntity(em, user);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public MethodResult save(EntityManager em) {

        try {

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new MethodResult();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new MethodResult(false, "User not saved");
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
