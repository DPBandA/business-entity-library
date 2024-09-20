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
package jm.com.dpbennett.business.entity.pm;

import jm.com.dpbennett.business.entity.dm.DocumentType;
import jm.com.dpbennett.business.entity.dm.Document;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.fm.Classification;
import jm.com.dpbennett.business.entity.fm.CostComponent;
import jm.com.dpbennett.business.entity.fm.Currency;
import jm.com.dpbennett.business.entity.dm.Attachment;
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
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.fm.Discount;
import jm.com.dpbennett.business.entity.fm.Tax;
import jm.com.dpbennett.business.entity.hrm.ApproverOrRecommender;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.sm.User;
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
            query = "SELECT p FROM PurchaseRequisition p ORDER BY p.id DESC")
})
public class PurchaseRequisition implements Document, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DocumentType documentType;
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
    // # of approvers    
    private Integer approvals;
    // TEAM LEADER
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approver1;
    // DIVISIONAL MANAGER
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approver2;
    // DIVISIONAL DIRECTOR
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approver3;
    // FINANCE DIRECTOR
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approver4;
    // EXECUTIVE DIRECTOR
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approver5;
    // # of Recommenders
    private Integer recommendations;
    // TEAM LEADER
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recommender1;
    // DIVISIONAL MANAGER
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recommender2;
    // DIVISIONAL DIRECTOR
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recommender3;
    // FINANCE DIRECTOR
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recommender4;
    // EXECUTIVE DIRECTOR
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recommender5;
    @Transient
    private ArrayList<ApproverOrRecommender> approversAndRecommenders;
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
    private Long purchaseOrderSequenceNumber;
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
    private Date approvalOrRecommendationDate1;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvalOrRecommendationDate2;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvalOrRecommendationDate3;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvalOrRecommendationDate4;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvalOrRecommendationDate5;
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
    private String procurementMethod;
    private Double currencyExchangeRate;

    public PurchaseRequisition() {
        costComponents = new ArrayList<>();
        approversAndRecommenders = new ArrayList<>();
        attachments = new ArrayList<>();
        actions = new ArrayList<>();
        description = "";
    }

    public Double getCurrencyExchangeRate() {

        if (currencyExchangeRate == null) {
            currencyExchangeRate = 1.0;
        }

        return currencyExchangeRate;
    }

    public void setCurrencyExchangeRate(Double currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;

        for (CostComponent costComponent : costComponents) {
            costComponent.setCurrencyExchangeRate(currencyExchangeRate);
            costComponent.setCurrency(currency);
            costComponent.setIsDirty(true);
        }
    }

    public static PurchaseRequisition create(EntityManager em, User user) {

        String defaultCurrencyName = SystemOption.getString(em,
                "defaultCurrency");
        Currency defaultCurrency = Currency.findByName(em, defaultCurrencyName);

        PurchaseRequisition selectedPurchaseRequisition = new PurchaseRequisition();
        selectedPurchaseRequisition.setPurchasingDepartment(Department.findDefault(em, "--"));
        selectedPurchaseRequisition.setProcurementOfficer(Employee.findDefault(em,
                "--", "--", false));
        selectedPurchaseRequisition.
                setOriginatingDepartment(user.getEmployee().getDepartment());
        selectedPurchaseRequisition.setProcurementMethod(SystemOption.getString(em,
                "defaultProcurementMethod"));
        selectedPurchaseRequisition.setOriginator(user.getEmployee());
        selectedPurchaseRequisition.setRequisitionDate(new Date());
        if (selectedPurchaseRequisition.getAutoGenerateNumber()) {
            selectedPurchaseRequisition.generateNumber();
        }
        selectedPurchaseRequisition.addAction(BusinessEntity.Action.CREATE);
        selectedPurchaseRequisition.setTax(Tax.findDefault(em, "0.0"));
        selectedPurchaseRequisition.setDiscount(Discount.findDefault(em, "0.0"));
        selectedPurchaseRequisition.setCurrency(defaultCurrency);
        selectedPurchaseRequisition.setIsDirty(true);

        return selectedPurchaseRequisition;
    }

    public static PurchaseRequisition create(
            EntityManager em,
            EntityManager hrmem,
            EntityManager smem,
            User user) {

        String defaultCurrencyName = SystemOption.getString(em,
                "defaultCurrency");
        Currency defaultCurrency = Currency.findByName(em, defaultCurrencyName);

        PurchaseRequisition selectedPurchaseRequisition = new PurchaseRequisition();
        selectedPurchaseRequisition.setPurchasingDepartment(Department.findDefault(hrmem, "--"));
        selectedPurchaseRequisition.setProcurementOfficer(Employee.findDefault(hrmem,
                "--", "--", false));
        selectedPurchaseRequisition.
                setOriginatingDepartment(user.getEmployee().getDepartment());
        selectedPurchaseRequisition.setProcurementMethod(SystemOption.getString(smem,
                "defaultProcurementMethod"));
        selectedPurchaseRequisition.setOriginator(user.getEmployee());
        selectedPurchaseRequisition.setRequisitionDate(new Date());
        if (selectedPurchaseRequisition.getAutoGenerateNumber()) {
            selectedPurchaseRequisition.generateNumber();
        }
        selectedPurchaseRequisition.addAction(BusinessEntity.Action.CREATE);
        selectedPurchaseRequisition.setTax(Tax.findDefault(em, "0.0"));
        selectedPurchaseRequisition.setDiscount(Discount.findDefault(em, "0.0"));
        selectedPurchaseRequisition.setCurrency(defaultCurrency);
        selectedPurchaseRequisition.setIsDirty(true);

        return selectedPurchaseRequisition;
    }

    public String getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(String procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public Boolean hasJustification() {
        for (Attachment attachment : attachments) {
            if (attachment.getDocumentType().equalsIgnoreCase("Justification")) {
                return true;
            }
        }

        return false;
    }

    public Integer getApprovals() {
        if (approvals == null) {
            approvals = 0;
        }
        return approvals;
    }

    public void setApprovals(Integer approvals) {
        this.approvals = approvals;
    }

    public Integer getRecommendations() {
        if (recommendations == null) {
            recommendations = 0;
        }
        return recommendations;
    }

    public void setRecommendations(Integer recommendations) {
        this.recommendations = recommendations;
    }

    public Employee getApprover1() {
        return approver1;
    }

    public void setApprover1(Employee approver1) {
        this.approver1 = approver1;
    }

    public Employee getApprover2() {
        return approver2;
    }

    public void setApprover2(Employee approver2) {
        this.approver2 = approver2;
    }

    public Employee getApprover3() {
        return approver3;
    }

    public void setApprover3(Employee approver3) {
        this.approver3 = approver3;
    }

    public Employee getApprover4() {
        return approver4;
    }

    public void setApprover4(Employee approver4) {
        this.approver4 = approver4;
    }

    public Employee getApprover5() {
        return approver5;
    }

    public void setApprover5(Employee approver5) {
        this.approver5 = approver5;
    }

    public Employee getRecommender1() {
        return recommender1;
    }

    public void setRecommender1(Employee recommender1) {
        this.recommender1 = recommender1;
    }

    public Employee getRecommender2() {
        return recommender2;
    }

    public void setRecommender2(Employee recommender2) {
        this.recommender2 = recommender2;
    }

    public Employee getRecommender3() {
        return recommender3;
    }

    public void setRecommender3(Employee recommender3) {
        this.recommender3 = recommender3;
    }

    public Employee getRecommender4() {
        return recommender4;
    }

    public void setRecommender4(Employee recommender4) {
        this.recommender4 = recommender4;
    }

    public Employee getRecommender5() {
        return recommender5;
    }

    public void setRecommender5(Employee recommender5) {
        this.recommender5 = recommender5;
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

    public String getCurrencySymbol() {
        return getCurrency().getSymbol();
    }

    public Currency getCurrency() {
        return (currency == null ? new Currency() : currency);
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getPleaseSupplyNote() {
        if (pleaseSupplyNote == null) {
            pleaseSupplyNote = "";
        }

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

    public Date getApprovalOrRecommendationDate1() {
        return approvalOrRecommendationDate1;
    }

    public void setApprovalOrRecommendationDate1(Date approvalOrRecommendationDate1) {
        this.approvalOrRecommendationDate1 = approvalOrRecommendationDate1;
    }

    public Date getApprovalOrRecommendationDate2() {
        return approvalOrRecommendationDate2;
    }

    public void setApprovalOrRecommendationDate2(Date approvalOrRecommendationDate2) {
        this.approvalOrRecommendationDate2 = approvalOrRecommendationDate2;
    }

    public Date getApprovalOrRecommendationDate3() {
        return approvalOrRecommendationDate3;
    }

    public void setApprovalOrRecommendationDate3(Date approvalOrRecommendationDate3) {
        this.approvalOrRecommendationDate3 = approvalOrRecommendationDate3;
    }

    public Date getApprovalOrRecommendationDate4() {
        return approvalOrRecommendationDate4;
    }

    public void setApprovalOrRecommendationDate4(Date approvalOrRecommendationDate4) {
        this.approvalOrRecommendationDate4 = approvalOrRecommendationDate4;
    }

    public Date getApprovalOrRecommendationDate5() {
        return approvalOrRecommendationDate5;
    }

    public void setApprovalOrRecommendationDate5(Date approvalOrRecommendationDate5) {
        this.approvalOrRecommendationDate5 = approvalOrRecommendationDate5;
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
            sequenceNumberStr = BusinessEntityUtils.getIntegerString(getSequenceNumber(), 6);
        } else {
            sequenceNumberStr = "?";
        }
        // Build the PR number
        number = "P" + sequenceNumberStr + "/"
                + year.substring(year.length() - 2, year.length());

        return number;

    }

    public String generatePurchaseOrderNumber() {

        String purchaseOrderSequenceNumberStr;

        // include the sequence number if it is valid
        if (getPurchaseOrderSequenceNumber() != null) {
            purchaseOrderSequenceNumberStr = BusinessEntityUtils.getIntegerString(getPurchaseOrderSequenceNumber(), 6);
        } else {
            purchaseOrderSequenceNumberStr = "?";
        }

        // Build the PO number
        purchaseOrderNumber = purchaseOrderSequenceNumberStr;

        return purchaseOrderNumber;

    }

    public Double getTotalTax() {

        if (getTax().getTaxValueType().equals("Percentage")) {
            return getTotalCostWithDiscount() * getTax().getValue();
        } else {
            return getTax().getValue();
        }

    }

    public Double getTotalCostComponentCosts() {
        Double total = 0.0;

        for (CostComponent component : costComponents) {
            total = total + component.getCost();
        }

        return total;
    }

    public Double getConvertedTotalCostComponentCosts() {
        Double total = 0.0;

        for (CostComponent component : costComponents) {
            total = total + component.getConvertedCost();
        }

        return total;
    }

    private Double getTotalCostWithDiscount() {
        Double totalCostWithDiscount;

        if (getDiscount().getDiscountValueType().equals("Percentage")) {
            totalCostWithDiscount = getTotalCostComponentCosts()
                    - getTotalCostComponentCosts() * getDiscount().getValue();
        } else {
            totalCostWithDiscount = getTotalCostComponentCosts() - getDiscount().getValue();
        }

        return totalCostWithDiscount;
    }

    private Double getConvertedTotalCostWithDiscount() {
        Double convertedTotalCostWithDiscount;

        if (getDiscount().getDiscountValueType().equals("Percentage")) {
            convertedTotalCostWithDiscount = getConvertedTotalCostComponentCosts()
                    - getConvertedTotalCostComponentCosts() * getDiscount().getValue();
        } else {
            convertedTotalCostWithDiscount = getConvertedTotalCostComponentCosts() - getDiscount().getValue();
        }

        return convertedTotalCostWithDiscount;
    }

    public Double getTotalCost() {

        return getTotalCostWithDiscount() + getTotalTax();
    }

    public Double getConvertedTotalCost() {

        return getConvertedTotalCostWithDiscount() + getTotalTax();
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
        if (purchaseOrderNumber == null) {
            return "--";
        }

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

    @Override
    public Date getDateEdited() {
        return dateEdited;
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public Boolean hasRecommender() {
        if (recommender1 != null) {
            return true;
        }
        if (recommender2 != null) {
            return true;
        }
        if (recommender3 != null) {
            return true;
        }
        if (recommender4 != null) {
            return true;
        }

        return recommender5 != null;
    }

    public String getApproversAndRecommendersList() {

        int index = 0;
        String approversAndRecommendersList = "";

        for (ApproverOrRecommender approverOrRecommender : getApproversAndRecommenders()) {

            if (index == 0) {
                approversAndRecommendersList = approverOrRecommender.getFirstName()
                        + " " + approverOrRecommender.getLastName();
            } else {
                approversAndRecommendersList = approversAndRecommendersList
                        + ", " + approverOrRecommender.getFirstName()
                        + " " + approverOrRecommender.getLastName();
            }

            index++;
        }

        if (approversAndRecommendersList.isEmpty()) {
            approversAndRecommendersList = "N/A";
        }

        return approversAndRecommendersList;

    }

    public ArrayList<ApproverOrRecommender> getApproversAndRecommenders() {

        approversAndRecommenders.clear();
        // Add approvers
        if (approver1 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(true, approver1));
        }
        if (approver2 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(true, approver2));
        }
        if (approver3 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(true, approver3));
        }
        if (approver4 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(true, approver4));
        }
        if (approver5 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(true, approver5));
        }

        // Add recommenders
        if (recommender1 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(false, recommender1));
        }
        if (recommender2 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(false, recommender2));
        }
        if (recommender3 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(false, recommender3));
        }
        if (recommender4 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(false, recommender4));
        }
        if (recommender5 != null) {
            approversAndRecommenders.add(new ApproverOrRecommender(false, recommender5));
        }

        return approversAndRecommenders;
    }

    public void setApproversAndRecommenders(ArrayList<ApproverOrRecommender> approversAndRecommenders) {
        this.approversAndRecommenders = approversAndRecommenders;
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

    @Override
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

    public Long getPurchaseOrderSequenceNumber() {
        return purchaseOrderSequenceNumber;
    }

    public void setPurchaseOrderSequenceNumber(Long purchaseOrderSequenceNumber) {
        this.purchaseOrderSequenceNumber = purchaseOrderSequenceNumber;
    }

    public Department getPurchasingDepartment() {
        if (purchasingDepartment == null) {
            return new Department();
        }
        return purchasingDepartment;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
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
        if (number == null) {
            return "--";
        }
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
    public DocumentType getDocumentType() {
        if (documentType == null) {
            return new DocumentType();
        }
        return documentType;
    }

    @Override
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
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
            String searchText,
            Date startDate,
            Date endDate,
            Long departmentId) {

        List<PurchaseRequisition> foundPRs;
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");
        String searchQuery;
        String searchTextAndClause;
        String departmentQuery = "";

        switch (searchType) {

            default:
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
                        + " OR UPPER(pr.status) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.workProgress) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.quotationNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(pr.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
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

    public static List<PurchaseRequisition> findAllActive(EntityManager em, int maxResults) {

        List<PurchaseRequisition> foundPRs;

        try {

            foundPRs = em.createQuery(
                    "SELECT p FROM PurchaseRequisition p "
                    + "WHERE p.workProgress != 'Completed' AND p.workProgress != 'Cancelled' "
                    + "ORDER BY p.id DESC",
                    PurchaseRequisition.class).setMaxResults(maxResults).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundPRs;
    }

    public static PurchaseRequisition findById(EntityManager em, Long Id) {

        return em.find(PurchaseRequisition.class, Id);
    }

    public static PurchaseRequisition findByPRNumber(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<PurchaseRequisition> purchaseRequisitions = em.createQuery("SELECT p FROM PurchaseRequisition p "
                    + "WHERE UPPER(p.number) "
                    + "= '" + value.toUpperCase() + "'", PurchaseRequisition.class).getResultList();

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

    public static List<PurchaseRequisition> findAll(EntityManager em, int maxResults) {

        try {
            return em.createNamedQuery("findAllPurchaseRequisitions", PurchaseRequisition.class)
                    .setMaxResults(maxResults).getResultList();
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

            // Save new attachments
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

    public ReturnMessage prepareAndSave(
            EntityManager em,
            EntityManager smem,
            User user) {
        Date now = new Date();
        PurchaseReqNumber nextPurchaseReqNumber = null;
        PurchaseOrderNumber nextPurchaseOrderNumber = null;

        try {
            // Get employee for later use
            Employee employee = user.getEmployee();

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

            if (getIsDirty()) {
                setDateEdited(now);
                setEditedBy(employee);
            }

            if (sequenceNumber == null) {

                boolean resetPRSequenceNumberYearly = SystemOption.getBoolean(
                        smem, "resetPRSequenceNumberYearly");

                nextPurchaseReqNumber = PurchaseReqNumber.
                        findNextPurchaseReqNumber(em,
                                resetPRSequenceNumberYearly,
                                BusinessEntityUtils.getYearFromDate(getRequisitionDate()));

                setSequenceNumber(nextPurchaseReqNumber.getSequentialNumber());
                generateNumber();
            }

            if (purchaseOrderSequenceNumber == null) {

                boolean resetPOSequenceNumberYearly = SystemOption.getBoolean(
                        smem, "resetPOSequenceNumberYearly");

                nextPurchaseOrderNumber = PurchaseOrderNumber.
                        findNextPurchaseOrderNumber(em,
                                resetPOSequenceNumberYearly,
                                BusinessEntityUtils.getYearFromDate(getRequisitionDate()));

                setPurchaseOrderSequenceNumber(nextPurchaseOrderNumber.getSequentialNumber());
                generatePurchaseOrderNumber();
            }

            ReturnMessage returnMessage = save(em);

            if (returnMessage.isSuccess()) {

                if (nextPurchaseReqNumber != null) {
                    nextPurchaseReqNumber.save(em);
                }

                if (nextPurchaseOrderNumber != null) {
                    nextPurchaseOrderNumber.save(em);
                }

                clean();
            } else {

                return new ReturnMessage(false,
                        "Undefined Error!",
                        "An undefined error occurred while saving purchase requisition "
                        + getNumber() + ":\n"
                        + returnMessage.getDetail(),
                        Message.SEVERITY_ERROR_NAME);
            }

        } catch (Exception e) {

            return new ReturnMessage(false,
                    "Undefined Error!",
                    "An undefined error occurred while saving purchase requisition "
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

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
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
    public ReturnMessage delete(EntityManager em) {
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

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
