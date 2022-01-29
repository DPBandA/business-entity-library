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
package jm.com.dpbennett.business.entity.hrm;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.BusinessEntity;
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
@XmlRootElement
public class User implements Serializable, BusinessEntity {

    public static final int CANENTERJOB = 0;
    public static final int CANEDITJOB = 1;
    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String userInterfaceThemeName;
    private String jobTableViewPreference;
    private Boolean authenticate;
    private String activity;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date pollTime;
    @Column(length = 255)
    private String password;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee employee;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Privilege privilege;
    @OneToOne(cascade = CascadeType.ALL)
    private Modules modules;
    @Transient
    private Boolean isDirty;
    private Boolean active;

    public User() {
        privilege = new Privilege();
        modules = new Modules();
        username = "";
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

    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

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

    public Modules getModule() {
        if (modules == null) {
            modules = new Modules();
        }
        return modules;
    }

    public void setModule(Modules modules) {
        this.modules = modules;
    }

    public Boolean getIsJobsPreferredJobTableView() {
        return getModule().getJobManagementAndTrackingModule() && getJobTableViewPreference().equals("Jobs");
    }

    public Boolean getIsCashierPreferredJobTableView() {
        return getModule().getJobManagementAndTrackingModule() && getJobTableViewPreference().equals("Cashier View");
    }

    public Boolean getIsJobCostingsPreferredJobTableView() {
        return getModule().getJobManagementAndTrackingModule() && getJobTableViewPreference().equals("Job Costings");
    }

    public static Boolean isUserDepartmentSupervisor(Job job, User user, EntityManager em) {

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

    /**
     * Logs the poll time and activity of a user. The user is saved after the
     * activity is logged.
     *
     * @param activity
     * @param em
     */
    public void logActivity(String activity, EntityManager em) {
        this.setPollTime(new Date());
        this.setActivity(activity);
        this.save(em);
    }

    /**
     * Gets the activity and poll time of a user
     *
     * @return
     */
    public String getLoggedActivity() {

        if (pollTime != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

            return getActivity() + " (" + formatter.format(pollTime) + ")";
        } else {
            return getActivity();
        }
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

//    @XmlTransient
//    public Department getDepartment() {
//        if (department == null) {
//            department = getEmployee().getDepartment();
//        }
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public void setName(String name) {
        username = name;
    }

    public static List<User> findJobManagerUsersByUsername(EntityManager em, String username) {

        String newUsername = username.replaceAll("'", "''");

        try {
            List<User> users
                    = em.createQuery("SELECT j FROM User j where UPPER(j.username) like '%"
                            + newUsername.toUpperCase().trim() + "%' ORDER BY j.username", User.class).getResultList();
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

        String newUsername = username.replaceAll("'", "''");
        try {
            List<User> users
                    = em.createNamedQuery("findByJobManagerUsername",
                            User.class).
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

    public static User findActiveJobManagerUserByUsername(EntityManager em, String username) {

        String newUsername = username.replaceAll("'", "''");
        try {

            List<User> users = em.createQuery("SELECT j FROM User j WHERE (j.active = 1 OR j.active IS NULL) AND j.username = '"
                    + newUsername + "'", User.class).getResultList();

            if (users.size() > 0) {
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

    public static List<User> findJobManagerUsersByName(EntityManager em, String name) {
        try {
            String newName = name.toUpperCase().trim().replaceAll("'", "''");

            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee e"
                    + " WHERE UPPER(e.firstName) like '%"
                    + newName + "%'" + " OR UPPER(e.lastName) like '%"
                    + newName + "%'" + " OR UPPER(j.username) like '%"
                    + newName + "%' ORDER BY j.username", User.class).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<User> findActiveJobManagerUsersByName(EntityManager em, String name) {
        try {
            String newName = name.toUpperCase().trim().replaceAll("'", "''");

            List<User> users = em.createQuery("SELECT j FROM User j"
                    + " JOIN j.employee e"
                    + " WHERE (j.active = 1 OR j.active IS NULL) AND (UPPER(e.firstName) like '%"
                    + newName + "%'" + " OR UPPER(e.lastName) like '%"
                    + newName + "%'" + " OR UPPER(j.username) like '%"
                    + newName + "%') ORDER BY j.username", User.class).getResultList();

            return users;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<User> findAllActiveJobManagerUsers(EntityManager em) {

        try {

            List<User> users = em.createQuery("SELECT j FROM User j WHERE j.active = 1 OR j.active IS NULL ORDER BY j.username", User.class).getResultList();

            return users;

        } catch (Exception e) {
            return null;
        }
    }

    public static List<User> findAllJobManagerUsers(EntityManager em) {

        try {

            List<User> users = em.createQuery("SELECT j FROM User j WHERE j.active = 1 ORDER BY j.username", User.class).getResultList();

            return users;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {

        try {

            if (getPrivilege().getId() == null || getPrivilege().getIsDirty()) {
                getPrivilege().save(em);
                getPrivilege().setIsDirty(false);
            }

            if (getModule().getId() == null || getModule().getIsDirty()) {
                getModule().save(em);
                getModule().setIsDirty(false);
            }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    // tk del. Move to User and make static method
    public Boolean isUserDepartmentSupervisor(Job job, EntityManager em) {

        Job foundJob = Job.findJobById(em, job.getId());

        if (Department.findDepartmentAssignedToJob(foundJob, em).getHead().getId().longValue() == this.getEmployee().getId().longValue()) {
            return true;
        } else {
            return (Department.findDepartmentAssignedToJob(foundJob, em).getActingHead().getId().longValue() == this.getEmployee().getId().longValue())
                    && Department.findDepartmentAssignedToJob(foundJob, em).getActingHeadActive();
        }
    }
}
