/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.im;

import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
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
import jm.com.dpbennett.business.entity.fm.Asset;
import jm.com.dpbennett.business.entity.fm.CostComponent;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "inventorydisbursement")
public class InventoryDisbursement implements Serializable, Comparable, BusinessEntity, Asset {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Inventory inventory;
    private Double quantityOrdered;
    private Double quantityReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOrdered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReceived;
    private Double unitCost;
    private Double cost;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    private String status;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Column(length = 1024)
    private String description;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;
    @Transient
    private List<BusinessEntity.Action> actions;
    @OneToOne(cascade = CascadeType.REFRESH)
    private CostComponent costComponent;

    public InventoryDisbursement() {
        quantityOrdered = 1.0;
        quantityReceived = 1.0;
        unitCost = 0.0;
        cost = 0.0;

        actions = new ArrayList<>();
    }

    public void updateCostComponent() {

        getCostComponent().setDescription(getDescription());
        getCostComponent().setHoursOrQuantity(-getQuantityReceived());
        getCostComponent().setUnit(getInventory().getMeasurementUnit());
        getCostComponent().setCostDate(new Date());
        getCostComponent().setRate(getUnitCost());
        getCostComponent().setCost(-getUnitCost() * getQuantityReceived());

    }

    public CostComponent getCostComponent() {

        if (costComponent == null) {
            costComponent = new CostComponent();
        }

        return costComponent;
    }

    public void setCostComponent(CostComponent costComponent) {
        this.costComponent = costComponent;
    }

    public void update() {

        setCost(quantityReceived * unitCost);

        updateCostComponent();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Inventory getInventory() {
        if (inventory == null) {
            Inventory invtry = new Inventory();

            return invtry;
        }
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Double getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(Double quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    @Override
    public Employee getEditedBy() {
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
                        "An undefined error occurred while saving disbursement"
                        + ":\n"
                        + returnMessage.getDetail(),
                        Message.SEVERITY_ERROR_NAME);
            }

        } catch (Exception e) {

            return new ReturnMessage(false,
                    "Undefined Error!",
                    "An undefined error occurred while saving disbursement"
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

    public InventoryDisbursement removeAction(BusinessEntity.Action action) {
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

    public static InventoryDisbursement findById(EntityManager em, Long Id) {

        return em.find(InventoryDisbursement.class, Id);
    }

    public static List<InventoryDisbursement> find(
            EntityManager em,
            String searchText,
            Integer maxResults) {
        
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");

        List<InventoryDisbursement> foundInventoryDisbursements;
        String searchQuery;
        String searchTextAndClause;
        String selectClause = "SELECT inventoryDisbursement FROM InventoryDisbursement inventoryDisbursement";
        String mainJoinClause
                = " JOIN inventoryDisbursement.inventory inventory"
                + " JOIN inventoryDisbursement.enteredBy enteredBy"
                + " JOIN inventoryDisbursement.editedBy editedBy";

        String mainSearchWhereClause = " UPPER(inventoryDisbursement.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventoryDisbursement.type) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventoryDisbursement.status) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventoryDisbursement.description) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(enteredBy.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(editedBy.name) LIKE '%" + searchText.toUpperCase() + "%'";

        // Build query     
        searchTextAndClause
                = " WHERE"
                + mainSearchWhereClause;
        searchQuery
                = selectClause
                + mainJoinClause
                + searchTextAndClause
                + " ORDER BY inventoryDisbursement.id DESC";

        try {
            if (maxResults == 0) {
                foundInventoryDisbursements = em.createQuery(searchQuery, InventoryDisbursement.class).getResultList();
            } else {
                foundInventoryDisbursements = em.createQuery(searchQuery, InventoryDisbursement.class).setMaxResults(maxResults).getResultList();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundInventoryDisbursements;
    }

    public Double getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Double quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Double getCost() {

        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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

        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof InventoryDisbursement)) {
            return false;
        }
        InventoryDisbursement other = (InventoryDisbursement) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {

        name = getInventory().getName();

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
        String oIdStr = (((InventoryDisbursement) o).getId() == null ? "" + Integer.MAX_VALUE : ((InventoryDisbursement) o).getId().toString());

        return Collator.getInstance().compare(thisIdStr, oIdStr);
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            
            getEnteredBy().save(em);
            getEditedBy().save(em);
           
            // tk check the purpose
            if (getInventory().findCostComponentById(getCostComponent().getId()) == null) {
                getInventory().getCostComponents().add(getCostComponent());
                getInventory().save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Inventory Disbusement not saved");
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
