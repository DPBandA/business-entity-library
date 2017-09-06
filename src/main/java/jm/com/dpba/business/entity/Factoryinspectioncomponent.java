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
@Table(name = "factoryinspectioncomponent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factoryinspectioncomponent.findAll", query = "SELECT f FROM Factoryinspectioncomponent f"),
    @NamedQuery(name = "Factoryinspectioncomponent.findById", query = "SELECT f FROM Factoryinspectioncomponent f WHERE f.id = :id"),
    @NamedQuery(name = "Factoryinspectioncomponent.findByCategory", query = "SELECT f FROM Factoryinspectioncomponent f WHERE f.category = :category"),
    @NamedQuery(name = "Factoryinspectioncomponent.findByResults", query = "SELECT f FROM Factoryinspectioncomponent f WHERE f.results = :results"),
    @NamedQuery(name = "Factoryinspectioncomponent.findByName", query = "SELECT f FROM Factoryinspectioncomponent f WHERE f.name = :name"),
    @NamedQuery(name = "Factoryinspectioncomponent.findByIsheading", query = "SELECT f FROM Factoryinspectioncomponent f WHERE f.isheading = :isheading"),
    @NamedQuery(name = "Factoryinspectioncomponent.findByComments", query = "SELECT f FROM Factoryinspectioncomponent f WHERE f.comments = :comments")})
public class Factoryinspectioncomponent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 255)
    @Column(name = "RESULTS")
    private String results;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ISHEADING")
    private Boolean isheading;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @ManyToMany(mappedBy = "factoryinspectioncomponentList")
    private List<Factoryinspection> factoryinspectionList;

    public Factoryinspectioncomponent() {
    }

    public Factoryinspectioncomponent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsheading() {
        return isheading;
    }

    public void setIsheading(Boolean isheading) {
        this.isheading = isheading;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlTransient
    @JsonIgnore
    public List<Factoryinspection> getFactoryinspectionList() {
        return factoryinspectionList;
    }

    public void setFactoryinspectionList(List<Factoryinspection> factoryinspectionList) {
        this.factoryinspectionList = factoryinspectionList;
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
        if (!(object instanceof Factoryinspectioncomponent)) {
            return false;
        }
        Factoryinspectioncomponent other = (Factoryinspectioncomponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Factoryinspectioncomponent[ id=" + id + " ]";
    }
    
}
