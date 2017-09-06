/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpba.business.entity;

import java.io.Serializable;
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


/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobsequencenumber")
@NamedQueries({   
    @NamedQuery(name = "findAllJobSequenceNumbers", query = "SELECT e FROM JobSequenceNumber e ORDER BY e.yearReceived"),
    @NamedQuery(name = "getLastJobSequenceNumber", query = "SELECT MAX(e.sequentialNumber) FROM JobSequenceNumber e WHERE e.yearReceived = :yearReceived")
})
public class JobSequenceNumber implements Serializable, BusinessEntity {
    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
    private Integer yearReceived;
    private Long sequentialNumber;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSequentialNumber() {
        return sequentialNumber;
    }

    public void setSequentialNumber(Long sequentialNumber) {
        this.sequentialNumber = sequentialNumber;
    }

    public Integer getYear() {
        return yearReceived;
    }

    public void setYear(Integer year) {
        this.yearReceived = year;
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
        if (!(object instanceof JobSequenceNumber)) {
            return false;
        }
        JobSequenceNumber other = (JobSequenceNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + sequentialNumber + " (" + yearReceived + ")";
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public static List<JobSequenceNumber> findAllJobSequencenceNumbers(EntityManager em) {

        try {
            List<JobSequenceNumber> jobSequenceNumber = em.createNamedQuery("findAllJobSequenceNumbers", JobSequenceNumber.class).getResultList();

            return jobSequenceNumber;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Long findNextJobSequentialNumber(EntityManager em, Integer year) {
        Long last;

        JobSequenceNumber jobSequenceNumber = new JobSequenceNumber();

        try {
            last = em.createNamedQuery("getLastJobSequenceNumber",
                    Long.class).setParameter("yearReceived", year).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        if (last == null) {
            jobSequenceNumber.setYear(year);
            jobSequenceNumber.setSequentialNumber(1L);
            em.getTransaction().begin();
            em.persist(jobSequenceNumber);
            em.getTransaction().commit();
        } else {
            jobSequenceNumber.setYear(year);
            jobSequenceNumber.setSequentialNumber(last + 1);
            em.getTransaction().begin();
            em.persist(jobSequenceNumber);
            em.getTransaction().commit();
        }

        return jobSequenceNumber.getSequentialNumber();
    }

    public static JobSequenceNumber findNextJobSequenceNumber(EntityManager em, Integer year) {
        Long last;

        JobSequenceNumber jobSequenceNumber = new JobSequenceNumber();

        try {
            last = em.createNamedQuery("getLastJobSequenceNumber",
                    Long.class).setParameter("yearReceived", year).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        if (last == null) {
            jobSequenceNumber.setYear(year);
            jobSequenceNumber.setSequentialNumber(1L);
        } else {
            jobSequenceNumber.setYear(year);
            jobSequenceNumber.setSequentialNumber(last + 1L);
        }

        return jobSequenceNumber;
    }

}
