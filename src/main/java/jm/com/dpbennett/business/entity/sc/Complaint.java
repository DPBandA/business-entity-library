/*
Business Entity Library (BEL)
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
package jm.com.dpbennett.business.entity.sc;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.cm.Client;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.hrm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "complaint")
public class Complaint implements Comparable, BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee officer;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCorrespondence;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateResolved;
    private String actionTaken;
    @Column(length = 1024)
    private String comments;
    @Column(length = 1024)
    private String complaint;
    @Column(length = 1024)
    private String actions;
    @Column(length = 1024)
    private String actionsResults;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client receivedVia;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client complainant;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<ProductInspection> productInspections;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> referredTo;
    private String jobNumber;
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

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getActionsResults() {
        return actionsResults;
    }

    public void setActionsResults(String actionsResults) {
        this.actionsResults = actionsResults;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public List<Employee> getReferredTo() {
        if (referredTo == null) {
            referredTo = new ArrayList<>();
        }

        return referredTo;
    }

    public void setReferredTo(List<Employee> referredTo) {
        this.referredTo = referredTo;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public List<ProductInspection> getProductInspections() {
        if (productInspections != null) {
            Collections.sort(productInspections);
        } else {
            productInspections = new ArrayList<>();
        }

        return productInspections;
    }

    public void setProductInspections(List<ProductInspection> productInspections) {
        this.productInspections = productInspections;
    }

    public Employee getOfficer() {
        return officer;
    }

    public void setOfficer(Employee officer) {
        this.officer = officer;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
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

    public Client getComplainant() {
        if (complainant == null) {
            return new Client();
        }
        return complainant;
    }

    public void setComplainant(Client complainant) {
        this.complainant = complainant;
    }

    public Client getReceivedVia() {
        if (receivedVia == null) {
            return new Client();
        }
        
        return receivedVia;
    }

    public void setReceivedVia(Client receivedVia) {
        this.receivedVia = receivedVia;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEnteredBy() {
        if (enteredBy == null) {
            return new Employee();
        }

        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getDateOfCorrespondence() {
        return dateOfCorrespondence;
    }

    public void setDateOfCorrespondence(Date dateOfCorrespondence) {
        this.dateOfCorrespondence = dateOfCorrespondence;
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
        if (!(object instanceof Complaint)) {
            return false;
        }
        Complaint other = (Complaint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getId().toString(), ((Complaint) o).getId().toString());
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

    public static List<Complaint> findDocumentInspectionsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {

        List<Complaint> foundDocumentInspections;
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
                searchTextAndClause
                        = " AND ("
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
                searchQuery
                        = "SELECT documentInspection FROM DocumentInspection documentInspection"
                        + joinClause
                        + " WHERE (0 = 0)" // used as place holder
                        + searchTextAndClause
                        //                        + " GROUP BY documentInspection.id"
                        + " ORDER BY documentInspection.id DESC";
            } else {
                searchQuery
                        = "SELECT documentInspection FROM DocumentInspection documentInspection"
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
            foundDocumentInspections = em.createQuery(searchQuery, Complaint.class).getResultList();
            if (foundDocumentInspections == null) {
                foundDocumentInspections = new ArrayList<Complaint>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundDocumentInspections;
    }

    public static Complaint findDocumentInspectionById(EntityManager em, Long Id) {

        try {
            Complaint documentInspection = em.find(Complaint.class, Id);
            return documentInspection;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            // Save product inspections
            if (!getProductInspections().isEmpty()) {
                for (ProductInspection productInspection : getProductInspections()) {
                    if ((productInspection.getIsDirty() || productInspection.getId() == null)
                            && !productInspection.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Product save error occurred",
                                "An error occurred while saving a product",
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Complaint not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
