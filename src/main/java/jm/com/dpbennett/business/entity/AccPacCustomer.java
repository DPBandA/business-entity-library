/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2018  D P Bennett & Associates Limited

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 * This class encapsulates the fields of the Accpac ARCUS database table as an
 * AccPacCustomer.
 *
 * @author Desmond Bennett
 * @version 1.0
 */
@Entity
@Table(name = "ARCUS")
public class AccPacCustomer implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 12, name = "IDCUST")
    private String idCust;
    @Column(length = 60, name = "NAMECUST")
    private String customerName;
    @Column(name = "AMTCRLIMT", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal creditLimit;
    @Column(name = "SWBALFWD", columnDefinition = "SMALLINT(5,0)")
    private Integer accountType;
    @Column(name = "AMTBALDUET", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal balanceDueInCust;
    @Column(name = "AMTBALDUEH", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal balanceDueInFunc;
    @Column(name = "DATELASTST", columnDefinition = "DECIMAL(9,0)")
    private BigDecimal dateOfLastStatement;
    @Column(name = "AMTLASTSTT", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal lastStatementTotalCust;
    @Column(name = "AMTPDUE", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal amountPastDue;
    @Column(name = "CNTOPENINV", columnDefinition = "DECIMAL(4,0)")
    private BigDecimal numberOfOpenDocuments;
    @Column(name = "AMTLASTIVH", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal lastInvoiceAmt;
    @Column(length = 6, name = "IDACCTSET")
    private String IDACCTSET;
    @Transient
    private Boolean isDirty;

    /**
     * Constructs an AccPacCustomer and initializes important fields.
     */
    public AccPacCustomer() {
        balanceDueInCust = new BigDecimal(0.0);
        balanceDueInFunc = new BigDecimal(0.0);
        creditLimit = new BigDecimal(0.0);
    }

    public AccPacCustomer(String customerName) {
        this.customerName = customerName;
        balanceDueInCust = new BigDecimal(0.0);
        balanceDueInFunc = new BigDecimal(0.0);
        creditLimit = new BigDecimal(0.0);
    }

    public AccPacCustomer(String idCust, String customerName) {
        this.idCust = idCust;
        this.customerName = customerName;
        balanceDueInCust = new BigDecimal(0.0);
        balanceDueInFunc = new BigDecimal(0.0);
        creditLimit = new BigDecimal(0.0);
    }

    public String getIdCust() {
        return idCust;
    }

    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    public String getIDACCTSET() {
        if (IDACCTSET == null) {
            IDACCTSET = "";
        }
        return IDACCTSET;
    }

    public void setIDACCTSET(String IDACCTSET) {
        this.IDACCTSET = IDACCTSET;
    }

    public BigDecimal getAmountPastDue() {
        return amountPastDue;
    }

    public void setAmountPastDue(BigDecimal amountPastDue) {
        this.amountPastDue = amountPastDue;
    }

    public BigDecimal getLastInvoiceAmt() {
        return lastInvoiceAmt;
    }

    public void setLastInvoiceAmt(BigDecimal lastInvoiceAmt) {
        this.lastInvoiceAmt = lastInvoiceAmt;
    }

    public BigDecimal getNumberOfOpenDocuments() {
        return numberOfOpenDocuments;
    }

    public void setNumberOfOpenDocuments(BigDecimal numberOfOpenDocuments) {
        this.numberOfOpenDocuments = numberOfOpenDocuments;
    }

    public BigDecimal getLastStatementTotalCust() {
        return lastStatementTotalCust;
    }

    public void setLastStatementTotalCust(BigDecimal lastStatementTotalCust) {
        this.lastStatementTotalCust = lastStatementTotalCust;
    }

    public BigDecimal getDateOfLastStatement() {
        return dateOfLastStatement;
    }

    public void setDateOfLastStatement(BigDecimal dateOfLastStatement) {
        this.dateOfLastStatement = dateOfLastStatement;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalanceDueInCust() {
        return balanceDueInCust;
    }

    public void setBalanceDueInCust(BigDecimal balanceDueInCust) {
        this.balanceDueInCust = balanceDueInCust;
    }

    public BigDecimal getBalanceDueInFunc() {
        return balanceDueInFunc;
    }

    public void setBalanceDueInFunc(BigDecimal balanceDueInFunc) {
        this.balanceDueInFunc = balanceDueInFunc;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getBalanceDueCreditLimitDifference() {

        if ((balanceDueInFunc != null) && (creditLimit != null)) {
            if (creditLimit.doubleValue() == 0.0) {
                return new BigDecimal(0.0);
            }

            BigDecimal diff = balanceDueInFunc.subtract(creditLimit);
            if (diff.doubleValue() < 0.0) {
                return new BigDecimal(0.0);
            } else {
                return diff;
            }
        } else {
            return new BigDecimal(0.0);
        }
    }

    public String getCustomerName() {
        if (customerName == null) {
            customerName = "";
        }
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCust != null ? idCust.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCust fields are not set
        if (!(object instanceof AccPacCustomer)) {
            return false;
        }
        AccPacCustomer other = (AccPacCustomer) object;
        
        return !((this.idCust == null && other.idCust != null) || (this.idCust != null && !this.idCust.equals(other.idCust)));
    }

    @Override
    public String toString() {
        return getCustomerName();
    }

    public static List<AccPacCustomer> findAccPacCustomersByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccPacCustomer> clients;
            clients = em.createQuery("SELECT a FROM AccPacCustomer a where UPPER(a.customerName) like '"
                    + value.toUpperCase().trim() + "%' ORDER BY a.customerName", AccPacCustomer.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static AccPacCustomer findByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccPacCustomer> customers = em.createQuery("SELECT c FROM AccPacCustomer c "
                    + "WHERE UPPER(c.customerName) "
                    + "LIKE '" + value.toUpperCase() + "%'", AccPacCustomer.class).getResultList();

            if (customers.size() > 0) {
                return customers.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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

    @Override
    public Long getId() {
        if (getIdCust() != null) {
            return 1L;
        }

        return null;
    }

    @Override
    public void setId(Long id) {
    }

    @Override
    public String getName() {
        return getCustomerName();
    }

    @Override
    public void setName(String name) {
        customerName = name;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Accpac customer not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
