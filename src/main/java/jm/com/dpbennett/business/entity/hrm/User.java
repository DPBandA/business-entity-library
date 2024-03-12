/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.hrm;

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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.Modules;
import jm.com.dpbennett.business.entity.auth.Privilege;
import jm.com.dpbennett.business.entity.jmts.Job;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
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
public class User implements BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Modules modules;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Privilege> privileges;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Modules> activeModules;
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
        privilege = new Privilege();
        employee = new Employee();
        modules = new Modules();
        username = "";
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

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
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
        for (Modules mod : getActiveModules()) {
            if (mod.getName().equals(moduleName)) {
                return true;
            }
        }

        return false;
    }

    public Modules getActiveModule(String moduleName) {
        for (Modules mod : getActiveModules()) {
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

    public List<Modules> getActiveModules() {
        if (activeModules == null) {
            activeModules = new ArrayList<>();
        }
        return activeModules;
    }

    public void setActiveModules(List<Modules> activeModules) {
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

    /**
     * Returns the business/organization to which the user's department belong.
     * The user's department is obtained through it's employee reference.
     *
     * @param em
     * @param user
     * @return
     */
    public static Business getUserOrganizationByDepartment(EntityManager em, User user) {

        try {
            Department department = user.getEmployee().getDepartment();
            for (Business business : Business.findAllBusinesses(em)) {
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

    public Modules getActiveModule() {
        if (modules == null) {
            modules = new Modules();
        }
        return modules;
    }

    public Boolean getIsJobsPreferredJobTableView() {
        return /*getModules().getJobManagementAndTrackingModule() &&*/ getJobTableViewPreference().equals("Jobs");
    }

    public Boolean getIsCashierPreferredJobTableView() {
        return /*getModules().getJobManagementAndTrackingModule() &&*/ getJobTableViewPreference().equals("Cashier View");
    }

    public Boolean getIsJobCostingsPreferredJobTableView() {
        return /*getModules().getJobManagementAndTrackingModule() &&*/ getJobTableViewPreference().equals("Job Costings");
    }

    public static Boolean isUserDepartmentSupervisor(Job job, User user, EntityManager em) {

        Job foundJob = Job.findJobById(em, job.getId());

        if (Department.findDepartmentAssignedToJob(foundJob, em).getHead().getId().longValue() == user.getEmployee().getId().longValue()) {
            return true;
        } else {
            return (Department.findDepartmentAssignedToJob(foundJob, em).getActingHead().getId().longValue() == user.getEmployee().getId().longValue())
                    && Department.findDepartmentAssignedToJob(foundJob, em).getActingHeadActive();
        }
    }

    public Privilege getActivePrivilege() {
        if (privilege == null) {
            privilege = new Privilege(username);
        }

        return privilege;
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
        if (PFThemeName == null) {
            PFThemeName = "saga";
        }

        return PFThemeName;
    }

    public void setPFThemeName(String PFThemeName) {
        this.PFThemeName = PFThemeName;
    }

    /**
     * Logs the poll time and activity of a user. The user is saved after the
     * activity is logged.
     *
     * @param activity
     * @param em
     */
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

    public static List<User> findJobManagerUsersByUsername(
            EntityManager em,
            String username,
            int maxResults) {

        try {
            List<User> users
                    = em.createQuery("SELECT j FROM User j where UPPER(j.username) like '%"
                            + username.toUpperCase().trim() + "%' ORDER BY j.username", User.class)
                            .setMaxResults(maxResults).getResultList();
            return users;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static User findJobManagerUserById(EntityManager em, Long Id) {
        return em.find(User.class, Id);
    }

    public static User findJobManagerUserByUsername(EntityManager em, String username) {

        try {
            List<User> users
                    = em.createNamedQuery("findByJobManagerUsername",
                            User.class).
                            setParameter("username", username.toUpperCase()).getResultList();

            if (!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static User findActiveJobManagerUserByUsername(EntityManager em, String username) {

        try {

            List<User> users = em.createQuery("SELECT j FROM User j WHERE (j.active = 1 OR j.active IS NULL) AND j.username = '"
                    + username + "'", User.class).getResultList();

            if (!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static User findActiveJobManagerUserByEmployeeId(EntityManager em, Long employeeId) {
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

    public static List<User> findJobManagerUsersByName(
            EntityManager em,
            String name,
            int maxResults) {
        try {

            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee e"
                    + " WHERE UPPER(e.firstName) like '%"
                    + name + "%'" + " OR UPPER(e.lastName) like '%"
                    + name + "%'" + " OR UPPER(j.username) like '%"
                    + name + "%' ORDER BY j.username", User.class)
                    .setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<User> findActiveJobManagerUsersByName(
            EntityManager em,
            String name,
            int maxResults) {
        try {

            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee e"
                    + " WHERE (j.active = 1 OR j.active IS NULL) AND (UPPER(e.firstName) like '%"
                    + name + "%'" + " OR UPPER(e.lastName) like '%"
                    + name + "%'" + " OR UPPER(j.username) like '%"
                    + name + "%') ORDER BY j.username", User.class).
                    setMaxResults(maxResults).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<User> findAllActiveJobManagerUsers(
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

    public static List<User> findAllJobManagerUsers(
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

        try {

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "User not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Determine if the current user is the department's supervisor. This is
     * done by determining if the user is the head/active acting head of the
     * department to which the job was assigned.
     *
     * @param job
     * @param em
     * @return
     */
    public Boolean isUserDepartmentSupervisor(Job job, EntityManager em) {

        Job foundJob = Job.findJobById(em, job.getId());

        if (Department.findDepartmentAssignedToJob(foundJob, em).getHead().getId().longValue() == this.getEmployee().getId().longValue()) {
            return true;
        } else {
            return (Department.findDepartmentAssignedToJob(foundJob, em).getActingHead().getId().longValue() == this.getEmployee().getId().longValue())
                    && Department.findDepartmentAssignedToJob(foundJob, em).getActingHeadActive();
        }
    }

    public ReturnMessage saveUnique(EntityManager em) {

        try {

            if (this.id == null) {
                User existingUser = User.findJobManagerUserByUsername(em, this.username);
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
