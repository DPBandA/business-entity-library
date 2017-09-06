/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "servicecontract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicecontract.findAll", query = "SELECT s FROM Servicecontract s"),
    @NamedQuery(name = "Servicecontract.findById", query = "SELECT s FROM Servicecontract s WHERE s.id = :id"),
    @NamedQuery(name = "Servicecontract.findBySpecialinstructions", query = "SELECT s FROM Servicecontract s WHERE s.specialinstructions = :specialinstructions"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketothertext", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketothertext = :intendedmarketothertext"),
    @NamedQuery(name = "Servicecontract.findByJobid", query = "SELECT s FROM Servicecontract s WHERE s.jobid = :jobid"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedcalibration", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedcalibration = :servicerequestedcalibration"),
    @NamedQuery(name = "Servicecontract.findByEstimatedturnaroundtime", query = "SELECT s FROM Servicecontract s WHERE s.estimatedturnaroundtime = :estimatedturnaroundtime"),
    @NamedQuery(name = "Servicecontract.findByAdditionalservicefaxresults", query = "SELECT s FROM Servicecontract s WHERE s.additionalservicefaxresults = :additionalservicefaxresults"),
    @NamedQuery(name = "Servicecontract.findByAdditionalservicetelephonepresumptiveresults", query = "SELECT s FROM Servicecontract s WHERE s.additionalservicetelephonepresumptiveresults = :additionalservicetelephonepresumptiveresults"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedlabelevaluation", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedlabelevaluation = :servicerequestedlabelevaluation"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketlocal", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketlocal = :intendedmarketlocal"),
    @NamedQuery(name = "Servicecontract.findByAdditionalserviceother", query = "SELECT s FROM Servicecontract s WHERE s.additionalserviceother = :additionalserviceother"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketusa", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketusa = :intendedmarketusa"),
    @NamedQuery(name = "Servicecontract.findByAdditionalservicesendmorecontractforms", query = "SELECT s FROM Servicecontract s WHERE s.additionalservicesendmorecontractforms = :additionalservicesendmorecontractforms"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketcaricom", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketcaricom = :intendedmarketcaricom"),
    @NamedQuery(name = "Servicecontract.findByServicerequesteddetails", query = "SELECT s FROM Servicecontract s WHERE s.servicerequesteddetails = :servicerequesteddetails"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedtesting", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedtesting = :servicerequestedtesting"),
    @NamedQuery(name = "Servicecontract.findByAutoaddsampleinformation", query = "SELECT s FROM Servicecontract s WHERE s.autoaddsampleinformation = :autoaddsampleinformation"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedinspection", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedinspection = :servicerequestedinspection"),
    @NamedQuery(name = "Servicecontract.findByAdditionalserviceurgent", query = "SELECT s FROM Servicecontract s WHERE s.additionalserviceurgent = :additionalserviceurgent"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedconsultancy", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedconsultancy = :servicerequestedconsultancy"),
    @NamedQuery(name = "Servicecontract.findBySubmittedby", query = "SELECT s FROM Servicecontract s WHERE s.submittedby = :submittedby"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedothertext", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedothertext = :servicerequestedothertext"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketuk", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketuk = :intendedmarketuk"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketother", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketother = :intendedmarketother"),
    @NamedQuery(name = "Servicecontract.findByReceivedby", query = "SELECT s FROM Servicecontract s WHERE s.receivedby = :receivedby"),
    @NamedQuery(name = "Servicecontract.findByAdditionalserviceothertext", query = "SELECT s FROM Servicecontract s WHERE s.additionalserviceothertext = :additionalserviceothertext"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedtraining", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedtraining = :servicerequestedtraining"),
    @NamedQuery(name = "Servicecontract.findByBillingaddressid", query = "SELECT s FROM Servicecontract s WHERE s.billingaddressid = :billingaddressid"),
    @NamedQuery(name = "Servicecontract.findByServicerequestedother", query = "SELECT s FROM Servicecontract s WHERE s.servicerequestedother = :servicerequestedother"),
    @NamedQuery(name = "Servicecontract.findByIntendedmarketcanada", query = "SELECT s FROM Servicecontract s WHERE s.intendedmarketcanada = :intendedmarketcanada")})
