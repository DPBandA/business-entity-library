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
import javax.persistence.OneToMany;
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
@Table(name = "sticker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sticker.findAll", query = "SELECT s FROM Sticker s"),
    @NamedQuery(name = "Sticker.findById", query = "SELECT s FROM Sticker s WHERE s.id = :id"),
    @NamedQuery(name = "Sticker.findByValid", query = "SELECT s FROM Sticker s WHERE s.valid = :valid"),
    @NamedQuery(name = "Sticker.findByDateassigned", query = "SELECT s FROM Sticker s WHERE s.dateassigned = :dateassigned"),
    @NamedQuery(name = "Sticker.findByName", query = "SELECT s FROM Sticker s WHERE s.name = :name"),
    @NamedQuery(name = "Sticker.findByDateissued", query = "SELECT s FROM Sticker s WHERE s.dateissued = :dateissued"),
    @NamedQuery(name = "Sticker.findByNumber", query = "SELECT s FROM Sticker s WHERE s.number = :number"),
    @NamedQuery(name = "Sticker.findByUsed", query = "SELECT s FROM Sticker s WHERE s.used = :used"),
    @NamedQuery(name = "Sticker.findByType", query = "SELECT s FROM Sticker s WHERE s.type = :type"),
    @NamedQuery(name = "Sticker.findByDateexpired", query = "SELECT s FROM Sticker s WHERE s.dateexpired = :dateexpired")})
public class Sticker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALID")
    private Boolean valid;
    @Column(name = "DATEASSIGNED")
    @Temporal(TemporalType.DATE)
    private Date dateassigned;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATEISSUED")
    @Temporal(TemporalType.DATE)
    private Date dateissued;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "USED")
    private Boolean used;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DATEEXPIRED")
    @Temporal(TemporalType.DATE)
    private Date dateexpired;
    @ManyToMany(mappedBy = "stickerList")
    private List<Scale> scaleList;
    @ManyToMany(mappedBy = "stickerList")
    private List<Petrolpumpnozzle> petrolpumpnozzleList;
    @ManyToMany(mappedBy = "stickerList")
    private List<Petrolpump> petrolpumpList;
    @JoinColumn(name = "ASSIGNEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee assigneeId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;
    @OneToMany(mappedBy = "laststickerissuedId")
    private List<Petrolpumpnozzle> petrolpumpnozzleList1;

    public Sticker() {
    }

    public Sticker(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getDateassigned() {
        return dateassigned;
    }

    public void setDateassigned(Date dateassigned) {
        this.dateassigned = dateassigned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateissued() {
        return dateissued;
    }

    public void setDateissued(Date dateissued) {
        this.dateissued = dateissued;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateexpired() {
        return dateexpired;
    }

    public void setDateexpired(Date dateexpired) {
        this.dateexpired = dateexpired;
    }

    @XmlTransient
    @JsonIgnore
    public List<Scale> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<Scale> scaleList) {
        this.scaleList = scaleList;
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
    public List<Petrolpump> getPetrolpumpList() {
        return petrolpumpList;
    }

    public void setPetrolpumpList(List<Petrolpump> petrolpumpList) {
        this.petrolpumpList = petrolpumpList;
    }

    public Employee getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Employee assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzle> getPetrolpumpnozzleList1() {
        return petrolpumpnozzleList1;
    }

    public void setPetrolpumpnozzleList1(List<Petrolpumpnozzle> petrolpumpnozzleList1) {
        this.petrolpumpnozzleList1 = petrolpumpnozzleList1;
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
        if (!(object instanceof Sticker)) {
            return false;
        }
        Sticker other = (Sticker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Sticker[ id=" + id + " ]";
    }
    
}
