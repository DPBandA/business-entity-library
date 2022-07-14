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

package jm.com.dpbennett.business.entity.pm;

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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;


/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "purchasereqnumber")
@NamedQueries({   
    @NamedQuery(name = "findAllPurchaseReqNumbers", query = "SELECT e FROM PurchaseReqNumber e ORDER BY e.yearReceived"),
    @NamedQuery(name = "getLastPurchaseReqNumber", query = "SELECT MAX(e.sequentialNumber) FROM PurchaseReqNumber e WHERE e.yearReceived = :yearReceived")
})
public class PurchaseReqNumber implements Serializable, BusinessEntity {
    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
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
        if (!(object instanceof PurchaseReqNumber)) {
            return false;
        }
        PurchaseReqNumber other = (PurchaseReqNumber) object;
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
        return "PurchaseReqNumber";
    }

    @Override
    public void setName(String name) {
    }

    
    public static List<PurchaseReqNumber> findAllPurchaseReqNumbers(EntityManager em) {

        try {
            List<PurchaseReqNumber> purchaseReqNumber = em.createNamedQuery("findAllPurchaseReqNumbers", PurchaseReqNumber.class).getResultList();

            return purchaseReqNumber;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Long findNextPurchaseReqSequentialNumber(EntityManager em, Integer year) {
        Long last;

        PurchaseReqNumber purchaseReqNumber = new PurchaseReqNumber();

        try {
            last = em.createNamedQuery("getLastPurchaseReqNumber",
                    Long.class).setParameter("yearReceived", year).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        if (last == null) {
            purchaseReqNumber.setYear(year);
            purchaseReqNumber.setSequentialNumber(1L);
            em.getTransaction().begin();
            em.persist(purchaseReqNumber);
            em.getTransaction().commit();
        } else {
            purchaseReqNumber.setYear(year);
            purchaseReqNumber.setSequentialNumber(last + 1);
            em.getTransaction().begin();
            em.persist(purchaseReqNumber);
            em.getTransaction().commit();
        }

        return purchaseReqNumber.getSequentialNumber();
    }
   
    public static PurchaseReqNumber findNextPurchaseReqNumber(EntityManager em, Integer year) {
        Long last;

        PurchaseReqNumber purchaseReqNumber = new PurchaseReqNumber();

        try {
            last = em.createNamedQuery("getLastPurchaseReqNumber",
                    Long.class).setParameter("yearReceived", year).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        if (last == null) {
            purchaseReqNumber.setYear(year);
            purchaseReqNumber.setSequentialNumber(1L);
        } else {
            purchaseReqNumber.setYear(year);
            purchaseReqNumber.setSequentialNumber(last + 1L);
        }

        return purchaseReqNumber;
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

        return new ReturnMessage(false, "Purchase requisition number not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

}
