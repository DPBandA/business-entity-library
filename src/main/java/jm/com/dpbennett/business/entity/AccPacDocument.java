/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "AROBL")
public class AccPacDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 22, name = "IDINVC")
    private String id;
    @Column(length = 12, name = "IDCUST")
    private String idCust;
    @Column(name = "AMTDUETC", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal custCurrencyAmountDue;
    @Column(name = "AMTDUEHC", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal funcCurrencyAmountDue;
    @Column(length = 4, name = "FISCYR")
    private String fiscalYear;
    @Column(name = "DATEINVC", columnDefinition = "DECIMAL(9,0)")
    private BigDecimal documentDate;
    @Column(name = "DATEDUE", columnDefinition = "DECIMAL(9,0)")
    private BigDecimal dueDate;
    @Column(name = "AMTINVCHC", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal funcCurrencyInvoiceAmount;
    @Column(name = "AMTINVCTC", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal custCurrencyInvoiceAmount;
    @Column(name = "DATEPAID", columnDefinition = "DECIMAL(9,0)")
    private BigDecimal datePaid;
    @Column(name = "TRXTYPEID", columnDefinition = "SMALLINT(5,0)")
    private Integer transactionType;
    @Column(name = "TRXTYPETXT", columnDefinition = "SMALLINT(5,0)")
    private Integer documentType;
    @Column(name = "SWPAID", columnDefinition = "SMALLINT(5,0)")
    private Integer fullyPaid;
    // other
//    @Column(name = "AUDTDATE", columnDefinition = "DECIMAL(9,0)")
//    private BigDecimal audtDate;
//    @Column(name = "AUDTTIME", columnDefinition = "DECIMAL(9,0)")
//    private BigDecimal audtTime;
//    @Column(name = "AUDTUSER", length = 8)
//    private String audtUser;
//    @Column(name = "AUDTORG", length = 6)
//    private String audtOrg;
//    @Column(name = "IDRMIT", length = 24)
//    private String IDRMIT;
//    @Column(name = "IDORDERNBR", length = 22)
//    private String IDORDERNBR;
//    @Column(name = "IDCUSTPO", length = 22)
//    private String IDCUSTPO;
//    @Column(name = "IDNATACCT", length = 12)
//    private String IDNATACCT;
//    @Column(name = "IDCUSTSHPT", length = 6)
//    private String IDCUSTSHPT;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getCustCurrencyAmountDue() {
        return custCurrencyAmountDue;
    }

    public void setCustCurrencyAmountDue(BigDecimal custCurrencyAmountDue) {
        this.custCurrencyAmountDue = custCurrencyAmountDue;
    }

    public Integer getFullyPaid() {
        return fullyPaid;
    }

    public void setFullyPaid(Integer fullyPaid) {
        this.fullyPaid = fullyPaid;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public BigDecimal getCustCurrencyInvoiceAmount() {
        return custCurrencyInvoiceAmount;
    }

    public void setCustCurrencyInvoiceAmount(BigDecimal custCurrencyInvoiceAmount) {
        this.custCurrencyInvoiceAmount = custCurrencyInvoiceAmount;
    }

    public BigDecimal getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(BigDecimal datePaid) {
        this.datePaid = datePaid;
    }

    public Date getFormattedDocumentDate() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            return formatter.parse(documentDate.toString());
        } catch (ParseException ex) {
            return Calendar.getInstance().getTime();
        }
    }

    public BigDecimal getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(BigDecimal documentDate) {
        this.documentDate = documentDate;
    }

    public Date getFormattedDueDate() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            return formatter.parse(dueDate.toString());
        } catch (ParseException ex) {
            return Calendar.getInstance().getTime();
        }
    }

    public BigDecimal getDueDate() {
        return dueDate;
    }

    public void setDueDate(BigDecimal dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getDaysOverdue() {
        Integer daysOverdue = 0;

        //
        Calendar cNow = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            Date due = formatter.parse(dueDate.toString());
            if (cNow.getTime().after(due)) {
                // get calendate date for due date and compare to the current
                // calendar date
                Calendar cDue = Calendar.getInstance();
                cDue.setTime(due);

                while ((cDue.get(Calendar.DAY_OF_MONTH) < cNow.get(Calendar.DAY_OF_MONTH))
                        || (cDue.get(Calendar.MONTH) < cNow.get(Calendar.MONTH))
                        || (cDue.get(Calendar.YEAR) < cNow.get(Calendar.YEAR))) {
                    ++daysOverdue;
                    cDue.add(Calendar.DAY_OF_MONTH, 1);
                }

            } else {
                return daysOverdue;
            }
            // determine days overdue
            //System.out.println(formatter.parse(dateString2));
        } catch (ParseException ex) {
            System.out.println(ex);
            return 0;
        }
        //System.out.println(formatter.parse(dateString2));

        return daysOverdue;
    }

    public Integer getDaysOverDocumentDate() {
        Integer daysOverDocumentDate = 0;

        Calendar cNow = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            Date docDate = formatter.parse(documentDate.toString());
            if (cNow.getTime().after(docDate)) {
                // get calendate date for document date and compare to the current
                // calendar date
                Calendar cDocDate = Calendar.getInstance();
                cDocDate.setTime(docDate);

                while ((cDocDate.get(Calendar.DAY_OF_MONTH) < cNow.get(Calendar.DAY_OF_MONTH))
                        || (cDocDate.get(Calendar.MONTH) < cNow.get(Calendar.MONTH))
                        || (cDocDate.get(Calendar.YEAR) < cNow.get(Calendar.YEAR))) {
                    ++daysOverDocumentDate;
                    cDocDate.add(Calendar.DAY_OF_MONTH, 1);
                }
            } else {
                return daysOverDocumentDate;
            }
        } catch (ParseException ex) {
            System.out.println(ex);
            return 0;
        }

        return daysOverDocumentDate;
    }

    public String getRangeOfDaysOverDue() {

        Integer days = getDaysOverdue();

        if ((days >= 1) && ((days <= 15))) {
            return "1-15";
        } else if ((days >= 16) && ((days <= 30))) {
            return "16-30";
        } else if ((days >= 31) && ((days <= 45))) {
            return "31-45";
        } else if (days > 45) {
            return "over 45";
        }

        return "current";
    }

    public String getRangeOfDaysOverDocumentDate() {

        Integer days = getDaysOverDocumentDate();

        if ((days >= 1) && ((days <= 15))) {
            return "1-15";
        } else if ((days >= 16) && ((days <= 30))) {
            return "16-30";
        } else if ((days >= 31) && ((days <= 45))) {
            return "31-45";
        } else if (days > 45) {
            return "over 45";
        }

        return "current";
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public BigDecimal getFuncCurrencyInvoiceAmount() {
        return funcCurrencyInvoiceAmount;
    }

    public void setFuncCurrencyInvoiceAmount(BigDecimal funcCurrencyInvoiceAmount) {
        this.funcCurrencyInvoiceAmount = funcCurrencyInvoiceAmount;
    }

    public String getIdCust() {
        return idCust;
    }

    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getFuncCurrencyAmountDue() {
        return funcCurrencyAmountDue;
    }

    public void setFuncCurrencyAmountDue(BigDecimal funcCurrencyAmountDue) {
        this.funcCurrencyAmountDue = funcCurrencyAmountDue;
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
        if (!(object instanceof AccPacDocument)) {
            return false;
        }
        AccPacDocument other = (AccPacDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.AccPacDocument[id=" + id + "]";
    }

    public static List<AccPacDocument> findAccPacDocumentsByCustomerId(EntityManager em, String customerId) {
        try {
            List<AccPacDocument> docs = em.createQuery("SELECT d FROM AccPacDocument d "
                    + "WHERE d.idCust "
                    + "LIKE '" + customerId + "%'", AccPacDocument.class).getResultList();

            if (docs == null) {
                return new ArrayList<>();
            } else {
                return docs;
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<AccPacDocument> findAccPacInvoicesByCustomerId(EntityManager em, String customerId) {
        try {
            List<AccPacDocument> docs = em.createQuery("SELECT d FROM AccPacDocument d "
                    + "WHERE d.idCust "
                    + "LIKE '" + customerId + "%' AND d.documentType = 1", AccPacDocument.class).getResultList();

            if (docs == null) {
                return new ArrayList<>();
            } else {
                return docs;
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    /**
     * NB: Documents with an amount due over 0.0 is assumed to be an invoice.
     *
     * @param em
     * @param customerId
     * @return
     */
    public static List<AccPacDocument> findAccPacInvoicesDueByCustomerId(EntityManager em,
            String customerId, Boolean includePrepayments) {

        List<AccPacDocument> foundsDocs;

        try {
            if (includePrepayments) {
                foundsDocs = em.createQuery("SELECT d FROM AccPacDocument d "
                        + "WHERE d.idCust "
                        + "LIKE '" + customerId + "%' AND d.fullyPaid = 0 ORDER BY d.dueDate DESC", AccPacDocument.class).getResultList();
            } else {
                foundsDocs = em.createQuery("SELECT d FROM AccPacDocument d "
                        + "WHERE d.idCust " // NB: 50 == Prepayment transaction type
                        + "LIKE '" + customerId + "%' AND d.fullyPaid = 0 AND d.transactionType <> 50 ORDER BY d.dueDate DESC", AccPacDocument.class).getResultList();
            }

            if (foundsDocs != null) {
                return foundsDocs;
            } else {
                return new ArrayList<>();
            }

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
