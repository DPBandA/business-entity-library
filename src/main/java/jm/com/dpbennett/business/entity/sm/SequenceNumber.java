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

package jm.com.dpbennett.business.entity.sm;

import java.io.Serializable;
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
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "sequencenumber")
@NamedQueries({
    @NamedQuery(name = "findAllSequenceNumbers", query = "SELECT e FROM SequenceNumber e ORDER BY e.yearReceived"),
    @NamedQuery(name = "getLastSequenceNumberByYear", query = "SELECT MAX(e.sequentialNumber) FROM SequenceNumber e WHERE e.yearReceived = :yearReceived"),
    @NamedQuery(name = "getLastSequenceNumberByName", query = "SELECT MAX(e.sequentialNumber) FROM SequenceNumber e WHERE e.name = :name"),
    @NamedQuery(name = "getLastSequenceNumberByNameAndYear", query = "SELECT MAX(e.sequentialNumber) FROM SequenceNumber e WHERE e.name = :name AND e.yearReceived = :yearReceived")
})
public class SequenceNumber implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer yearReceived;
    private Long sequentialNumber;
    @Transient
    private Boolean isDirty;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Integer getYearReceived() {
        return yearReceived;
    }

    public void setYearReceived(Integer yearReceived) {
        this.yearReceived = yearReceived;
    }

    public Long getSequentialNumber() {
        return sequentialNumber;
    }

    public void setSequentialNumber(Long sequentialNumber) {
        this.sequentialNumber = sequentialNumber;
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
        if (!(object instanceof SequenceNumber)) {
            return false;
        }
        SequenceNumber other = (SequenceNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpbennett.entity.SequenceNumber[ id=" + id + " ]";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static Long findNextSequentialNumberByNameAndByYear(EntityManager em, String name, int year) {
        Long last;
        SequenceNumber sequenceNumber = new SequenceNumber();

        try {
            last = em.createNamedQuery("getLastSequenceNumberByNameAndYear",
                    Long.class).setParameter("name", name).setParameter("yearReceived", year).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        if (last == null) {
            sequenceNumber.setName(name);
            sequenceNumber.setSequentialNumber(1L);
            sequenceNumber.setYearReceived(year);
//            em.getTransaction().begin();
            em.persist(sequenceNumber);
//            em.getTransaction().commit();
        } else {
            sequenceNumber.setName(name);
            sequenceNumber.setSequentialNumber(last + 1);
            sequenceNumber.setYearReceived(year);
//            em.getTransaction().begin();
            em.persist(sequenceNumber);
//            em.getTransaction().commit();
        }

        return sequenceNumber.getSequentialNumber();
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
