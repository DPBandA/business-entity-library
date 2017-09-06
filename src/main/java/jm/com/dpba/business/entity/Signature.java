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
import javax.persistence.Lob;
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
@Table(name = "signature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signature.findAll", query = "SELECT s FROM Signature s"),
    @NamedQuery(name = "Signature.findById", query = "SELECT s FROM Signature s WHERE s.id = :id"),
    @NamedQuery(name = "Signature.findByName", query = "SELECT s FROM Signature s WHERE s.name = :name")})
public class Signature implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Lob
    @Column(name = "SIGNATUREIMAGE")
    private byte[] signatureimage;
    @OneToMany(mappedBy = "signatureId")
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "authsigfornoticeofdentiondmId")
    private List<Compliancesurvey> compliancesurveyList;
    @OneToMany(mappedBy = "authsigfordetentionrequestpoeId")
    private List<Compliancesurvey> compliancesurveyList1;
    @OneToMany(mappedBy = "inspectorsigforsamplerequestpoeId")
    private List<Compliancesurvey> compliancesurveyList2;
    @OneToMany(mappedBy = "authsigfornoticeofreleasefromdentiondmId")
    private List<Compliancesurvey> compliancesurveyList3;
    @OneToMany(mappedBy = "approvedbysigforreleaserequestpoeId")
    private List<Compliancesurvey> compliancesurveyList4;
    @OneToMany(mappedBy = "preparedbysigforreleaserequestpoeId")
    private List<Compliancesurvey> compliancesurveyList5;

    public Signature() {
    }

    public Signature(Long id) {
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

    public byte[] getSignatureimage() {
        return signatureimage;
    }

    public void setSignatureimage(byte[] signatureimage) {
        this.signatureimage = signatureimage;
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
    public List<Compliancesurvey> getCompliancesurveyList() {
        return compliancesurveyList;
    }

    public void setCompliancesurveyList(List<Compliancesurvey> compliancesurveyList) {
        this.compliancesurveyList = compliancesurveyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList1() {
        return compliancesurveyList1;
    }

    public void setCompliancesurveyList1(List<Compliancesurvey> compliancesurveyList1) {
        this.compliancesurveyList1 = compliancesurveyList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList2() {
        return compliancesurveyList2;
    }

    public void setCompliancesurveyList2(List<Compliancesurvey> compliancesurveyList2) {
        this.compliancesurveyList2 = compliancesurveyList2;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList3() {
        return compliancesurveyList3;
    }

    public void setCompliancesurveyList3(List<Compliancesurvey> compliancesurveyList3) {
        this.compliancesurveyList3 = compliancesurveyList3;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList4() {
        return compliancesurveyList4;
    }

    public void setCompliancesurveyList4(List<Compliancesurvey> compliancesurveyList4) {
        this.compliancesurveyList4 = compliancesurveyList4;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList5() {
        return compliancesurveyList5;
    }

    public void setCompliancesurveyList5(List<Compliancesurvey> compliancesurveyList5) {
        this.compliancesurveyList5 = compliancesurveyList5;
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
        if (!(object instanceof Signature)) {
            return false;
        }
        Signature other = (Signature) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Signature[ id=" + id + " ]";
    }
    
}
