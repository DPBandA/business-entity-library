/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "alert")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alert.findAll", query = "SELECT a FROM Alert a")
    , @NamedQuery(name = "Alert.findById", query = "SELECT a FROM Alert a WHERE a.id = :id")
    , @NamedQuery(name = "Alert.findByComment", query = "SELECT a FROM Alert a WHERE a.comment = :comment")
    , @NamedQuery(name = "Alert.findByDuetime", query = "SELECT a FROM Alert a WHERE a.duetime = :duetime")
    , @NamedQuery(name = "Alert.findByName", query = "SELECT a FROM Alert a WHERE a.name = :name")
    , @NamedQuery(name = "Alert.findByPeriodtype", query = "SELECT a FROM Alert a WHERE a.periodtype = :periodtype")
    , @NamedQuery(name = "Alert.findByRecurrenceperiod", query = "SELECT a FROM Alert a WHERE a.recurrenceperiod = :recurrenceperiod")
    , @NamedQuery(name = "Alert.findByReference", query = "SELECT a FROM Alert a WHERE a.reference = :reference")
    , @NamedQuery(name = "Alert.findByStatus", query = "SELECT a FROM Alert a WHERE a.status = :status")
    , @NamedQuery(name = "Alert.findByType", query = "SELECT a FROM Alert a WHERE a.type = :type")
    , @NamedQuery(name = "Alert.findByActiontotake", query = "SELECT a FROM Alert a WHERE a.actiontotake = :actiontotake")
    , @NamedQuery(name = "Alert.findByActiontaken", query = "SELECT a FROM Alert a WHERE a.actiontaken = :actiontaken")
    , @NamedQuery(name = "Alert.findByActive", query = "SELECT a FROM Alert a WHERE a.active = :active")
    , @NamedQuery(name = "Alert.findByMessage", query = "SELECT a FROM Alert a WHERE a.message = :message")
    , @NamedQuery(name = "Alert.findByOwnerid", query = "SELECT a FROM Alert a WHERE a.ownerid = :ownerid")
    , @NamedQuery(name = "Alert.findBySubject", query = "SELECT a FROM Alert a WHERE a.subject = :subject")})
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COMMENT")
    private String comment;
    @Basic(optional = false)
    @Column(name = "DUETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duetime;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PERIODTYPE")
    private String periodtype;
    @Column(name = "RECURRENCEPERIOD")
    private BigInteger recurrenceperiod;
    @Column(name = "REFERENCE")
    private String reference;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ACTIONTOTAKE")
    private String actiontotake;
    @Column(name = "ACTIONTAKEN")
    private String actiontaken;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "OWNERID")
    private BigInteger ownerid;
    @Column(name = "SUBJECT")
    private String subject;
    @JoinColumn(name = "RECIPIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee recipientId;

    public Alert() {
    }

    public Alert(Long id) {
        this.id = id;
    }

    public Alert(Long id, Date duetime) {
        this.id = id;
        this.duetime = duetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDuetime() {
        return duetime;
    }

    public void setDuetime(Date duetime) {
        this.duetime = duetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodtype() {
        return periodtype;
    }

    public void setPeriodtype(String periodtype) {
        this.periodtype = periodtype;
    }

    public BigInteger getRecurrenceperiod() {
        return recurrenceperiod;
    }

    public void setRecurrenceperiod(BigInteger recurrenceperiod) {
        this.recurrenceperiod = recurrenceperiod;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActiontotake() {
        return actiontotake;
    }

    public void setActiontotake(String actiontotake) {
        this.actiontotake = actiontotake;
    }

    public String getActiontaken() {
        return actiontaken;
    }

    public void setActiontaken(String actiontaken) {
        this.actiontaken = actiontaken;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigInteger getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(BigInteger ownerid) {
        this.ownerid = ownerid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Employee getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Employee recipientId) {
        this.recipientId = recipientId;
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
        return "jm.com.dpba.business.entity.Alert[ id=" + id + " ]";
    }
    
}
