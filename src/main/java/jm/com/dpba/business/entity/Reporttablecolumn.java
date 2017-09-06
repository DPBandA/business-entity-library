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
@Table(name = "reporttablecolumn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporttablecolumn.findAll", query = "SELECT r FROM Reporttablecolumn r"),
    @NamedQuery(name = "Reporttablecolumn.findById", query = "SELECT r FROM Reporttablecolumn r WHERE r.id = :id"),
    @NamedQuery(name = "Reporttablecolumn.findByName", query = "SELECT r FROM Reporttablecolumn r WHERE r.name = :name"),
    @NamedQuery(name = "Reporttablecolumn.findByEntityclassmethodname", query = "SELECT r FROM Reporttablecolumn r WHERE r.entityclassmethodname = :entityclassmethodname")})
public class Reporttablecolumn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "ENTITYCLASSMETHODNAME")
    private String entityclassmethodname;
    @ManyToMany(mappedBy = "reporttablecolumnList")
    private List<Report> reportList;

    public Reporttablecolumn() {
    }

    public Reporttablecolumn(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityclassmethodname() {
        return entityclassmethodname;
    }

    public void setEntityclassmethodname(String entityclassmethodname) {
        this.entityclassmethodname = entityclassmethodname;
    }

    @XmlTransient
    @JsonIgnore
    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
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
        if (!(object instanceof Reporttablecolumn)) {
            return false;
        }
        Reporttablecolumn other = (Reporttablecolumn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Reporttablecolumn[ id=" + id + " ]";
    }
    
}
