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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "cashpayment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cashpayment.findAll", query = "SELECT c FROM Cashpayment c")
    , @NamedQuery(name = "Cashpayment.findById", query = "SELECT c FROM Cashpayment c WHERE c.id = :id")
    , @NamedQuery(name = "Cashpayment.findByPayeetitle", query = "SELECT c FROM Cashpayment c WHERE c.payeetitle = :payeetitle")
    , @NamedQuery(name = "Cashpayment.findByJobid", query = "SELECT c FROM Cashpayment c WHERE c.jobid = :jobid")
    , @NamedQuery(name = "Cashpayment.findByPayment", query = "SELECT c FROM Cashpayment c WHERE c.payment = :payment")
    , @NamedQuery(name = "Cashpayment.findByPayeefirstname", query = "SELECT c FROM Cashpayment c WHERE c.payeefirstname = :payeefirstname")
    , @NamedQuery(name = "Cashpayment.findByPayeelastname", query = "SELECT c FROM Cashpayment c WHERE c.payeelastname = :payeelastname")
    , @NamedQuery(name = "Cashpayment.findByJmtsuserid", query = "SELECT c FROM Cashpayment c WHERE c.jmtsuserid = :jmtsuserid")
    , @NamedQuery(name = "Cashpayment.findByType", query = "SELECT c FROM Cashpayment c WHERE c.type = :type")
    , @NamedQuery(name = "Cashpayment.findByComment", query = "SELECT c FROM Cashpayment c WHERE c.comment = :comment")
    , @NamedQuery(name = "Cashpayment.findByReceiptnumber", query = "SELECT c FROM Cashpayment c WHERE c.receiptnumber = :receiptnumber")
    , @NamedQuery(name = "Cashpayment.findByDateofpayment", query = "SELECT c FROM Cashpayment c WHERE c.dateofpayment = :dateofpayment")})
public class Cashpayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PAYEETITLE")
    private String payeetitle;
    @Column(name = "JOBID")
    private BigInteger jobid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PAYMENT")
    private Double payment;
    @Column(name = "PAYEEFIRSTNAME")
    private String payeefirstname;
    @Column(name = "PAYEELASTNAME")
    private String payeelastname;
    @Column(name = "JMTSUSERID")
    private BigInteger jmtsuserid;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "RECEIPTNUMBER")
    private String receiptnumber;
    @Column(name = "DATEOFPAYMENT")
    @Temporal(TemporalType.DATE)
    private Date dateofpayment;
    @ManyToMany(mappedBy = "cashpaymentList")
    private List<Jobcostingandpayment> jobcostingandpaymentList;

    public Cashpayment() {
    }

    public Cashpayment(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayeetitle() {
        return payeetitle;
    }

    public void setPayeetitle(String payeetitle) {
        this.payeetitle = payeetitle;
    }

    public BigInteger getJobid() {
        return jobid;
    }

    public void setJobid(BigInteger jobid) {
        this.jobid = jobid;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getPayeefirstname() {
        return payeefirstname;
    }

    public void setPayeefirstname(String payeefirstname) {
        this.payeefirstname = payeefirstname;
    }

    public String getPayeelastname() {
        return payeelastname;
    }

    public void setPayeelastname(String payeelastname) {
        this.payeelastname = payeelastname;
    }

    public BigInteger getJmtsuserid() {
        return jmtsuserid;
    }

    public void setJmtsuserid(BigInteger jmtsuserid) {
        this.jmtsuserid = jmtsuserid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReceiptnumber() {
        return receiptnumber;
    }

    public void setReceiptnumber(String receiptnumber) {
        this.receiptnumber = receiptnumber;
    }

    public Date getDateofpayment() {
        return dateofpayment;
    }

    public void setDateofpayment(Date dateofpayment) {
        this.dateofpayment = dateofpayment;
    }

    @XmlTransient
    public List<Jobcostingandpayment> getJobcostingandpaymentList() {
        return jobcostingandpaymentList;
    }

    public void setJobcostingandpaymentList(List<Jobcostingandpayment> jobcostingandpaymentList) {
        this.jobcostingandpaymentList = jobcostingandpaymentList;
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
        if (!(object instanceof Cashpayment)) {
            return false;
        }
        Cashpayment other = (Cashpayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Cashpayment[ id=" + id + " ]";
    }
    
}
