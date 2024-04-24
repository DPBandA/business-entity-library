/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.fm;

import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "inventoryrequisition")
public class InventoryRequisition implements Serializable, Comparable, BusinessEntity, Asset {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String type;
    private Boolean prepared;
    private Boolean approved;
    private Boolean received;
    private Boolean issued;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee contactPerson;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRequisitionApproved;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateInventoryReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfRequisition;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateInventoryIssued;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee requisitionApprovedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inventoryReceivedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee requisitionBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inventoryIssuedBy;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<InventoryDisbursement> inventoryDisbursements;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;
    @Transient
    private List<BusinessEntity.Action> actions;
    private String workProgress;

    public InventoryRequisition() {
        actions = new ArrayList<>();
        inventoryDisbursements = new ArrayList<>();
    }

    public Boolean getIssued() {

        if (issued == null) {
            issued = false;
        }
        
        return issued;
    }

    public void setIssued(Boolean issued) {
        this.issued = issued;
    }

    public Boolean getReceived() {

        if (received == null) {
            received = false;
        }

        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Double getTotalQuantityOrdered() {

        Double total = 0.0;

        for (InventoryDisbursement inventoryDisbursement : inventoryDisbursements) {
            total = total + inventoryDisbursement.getQuantityOrdered();
        }

        return total;
    }

    public Double getTotalQuantityReceived() {

        Double total = 0.0;

        for (InventoryDisbursement inventoryDisbursement : inventoryDisbursements) {
            total = total + inventoryDisbursement.getQuantityReceived();
        }

        return total;
    }

    public Double getTotalCost() {

        Double total = 0.0;

        for (InventoryDisbursement inventoryDisbursement : inventoryDisbursements) {
            total = total + inventoryDisbursement.getCost();
        }

        return total;
    }

    public String getWorkProgress() {
        if (workProgress == null) {
            workProgress = "";
        }
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
    }

    public Boolean getPrepared() {

        if (prepared == null) {
            prepared = false;
        }

        return prepared;
    }

    public void setPrepared(Boolean prepared) {
        this.prepared = prepared;
    }

    public Boolean getApproved() {

        if (approved == null) {
            approved = false;
        }

        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Employee getInventoryIssuedBy() {
        if (inventoryIssuedBy == null) {
            return new Employee();
        }

        return inventoryIssuedBy;
    }

    public void setInventoryIssuedBy(Employee inventoryIssuedBy) {
        this.inventoryIssuedBy = inventoryIssuedBy;
    }

    public Date getDateInventoryIssued() {
        return dateInventoryIssued;
    }

    public void setDateInventoryIssued(Date dateInventoryIssued) {
        this.dateInventoryIssued = dateInventoryIssued;
    }

    public Department getDepartment() {
        if (department == null) {
            return new Department("");
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getContactPerson() {

        if (contactPerson == null) {
            return new Employee();
        }

        return contactPerson;
    }

    public void setContactPerson(Employee contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Date getDateRequisitionApproved() {
        return dateRequisitionApproved;
    }

    public void setDateRequisitionApproved(Date dateRequisitionApproved) {
        this.dateRequisitionApproved = dateRequisitionApproved;
    }

    public Date getDateInventoryReceived() {
        return dateInventoryReceived;
    }

    public void setDateInventoryReceived(Date dateInventoryReceived) {
        this.dateInventoryReceived = dateInventoryReceived;
    }

    public Date getDateOfRequisition() {
        return dateOfRequisition;
    }

    public void setDateOfRequisition(Date dateOfRequisition) {
        this.dateOfRequisition = dateOfRequisition;
    }

    public Employee getRequisitionApprovedBy() {
        if (requisitionApprovedBy == null) {
            return new Employee();
        }

        return requisitionApprovedBy;
    }

    public void setRequisitionApprovedBy(Employee requisitionApprovedBy) {
        this.requisitionApprovedBy = requisitionApprovedBy;
    }

    public Employee getInventoryReceivedBy() {

        if (inventoryReceivedBy == null) {
            return new Employee();
        }

        return inventoryReceivedBy;
    }

    public void setInventoryReceivedBy(Employee inventoryReceivedBy) {
        this.inventoryReceivedBy = inventoryReceivedBy;
    }

    public Employee getRequisitionBy() {

        if (requisitionBy == null) {
            return new Employee();
        }

        return requisitionBy;
    }

    public void setRequisitionBy(Employee requisitionBy) {
        this.requisitionBy = requisitionBy;
    }

    public String getCode() {
        if (code == null) {
            code = "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<InventoryDisbursement> getAllSortedInventoryDisbursements() {

        Collections.sort(getInventoryDisbursements());

        return inventoryDisbursements;
    }

    public List<InventoryDisbursement> getInventoryDisbursements() {
        if (inventoryDisbursements == null) {
            inventoryDisbursements = new ArrayList<>();
        }
        return inventoryDisbursements;
    }

    public void setInventoryDisbursements(List<InventoryDisbursement> inventoryDisbursements) {
        this.inventoryDisbursements = inventoryDisbursements;
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
    public Employee getEditedBy() {

        if (editedBy == null) {
            return new Employee();
        }

        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
    }

    @Override
    public Date getDateEntered() {
        return dateEntered;
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public ReturnMessage prepareAndSave(EntityManager em, User user) {
        Date now = new Date();

        try {
            // Get employee for later use
            Employee employee = user.getEmployee();

            if (getIsDirty()) {

                setDateEdited(now);
                setEditedBy(employee);
            }

            ReturnMessage returnMessage = save(em);

            if (returnMessage.isSuccess()) {

                setIsDirty(false);
            } else {

                return new ReturnMessage(false,
                        "Undefined Error!",
                        "An undefined error occurred while saving inventory requisition"
                        + ":\n"
                        + returnMessage.getDetail(),
                        Message.SEVERITY_ERROR_NAME);
            }

        } catch (Exception e) {

            return new ReturnMessage(false,
                    "Undefined Error!",
                    "An undefined error occurred while saving inventory requisition"
                    + ":\n"
                    + e,
                    Message.SEVERITY_ERROR_NAME);
        }

        return new ReturnMessage();
    }

    public List<BusinessEntity.Action> getActions() {
        return actions;
    }

    public void setActions(List<BusinessEntity.Action> actions) {
        this.actions = actions;
    }

    public void addAction(BusinessEntity.Action action) {

        // Just return if the action already exists.
        for (Action existingAction : getActions()) {
            if (existingAction == action) {
                return;
            }
        }
        // Add a new action if possible
        switch (action) {
            case CREATE:
                getActions().add(BusinessEntity.Action.CREATE);
                break;
            case EDIT:
                if ((findAction(BusinessEntity.Action.CREATE) == null)
                        && (findAction(BusinessEntity.Action.APPROVE) == null)
                        && (findAction(BusinessEntity.Action.COMPLETE) == null)) {

                    getActions().add(action);
                }
                break;
            case APPROVE:
                if ((findAction(BusinessEntity.Action.CREATE) == null)
                        && (findAction(BusinessEntity.Action.COMPLETE) == null)) {

                    getActions().clear();
                    getActions().add(BusinessEntity.Action.APPROVE);
                }
                break;
            case RECOMMEND:
                if ((findAction(BusinessEntity.Action.CREATE) == null)
                        && (findAction(BusinessEntity.Action.COMPLETE) == null)) {

                    getActions().clear();
                    getActions().add(BusinessEntity.Action.RECOMMEND);
                }
                break;
            case COMPLETE:
                if ((findAction(BusinessEntity.Action.CREATE) == null)) {
                    getActions().clear();
                    getActions().add(BusinessEntity.Action.COMPLETE);
                }
                break;
            default:
                break;
        }

    }

    public Action findAction(BusinessEntity.Action action) {
        for (Action existingAction : getActions()) {
            if (existingAction == action) {
                return action;
            }
        }

        return null;
    }

    public InventoryRequisition removeAction(BusinessEntity.Action action) {
        int index = 0;

        for (Action existingAction : getActions()) {
            if (existingAction == action) {
                actions.remove(index);

                return this;
            }
            ++index;
        }

        return this;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public static InventoryRequisition findById(EntityManager em, Long Id) {

        return em.find(InventoryRequisition.class, Id);
    }

    public static List<InventoryRequisition> find(
            EntityManager em,
            String searchText,
            Integer maxResults) {

        List<InventoryRequisition> foundInventoryRequisitions = new ArrayList<>();
        String searchQuery;
        String searchTextAndClause;
        String selectClause = "SELECT InventoryRequisition FROM InventoryRequisition inventoryRequisition";
        String mainJoinClause
                = " JOIN inventoryRequisition.editedBy editedBy"
                + " JOIN inventoryRequisition.enteredBy enteredBy";

        String mainSearchWhereClause = " UPPER(inventoryRequisition.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventoryRequisition.code) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventoryRequisition.type) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(editedBy.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(enteredBy.name) LIKE '%" + searchText.toUpperCase() + "%'";

        // Build query     
        searchTextAndClause
                = " WHERE"
                + mainSearchWhereClause;
        searchQuery
                = selectClause
                + mainJoinClause
                + searchTextAndClause
                + " ORDER BY inventoryRequisition.id DESC";

        try {
            if (maxResults == 0) {
                foundInventoryRequisitions = em.createQuery(searchQuery, InventoryRequisition.class).getResultList();
            } else {
                foundInventoryRequisitions = em.createQuery(searchQuery, InventoryRequisition.class).setMaxResults(maxResults).getResultList();
            }
        } catch (Exception e) {
            System.out.println(e);
            return foundInventoryRequisitions;
        }

        return foundInventoryRequisitions;
    }

    public static List<InventoryRequisition> findAllActive(EntityManager em, int maxResults) {

        List<InventoryRequisition> foundIRs;

        try {

            foundIRs = em.createQuery(
                    "SELECT i FROM InventoryRequisition i "
                    + "WHERE i.workProgress != 'Completed' AND i.workProgress != 'Cancelled' "
                    + "ORDER BY i.id DESC",
                    InventoryRequisition.class).setMaxResults(maxResults).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundIRs;
    }

    @Override
    public Date getDateEdited() {
        return dateEdited;
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
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
    public Employee getEnteredBy() {

        if (enteredBy == null) {
            return new Employee();
        }

        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {
        this.enteredBy = enteredBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof InventoryRequisition)) {
            return false;
        }
        InventoryRequisition other = (InventoryRequisition) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {

        name = getInventoryDisbursements().toString();

        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    @Override
    public int compareTo(Object o) {
        String thisIdStr = (this.getId() == null ? "" + Integer.MAX_VALUE : this.getId().toString());
        String oIdStr = (((InventoryRequisition) o).getId() == null ? "" + Integer.MAX_VALUE : ((InventoryRequisition) o).getId().toString());

        return Collator.getInstance().compare(thisIdStr, oIdStr);
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            // Save new/edited cost components
            if (!getInventoryDisbursements().isEmpty()) {
                for (InventoryDisbursement inventoryDisbursement : getInventoryDisbursements()) {
                    if ((inventoryDisbursement.getIsDirty() || inventoryDisbursement.getId() == null)
                            && !inventoryDisbursement.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Inventory disbursement save error occurred",
                                "An error occurred while saving an inventory disbursement",
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

        return new ReturnMessage(false, "Inventory Disbursement not saved");
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
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
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
