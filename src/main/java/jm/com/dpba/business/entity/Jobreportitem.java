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
@Table(name = "jobreportitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobreportitem.findAll", query = "SELECT j FROM Jobreportitem j"),
    @NamedQuery(name = "Jobreportitem.findById", query = "SELECT j FROM Jobreportitem j WHERE j.id = :id"),
    @NamedQuery(name = "Jobreportitem.findByItemvalue", query = "SELECT j FROM Jobreportitem j WHERE j.itemvalue = :itemvalue"),
    @NamedQuery(name = "Jobreportitem.findByName", query = "SELECT j FROM Jobreportitem j WHERE j.name = :name")})
public class Jobreportitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ITEMVALUE")
    private Double itemvalue;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "jobreportitem_department", joinColumns = {
        @JoinColumn(name = "JobReportItem_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "departments_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Department> departmentList;

    public Jobreportitem() {
    }

    public Jobreportitem(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(Double itemvalue) {
        this.itemvalue = itemvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
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
        if (!(object instanceof Jobreportitem)) {
            return false;
        }
        Jobreportitem other = (Jobreportitem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobreportitem[ id=" + id + " ]";
    }
    
}
