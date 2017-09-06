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
@Table(name = "scale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scale.findAll", query = "SELECT s FROM Scale s"),
    @NamedQuery(name = "Scale.findById", query = "SELECT s FROM Scale s WHERE s.id = :id"),
    @NamedQuery(name = "Scale.findByModel", query = "SELECT s FROM Scale s WHERE s.model = :model"),
    @NamedQuery(name = "Scale.findByStatus", query = "SELECT s FROM Scale s WHERE s.status = :status"),
    @NamedQuery(name = "Scale.findByCapacityunit", query = "SELECT s FROM Scale s WHERE s.capacityunit = :capacityunit"),
    @NamedQuery(name = "Scale.findBySerialnumber", query = "SELECT s FROM Scale s WHERE s.serialnumber = :serialnumber"),
    @NamedQuery(name = "Scale.findByNumber", query = "SELECT s FROM Scale s WHERE s.number = :number"),
    @NamedQuery(name = "Scale.findByType", query = "SELECT s FROM Scale s WHERE s.type = :type"),
    @NamedQuery(name = "Scale.findByDatescheduledfortest", query = "SELECT s FROM Scale s WHERE s.datescheduledfortest = :datescheduledfortest"),
    @NamedQuery(name = "Scale.findByName", query = "SELECT s FROM Scale s WHERE s.name = :name"),
    @NamedQuery(name = "Scale.findByCapacity", query = "SELECT s FROM Scale s WHERE s.capacity = :capacity")})
public class Scale implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "MODEL")
    private String model;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "CAPACITYUNIT")
    private String capacityunit;
    @Size(max = 255)
    @Column(name = "SERIALNUMBER")
    private String serialnumber;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DATESCHEDULEDFORTEST")
    @Temporal(TemporalType.DATE)
    private Date datescheduledfortest;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAPACITY")
    private Double capacity;
    @ManyToMany(mappedBy = "scaleList")
    private List<Scalecompany> scalecompanyList;
    @JoinTable(name = "scale_sticker", joinColumns = {
        @JoinColumn(name = "Scale_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "stickers_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Sticker> stickerList;
    @JoinColumn(name = "CERTIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Certification certificationId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;

    public Scale() {
    }

    public Scale(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapacityunit() {
        return capacityunit;
    }

    public void setCapacityunit(String capacityunit) {
        this.capacityunit = capacityunit;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatescheduledfortest() {
        return datescheduledfortest;
    }

    public void setDatescheduledfortest(Date datescheduledfortest) {
        this.datescheduledfortest = datescheduledfortest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    @XmlTransient
    @JsonIgnore
    public List<Scalecompany> getScalecompanyList() {
        return scalecompanyList;
    }

    public void setScalecompanyList(List<Scalecompany> scalecompanyList) {
        this.scalecompanyList = scalecompanyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    public Certification getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(Certification certificationId) {
        this.certificationId = certificationId;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof Scale)) {
            return false;
        }
        Scale other = (Scale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Scale[ id=" + id + " ]";
    }
    
}
