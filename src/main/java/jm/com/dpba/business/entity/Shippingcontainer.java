/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "shippingcontainer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shippingcontainer.findAll", query = "SELECT s FROM Shippingcontainer s"),
    @NamedQuery(name = "Shippingcontainer.findById", query = "SELECT s FROM Shippingcontainer s WHERE s.id = :id"),
    @NamedQuery(name = "Shippingcontainer.findByH", query = "SELECT s FROM Shippingcontainer s WHERE s.h = :h"),
    @NamedQuery(name = "Shippingcontainer.findByL", query = "SELECT s FROM Shippingcontainer s WHERE s.l = :l"),
    @NamedQuery(name = "Shippingcontainer.findByW", query = "SELECT s FROM Shippingcontainer s WHERE s.w = :w"),
    @NamedQuery(name = "Shippingcontainer.findByDimensionunit", query = "SELECT s FROM Shippingcontainer s WHERE s.dimensionunit = :dimensionunit"),
    @NamedQuery(name = "Shippingcontainer.findByName", query = "SELECT s FROM Shippingcontainer s WHERE s.name = :name"),
    @NamedQuery(name = "Shippingcontainer.findByNumber", query = "SELECT s FROM Shippingcontainer s WHERE s.number = :number"),
    @NamedQuery(name = "Shippingcontainer.findByPercentagedetained", query = "SELECT s FROM Shippingcontainer s WHERE s.percentagedetained = :percentagedetained"),
    @NamedQuery(name = "Shippingcontainer.findBySizedescription", query = "SELECT s FROM Shippingcontainer s WHERE s.sizedescription = :sizedescription"),
    @NamedQuery(name = "Shippingcontainer.findByType", query = "SELECT s FROM Shippingcontainer s WHERE s.type = :type")})
public class Shippingcontainer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "H")
    private Integer h;
    @Column(name = "L")
    private Integer l;
    @Column(name = "W")
    private Integer w;
    @Size(max = 255)
    @Column(name = "DIMENSIONUNIT")
    private String dimensionunit;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PERCENTAGEDETAINED")
    private Double percentagedetained;
    @Size(max = 255)
    @Column(name = "SIZEDESCRIPTION")
    private String sizedescription;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @ManyToMany(mappedBy = "shippingcontainerList")
    private List<Entrydocumentinspection> entrydocumentinspectionList;

    public Shippingcontainer() {
    }

    public Shippingcontainer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public String getDimensionunit() {
        return dimensionunit;
    }

    public void setDimensionunit(String dimensionunit) {
        this.dimensionunit = dimensionunit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getPercentagedetained() {
        return percentagedetained;
    }

    public void setPercentagedetained(Double percentagedetained) {
        this.percentagedetained = percentagedetained;
    }

    public String getSizedescription() {
        return sizedescription;
    }

    public void setSizedescription(String sizedescription) {
        this.sizedescription = sizedescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Entrydocumentinspection> getEntrydocumentinspectionList() {
        return entrydocumentinspectionList;
    }

    public void setEntrydocumentinspectionList(List<Entrydocumentinspection> entrydocumentinspectionList) {
        this.entrydocumentinspectionList = entrydocumentinspectionList;
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
        if (!(object instanceof Shippingcontainer)) {
            return false;
        }
        Shippingcontainer other = (Shippingcontainer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Shippingcontainer[ id=" + id + " ]";
    }
    
}
