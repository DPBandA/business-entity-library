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
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @Column(length = 1024)
    private String description = "";
    @Column(length = 1024)
    private String reportFileTemplate = "";
    @Column(length = 1024)
    private String reportFile = "";
    private String reportFileMimeType = "";
    private String reportOutputFileMimeType = "";
    @OneToMany(cascade = CascadeType.ALL)
    private List<ReportTableColumn> reportColumns; // tk retire this 
    private Boolean active;
    private Boolean usePackagedReportFileTemplate;
    @Transient
    private Boolean isDirty;
    // Report parameters
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Department> departments;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Client> clients;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<DatePeriod> datePeriods;
    private Boolean departmentRequired;
    private Boolean employeeRequired;
    private Boolean clientRequired;
    private Boolean datePeriodRequired;

    public Report() {
        reportColumns = new ArrayList<>(); // tk retire use of this
    }

    public Report(String name) {
        this.name = name;
        reportColumns = new ArrayList<>(); // tk retire use of this
    }
    
    public Boolean getDepartmentRequired() {
        if (departmentRequired == null) {
            departmentRequired = false;
        }
        return departmentRequired;
    }

    public void setDepartmentRequired(Boolean departmentRequired) {
        this.departmentRequired = departmentRequired;
    }

    public Boolean getEmployeeRequired() {
        if (employeeRequired == null) {
            employeeRequired = false;
        }
        return employeeRequired;
    }

    public void setEmployeeRequired(Boolean employeeRequired) {
        this.employeeRequired = employeeRequired;
    }

    public Boolean getClientRequired() {
        if (clientRequired == null) {
            clientRequired = false;
        }
        return clientRequired;
    }

    public void setClientRequired(Boolean clientRequired) {
        this.clientRequired = clientRequired;
    }

    public Boolean getDatePeriodRequired() {
        if (datePeriodRequired == null) {
            datePeriodRequired = false;
        }
        return datePeriodRequired;
    }

    public void setDatePeriodRequired(Boolean datePeriodRequired) {
        this.datePeriodRequired = datePeriodRequired;
    }

    public List<Department> getDepartments() {
        if (departments != null) {
            Collections.sort(departments);
        } else {
            departments = new ArrayList<>();
        }

        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Client> getClients() {
        if (clients != null) {
            Collections.sort(clients);
        } else {
            clients = new ArrayList<>();
        }

        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<DatePeriod> getDatePeriods() {
        if (datePeriods != null) {
            Collections.sort(datePeriods);
        } else {
            datePeriods = new ArrayList<>();
        }

        return datePeriods;
    }

    public void setDatePeriods(List<DatePeriod> datePeriods) {
        this.datePeriods = datePeriods;
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

    public String getReportOutputFileMimeType() {
        return reportOutputFileMimeType;
    }

    public void setReportOutputFileMimeType(String reportOutputFileMimeType) {
        this.reportOutputFileMimeType = reportOutputFileMimeType;
    }

    public Boolean getUsePackagedReportFileTemplate() {
        if (usePackagedReportFileTemplate == null) {
            usePackagedReportFileTemplate = true;
        }
        return usePackagedReportFileTemplate;
    }

    public void setUsePackagedReportFileTemplate(Boolean usePackagedReportFileTemplate) {
        this.usePackagedReportFileTemplate = usePackagedReportFileTemplate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List getCategories() {
        ArrayList categories = new ArrayList();

        categories.add(new SelectItem("Job", "Job"));
        categories.add(new SelectItem("Legal", "Legal"));

        return categories;
    }

    public static List getMimeTypes() {
        ArrayList categories = new ArrayList();

        categories.add(new SelectItem("--", "--"));
        categories.add(new SelectItem("application/jasper", "application/jasper"));
        categories.add(new SelectItem("application/xls", "application/xls"));
        categories.add(new SelectItem("application/xlsx", "application/xlsx"));
        categories.add(new SelectItem("application/pdf", "application/pdf"));

        return categories;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    public static List<Report> findReports(EntityManager em, String query) {

        try {
            String newQuery = query.replaceAll("'", "''");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where UPPER(r.name) like '%"
                            + newQuery.toUpperCase().trim() + "%' OR UPPER(r.description) like '%"
                            + newQuery.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReportsByName(EntityManager em, String query) {

        try {
            String newQuery = query.replaceAll("'", "''");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where r.active = 1 AND UPPER(r.name) like '%"
                            + newQuery.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReportsByCategoryAndName(EntityManager em, String category, String name) {

        try {
            String newName = name.replaceAll("'", "''");
            String newCategory = category.replaceAll("'", "''");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where r.active = 1 AND UPPER(r.name) like '%"
                            + newName.toUpperCase().trim() + "%'"
                            + " AND UPPER(r.category) like '%"
                            + newCategory.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReports(EntityManager em, String query) {

        try {
            String newQuery = query.replaceAll("'", "''");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where r.active = 1 AND (UPPER(r.name) like '%"
                            + newQuery.toUpperCase().trim() + "%' OR UPPER(r.description) like '%"
                            + newQuery.toUpperCase().trim() + "%') ORDER BY r.name", Report.class).getResultList();
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
            // Save new/edited date periods 
            if (!getDatePeriods().isEmpty()) {
                for (DatePeriod datePeriod : getDatePeriods()) {
                    if ((datePeriod.getIsDirty() || datePeriod.getId() == null)
                            && !datePeriod.save(em).isSuccess()) {
                        
                        return new ReturnMessage(false,
                                "Date period save error occurred",
                                "An error occurred while saving a date period",
                                FacesMessage.SEVERITY_ERROR);
                        
                    }
                }
            }

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
