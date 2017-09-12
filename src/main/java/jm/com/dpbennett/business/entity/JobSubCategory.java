/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobsubcategory")
@NamedQueries({
    @NamedQuery(name = "findAllJobSubCategories", query = "SELECT e FROM JobSubCategory e ORDER BY e.subCategory"),
    @NamedQuery(name = "findAllActiveJobSubCategories", query = "SELECT e FROM JobSubCategory e WHERE e.active = 1 ORDER BY e.subCategory"),
    @NamedQuery(name = "findByCategoryId", query = "SELECT e FROM JobSubCategory e WHERE e.categoryId = :categoryId")
})
@XmlRootElement
public class JobSubCategory implements Serializable, BusinessEntity, Comparable {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SubCategory")
    private String subCategory;
    @Column(name = "IsEarning")
    private Boolean isEarning;
    @Column(name = "CategoryId")
    private Long categoryId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departments;
    private Boolean active;
    @Column(length = 1024)
    private String description;

    public JobSubCategory() {
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

    public List<Department> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<>();
        }
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
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
        if (!(object instanceof JobSubCategory)) {
            return false;
        }
        JobSubCategory other = (JobSubCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return subCategory;
    }

    @Override
    public String getName() {
        return subCategory;
    }

    @Override
    public void setName(String name) {
        subCategory = name;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    public static JobSubCategory findJobSubCategoryById(EntityManager em, Long Id) {

        try {
            return em.find(JobSubCategory.class, Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static JobSubCategory findJobSubCategoryByName(EntityManager em, String name) {

        try {
            String newSubCategory = name.trim().replaceAll("'", "''");

            List<JobSubCategory> jobSubCategories =
                    em.createQuery("SELECT c FROM JobSubCategory c "
                    + "WHERE UPPER(c.subCategory) "
                    + "= '" + newSubCategory.toUpperCase() + "'", JobSubCategory.class).getResultList();
            if (jobSubCategories.size() > 0) {
                return jobSubCategories.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobSubCategory> findJobSubCategoriesByCategoryId(EntityManager em, Long Id) {
        try {
            return em.createNamedQuery("findByCategoryId", JobSubCategory.class).
                    setParameter("categoryId", Id).
                    getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobSubCategory> findAllJobSubCategories(EntityManager em) {
        try {
            return em.createNamedQuery("findAllJobSubCategories", JobSubCategory.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobSubCategory> findAllActiveJobSubCategories(EntityManager em) {
        try {
            return em.createNamedQuery("findAllActiveJobSubCategories", JobSubCategory.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobSubCategory> findAllJobSubCategoriesByDepartment(EntityManager em, Department department) {
        try {
            List<JobSubCategory> jobSubCategories =
                    em.createQuery(
                    "SELECT j FROM JobSubCategory j JOIN j.departments department"
                    + " WHERE department.name = '" + department.getName().trim() + "'"
                    + " ORDER BY j.subCategory", JobSubCategory.class).getResultList();
            return jobSubCategories;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<JobSubCategory> findAllJobSubCategoriesGroupedByEarningsByDepartment(EntityManager em, Department department) {
        try {
            List<JobSubCategory> earningJobSubCategories = new ArrayList<>();
            List<JobSubCategory> nonEarningJobSubCategories = new ArrayList<>();
            List<JobSubCategory> jobSubCategories =
                    em.createQuery(
                    "SELECT j FROM JobSubCategory j JOIN j.departments department"
                    + " WHERE department.name = '" + department.getName().trim() + "'",
                    JobSubCategory.class).getResultList();

            if (!jobSubCategories.isEmpty()) {
                for (JobSubCategory jobSubCategory : jobSubCategories) {
                    if (jobSubCategory.getIsEarning()) {
                        earningJobSubCategories.add(jobSubCategory);
                    } else {
                        nonEarningJobSubCategories.add(jobSubCategory);
                    }
                }
            }

            // sort each earning cat and rebuild list
            jobSubCategories.clear();
            Collections.sort(earningJobSubCategories);
            Collections.sort(nonEarningJobSubCategories);
            jobSubCategories.addAll(earningJobSubCategories);
            jobSubCategories.addAll(nonEarningJobSubCategories);

            return jobSubCategories;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<JobSubCategory> findJobSubCategoriesById(EntityManager em, Long jobCategoryId) {

        try {
            List<JobSubCategory> subCategories = em.createNamedQuery("findByCategoryId", JobSubCategory.class).
                    setParameter("jobCategoryId", jobCategoryId).
                    getResultList();

            return subCategories;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<String> findAllJobSubCategoryNames(EntityManager em) {
        ArrayList<String> names = new ArrayList<>();

        try {  // use String.class
            List<JobSubCategory> jobSubCategories = em.createNamedQuery("findAllJobSubCategories", JobSubCategory.class).getResultList();
            for (JobSubCategory jobSubCategory : jobSubCategories) {
                names.add(jobSubCategory.getSubCategory());
            }
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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
        }
        else {
            setIsEarning(false);
        }
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

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}