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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
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
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "attachment")
@NamedQueries({
    @NamedQuery(name = "findAllAttachments", query = "SELECT a FROM Attachment a ORDER BY a.name")
})
public class Attachment implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String sourceURL;
    private String destinationURL;
    private String contentType;
    private String documentType;
    private Boolean active;
    @Column(length = 1024)
    private String description;
    private String category;
    @Transient
    private Boolean isDirty;

    public Attachment() {

        this.active = true;
        this.name = "";
        this.description = "";
        this.category = "";
        this.sourceURL = "";
        this.destinationURL = "";
        this.contentType = "";
        this.type = "";
        this.isDirty = false;
        this.documentType = "Other";
    }

    public Attachment(String name,
            String sourceURL,
            String destinationURL,
            String contentType) {

        this.active = true;
        this.name = name;
        this.sourceURL = sourceURL;
        this.destinationURL = destinationURL;
        this.contentType = contentType;
        this.description = "";
        this.category = "";
        this.type = "";
        this.isDirty = false;
        this.documentType = "Other";
    }

    public String getDocumentType() {
        if (documentType == null) {
            documentType = "";
        }
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String getType() {
        if (type == null) {
            type = "";
        }
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDestinationURL() {
        return destinationURL;
    }

    public void setDestinationURL(String destinationURL) {
        this.destinationURL = destinationURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
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
    public String getCategory() {
        if (category == null) {
            category = "";
        }
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Attachment> findAllAttachments(EntityManager em) {

        try {
            List<Attachment> classfications = em.createNamedQuery("findAllAttachments", Attachment.class).getResultList();

            return classfications;
        } catch (Exception e) {

            return null;
        }

    }

    public static Attachment findAttachmentById(EntityManager em, Long id) {

        try {
            Attachment attachment = em.find(Attachment.class, id);

            return attachment;
        } catch (Exception e) {

            return null;
        }
    }

    public static Attachment findAttachmentByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Attachment> attachments = em.createQuery("SELECT a FROM Attachment a "
                    + "WHERE UPPER(a.name) "
                    + "= '" + value.toUpperCase() + "'", Attachment.class).getResultList();
            if (!attachments.isEmpty()) {
                return attachments.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static List<Attachment> findAttachmentsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Attachment> attachments
                    = em.createQuery("SELECT a FROM Attachment a where UPPER(a.name) like '%"
                            + value.toUpperCase().trim() // tk max results to be made system option
                            + "%' ORDER BY a.name", Attachment.class).setMaxResults(100).getResultList();
            return attachments;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Attachment> findAttachmentsByNameAndCategory(EntityManager em, String value, String category) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Attachment> attachments
                    = em.createQuery("SELECT a FROM Attachment a where UPPER(a.name) like '%"
                            + value.toUpperCase().trim() + "%' AND a.category = " + category + " ORDER BY a.name", Attachment.class).getResultList();
            return attachments;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
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

        return new ReturnMessage(false, "Attachment not saved");
    }

    public boolean deleteFile() {

        return new File(destinationURL).delete();

    }

    public InputStream getFileInputStream() {
        File file = new File(destinationURL);
        try {
            InputStream inputStream = new FileInputStream(file);

            return inputStream;
        } catch (FileNotFoundException ex) {
            System.out.println("File attachment not found!");
        }

        return null;
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

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), ((Attachment) o).toString());
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
