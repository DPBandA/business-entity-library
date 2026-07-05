/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.DefaultEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.auth.Privilege;
import jm.com.dpbennett.business.entity.hrm.Business;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.jmts.Job;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "jobmanageruser")
@NamedQueries({
    @NamedQuery(name = "findAllJobManagerUsers", query = "SELECT e FROM User e ORDER BY e.username"),
    @NamedQuery(name = "findByJobManagerUsername", query = "SELECT e FROM User e WHERE UPPER(e.username) = :username")

})
public class User extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private Boolean active;
    private String username;
    private String PFThemeName;
    private String jobTableViewPreference;
    private Boolean authenticate;
    private String activity;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date pollTime;
    @Column(length = 255)
    private String password;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL)
    private Privilege privilege;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Privilege> privileges;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Module> activeModules;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<SystemOption> settings;
    @Transient
    private Boolean isDirty;
    @Transient
    private String newPassword;
    @Transient
    private String confirmedNewPassword;
    @Transient
    private Boolean updateLDAPUser;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date loginTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date logoutTime;
    @Transient
    private String email;

    public User() {
        employee = new Employee();
        username = "";
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public List<SystemOption> getSettings() {
        if (settings == null) {
            settings = new ArrayList<>();
        }

        return settings;
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        this.settings = settings;
    }

    public static boolean isNotificationActive(
            User user,
            String notificationSetting) {

        SystemOption ns = user.getSetting(
                notificationSetting,
                null,
                "Boolean",
                "Notification");

        return ns.getBoolean();

    }

    public boolean isJobAssigned() {

        SystemOption setting = getSetting(
                "jobAssigned",
                "false",
                "Boolean",
                "Notification");

        return setting.getBoolean();
    }

    public void setJobAssigned(boolean jobAssigned) {
        setSetting(
                "jobAssigned",
                Boolean.toString(jobAssigned),
                "Boolean",
                "Notification");
    }

    public boolean isJobCostingPrepared() {
        SystemOption setting = getSetting(
                "jobCostingPrepared",
                "false",
                "Boolean",
                "Notification");

        return setting.getBoolean();

    }

    public void setJobCostingPrepared(boolean jobCostingPrepared) {
        setSetting(
                "jobCostingPrepared",
                Boolean.toString(jobCostingPrepared),
                "Boolean",
                "Notification");

    }

    public boolean isJobCostingApproved() {
        SystemOption setting = getSetting(
                "jobCostingApproved",
                "false",
                "Boolean",
                "Notification");

        return setting.getBoolean();

    }

    public void setJobCostingApproved(boolean jobCostingApproved) {
        setSetting(
                "jobCostingApproved",
                Boolean.toString(jobCostingApproved),
                "Boolean",
                "Notification");
    }

    public boolean isCashPaymentMade() {
        SystemOption setting = getSetting(
                "cashPaymentMade",
                "false",
                "Boolean",
                "Notification");

        return setting.getBoolean();

    }

    public void setCashPaymentMade(boolean cashPaymentMade) {
        setSetting(
                "cashPaymentMade",
                Boolean.toString(cashPaymentMade),
                "Boolean",
                "Notification");
    }

    public boolean isRenderMobileUI() {
        SystemOption setting = getSetting(
                "renderMobileUI",
                "false",
                "Boolean",
                "Notification");

        return setting.getBoolean();
    }

    public void setRenderMobileUI(boolean renderMobileUI) {
        setSetting(
                "renderMobileUI",
                Boolean.toString(renderMobileUI),
                "Boolean",
                "Notification");
    }

    public Privilege getPrivilege() {

        if (privilege == null) {
            return new Privilege();
        }

        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public String getEmail() {
        if (email == null) {
            email = "";
        }

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUpdateLDAPUser() {
        return updateLDAPUser;
    }

    public void setUpdateLDAPUser(Boolean updateLDAPUser) {
        this.updateLDAPUser = updateLDAPUser;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNewPassword() {
        if (newPassword == null) {
            newPassword = "";
        }
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedNewPassword() {
        if (confirmedNewPassword == null) {
            confirmedNewPassword = "";
        }
        return confirmedNewPassword;
    }

    public void setConfirmedNewPassword(String confirmedNewPassword) {
        this.confirmedNewPassword = confirmedNewPassword;
    }

    public Boolean hasModule(String moduleName) {
        for (Module mod : getActiveModules()) {
            if (mod.getName().equals(moduleName)) {
                return true;
            }
        }

        return false;
    }

    public Module getActiveModule(String moduleName) {
        for (Module mod : getActiveModules()) {
            if (mod.getName().equals(moduleName)) {
                return mod;
            }
        }

        return null;
    }

    public Boolean can(String privilegeName) {
        for (Privilege priv : getPrivileges()) {
            if (priv.getName().equals(privilegeName)) {
                return true;
            }
        }

        return false;
    }

    public List<Privilege> getPrivileges() {
        if (privileges == null) {
            privileges = new ArrayList<>();
        }

        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public List<Module> getActiveModules() {
        if (activeModules == null) {
            activeModules = new ArrayList<>();
        }

        return activeModules;
    }

    public String getAllActiveModules() {
        String allActiveModules = "";

        for (int i = 0; i < getActiveModules().size(); i++) {
            if (i == 0) {
                allActiveModules = getActiveModules().get(i).getDescription();
            } else {
                allActiveModules = allActiveModules + ", " + getActiveModules().get(i).getDescription();
            }
        }

        return allActiveModules;
    }

    public String getAllPrivileges() {
        String allPrivileges = "";

        for (int i = 0; i < getPrivileges().size(); i++) {
            if (i == 0) {
                allPrivileges = getPrivileges().get(i).getDescription();
            } else {
                allPrivileges = allPrivileges + ", " + getPrivileges().get(i).getDescription();
            }
        }

        return allPrivileges;
    }

    public void setActiveModules(List<Module> activeModules) {
        this.activeModules = activeModules;
    }

    public Boolean isMemberOf(EntityManager em, Department department) {
        if (department != null) {
            if (getEmployee().isMemberOf(department)) {
                return true;
            }
        }

        Business organization = User.getUserOrganizationByDepartment(em, this);
        if ((organization != null) && (department != null)) {
            for (Department memberDepartment : organization.getDepartments()) {
                if (getEmployee().isStaffMemberOf(memberDepartment)
                        && (Objects.equals(department.getId(), memberDepartment.getId()))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Business getUserOrganizationByDepartment(EntityManager em, User user) {

        try {
            Department department = user.getEmployee().getDepartment();
            for (Business business : Business.findAllActive(em)) {
                for (Department dept : business.getDepartments()) {
                    if (Objects.equals(department.getId(), dept.getId())) {
                        return business;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error occurred while getting the organization of a user: " + e);
        }

        return null;
    }

    @Override
    public Boolean getActive() {
        if (active == null) {
            active = true;
        }

        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
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

    public Boolean getIsJobsPreferredJobTableView() {
        return getJobTableViewPreference().equals("Jobs");
    }

    public Boolean getIsCashierPreferredJobTableView() {
        return getJobTableViewPreference().equals("Cashier View");
    }

    public Boolean getIsJobCostingsPreferredJobTableView() {
        return getJobTableViewPreference().equals("Job Costings");
    }

    public static Boolean isUserDepartmentSupervisor(Job job, User user, EntityManager em) {

        Job foundJob = Job.findJobById(em, job.getId());

        if (Department.findAssignedToJob(foundJob, em).getHead().getId().longValue() == user.getEmployee().getId().longValue()) {
            return true;
        } else {
            return (Department.findAssignedToJob(foundJob, em).getActingHead().getId().longValue() == user.getEmployee().getId().longValue())
                    && Department.findAssignedToJob(foundJob, em).getActingHeadActive();
        }
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

    public String getPFThemeName() {
        if (id == null) {
            PFThemeName = "saga";
        }

        return PFThemeName;
    }

    public void setPFThemeName(String PFThemeName) {
        this.PFThemeName = PFThemeName;
    }

    public void logActivity(String activity, EntityManager em) {
        this.setActivity(activity);
        this.save(em);
    }

    public String getActivity() {
        if (activity == null) {
            activity = "";
        }
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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
        if (pollTime == null) {
            pollTime = new Date();
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
            return new Employee();
        }

        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public String getName() {
        if (username == null) {
            username = "";
        }

        return username;
    }

    @Override
    public void setName(String name) {
        username = name;
    }

    public static List<User> findAllByUsername(
            EntityManager em,
            String value,
            int maxResults) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<User> users
                    = em.createQuery("SELECT j FROM User j where UPPER(j.username) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY j.username", User.class)
                            .setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static User findById(EntityManager em, Long Id) {
        return em.find(User.class, Id);
    }

    public static User findByUsername(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<User> users
                    = em.createNamedQuery("findByJobManagerUsername",
                            User.class).
                            setParameter("username", value.toUpperCase()).getResultList();

            if (!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static User findActiveByUsername(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<User> users = em.createQuery("SELECT j FROM User j WHERE (j.active = 1 OR j.active IS NULL) AND j.username = '"
                    + value + "'", User.class).getResultList();

            if (!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static User findActiveByEmployeeId(
            EntityManager em, Long employeeId) {
        try {
            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee employee"
                    + " WHERE j.active = 1 AND employee.id = " + employeeId, User.class).getResultList();

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

    public static List<User> findAllByName(
            EntityManager em,
            String value,
            int maxResults) {
        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee e"
                    + " WHERE UPPER(e.firstName) like '%"
                    + value + "%'" + " OR UPPER(e.lastName) like '%"
                    + value + "%'" + " OR UPPER(j.username) like '%"
                    + value + "%' ORDER BY j.username", User.class)
                    .setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<User> findAllActiveByName(
            EntityManager em,
            String value,
            int maxResults) {
        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee e"
                    + " WHERE (j.active = 1 OR j.active IS NULL) AND (UPPER(e.firstName) like '%"
                    + value + "%'" + " OR UPPER(e.lastName) like '%"
                    + value + "%'" + " OR UPPER(j.username) like '%"
                    + value + "%') ORDER BY j.username", User.class).
                    setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<User> findAllActive(
            EntityManager em,
            int maxResults) {

        try {

            List<User> users = em.createQuery("SELECT j FROM User j WHERE j.active = 1 OR j.active IS NULL ORDER BY j.username", User.class).
                    setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            return null;
        }
    }

    public static List<User> findAll(
            EntityManager em,
            int maxResults) {

        try {

            List<User> users = em.createQuery("SELECT j FROM User j WHERE j.active = 1 ORDER BY j.username", User.class).
                    setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {

        ReturnMessage rm = new ReturnMessage();

        try {

            if (employee != null) {
                employee.save(em);
            }
//
//            if (privilege != null) {
//                privilege.save(em);
//            }

            for (Privilege priv : getPrivileges()) {
                priv.save(em);
            }

            for (Module activeModule : getActiveModules()) {
                activeModule.save(em);
            }

            for (SystemOption setting : getSettings()) {
                setting.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

        } catch (Exception e) {

            rm.setHeader("User Not Saved!");
            rm.setMessage("An error occured while saving this user. Check for duplicate privileges.");
            rm.setSeverity(Message.SEVERITY_ERROR_NAME);
            rm.setSuccess(false);

            System.out.println(e);

        }

        return rm;
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean isUserDepartmentSupervisor(Job job, EntityManager em) {

        Job foundJob = Job.findJobById(em, job.getId());

        if (Department.findAssignedToJob(foundJob, em).getHead().getId().longValue() == this.getEmployee().getId().longValue()) {
            return true;
        } else {
            return (Department.findAssignedToJob(foundJob, em).getActingHead().getId().longValue() == this.getEmployee().getId().longValue())
                    && Department.findAssignedToJob(foundJob, em).getActingHeadActive();
        }
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {

        try {

            if (this.id == null) {
                User existingUser = User.findActiveByUsername(em, this.username);
                if (existingUser != null) {
                    return new ReturnMessage(false, "User exists");
                } else {
                    return save(em);
                }
            } else {
                return save(em);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "User not saved");
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
