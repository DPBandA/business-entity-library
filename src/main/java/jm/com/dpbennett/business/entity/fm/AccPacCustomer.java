/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

package jm.com.dpbennett.business.entity.fm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 * @author Desmond Bennett
 * @version 1.0
 */
@Entity
@Table(name = "arcus")
public class AccPacCustomer implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(AccPacCustomer.class.getName());
    
    public static List<AccPacCustomer> findAllByName(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<AccPacCustomer> clients;
            clients = em.createQuery(
                    "SELECT a FROM AccPacCustomer a"
                            + " WHERE UPPER(a.customerName)"
                            + " LIKE '" + value.toUpperCase().trim()
                            + "%' ORDER BY a.customerName", AccPacCustomer.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static List<AccPacCustomer> findAllByNameAndId(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<AccPacCustomer> clients;
            clients = em.createQuery(
                    "SELECT a FROM AccPacCustomer a"
                            + " WHERE UPPER(a.customerName) LIKE '" + value.toUpperCase().trim() + "%'"
                                    + " OR UPPER(a.idCust) LIKE '" + value.toUpperCase().trim() + "%'"
                                            + " ORDER BY a.customerName", AccPacCustomer.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static AccPacCustomer findByName(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<AccPacCustomer> customers = em.createQuery(
                    "SELECT a FROM AccPacCustomer a"
                            + " WHERE UPPER(a.customerName)"
                            + " LIKE '" + value.toUpperCase().trim()
                            + "%' ORDER BY a.customerName", AccPacCustomer.class).getResultList();
            
            if (!customers.isEmpty()) {
                return customers.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    @Id
    @Column(length = 12, name = "IDCUST")
    private String idCust;
    @Column(length = 60, name = "NAMECUST")
    private String customerName;
    @Column(name = "AMTCRLIMT", precision = 10, scale = 3)
    private BigDecimal creditLimit;
    @Column(name = "SWBALFWD")
    private Integer accountType;
    @Column(name = "AMTBALDUET", precision = 10, scale = 3)
    private BigDecimal balanceDueInCust;
    @Column(name = "AMTBALDUEH", precision = 10, scale = 3)
    private BigDecimal balanceDueInFunc;
    @Column(name = "DATELASTST")
    private BigDecimal dateOfLastStatement;
    @Column(name = "AMTLASTSTT", precision = 10, scale = 3)
    private BigDecimal lastStatementTotalCust;
    @Column(name = "AMTPDUE", precision = 10, scale = 3)
    private BigDecimal amountPastDue;
    @Column(name = "CNTOPENINV", precision = 4, scale = 0)
    private BigDecimal numberOfOpenDocuments;
    @Column(name = "AMTLASTIVH", precision = 10, scale = 3)
    private BigDecimal lastInvoiceAmt;
    @Column(length = 6, name = "IDACCTSET")
    private String IDACCTSET;    
    @Transient
    private Boolean isDirty;

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

        return new ReturnMessage(false, "Accpac Customer not saved");
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
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
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
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
