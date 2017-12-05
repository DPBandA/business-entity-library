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
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

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
    private Long id;
    private String type;
    private Long jobId;
    private Double payment;
    private String receiptNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfPayment;
    private String payeeTitle;
    private String payeeFirstname;
    private String payeeLastname;
    private String comment;
    private Long userId;
    private Double discount;
    private String discountType;
    private String paymentTerms;

    public CashPayment() {
        this.discount = 0.0;
        this.comment = "";
        this.payeeLastname = "";
        this.payeeFirstname = "";
        this.payeeTitle = "";
        this.receiptNumber = "";
        this.payment = 0.0;
        this.type = "Cash";        
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
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

    public Double getDiscount() {
        if (discount == null) {
            discount = 0.0;
        }
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
