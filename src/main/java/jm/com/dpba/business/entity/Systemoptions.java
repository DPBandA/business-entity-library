/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "systemoptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Systemoptions.findAll", query = "SELECT s FROM Systemoptions s"),
    @NamedQuery(name = "Systemoptions.findById", query = "SELECT s FROM Systemoptions s WHERE s.id = :id"),
    @NamedQuery(name = "Systemoptions.findByOptionvaluetype", query = "SELECT s FROM Systemoptions s WHERE s.optionvaluetype = :optionvaluetype"),
    @NamedQuery(name = "Systemoptions.findByCategory", query = "SELECT s FROM Systemoptions s WHERE s.category = :category"),
    @NamedQuery(name = "Systemoptions.findByName", query = "SELECT s FROM Systemoptions s WHERE s.name = :name"),
    @NamedQuery(name = "Systemoptions.findByComments", query = "SELECT s FROM Systemoptions s WHERE s.comments = :comments"),
    @NamedQuery(name = "Systemoptions.findByOptionvalue", query = "SELECT s FROM Systemoptions s WHERE s.optionvalue = :optionvalue")})
public class Systemoptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "OPTIONVALUETYPE")
    private String optionvaluetype;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Size(max = 255)
    @Column(name = "OPTIONVALUE")
    private String optionvalue;

    public Systemoptions() {
    }

    public Systemoptions(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionvaluetype() {
        return optionvaluetype;
    }

    public void setOptionvaluetype(String optionvaluetype) {
        this.optionvaluetype = optionvaluetype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getOptionvalue() {
        return optionvalue;
    }

    public void setOptionvalue(String optionvalue) {
        this.optionvalue = optionvalue;
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
        if (!(object instanceof Systemoptions)) {
            return false;
        }
        Systemoptions other = (Systemoptions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Systemoptions[ id=" + id + " ]";
    }
    
}
