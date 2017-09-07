/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "cashpayment")
@NamedQueries({    
    @NamedQuery(name = "findAllCashPayments", query = "SELECT e FROM CashPayment e ORDER BY e.type")
})
public class CashPayment implements Serializable, BusinessEntity {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String type = "";    
    private Long jobId = null;
    private Double payment = null;    
    private String receiptNumber = "";    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfPayment = null;    
    private String payeeTitle = "";   
    private String payeeFirstname = "";    
    private String payeeLastname = "";    
    private String comment = "";    
    private Long JMTSUserId = null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getJMTSUserId() {
        return JMTSUserId;
    }

    public void setJMTSUserId(Long JMTSUserId) {
        this.JMTSUserId = JMTSUserId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getPayeeTitle() {
        return payeeTitle;
    }

    public void setPayeeTitle(String payeeTitle) {
        this.payeeTitle = payeeTitle;
    }

    public String getPayeeFirstname() {
        return payeeFirstname;
    }

    public void setPayeeFirstname(String payeeFirstname) {
        this.payeeFirstname = payeeFirstname;
    }

    public String getPayeeLastname() {
        return payeeLastname;
    }

    public void setPayeeLastname(String payeeLastname) {
        this.payeeLastname = payeeLastname;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getReceiptNumber() {
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
        if (!(object instanceof CashPayment)) {
            return false;
        }
        CashPayment other = (CashPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return type + "(" + payment + ")";
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}