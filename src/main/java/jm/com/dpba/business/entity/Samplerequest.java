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
import javax.persistence.JoinTable;
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
@Table(name = "samplerequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Samplerequest.findAll", query = "SELECT s FROM Samplerequest s"),
    @NamedQuery(name = "Samplerequest.findById", query = "SELECT s FROM Samplerequest s WHERE s.id = :id"),
    @NamedQuery(name = "Samplerequest.findByName", query = "SELECT s FROM Samplerequest s WHERE s.name = :name"),
    @NamedQuery(name = "Samplerequest.findByType", query = "SELECT s FROM Samplerequest s WHERE s.type = :type"),
    @NamedQuery(name = "Samplerequest.findByComments", query = "SELECT s FROM Samplerequest s WHERE s.comments = :comments"),
    @NamedQuery(name = "Samplerequest.findByDateofrequest", query = "SELECT s FROM Samplerequest s WHERE s.dateofrequest = :dateofrequest")})
public class Samplerequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "DATEOFREQUEST")
    @Temporal(TemporalType.DATE)
    private Date dateofrequest;
    @JoinTable(name = "samplerequest_marketproduct", joinColumns = {
        @JoinColumn(name = "SampleRequest_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Marketproduct> marketproductList;
    @ManyToMany(mappedBy = "samplerequestList")
    private List<Productinspection> productinspectionList;
    @JoinColumn(name = "INSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee inspectorId;
    @JoinColumn(name = "RECEIVEDFROM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client receivedfromId;
    @JoinColumn(name = "REPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact representativeId;

    public Samplerequest() {
    }

    public Samplerequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateofrequest() {
        return dateofrequest;
    }

    public void setDateofrequest(Date dateofrequest) {
        this.dateofrequest = dateofrequest;
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

    public Employee getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Employee inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Client getReceivedfromId() {
        return receivedfromId;
    }

    public void setReceivedfromId(Client receivedfromId) {
        this.receivedfromId = receivedfromId;
    }

    public Contact getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(Contact representativeId) {
        this.representativeId = representativeId;
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
        if (!(object instanceof Samplerequest)) {
            return false;
        }
        Samplerequest other = (Samplerequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Samplerequest[ id=" + id + " ]";
    }
    
}
