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
package jm.com.dpbennett.business.entity.sm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "notification")
public class Notification implements BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String name;
    private String type;
    private String reference;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dueTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date issueTime;
    private String periodType;
    private Long recurrencePeriod;
    @Column(length = 1024)
    private String comment;
    @Column(length = 1024)
    private String status;
    @Column(length = 1024)
    private String actionToTake;
    @Column(length = 1024)
    private String actionTaken;
    private Boolean active;
    private String subject;
    @Column(length = 1024)
    private String message;
    @Transient
    private Boolean isDirty;

    public Notification() {
        this.message = "";
        this.subject = "";
        this.active = false;
        this.actionTaken = "";
        this.actionToTake = "";
        this.status = "";
        this.comment = "";
        this.recurrencePeriod = null;
        this.periodType = "";
        this.dueTime = null;
        this.reference = "";
        this.type = "";
        this.name = "";
        this.ownerId = 0L;
        active = true;
    }

    public Notification(String name) {
        this.message = "";
        this.subject = "";
        this.active = false;
        this.actionTaken = "";
        this.actionToTake = "";
        this.status = "";
        this.comment = "";
        this.recurrencePeriod = null;
        this.periodType = "";
        this.dueTime = null;
        this.reference = "";
        this.type = "";
        this.name = name;
        this.ownerId = 0L;
        active = true;
    }

    public Notification(Long ownerId,
            Date dueTime,
            String status) {
        this.message = "";
        this.subject = "";
        this.active = false;
        this.actionTaken = "";
        this.actionToTake = "";
        this.status = "";
        this.comment = "";
        this.recurrencePeriod = null;
        this.periodType = "";
        this.reference = "";
        this.type = "";
        this.name = "";
        this.ownerId = 0L;
        active = true;
        this.ownerId = ownerId;
        this.dueTime = dueTime;
        this.status = status;
    }

    public String getStyle() {
        if (active) {
            return "font-weight: bold";
        }

        return "font-weight: normal";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }

    public String getActionToTake() {
        return actionToTake;
    }

    public void setActionToTake(String actionToTake) {
        this.actionToTake = actionToTake;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public Long getRecurrencePeriod() {
        return recurrencePeriod;
    }

    public void setRecurrencePeriod(Long recurrencePeriod) {
        this.recurrencePeriod = recurrencePeriod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Notification findNotificationById(EntityManager em, Long Id) {

        try {
            return em.find(Notification.class, Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Notification> findNotificationsByName(
            EntityManager em,
            String value,
            int maxResults) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Notification> notifications
                    = em.createQuery("SELECT n FROM Notification n WHERE UPPER(n.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY n.issueTime DESC",
                            Notification.class).setMaxResults(maxResults).getResultList();

            return notifications;

        } catch (Exception e) {

            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Notification findFirstNotificationByOwnerId(
            EntityManager em, Long ownerId) {

        try {
            List<Notification> notifications = em.createQuery("SELECT n FROM Notification n "
                    + "WHERE n.ownerId"
                    + "= " + ownerId + " ORDER BY n.issueTime DESC", Notification.class).getResultList();

            if (!notifications.isEmpty()) {
                return notifications.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Notification> findAllActiveNotifications(EntityManager em) {

        try {
            List<Notification> alerts = em.createQuery("SELECT n FROM Notification n "
                    + "WHERE n.active = 1 ORDER BY n.issueTime DESC",
                    // tk max results to be made system option
                    Notification.class).setMaxResults(100).getResultList();

            return alerts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static String findLastActiveSystemNotificationMessage(EntityManager em) {

        try {
            List<Notification> alerts = em.createQuery("SELECT n FROM Notification n "
                    + "WHERE n.active = 1 AND n.type = 'System' ORDER BY n.id DESC",
                    Notification.class).setMaxResults(1).getResultList();

            if (!alerts.isEmpty()) {
                return alerts.get(0).message;
            }
            
            return "";
            
        } catch (Exception e) {

            System.out.println(e);

            return "";
        }
    }

    public static List<Notification> findActiveNotificationsByOwnerId(
            EntityManager em, Long ownerId) {

        try {
            List<Notification> alerts = em.createQuery("SELECT n FROM Notification n "
                    + "WHERE n.active = 1 AND "
                    + "n.ownerId = " + ownerId
                    + " ORDER BY n.issueTime DESC", Notification.class).getResultList();

            return alerts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Notification> findNotificationsByOwnerId(
            EntityManager em, Long ownerId) {

        int maxResults = SystemOption.getInteger(em, "maxNotificationsSearchResults");

        try {

            List<Notification> alerts = em.createQuery("SELECT n FROM Notification n"
                    + " WHERE n.ownerId = " + ownerId
                    + " ORDER BY n.issueTime DESC", Notification.class)
                    .setMaxResults(maxResults).getResultList();

            return alerts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Notification findNotificationByName(
            EntityManager em, String value, Boolean ignoreCase) {

        List<Notification> notifications;

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            if (ignoreCase) {
                notifications = em.createQuery("SELECT n FROM Notification n"
                        + " WHERE UPPER(n.name)"
                        + " = '" + value.toUpperCase() + "'", Notification.class).getResultList();
            } else {
                notifications = em.createQuery("SELECT n FROM Notification n"
                        + " WHERE n.name "
                        + "= '" + value + "'", Notification.class).getResultList();
            }

            if (!notifications.isEmpty()) {
                return notifications.get(0);
            }

            return null;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Notification findNotificationByNameAndOwnerId(
            EntityManager em,
            String value,
            Long ownerId,
            Boolean ignoreCase) {

        List<Notification> notifications;

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            if (ignoreCase) {
                notifications = em.createQuery("SELECT n FROM Notification n"
                        + " WHERE UPPER(n.name)"
                        + " = '" + value.toUpperCase() + "'"
                        + " AND n.ownerId = " + ownerId, Notification.class).getResultList();
            } else {
                notifications = em.createQuery("SELECT n FROM Notification n"
                        + " WHERE n.name "
                        + "= '" + value + "'"
                        + " AND n.ownerId = " + ownerId, Notification.class).getResultList();
            }

            if (!notifications.isEmpty()) {
                return notifications.get(0);
            }

            return null;

        } catch (Exception e) {
            System.out.println(e);
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
            return new ReturnMessage(false,
                    "Notification Save Error Occurred!",
                    "An error occurred while saving Notification"
                    + "\n" + e,
                    Message.SEVERITY_ERROR_NAME);
        }
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        try {
            em.getTransaction().begin();
            Notification n = em.find(Notification.class, this.id);
            em.remove(n);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println(e);

            return new ReturnMessage(false, "Notification NOT deleted!");
        }

        return new ReturnMessage();
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
