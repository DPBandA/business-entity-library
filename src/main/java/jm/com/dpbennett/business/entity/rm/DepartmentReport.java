/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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

import jm.com.dpbennett.business.entity.hrm.Department;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "departmentreport")
public class DepartmentReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer reportPeriod;
    private Integer reportYear;
    private String executiveSummary;
    @OneToOne (cascade=CascadeType.REFRESH)
    private Department department;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public Integer getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(Integer reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public Integer getReportYear() {
        return reportYear;
    }

    public void setReportYear(Integer reportYear) {
        this.reportYear = reportYear;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        if (!(object instanceof DepartmentReport)) {
            return false;
        }
        DepartmentReport other = (DepartmentReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.DepartmentReport[id=" + id + "]";
    }
    
    public static DepartmentReport findDepartmentReportById(EntityManager em, Long Id) {

        return em.find(DepartmentReport.class, Id);
    }

    public static DepartmentReport findDepartmentReport(
            EntityManager em, 
            String value,
            Integer reportPeriod,
            Integer reportYear) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            String searchQuery =
                    "SELECT r FROM DepartmentReport r JOIN r.department d WHERE d.name = '" + value + "'"
                    + " AND r.reportPeriod = " + reportPeriod
                    + " AND r.reportYear = " + reportYear;

            List<DepartmentReport> reports = em.createQuery(searchQuery, DepartmentReport.class).getResultList();
            if (reports.size() > 0) {
                return reports.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
