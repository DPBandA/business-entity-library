/*
Business Entity Library (BEL)
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
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import jm.com.dpbennett.business.entity.hrm.Department;
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
public class Complaint implements Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee receivedBy;
    private String complaintOfficer;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCorrespondence;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateResolved;
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
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Department> referredToDepartment;
    private String jobNumber;
    private Boolean internal;
    private String workProgress;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;

    public Complaint() {
        internal = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getIsJobNumberValid() {
        return !getJobNumber().isEmpty();
    }

    public static List<Object[]> getReportRecords(
            EntityManager em,
            String startDate,
            String endDate,
            Long departmentId) {

        String reportSQL = "SELECT DISTINCT"
                + "     complaint.`JOBNUMBER`," // 0 - Job number 
                + "     businessoffice.`NAME`," // 1 - Business office
                + "     complaint.`COMMENTS`," // 2 - Comments                  
                + "     enteredBy.`NAME`," // 3 - Entered by     
                + "     complaint.`DATERECEIVED`," // 4 - Date received
                + "     complaint.`COMPLAINT`," // 5 - Complaint
                + "     complainant.`NAME`," // 6 - Complainant  
                + "     complainant.`ID`," // 7 - ID   
                + "     complaint.`WORKPROGRESS`" // 8 - Work progress
                + " FROM"
                + "     complaint"
                + "     LEFT JOIN `businessoffice` businessoffice ON complaint.`BUSINESSOFFICE_ID` = businessoffice.`ID`"
                + "     LEFT JOIN `employee` enteredBy ON complaint.`ENTEREDBY_ID` = enteredBy.`ID`"
                + "     LEFT JOIN `client` complainant ON complaint.`COMPLAINANT_ID` = complainant.`ID`"
                + " WHERE"
                + "     (complaint.`DATERECEIVED` >= " + startDate
                + " AND complaint.`DATERECEIVED` <= " + endDate + ")"
                + " GROUP BY"
                + "     complaint.`ID`"
                + " ORDER BY"
                + "     complaint.`ID` DESC";

        try {
            return em.createNativeQuery(reportSQL).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    public BusinessOffice getBusinessOffice() {
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public String getWorkProgress() {
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public List<Department> getReferredToDepartment() {

        if (referredToDepartment == null) {
            referredToDepartment = new ArrayList<>();
        }

        return referredToDepartment;
    }

    public void setReferredToDepartment(List<Department> referredToDepartment) {
        this.referredToDepartment = referredToDepartment;
    }

    public String getComplaintOfficer() {
        return complaintOfficer;
    }

    public void setComplaintOfficer(String complaintOfficer) {
        this.complaintOfficer = complaintOfficer;
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
        if (jobNumber == null) {
            jobNumber = "";
        }

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

    public Employee getReceivedBy() {
        if (receivedBy == null) {
            return new Employee();
        }
        return receivedBy;
    }

    public void setReceivedBy(Employee receivedBy) {
        this.receivedBy = receivedBy;
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

        if (isDirty) {
            setEditStatus("(edited)");
        } else {
            setEditStatus("        ");
        }
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

    @Override
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
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

    public static List<Complaint> findAllComplaints(EntityManager em) {

        try {

            return em.createQuery("SELECT c FROM Complaint c ORDER BY c.id DESC",
                    Complaint.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static List<Complaint> findComplaintsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String searchText,
            Date startDate,
            Date endDate,
            int maxResults) {

        List<Complaint> foundComplaints;
        String searchQuery = "";
        String searchTextAndClause = "";
        String joinClause;

        joinClause = " LEFT JOIN complaint.enteredBy enteredBy"
                + " LEFT JOIN complaint.receivedBy receivedBy"
                + " LEFT JOIN complaint.productInspections productInspections"
                + " LEFT JOIN complaint.referredTo referredTo"
                + " LEFT JOIN complaint.complainant complainant"
                + " LEFT JOIN complaint.receivedVia receivedVia";

        if (searchType.equals("General")) {
            if (!searchText.equals("")) {
                searchTextAndClause
                        = " AND ("
                        + " UPPER(enteredBy.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(enteredBy.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(receivedBy.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(receivedBy.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(referredTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(referredTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.complaintOfficer) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.workProgress) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.actionsResults) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.actions) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(complaint.complaint) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
            }
            if ((startDate == null) || (endDate == null)) {

                searchQuery
                        = "SELECT DISTINCT complaint FROM Complaint complaint"
                        + joinClause
                        + " WHERE (0 = 0)"
                        + searchTextAndClause
                        + " ORDER BY complaint.id DESC";
            } else {
                searchQuery
                        = "SELECT DISTINCT complaint FROM Complaint complaint"
                        + joinClause
                        + " WHERE (complaint." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND complaint." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY complaint.id DESC";
            }
        } else if (searchType.equals("?")) {
        }

        try {
            foundComplaints = em.createQuery(searchQuery, Complaint.class)
                    .setMaxResults(maxResults).getResultList();
            if (foundComplaints == null) {
                foundComplaints = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }

        return foundComplaints;
    }

    public static Complaint findComplaintById(EntityManager em, Long Id) {

        try {

            return em.find(Complaint.class, Id);
        } catch (Exception e) {
            System.out.println(e);

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
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
