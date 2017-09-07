/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "reporttablecolumn")
@NamedQueries({
    @NamedQuery(name = "findAllReportTableColumns", query = "SELECT r FROM ReportTableColumn r ORDER BY r.name")
})
public class ReportTableColumn implements Serializable, BusinessEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    /**
     * This is the method of the entity class that is called to get the
     * value for the column
     * The format of specifying a method is {class path}/{method}
     * e.g. jm.org.bsj.entity.Job/getJobNumber
     */
    private String entityClassMethodName;
   
    public ReportTableColumn() {
    }

    public ReportTableColumn(String name, String entityClassMethodName) {
        this.name = name;
        this.entityClassMethodName = entityClassMethodName;       
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getEntityClassMethodName() {
        return entityClassMethodName;
    }

    public void setEntityClassMethodName(String entityClassMethodName) {
        this.entityClassMethodName = entityClassMethodName;
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
        if (!(object instanceof ReportTableColumn)) {
            return false;
        }
        ReportTableColumn other = (ReportTableColumn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.ReportTableColumn[id=" + id + "]";
    }
    
    
    public static List<ReportTableColumn> findAllReportTableColumns(EntityManager em) {

        try {
            List<ReportTableColumn> reportTableColumns = em.createNamedQuery("findAllReportTableColumns", ReportTableColumn.class).getResultList();
            return reportTableColumns;
        } catch (Exception e) {
            return null;
        }
    }

}