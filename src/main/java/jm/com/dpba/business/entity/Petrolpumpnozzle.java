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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "petrolpumpnozzle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolpumpnozzle.findAll", query = "SELECT p FROM Petrolpumpnozzle p"),
    @NamedQuery(name = "Petrolpumpnozzle.findById", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolpumpnozzle.findByModel", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.model = :model"),
    @NamedQuery(name = "Petrolpumpnozzle.findBySerialnumber", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.serialnumber = :serialnumber"),
    @NamedQuery(name = "Petrolpumpnozzle.findByNumber", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.number = :number"),
    @NamedQuery(name = "Petrolpumpnozzle.findByType", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.type = :type"),
    @NamedQuery(name = "Petrolpumpnozzle.findByTestmeasures", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.testmeasures = :testmeasures"),
    @NamedQuery(name = "Petrolpumpnozzle.findByName", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.name = :name"),
    @NamedQuery(name = "Petrolpumpnozzle.findByComments", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.comments = :comments"),
    @NamedQuery(name = "Petrolpumpnozzle.findByStatus", query = "SELECT p FROM Petrolpumpnozzle p WHERE p.status = :status")})
public class Petrolpumpnozzle implements Serializable {
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
    @Column(name = "SERIALNUMBER")
    private String serialnumber;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "TESTMEASURES")
    private String testmeasures;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "COMMENTS")
    private String comments;
    @Size(max = 1024)
    @Column(name = "STATUS")
    private String status;
    @JoinTable(name = "petrolpumpnozzle_certification", joinColumns = {
        @JoinColumn(name = "PetrolPumpNozzle_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "certifications_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Certification> certificationList;
    @JoinTable(name = "petrolpumpnozzle_sticker", joinColumns = {
        @JoinColumn(name = "PetrolPumpNozzle_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "stickers_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Sticker> stickerList;
    @ManyToMany(mappedBy = "petrolpumpnozzleList")
    private List<Petrolpump> petrolpumpList;
    @JoinTable(name = "petrolpumpnozzle_seal", joinColumns = {
        @JoinColumn(name = "PetrolPumpNozzle_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "seals_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Seal> sealList;
    @ManyToMany(mappedBy = "petrolpumpnozzleList")
    private List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList;
    @JoinColumn(name = "LASTCALIBRATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Petrolpumpnozzlecalibration lastcalibrationId;
    @JoinColumn(name = "LASTSEALISSUED_ID", referencedColumnName = "ID")
    @ManyToOne
    private Seal lastsealissuedId;
    @JoinColumn(name = "LASTSTICKERISSUED_ID", referencedColumnName = "ID")
    @ManyToOne
    private Sticker laststickerissuedId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Petrolpumpnozzle() {
    }

    public Petrolpumpnozzle(Long id) {
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

    public String getTestmeasures() {
        return testmeasures;
    }

    public void setTestmeasures(String testmeasures) {
        this.testmeasures = testmeasures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    @JsonIgnore
    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpump> getPetrolpumpList() {
        return petrolpumpList;
    }

    public void setPetrolpumpList(List<Petrolpump> petrolpumpList) {
        this.petrolpumpList = petrolpumpList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Seal> getSealList() {
        return sealList;
    }

    public void setSealList(List<Seal> sealList) {
        this.sealList = sealList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzlecalibration> getPetrolpumpnozzlecalibrationList() {
        return petrolpumpnozzlecalibrationList;
    }

    public void setPetrolpumpnozzlecalibrationList(List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList) {
        this.petrolpumpnozzlecalibrationList = petrolpumpnozzlecalibrationList;
    }

    public Petrolpumpnozzlecalibration getLastcalibrationId() {
        return lastcalibrationId;
    }

    public void setLastcalibrationId(Petrolpumpnozzlecalibration lastcalibrationId) {
        this.lastcalibrationId = lastcalibrationId;
    }

    public Seal getLastsealissuedId() {
        return lastsealissuedId;
    }

    public void setLastsealissuedId(Seal lastsealissuedId) {
        this.lastsealissuedId = lastsealissuedId;
    }

    public Sticker getLaststickerissuedId() {
        return laststickerissuedId;
    }

    public void setLaststickerissuedId(Sticker laststickerissuedId) {
        this.laststickerissuedId = laststickerissuedId;
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
        if (!(object instanceof Petrolpumpnozzle)) {
            return false;
        }
        Petrolpumpnozzle other = (Petrolpumpnozzle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolpumpnozzle[ id=" + id + " ]";
    }
    
}
