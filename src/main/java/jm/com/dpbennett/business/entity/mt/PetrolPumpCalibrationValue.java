/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.mt;

import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.Collator;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolpumpcalibrationvalue")
public class PetrolPumpCalibrationValue implements Comparable, Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRecorded;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee recordedBy;
    private Double totalizerStart = 0.0;
    private Double totalizerEnd = 0.0;
    private Double rate = 0.0;
    private Double petrolUsage = 0.0;
    private Double petrolCost = 0.0;
    // pump nozzle errors
    private Double error1 = 0.0;
    private Double error2 = 0.0;
    private Double error3 = 0.0;
    private Double error4 = 0.0;
    private Double error5 = 0.0;
    private Double error6 = 0.0;
    // pump measure/capacity (in litres) used in calibration
    private Double measure1 = 5.0;
    private Double measure2 = 5.0;
    private Double measure3 = 5.0;
    private Double measure4 = 20.0;
    private Double measure5 = 20.0;
    private Double measure6 = 20.0;
    // pump measure tolerances
    private Double measureTolerance1 = 15.0;
    private Double measureTolerance2 = 15.0;
    private Double measureTolerance3 = 15.0;
    private Double measureTolerance4 = 60.0;
    private Double measureTolerance5 = 60.0;
    private Double measureTolerance6 = 60.0;

    public PetrolPumpCalibrationValue() {
    }

    public PetrolPumpCalibrationValue(PetrolPumpCalibrationValue original) {
        this.dateRecorded = original.dateRecorded;
        this.totalizerStart = original.totalizerStart;
        this.totalizerEnd = original.totalizerEnd;
        this.rate = original.rate;
        this.petrolUsage = original.petrolUsage;
        this.petrolCost = original.petrolCost;
        this.error1 = original.error1;
        this.error2 = original.error2;
        this.error3 = original.error3;
        this.error4 = original.error4;
        this.error5 = original.error5;
        this.error6 = original.error6;
        this.measure1 = original.measure1;
        this.measure2 = original.measure2;
        this.measure3 = original.measure3;
        this.measure4 = original.measure4;
        this.measure5 = original.measure5;
        this.measure6 = original.measure6;
        this.measureTolerance1 = original.measureTolerance1;
        this.measureTolerance2 = original.measureTolerance2;
        this.measureTolerance3 = original.measureTolerance3;
        this.measureTolerance4 = original.measureTolerance4;
        this.measureTolerance5 = original.measureTolerance5;
        this.measureTolerance6 = original.measureTolerance6;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(Employee recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Double getPetrolCost() {
        return petrolCost;

    }

    public void setPetrolCost(Double petrolCost) {
        this.petrolCost = petrolCost;

    }

    public Double getPetrolUsage() {
        return petrolUsage;

    }

    public void setPetrolUsage(Double petrolUsage) {
        this.petrolUsage = petrolUsage;

    }

    public Double getRate() {
        return rate;

    }

    public void setRate(Double rate) {
        this.rate = rate;

    }

    public String getPass() {
        int numOfFailuresForFirst3 = 0; // first 3 errors

        int numOfFailuresForSecond3 = 0; // second 3 errors
        String pass = "Yes";

        // first 3 measures
        if (Math.abs(error1) > Math.abs(measureTolerance1 * 1000)) {
            ++numOfFailuresForFirst3;

        }
        if (Math.abs(error2) > Math.abs(measureTolerance2 * 1000)) {
            ++numOfFailuresForFirst3;

        }
        if (Math.abs(error3) > Math.abs(measureTolerance3 * 1000)) {
            ++numOfFailuresForFirst3;

        } // remaining 3 measures
        if (Math.abs(error4) > Math.abs(measureTolerance4 * 1000)) {
            ++numOfFailuresForSecond3;

        }
        if (Math.abs(error5) > Math.abs(measureTolerance5 * 1000)) {
            ++numOfFailuresForSecond3;

        }
        if (Math.abs(error6) > Math.abs(measureTolerance6 * 1000)) {
            ++numOfFailuresForSecond3;

        }

        if (numOfFailuresForFirst3 > 1) {
            pass = "No";

        }
        if (numOfFailuresForSecond3 > 1) {
            pass = "No";

        }

        return pass;

    }

    public Date getDateRecorded() {
        return dateRecorded;

    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;

    }

    public Double getTotalizerStart() {
        return totalizerStart;

    }

    public void setTotalizerStart(Double totalizerStart) {
        this.totalizerStart = totalizerStart;

    }

    public Double getTotalizerEnd() {
        return totalizerEnd;

    }

    public void setTotalizerEnd(Double totalizerEnd) {
        this.totalizerEnd = totalizerEnd;

    }

    public Double getError1() {
        return error1;

    }

    public void setError1(Double error1) {
        this.error1 = error1;

    }

    public Double getError2() {
        return error2;

    }

    public void setError2(Double error2) {
        this.error2 = error2;

    }

    public Double getError3() {
        return error3;

    }

    public void setError3(Double error3) {
        this.error3 = error3;

    }

    public Double getError4() {
        return error4;

    }

    public void setError4(Double error4) {
        this.error4 = error4;

    }

    public Double getError5() {
        return error5;

    }

    public void setError5(Double error5) {
        this.error5 = error5;

    }

    public Double getError6() {
        return error6;

    }

    public void setError6(Double error6) {
        this.error6 = error6;

    }

    public Double getMeasure1() {
        return measure1;

    }

    public void setMeasure1(Double measure1) {
        this.measure1 = measure1;

    }

    public Double getMeasure2() {
        return measure2;

    }

    public void setMeasure2(Double measure2) {
        this.measure2 = measure2;

    }

    public Double getMeasure3() {
        return measure3;

    }

    public void setMeasure3(Double measure3) {
        this.measure3 = measure3;

    }

    public Double getMeasure4() {
        return measure4;

    }

    public void setMeasure4(Double measure4) {
        this.measure4 = measure4;

    }

    public Double getMeasure5() {
        return measure5;

    }

    public void setMeasure5(Double measure5) {
        this.measure5 = measure5;

    }

    public Double getMeasure6() {
        return measure6;

    }

    public void setMeasure6(Double measure6) {
        this.measure6 = measure6;

    }

    public Double getMeasureTolerance1() {
        return measureTolerance1;

    }

    public void setMeasureTolerance1(Double measureTolerance1) {
        this.measureTolerance1 = measureTolerance1;

    }

    public Double getMeasureTolerance2() {
        return measureTolerance2;

    }

    public void setMeasureTolerance2(Double measureTolerance2) {
        this.measureTolerance2 = measureTolerance2;

    }

    public Double getMeasureTolerance3() {
        return measureTolerance3;

    }

    public void setMeasureTolerance3(Double measureTolerance3) {
        this.measureTolerance3 = measureTolerance3;

    }

    public Double getMeasureTolerance4() {
        return measureTolerance4;

    }

    public void setMeasureTolerance4(Double measureTolerance4) {
        this.measureTolerance4 = measureTolerance4;

    }

    public Double getMeasureTolerance5() {
        return measureTolerance5;

    }

    public void setMeasureTolerance5(Double measureTolerance5) {
        this.measureTolerance5 = measureTolerance5;

    }

    public Double getMeasureTolerance6() {
        return measureTolerance6;

    }

    public void setMeasureTolerance6(Double measureTolerance6) {
        this.measureTolerance6 = measureTolerance6;

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
        if (!(object instanceof PetrolPumpCalibrationValue)) {
            return false;

        }
        PetrolPumpCalibrationValue other = (PetrolPumpCalibrationValue) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;

        }
        return true;

    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.PetrolPumpCalibrationValue[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            
            getRecordedBy().save(em);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Petrol Pump Calibration not saved");
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean getIsDirty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
