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
@Table(name = "phonenumber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phonenumber.findAll", query = "SELECT p FROM Phonenumber p"),
    @NamedQuery(name = "Phonenumber.findById", query = "SELECT p FROM Phonenumber p WHERE p.id = :id"),
    @NamedQuery(name = "Phonenumber.findByExtension", query = "SELECT p FROM Phonenumber p WHERE p.extension = :extension"),
    @NamedQuery(name = "Phonenumber.findByLocalnumber", query = "SELECT p FROM Phonenumber p WHERE p.localnumber = :localnumber"),
    @NamedQuery(name = "Phonenumber.findByCityorareacode", query = "SELECT p FROM Phonenumber p WHERE p.cityorareacode = :cityorareacode"),
    @NamedQuery(name = "Phonenumber.findByCountryorregioncode", query = "SELECT p FROM Phonenumber p WHERE p.countryorregioncode = :countryorregioncode"),
    @NamedQuery(name = "Phonenumber.findByType", query = "SELECT p FROM Phonenumber p WHERE p.type = :type")})
public class Phonenumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "EXTENSION")
    private String extension;
    @Size(max = 255)
    @Column(name = "LOCALNUMBER")
    private String localnumber;
    @Size(max = 255)
    @Column(name = "CITYORAREACODE")
    private String cityorareacode;
    @Size(max = 255)
    @Column(name = "COUNTRYORREGIONCODE")
    private String countryorregioncode;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @ManyToMany(mappedBy = "phonenumberList")
    private List<Employee> employeeList;
    @ManyToMany(mappedBy = "phonenumberList")
    private List<Contact> contactList;

    public Phonenumber() {
    }

    public Phonenumber(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getLocalnumber() {
        return localnumber;
    }

    public void setLocalnumber(String localnumber) {
        this.localnumber = localnumber;
    }

    public String getCityorareacode() {
        return cityorareacode;
    }

    public void setCityorareacode(String cityorareacode) {
        this.cityorareacode = cityorareacode;
    }

    public String getCountryorregioncode() {
        return countryorregioncode;
    }

    public void setCountryorregioncode(String countryorregioncode) {
        this.countryorregioncode = countryorregioncode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
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
        if (!(object instanceof Phonenumber)) {
            return false;
        }
        Phonenumber other = (Phonenumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Phonenumber[ id=" + id + " ]";
    }
    
}
