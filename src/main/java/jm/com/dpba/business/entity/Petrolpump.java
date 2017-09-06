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
@Table(name = "petrolpump")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolpump.findAll", query = "SELECT p FROM Petrolpump p"),
    @NamedQuery(name = "Petrolpump.findById", query = "SELECT p FROM Petrolpump p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolpump.findByModel", query = "SELECT p FROM Petrolpump p WHERE p.model = :model"),
    @NamedQuery(name = "Petrolpump.findByStatus", query = "SELECT p FROM Petrolpump p WHERE p.status = :status"),
    @NamedQuery(name = "Petrolpump.findBySerialnumber", query = "SELECT p FROM Petrolpump p WHERE p.serialnumber = :serialnumber"),
    @NamedQuery(name = "Petrolpump.findByNumber", query = "SELECT p FROM Petrolpump p WHERE p.number = :number"),
    @NamedQuery(name = "Petrolpump.findByType", query = "SELECT p FROM Petrolpump p WHERE p.type = :type"),
    @NamedQuery(name = "Petrolpump.findByDatescheduledfortest", query = "SELECT p FROM Petrolpump p WHERE p.datescheduledfortest = :datescheduledfortest"),
    @NamedQuery(name = "Petrolpump.findByRate", query = "SELECT p FROM Petrolpump p WHERE p.rate = :rate"),
    @NamedQuery(name = "Petrolpump.findByName", query = "SELECT p FROM Petrolpump p WHERE p.name = :name")})
public class Petrolpump implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATE")
    private Double rate;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "petrolpumpList")
    private List<Petrolstation> petrolstationList;
    @JoinTable(name = "petrolpump_petrolpumpnozzle", joinColumns = {
        @JoinColumn(name = "PetrolPump_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "nozzles_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Petrolpumpnozzle> petrolpumpnozzleList;
    @JoinTable(name = "petrolpump_sticker", joinColumns = {
        @JoinColumn(name = "PetrolPump_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "stickers_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Sticker> stickerList;
    @JoinColumn(name = "CERTIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Certification certificationId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Petrolpump() {
    }

    public Petrolpump(Long id) {
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolstation> getPetrolstationList() {
        return petrolstationList;
    }

    public void setPetrolstationList(List<Petrolstation> petrolstationList) {
        this.petrolstationList = petrolstationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzle> getPetrolpumpnozzleList() {
        return petrolpumpnozzleList;
    }

    public void setPetrolpumpnozzleList(List<Petrolpumpnozzle> petrolpumpnozzleList) {
        this.petrolpumpnozzleList = petrolpumpnozzleList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Petrolpump)) {
            return false;
        }
        Petrolpump other = (Petrolpump) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolpump[ id=" + id + " ]";
    }
    
}
