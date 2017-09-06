/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "documentinspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentinspection.findAll", query = "SELECT d FROM Documentinspection d"),
    @NamedQuery(name = "Documentinspection.findById", query = "SELECT d FROM Documentinspection d WHERE d.id = :id"),
    @NamedQuery(name = "Documentinspection.findByActiontaken", query = "SELECT d FROM Documentinspection d WHERE d.actiontaken = :actiontaken"),
    @NamedQuery(name = "Documentinspection.findByComments", query = "SELECT d FROM Documentinspection d WHERE d.comments = :comments"),
    @NamedQuery(name = "Documentinspection.findByDateofinspection", query = "SELECT d FROM Documentinspection d WHERE d.dateofinspection = :dateofinspection"),
    @NamedQuery(name = "Documentinspection.findByName", query = "SELECT d FROM Documentinspection d WHERE d.name = :name"),
    @NamedQuery(name = "Documentinspection.findByPortofentry", query = "SELECT d FROM Documentinspection d WHERE d.portofentry = :portofentry"),
    @NamedQuery(name = "Documentinspection.findByType", query = "SELECT d FROM Documentinspection d WHERE d.type = :type")})
public class Documentinspection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "ACTIONTAKEN")
    private String actiontaken;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "DATEOFINSPECTION")
    @Temporal(TemporalType.DATE)
    private Date dateofinspection;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "PORTOFENTRY")
    private String portofentry;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "CONSIGNEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client consigneeId;
    @JoinColumn(name = "INSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee inspectorId;

    public Documentinspection() {
    }

    public Documentinspection(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActiontaken() {
        return actiontaken;
    }

    public void setActiontaken(String actiontaken) {
        this.actiontaken = actiontaken;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateofinspection() {
        return dateofinspection;
    }

    public void setDateofinspection(Date dateofinspection) {
        this.dateofinspection = dateofinspection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortofentry() {
        return portofentry;
    }

    public void setPortofentry(String portofentry) {
        this.portofentry = portofentry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Client consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Employee getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Employee inspectorId) {
        this.inspectorId = inspectorId;
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
        if (!(object instanceof Documentinspection)) {
            return false;
        }
        Documentinspection other = (Documentinspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Documentinspection[ id=" + id + " ]";
    }
    
}
