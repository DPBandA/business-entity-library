/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "jobcostingandpayment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobcostingandpayment.findAll", query = "SELECT j FROM Jobcostingandpayment j")
    , @NamedQuery(name = "Jobcostingandpayment.findById", query = "SELECT j FROM Jobcostingandpayment j WHERE j.id = :id")
    , @NamedQuery(name = "Jobcostingandpayment.findByCostingdate", query = "SELECT j FROM Jobcostingandpayment j WHERE j.costingdate = :costingdate")
    , @NamedQuery(name = "Jobcostingandpayment.findByPaymentreceivedtodate", query = "SELECT j FROM Jobcostingandpayment j WHERE j.paymentreceivedtodate = :paymentreceivedtodate")
    , @NamedQuery(name = "Jobcostingandpayment.findByMindeposit", query = "SELECT j FROM Jobcostingandpayment j WHERE j.mindeposit = :mindeposit")
    , @NamedQuery(name = "Jobcostingandpayment.findByCostingapproved", query = "SELECT j FROM Jobcostingandpayment j WHERE j.costingapproved = :costingapproved")
    , @NamedQuery(name = "Jobcostingandpayment.findByJobid", query = "SELECT j FROM Jobcostingandpayment j WHERE j.jobid = :jobid")
    , @NamedQuery(name = "Jobcostingandpayment.findByEstimatedcostdoneby", query = "SELECT j FROM Jobcostingandpayment j WHERE j.estimatedcostdoneby = :estimatedcostdoneby")
    , @NamedQuery(name = "Jobcostingandpayment.findByDeposit", query = "SELECT j FROM Jobcostingandpayment j WHERE j.deposit = :deposit")
    , @NamedQuery(name = "Jobcostingandpayment.findByFinalcost", query = "SELECT j FROM Jobcostingandpayment j WHERE j.finalcost = :finalcost")
    , @NamedQuery(name = "Jobcostingandpayment.findByFinalcostdoneby", query = "SELECT j FROM Jobcostingandpayment j WHERE j.finalcostdoneby = :finalcostdoneby")
    , @NamedQuery(name = "Jobcostingandpayment.findByInvoicenumber", query = "SELECT j FROM Jobcostingandpayment j WHERE j.invoicenumber = :invoicenumber")
    , @NamedQuery(name = "Jobcostingandpayment.findByDateoflastpayment", query = "SELECT j FROM Jobcostingandpayment j WHERE j.dateoflastpayment = :dateoflastpayment")
    , @NamedQuery(name = "Jobcostingandpayment.findByEstimatedcost", query = "SELECT j FROM Jobcostingandpayment j WHERE j.estimatedcost = :estimatedcost")
    , @NamedQuery(name = "Jobcostingandpayment.findByDiscount", query = "SELECT j FROM Jobcostingandpayment j WHERE j.discount = :discount")
    , @NamedQuery(name = "Jobcostingandpayment.findByPurchaseordernumber", query = "SELECT j FROM Jobcostingandpayment j WHERE j.purchaseordernumber = :purchaseordernumber")
    , @NamedQuery(name = "Jobcostingandpayment.findByName", query = "SELECT j FROM Jobcostingandpayment j WHERE j.name = :name")
    , @NamedQuery(name = "Jobcostingandpayment.findByPaymentterms", query = "SELECT j FROM Jobcostingandpayment j WHERE j.paymentterms = :paymentterms")
    , @NamedQuery(name = "Jobcostingandpayment.findByReceiptnumber", query = "SELECT j FROM Jobcostingandpayment j WHERE j.receiptnumber = :receiptnumber")
    , @NamedQuery(name = "Jobcostingandpayment.findByCostingcompleted", query = "SELECT j FROM Jobcostingandpayment j WHERE j.costingcompleted = :costingcompleted")
    , @NamedQuery(name = "Jobcostingandpayment.findByDepositdate", query = "SELECT j FROM Jobcostingandpayment j WHERE j.depositdate = :depositdate")
    , @NamedQuery(name = "Jobcostingandpayment.findByCompleted", query = "SELECT j FROM Jobcostingandpayment j WHERE j.completed = :completed")
    , @NamedQuery(name = "Jobcostingandpayment.findByInvoiced", query = "SELECT j FROM Jobcostingandpayment j WHERE j.invoiced = :invoiced")
    , @NamedQuery(name = "Jobcostingandpayment.findByAmountdue", query = "SELECT j FROM Jobcostingandpayment j WHERE j.amountdue = :amountdue")
    , @NamedQuery(name = "Jobcostingandpayment.findByTotaltax", query = "SELECT j FROM Jobcostingandpayment j WHERE j.totaltax = :totaltax")
    , @NamedQuery(name = "Jobcostingandpayment.findByTotalcost", query = "SELECT j FROM Jobcostingandpayment j WHERE j.totalcost = :totalcost")
    , @NamedQuery(name = "Jobcostingandpayment.findByLastpaymententeredbyId", query = "SELECT j FROM Jobcostingandpayment j WHERE j.lastpaymententeredbyId = :lastpaymententeredbyId")
    , @NamedQuery(name = "Jobcostingandpayment.findByPercentagegct", query = "SELECT j FROM Jobcostingandpayment j WHERE j.percentagegct = :percentagegct")
    , @NamedQuery(name = "Jobcostingandpayment.findByDiscounttype", query = "SELECT j FROM Jobcostingandpayment j WHERE j.discounttype = :discounttype")})
