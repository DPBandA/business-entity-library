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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "testmeasure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testmeasure.findAll", query = "SELECT t FROM Testmeasure t"),
    @NamedQuery(name = "Testmeasure.findById", query = "SELECT t FROM Testmeasure t WHERE t.id = :id"),
    @NamedQuery(name = "Testmeasure.findByCapacityunit", query = "SELECT t FROM Testmeasure t WHERE t.capacityunit = :capacityunit"),
    @NamedQuery(name = "Testmeasure.findByName", query = "SELECT t FROM Testmeasure t WHERE t.name = :name"),
    @NamedQuery(name = "Testmeasure.findByTolerance", query = "SELECT t FROM Testmeasure t WHERE t.tolerance = :tolerance"),
    @NamedQuery(name = "Testmeasure.findByCapacity", query = "SELECT t FROM Testmeasure t WHERE t.capacity = :capacity"),
    @NamedQuery(name = "Testmeasure.findByType", query = "SELECT t FROM Testmeasure t WHERE t.type = :type")})
public class Testmeasure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CAPACITYUNIT")
    private String capacityunit;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOLERANCE")
    private Double tolerance;
    @Column(name = "CAPACITY")
    private Double capacity;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @OneToMany(mappedBy = "testmeasureId")
    private List<Petrolpumpnozzlecalibrationpoint> petrolpumpnozzlecalibrationpointList;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Testmeasure() {
    }

    public Testmeasure(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCapacityunit() {
        return capacityunit;
    }

    public void setCapacityunit(String capacityunit) {
        this.capacityunit = capacityunit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzlecalibrationpoint> getPetrolpumpnozzlecalibrationpointList() {
        return petrolpumpnozzlecalibrationpointList;
    }

    public void setPetrolpumpnozzlecalibrationpointList(List<Petrolpumpnozzlecalibrationpoint> petrolpumpnozzlecalibrationpointList) {
        this.petrolpumpnozzlecalibrationpointList = petrolpumpnozzlecalibrationpointList;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
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
        if (!(object instanceof Testmeasure)) {
            return false;
        }
        Testmeasure other = (Testmeasure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Testmeasure[ id=" + id + " ]";
    }
    
}
