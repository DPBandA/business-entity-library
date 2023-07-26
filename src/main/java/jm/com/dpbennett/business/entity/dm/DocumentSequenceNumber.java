/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2023  D P Bennett & Associates Limited

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

package jm.com.dpbennett.business.entity.dm;

import java.io.Serializable;
import java.util.Date;
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
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "documentsequencenumber")
@NamedQueries({
    @NamedQuery(name = "findAllDocumentSequenceNumbers", query = "SELECT d FROM DocumentSequenceNumber d ORDER BY d.sequentialNumber"),
    @NamedQuery(name = "getLastDocumentSequenceNumber",
    query = "SELECT MAX(e.sequentialNumber) "
    + "FROM DocumentSequenceNumber e "
    + "WHERE e.yearReceived = :yearReceived "
    + "AND e.monthReceived = :monthReceived "
    + "AND e.documentTypeId = :documentTypeId"),
    @NamedQuery(name = "getDocumentSequenceNumber",
    query = "SELECT MAX(e.sequentialNumber) "
    + "FROM DocumentSequenceNumber e "
    + "WHERE e.yearReceived = :yearReceived "
    + "AND e.monthReceived = :monthReceived "
    + "AND e.documentTypeId = :documentTypeId "
    + "AND e.sequentialNumber = :sequentialNumber")
})
public class DocumentSequenceNumber implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer yearReceived;
    private Integer monthReceived;
    private Long sequentialNumber;
    private Long documentTypeId;
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

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Integer getMonthReceived() {
        return monthReceived;
    }

    public void setMonthReceived(Integer monthReceived) {
        this.monthReceived = monthReceived;
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
        if (!(object instanceof DocumentSequenceNumber)) {
            return false;
        }
        DocumentSequenceNumber other = (DocumentSequenceNumber) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.DocumentSequenceNumber[id=" + id + "]";
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public static List<DocumentSequenceNumber> findAllDocumentSequenceNumbers(EntityManager em) {

        try {
            List<DocumentSequenceNumber> documentSequenceNumbers = em.createNamedQuery("findAllDocumentSequenceNumbers", DocumentSequenceNumber.class).getResultList();

            return documentSequenceNumbers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Long findLastDocumentSequenceNumber(EntityManager em, Integer year, Integer month, Long typeId) {
        Long last;

        try {
            last = em.createNamedQuery("getLastDocumentSequenceNumber",
                    Long.class).setParameter("yearReceived", year).
                    setParameter("monthReceived", month).
                    setParameter("documentTypeId", typeId).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        return last;
    }
    
     /**
     * Returns the supplied sequential number of if there is an existing
     * sequence number with the supplied year, month and document type id.nceNumber
     *
     * @param em
     * @param sequentialNumber
     * @param year
     * @param month
     * @param typeId
     * @return
     */
    public static Long findDocumentSequenceNumber(EntityManager em, 
            Long sequentialNumber,
            Integer year, 
            Integer month, 
            Long typeId) {
        
        Long last;

        try {
            last = em.createNamedQuery("getDocumentSequenceNumber",
                    Long.class).
                    setParameter("yearReceived", year).
                    setParameter("monthReceived", month).
                    setParameter("documentTypeId", typeId).
                    setParameter("sequentialNumber", sequentialNumber).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            last = null;
        }

        return last;
    }

    public static Long findNextDocumentSequenceNumber(EntityManager em, Integer year, Integer month, Long typeId) {
        Long last;

        DocumentSequenceNumber documentSequenceNumber = new DocumentSequenceNumber();

        last = DocumentSequenceNumber.findLastDocumentSequenceNumber(em, year, month, typeId);

        if (last == null) {
            documentSequenceNumber.setYearReceived(year);
            documentSequenceNumber.setMonthReceived(month);
            documentSequenceNumber.setDocumentTypeId(typeId);
            documentSequenceNumber.setSequentialNumber(0L);
            em.persist(documentSequenceNumber);
        } else {
            documentSequenceNumber.setYearReceived(year);
            documentSequenceNumber.setMonthReceived(month);
            documentSequenceNumber.setDocumentTypeId(typeId);
            documentSequenceNumber.setSequentialNumber(last + 1);
            em.persist(documentSequenceNumber);
        }

        return documentSequenceNumber.getSequentialNumber();
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
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
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
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

}
