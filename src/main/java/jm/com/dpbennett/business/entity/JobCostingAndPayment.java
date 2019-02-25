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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.Message;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "jobcostingandpayment")
@XmlRootElement
public class JobCostingAndPayment implements Serializable, BusinessEntity {

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
    private Double minDeposit;
    private Double totalTax;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Tax tax;
    private Double totalCost;
    @Transient
    private Double estimatedCostIncludingTaxes;
    @Transient
    private Double minDepositIncludingTaxes;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee lastPaymentEnteredBy;    
    private Double discount;
    private String discountType;
    @Transient
    private Boolean isDirty;
    @OneToOne(cascade = CascadeType.REFRESH)
    private AccountingCode accountingCode; // tk delete if not necessary

    public JobCostingAndPayment() {
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

    public Tax getTax() {
        return (tax == null ? new Tax(): tax);
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public List<AccountingCode> getAccountingCodes() {
        return new ArrayList<>();
    }

    public AccountingCode getAccountingCode() {
        if (accountingCode == null) {
            accountingCode = new AccountingCode();
        }
        return accountingCode;
    }

    public void setAccountingCode(AccountingCode accountingCode) {
        this.accountingCode = accountingCode;
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

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }

        isDirty = isDirty || isCostComponentDirty();
        isDirty = isDirty || isCashPaymentDirty();

        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public static JobCostingAndPayment create() {
        JobCostingAndPayment jobCostingAndPayment = new JobCostingAndPayment();

        jobCostingAndPayment.setPurchaseOrderNumber("");

        return jobCostingAndPayment;
    }

    public String getDiscountType() {
        if (discountType == null) {
            discountType = "Percentage";
        }
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getMinDepositIncludingTaxes() {
        minDepositIncludingTaxes
                = getMinDeposit() + getMinDeposit() * getTax().getValue();
        
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

    public Double getAmountDue() {

        amountDue = BusinessEntityUtils.roundTo2DecimalPlaces(getTotalCost())
                - BusinessEntityUtils.roundTo2DecimalPlaces(getTotalPayment());

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

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountValue() {
        if (getDiscountType().equals("Percentage")) {
            return getFinalCost() * getDiscount() / 100.0;
        } else {
            return getDiscount();
        }
    }

    public String getTotalTaxLabel() {
        return "Tax:";
    }

    public String getTotalCostWithTaxLabel() {
        if (getDiscountValue() == 0.0) {
            if (getTax().getTaxValue() != 0.0) {
                return "Total cost (incl. tax)($): ";
            } else {
                return "Total cost ($): ";
            }
        } else {
            if (getTax().getTaxValue() != 0.0) {
                return "Total cost (incl. tax & discount)($): ";
            } else {
                return "Total cost (incl. discount)($): ";
            }
        }
    }

    public String getCostEstimateWithTaxLabel() {
        if (getTax().getTaxValue() != 0.0) {
            return "Calculated cost estimate (incl. tax)($): ";
        } else {
            return "Calculated cost estimate ($): ";
        }
    }

    public String getMinDepositWithTaxLabel() {
        if (getTax().getTaxValue() != 0.0) {
            return "Minimum deposit (incl. tax)($): ";
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
    //@JsonIgnore
    public List<CashPayment> getCashPayments() {
        if (cashPayments != null) {
            Collections.sort(cashPayments);
        } else {
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
        deposit = 0.0;

        for (CashPayment cashPayment : getCashPayments()) {
            if (cashPayment.getPaymentPurpose().equals("Deposit")) {
                deposit = deposit + cashPayment.getPayment();
            }
        }

        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    /**
     * Get the total payments from cash payments and deposit if any.
     *
     * @return
     */
    public Double getTotalPayment() {
        Double payment = getDeposit();

        for (CashPayment cashPayment : getCashPayments()) {
            if (!cashPayment.getPaymentPurpose().equals("Deposit")) {
                payment = payment + cashPayment.getPayment();
            }
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
                = getEstimatedCost() + getEstimatedCost() * getTax().getValue();
        
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

    public String getAllPaymentTerms() {

        paymentTerms = "";

        int index = 0;

        for (CashPayment cashPayment : getCashPayments()) {
            if (index == 0) {
                paymentTerms = "(" + (index + 1) + ") " + cashPayment.getPaymentTerms();
            } else {
                if (!cashPayment.getPaymentTerms().trim().equals("")) {
                    paymentTerms = paymentTerms + ", " + "(" + (index + 1) + ") " + cashPayment.getPaymentTerms();
                }
            }

            index++;
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

    public String getReceiptNumbers() {
        int index = 0;

        for (CashPayment cashPayment : getCashPayments()) {
            if (index == 0) {
                receiptNumber = cashPayment.getReceiptNumber();
            } else {
                if (!cashPayment.getReceiptNumber().trim().equals("")) {
                    receiptNumber = receiptNumber + ", " + cashPayment.getReceiptNumber();
                }
            }

            index++;
        }

        return receiptNumber;
    }

    public Date getLastPaymentDate() {
        if (!getCashPayments().isEmpty()) {
            return getCashPayments().get(getCashPayments().size() - 1).getDateOfPayment();
        } else {
            return null;
        }
    }

    public String getReceiptNumber() {
        if (receiptNumber == null) {
            receiptNumber = "";
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
    //@JsonIgnore
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

    public Double getTotalTax() {
        Double finalCostWithDiscount = getFinalCost() - getDiscountValue(); 

        totalTax = finalCostWithDiscount * getTax().getValue();

        return totalTax;
    }

    /**
     * Get total cost. Total cost includes total tax
     *
     * @return
     */
    public Double getTotalCost() {
        Double finalCostWithDiscount = getFinalCost() - getDiscountValue(); //BusinessEntityUtils.roundTo2DecimalPlaces(getDiscountValue());

        totalCost = finalCostWithDiscount + getTotalTax();

        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * 
     * @param job
     * @return 
     */
    public static Boolean getCanApplyTax(Job job) {
        return !job.getIsSubContract()
                && job.getClassification().getIsEarning()
                && job.getDepartment().getPrivilege().getCanApplyTaxesToJobCosting()
                && (BusinessEntityUtils.getMediumDateStringAsLong("Mar 21, 2016") // tk make sys option

                <= BusinessEntityUtils.getMediumDateStringAsLong( // tk why on earth is this here??
                        BusinessEntityUtils.getDateInMediumDateFormat(job.getJobStatusAndTracking().getDateSubmitted())));
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
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

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
                        Message.SEVERITY_INFO_NAME);
            }

        } catch (Exception e) {
            return new ReturnMessage(false,
                    "Costing and payment save error occurred!",
                    "An error occurred while saving the job costing and payment: " + e,
                    Message.SEVERITY_ERROR_NAME);
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
