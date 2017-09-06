/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findById", query = "SELECT r FROM Report r WHERE r.id = :id"),
    @NamedQuery(name = "Report.findByStartdate", query = "SELECT r FROM Report r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Report.findByReportfile", query = "SELECT r FROM Report r WHERE r.reportfile = :reportfile"),
    @NamedQuery(name = "Report.findByDatabaseurl", query = "SELECT r FROM Report r WHERE r.databaseurl = :databaseurl"),
    @NamedQuery(name = "Report.findBySqltext", query = "SELECT r FROM Report r WHERE r.sqltext = :sqltext"),
    @NamedQuery(name = "Report.findByDatabasepassword", query = "SELECT r FROM Report r WHERE r.databasepassword = :databasepassword"),
    @NamedQuery(name = "Report.findByEnddate", query = "SELECT r FROM Report r WHERE r.enddate = :enddate"),
    @NamedQuery(name = "Report.findByDatabasedriverclass", query = "SELECT r FROM Report r WHERE r.databasedriverclass = :databasedriverclass"),
    @NamedQuery(name = "Report.findByReportfiletemplate", query = "SELECT r FROM Report r WHERE r.reportfiletemplate = :reportfiletemplate"),
    @NamedQuery(name = "Report.findByCategory", query = "SELECT r FROM Report r WHERE r.category = :category"),
    @NamedQuery(name = "Report.findByName", query = "SELECT r FROM Report r WHERE r.name = :name"),
    @NamedQuery(name = "Report.findByReportfilemimetype", query = "SELECT r FROM Report r WHERE r.reportfilemimetype = :reportfilemimetype"),
    @NamedQuery(name = "Report.findByDateformat", query = "SELECT r FROM Report r WHERE r.dateformat = :dateformat"),
    @NamedQuery(name = "Report.findByDatabaseusername", query = "SELECT r FROM Report r WHERE r.databaseusername = :databaseusername")})
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Size(max = 1024)
    @Column(name = "REPORTFILE")
    private String reportfile;
    @Size(max = 1024)
    @Column(name = "DATABASEURL")
    private String databaseurl;
    @Size(max = 1024)
    @Column(name = "SQLTEXT")
    private String sqltext;
    @Size(max = 255)
    @Column(name = "DATABASEPASSWORD")
    private String databasepassword;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Size(max = 1024)
    @Column(name = "DATABASEDRIVERCLASS")
    private String databasedriverclass;
    @Size(max = 1024)
    @Column(name = "REPORTFILETEMPLATE")
    private String reportfiletemplate;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "REPORTFILEMIMETYPE")
    private String reportfilemimetype;
    @Size(max = 255)
    @Column(name = "DATEFORMAT")
    private String dateformat;
    @Size(max = 255)
    @Column(name = "DATABASEUSERNAME")
    private String databaseusername;
    @JoinTable(name = "report_reporttablecolumn", joinColumns = {
        @JoinColumn(name = "Report_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "reportColumns_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Reporttablecolumn> reporttablecolumnList;

    public Report() {
    }

    public Report(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getReportfile() {
        return reportfile;
    }

    public void setReportfile(String reportfile) {
        this.reportfile = reportfile;
    }

    public String getDatabaseurl() {
        return databaseurl;
    }

    public void setDatabaseurl(String databaseurl) {
        this.databaseurl = databaseurl;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }

    public String getDatabasepassword() {
        return databasepassword;
    }

    public void setDatabasepassword(String databasepassword) {
        this.databasepassword = databasepassword;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDatabasedriverclass() {
        return databasedriverclass;
    }

    public void setDatabasedriverclass(String databasedriverclass) {
        this.databasedriverclass = databasedriverclass;
    }

    public String getReportfiletemplate() {
        return reportfiletemplate;
    }

    public void setReportfiletemplate(String reportfiletemplate) {
        this.reportfiletemplate = reportfiletemplate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportfilemimetype() {
        return reportfilemimetype;
    }

    public void setReportfilemimetype(String reportfilemimetype) {
        this.reportfilemimetype = reportfilemimetype;
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public String getDatabaseusername() {
        return databaseusername;
    }

    public void setDatabaseusername(String databaseusername) {
        this.databaseusername = databaseusername;
    }

    @XmlTransient
    @JsonIgnore
    public List<Reporttablecolumn> getReporttablecolumnList() {
        return reporttablecolumnList;
    }

    public void setReporttablecolumnList(List<Reporttablecolumn> reporttablecolumnList) {
        this.reporttablecolumnList = reporttablecolumnList;
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
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Report[ id=" + id + " ]";
    }
    
}
