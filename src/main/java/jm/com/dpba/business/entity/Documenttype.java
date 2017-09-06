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
@Table(name = "documenttype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documenttype.findAll", query = "SELECT d FROM Documenttype d"),
    @NamedQuery(name = "Documenttype.findById", query = "SELECT d FROM Documenttype d WHERE d.id = :id"),
    @NamedQuery(name = "Documenttype.findByName", query = "SELECT d FROM Documenttype d WHERE d.name = :name"),
    @NamedQuery(name = "Documenttype.findByCode", query = "SELECT d FROM Documenttype d WHERE d.code = :code"),
    @NamedQuery(name = "Documenttype.findByType", query = "SELECT d FROM Documenttype d WHERE d.type = :type")})
public class Documenttype implements Serializable {
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
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @OneToMany(mappedBy = "typeId")
    private List<Legaldocument> legaldocumentList;
    @OneToMany(mappedBy = "documenttypeId")
    private List<Documentstandard> documentstandardList;
    @OneToMany(mappedBy = "typeId")
    private List<Documenttracking> documenttrackingList;

    public Documenttype() {
    }

    public Documenttype(Long id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Legaldocument> getLegaldocumentList() {
        return legaldocumentList;
    }

    public void setLegaldocumentList(List<Legaldocument> legaldocumentList) {
        this.legaldocumentList = legaldocumentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Documentstandard> getDocumentstandardList() {
        return documentstandardList;
    }

    public void setDocumentstandardList(List<Documentstandard> documentstandardList) {
        this.documentstandardList = documentstandardList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Documenttracking> getDocumenttrackingList() {
        return documenttrackingList;
    }

    public void setDocumenttrackingList(List<Documenttracking> documenttrackingList) {
        this.documenttrackingList = documenttrackingList;
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
        if (!(object instanceof Documenttype)) {
            return false;
        }
        Documenttype other = (Documenttype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Documenttype[ id=" + id + " ]";
    }
    
}
