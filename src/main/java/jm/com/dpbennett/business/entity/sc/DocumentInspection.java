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

package jm.com.dpbennett.business.entity.sc;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.cm.Client;
import java.text.Collator;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "documentinspection")
public class DocumentInspection implements Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inspector;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfInspection;
    private String portOfEntry;
    private String actionTaken;
    @Column(length = 1024)
    private String comments;
    @OneToOne(cascade = CascadeType.ALL)
    private Client consignee;
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

    public Client getConsignee() {
        if (consignee == null) {
            consignee = new Client("", false);
        }
        return consignee;
    }

    public void setConsignee(Client consignee) {
        this.consignee = consignee;
    }

    public String getPortOfEntry() {
        return portOfEntry;
    }

    public void setPortOfEntry(String portOfEntry) {
        this.portOfEntry = portOfEntry;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public Employee getInspector() {
        if (inspector == null) {
            return new Employee();
        }

        return inspector;
    }

    public void setInspector(Employee inspector) {
        this.inspector = inspector;
    }

    public Date getDateOfInspection() {
        return dateOfInspection;
    }

    public void setDateOfInspection(Date dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
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
        if (!(object instanceof DocumentInspection)) {
            return false;
        }
        DocumentInspection other = (DocumentInspection) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getId().toString(), ((DocumentInspection) o).getId().toString());
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

    public static List<DocumentInspection> findDocumentInspectionsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {

        List<DocumentInspection> foundDocumentInspections;
        String searchQuery = null;
        String searchTextAndClause = "";
        String joinClause;
        String searchText;

        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''");
        } else {
            searchText = "";
        }

        joinClause = " JOIN documentInspection.inspector inspector";

        if (searchType.equals("General")) {
            if (!searchText.equals("")) {
                searchTextAndClause =
                        " AND ("
                        + " UPPER(documentInspection.portOfEntry) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(inspector.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(inspector.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(documentInspection.type) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(documentInspection.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(documentInspection.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(documentInspection.actionTaken) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
            }
            if ((startDate == null) || (endDate == null)) {
                searchQuery =
                        "SELECT documentInspection FROM DocumentInspection documentInspection"
                        + joinClause
                        + " WHERE (0 = 0)" // used as place holder
                        + searchTextAndClause
                        //                        + " GROUP BY documentInspection.id"
                        + " ORDER BY documentInspection.id DESC";
            } else {
                searchQuery =
                        "SELECT documentInspection FROM DocumentInspection documentInspection"
                        + joinClause
                        + " WHERE (documentInspection." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND documentInspection." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        //                        + " GROUP BY complianceSurvey.id"
                        + " ORDER BY documentInspection.id DESC";
            }
        } else if (searchType.equals("?")) {
        }

        try {
            foundDocumentInspections = em.createQuery(searchQuery, DocumentInspection.class).getResultList();
            if (foundDocumentInspections == null) {
                foundDocumentInspections = new ArrayList<DocumentInspection>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundDocumentInspections;
    }
    
    

    public static DocumentInspection findDocumentInspectionById(EntityManager em, Long Id) {

        try {
            DocumentInspection documentInspection = em.find(DocumentInspection.class, Id);
            return documentInspection;
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

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
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
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
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
