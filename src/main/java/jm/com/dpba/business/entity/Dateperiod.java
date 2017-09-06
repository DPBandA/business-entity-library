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
@Table(name = "dateperiod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dateperiod.findAll", query = "SELECT d FROM Dateperiod d")
    , @NamedQuery(name = "Dateperiod.findById", query = "SELECT d FROM Dateperiod d WHERE d.id = :id")
    , @NamedQuery(name = "Dateperiod.findByEnddatedisabled", query = "SELECT d FROM Dateperiod d WHERE d.enddatedisabled = :enddatedisabled")
    , @NamedQuery(name = "Dateperiod.findByStartdate", query = "SELECT d FROM Dateperiod d WHERE d.startdate = :startdate")
    , @NamedQuery(name = "Dateperiod.findByName", query = "SELECT d FROM Dateperiod d WHERE d.name = :name")
    , @NamedQuery(name = "Dateperiod.findByStartdatedisabled", query = "SELECT d FROM Dateperiod d WHERE d.startdatedisabled = :startdatedisabled")
    , @NamedQuery(name = "Dateperiod.findByEnddate", query = "SELECT d FROM Dateperiod d WHERE d.enddate = :enddate")
    , @NamedQuery(name = "Dateperiod.findByType", query = "SELECT d FROM Dateperiod d WHERE d.type = :type")})
public class Dateperiod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ENDDATEDISABLED")
    private Boolean enddatedisabled;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STARTDATEDISABLED")
    private Boolean startdatedisabled;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Column(name = "TYPE")
    private String type;

    public Dateperiod() {
    }

    public Dateperiod(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnddatedisabled() {
        return enddatedisabled;
    }

    public void setEnddatedisabled(Boolean enddatedisabled) {
        this.enddatedisabled = enddatedisabled;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStartdatedisabled() {
        return startdatedisabled;
    }

    public void setStartdatedisabled(Boolean startdatedisabled) {
        this.startdatedisabled = startdatedisabled;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Dateperiod)) {
            return false;
        }
        Dateperiod other = (Dateperiod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Dateperiod[ id=" + id + " ]";
    }
    
}
