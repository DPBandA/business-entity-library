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
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

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

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
