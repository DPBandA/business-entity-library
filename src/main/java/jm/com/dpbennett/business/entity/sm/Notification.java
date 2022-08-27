/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

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

import java.io.Serializable;
import java.sql.Time;
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
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "notification")
public class Notification implements NotificationInterface, Serializable {

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

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
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

    @Override
    public String getActionToTake() {
        return actionToTake;
    }

    @Override
    public void setActionToTake(String actionToTake) {
        this.actionToTake = actionToTake;
    }

    @Override
    public String getActionTaken() {
        return actionTaken;
    }

    @Override
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

    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public Date getDueTime() {
        return dueTime;
    }

    @Override
    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    @Override
    public String getPeriodType() {
        return periodType;
    }

    @Override
    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    @Override
    public Long getRecurrencePeriod() {
        return recurrencePeriod;
    }

    @Override
    public void setRecurrencePeriod(Long recurrencePeriod) {
        this.recurrencePeriod = recurrencePeriod;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
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

    public static List<Notification> findNotificationsByName(EntityManager em, String name) {

        try {
            List<Notification> notifications
                    = em.createQuery("SELECT n FROM Notification n WHERE UPPER(n.name) LIKE '%"
                            + name.toUpperCase().trim() + "%' ORDER BY n.name", Notification.class).getResultList();

            return notifications;

        } catch (Exception e) {

            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Notification findFirstNotificationByOwnerId(EntityManager em, Long ownerId) {

        try {
            List<Notification> notifications = em.createQuery("SELECT n FROM Notification n "
                    + "WHERE n.ownerId"
                    + "= " + ownerId + " ORDER BY n.id", Notification.class).getResultList();

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
                    + "WHERE n.active = 1 ORDER BY n.id", Notification.class).getResultList();

            return alerts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Notification> findActiveNotificationsByOwnerId(EntityManager em, Long ownerId) {

        try {
            List<Notification> alerts = em.createQuery("SELECT n FROM Notification n "
                    + "WHERE n.active = 1 AND "
                    + "n.ownerId = " + ownerId
                    + " ORDER BY n.id", Notification.class).getResultList();

            return alerts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static Notification findActiveNotificationByName(EntityManager em, String value, Boolean ignoreCase) {

        List<Notification> notifications;

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

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
}
