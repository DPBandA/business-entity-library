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
@Table(name = "generatedform")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Generatedform.findAll", query = "SELECT g FROM Generatedform g")
    , @NamedQuery(name = "Generatedform.findById", query = "SELECT g FROM Generatedform g WHERE g.id = :id")
    , @NamedQuery(name = "Generatedform.findByDatabasedriverclass", query = "SELECT g FROM Generatedform g WHERE g.databasedriverclass = :databasedriverclass")
    , @NamedQuery(name = "Generatedform.findByDatabasepassword", query = "SELECT g FROM Generatedform g WHERE g.databasepassword = :databasepassword")
    , @NamedQuery(name = "Generatedform.findByDatabaseurl", query = "SELECT g FROM Generatedform g WHERE g.databaseurl = :databaseurl")
    , @NamedQuery(name = "Generatedform.findByDatabaseusername", query = "SELECT g FROM Generatedform g WHERE g.databaseusername = :databaseusername")
    , @NamedQuery(name = "Generatedform.findByEnddate", query = "SELECT g FROM Generatedform g WHERE g.enddate = :enddate")
    , @NamedQuery(name = "Generatedform.findByName", query = "SELECT g FROM Generatedform g WHERE g.name = :name")
    , @NamedQuery(name = "Generatedform.findByReportfile", query = "SELECT g FROM Generatedform g WHERE g.reportfile = :reportfile")
    , @NamedQuery(name = "Generatedform.findByReportfilemimetype", query = "SELECT g FROM Generatedform g WHERE g.reportfilemimetype = :reportfilemimetype")
    , @NamedQuery(name = "Generatedform.findByStartdate", query = "SELECT g FROM Generatedform g WHERE g.startdate = :startdate")})
public class Generatedform implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATABASEDRIVERCLASS")
    private String databasedriverclass;
    @Column(name = "DATABASEPASSWORD")
    private String databasepassword;
    @Column(name = "DATABASEURL")
    private String databaseurl;
    @Column(name = "DATABASEUSERNAME")
    private String databaseusername;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Column(name = "NAME")
    private String name;
    @Column(name = "REPORTFILE")
    private String reportfile;
    @Column(name = "REPORTFILEMIMETYPE")
    private String reportfilemimetype;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;

    public Generatedform() {
    }

    public Generatedform(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatabasedriverclass() {
        return databasedriverclass;
    }

    public void setDatabasedriverclass(String databasedriverclass) {
        this.databasedriverclass = databasedriverclass;
    }

    public String getDatabasepassword() {
        return databasepassword;
    }

    public void setDatabasepassword(String databasepassword) {
        this.databasepassword = databasepassword;
    }

    public String getDatabaseurl() {
        return databaseurl;
    }

    public void setDatabaseurl(String databaseurl) {
        this.databaseurl = databaseurl;
    }

    public String getDatabaseusername() {
        return databaseusername;
    }

    public void setDatabaseusername(String databaseusername) {
        this.databaseusername = databaseusername;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportfile() {
        return reportfile;
    }

    public void setReportfile(String reportfile) {
        this.reportfile = reportfile;
    }

    public String getReportfilemimetype() {
        return reportfilemimetype;
    }

    public void setReportfilemimetype(String reportfilemimetype) {
        this.reportfilemimetype = reportfilemimetype;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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
        if (!(object instanceof Generatedform)) {
            return false;
        }
        Generatedform other = (Generatedform) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Generatedform[ id=" + id + " ]";
    }
    
}