public class Servicecontract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "SPECIALINSTRUCTIONS")
    private String specialinstructions;
    @Size(max = 255)
    @Column(name = "INTENDEDMARKETOTHERTEXT")
    private String intendedmarketothertext;
    @Column(name = "JOBID")
    private BigInteger jobid;
    @Column(name = "SERVICEREQUESTEDCALIBRATION")
    private Boolean servicerequestedcalibration;
    @Column(name = "ESTIMATEDTURNAROUNDTIME")
    private Integer estimatedturnaroundtime;
    @Column(name = "ADDITIONALSERVICEFAXRESULTS")
    private Boolean additionalservicefaxresults;
    @Column(name = "ADDITIONALSERVICETELEPHONEPRESUMPTIVERESULTS")
    private Boolean additionalservicetelephonepresumptiveresults;
    @Column(name = "SERVICEREQUESTEDLABELEVALUATION")
    private Boolean servicerequestedlabelevaluation;
    @Column(name = "INTENDEDMARKETLOCAL")
    private Boolean intendedmarketlocal;
    @Column(name = "ADDITIONALSERVICEOTHER")
    private Boolean additionalserviceother;
    @Column(name = "INTENDEDMARKETUSA")
    private Boolean intendedmarketusa;
    @Column(name = "ADDITIONALSERVICESENDMORECONTRACTFORMS")
    private Boolean additionalservicesendmorecontractforms;
    @Column(name = "INTENDEDMARKETCARICOM")
    private Boolean intendedmarketcaricom;
    @Size(max = 255)
    @Column(name = "SERVICEREQUESTEDDETAILS")
    private String servicerequesteddetails;
    @Column(name = "SERVICEREQUESTEDTESTING")
    private Boolean servicerequestedtesting;
    @Column(name = "AUTOADDSAMPLEINFORMATION")
    private Boolean autoaddsampleinformation;
    @Column(name = "SERVICEREQUESTEDINSPECTION")
    private Boolean servicerequestedinspection;
    @Column(name = "ADDITIONALSERVICEURGENT")
    private Boolean additionalserviceurgent;
    @Column(name = "SERVICEREQUESTEDCONSULTANCY")
    private Boolean servicerequestedconsultancy;
    @Size(max = 255)
    @Column(name = "SUBMITTEDBY")
    private String submittedby;
    @Size(max = 255)
    @Column(name = "SERVICEREQUESTEDOTHERTEXT")
    private String servicerequestedothertext;
    @Column(name = "INTENDEDMARKETUK")
    private Boolean intendedmarketuk;
    @Column(name = "INTENDEDMARKETOTHER")
    private Boolean intendedmarketother;
    @Size(max = 255)
    @Column(name = "RECEIVEDBY")
    private String receivedby;
    @Size(max = 255)
    @Column(name = "ADDITIONALSERVICEOTHERTEXT")
    private String additionalserviceothertext;
    @Column(name = "SERVICEREQUESTEDTRAINING")
    private Boolean servicerequestedtraining;
    @Column(name = "BILLINGADDRESSID")
    private BigInteger billingaddressid;
    @Column(name = "SERVICEREQUESTEDOTHER")
    private Boolean servicerequestedother;
    @Column(name = "INTENDEDMARKETCANADA")
    private Boolean intendedmarketcanada;
    @OneToMany(mappedBy = "servicecontractId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "servicecontractId")
    private List<Job> jobList;

    public Servicecontract() {
    }

    public Servicecontract(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialinstructions() {
        return specialinstructions;
    }

    public void setSpecialinstructions(String specialinstructions) {
        this.specialinstructions = specialinstructions;
    }

    public String getIntendedmarketothertext() {
        return intendedmarketothertext;
    }

    public void setIntendedmarketothertext(String intendedmarketothertext) {
        this.intendedmarketothertext = intendedmarketothertext;
    }

    public BigInteger getJobid() {
        return jobid;
    }

    public void setJobid(BigInteger jobid) {
        this.jobid = jobid;
    }

    public Boolean getServicerequestedcalibration() {
        return servicerequestedcalibration;
    }

    public void setServicerequestedcalibration(Boolean servicerequestedcalibration) {
        this.servicerequestedcalibration = servicerequestedcalibration;
    }

    public Integer getEstimatedturnaroundtime() {
        return estimatedturnaroundtime;
    }

    public void setEstimatedturnaroundtime(Integer estimatedturnaroundtime) {
        this.estimatedturnaroundtime = estimatedturnaroundtime;
    }

    public Boolean getAdditionalservicefaxresults() {
        return additionalservicefaxresults;
    }

    public void setAdditionalservicefaxresults(Boolean additionalservicefaxresults) {
        this.additionalservicefaxresults = additionalservicefaxresults;
    }

    public Boolean getAdditionalservicetelephonepresumptiveresults() {
        return additionalservicetelephonepresumptiveresults;
    }

    public void setAdditionalservicetelephonepresumptiveresults(Boolean additionalservicetelephonepresumptiveresults) {
        this.additionalservicetelephonepresumptiveresults = additionalservicetelephonepresumptiveresults;
    }

    public Boolean getServicerequestedlabelevaluation() {
        return servicerequestedlabelevaluation;
    }

    public void setServicerequestedlabelevaluation(Boolean servicerequestedlabelevaluation) {
        this.servicerequestedlabelevaluation = servicerequestedlabelevaluation;
    }

    public Boolean getIntendedmarketlocal() {
        return intendedmarketlocal;
    }

    public void setIntendedmarketlocal(Boolean intendedmarketlocal) {
        this.intendedmarketlocal = intendedmarketlocal;
    }

    public Boolean getAdditionalserviceother() {
        return additionalserviceother;
    }

    public void setAdditionalserviceother(Boolean additionalserviceother) {
        this.additionalserviceother = additionalserviceother;
    }

    public Boolean getIntendedmarketusa() {
        return intendedmarketusa;
    }

    public void setIntendedmarketusa(Boolean intendedmarketusa) {
        this.intendedmarketusa = intendedmarketusa;
    }

    public Boolean getAdditionalservicesendmorecontractforms() {
        return additionalservicesendmorecontractforms;
    }

    public void setAdditionalservicesendmorecontractforms(Boolean additionalservicesendmorecontractforms) {
        this.additionalservicesendmorecontractforms = additionalservicesendmorecontractforms;
    }

    public Boolean getIntendedmarketcaricom() {
        return intendedmarketcaricom;
    }

    public void setIntendedmarketcaricom(Boolean intendedmarketcaricom) {
        this.intendedmarketcaricom = intendedmarketcaricom;
    }

    public String getServicerequesteddetails() {
        return servicerequesteddetails;
    }

    public void setServicerequesteddetails(String servicerequesteddetails) {
        this.servicerequesteddetails = servicerequesteddetails;
    }

    public Boolean getServicerequestedtesting() {
        return servicerequestedtesting;
    }

    public void setServicerequestedtesting(Boolean servicerequestedtesting) {
        this.servicerequestedtesting = servicerequestedtesting;
    }

    public Boolean getAutoaddsampleinformation() {
        return autoaddsampleinformation;
    }

    public void setAutoaddsampleinformation(Boolean autoaddsampleinformation) {
        this.autoaddsampleinformation = autoaddsampleinformation;
    }

    public Boolean getServicerequestedinspection() {
        return servicerequestedinspection;
    }

    public void setServicerequestedinspection(Boolean servicerequestedinspection) {
        this.servicerequestedinspection = servicerequestedinspection;
    }

    public Boolean getAdditionalserviceurgent() {
        return additionalserviceurgent;
    }

    public void setAdditionalserviceurgent(Boolean additionalserviceurgent) {
        this.additionalserviceurgent = additionalserviceurgent;
    }

    public Boolean getServicerequestedconsultancy() {
        return servicerequestedconsultancy;
    }

    public void setServicerequestedconsultancy(Boolean servicerequestedconsultancy) {
        this.servicerequestedconsultancy = servicerequestedconsultancy;
    }

    public String getSubmittedby() {
        return submittedby;
    }

    public void setSubmittedby(String submittedby) {
        this.submittedby = submittedby;
    }

    public String getServicerequestedothertext() {
        return servicerequestedothertext;
    }

    public void setServicerequestedothertext(String servicerequestedothertext) {
        this.servicerequestedothertext = servicerequestedothertext;
    }

    public Boolean getIntendedmarketuk() {
        return intendedmarketuk;
    }

    public void setIntendedmarketuk(Boolean intendedmarketuk) {
        this.intendedmarketuk = intendedmarketuk;
    }

    public Boolean getIntendedmarketother() {
        return intendedmarketother;
    }

    public void setIntendedmarketother(Boolean intendedmarketother) {
        this.intendedmarketother = intendedmarketother;
    }

    public String getReceivedby() {
        return receivedby;
    }

    public void setReceivedby(String receivedby) {
        this.receivedby = receivedby;
    }

    public String getAdditionalserviceothertext() {
        return additionalserviceothertext;
    }

    public void setAdditionalserviceothertext(String additionalserviceothertext) {
        this.additionalserviceothertext = additionalserviceothertext;
    }

    public Boolean getServicerequestedtraining() {
        return servicerequestedtraining;
    }

    public void setServicerequestedtraining(Boolean servicerequestedtraining) {
        this.servicerequestedtraining = servicerequestedtraining;
    }

    public BigInteger getBillingaddressid() {
        return billingaddressid;
    }

    public void setBillingaddressid(BigInteger billingaddressid) {
        this.billingaddressid = billingaddressid;
    }

    public Boolean getServicerequestedother() {
        return servicerequestedother;
    }

    public void setServicerequestedother(Boolean servicerequestedother) {
        this.servicerequestedother = servicerequestedother;
    }

    public Boolean getIntendedmarketcanada() {
        return intendedmarketcanada;
    }

    public void setIntendedmarketcanada(Boolean intendedmarketcanada) {
        this.intendedmarketcanada = intendedmarketcanada;
    }

    @XmlTransient
    @JsonIgnore
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
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
        if (!(object instanceof Servicecontract)) {
            return false;
        }
        Servicecontract other = (Servicecontract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Servicecontract[ id=" + id + " ]";
    }
    
}
