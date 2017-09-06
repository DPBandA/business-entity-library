/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobcategory")
@NamedQueries({
    @NamedQuery(name = "findAllJobCategories", query = "SELECT e FROM JobCategory e ORDER BY e.category"),
    @NamedQuery(name = "findAllActiveJobCategories", query = "SELECT e FROM JobCategory e WHERE e.active = 1 ORDER BY e.category"),
    @NamedQuery(name = "findByCategory", query = "SELECT e FROM JobCategory e WHERE e.category = :category")
})
@XmlRootElement
public class JobCategory implements Serializable, BusinessEntity {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String classification;
    private String category;
    private Boolean isEarning;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departments;
    private Boolean active;
    @Column(length = 1024)
    private String description;

    public JobCategory() {
        departments = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
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

    public List<Department> getDeparments() {
        if (departments == null) {
            departments = new ArrayList<>();
        }
        return departments;
    }

    public void setDeparments(List<Department> departments) {
        this.departments = departments;
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

    public Boolean getIsEarning() {
        if (isEarning == null) {
            isEarning = false;
        }
        return isEarning;
    }

    public void setIsEarning(Boolean isEarning) {
        this.isEarning = isEarning;
    }

    public String getIsBillable() {
        if (getIsEarning()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setIsBillable(String billable) {
        if (billable.equals("Yes")) {
            setIsEarning(true);
        } else {
            setIsEarning(false);
        }
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
        if (!(object instanceof JobCategory)) {
            return false;
        }
        JobCategory other = (JobCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return category;
    }

    @Override
    public String getName() {
        return category;
    }

    @Override
    public void setName(String name) {
        category = name;
    }

    public static List<JobCategory> findAllJobCategories(EntityManager em) {

        try {
            List<JobCategory> categories = em.createNamedQuery("findAllJobCategories", JobCategory.class).getResultList();

            return categories;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<JobCategory> findAllActiveJobCategories(EntityManager em) {

        try {
            List<JobCategory> categories = em.createNamedQuery("findAllActiveJobCategories", JobCategory.class).getResultList();

            return categories;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<String> findAllJobCategoryNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<String>();

        try {
            List<JobCategory> jobCategories = em.createNamedQuery("findAllJobCategories", JobCategory.class).getResultList();
            for (JobCategory jobCategory : jobCategories) {
                names.add(jobCategory.getCategory());
            }

            return names;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static JobCategory findJobCategoryById(EntityManager em, Long Id) {
        try {
            return em.find(JobCategory.class, Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static JobCategory findJobCategoryByName(EntityManager em, String name) {

        try {
            String newCategory = name.trim().replaceAll("'", "''");

            List<JobCategory> jobCategories = em.createQuery("SELECT c FROM JobCategory c "
                    + "WHERE UPPER(c.category) "
                    + "= '" + newCategory.toUpperCase() + "'", JobCategory.class).getResultList();

            if (jobCategories.size() > 0) {
                return jobCategories.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
