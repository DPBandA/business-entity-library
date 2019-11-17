/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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
import jm.com.dpbennett.business.entity.mt.Calibration;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolpumpcalibration")
public class PetrolPumpCalibration implements Calibration, Comparable, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private Double hourlyRate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date calibrationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reCalibrationDate;
    @OneToOne(cascade=CascadeType.REFRESH)
    private Employee calibrationDoneBy;
    @OneToMany(cascade=CascadeType.ALL)
    private List<PetrolPumpCalibrationValue> calibrationValues;

    public PetrolPumpCalibration() {
       calibrationValues = new ArrayList<PetrolPumpCalibrationValue>();
       calibrationValues.add(new PetrolPumpCalibrationValue());
    }
   
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public PetrolPumpCalibrationValue getLastCalibrationValue() {
       if (calibrationValues.size() > 0) {
           return calibrationValues.get(calibrationValues.size() - 1);
       }
       else {
           calibrationValues.add(new PetrolPumpCalibrationValue());
           return calibrationValues.get(0);
       }
    }

    public List<PetrolPumpCalibrationValue> getCalibrationValues() {
        return calibrationValues;
    }

    public void setCalibrationValues(List<PetrolPumpCalibrationValue> calibrationValues) {
        this.calibrationValues = calibrationValues;
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
        if (!(object instanceof PetrolPumpCalibration)) {
            return false;
        }
        PetrolPumpCalibration other = (PetrolPumpCalibration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.PetrolPumpCalibration[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public Double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public Date getCalibrationDate() {
        return calibrationDate;
    }

    @Override
    public void setCalibrationDate(Date calibrationDate) {
        this.calibrationDate = calibrationDate;
    }

    @Override
    public Employee getCalibrationDoneBy() {
        return calibrationDoneBy;
    }

    @Override
    public void setCalibrationDoneBy(Employee calibrationDoneBy) {
        this.calibrationDoneBy = calibrationDoneBy;
    }

    @Override
    public Date getReCalibrationDate() {
        return reCalibrationDate;
    }

    @Override
    public void setReCalibrationDate(Date reCalibrationDate) {
        this.reCalibrationDate = reCalibrationDate;
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

}
