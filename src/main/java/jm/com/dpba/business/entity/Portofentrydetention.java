/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "portofentrydetention")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portofentrydetention.findAll", query = "SELECT p FROM Portofentrydetention p"),
    @NamedQuery(name = "Portofentrydetention.findById", query = "SELECT p FROM Portofentrydetention p WHERE p.id = :id"),
    @NamedQuery(name = "Portofentrydetention.findByCountryofconsignment", query = "SELECT p FROM Portofentrydetention p WHERE p.countryofconsignment = :countryofconsignment"),
    @NamedQuery(name = "Portofentrydetention.findByDateinspected", query = "SELECT p FROM Portofentrydetention p WHERE p.dateinspected = :dateinspected"),
    @NamedQuery(name = "Portofentrydetention.findByReferencenumber", query = "SELECT p FROM Portofentrydetention p WHERE p.referencenumber = :referencenumber"),
    @NamedQuery(name = "Portofentrydetention.findByDateauthorized", query = "SELECT p FROM Portofentrydetention p WHERE p.dateauthorized = :dateauthorized"),
    @NamedQuery(name = "Portofentrydetention.findByReasonfordetention", query = "SELECT p FROM Portofentrydetention p WHERE p.reasonfordetention = :reasonfordetention"),
    @NamedQuery(name = "Portofentrydetention.findByEntrydocumentnumber", query = "SELECT p FROM Portofentrydetention p WHERE p.entrydocumentnumber = :entrydocumentnumber"),
    @NamedQuery(name = "Portofentrydetention.findByInspectionpoint", query = "SELECT p FROM Portofentrydetention p WHERE p.inspectionpoint = :inspectionpoint"),
    @NamedQuery(name = "Portofentrydetention.findByContainernumber", query = "SELECT p FROM Portofentrydetention p WHERE p.containernumber = :containernumber"),
    @NamedQuery(name = "Portofentrydetention.findByName", query = "SELECT p FROM Portofentrydetention p WHERE p.name = :name"),
    @NamedQuery(name = "Portofentrydetention.findByQuantity", query = "SELECT p FROM Portofentrydetention p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Portofentrydetention.findByReportdate", query = "SELECT p FROM Portofentrydetention p WHERE p.reportdate = :reportdate"),
    @NamedQuery(name = "Portofentrydetention.findByNumberofsamplestaken", query = "SELECT p FROM Portofentrydetention p WHERE p.numberofsamplestaken = :numberofsamplestaken")})
public class Portofentrydetention implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "COUNTRYOFCONSIGNMENT")
    private String countryofconsignment;
    @Column(name = "DATEINSPECTED")
    @Temporal(TemporalType.DATE)
    private Date dateinspected;
    @Size(max = 255)
    @Column(name = "REFERENCENUMBER")
    private String referencenumber;
    @Column(name = "DATEAUTHORIZED")
    @Temporal(TemporalType.DATE)
    private Date dateauthorized;
    @Size(max = 1024)
    @Column(name = "REASONFORDETENTION")
    private String reasonfordetention;
    @Size(max = 255)
    @Column(name = "ENTRYDOCUMENTNUMBER")
    private String entrydocumentnumber;
    @Size(max = 255)
    @Column(name = "INSPECTIONPOINT")
    private String inspectionpoint;
    @Size(max = 255)
    @Column(name = "CONTAINERNUMBER")
    private String containernumber;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "QUANTITY")
    private String quantity;
    @Column(name = "REPORTDATE")
    @Temporal(TemporalType.DATE)
    private Date reportdate;
    @Column(name = "NUMBEROFSAMPLESTAKEN")
    private Integer numberofsamplestaken;
    @ManyToMany(mappedBy = "portofentrydetentionList")
    private List<Marketproduct> marketproductList;
    @ManyToMany(mappedBy = "portofentrydetentionList")
    private List<Productinspection> productinspectionList;
    @JoinColumn(name = "AUTHORIZEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact authorizedbyId;
    @JoinColumn(name = "BROKER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client brokerId;
    @JoinColumn(name = "CONSIGNEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client consigneeId;
    @JoinColumn(name = "INSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee inspectorId;
    @JoinColumn(name = "BROKERREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact brokerrepresentativeId;
    @JoinColumn(name = "CONSIGNEEREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact consigneerepresentativeId;

    public Portofentrydetention() {
    }

    public Portofentrydetention(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryofconsignment() {
        return countryofconsignment;
    }

    public void setCountryofconsignment(String countryofconsignment) {
        this.countryofconsignment = countryofconsignment;
    }

    public Date getDateinspected() {
        return dateinspected;
    }

    public void setDateinspected(Date dateinspected) {
        this.dateinspected = dateinspected;
    }

    public String getReferencenumber() {
        return referencenumber;
    }

    public void setReferencenumber(String referencenumber) {
        this.referencenumber = referencenumber;
    }

    public Date getDateauthorized() {
        return dateauthorized;
    }

    public void setDateauthorized(Date dateauthorized) {
        this.dateauthorized = dateauthorized;
    }

    public String getReasonfordetention() {
        return reasonfordetention;
    }

    public void setReasonfordetention(String reasonfordetention) {
        this.reasonfordetention = reasonfordetention;
    }

    public String getEntrydocumentnumber() {
        return entrydocumentnumber;
    }

    public void setEntrydocumentnumber(String entrydocumentnumber) {
        this.entrydocumentnumber = entrydocumentnumber;
    }

    public String getInspectionpoint() {
        return inspectionpoint;
    }

    public void setInspectionpoint(String inspectionpoint) {
        this.inspectionpoint = inspectionpoint;
    }

    public String getContainernumber() {
        return containernumber;
    }

    public void setContainernumber(String containernumber) {
        this.containernumber = containernumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public Integer getNumberofsamplestaken() {
        return numberofsamplestaken;
    }

    public void setNumberofsamplestaken(Integer numberofsamplestaken) {
        this.numberofsamplestaken = numberofsamplestaken;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproduct> getMarketproductList() {
        return marketproductList;
    }

    public void setMarketproductList(List<Marketproduct> marketproductList) {
        this.marketproductList = marketproductList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Productinspection> getProductinspectionList() {
        return productinspectionList;
    }

    public void setProductinspectionList(List<Productinspection> productinspectionList) {
        this.productinspectionList = productinspectionList;
    }

    public Contact getAuthorizedbyId() {
        return authorizedbyId;
    }

    public void setAuthorizedbyId(Contact authorizedbyId) {
        this.authorizedbyId = authorizedbyId;
    }

    public Client getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Client brokerId) {
        this.brokerId = brokerId;
    }

    public Client getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Client consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Employee getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Employee inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Contact getBrokerrepresentativeId() {
        return brokerrepresentativeId;
    }

    public void setBrokerrepresentativeId(Contact brokerrepresentativeId) {
        this.brokerrepresentativeId = brokerrepresentativeId;
    }

    public Contact getConsigneerepresentativeId() {
        return consigneerepresentativeId;
    }

    public void setConsigneerepresentativeId(Contact consigneerepresentativeId) {
        this.consigneerepresentativeId = consigneerepresentativeId;
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
        if (!(object instanceof Portofentrydetention)) {
            return false;
        }
        Portofentrydetention other = (Portofentrydetention) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Portofentrydetention[ id=" + id + " ]";
    }
    
}
