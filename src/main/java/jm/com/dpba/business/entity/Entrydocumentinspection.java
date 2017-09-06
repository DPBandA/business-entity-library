/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "entrydocumentinspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrydocumentinspection.findAll", query = "SELECT e FROM Entrydocumentinspection e")
    , @NamedQuery(name = "Entrydocumentinspection.findById", query = "SELECT e FROM Entrydocumentinspection e WHERE e.id = :id")
    , @NamedQuery(name = "Entrydocumentinspection.findByCif", query = "SELECT e FROM Entrydocumentinspection e WHERE e.cif = :cif")
    , @NamedQuery(name = "Entrydocumentinspection.findByScf", query = "SELECT e FROM Entrydocumentinspection e WHERE e.scf = :scf")
    , @NamedQuery(name = "Entrydocumentinspection.findByContainernumbers", query = "SELECT e FROM Entrydocumentinspection e WHERE e.containernumbers = :containernumbers")
    , @NamedQuery(name = "Entrydocumentinspection.findByCountryofconsignment", query = "SELECT e FROM Entrydocumentinspection e WHERE e.countryofconsignment = :countryofconsignment")
    , @NamedQuery(name = "Entrydocumentinspection.findByDocumentstamped", query = "SELECT e FROM Entrydocumentinspection e WHERE e.documentstamped = :documentstamped")
    , @NamedQuery(name = "Entrydocumentinspection.findByEntrydocumentnumber", query = "SELECT e FROM Entrydocumentinspection e WHERE e.entrydocumentnumber = :entrydocumentnumber")
    , @NamedQuery(name = "Entrydocumentinspection.findByEntrydocumentreportdate", query = "SELECT e FROM Entrydocumentinspection e WHERE e.entrydocumentreportdate = :entrydocumentreportdate")
    , @NamedQuery(name = "Entrydocumentinspection.findByName", query = "SELECT e FROM Entrydocumentinspection e WHERE e.name = :name")
    , @NamedQuery(name = "Entrydocumentinspection.findByVessel", query = "SELECT e FROM Entrydocumentinspection e WHERE e.vessel = :vessel")
    , @NamedQuery(name = "Entrydocumentinspection.findByWaybill", query = "SELECT e FROM Entrydocumentinspection e WHERE e.waybill = :waybill")
    , @NamedQuery(name = "Entrydocumentinspection.findByInternalnumber", query = "SELECT e FROM Entrydocumentinspection e WHERE e.internalnumber = :internalnumber")
    , @NamedQuery(name = "Entrydocumentinspection.findByInvoicenumber", query = "SELECT e FROM Entrydocumentinspection e WHERE e.invoicenumber = :invoicenumber")
    , @NamedQuery(name = "Entrydocumentinspection.findByInvoicedate", query = "SELECT e FROM Entrydocumentinspection e WHERE e.invoicedate = :invoicedate")
    , @NamedQuery(name = "Entrydocumentinspection.findByScfamountcalculated", query = "SELECT e FROM Entrydocumentinspection e WHERE e.scfamountcalculated = :scfamountcalculated")
    , @NamedQuery(name = "Entrydocumentinspection.findByScffreecode", query = "SELECT e FROM Entrydocumentinspection e WHERE e.scffreecode = :scffreecode")})
public class Entrydocumentinspection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CIF")
    private Double cif;
    @Column(name = "SCF")
    private Double scf;
    @Column(name = "CONTAINERNUMBERS")
    private String containernumbers;
    @Column(name = "COUNTRYOFCONSIGNMENT")
    private String countryofconsignment;
    @Column(name = "DOCUMENTSTAMPED")
    private String documentstamped;
    @Column(name = "ENTRYDOCUMENTNUMBER")
    private String entrydocumentnumber;
    @Column(name = "ENTRYDOCUMENTREPORTDATE")
    @Temporal(TemporalType.DATE)
    private Date entrydocumentreportdate;
    @Column(name = "NAME")
    private String name;
    @Column(name = "VESSEL")
    private String vessel;
    @Column(name = "WAYBILL")
    private String waybill;
    @Column(name = "INTERNALNUMBER")
    private String internalnumber;
    @Column(name = "INVOICENUMBER")
    private String invoicenumber;
    @Column(name = "INVOICEDATE")
    @Temporal(TemporalType.DATE)
    private Date invoicedate;
    @Column(name = "SCFAMOUNTCALCULATED")
    private Double scfamountcalculated;
    @Column(name = "SCFFREECODE")
    private String scffreecode;
    @OneToMany(mappedBy = "entrydocumentinspectionId")
    private List<Compliancesurvey> compliancesurveyList;

    public Entrydocumentinspection() {
    }

    public Entrydocumentinspection(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCif() {
        return cif;
    }

    public void setCif(Double cif) {
        this.cif = cif;
    }

    public Double getScf() {
        return scf;
    }

    public void setScf(Double scf) {
        this.scf = scf;
    }

    public String getContainernumbers() {
        return containernumbers;
    }

    public void setContainernumbers(String containernumbers) {
        this.containernumbers = containernumbers;
    }

    public String getCountryofconsignment() {
        return countryofconsignment;
    }

    public void setCountryofconsignment(String countryofconsignment) {
        this.countryofconsignment = countryofconsignment;
    }

    public String getDocumentstamped() {
        return documentstamped;
    }

    public void setDocumentstamped(String documentstamped) {
        this.documentstamped = documentstamped;
    }

    public String getEntrydocumentnumber() {
        return entrydocumentnumber;
    }

    public void setEntrydocumentnumber(String entrydocumentnumber) {
        this.entrydocumentnumber = entrydocumentnumber;
    }

    public Date getEntrydocumentreportdate() {
        return entrydocumentreportdate;
    }

    public void setEntrydocumentreportdate(Date entrydocumentreportdate) {
        this.entrydocumentreportdate = entrydocumentreportdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getInternalnumber() {
        return internalnumber;
    }

    public void setInternalnumber(String internalnumber) {
        this.internalnumber = internalnumber;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Double getScfamountcalculated() {
        return scfamountcalculated;
    }

    public void setScfamountcalculated(Double scfamountcalculated) {
        this.scfamountcalculated = scfamountcalculated;
    }

    public String getScffreecode() {
        return scffreecode;
    }

    public void setScffreecode(String scffreecode) {
        this.scffreecode = scffreecode;
    }

    @XmlTransient
    public List<Compliancesurvey> getCompliancesurveyList() {
        return compliancesurveyList;
    }

    public void setCompliancesurveyList(List<Compliancesurvey> compliancesurveyList) {
        this.compliancesurveyList = compliancesurveyList;
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
        if (!(object instanceof Entrydocumentinspection)) {
            return false;
        }
        Entrydocumentinspection other = (Entrydocumentinspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Entrydocumentinspection[ id=" + id + " ]";
    }
    
}
