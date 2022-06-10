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
package jm.com.dpbennett.business.entity.pm;

import jm.com.dpbennett.business.entity.dm.DocumentType;
import jm.com.dpbennett.business.entity.dm.Document;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.EmployeePosition;
import jm.com.dpbennett.business.entity.fm.Classification;
import jm.com.dpbennett.business.entity.fm.CostComponent;
import jm.com.dpbennett.business.entity.fm.Currency;
import jm.com.dpbennett.business.entity.dm.Attachment;
import java.io.Serializable;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.fm.Discount;
import jm.com.dpbennett.business.entity.fm.Tax;
import jm.com.dpbennett.business.entity.hrm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "purchaserequisition")
@NamedQueries({
    @NamedQuery(name = "findAllPurchaseRequisitions",
            query = "SELECT p FROM PurchaseRequisition p ORDER BY p.number")
})
public class PurchaseRequisition implements Document, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DocumentType type;
    private String number;
    private Boolean autoGenerateNumber;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department originatingDepartment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department purchasingDepartment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee procurementOfficer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee originator;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> approvers;
    @Column(length = 1024)
    private String description;
    @Column(length = 1024)
    private String notes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date requisitionDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedDateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRequired;
    private String url;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Classification classification;
    @Column(length = 1024)
    private String comments;
    private Long sequenceNumber;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Supplier supplier;
    @Column(length = 1024)
    private String status;
    private String workProgress;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    private String priorityCode;
    private Boolean onHandNow;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<CostComponent> costComponents;
    @Transient
    private Boolean isDirty;
    @Transient
    private Boolean visited;
    @Transient
    private List<BusinessEntity.Action> actions;
    @Transient
    private String editStatus;
    // Approval dates
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvalDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date teamLeaderApprovalDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date divisionalManagerApprovalDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date divisionalDirectorApprovalDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date financeManagerApprovalDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date executiveDirectorApprovalDate;
    // Purchase order detail    
    private String quotationNumber;
    private String purchaseOrderNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date purchaseOrderDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date importLicenceDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deliveryDateRequired;
    private String importLicenceNum;
    @Column(length = 1024)
    private String terms;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Attachment> attachments;
    @Column(length = 1024)
    private String pleaseSupplyNote;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Currency currency;
    // Shipping instructions
    private Boolean airFreight;
    private Boolean surface;
    private Boolean airParcelPost;
    // To be used as special instructions
    @Column(length = 1024)
    private String shippingInstructions;
    // Budget information
    private Boolean budgeted;
    // Recurrent
    private Double budgetedRecurrent;
    private Double yearToDateRecurrent;
    private Double balanceRecurrent;
    // Capital
    private Double budgetedCapital;
    private Double yearToDateCapital;
    private Double balanceCapital;
    // Recoverable
    private Double budgetedRecoverable;
    private Double yearToDateRecoverable;
    private Double balanceRecoverable;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Tax tax;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Discount discount;

    /**
     * Default constructor.
     */
    public PurchaseRequisition() {
        approvers = new ArrayList<>();
        costComponents = new ArrayList<>();
        actions = new ArrayList<>();
        description = "";
    }

    public Tax getTax() {
        return (tax == null ? new Tax() : tax);
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Discount getDiscount() {
        return (discount == null ? new Discount() : discount);
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Boolean getAirFreight() {
        if (airFreight == null) {
            airFreight = false;
        }
        return airFreight;
    }

    public void setAirFreight(Boolean airFreight) {
        this.airFreight = airFreight;
    }

    public Boolean getSurface() {
        if (surface == null) {
            surface = false;
        }
        return surface;
    }

    public void setSurface(Boolean surface) {
        this.surface = surface;
    }

    public Boolean getAirParcelPost() {
        if (airParcelPost == null) {
            airParcelPost = false;
        }
        return airParcelPost;
    }

    public void setAirParcelPost(Boolean airParcelPost) {
        this.airParcelPost = airParcelPost;
    }

    public Boolean getBudgeted() {
        if (budgeted == null) {
            budgeted = false;
        }
        return budgeted;
    }

    public void setBudgeted(Boolean budgeted) {
        this.budgeted = budgeted;
    }

    public Double getBudgetedRecurrent() {
        if (budgetedRecurrent == null) {
            budgetedRecurrent = 0.0;
        }
        return budgetedRecurrent;
    }

    public void setBudgetedRecurrent(Double budgetedRecurrent) {
        this.budgetedRecurrent = budgetedRecurrent;
    }

    public Double getBudgetedCapital() {
        if (budgetedCapital == null) {
            budgetedCapital = 0.0;
        }
        return budgetedCapital;
    }

    public void setBudgetedCapital(Double budgetedCapital) {
        this.budgetedCapital = budgetedCapital;
    }

    public Double getBudgetedRecoverable() {
        if (budgetedRecoverable == null) {
            budgetedRecoverable = 0.0;
        }
        return budgetedRecoverable;
    }

    public void setBudgetedRecoverable(Double budgetedRecoverable) {
        this.budgetedRecoverable = budgetedRecoverable;
    }

    public Double getYearToDateRecurrent() {
        if (yearToDateRecurrent == null) {
            yearToDateRecurrent = 0.0;
        }
        return yearToDateRecurrent;
    }

    public void setYearToDateRecurrent(Double yearToDateRecurrent) {
        this.yearToDateRecurrent = yearToDateRecurrent;
    }

    public Double getYearToDateCapital() {
        if (yearToDateCapital == null) {
            yearToDateCapital = 0.0;
        }
        return yearToDateCapital;
    }

    public void setYearToDateCapital(Double yearToDateCapital) {
        this.yearToDateCapital = yearToDateCapital;
    }

    public Double getYearToDateRecoverable() {
        if (yearToDateRecoverable == null) {
            yearToDateRecoverable = 0.0;
        }
        return yearToDateRecoverable;
    }

    public void setYearToDateRecoverable(Double yearToDateRecoverable) {
        this.yearToDateRecoverable = yearToDateRecoverable;
    }

    public Double getBalanceRecurrent() {
        if (balanceRecurrent == null) {
            balanceRecurrent = 0.0;
        }
        return balanceRecurrent;
    }

    public void setBalanceRecurrent(Double balanceRecurrent) {
        this.balanceRecurrent = balanceRecurrent;
    }

    public Double getBalanceCapital() {
        if (balanceCapital == null) {
            balanceCapital = 0.0;
        }
        return balanceCapital;
    }

    public void setBalanceCapital(Double balanceCapital) {
        this.balanceCapital = balanceCapital;
    }

    public Double getBalanceRecoverable() {
        if (balanceRecoverable == null) {
            balanceRecoverable = 0.0;
        }
        return balanceRecoverable;
    }

    public void setBalanceRecoverable(Double balanceRecoverable) {
        this.balanceRecoverable = balanceRecoverable;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getPleaseSupplyNote() {
        return pleaseSupplyNote;
    }

    public void setPleaseSupplyNote(String pleaseSupplyNote) {
        this.pleaseSupplyNote = pleaseSupplyNote;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }

    public void setShippingInstructions(String shippingInstructions) {
        this.shippingInstructions = shippingInstructions;
    }

    public String getImportLicenceNum() {
        return importLicenceNum;
    }

    public void setImportLicenceNum(String importLicenceNum) {
        this.importLicenceNum = importLicenceNum;
    }

    public Date getDeliveryDateRequired() {
        return deliveryDateRequired;
    }

    public void setDeliveryDateRequired(Date deliveryDateRequired) {
        this.deliveryDateRequired = deliveryDateRequired;
    }

    public Date getImportLicenceDate() {
        return importLicenceDate;
    }

    public void setImportLicenceDate(Date importLicenceDate) {
        this.importLicenceDate = importLicenceDate;
    }

    /**
     * Splits the description into three(3) parts.
     *
     * @param part1Length
     * @param part2Length
     * @param part3Length
     * @return
     */
    public String[] splitDescription(int part1Length, int part2Length, int part3Length) {
        int descriptionLength = getDescription().length();
        String[] descriptionParts = {"", "", ""};

        // Get part 1
        if (part1Length <= descriptionLength) {
            descriptionParts[0] = getDescription().substring(0, part1Length);
        }
        // Get part 2
        if ((part1Length + part2Length) <= descriptionLength) {
            descriptionParts[1] = getDescription().substring(part1Length,
                    (part1Length + part2Length));
        }
        // Get part 3
        if ((part1Length + part2Length + part3Length) <= descriptionLength) {
            descriptionParts[2] = getDescription().substring((part1Length + part2Length),
                    (part1Length + part2Length + part3Length));
        }

        return descriptionParts;
    }

    public Employee getFirstApproverByPositionTitle(String positionTitle) {
        for (Employee approver : approvers) {
            for (EmployeePosition position : approver.getPositions()) {
                if (position.getTitle().equals(positionTitle)) {
                    return approver;
                }
            }
        }

        return null;
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

    public PurchaseRequisition removeAction(BusinessEntity.Action action) {
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

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getTeamLeaderApprovalDate() {
        return teamLeaderApprovalDate;
    }

    public void setTeamLeaderApprovalDate(Date teamLeaderApprovalDate) {
        this.teamLeaderApprovalDate = teamLeaderApprovalDate;
    }

    public Date getDivisionalManagerApprovalDate() {
        return divisionalManagerApprovalDate;
    }

    public void setDivisionalManagerApprovalDate(Date divisionalManagerApprovalDate) {
        this.divisionalManagerApprovalDate = divisionalManagerApprovalDate;
    }

    public Date getDivisionalDirectorApprovalDate() {
        return divisionalDirectorApprovalDate;
    }

    public void setDivisionalDirectorApprovalDate(Date divisionalDirectorApprovalDate) {
        this.divisionalDirectorApprovalDate = divisionalDirectorApprovalDate;
    }

    public Date getFinanceManagerApprovalDate() {
        return financeManagerApprovalDate;
    }

    public void setFinanceManagerApprovalDate(Date financeManagerApprovalDate) {
        this.financeManagerApprovalDate = financeManagerApprovalDate;
    }

    public Date getExecutiveDirectorApprovalDate() {
        return executiveDirectorApprovalDate;
    }

    public void setExecutiveDirectorApprovalDate(Date executiveDirectorApprovalDate) {
        this.executiveDirectorApprovalDate = executiveDirectorApprovalDate;
    }

    public Boolean isApproved(int requiredNumOfApprover) {
        return getApprovers().size() >= requiredNumOfApprover;
    }

    public void clean() {
        setIsDirty(false);
    }

    public String getWorkProgress() {
        if (workProgress == null) {
            workProgress = "Ongoing";
        }
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
    }

    public String generateNumber() {

        Calendar c = Calendar.getInstance();
        String year;
        String sequenceNumberStr;

        // Use the date entered to get the year if it is valid
        if (getRequisitionDate() != null) {
            c.setTime(getRequisitionDate());
            year = "" + c.get(Calendar.YEAR);
        } else {
            year = "" + BusinessEntityUtils.getCurrentYear();
        }
        // include the sequence number if it is valid
        if (getSequenceNumber() != null) {
            sequenceNumberStr = BusinessEntityUtils.getFourDigitString(getSequenceNumber());
        } else {
            sequenceNumberStr = "?";
        }
        // Build the PR number
        number = "PR/" + year + "/" + sequenceNumberStr;

        return number;

    }

    public String generatePurchaseOrderNumber() {

        Calendar c = Calendar.getInstance();
        String year;
        String sequenceNumberStr;

        // Use the date entered to get the year if it is valid
        if (getRequisitionDate() != null) {
            c.setTime(getRequisitionDate());
            year = "" + c.get(Calendar.YEAR);
        } else {
            year = "" + BusinessEntityUtils.getCurrentYear();
        }
        // include the sequence number if it is valid
        if (getSequenceNumber() != null) {
            sequenceNumberStr = BusinessEntityUtils.getFourDigitString(getSequenceNumber());
        } else {
            sequenceNumberStr = "?";
        }
        // Build the PR number
        purchaseOrderNumber = "PO/" + year + "/" + sequenceNumberStr;

        return purchaseOrderNumber;

    }

    // tk placeholder for now
    public Double getTotalCost() {
        Double total = 0.0;

        for (CostComponent component : costComponents) {
            total = total + component.getCost();
        }

        return total;
    }

    /**
     * Builds and return a list of cost components with the costing to which the
     * cost component used as a header cost component belong
     *
     * @return
     */
    public List<CostComponent> getAllSortedCostComponents() {

        Collections.sort(getCostComponents());

        return costComponents;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public String getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(String quotationNumber) {
        this.quotationNumber = quotationNumber;
    }

    public List<BusinessEntity.Action> getActions() {
        return actions;
    }

    public void setActions(List<BusinessEntity.Action> actions) {
        this.actions = actions;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Date getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(Date purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public List<CostComponent> getCostComponents() {
        if (costComponents == null) {
            costComponents = new ArrayList<>();
        }
        return costComponents;
    }

    public void setCostComponents(List<CostComponent> costComponents) {
        this.costComponents = costComponents;
    }

    public List<Attachment> getAllSortedAttachments() {

        Collections.sort(getAttachments());

        return attachments;
    }

    public List<Attachment> getAttachments() {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Date getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(Date dateRequired) {
        this.dateRequired = dateRequired;
    }

    public Boolean getOnHandNow() {
        return onHandNow;
    }

    public void setOnHandNow(Boolean onHandNow) {
        this.onHandNow = onHandNow;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public List<Employee> getApprovers() {
        if (approvers == null) {
            approvers = new ArrayList<>();
        }
        return approvers;
    }

    public void setApprovers(List<Employee> approvers) {
        this.approvers = approvers;
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

    public Boolean getVisited() {
        if (visited == null) {
            visited = false;
        }
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    private Boolean getCompleted() {
        return dateOfCompletion != null;
    }

    public String getRowStyle() {

        if (getVisited()) {
            visited = false;
            return "lightgreybg";
        } else if (getCompleted()) {
            return "lightgreenbg";
        } else if (getExpectedDateOfCompletion() != null) {
            if (BusinessEntityUtils.getNow().compareTo(getExpectedDateOfCompletion()) >= 0) {
                // Due or overdue
                return "orangeredbg";
            } else if (BusinessEntityUtils.getNow().compareTo(BusinessEntityUtils.adjustDate(getExpectedDateOfCompletion(), Calendar.DATE, -3)) >= 0) {
                // Soon due 
                return "yellowbg";
            } else {
                // It's all good!
                return "";
            }

        } else { // EDOC possibly not set so warn
            return "lightyellowbg";
        }
    }

    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    public void setEditedBy(Employee employee) {
        editedBy = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAutoGenerateNumber() {
        if (autoGenerateNumber == null) {
            autoGenerateNumber = true;
        }
        return autoGenerateNumber;
    }

    public void setAutoGenerateNumber(Boolean autoGenerateNumber) {
        this.autoGenerateNumber = autoGenerateNumber;
    }

    public Department getOriginatingDepartment() {
        if (originatingDepartment == null) {
            originatingDepartment = new Department();
        }

        return originatingDepartment;
    }

    public void setOriginatingDepartment(Department originatingDepartment) {
        this.originatingDepartment = originatingDepartment;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Department getPurchasingDepartment() {
        if (purchasingDepartment == null) {
            return new Department();
        }
        return purchasingDepartment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPurchasingDepartment(Department purchasingDepartment) {
        this.purchasingDepartment = purchasingDepartment;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpectedDateOfCompletion() {
        return expectedDateOfCompletion;
    }

    public void setExpectedDateOfCompletion(Date expectedDateOfCompletion) {
        this.expectedDateOfCompletion = expectedDateOfCompletion;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getProcurementOfficer() {
        if (procurementOfficer == null) {
            return new Employee();
        }
        return procurementOfficer;
    }

    public void setProcurementOfficer(Employee procurementOfficer) {
        this.procurementOfficer = procurementOfficer;
    }

    public Employee getOriginator() {
        if (originator == null) {
            return new Employee();
        }
        return originator;
    }

    public void setOriginator(Employee originator) {
        this.originator = originator;
    }

    @Override
    public DocumentType getType() {
        if (type == null) {
            return new DocumentType();
        }
        return type;
    }

    @Override
    public void setType(DocumentType type) {
        this.type = type;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof PurchaseRequisition)) {
            return false;
        }
        PurchaseRequisition other = (PurchaseRequisition) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Purchase Requisition (" + getNumber() + ")";
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public Classification getClassification() {
        if (classification == null) {
            return new Classification();
        }
        return classification;
    }

    @Override
    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Integer getCurrentDocumentActualTurnaroundTime() {
        if (requisitionDate != null && dateOfCompletion != null) {
            return BusinessEntityUtils.calculatePeriodInWorkingDays(requisitionDate, dateOfCompletion);
        } else {
            return 0;
        }
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public void setName(String name) {
    }

    public static List<PurchaseRequisition> findByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate,
            Long departmentId) {

        List<PurchaseRequisition> foundPRs;
        String searchQuery = "";
        String searchTextAndClause;
        String searchText = originalSearchText.replaceAll("'", "''");
        String departmentQuery = "";

        switch (searchType) {
            case "Purchase requisitions":

                if (departmentId != null) {
                    departmentQuery = " AND (originatingDepartment.id = " + departmentId + ")";
                }

                searchTextAndClause
                        = " AND ("
                        + " UPPER(pr.number) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(supplier.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(originatingDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(procurementOfficer.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(procurementOfficer.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(originator.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(originator.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.description) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.notes) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.terms) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.priorityCode) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.url) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )"
                        + departmentQuery;

                searchQuery
                        = "SELECT pr FROM PurchaseRequisition pr"
                        + " JOIN pr.originatingDepartment originatingDepartment"
                        + " JOIN pr.procurementOfficer procurementOfficer"
                        + " JOIN pr.originator originator"
                        + " JOIN pr.supplier supplier"
                        + " WHERE (pr." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND pr." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY pr.id DESC";
                break;
            default:
                break;
        }

        try {
            foundPRs = em.createQuery(searchQuery, PurchaseRequisition.class).getResultList();
            if (foundPRs == null) {
                foundPRs = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundPRs;
    }

    public static PurchaseRequisition findById(EntityManager em, Long Id) {

        return em.find(PurchaseRequisition.class, Id);
    }

    public static PurchaseRequisition findByPRNumber(EntityManager em, String prNumber) {

        try {
            String newPRNumber = prNumber.trim().replaceAll("'", "''");

            List<PurchaseRequisition> purchaseRequisitions = em.createQuery("SELECT p FROM PurchaseRequisition p "
                    + "WHERE UPPER(p.number) "
                    + "= '" + newPRNumber.toUpperCase() + "'", PurchaseRequisition.class).getResultList();

            if (!purchaseRequisitions.isEmpty()) {
                return purchaseRequisitions.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<PurchaseRequisition> findAll(EntityManager em) {

        try {
            return em.createNamedQuery("findAllPurchaseRequisitions", PurchaseRequisition.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getNumber(PurchaseRequisition pr, String prefix) {
        String number = prefix;

        // Append department code
        if (pr.getPurchasingDepartment().getCode() != null) {
            number = number + pr.getPurchasingDepartment().getCode();
        } else {
            number = number + "?";
        }
        // Append seq #
        if (pr.getSequenceNumber() != null) {
            NumberFormat formatter = DecimalFormat.getIntegerInstance();
            formatter.setMinimumIntegerDigits(2);
            number = number + "_" + formatter.format(pr.getSequenceNumber());
        } else {
            number = number + "_?";
        }
        // Append month in the form (MMM) and year in the form (YY).
        if (pr.getRequisitionDate() != null) {
            number = number + "/" + BusinessEntityUtils.getMonthShortFormat(pr.getRequisitionDate())
                    + BusinessEntityUtils.getYearShortFormat(pr.getRequisitionDate(), 2);
        }

        return number;
    }

    @Override
    public ReturnMessage save(EntityManager em) {

        try {

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

            // Save new attachements
            if (!getAttachments().isEmpty()) {
                for (Attachment attachment : getAttachments()) {
                    if ((attachment.getId() == null || attachment.getIsDirty())
                            && !attachment.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Attachment save error occurred",
                                "An error occurred while saving an attachment",
                                Message.SEVERITY_ERROR_NAME);

                    }
                }
            }

            // Save    
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();

        } catch (Exception e) {
            return new ReturnMessage(false,
                    "Purchase Requisition Save Error Occurred!",
                    "An error occurred while saving purchase requisition " + getNumber()
                    + "\n" + e,
                    Message.SEVERITY_ERROR_NAME);
        }

    }

    public ReturnMessage prepareAndSave(EntityManager em, User user) {
        Date now = new Date();
        PurchaseReqNumber nextPurchaseReqNumber = null;

        try {
            // Get employee for later use
            Employee employee = user.getEmployee();

            // Do not save changed PR if it's already marked as completed in the database
            // However, saving is allowed if the user belongs to the "Invoicing department"
            // or is a procurement officer.
            if (getId() != null) {
                PurchaseRequisition pr = PurchaseRequisition.findById(em, getId());
                if (pr.getWorkProgress().equals("Completed")
                        && !user.getEmployee().isProcurementOfficer()) {

                    setIsDirty(false);

                    return new ReturnMessage(false,
                            "Purchase Requisition Cannot Be Saved",
                            "This purchase requisition is marked as completed so changes cannot be saved. You may contact a procurement officer",
                            Message.SEVERITY_ERROR_NAME);
                }
            }

            // Update re the person who last edited/entered the PR etc.
            if (getIsDirty()) {
                setDateEdited(now);
                setEditedBy(employee);
            }

            // Modify number with sequence number if required
            if (getAutoGenerateNumber()) {
                if ((getSequenceNumber() == null)) {
                    nextPurchaseReqNumber = PurchaseReqNumber.
                            findNextPurchaseReqNumber(em,
                                    BusinessEntityUtils.getYearFromDate(getRequisitionDate()));

                    setSequenceNumber(nextPurchaseReqNumber.getSequentialNumber());
                    generateNumber();
                    generatePurchaseOrderNumber();
                    setPurchaseOrderNumber(purchaseOrderNumber);
                } else {
                    generateNumber();
                    generatePurchaseOrderNumber();
                }
            }

            // Finally..save the PR
            ReturnMessage returnMessage = save(em);

            if (returnMessage.isSuccess()) {
                // Save PR sequence number since it was used by this PR
                if (nextPurchaseReqNumber != null) {
                    nextPurchaseReqNumber.save(em);
                }

                clean();
            } else {

                // Reset number if number is set to auto-generate
                if (getAutoGenerateNumber()) {
                    setSequenceNumber(null);
                    generateNumber();
                }

                return new ReturnMessage(false,
                        "Undefined Error!",
                        "An undefined error occurred while saving purchase requisition "
                        + getNumber() + ":\n"
                        + returnMessage.getDetail(),
                        Message.SEVERITY_ERROR_NAME);
            }

        } catch (Exception e) {

            // Reset number if number is set to auto-generate
            if (getAutoGenerateNumber()) {
                setSequenceNumber(null);
                generateNumber();
            }

            return new ReturnMessage(false,
                    "Undefined Error!",
                    "An undefined error occurred while purchase requisition "
                    + getNumber() + ":\n"
                    + e,
                    Message.SEVERITY_ERROR_NAME);
        }

        return new ReturnMessage();
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
