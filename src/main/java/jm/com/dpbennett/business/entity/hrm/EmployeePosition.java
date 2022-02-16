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
package jm.com.dpbennett.business.entity.hrm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "employeeposition")
@NamedQueries({
    @NamedQuery(name = "findAllEmployeePositions", query = "SELECT e FROM EmployeePosition e ORDER BY e.title")
    ,
    @NamedQuery(name = "findAllActiveEmployeePositions", query = "SELECT e FROM EmployeePosition e WHERE e.active = 1 ORDER BY e.title")
})
public class EmployeePosition implements Serializable, BusinessEntity {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String classification;
    private String category;    
    private Boolean active;
    @Column(length = 1024)
    private String description;
    private Double salary;
    private Double manHourRate;
    private Double lowerApprovalLevel;
    private Double upperApprovalLevel;
    @Transient
    private Boolean isDirty;

    /**
     * Constructs and EmployeePosition object.
     * 
     */
    public EmployeePosition() {
        title = "";
        classification = "";
        category = "";
        salary = 0.0;
        manHourRate = 0.0;
        lowerApprovalLevel = 0.0;
        upperApprovalLevel = 0.0;
        active = true;
        description = "";
    }

    public EmployeePosition(String title) {
        this.title = title;
        classification = "";
        category = "";
        salary = 0.0;
        manHourRate = 0.0;
        lowerApprovalLevel = 0.0;
        upperApprovalLevel = 0.0;
        active = true;
        description = "";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLowerApprovalLevel() {
        return lowerApprovalLevel;
    }

    public void setLowerApprovalLevel(Double lowerApprovalLevel) {
        this.lowerApprovalLevel = lowerApprovalLevel;
    }

    public Double getUpperApprovalLevel() {
        return upperApprovalLevel;
    }

    public void setUpperApprovalLevel(Double upperApprovalLevel) {
        this.upperApprovalLevel = upperApprovalLevel;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getManHourRate() {
        if (manHourRate == null) {
            manHourRate = 0.0;
        }
        return manHourRate;
    }

    public void setManHourRate(Double manHourRate) {
        this.manHourRate = manHourRate;
    }

    public Double getSalary() {
        if (salary == null) {
            salary = 0.0;
        }
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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
        if (!(object instanceof EmployeePosition)) {
            return false;
        }
        EmployeePosition other = (EmployeePosition) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public void setName(String name) {
        title = name;
    }

    public static List<EmployeePosition> findAllEmployeePositions(EntityManager em) {

        try {
            List<EmployeePosition> employeePositions = em.createNamedQuery("findAllEmployeePositions", EmployeePosition.class).getResultList();

            return employeePositions;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<EmployeePosition> findAllActiveEmployeePositions(EntityManager em) {

        try {
            List<EmployeePosition> employeePositions = em.createNamedQuery("findAllActiveEmployeePositions", EmployeePosition.class).getResultList();

            return employeePositions;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<String> findAllEmployeePositionTitles(EntityManager em) {

        ArrayList<String> titles = new ArrayList<>();

        try {
            List<EmployeePosition> employeePositions
                    = em.createNamedQuery("findAllEmployeePositions",
                            EmployeePosition.class).getResultList();
            for (EmployeePosition employeePosition : employeePositions) {
                titles.add(employeePosition.getTitle());
            }

            return titles;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static EmployeePosition findEmployeePositionById(EntityManager em, Long Id) {
        try {
            return em.find(EmployeePosition.class, Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static EmployeePosition findEmployeePositionByTitle(EntityManager em, String title) {

        try {
            String newTitle = title.trim().replaceAll("'", "''");

            List<EmployeePosition> employeePositions = em.createQuery("SELECT e FROM EmployeePosition e "
                    + "WHERE UPPER(e.title) "
                    + "= '" + newTitle.toUpperCase() + "'", EmployeePosition.class).getResultList();

            if (employeePositions.size() > 0) {
                return employeePositions.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<EmployeePosition> findEmployeePositionsByTitle(EntityManager em, String title) {

        try {
            String newTitle = title.replaceAll("'", "''");

            List<EmployeePosition> employeePositions
                    = em.createQuery("SELECT e FROM EmployeePosition e WHERE UPPER(e.title) like '%"
                            + newTitle.toUpperCase().trim() + "%' ORDER BY e.title", EmployeePosition.class).getResultList();
            return employeePositions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<EmployeePosition> findActiveEmployeePositionsByTitle(EntityManager em, String title) {

        try {
            String newTitle = title.replaceAll("'", "''");

            List<EmployeePosition> employeePositions
                    = em.createQuery("SELECT e FROM EmployeePosition e WHERE UPPER(e.title) like '%"
                            + newTitle.toUpperCase().trim() + "%' AND e.active = 1 ORDER BY e.title", EmployeePosition.class).getResultList();
            return employeePositions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static EmployeePosition findActiveEmployeePositionByTitle(EntityManager em,
            String title) {

        String newTitle = title.replaceAll("'", "''").trim().toUpperCase();

        try {
            List<EmployeePosition> employeePositions = em.createQuery("SELECT e FROM EmployeePosition e "
                    + "WHERE e.active = 1 AND UPPER(e.title) "
                    + "= '" + newTitle + "'",
                    EmployeePosition.class).getResultList();
            if (employeePositions.size() > 0) {
                EmployeePosition employeePosition = employeePositions.get(0);

                return employeePosition;
            }
        } catch (Exception e) {
            return null;
        }

        return null;
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

        return new ReturnMessage(false, "Employee Position not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
