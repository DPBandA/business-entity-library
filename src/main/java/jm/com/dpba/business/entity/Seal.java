/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "seal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seal.findAll", query = "SELECT s FROM Seal s")
    , @NamedQuery(name = "Seal.findById", query = "SELECT s FROM Seal s WHERE s.id = :id")
    , @NamedQuery(name = "Seal.findByValid", query = "SELECT s FROM Seal s WHERE s.valid = :valid")
    , @NamedQuery(name = "Seal.findByModel", query = "SELECT s FROM Seal s WHERE s.model = :model")
    , @NamedQuery(name = "Seal.findByDateassigned", query = "SELECT s FROM Seal s WHERE s.dateassigned = :dateassigned")
    , @NamedQuery(name = "Seal.findBySerialnumber", query = "SELECT s FROM Seal s WHERE s.serialnumber = :serialnumber")
    , @NamedQuery(name = "Seal.findByDateissued", query = "SELECT s FROM Seal s WHERE s.dateissued = :dateissued")
    , @NamedQuery(name = "Seal.findByNumber", query = "SELECT s FROM Seal s WHERE s.number = :number")
    , @NamedQuery(name = "Seal.findByType", query = "SELECT s FROM Seal s WHERE s.type = :type")
    , @NamedQuery(name = "Seal.findByUsed", query = "SELECT s FROM Seal s WHERE s.used = :used")
    , @NamedQuery(name = "Seal.findByDateexpired", query = "SELECT s FROM Seal s WHERE s.dateexpired = :dateexpired")
    , @NamedQuery(name = "Seal.findByName", query = "SELECT s FROM Seal s WHERE s.name = :name")
    , @NamedQuery(name = "Seal.findByNote", query = "SELECT s FROM Seal s WHERE s.note = :note")})
public class Seal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALID")
    private Boolean valid;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "DATEASSIGNED")
    @Temporal(TemporalType.DATE)
    private Date dateassigned;
    @Column(name = "SERIALNUMBER")
    private String serialnumber;
    @Column(name = "DATEISSUED")
    @Temporal(TemporalType.DATE)
    private Date dateissued;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "USED")
    private Boolean used;
    @Column(name = "DATEEXPIRED")
    @Temporal(TemporalType.DATE)
    private Date dateexpired;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTE")
    private String note;
    @JoinColumn(name = "ASSIGNEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee assigneeId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Seal() {
    }

    public Seal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateassigned() {
        return dateassigned;
    }

    public void setDateassigned(Date dateassigned) {
        this.dateassigned = dateassigned;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Date getDateissued() {
        return dateissued;
    }

    public void setDateissued(Date dateissued) {
        this.dateissued = dateissued;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Date getDateexpired() {
        return dateexpired;
    }

    public void setDateexpired(Date dateexpired) {
        this.dateexpired = dateexpired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Employee getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Employee assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
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
        if (!(object instanceof Seal)) {
            return false;
        }
        Seal other = (Seal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Seal[ id=" + id + " ]";
    }
    
}
