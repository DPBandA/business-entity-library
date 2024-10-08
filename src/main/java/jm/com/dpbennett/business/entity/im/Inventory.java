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
package jm.com.dpbennett.business.entity.im;

import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import jm.com.dpbennett.business.entity.fm.Asset;
import jm.com.dpbennett.business.entity.fm.CostComponent;
import jm.com.dpbennett.business.entity.fm.Currency;
import jm.com.dpbennett.business.entity.fm.MarketProduct;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.pm.Supplier;
import jm.com.dpbennett.business.entity.sm.Category;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable, Comparable, BusinessEntity, Asset {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Category category;
    private Double quantity;
    private Double unitCost;
    private Double cost;
    private Double budget;
    private String stockKeepingUnit;
    private String measurementUnit;
    private String valuationMethod;
    @OneToOne(cascade = CascadeType.REFRESH)
    private MarketProduct product;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAcquired;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateChecked;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timeChecked;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Supplier supplier;
    private String batchCode;
    private String dateMark;
    private String status;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Currency currency;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<CostComponent> costComponents;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;
    @Transient
    private List<BusinessEntity.Action> actions;
    @Column(length = 1024)
    private Boolean active;
    private String description;
    @Column(length = 1024)
    private String notes;
    @Column(length = 1024)
    private String comments;
    private String productImageURL;
    private String productURL;
    private Double sellingPrice;
    private String disbursementMethod;
    private String location;
    private Integer lowStockThreshold;

    public Inventory() {
        actions = new ArrayList<>();
        costComponents = new ArrayList<>();
    }

    public Inventory(String name) {
        this.name = name;
        actions = new ArrayList<>();
        costComponents = new ArrayList<>();
    }

    public Integer getLowStockThreshold() {

        if (lowStockThreshold == null) {
            lowStockThreshold = 0;
        }

        return lowStockThreshold;
    }

    public void setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDisbursementMethod() {
        if (disbursementMethod == null) {
            disbursementMethod = "FIFO";
        }

        return disbursementMethod;
    }

    public void setDisbursementMethod(String disbursementMethod) {
        this.disbursementMethod = disbursementMethod;
    }

    public Boolean getShowSellingPrice() {
        return getSellingPrice() != 0.0;
    }

    public Double getSellingPrice() {
        if (sellingPrice == null) {
            sellingPrice = 0.0;
        }

        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getProductImageURL() {
        if (productImageURL == null) {
            productImageURL = "/";
        }

        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    public String getProductURL() {
        if (productURL == null) {
            productURL = "/";
        }

        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public Currency getCurrency() {
        return (currency == null ? new Currency() : currency);
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCurrencySymbol() {
        return getCurrency().getSymbol();
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

    public Double getTotalCost() {
        return getTotalCostComponentCosts();
    }

    public Double getTotalCostComponentCosts() {
        Double total = 0.0;

        for (CostComponent component : costComponents) {
            total = total + component.getCost();
        }

        return total;
    }

    public Double getTotalCostComponentQuantities() {
        Double total = 0.0;

        for (CostComponent component : costComponents) {
            total = total + component.getHoursOrQuantity();
        }

        return total;
    }

    public List<CostComponent> getAllSortedCostComponents() {

        Comparator<CostComponent> comparator
                = (CostComponent costComponent1, CostComponent costComponent2) -> {

                    if (costComponent1.getCostDate() == null
                    || costComponent2.getCostDate() == null) {
                        return 0;
                    }

                    if (costComponent1.getCostDate().before(costComponent2.getCostDate())) {
                        return 1;
                    }

                    if (costComponent1.getCostDate().after(costComponent2.getCostDate())) {
                        return -1;
                    }

                    return 0;

                };

        Collections.sort(getCostComponents(), comparator);

        return costComponents;
    }

    public List<CostComponent> getAllCostComponentsSortedByCostDate() {

        Comparator<CostComponent> comparator
                = (CostComponent costComponent1, CostComponent costComponent2) -> {

                    if (costComponent1.getCostDate() == null
                    || costComponent2.getCostDate() == null) {
                        return 0;
                    }

                    if (costComponent1.getCostDate().before(costComponent2.getCostDate())) {
                        return -1;
                    }

                    if (costComponent1.getCostDate().after(costComponent2.getCostDate())) {
                        return 1;
                    }

                    return 0;

                };

        Collections.sort(getPositiveCostComponents(), comparator);

        return costComponents;
    }

    public List<CostComponent> getCostComponents() {
        if (costComponents == null) {
            costComponents = new ArrayList<>();
        }
        return costComponents;
    }

    public List<CostComponent> getPositiveCostComponents() {

        List<CostComponent> pcc = new ArrayList<>();

        for (CostComponent costComponent : getCostComponents()) {
            if (costComponent.getHoursOrQuantity() > 0) {
                pcc.add(costComponent);
            }
        }

        return pcc;
    }

    public void setCostComponents(List<CostComponent> costComponents) {
        this.costComponents = costComponents;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getBudget() {
        if (budget == null) {
            budget = 0.0;
        }
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
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
                        "An undefined error occurred while saving inventory"
                        + ":\n"
                        + returnMessage.getDetail(),
                        Message.SEVERITY_ERROR_NAME);
            }

        } catch (Exception e) {

            return new ReturnMessage(false,
                    "Undefined Error!",
                    "An undefined error occurred while saving inventory"
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

    public Inventory removeAction(BusinessEntity.Action action) {
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

    public CostComponent findCostComponentById(Long id) {

        for (CostComponent costComponent : getCostComponents()) {
            if (costComponent.getId().equals(id)) {

                return costComponent;
            }
        }

        return null;
    }

    public static Inventory findById(EntityManager em, Long Id) {

        return em.find(Inventory.class, Id);
    }

    public static List<Inventory> findAllByName(EntityManager em,
            String name) {

        try {
            
            name = name.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Inventory> inventory
                    = em.createQuery("SELECT i FROM Inventory i WHERE UPPER(i.name) like '%"
                            + name.toUpperCase() + "%'"
                            + " ORDER BY i.name", Inventory.class).getResultList();

            return inventory;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Inventory findByName(
            EntityManager em, String name) {

        try {
            
            name = name.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Inventory> inventory = em.createQuery("SELECT i FROM Inventory i "
                    + "WHERE UPPER(i.name)" + " = '" + name + "'",
                    Inventory.class).getResultList();
            if (!inventory.isEmpty()) {
                Inventory inventoryItem = inventory.get(0);

                return inventoryItem;
            }
        } catch (Exception e) {
            System.out.println("Error finding inventory: " + e);
            return null;
        }

        return null;
    }
    
    public static Inventory findActiveByName(
            EntityManager em, String name) {

        try {
            
            name = name.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Inventory> inventory = em.createQuery("SELECT i FROM Inventory i "
                    + "WHERE UPPER(i.name)" + " = '" + name + "'"
                    + " AND i.active = 1",
                    Inventory.class).getResultList();
            if (!inventory.isEmpty()) {
                Inventory inventoryItem = inventory.get(0);

                return inventoryItem;
            }
        } catch (Exception e) {
            System.out.println("Error finding inventory: " + e);
            return null;
        }

        return null;
    }

    public static List<Inventory> find(
            EntityManager em,
            String searchText,
            Integer maxResults) {
        
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");

        List<Inventory> foundInventory = new ArrayList<>();
        String searchQuery;
        String searchTextAndClause;
        String selectClause = "SELECT inventory FROM Inventory inventory";
        String mainJoinClause
                = " JOIN inventory.category category"
                + " JOIN inventory.supplier supplier"
                + " JOIN inventory.product product"
                + " JOIN inventory.enteredBy enteredBy";

        String mainSearchWhereClause = " UPPER(category.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(supplier.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(product.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.type) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.stockKeepingUnit) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.measurementUnit) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.valuationMethod) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.batchCode) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.dateMark) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.status) LIKE '%" + searchText.toUpperCase() + "%'";

        // Build query     
        searchTextAndClause
                = " WHERE"
                + mainSearchWhereClause;
        searchQuery
                = selectClause
                + mainJoinClause
                + searchTextAndClause
                + " ORDER BY inventory.id DESC";

        try {
            if (maxResults == 0) {
                foundInventory = em.createQuery(searchQuery, Inventory.class).getResultList();
            } else {
                foundInventory = em.createQuery(searchQuery, Inventory.class).setMaxResults(maxResults).getResultList();
            }
        } catch (Exception e) {
            System.out.println(e);
            return foundInventory;
        }

        return foundInventory;
    }

    public static List<Inventory> findActive(
            EntityManager em,
            String searchText,
            Integer maxResults) {

        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");
        
        List<Inventory> foundInventory = new ArrayList<>();
        String searchQuery;
        String searchTextAndClause;
        String selectClause = "SELECT inventory FROM Inventory inventory";
        String mainJoinClause
                = " JOIN inventory.category category"
                + " JOIN inventory.supplier supplier"
                + " JOIN inventory.product product"
                + " JOIN inventory.enteredBy enteredBy";

        String mainSearchWhereClause = " UPPER(category.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(supplier.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(product.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.type) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.stockKeepingUnit) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.measurementUnit) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.valuationMethod) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.batchCode) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.dateMark) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(inventory.status) LIKE '%" + searchText.toUpperCase() + "%')";

        // Build query     
        searchTextAndClause
                = " WHERE  inventory.active = 1 AND ("
                + mainSearchWhereClause;
        searchQuery
                = selectClause
                + mainJoinClause
                + searchTextAndClause
                + " ORDER BY inventory.name";

        try {
            if (maxResults == 0) {
                foundInventory = em.createQuery(searchQuery, Inventory.class).getResultList();
            } else {
                foundInventory = em.createQuery(searchQuery, Inventory.class).setMaxResults(maxResults).getResultList();
            }
        } catch (Exception e) {
            System.out.println(e);
            return foundInventory;
        }

        return foundInventory;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getValuationMethod() {
        if (valuationMethod == null) {
            valuationMethod = "--";
        }

        return valuationMethod;
    }

    public void setValuationMethod(String valuationMethod) {
        this.valuationMethod = valuationMethod;
    }

    public Double getQuantity() {

        quantity = getTotalCostComponentQuantities();

        return quantity;
    }

    public Double getUnitCost() {
        if (unitCost == null) {
            unitCost = 0.0;
        }
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getCost() {

        cost = getTotalCost();

        return cost;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public Supplier getSupplier() {
        if (supplier == null) {
            supplier = new Supplier();
        }
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public MarketProduct getProduct() {
        if (product == null) {
            product = new MarketProduct();
        }
        return product;
    }

    public void setProduct(MarketProduct product) {
        this.product = product;
    }

    @Override
    public Employee getEnteredBy() {

        return enteredBy;
    }

    @Override
    public void setEnteredBy(Person enteredBy) {
        this.enteredBy = (Employee) enteredBy;
    }

    public Category getInventoryCategory() {
        if (category == null) {
            return new Category();
        }
        return category;
    }

    public void setInventoryCategory(Category category) {
        this.category = category;
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
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {

        name = getProduct().getName();

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

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }

    public String getDateMark() {
        return dateMark;
    }

    public void setDateMark(String dateMark) {
        this.dateMark = dateMark;
    }

    public Date getTimeChecked() {
        return timeChecked;
    }

    public void setTimeChecked(Date timeChecked) {
        this.timeChecked = timeChecked;
    }

    @Override
    public int compareTo(Object o) {
        String thisIdStr = (this.getId() == null ? "" + Integer.MAX_VALUE : this.getId().toString());
        String oIdStr = (((Inventory) o).getId() == null ? "" + Integer.MAX_VALUE : ((Inventory) o).getId().toString());

        return Collator.getInstance().compare(thisIdStr, oIdStr);
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            
            getInventoryCategory().save(em);
            getEnteredBy().save(em);
            getEditedBy().save(em);

            // Save new/edited cost components
            if (!getCostComponents().isEmpty()) {
                for (CostComponent costComponent : getCostComponents()) {
                    if ((costComponent.getIsDirty() || costComponent.getId() == null)
                            && !costComponent.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Cost component save error occurred",
                                "An error occurred while saving a cost component",
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

        return new ReturnMessage(false, "Inventory not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
    public ReturnMessage delete(EntityManager em) {
        return new ReturnMessage();
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

    @Override
    public void setEditedBy(Person person) {
        editedBy = (Employee) person;
    }

    @Override
    public String getCategory() {
        return getInventoryCategory().getName();
    }

    @Override
    public void setCategory(String category) {
        getInventoryCategory().setName(category);
    }

    @Override
    public String getNotes() {
        if (notes == null) {
            notes = "";
        }

        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getComments() {
        if (comments == null) {
            comments = "";
        }

        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }
}
