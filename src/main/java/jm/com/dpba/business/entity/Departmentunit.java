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
@Table(name = "departmentunit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departmentunit.findAll", query = "SELECT d FROM Departmentunit d"),
    @NamedQuery(name = "Departmentunit.findById", query = "SELECT d FROM Departmentunit d WHERE d.id = :id"),
    @NamedQuery(name = "Departmentunit.findByActive", query = "SELECT d FROM Departmentunit d WHERE d.active = :active"),
    @NamedQuery(name = "Departmentunit.findByName", query = "SELECT d FROM Departmentunit d WHERE d.name = :name"),
    @NamedQuery(name = "Departmentunit.findByNumber", query = "SELECT d FROM Departmentunit d WHERE d.number = :number"),
    @NamedQuery(name = "Departmentunit.findByType", query = "SELECT d FROM Departmentunit d WHERE d.type = :type")})
public class Departmentunit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @ManyToMany(mappedBy = "departmentunitList")
    private List<Department> departmentList;
    @OneToMany(mappedBy = "departmentunitId")
    private List<Unitcost> unitcostList;

    public Departmentunit() {
    }

    public Departmentunit(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Unitcost> getUnitcostList() {
        return unitcostList;
    }

    public void setUnitcostList(List<Unitcost> unitcostList) {
        this.unitcostList = unitcostList;
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
        if (!(object instanceof Departmentunit)) {
            return false;
        }
        Departmentunit other = (Departmentunit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Departmentunit[ id=" + id + " ]";
    }
    
}
