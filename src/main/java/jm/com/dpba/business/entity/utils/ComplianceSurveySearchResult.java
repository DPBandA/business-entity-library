/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity.utils;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpba.business.entity.Employee;


/**
 *
 * @author desbenn
 */
@XmlRootElement
public class ComplianceSurveySearchResult {
    private Long id;
    private Date dateOfSurvey;
    private Employee inspector;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(Date dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }

    public Employee getInspector() {
        return inspector;
    }

    public void setInspector(Employee inspector) {
        this.inspector = inspector;
    }
    
}
