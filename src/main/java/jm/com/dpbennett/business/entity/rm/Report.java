/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.rm;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.cm.Client;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "report")
@NamedQueries({
    @NamedQuery(name = "findAllReports", query = "SELECT j FROM Report j ORDER BY j.name")
})
public class Report implements BusinessEntity {

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
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<ReportTableColumn> reportColumns;
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
    private Boolean showDateField;

    public Report() {
        reportColumns = new ArrayList<>();
    }

    public Report(String name) {
        this.name = name;
        reportColumns = new ArrayList<>();
    }

    public Boolean getShowDateField() {
        if (showDateField == null) {
            showDateField = false;
        }
        return showDateField;
    }

    public void setShowDateField(Boolean showDateField) {
        this.showDateField = showDateField;
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
        if (departments != null && !departments.isEmpty()) {
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
        if (employees != null) {
            Collections.sort(employees);
        } else {
            employees = new ArrayList<>();
        }

        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Client> getClients() {
        if (clients != null && !clients.isEmpty()) {
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    @Override
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

    @Override
    public String getCategory() {
        return category;
    }

    @Override
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

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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
            return em.createQuery("SELECT r FROM Report r WHERE (r.active = 1 OR r.active IS NULL) ORDER BY r.name", Report.class).getResultList();
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

    public static Report findReportByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports = em.createQuery("SELECT r FROM Report r "
                    + "WHERE UPPER(r.name) "
                    + "= '" + value.toUpperCase() + "'", Report.class).getResultList();
            if (!reports.isEmpty()) {
                return reports.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Report findActiveReportByName(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports = em.createQuery("SELECT r FROM Report r "
                    + "WHERE UPPER(r.name)"
                    + " = '" + value.toUpperCase() + "'"
                    + " AND (r.active = 1 OR r.active IS NULL)", Report.class).getResultList();
            if (!reports.isEmpty()) {
                return reports.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Report> findReportsByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where UPPER(r.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();

            return reports;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findReports(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where UPPER(r.name) like '%"
                            + value.toUpperCase().trim() + "%' OR UPPER(r.description) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();

            return reports;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReportsByName(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where (r.active = 1 OR r.active IS NULL) AND UPPER(r.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReportsByCategoryAndName(
            EntityManager em,
            String category, String name) {

        try {

            category = category.replaceAll("&amp;", "&").replaceAll("'", "`");
            name = name.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where UPPER(r.name) like '%"
                            + name.toUpperCase().trim() + "%'"
                            + " AND (r.active = 1 OR r.active IS NULL)"
                            + " AND UPPER(r.category) like '%"
                            + category.toUpperCase().trim() + "%' ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Report> findActiveReports(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Report> reports
                    = em.createQuery("SELECT r FROM Report r where (r.active = 1 OR r.active IS NULL) AND (UPPER(r.name) like '%"
                            + value.toUpperCase().trim() + "%' OR UPPER(r.description) like '%"
                            + value.toUpperCase().trim() + "%') ORDER BY r.name", Report.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Report findDefaultReport(EntityManager em, String name) {
        Report report = Report.findActiveReportByName(em, name);

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

            for (ReportTableColumn reportColumn : reportColumns) {
                reportColumn.save(em);
            }

            for (Department department : departments) {
                department.save(em);
            }

            for (Employee employee : employees) {
                employee.save(em);
            }
            
            for (Client client : clients) {
                client.save(em);
            }

            if (!getDatePeriods().isEmpty()) {
                for (DatePeriod datePeriod : getDatePeriods()) {
                    if ((datePeriod.getIsDirty() || datePeriod.getId() == null)
                            && !datePeriod.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Date period save error occurred",
                                "An error occurred while saving a date period",
                                Message.SEVERITY_ERROR_NAME);

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

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
