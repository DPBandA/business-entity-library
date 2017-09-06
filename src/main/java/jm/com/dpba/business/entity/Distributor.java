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
@Table(name = "distributor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributor.findAll", query = "SELECT d FROM Distributor d"),
    @NamedQuery(name = "Distributor.findById", query = "SELECT d FROM Distributor d WHERE d.id = :id"),
    @NamedQuery(name = "Distributor.findByPhone", query = "SELECT d FROM Distributor d WHERE d.phone = :phone"),
    @NamedQuery(name = "Distributor.findByFax", query = "SELECT d FROM Distributor d WHERE d.fax = :fax"),
    @NamedQuery(name = "Distributor.findByEmail", query = "SELECT d FROM Distributor d WHERE d.email = :email"),
    @NamedQuery(name = "Distributor.findByName", query = "SELECT d FROM Distributor d WHERE d.name = :name"),
    @NamedQuery(name = "Distributor.findByStreet", query = "SELECT d FROM Distributor d WHERE d.street = :street"),
    @NamedQuery(name = "Distributor.findByPo", query = "SELECT d FROM Distributor d WHERE d.po = :po"),
    @NamedQuery(name = "Distributor.findByCity", query = "SELECT d FROM Distributor d WHERE d.city = :city"),
    @NamedQuery(name = "Distributor.findByCountry", query = "SELECT d FROM Distributor d WHERE d.country = :country")})
public class Distributor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "FAX")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "STREET")
    private String street;
    @Size(max = 255)
    @Column(name = "PO")
    private String po;
    @Size(max = 255)
    @Column(name = "CITY")
    private String city;
    @Size(max = 255)
    @Column(name = "COUNTRY")
    private String country;
    @OneToMany(mappedBy = "distributorId")
    private List<Productinspection> productinspectionList;

    public Distributor() {
    }

    public Distributor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    @JsonIgnore
    public List<Productinspection> getProductinspectionList() {
        return productinspectionList;
    }

    public void setProductinspectionList(List<Productinspection> productinspectionList) {
        this.productinspectionList = productinspectionList;
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
        if (!(object instanceof Distributor)) {
            return false;
        }
        Distributor other = (Distributor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Distributor[ id=" + id + " ]";
    }
    
}
