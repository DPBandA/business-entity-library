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
@Table(name = "documentreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentreport.findAll", query = "SELECT d FROM Documentreport d"),
    @NamedQuery(name = "Documentreport.findById", query = "SELECT d FROM Documentreport d WHERE d.id = :id"),
    @NamedQuery(name = "Documentreport.findByStartdate", query = "SELECT d FROM Documentreport d WHERE d.startdate = :startdate"),
    @NamedQuery(name = "Documentreport.findByShowcomments", query = "SELECT d FROM Documentreport d WHERE d.showcomments = :showcomments"),
    @NamedQuery(name = "Documentreport.findByShowresponsibleofficer", query = "SELECT d FROM Documentreport d WHERE d.showresponsibleofficer = :showresponsibleofficer"),
    @NamedQuery(name = "Documentreport.findByShowmonthreceived", query = "SELECT d FROM Documentreport d WHERE d.showmonthreceived = :showmonthreceived"),
    @NamedQuery(name = "Documentreport.findByEnddate", query = "SELECT d FROM Documentreport d WHERE d.enddate = :enddate"),
    @NamedQuery(name = "Documentreport.findByShowdatereceived", query = "SELECT d FROM Documentreport d WHERE d.showdatereceived = :showdatereceived"),
    @NamedQuery(name = "Documentreport.findByShowdocumentform", query = "SELECT d FROM Documentreport d WHERE d.showdocumentform = :showdocumentform"),
    @NamedQuery(name = "Documentreport.findByShowurl", query = "SELECT d FROM Documentreport d WHERE d.showurl = :showurl"),
    @NamedQuery(name = "Documentreport.findByColumnstoexclude", query = "SELECT d FROM Documentreport d WHERE d.columnstoexclude = :columnstoexclude"),
    @NamedQuery(name = "Documentreport.findByShowworkperformedondocument", query = "SELECT d FROM Documentreport d WHERE d.showworkperformedondocument = :showworkperformedondocument"),
    @NamedQuery(name = "Documentreport.findByName", query = "SELECT d FROM Documentreport d WHERE d.name = :name"),
    @NamedQuery(name = "Documentreport.findByShowtype", query = "SELECT d FROM Documentreport d WHERE d.showtype = :showtype"),
    @NamedQuery(name = "Documentreport.findByShowsequencenumber", query = "SELECT d FROM Documentreport d WHERE d.showsequencenumber = :showsequencenumber"),
    @NamedQuery(name = "Documentreport.findByShownumberofdocuments", query = "SELECT d FROM Documentreport d WHERE d.shownumberofdocuments = :shownumberofdocuments"),
    @NamedQuery(name = "Documentreport.findByShowdescription", query = "SELECT d FROM Documentreport d WHERE d.showdescription = :showdescription"),
    @NamedQuery(name = "Documentreport.findBySqltext", query = "SELECT d FROM Documentreport d WHERE d.sqltext = :sqltext"),
    @NamedQuery(name = "Documentreport.findByShowrequestingdepartment", query = "SELECT d FROM Documentreport d WHERE d.showrequestingdepartment = :showrequestingdepartment"),
    @NamedQuery(name = "Documentreport.findByShowturnaroundtime", query = "SELECT d FROM Documentreport d WHERE d.showturnaroundtime = :showturnaroundtime"),
    @NamedQuery(name = "Documentreport.findByShowsubmittedby", query = "SELECT d FROM Documentreport d WHERE d.showsubmittedby = :showsubmittedby"),
    @NamedQuery(name = "Documentreport.findByShownumber", query = "SELECT d FROM Documentreport d WHERE d.shownumber = :shownumber"),
    @NamedQuery(name = "Documentreport.findByShowdateofcompletion", query = "SELECT d FROM Documentreport d WHERE d.showdateofcompletion = :showdateofcompletion"),
    @NamedQuery(name = "Documentreport.findByShowcurrentdocumentturnaroundtime", query = "SELECT d FROM Documentreport d WHERE d.showcurrentdocumentturnaroundtime = :showcurrentdocumentturnaroundtime"),
    @NamedQuery(name = "Documentreport.findByShowclassification", query = "SELECT d FROM Documentreport d WHERE d.showclassification = :showclassification"),
    @NamedQuery(name = "Documentreport.findByShownotes", query = "SELECT d FROM Documentreport d WHERE d.shownotes = :shownotes"),
    @NamedQuery(name = "Documentreport.findByShowexpecteddateofcompletion", query = "SELECT d FROM Documentreport d WHERE d.showexpecteddateofcompletion = :showexpecteddateofcompletion")})