public class Jobcostingandpayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COSTINGDATE")
    @Temporal(TemporalType.DATE)
    private Date costingdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PAYMENTRECEIVEDTODATE")
    private Double paymentreceivedtodate;
    @Column(name = "MINDEPOSIT")
    private Double mindeposit;
    @Column(name = "COSTINGAPPROVED")
    private Boolean costingapproved;
    @Column(name = "JOBID")
    private BigInteger jobid;
    @Column(name = "ESTIMATEDCOSTDONEBY")
    private String estimatedcostdoneby;
    @Column(name = "DEPOSIT")
    private Double deposit;
    @Column(name = "FINALCOST")
    private Double finalcost;
    @Column(name = "FINALCOSTDONEBY")
    private String finalcostdoneby;
    @Column(name = "INVOICENUMBER")
    private String invoicenumber;
    @Column(name = "DATEOFLASTPAYMENT")
    @Temporal(TemporalType.DATE)
    private Date dateoflastpayment;
    @Column(name = "ESTIMATEDCOST")
    private Double estimatedcost;
    @Column(name = "DISCOUNT")
    private Double discount;
    @Column(name = "PURCHASEORDERNUMBER")
    private String purchaseordernumber;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PAYMENTTERMS")
    private String paymentterms;
    @Column(name = "RECEIPTNUMBER")
    private String receiptnumber;
    @Column(name = "COSTINGCOMPLETED")
    private Boolean costingcompleted;
    @Column(name = "DEPOSITDATE")
    @Temporal(TemporalType.DATE)
    private Date depositdate;
    @Column(name = "COMPLETED")
    private Boolean completed;
    @Column(name = "INVOICED")
    private Boolean invoiced;
    @Column(name = "AMOUNTDUE")
    private Double amountdue;
    @Column(name = "TOTALTAX")
    private Double totaltax;
    @Column(name = "TOTALCOST")
    private Double totalcost;
    @Column(name = "LASTPAYMENTENTEREDBY_ID")
    private BigInteger lastpaymententeredbyId;
    @Column(name = "PERCENTAGEGCT")
    private Double percentagegct;
    @Column(name = "DISCOUNTTYPE")
    private String discounttype;
    @JoinTable(name = "jobcostingandpayment_costcomponent", joinColumns = {
        @JoinColumn(name = "JobCostingAndPayment_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "costComponents_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Costcomponent> costcomponentList;
    @ManyToMany(mappedBy = "jobcostingandpaymentList")
    private List<Jobcosting> jobcostingList;
    @JoinTable(name = "jobcostingandpayment_cashpayment", joinColumns = {
        @JoinColumn(name = "JobCostingAndPayment_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "cashPayments_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Cashpayment> cashpaymentList;
    @OneToMany(mappedBy = "jobcostingandpaymentId")
    private List<Job> jobList;
    @JoinColumn(name = "COSTINGAPPROVEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee costingapprovedbyId;

    public Jobcostingandpayment() {
    }

    public Jobcostingandpayment(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCostingdate() {
        return costingdate;
    }

    public void setCostingdate(Date costingdate) {
        this.costingdate = costingdate;
    }

    public Double getPaymentreceivedtodate() {
        return paymentreceivedtodate;
    }

    public void setPaymentreceivedtodate(Double paymentreceivedtodate) {
        this.paymentreceivedtodate = paymentreceivedtodate;
    }

    public Double getMindeposit() {
        return mindeposit;
    }

    public void setMindeposit(Double mindeposit) {
        this.mindeposit = mindeposit;
    }

    public Boolean getCostingapproved() {
        return costingapproved;
    }

    public void setCostingapproved(Boolean costingapproved) {
        this.costingapproved = costingapproved;
    }

    public BigInteger getJobid() {
        return jobid;
    }

    public void setJobid(BigInteger jobid) {
        this.jobid = jobid;
    }

    public String getEstimatedcostdoneby() {
        return estimatedcostdoneby;
    }

    public void setEstimatedcostdoneby(String estimatedcostdoneby) {
        this.estimatedcostdoneby = estimatedcostdoneby;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getFinalcost() {
        return finalcost;
    }

    public void setFinalcost(Double finalcost) {
        this.finalcost = finalcost;
    }

    public String getFinalcostdoneby() {
        return finalcostdoneby;
    }

    public void setFinalcostdoneby(String finalcostdoneby) {
        this.finalcostdoneby = finalcostdoneby;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public Date getDateoflastpayment() {
        return dateoflastpayment;
    }

    public void setDateoflastpayment(Date dateoflastpayment) {
        this.dateoflastpayment = dateoflastpayment;
    }

    public Double getEstimatedcost() {
        return estimatedcost;
    }

    public void setEstimatedcost(Double estimatedcost) {
        this.estimatedcost = estimatedcost;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPurchaseordernumber() {
        return purchaseordernumber;
    }

    public void setPurchaseordernumber(String purchaseordernumber) {
        this.purchaseordernumber = purchaseordernumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentterms() {
        return paymentterms;
    }

    public void setPaymentterms(String paymentterms) {
        this.paymentterms = paymentterms;
    }

    public String getReceiptnumber() {
        return receiptnumber;
    }

    public void setReceiptnumber(String receiptnumber) {
        this.receiptnumber = receiptnumber;
    }

    public Boolean getCostingcompleted() {
        return costingcompleted;
    }

    public void setCostingcompleted(Boolean costingcompleted) {
        this.costingcompleted = costingcompleted;
    }

    public Date getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(Date depositdate) {
        this.depositdate = depositdate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getInvoiced() {
        return invoiced;
    }

    public void setInvoiced(Boolean invoiced) {
        this.invoiced = invoiced;
    }

    public Double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(Double amountdue) {
        this.amountdue = amountdue;
    }

    public Double getTotaltax() {
        return totaltax;
    }

    public void setTotaltax(Double totaltax) {
        this.totaltax = totaltax;
    }

    public Double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
    }

    public BigInteger getLastpaymententeredbyId() {
        return lastpaymententeredbyId;
    }

    public void setLastpaymententeredbyId(BigInteger lastpaymententeredbyId) {
        this.lastpaymententeredbyId = lastpaymententeredbyId;
    }

    public Double getPercentagegct() {
        return percentagegct;
    }

    public void setPercentagegct(Double percentagegct) {
        this.percentagegct = percentagegct;
    }

    public String getDiscounttype() {
        return discounttype;
    }

    public void setDiscounttype(String discounttype) {
        this.discounttype = discounttype;
    }

    @XmlTransient
    public List<Costcomponent> getCostcomponentList() {
        return costcomponentList;
    }

    public void setCostcomponentList(List<Costcomponent> costcomponentList) {
        this.costcomponentList = costcomponentList;
    }

    @XmlTransient
    public List<Jobcosting> getJobcostingList() {
        return jobcostingList;
    }

    public void setJobcostingList(List<Jobcosting> jobcostingList) {
        this.jobcostingList = jobcostingList;
    }

    @XmlTransient
    public List<Cashpayment> getCashpaymentList() {
        return cashpaymentList;
    }

    public void setCashpaymentList(List<Cashpayment> cashpaymentList) {
        this.cashpaymentList = cashpaymentList;
    }

    @XmlTransient
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public Employee getCostingapprovedbyId() {
        return costingapprovedbyId;
    }

    public void setCostingapprovedbyId(Employee costingapprovedbyId) {
        this.costingapprovedbyId = costingapprovedbyId;
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
        if (!(object instanceof Jobcostingandpayment)) {
            return false;
        }
        Jobcostingandpayment other = (Jobcostingandpayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Jobcostingandpayment[ id=" + id + " ]";
    }
    
}
