/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "report")
@NamedQueries({
    @NamedQuery(name = "findAllReports", query = "SELECT j FROM Report j ORDER BY j.name")
})
public class Report implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String category = "";
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date startDate;
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date endDate;
//    @Column(length = 1024)
//    private String sqlText = "";
    @Column(length = 1024)
    private String reportFileTemplate = "";
    @Column(length = 1024)
    private String reportFile = "";
    private String reportFileMimeType = "";
    private String dateFormat = "MMM dd, yyyy";
    @OneToMany(cascade = CascadeType.ALL)
    private List<ReportTableColumn> reportColumns; // tk retire use of this
    @Column(length = 1024)
    private String databaseURL = "";
    @Column(length = 1024)
    private String databaseDriverClass = "";
    private String databaseUsername = "";
    private String databasePassword = "";
    private Boolean active;

    public Report() {
        reportColumns = new ArrayList<>(); // tk retire use of this
    }

    public Report(String name) {
        this.name = name;
        reportColumns = new ArrayList<>(); // tk retire use of this
    }

    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabaseDriverClass() {
        return databaseDriverClass;
    }

    public void setDatabaseDriverClass(String databaseDriverClass) {
        this.databaseDriverClass = databaseDriverClass;
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ReportTableColumn> getReportColumns() {
        return reportColumns;
    }

    public void setReportColumns(List<ReportTableColumn> reportColumns) {
        this.reportColumns = reportColumns;
    }

    public String getReportFileMimeType() {
        return reportFileMimeType;
    }

    public void setReportFileMimeType(String reportFileMimeType) {
        this.reportFileMimeType = reportFileMimeType;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getReportFileTemplate() {
        return reportFileTemplate;
    }

    public void setReportFileTemplate(String reportFileTemplate) {
        this.reportFileTemplate = reportFileTemplate;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

//    public String getSqlText() {
//        return sqlText;
//    }
//
//    public void setSqlText(String sqlText) {
//        this.sqlText = sqlText;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }

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
        return "jm.org.bsj.entity.JobReport[id=" + id + "]";
    }

    public static List<Report> findAllReports(EntityManager em) {

        try {
            List<Report> reports = em.createNamedQuery("findAllReports", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Report> findAllActiveReports(EntityManager em) {

        try {
            return em.createQuery("SELECT r FROM Report r WHERE r.active = 1 ORDER BY r.name", Report.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Report findReportById(EntityManager em, Long Id) {

        try {
            Report report = em.find(Report.class, Id);
            return report;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Report findReportByName(EntityManager em, String reportName) {

        try {
            String newReportName = reportName.trim().replaceAll("'", "''");

            List<Report> reports = em.createQuery("SELECT r FROM Report r "
                    + "WHERE UPPER(r.name) "
                    + "= '" + newReportName.toUpperCase() + "'", Report.class).getResultList();
            if (reports.size() > 0) {
                return reports.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Report> findReportsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where UPPER(r.name) like '%"
                            + newName.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReportsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where r.active = 1 AND UPPER(r.name) like '%"
                            + newName.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
      
    public static Report findDefaultReport(EntityManager em, String name) {
        Report report = Report.findReportByName(em, name);

        if (report == null) {
            report = new Report(name);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, report);
            em.getTransaction().commit();
        }

        return report;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Report not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

}
