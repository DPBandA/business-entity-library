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
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "documenttype")
@XmlRootElement
public class DocumentType implements Comparable, BusinessEntity, Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String code;
    @Transient
    private Boolean isDirty;

    public DocumentType() {
        this.name = "";
        this.type = "";
        this.code = "";
    }

    public DocumentType(String name) {
        this.name = name;
        this.type = "";
        this.code = "";
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof DocumentType)) {
            return false;
        }
        DocumentType other = (DocumentType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.DocumentType[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    public static List<DocumentType> findDocumentTypesByCode(EntityManager em, String code) {

        try {
            List<DocumentType> types
                    = em.createQuery("SELECT d FROM DocumentType d where UPPER(d.code) like '"
                            + code.toUpperCase().trim() + "%' ORDER BY d.code", DocumentType.class).getResultList();
            return types;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<DocumentType> findDocumentTypesByName(EntityManager em, String name) {

        try {
            List<DocumentType> types
                    = em.createQuery("SELECT d FROM DocumentType d where UPPER(d.name) like '"
                            + name.toUpperCase().trim() + "%' ORDER BY d.name", DocumentType.class).getResultList();
            return types;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static DocumentType findDocumentTypeByNameAndCode(EntityManager em, String name, String code) {
        String trimmedName = name.trim();
        String trimmedCode = code.trim();

        try {
            List<DocumentType> types
                    = em.createQuery("SELECT d FROM DocumentType d WHERE d.name = '"
                            + trimmedName + "'" + " AND d.code = '"
                            + trimmedCode + "'"
                            + " ORDER BY d.name", DocumentType.class).getResultList();
            if (types != null) {
                if (!types.isEmpty()) {
                    return types.get(0);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static DocumentType findDocumentTypeById(EntityManager em, Long id) {

        try {
            DocumentType type = em.find(DocumentType.class, id);
            return type;
        } catch (Exception e) {
            return null;
        }
    }

    public static DocumentType findDocumentTypeByName(EntityManager em, String documentTypeName) {

        try {
            String newDocumentTypeName = documentTypeName.trim().replaceAll("'", "''");

            List<DocumentType> documentTypes = em.createQuery("SELECT d FROM DocumentType d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + newDocumentTypeName.toUpperCase() + "'", DocumentType.class).getResultList();
            if (documentTypes.size() > 0) {
                return documentTypes.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public static DocumentType findDocumentTypeByCode(EntityManager em, String documentTypeCode) {

        String newDocumentTypeCode = documentTypeCode.replaceAll("'", "''");

        try {
            List<DocumentType> documentTypes = em.createQuery("SELECT d FROM DocumentType d "
                    + "WHERE d.code "
                    + "= '" + newDocumentTypeCode + "'", DocumentType.class).getResultList();
            if (!documentTypes.isEmpty()) {
                return documentTypes.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }
    
     public static DocumentType findDefaultDepartment(EntityManager em,
            String name) {
        DocumentType documentType = DocumentType.findDocumentTypeByName(em, name);

        if (documentType == null) {
            documentType = new DocumentType(name);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, documentType);
            em.getTransaction().commit();
        }

        return documentType;
    }

    public static List<DocumentType> findAllDocumentTypes(EntityManager em) {

        try {
            List<DocumentType> types = em.createQuery("SELECT t FROM DocumentType t ORDER BY t.name", DocumentType.class).getResultList();

            return types;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            DocumentType documentType = new DocumentType();
            documentType.setName(submittedValue.trim());

            return documentType;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null || value.equals("")) {
            return "";
        } else {
            if (((DocumentType) value).getName() != null) {
                return ((DocumentType) value).getName().replaceAll("&#38;", "&");
            } else {
                return "";
            }
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

        return new ReturnMessage(false, "Document type not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
