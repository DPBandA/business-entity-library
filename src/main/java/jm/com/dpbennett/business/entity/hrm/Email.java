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
package jm.com.dpbennett.business.entity.hrm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
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
@Table(name = "email")
@NamedQueries({
    @NamedQuery(name = "findAllEmails", query = "SELECT e FROM Email e ORDER BY e.subject")
})
public class Email implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type; // eg Template
    private String classification;
    private String category;
    private String subject;
    @Column(length = 2048)
    private String content;
    @Column(length = 1024)
    private String description;
    @Column(length = 1024)
    private String templateFile;
    private String contentType;
    private Boolean active;
    private Boolean usePackagedTemplate;
    @Transient
    private Boolean isDirty;

    public Email() {
        name = "";
        type = "Template";
        templateFile = "";
        classification = "";
        category = "";
        subject = "";
        content = "";
        description = "";
        active = true;
        usePackagedTemplate = true;
        isDirty = false;
    }

    public Email(String name) {
        this.name = name;
        type = "Template";
        templateFile = "";
        classification = "";
        category = "";
        subject = "";
        content = "";
        description = "";
        active = true;
        usePackagedTemplate = true;
        isDirty = false;
    }

    public String getContent(String templatePath) {
        try {
            if (usePackagedTemplate) {

                InputStream is = new FileInputStream(getClass().getClassLoader().
                        getResource(templatePath + templateFile).getFile());
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));

                String line = buf.readLine();
                StringBuilder sb = new StringBuilder();

                while (line != null) {
                    sb.append(line).append("\n");
                    line = buf.readLine();
                }

                return sb.toString();

            } else {
                return content;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return "";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Boolean getUsePackagedTemplate() {
        if (usePackagedTemplate == null) {
            usePackagedTemplate = true;
        }
        return usePackagedTemplate;
    }

    public void setUsePackagedTemplate(Boolean usePackagedTemplate) {
        this.usePackagedTemplate = usePackagedTemplate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
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
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Email[id=" + id + "]";
    }

    public static List<Email> findAllEmails(EntityManager em) {

        try {
            List<Email> emails = em.createNamedQuery("findAllEmails", Email.class).getResultList();

            return emails;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Email> findAllActiveEmails(EntityManager em) {

        try {
            return em.createQuery("SELECT e FROM Email e WHERE e.active = 1 ORDER BY e.subject", Email.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static Email findEmailById(EntityManager em, Long Id) {

        try {
            Email email = em.find(Email.class, Id);
            return email;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }
    
    public static Email findEmailByName(EntityManager em, String name) {

        try {
           
            List<Email> emails = em.createQuery("SELECT e FROM Email e "
                    + "WHERE UPPER(e.name) "
                    + "= '" + name.toUpperCase() + "'", Email.class).getResultList();

            if (!emails.isEmpty()) {
                return emails.get(0);
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Email findActiveEmailByName(EntityManager em, String name) {

        try {
           
            List<Email> emails = em.createQuery("SELECT e FROM Email e "
                    + "WHERE e.active = 1 AND UPPER(e.name) "
                    + "= '" + name.toUpperCase() + "'", Email.class).getResultList();

            if (!emails.isEmpty()) {
                return emails.get(0);
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Email findEmailBySubject(EntityManager em, String subject) {

        try {
           
            List<Email> emails = em.createQuery("SELECT e FROM Email e "
                    + "WHERE UPPER(e.subject) "
                    + "= '" + subject.toUpperCase() + "'", Email.class).getResultList();

            if (!emails.isEmpty()) {
                return emails.get(0);
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Email> findEmailsBySubject(EntityManager em, String subject) {

        try {
           
            List<Email> emails
                    = em.createQuery("SELECT e FROM Email e where UPPER(e.subject) like '%"
                            + subject.toUpperCase().trim() + "%' ORDER BY e.subject", Email.class).getResultList();

            return emails;
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static List<Email> findEmails(EntityManager em, String query) {

        try {
           
            List<Email> emails
                    = em.createQuery("SELECT e FROM Email e where UPPER(e.name) like '%" + query.toUpperCase().trim()
                            + "%' OR UPPER(e.subject) like '%" + query.toUpperCase().trim()
                            + "%' OR UPPER(e.description) like '%" + query.toUpperCase().trim()
                            + "%' ORDER BY e.subject", Email.class).getResultList();

            return emails;
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static List<Email> findActiveEmailsBySubject(EntityManager em, String query) {

        try {
           
            List<Email> emails
                    = em.createQuery("SELECT e FROM Email e where e.active = 1 AND UPPER(e.subject) like '%"
                            + query.toUpperCase().trim() + "%' ORDER BY e.subject", Email.class).getResultList();

            return emails;
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static List<Email> findActiveEmailsByCategoryAndSubject(EntityManager em,
            String category, String subject) {

        try {
          
            List<Email> emails
                    = em.createQuery("SELECT e FROM Email e where e.active = 1 AND UPPER(e.subject) like '%"
                            + subject.toUpperCase().trim() + "%'"
                            + " AND UPPER(e.category) like '%"
                            + category.toUpperCase().trim() + "%' ORDER BY e.subject", Email.class).getResultList();

            return emails;
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static List<Email> findActiveEmails(EntityManager em, String query) {

        try {
           
            List<Email> emails
                    = em.createQuery("SELECT e FROM Email e where e.active = 1 AND (UPPER(e.name) like '%" + query.toUpperCase().trim()
                            + "%' OR UPPER(e.subject) like '%" + query.toUpperCase().trim()
                            + "%' OR UPPER(e.description) like '%" + query.toUpperCase().trim()
                            + "%') ORDER BY e.subject", Email.class).getResultList();

            return emails;
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static Email findDefaultEmail(EntityManager em, String subject) {
        Email email = Email.findEmailBySubject(em, subject);

        if (email == null) {
            email = new Email(subject);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, email);
            em.getTransaction().commit();
        }

        return email;
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

        return new ReturnMessage(false, "Email not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
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

}
