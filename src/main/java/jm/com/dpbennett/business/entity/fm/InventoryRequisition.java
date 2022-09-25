/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<InventoryDisbursement> inventoryDisbursements;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;
    @Transient
    private List<BusinessEntity.Action> actions;

    public InventoryRequisition() {
        actions = new ArrayList<>();
        inventoryDisbursements = new ArrayList<>();
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

    public Employee getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public ReturnMessage prepareAndSave(EntityManager em, User user) {
        Date now = new Date();

        try {
            // Get employee for later use
            Employee employee = user.getEmployee();

            if (getIsDirty()) {
                if (getEnteredBy() == null) {
                    setDateEntered(now);
                    setEnteredBy(employee);
                }

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

        List<InventoryRequisition> foundInventoryRequisitions;
        String searchQuery;
        String searchTextAndClause;
        String selectClause = "SELECT InventoryRequisition FROM InventoryRequisition inventoryRequisition";
        String mainJoinClause
                = " JOIN inventoryRequisition.editedBy editedBy"              
                + " JOIN inventoryRequisition.enteredBy enteredBy";

        if (searchText != null) {
            searchText = searchText.trim().replaceAll("'", "''");
        } else {
            searchText = "";
        }

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
            return null;
        }

        return foundInventoryRequisitions;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

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

    public Employee getEnteredBy() {

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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
}
