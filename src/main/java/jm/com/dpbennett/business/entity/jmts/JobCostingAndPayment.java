/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
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
package jm.com.dpbennett.business.entity.jmts;

import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.DecimalFormat;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.fm.AccountingCode;
import jm.com.dpbennett.business.entity.fm.CashPayment;
import jm.com.dpbennett.business.entity.fm.CostComponent;
import jm.com.dpbennett.business.entity.fm.Discount;
import jm.com.dpbennett.business.entity.fm.Tax;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

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
    private Employee costingPreparedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee costingApprovedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee costingInvoicedBy;
    private Double minDeposit;
    private Double totalTax;
    private Double totalCost;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee lastPaymentEnteredBy;
    private String percentageGCT;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Tax tax;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Discount discount;
    private String discountType;
    @Column(name = "DISCOUNT")
    private Double discountValue;
    @Transient
    private Boolean isDirty;
    private Boolean active;
    @Column(length = 1024)
    private String description;
    private Boolean estimate;

    public JobCostingAndPayment() {
        this.totalCost = 0.0;
        this.totalTax = 0.0;
        this.minDeposit = 0.0;
        this.amountDue = 0.0;
        this.deposit = 0.0;
        this.paymentReceivedToDate = 0.0;
        this.finalCost = 0.0;
        this.estimatedCost = 0.0;
        this.cashPayments = new ArrayList<>();
        this.costComponents = new ArrayList<>();
        this.isDirty = false;
        this.active = true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstimate() {
        if (estimate == null) {
            estimate = false;
        }
        return estimate;
    }

    public void setEstimate(Boolean estimate) {
        this.estimate = estimate;
    }

    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }

    /**
     * Returns the type of discount as Percentage, Currency or Fixed Cost.
     *
     * @deprecated This feature is already encapsulated in the Discount class.
     *
     * @return
     */
    public String getDiscountType() {
        if (discountType == null) {
            discountType = "Percentage";
        }
        return discountType;
    }

    /**
     * Sets the type of discount as Percentage, Currency or Fixed Cost.
     *
     * @deprecated This feature is already encapsulated in the Discount class.
     *
     * @param discountType
     */
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    /**
     * Gets the value of the discount.
     *
     * @deprecated This feature is already encapsulated in the Discount class.
     *
     * @return
     */
    public Double getDiscountValue() {
        if (discountValue == null) {
            discountValue = 0.0;
        }
        return discountValue;
    }

    /**
     * Sets the value of the discount.
     *
     * @deprecated This feature is already encapsulated in the Discount class.
     *
     * @param discountValue
     */
    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    /**
     * Gets the General Consumption Tax (GCT).
     *
     * @deprecated This feature is already encapsulated in the Tax class.
     *
     * @return
     */
    public String getPercentageGCT() {
        return percentageGCT;
    }

    /**
     * Sets the General Consumption Tax (GCT).
     *
     * @deprecated This feature is already encapsulated in the Discount class.
     *
     * @param percentageGCT
     */
    public void setPercentageGCT(String percentageGCT) {
        this.percentageGCT = percentageGCT;
    }

    public Employee getCostingInvoicedBy() {
        return costingInvoicedBy;
    }

    public void setCostingInvoicedBy(Employee costingInvoicedBy) {
        this.costingInvoicedBy = costingInvoicedBy;
    }

    public Employee getCostingPreparedBy() {
        return costingPreparedBy;
    }

    public void setCostingPreparedBy(Employee costingPreparedBy) {
        this.costingPreparedBy = costingPreparedBy;
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

    public List<AccountingCode> getAccountingCodes() {
        return new ArrayList<>();
    }

//    public AccountingCode getAccountingCode() {
//        if (accountingCode == null) {
//            accountingCode = new AccountingCode();
//        }
//        return accountingCode;
//    }
//
//    public void setAccountingCode(AccountingCode accountingCode) {
//        this.accountingCode = accountingCode;
//    }
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

    public static JobCostingAndPayment create(EntityManager em) {
        JobCostingAndPayment jobCostingAndPayment = new JobCostingAndPayment();

        jobCostingAndPayment.setPurchaseOrderNumber("");
        jobCostingAndPayment.setTax(Tax.findDefault(em, "0.0"));
        jobCostingAndPayment.setDiscount(Discount.findDefault(em, "0.0"));

        return jobCostingAndPayment;
    }

    public Double getCalculatedMinDeposit() {

        return getMinDepositWithDiscount() + getMinDepositTotalTax();
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

    public String getTotalTaxLabel() {
        return "Tax:";
    }

    public String getTotalCostWithTaxLabel() {
        if (getDiscount().getDiscountValue() == 0.0) {
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

    public String getCalculatedCostEstimateLabel() {
        if (getDiscount().getDiscountValue() == 0.0) {
            if (getTax().getTaxValue() != 0.0) {
                return "Calc'ed cost estimate (incl. tax)($): ";
            } else {
                return "Calc'ed cost estimate ($): ";
            }
        } else {
            if (getTax().getTaxValue() != 0.0) {
                return "Calc'ed cost estimate (incl. tax & discount)($): ";
            } else {
                return "Calc'ed cost estimate (incl. discount)($): ";
            }
        }
    }

    public String getCalculatedMinDepositLabel() {

        if (getDiscount().getDiscountValue() == 0.0) {
            if (getTax().getTaxValue() != 0.0) {
                return "Calc'ed min. deposit (incl. tax)($): ";
            } else {
                return "Calc'ed min. deposit ($): ";
            }
        } else {
            if (getTax().getTaxValue() != 0.0) {
                return "Calc'ed min. deposit (incl. tax & discount)($): ";
            } else {
                return "Calc'ed min. deposit (incl. discount)($): ";
            }
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

    public Double getCalculatedCostEstimate() {

        return getEstimatedCostWithDiscount() + getEstimatedCostTotalTax();
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
                            + " WHERE UPPER(jobCostingAndPayment.name) LIKE '%"
                            + newJobCostingAndPaymentName.toUpperCase().trim() + "%'"
                            + " AND ( UPPER(department.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " OR UPPER(subContractedDepartment.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " )"
                            + " GROUP BY jobCostingAndPayment.name ORDER BY jobCostingAndPayment.name",
                            Job.class).setMaxResults(50).getResultList();
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

    public static List<JobCostingAndPayment> findAllActiveJobCostingAndPaymentsByDepartmentAndName(
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
                            + " WHERE UPPER(jobCostingAndPayment.name) LIKE '%"
                            + newJobCostingAndPaymentName.toUpperCase().trim() + "%'"
                            + " AND ( UPPER(department.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " OR UPPER(subContractedDepartment.name) = '" + newDepartmentName.toUpperCase() + "'"
                            + " )"
                            + " AND (jobCostingAndPayment.active = 1 OR jobCostingAndPayment.active IS NULL)"
                            + " GROUP BY jobCostingAndPayment.name ORDER BY jobCostingAndPayment.name",
                            Job.class).setMaxResults(50).getResultList();
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

//    public static JobCostingAndPayment findActiveJobCostingAndPaymentByName(
//            EntityManager em,
//            String jobCostingAndPaymentName) {
//
//        try {
//            String newJobCostingAndPaymentName = jobCostingAndPaymentName.replaceAll("'", "''");
//            String newDepartmentName = departmentName.replaceAll("'", "''");
//
//            List<Job> jobs
//                    = em.createQuery("SELECT job FROM Job job"
//                            + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
//                            + " JOIN job.department department"
//                            + " JOIN job.subContractedDepartment subContractedDepartment"
//                            + " WHERE UPPER(jobCostingAndPayment.name) = '"
//                            + newJobCostingAndPaymentName.toUpperCase().trim() + "'"
//                            + " AND ( UPPER(department.name) = '" + newDepartmentName.toUpperCase() + "'"
//                            + " OR UPPER(subContractedDepartment.name) = '" + newDepartmentName.toUpperCase() + "'"
//                            + " )"
//                            + " AND (jobCostingAndPayment.active = 1 OR jobCostingAndPayment.active IS NULL)"
//                            + " ORDER BY jobCostingAndPayment.name", Job.class).getResultList();
//            if (!jobs.isEmpty()) {
//                return jobs.get(0).getJobCostingAndPayment();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
//
//        return null;
//    }
    public static JobCostingAndPayment findActiveJobCostingAndPaymentByDepartmentAndName(
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
                            + " AND (jobCostingAndPayment.active = 1 OR jobCostingAndPayment.active IS NULL)"
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

    private Double getFinalCostWithDiscount() {
        Double finalCostWithDiscount;

        if (getDiscount().getDiscountValueType().equals("Percentage")) {
            finalCostWithDiscount = getFinalCost() - getFinalCost() * getDiscount().getValue();
        } else {
            finalCostWithDiscount = getFinalCost() - getDiscount().getValue();
        }

        return finalCostWithDiscount;
    }

    private Double getEstimatedCostWithDiscount() {
        Double estimatedCostWithDiscount;

        if (getDiscount().getDiscountValueType().equals("Percentage")) {
            estimatedCostWithDiscount = getEstimatedCost() - getEstimatedCost() * getDiscount().getValue();
        } else {
            estimatedCostWithDiscount = getEstimatedCost() - getDiscount().getValue();
        }

        return estimatedCostWithDiscount;
    }

    private Double getMinDepositWithDiscount() {
        Double minDepositWithDiscount;

        if (getDiscount().getDiscountValueType().equals("Percentage")) {
            minDepositWithDiscount = getMinDeposit() - getMinDeposit() * getDiscount().getValue();
        } else {
            minDepositWithDiscount = getMinDeposit() - getDiscount().getValue();
        }

        return minDepositWithDiscount;
    }

    public Double getTotalDiscount() {
        Double totalDiscount;

        if (getDiscount().getDiscountValueType().equals("Percentage")) {
            totalDiscount = getFinalCost() * getDiscount().getValue();
        } else {
            totalDiscount = getDiscount().getValue();
        }

        return totalDiscount;
    }

    public Double getTotalTax() {

        if (getTax().getTaxValueType().equals("Percentage")) {
            totalTax = getFinalCostWithDiscount() * getTax().getValue();
        } else {
            totalTax = getTax().getValue();
        }

        return totalTax;
    }

    public Double getEstimatedCostTotalTax() {

        Double estimatedCostTotalTax;

        if (getTax().getTaxValueType().equals("Percentage")) {
            estimatedCostTotalTax = getEstimatedCostWithDiscount() * getTax().getValue();
        } else {
            estimatedCostTotalTax = getTax().getValue();
        }

        return estimatedCostTotalTax;
    }

    public Double getMinDepositTotalTax() {

        Double minDepositTotalTax;

        if (getTax().getTaxValueType().equals("Percentage")) {
            minDepositTotalTax = getMinDepositWithDiscount() * getTax().getValue();
        } else {
            minDepositTotalTax = getTax().getValue();
        }

        return minDepositTotalTax;
    }

    /**
     * Get total cost. Total cost includes total tax
     *
     * @return
     */
    public Double getTotalCost() {

        totalCost = getFinalCostWithDiscount() + getTotalTax();

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
        return job.getClassification().getIsEarning()
                && job.getDepartment().getPrivilege().getCanApplyTaxesToJobCosting()
                && (BusinessEntityUtils.getMediumDateStringAsLong("Mar 21, 2016") // tk make sys option?
                <= BusinessEntityUtils.getMediumDateStringAsLong(
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
                doSave(em);

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

    public void doSave(EntityManager em) {
        em.getTransaction().begin();
        isDirty = false;
        BusinessEntityUtils.saveBusinessEntity(em, this);
        em.getTransaction().commit();
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

    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static List<JobCostingAndPayment> findAllEstimateJobCostingAndPayments(
            EntityManager em,
            String jobCostingAndPaymentName) {

        try {
            String newJobCostingAndPaymentName = jobCostingAndPaymentName.replaceAll("'", "''");
            
            List<JobCostingAndPayment> jobCostingAndPayments
                    = em.createQuery("SELECT jobCostingAndPayment FROM JobCostingAndPayment jobCostingAndPayment"
                            + " WHERE UPPER(jobCostingAndPayment.name) LIKE '%"
                            + newJobCostingAndPaymentName.toUpperCase().trim() + "%'"
                            + " AND (jobCostingAndPayment.estimate = 1)"
                            + " GROUP BY jobCostingAndPayment.name ORDER BY jobCostingAndPayment.name",
                            JobCostingAndPayment.class).setMaxResults(500).getResultList();
            
           return jobCostingAndPayments;
           
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }        
    }

}
