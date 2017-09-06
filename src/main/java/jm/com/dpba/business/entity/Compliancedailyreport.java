/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "compliancedailyreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compliancedailyreport.findAll", query = "SELECT c FROM Compliancedailyreport c"),
    @NamedQuery(name = "Compliancedailyreport.findById", query = "SELECT c FROM Compliancedailyreport c WHERE c.id = :id"),
    @NamedQuery(name = "Compliancedailyreport.findByDriver", query = "SELECT c FROM Compliancedailyreport c WHERE c.driver = :driver"),
    @NamedQuery(name = "Compliancedailyreport.findByEndofperiod", query = "SELECT c FROM Compliancedailyreport c WHERE c.endofperiod = :endofperiod"),
    @NamedQuery(name = "Compliancedailyreport.findByLocation", query = "SELECT c FROM Compliancedailyreport c WHERE c.location = :location"),
    @NamedQuery(name = "Compliancedailyreport.findByName", query = "SELECT c FROM Compliancedailyreport c WHERE c.name = :name"),
    @NamedQuery(name = "Compliancedailyreport.findByReportname", query = "SELECT c FROM Compliancedailyreport c WHERE c.reportname = :reportname"),
    @NamedQuery(name = "Compliancedailyreport.findByStartofperiod", query = "SELECT c FROM Compliancedailyreport c WHERE c.startofperiod = :startofperiod"),
    @NamedQuery(name = "Compliancedailyreport.findByTeam", query = "SELECT c FROM Compliancedailyreport c WHERE c.team = :team"),
    @NamedQuery(name = "Compliancedailyreport.findByTeammembers", query = "SELECT c FROM Compliancedailyreport c WHERE c.teammembers = :teammembers"),
    @NamedQuery(name = "Compliancedailyreport.findByTimeofarrival", query = "SELECT c FROM Compliancedailyreport c WHERE c.timeofarrival = :timeofarrival"),
    @NamedQuery(name = "Compliancedailyreport.findByTimeofdeparture", query = "SELECT c FROM Compliancedailyreport c WHERE c.timeofdeparture = :timeofdeparture")})
public class Compliancedailyreport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "DRIVER")
    private String driver;
    @Column(name = "ENDOFPERIOD")
    @Temporal(TemporalType.TIME)
    private Date endofperiod;
    @Size(max = 255)
    @Column(name = "LOCATION")
    private String location;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "REPORTNAME")
    private String reportname;
    @Column(name = "STARTOFPERIOD")
    @Temporal(TemporalType.TIME)
    private Date startofperiod;
    @Size(max = 255)
    @Column(name = "TEAM")
    private String team;
    @Size(max = 255)
    @Column(name = "TEAMMEMBERS")
    private String teammembers;
    @Column(name = "TIMEOFARRIVAL")
    @Temporal(TemporalType.TIME)
    private Date timeofarrival;
    @Column(name = "TIMEOFDEPARTURE")
    @Temporal(TemporalType.TIME)
    private Date timeofdeparture;

    public Compliancedailyreport() {
    }

    public Compliancedailyreport(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Date getEndofperiod() {
        return endofperiod;
    }

    public void setEndofperiod(Date endofperiod) {
        this.endofperiod = endofperiod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public Date getStartofperiod() {
        return startofperiod;
    }

    public void setStartofperiod(Date startofperiod) {
        this.startofperiod = startofperiod;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeammembers() {
        return teammembers;
    }

    public void setTeammembers(String teammembers) {
        this.teammembers = teammembers;
    }

    public Date getTimeofarrival() {
        return timeofarrival;
    }

    public void setTimeofarrival(Date timeofarrival) {
        this.timeofarrival = timeofarrival;
    }

    public Date getTimeofdeparture() {
        return timeofdeparture;
    }

    public void setTimeofdeparture(Date timeofdeparture) {
        this.timeofdeparture = timeofdeparture;
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
        if (!(object instanceof Compliancedailyreport)) {
            return false;
        }
        Compliancedailyreport other = (Compliancedailyreport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Compliancedailyreport[ id=" + id + " ]";
    }
    
}
