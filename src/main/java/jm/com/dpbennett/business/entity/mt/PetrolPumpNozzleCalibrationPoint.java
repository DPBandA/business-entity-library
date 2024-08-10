/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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

import java.io.Serializable;
import java.text.Collator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolpumpnozzlecalibrationpoint")
public class PetrolPumpNozzleCalibrationPoint implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long number;
    @OneToOne(cascade = CascadeType.REFRESH)
    private TestMeasure testMeasure;
    private Double error;
    private Double tolerance;
    private Boolean useit;

    public PetrolPumpNozzleCalibrationPoint() {
    }

    public PetrolPumpNozzleCalibrationPoint(Long number) {
        this.number = number;
    }

    public PetrolPumpNozzleCalibrationPoint(Long number,
            TestMeasure testMeasure,
            Double error,
            Double tolerance) {
        this.number = number;
        this.testMeasure = testMeasure;
        this.error = error;
        this.tolerance = tolerance;
    }

    public PetrolPumpNozzleCalibrationPoint(Long number,
            TestMeasure testMeasure) {
        this.number = number;
        this.testMeasure = testMeasure;
    }

    public PetrolPumpNozzleCalibrationPoint(PetrolPumpNozzleCalibrationPoint point) {
        this.number = point.number;
        this.testMeasure = point.testMeasure;
        this.error = point.error;
        this.tolerance = point.tolerance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getUseit() {
        return useit;
    }

    public void setUseit(Boolean useit) {
        this.useit = useit;
    }
     public Long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double error) {
        this.error = error;
    }

    public TestMeasure getTestMeasure() {
        return testMeasure;
    }

    public void setTestMeasure(TestMeasure testMeasure) {
        this.testMeasure = testMeasure;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
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
        if (!(object instanceof PetrolPumpNozzleCalibrationPoint)) {
            return false;
        }
        PetrolPumpNozzleCalibrationPoint other = (PetrolPumpNozzleCalibrationPoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.PetrolPumpNozzleCalibrationPoint[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.number.toString(), ((PetrolPumpNozzleCalibrationPoint) o).number.toString());
    }
}
