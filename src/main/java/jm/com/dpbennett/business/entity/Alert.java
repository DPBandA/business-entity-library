/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@XmlRootElement
public class Alert implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId = 0L;
    private String name = "";
    private String type = "";
    private String reference = "";
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dueTime = null;
    private String periodType = "";
    private Long recurrencePeriod = null;
    @Column(length = 1024)
    private String comment = "";
    @Column(length = 1024)
    private String status = "";
    @Column(length = 1024)
    private String actionToTake = "";
    @Column(length = 1024)
    private String actionTaken = "";
    private Boolean active = false;
    private String subject = "";
    @Column(length = 1024)
    private String message = "";
    private static ArrayList<String> statuses = null;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recipient = null;

    public Alert() {
        active = true;
        statuses = new ArrayList<String>();
    }

    public Alert(Long ownerId,
            Date dueTime,
            String status) {

        active = true;
        statuses = new ArrayList<String>();
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

    public Employee getRecipient() {
        return recipient;
    }

    public void setRecipient(Employee recipient) {
        this.recipient = recipient;
    }

    public static ArrayList<String> getStatuses() {
        if (statuses.isEmpty()) {
            statuses.add("Job saved");
            statuses.add("Job entered");
            statuses.add("Job to be completed");
            statuses.add("Job costing due");
            statuses.add("New job email sent");
            statuses.add("Job update email sent");
            statuses.add("Job is being tracked");
        }

        Collections.sort(statuses);

        return statuses;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (!(object instanceof Alert)) {
            return false;
        }
        Alert other = (Alert) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    public String getType() {
        return type;
    }

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

    public static Alert findAlertById(EntityManager em, Long Id) {

        try {
            return em.find(Alert.class, Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Alert findFirstAlertByOwnerId(EntityManager em, Long ownerId) {

        try {
            List<Alert> alerts = em.createQuery("SELECT a FROM Alert a "
                    + "WHERE a.ownerId"
                    + "= " + ownerId + " ORDER BY a.id", Alert.class).getResultList();

            if (!alerts.isEmpty()) {
                return alerts.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Alert> findAllActiveAlerts(EntityManager em) {

        try {
            List<Alert> alerts = em.createQuery("SELECT a FROM Alert a "
                    + "WHERE a.active = 1 ORDER BY a.id", Alert.class).getResultList();

            return alerts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Alert>();
        }
    }

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
