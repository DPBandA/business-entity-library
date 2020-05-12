/*
Business Entity Library (BEL) - A foundational library for enterprise applications 
Copyright (C) 2020  D P Bennett & Associates Limited

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
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "statusnote")
@NamedQueries({
    @NamedQuery(name = "findAllStatusNotes",
            query = "SELECT s FROM StatusNote s ORDER BY s.dateCreated DESC")
})
public class StatusNote implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long entityId;
    private Boolean active;
    private String name;
    private String code;
    @Column(length = 1024)
    private String text;
    private String type;
    @Column(length = 1024)
    private String description;
    private String category;
    @Transient
    private Boolean isDirty;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee createdBy;
    

    public StatusNote() {
        active = true;
        name = "";
        code = "";
        text = "";
        type = "";
        description = "";
        category = "";
    }

    public StatusNote(String name) {
        active = true;
        this.name = name;
        code = "";
        text = "";
        type = "";
        description = "";
        category = "";
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static List<StatusNote> findAllStatusNotes(EntityManager em) {

        try {
            List<StatusNote> codes = em.createNamedQuery("findAllStatusNotes", StatusNote.class).getResultList();

            return codes;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<StatusNote> findstatusNotes(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<StatusNote> statusNotes
                    = em.createQuery("SELECT s FROM StatusNote s WHERE UPPER(s.text) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(s.description) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(s.code) LIKE '%" + value.toUpperCase().trim()                          
                            + "%' OR UPPER(s.type) LIKE '%" + value.toUpperCase().trim()                           
                            + "%' ORDER BY s.dateCreated DESC",
                            StatusNote.class).getResultList();
            return statusNotes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<StatusNote> findActiveStatusNotes(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<StatusNote> statusNotes
                    = em.createQuery("SELECT s FROM StatusNote s WHERE (UPPER(s.text) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(s.description) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(s.code) LIKE '%" + value.toUpperCase().trim()                           
                            + "%' OR UPPER(s.type) LIKE '%" + value.toUpperCase().trim()
                            + "%') AND (s.active = 1 OR s.active IS NULL) ORDER BY s.dateCreated DESC",
                            StatusNote.class).getResultList();
            return statusNotes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<StatusNote> findActiveStatusNotesByEntityId(EntityManager em, Long entityId) {

        try {
            
            List<StatusNote> statusNotes
                    = em.createQuery("SELECT s FROM StatusNote s WHERE" 
                            + " s.entityId = " + entityId
                            + " AND (s.active = 1 OR s.active IS NULL) ORDER BY s.id DESC",
                            StatusNote.class).getResultList();
            return statusNotes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof StatusNote)) {
            return false;
        }
        StatusNote other = (StatusNote) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

        return new ReturnMessage(false, "Status note not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
   
}