public class Documentreport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "SHOWCOMMENTS")
    private Boolean showcomments;
    @Column(name = "SHOWRESPONSIBLEOFFICER")
    private Boolean showresponsibleofficer;
    @Column(name = "SHOWMONTHRECEIVED")
    private Boolean showmonthreceived;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Column(name = "SHOWDATERECEIVED")
    private Boolean showdatereceived;
    @Column(name = "SHOWDOCUMENTFORM")
    private Boolean showdocumentform;
    @Column(name = "SHOWURL")
    private Boolean showurl;
    @Size(max = 1024)
    @Column(name = "COLUMNSTOEXCLUDE")
    private String columnstoexclude;
    @Column(name = "SHOWWORKPERFORMEDONDOCUMENT")
    private Boolean showworkperformedondocument;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "SHOWTYPE")
    private Boolean showtype;
    @Column(name = "SHOWSEQUENCENUMBER")
    private Boolean showsequencenumber;
    @Column(name = "SHOWNUMBEROFDOCUMENTS")
    private Boolean shownumberofdocuments;
    @Column(name = "SHOWDESCRIPTION")
    private Boolean showdescription;
    @Size(max = 1024)
    @Column(name = "SQLTEXT")
    private String sqltext;
    @Column(name = "SHOWREQUESTINGDEPARTMENT")
    private Boolean showrequestingdepartment;
    @Column(name = "SHOWTURNAROUNDTIME")
    private Boolean showturnaroundtime;
    @Column(name = "SHOWSUBMITTEDBY")
    private Boolean showsubmittedby;
    @Column(name = "SHOWNUMBER")
    private Boolean shownumber;
    @Column(name = "SHOWDATEOFCOMPLETION")
    private Boolean showdateofcompletion;
    @Column(name = "SHOWCURRENTDOCUMENTTURNAROUNDTIME")
    private Boolean showcurrentdocumentturnaroundtime;
    @Column(name = "SHOWCLASSIFICATION")
    private Boolean showclassification;
    @Column(name = "SHOWNOTES")
    private Boolean shownotes;
    @Column(name = "SHOWEXPECTEDDATEOFCOMPLETION")
    private Boolean showexpecteddateofcompletion;

    public Documentreport() {
    }

    public Documentreport(Long id) {
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

    public Boolean getShowcomments() {
        return showcomments;
    }

    public void setShowcomments(Boolean showcomments) {
        this.showcomments = showcomments;
    }

    public Boolean getShowresponsibleofficer() {
        return showresponsibleofficer;
    }

    public void setShowresponsibleofficer(Boolean showresponsibleofficer) {
        this.showresponsibleofficer = showresponsibleofficer;
    }

    public Boolean getShowmonthreceived() {
        return showmonthreceived;
    }

    public void setShowmonthreceived(Boolean showmonthreceived) {
        this.showmonthreceived = showmonthreceived;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Boolean getShowdatereceived() {
        return showdatereceived;
    }

    public void setShowdatereceived(Boolean showdatereceived) {
        this.showdatereceived = showdatereceived;
    }

    public Boolean getShowdocumentform() {
        return showdocumentform;
    }

    public void setShowdocumentform(Boolean showdocumentform) {
        this.showdocumentform = showdocumentform;
    }

    public Boolean getShowurl() {
        return showurl;
    }

    public void setShowurl(Boolean showurl) {
        this.showurl = showurl;
    }

    public String getColumnstoexclude() {
        return columnstoexclude;
    }

    public void setColumnstoexclude(String columnstoexclude) {
        this.columnstoexclude = columnstoexclude;
    }

    public Boolean getShowworkperformedondocument() {
        return showworkperformedondocument;
    }

    public void setShowworkperformedondocument(Boolean showworkperformedondocument) {
        this.showworkperformedondocument = showworkperformedondocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShowtype() {
        return showtype;
    }

    public void setShowtype(Boolean showtype) {
        this.showtype = showtype;
    }

    public Boolean getShowsequencenumber() {
        return showsequencenumber;
    }

    public void setShowsequencenumber(Boolean showsequencenumber) {
        this.showsequencenumber = showsequencenumber;
    }

    public Boolean getShownumberofdocuments() {
        return shownumberofdocuments;
    }

    public void setShownumberofdocuments(Boolean shownumberofdocuments) {
        this.shownumberofdocuments = shownumberofdocuments;
    }

    public Boolean getShowdescription() {
        return showdescription;
    }

    public void setShowdescription(Boolean showdescription) {
        this.showdescription = showdescription;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }

    public Boolean getShowrequestingdepartment() {
        return showrequestingdepartment;
    }

    public void setShowrequestingdepartment(Boolean showrequestingdepartment) {
        this.showrequestingdepartment = showrequestingdepartment;
    }

    public Boolean getShowturnaroundtime() {
        return showturnaroundtime;
    }

    public void setShowturnaroundtime(Boolean showturnaroundtime) {
        this.showturnaroundtime = showturnaroundtime;
    }

    public Boolean getShowsubmittedby() {
        return showsubmittedby;
    }

    public void setShowsubmittedby(Boolean showsubmittedby) {
        this.showsubmittedby = showsubmittedby;
    }

    public Boolean getShownumber() {
        return shownumber;
    }

    public void setShownumber(Boolean shownumber) {
        this.shownumber = shownumber;
    }

    public Boolean getShowdateofcompletion() {
        return showdateofcompletion;
    }

    public void setShowdateofcompletion(Boolean showdateofcompletion) {
        this.showdateofcompletion = showdateofcompletion;
    }

    public Boolean getShowcurrentdocumentturnaroundtime() {
        return showcurrentdocumentturnaroundtime;
    }

    public void setShowcurrentdocumentturnaroundtime(Boolean showcurrentdocumentturnaroundtime) {
        this.showcurrentdocumentturnaroundtime = showcurrentdocumentturnaroundtime;
    }

    public Boolean getShowclassification() {
        return showclassification;
    }

    public void setShowclassification(Boolean showclassification) {
        this.showclassification = showclassification;
    }

    public Boolean getShownotes() {
        return shownotes;
    }

    public void setShownotes(Boolean shownotes) {
        this.shownotes = shownotes;
    }

    public Boolean getShowexpecteddateofcompletion() {
        return showexpecteddateofcompletion;
    }

    public void setShowexpecteddateofcompletion(Boolean showexpecteddateofcompletion) {
        this.showexpecteddateofcompletion = showexpecteddateofcompletion;
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
        if (!(object instanceof Documentreport)) {
            return false;
        }
        Documentreport other = (Documentreport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Documentreport[ id=" + id + " ]";
    }
    
}
