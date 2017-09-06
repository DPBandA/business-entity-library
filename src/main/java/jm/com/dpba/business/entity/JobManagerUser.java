/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpba.business.entity.utils.BusinessEntityUtils;


/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobmanageruser")
@NamedQueries({
    @NamedQuery(name = "findAllJobManagerUsers", query = "SELECT e FROM JobManagerUser e ORDER BY e.username"),
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
    @Temporal(TemporalType.DATE)
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
            userInterfaceThemeName = "black-tie";
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

    public String getStatus() {
        Long currentTime = new Date().getTime();

        if (pollTime != null) {
//             System.out.println("poll time: " + pollTime); // tk
            Long diff = currentTime - pollTime.getTime();
//             System.out.println("Time diff: " + diff);
            if (diff <= 241000) {// tk get from system option org <=
                TimeZone z = TimeZone.getTimeZone("America/Jamaica"); // tk get from system option
                Calendar c = Calendar.getInstance();
                c.setTime(pollTime);
                c.setTimeZone(z);

                return "Checked in (" + c.getTime() + ")";
            } else {
                return "--";
            }
        } else {
            System.out.println("poll time: " + pollTime); //tk
            return "--";
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
        if (pollTime == null) {  // tk
            pollTime = new Date(); // tk
        }
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

//    public Boolean getCanBeJMTSAdministrator() {
//        if (canBeJMTSAdministrator == null) {
//            canBeJMTSAdministrator = false;
//        }
//
//        return canBeJMTSAdministrator;
//    }
//
//    public void setCanBeJMTSAdministrator(Boolean canBeJMTSAdministrator) {
//        this.canBeJMTSAdministrator = canBeJMTSAdministrator;
//    }
//
//    public Boolean getCanBeSuperUser() {
//        if (canBeSuperUser == null) {
//            canBeSuperUser = false;
//        }
//        return canBeSuperUser;
//    }
//    public void setCanBeSuperUser(Boolean canBeSuperUser) {
//        this.canBeSuperUser = canBeSuperUser;
//    }
//
//    public Boolean getCanAddClient() {
//        if (canAddClient == null) {
//            canAddClient = false;
//        }
//        return canAddClient;
//    }
//
//    public void setCanAddClient(Boolean canAddClient) {
//        this.canAddClient = canAddClient;
//    }
//
//    public Boolean getCanAddDepartment() {
//        if (canAddDepartment == null) {
//            canAddDepartment = false;
//        }
//        return canAddDepartment;
//    }
//
//    public void setCanAddDepartment(Boolean canAddDepartment) {
//        this.canAddDepartment = canAddDepartment;
//    }
//
//    public Boolean getCanAddEmployee() {
//        if (canAddEmployee == null) {
//            canAddEmployee = false;
//        }
//        return canAddEmployee;
//    }
//
//    public void setCanAddEmployee(Boolean canAddEmployee) {
//        this.canAddEmployee = canAddEmployee;
//    }
//
//    public Boolean getCanDeleteClient() {
//        if (canDeleteClient == null) {
//            canDeleteClient = false;
//        }
//        return canDeleteClient;
//    }
//
//    public void setCanDeleteClient(Boolean canDeleteClient) {
//        this.canDeleteClient = canDeleteClient;
//    }
//
//    public Boolean getCanDeleteDepartment() {
//        if (canDeleteDepartment == null) {
//            canDeleteDepartment = false;
//        }
//        return canDeleteDepartment;
//    }
//
//    public void setCanDeleteDepartment(Boolean canDeleteDepartment) {
//        this.canDeleteDepartment = canDeleteDepartment;
//    }
//
//    public Boolean getCanDeleteEmployee() {
//        if (canDeleteEmployee == null) {
//            canDeleteEmployee = false;
//        }
//        return canDeleteEmployee;
//    }
//
//    public void setCanDeleteEmployee(Boolean canDeleteEmployee) {
//        this.canDeleteEmployee = canDeleteEmployee;
//    }
//
//    public Boolean getCanEditDepartmentalJob() {
//        if (canEditDepartmentalJob == null) {
//            canEditDepartmentalJob = true;
//        }
//        return canEditDepartmentalJob;
//    }
//
//    public void setCanEditDepartmentalJob(Boolean canEditDepartmentalJob) {
//        this.canEditDepartmentalJob = canEditDepartmentalJob;
//    }
//
//    public Boolean getCanDeleteJob() {
//        if (canDeleteJob == null) {
//            canDeleteJob = false;
//        }
//        return canDeleteJob;
//    }
//
//    public void setCanDeleteJob(Boolean canDeleteJob) {
//        this.canDeleteJob = canDeleteJob;
//    }
//
//    public Boolean getCanEditJob() {
//        if (canEditJob == null) {
//            canEditJob = false;
//        }
//        return canEditJob;
//    }
//
//    public void setCanEditJob(Boolean canEditJob) {
//        this.canEditJob = canEditJob;
//    }
//
//    public Boolean getCanEditOwnJob() {
//        if (canEditOwnJob == null) {
//            canEditOwnJob = false;
//        }
//        return canEditOwnJob;
//    }
//    public void setCanEditOwnJob(Boolean canEditOwnJob) {
//        this.canEditOwnJob = canEditOwnJob;
//    }
//
//    public Boolean getCanEnterJob() {
//        if (canEnterJob == null) {
//            canEnterJob = false;
//        }
//        return canEnterJob;
//    }
//
//    public void setCanEnterJob(Boolean canEnterJob) {
//        this.canEnterJob = canEnterJob;
//    }
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
            List<JobManagerUser> users =
                    em.createQuery("SELECT j FROM JobManagerUser j where UPPER(j.username) like '%"
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
            List<JobManagerUser> users =
                    em.createNamedQuery("findByJobManagerUsername",
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

    // tk be made efficient by find all users with the given status
    public static List<JobManagerUser> findLoggedInJobManagerUsers(EntityManager em) {
        List<JobManagerUser> users = findAllJobManagerUsers(em);
        List<JobManagerUser> loggedInusers = new ArrayList<JobManagerUser>();

        if (users != null) {
            for (JobManagerUser jobManagerUser : users) {
                em.refresh(jobManagerUser);
                if (!jobManagerUser.getStatus().equals("--")) {
                    loggedInusers.add(jobManagerUser);
                }
            }
        }

        return loggedInusers;
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
}
