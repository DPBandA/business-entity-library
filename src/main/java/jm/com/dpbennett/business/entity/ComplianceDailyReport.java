 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "complianceDailyReport")
public class ComplianceDailyReport implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String name = "";
    private String reportName = "";
    private String team = "";
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date startOfPeriod = null;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date endOfPeriod = null;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timeOfArrival = null;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timeOfDeparture = null;
    private String location = "";
    private String teamMembers = "";
    private String driver = "";

    public ComplianceDailyReport() {
        this.name = "";
        this.reportName = "";
        this.startOfPeriod = new Date();
        this.endOfPeriod = startOfPeriod;
        this.location = "";
        this.teamMembers = "";
    }

    public ComplianceDailyReport(String reportName, 
            Date startOfPeriod, 
            String location, 
            String teamMembers) {
        this.name = reportName;
        this.reportName = reportName;
        this.startOfPeriod = startOfPeriod;
        this.endOfPeriod = startOfPeriod;
        this.location = location;
        this.teamMembers = teamMembers;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getNumOfInspectorsOnVisit() {
        return new Integer(teamMembers.split("[,;:|/]").length);
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
        if (!(object instanceof ComplianceDailyReport)) {
            return false;
        }
        ComplianceDailyReport other = (ComplianceDailyReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Date getStartOfPeriod() {
        return startOfPeriod;
    }

    public void setStartOfPeriod(Date startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }

    public Date getEndOfPeriod() {
        return endOfPeriod;
    }

    public void setEndOfPeriod(Date endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
    }

    public Date getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Date timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public Date getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(Date timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
