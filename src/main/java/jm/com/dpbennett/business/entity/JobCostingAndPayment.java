/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobcostingandpayment")
@XmlRootElement
public class JobCostingAndPayment implements Serializable, BusinessEntity, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private String name;
    private Boolean completed;
    private String invoiceNumber;
    private String purchaseOrderNumber;
    private String receiptNumber;
    private String paymentTerms;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<CashPayment> cashPayments;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<CostComponent> costComponents;
    private Double estimatedCost;
    private String estimatedCostDoneBy;
    private Double finalCost;
    private String finalCostDoneBy;
    private Double paymentReceivedToDate;
    private Double deposit;
    private Double amountDue;
    private Boolean costingCompleted;
    private Boolean costingApproved;
    private Boolean invoiced;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee costingApprovedBy;
    private Double discount;
    private Double minDeposit;
    private Double totalTax;
    private Double totalCost;
    private Double percentageGCT;
    @Transient
    private Double estimatedCostIncludingTaxes;
    @Transient
    private Double minDepositIncludingTaxes;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee lastPaymentEnteredBy;
    private String discountType;
    @Transient
    private Boolean isDirty;

    public JobCostingAndPayment() {
        this.percentageGCT = 0.0;
        this.totalCost = 0.0;
        this.totalTax = 0.0;
        this.minDeposit = 0.0;
        this.discount = 0.0;
        this.amountDue = 0.0;
        this.deposit = 0.0;
        this.paymentReceivedToDate = 0.0;
        this.finalCost = 0.0;
        this.estimatedCost = 0.0;
        this.cashPayments = new ArrayList<>();
        this.costComponents = new ArrayList<>();
        this.isDirty = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public static void createSampleBasedJobCostings(Job currentJob) {
        if (currentJob.getJobCostingAndPayment().getAllSortedCostComponents().isEmpty()) {
            // Add all existing samples as cost oomponents            
            for (JobSample jobSample : currentJob.getJobSamples()) {
                currentJob.getJobCostingAndPayment().getAllSortedCostComponents().add(new CostComponent(jobSample.getDescription()));
            }
        } else if (currentJob.getJobSamples().size() > currentJob.getJobCostingAndPayment().getAllSortedCostComponents().size()) {
        }
    }

    public static void createDefaultJobCostings(Job currentJob) {

        if (currentJob.getJobCostingAndPayment().getCostComponents().isEmpty()) {
            currentJob.getJobCostingAndPayment().getCostComponents().add(new CostComponent("List of Assessments", Boolean.TRUE));
            currentJob.getJobCostingAndPayment().getCostComponents().add(new CostComponent(""));
        }

    }

    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }

        isDirty = isDirty || isCostComponentDirty();
        isDirty = isDirty || isCashPaymentDirty();

        return isDirty;
    }

    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public static JobCostingAndPayment create() {
        JobCostingAndPayment jobCostingAndPayment = new JobCostingAndPayment();

        jobCostingAndPayment.setPurchaseOrderNumber("");
        jobCostingAndPayment.setReceiptNumber("");

        return jobCostingAndPayment;
    }

    public String getDiscountType() {
        if (discountType == null) {
            discountType = "Fixed Cost";
        }
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getMinDepositIncludingTaxes() {
        minDepositIncludingTaxes
                = getMinDeposit() + getMinDeposit() * getPercentageGCT() / 100.0;
        //= BusinessEntityUtils.roundTo2DecimalPlaces(getMinDeposit() + getMinDeposit() * getPercentageGCT() / 100.0);

        return minDepositIncludingTaxes;
    }

    public void setMinDepositIncludingTaxes(Double minDepositIncludingTaxes) {
        this.minDepositIncludingTaxes = minDepositIncludingTaxes;
    }

    public Employee getLastPaymentEnteredBy() {
        if (lastPaymentEnteredBy == null) {
            return new Employee();
        }
        return lastPaymentEnteredBy;
    }

    public void setLastPaymentEnteredBy(Employee lastPaymentEnteredBy) {
        this.lastPaymentEnteredBy = lastPaymentEnteredBy;
    }

    public Double getPercentageGCT() {
        if (percentageGCT == null) {
            percentageGCT = 0.0;
        }
        return percentageGCT;
    }

    public void setPercentageGCT(Double percentageGCT) {
        this.percentageGCT = percentageGCT;
    }

    public Double getAmountDue() {
//        if (amountDue == null) {
//            amountDue = 0.0;
//        }
        amountDue = getTotalCost() - getTotalPayment();

        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public Boolean getInvoiced() {
        if (invoiced == null) {
            invoiced = false;
        }
        return invoiced;
    }

    public void setInvoiced(Boolean invoiced) {
        this.invoiced = invoiced;
    }

    public Double getMinDeposit() {
        if (minDeposit == null) {
            minDeposit = 0.0;
        }
        return minDeposit;
    }

    public void setMinDeposit(Double minDeposit) {
        this.minDeposit = minDeposit;
    }

    public Double getDiscount() {
        if (discount == null) {
            discount = 0.0;
        }
        return discount;
    }

    /**
     * Returns the discount type that can be applied to a payment/amount NB: To
     * be deprecated
     *
     * @return
     */
    public static List getDiscountTypes() {
        ArrayList discountTypes = new ArrayList();

        discountTypes.add(new SelectItem("Currency", "Currency: "));
        discountTypes.add(new SelectItem("Percentage", "Percentage: "));

        return discountTypes;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountValue() {
        if (getDiscountType().equals("Currency")) {
            return getDiscount();
        } else {
            return getFinalCost() * getDiscount() / 100.0;
        }
    }

    public String getTotalTaxLabel() {
        return "Tax (GCT: " + getPercentageGCT() + "%)($):";
    }

    public String getTotalCostWithTaxLabel() {
        if (getDiscountValue() == 0.0) {
            if (getPercentageGCT() != 0.0) {
                return "Total cost (incl. GCT)($): ";
            } else {
                return "Total cost ($): ";
            }
        } else {
            if (getPercentageGCT() != 0.0) {
                return "Total cost (incl. GCT & discount)($): ";
            } else {
                return "Total cost (incl. discount)($): ";
            }
        }
    }

    public String getCostEstimateWithTaxLabel() {
        if (getPercentageGCT() != 0.0) {
            return "Calculated cost estimate (incl. GCT)($): ";
        } else {
            return "Calculated cost estimate ($): ";
        }
    }

    public String getMinDepositWithTaxLabel() {
        if (getPercentageGCT() != 0.0) {
            return "Minimum deposit (incl. GCT)($): ";
        } else {
            return "Minimum deposit ($): ";
        }
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

    public Boolean getCostingApproved() {
        if (costingApproved == null) {
            costingApproved = false;
        }
        return costingApproved;
    }

    public void setCostingApproved(Boolean costingApproved) {
        this.costingApproved = costingApproved;
    }

    public Double getFinalCost() {
        finalCost = getTotalJobCostingsAmount();

        return finalCost;
    }

    public String getFormattedFinalCost() {
        DecimalFormat formatter = new DecimalFormat("$#,##0.00");

        return formatter.format(getFinalCost());
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

//    public Double calculateAmountDue() {
//
//        // NB: Remove discount before taxes are applied
//        // Take into account discount when it is a percentage       
////        Double finalCostWithDiscount = getFinalCost() - getDiscountValue(); //BusinessEntityUtils.roundTo2DecimalPlaces(getDiscountValue());
////
////        // Add taxes to total (eg GCT)
////        //setTotalTax(BusinessEntityUtils.roundTo2DecimalPlaces(finalCostWithDiscount * getPercentageGCT() / 100.0));
////        setTotalTax(finalCostWithDiscount * getPercentageGCT() / 100.0);
////        setTotalCost(finalCostWithDiscount + getTotalTax());
//
//        // Remove deposit(s)/total payments if any       
//        //setAmountDue(getTotalCost() - BusinessEntityUtils.roundTo2DecimalPlaces(getTotalPayment()));
//        setAmountDue(getTotalCost() - getTotalPayment());
//
//        return getAmountDue();
//    }
    public Double getTotalJobCostingsAmount() {
        Double total = 0.0;

        for (CostComponent component : costComponents) {
            total = total + component.getCost();
        }

        return total;
    }

    public Employee getCostingApprovedBy() {
        return costingApprovedBy;
    }

    public void setCostingApprovedBy(Employee costingApprovedBy) {
        this.costingApprovedBy = costingApprovedBy;
    }

    public Boolean getCostingCompleted() {
        if (costingCompleted == null) {
            costingCompleted = false;
        }
        return costingCompleted;
    }

    public void setCostingCompleted(Boolean costingCompleted) {
        this.costingCompleted = costingCompleted;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets the cash payments made. Note that if there are no cash payments but
     * a deposit exists, the deposit is created as a "Final" payment.
     *
     * @return
     */
    @XmlTransient
    @JsonIgnore
    public List<CashPayment> getCashPayments() {
        if (cashPayments == null) {
            cashPayments = new ArrayList<>();
        }

        return cashPayments;
    }

    public void setCashPayments(List<CashPayment> cashPayments) {
        this.cashPayments = cashPayments;
    }

    /**
     * Get the deposit payment for the job.
     *
     * @return
     */
    public Double getDeposit() {
        if (deposit == null) {
            deposit = 0.0;
        }

        return deposit;
    }

    /**
     * Get the total payments from cash payments and deposit if any.
     *
     * @return
     */
    public Double getTotalPayment() {
        Double payment = getDeposit();

        for (CashPayment cashPayment : getCashPayments()) {
            payment = payment + cashPayment.getPayment();
        }

        return payment;
    }

    public Double getEstimatedCost() {
        if (estimatedCost == null) {
            estimatedCost = 0.0;
        }

        return estimatedCost;
    }

    public Double getEstimatedCostIncludingTaxes() {
        estimatedCostIncludingTaxes
                = getEstimatedCost() + getEstimatedCost() * getPercentageGCT() / 100.0;
        //= BusinessEntityUtils.roundTo2DecimalPlaces(getEstimatedCost() + getEstimatedCost() * getPercentageGCT() / 100.0);

        return estimatedCostIncludingTaxes;
    }

    public void setEstimatedCostIncludingTaxes(Double estimatedCostIncludingTaxes) {
        this.estimatedCostIncludingTaxes = estimatedCostIncludingTaxes;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getEstimatedCostDoneBy() {
        return estimatedCostDoneBy;
    }

    public void setEstimatedCostDoneBy(String estimatedCostDoneBy) {
        this.estimatedCostDoneBy = estimatedCostDoneBy;
    }

    public String getFinalCostDoneBy() {
        return finalCostDoneBy;
    }

    public void setFinalCostDoneBy(String finalCostDoneBy) {
        this.finalCostDoneBy = finalCostDoneBy;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Double getPaymentReceivedToDate() {
        return paymentReceivedToDate;
    }

    public void setPaymentReceivedToDate(Double paymentReceivedToDate) {
        this.paymentReceivedToDate = paymentReceivedToDate;
    }

    public String getPaymentTerms() {
        if (paymentTerms == null) {
            paymentTerms = "";
        }
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getPurchaseOrderNumber() {
        if (purchaseOrderNumber == null) {
            purchaseOrderNumber = "";
        }
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getReceiptNumber() {
        receiptNumber = "";

        for (CashPayment cashPayment : getCashPayments()) {
            setReceiptNumber(receiptNumber + " " + cashPayment.getReceiptNumber());
        }

        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
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
        if (!(object instanceof JobCostingAndPayment)) {
            return false;
        }
        JobCostingAndPayment other = (JobCostingAndPayment) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    @JsonIgnore
    public List<CostComponent> getCostComponents() {
        if (costComponents == null) {
            costComponents = new ArrayList<>();
        }
        return costComponents;
    }

    public void setCostComponents(List<CostComponent> costComponents) {
        this.costComponents = costComponents;
    }

    public static List<JobCostingAndPayment> findAllJobCostingAndPaymentsByDepartmentAndName(
            EntityManager em,
            String departmentName,
            String jobCostingAndPaymentName) {

        List<JobCostingAndPayment> jobCostingAndPayments = new ArrayList<>();

        try {
            String newJobCostingAndPaymentName = jobCostingAndPaymentName.replaceAll("'", "''");
            String newDepartmentName = departmentName.replaceAll("'", "''");

            List<Job> jobs
                    = em.createQuery("SELECT job FROM Job job"
                            + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                            + " JOIN job.department department"
                            + " JOIN job.subContractedDepartment subContractedDepartment"
                            + " WHERE UPPER(jobCostingAndPayment.name) LIKE '"
                            + newJobCostingAndPaymentName.toUpperCase().trim() + "%'"
                            + " AND ( UPPER(department.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " OR UPPER(subContractedDepartment.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " )"
                            + " GROUP BY jobCostingAndPayment.name ORDER BY jobCostingAndPayment.name", Job.class).getResultList();
            if (!jobs.isEmpty()) {
                for (int i = 0; i < jobs.size(); i++) {
                    if (!jobs.get(i).getJobCostingAndPayment().getName().trim().equals("")) {
                        jobCostingAndPayments.add(jobs.get(i).getJobCostingAndPayment());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

        return jobCostingAndPayments;
    }

    public static JobCostingAndPayment findJobCostingAndPaymentByDepartmentAndName(
            EntityManager em,
            String departmentName,
            String jobCostingAndPaymentName) {

        try {
            String newJobCostingAndPaymentName = jobCostingAndPaymentName.replaceAll("'", "''");
            String newDepartmentName = departmentName.replaceAll("'", "''");

            List<Job> jobs
                    = em.createQuery("SELECT job FROM Job job"
                            + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                            + " JOIN job.department department"
                            + " JOIN job.subContractedDepartment subContractedDepartment"
                            + " WHERE UPPER(jobCostingAndPayment.name) = '"
                            + newJobCostingAndPaymentName.toUpperCase().trim() + "'"
                            + " AND ( UPPER(department.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " OR UPPER(subContractedDepartment.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " )"
                            + " ORDER BY jobCostingAndPayment.name", Job.class).getResultList();
            if (!jobs.isEmpty()) {
                return jobs.get(0).getJobCostingAndPayment();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return null;
    }

    public static JobCostingAndPayment findJobCostingAndPaymentById(EntityManager em, Long Id) {
        return em.find(JobCostingAndPayment.class, Id);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        JobCostingAndPayment jobCostingAndPayment = new JobCostingAndPayment();
        jobCostingAndPayment.setName(value);

        return jobCostingAndPayment;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((JobCostingAndPayment) value).getName();
    }

    public Double getTotalTax() {
        Double finalCostWithDiscount = getFinalCost() - getDiscountValue(); //BusinessEntityUtils.roundTo2DecimalPlaces(getDiscountValue());

        // Add taxes to total (eg GCT)
        //setTotalTax(BusinessEntityUtils.roundTo2DecimalPlaces(finalCostWithDiscount * getPercentageGCT() / 100.0));
        totalTax = finalCostWithDiscount * getPercentageGCT() / 100.0;

        return totalTax;
    }

//    public void setTotalTax(Double totalTax) {
//        this.totalTax = totalTax;
//    }
    /**
     * Get total cost. Total cost includes total tax
     *
     * @return
     */
    public Double getTotalCost() {
        Double finalCostWithDiscount = getFinalCost() - getDiscountValue(); //BusinessEntityUtils.roundTo2DecimalPlaces(getDiscountValue());
//
//        // Add taxes to total (eg GCT)
//        //setTotalTax(BusinessEntityUtils.roundTo2DecimalPlaces(finalCostWithDiscount * getPercentageGCT() / 100.0));
//        setTotalTax(finalCostWithDiscount * getPercentageGCT() / 100.0);
        totalCost = finalCostWithDiscount + getTotalTax();

        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public static Boolean getCanApplyGCT(Job job) {
        return !job.getIsSubContract()
                && job.getClassification().getIsEarning()
                && job.getDepartment().getPrivilege().getCanApplyTaxesToJobCosting()
                && (BusinessEntityUtils.getMediumDateStringAsLong("Mar 21, 2016") // tk make sys option

                <= BusinessEntityUtils.getMediumDateStringAsLong( // tk why on earth is this here??
                        BusinessEntityUtils.getDateInMediumDateFormat(job.getJobStatusAndTracking().getDateSubmitted())));
    }

    public static void setJobCostingTaxes(EntityManager em, Job job) {

        if (JobCostingAndPayment.getCanApplyGCT(job)) {
            Double percentGCT = SystemOption.getGCTPercentage(em);
            job.getJobCostingAndPayment().setPercentageGCT(percentGCT);
        } else {
            job.getJobCostingAndPayment().setPercentageGCT(0.0);
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {

        try {
            // Save new payments 
            if (!getCashPayments().isEmpty()) {
                for (CashPayment payment : getCashPayments()) {
                    if ((payment.getIsDirty() || payment.getId() == null)
                            && !payment.save(em).isSuccess()) {
                        return new ReturnMessage(false,
                                "Payment save error occurred",
                                "An error occurred while saving a payment",
                                FacesMessage.SEVERITY_ERROR);
                    }
                }
            }

            // Save new cost components
            if (!getCostComponents().isEmpty()) {
                for (CostComponent costComponent : getCostComponents()) {
                    if ((costComponent.getIsDirty() || costComponent.getId() == null)
                            && !costComponent.save(em).isSuccess()) {
                        return new ReturnMessage(false,
                                "Cost component save error occurred",
                                "An error occurred while saving a cost component",
                                FacesMessage.SEVERITY_ERROR);
                    }
                }
            }

            // Save   
            if (isDirty || id == null) {
                em.getTransaction().begin();
                isDirty = false;
                BusinessEntityUtils.saveBusinessEntity(em, this);
                em.getTransaction().commit();

                return new ReturnMessage();
            } else {
                return new ReturnMessage(true,
                        "Costing and payment NOT saved",
                        "Not saved because it was not edited",
                        FacesMessage.SEVERITY_INFO);
            }

        } catch (Exception e) {
            return new ReturnMessage(false,
                    "Costing and payment save error occurred!",
                    "An error occurred while saving the job costing and payment: " + e,
                    FacesMessage.SEVERITY_ERROR);
        }

    }

    public Boolean isCostComponentDirty() {
        boolean dirty = false;

        if (!getCostComponents().isEmpty()) {
            for (CostComponent costComponent : getCostComponents()) {
                dirty = dirty || costComponent.getIsDirty();
            }
        }

        return dirty;
    }

    public Boolean isCashPaymentDirty() {
        boolean dirty = false;

        if (!getCashPayments().isEmpty()) {
            for (CashPayment payment : getCashPayments()) {
                dirty = dirty || payment.getIsDirty();
            }
        }

        return dirty;
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
