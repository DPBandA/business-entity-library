/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.Collator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolpumpnozzlecalibration")
public class PetrolPumpNozzleCalibration implements Calibration, Comparable,
        Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(PetrolPumpNozzleCalibration.class.getName());
    public static PetrolPumpNozzleCalibration findLastPetrolPumpNozzleCalibrationByJobNumber(EntityManager em, String jobNumber) {
        List<PetrolPumpNozzleCalibration> foundPetrolPumpNozzleCalibrations;
        
        String searchQuery
                = "SELECT PetrolPumpNozzleCalibration FROM PetrolPumpNozzleCalibration petrolPumpNozzleCalibration"
                + " JOIN petrolPumpNozzleCalibration.job job"
                + " WHERE job.jobNumber = '" + jobNumber + "'"
                + " ORDER BY petrolPumpNozzleCalibration.id DESC";
        
        try {
            foundPetrolPumpNozzleCalibrations = em.createQuery(searchQuery, PetrolPumpNozzleCalibration.class).getResultList();
            if (foundPetrolPumpNozzleCalibrations != null) {
                if (!foundPetrolPumpNozzleCalibrations.isEmpty()) {
                    return foundPetrolPumpNozzleCalibrations.get(0);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return null;
    }
    public static List<PetrolPumpNozzleCalibration> findPetrolPumpNozzleCalibrationsByJobNumber(EntityManager em, String jobNumber) {
        List<PetrolPumpNozzleCalibration> foundPetrolPumpNozzleCalibrations;
        
        String searchQuery
                = "SELECT PetrolPumpNozzleCalibration FROM PetrolPumpNozzleCalibration petrolPumpNozzleCalibration"
                + " JOIN petrolPumpNozzleCalibration.job job"
                + " WHERE job.jobNumber = '" + jobNumber + "'"
                + " ORDER BY petrolPumpNozzleCalibration.id DESC";
        
        try {
            foundPetrolPumpNozzleCalibrations = em.createQuery(searchQuery, PetrolPumpNozzleCalibration.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return foundPetrolPumpNozzleCalibrations;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String name;
    private String type;
    private Double hourlyRate = 0.0;
    private Double petrolPriceRate = 0.0;
    private Double totalizerStart = 0.0;
    private Double totalizerEnd = 0.0;
    private String productDispensed;
    private Double setPetrolUsage = 0.0;
    private Double actualPetrolUsage = 0.0;
    private Double petrolCost = 0.0;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PetrolPumpNozzleCalibrationPoint> calibrationPoints;
    private LocalDateTime calibrationDate;
    private LocalDateTime reCalibrationDate;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee calibrationDoneBy;
    private String results;
    private LocalDateTime dateAndTimeRecorded;

    public PetrolPumpNozzleCalibration() {
        calibrationPoints = new ArrayList<>();

        int i;
        for (i = 0; i < 3; i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    new TestMeasure(5.0, "L", 5.0 * 0.003)));
        }
        for (i = 3; i < 6; i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    new TestMeasure(20.0, "L", 20.0 * 0.003)));
        }
    }

    public PetrolPumpNozzleCalibration(ArrayList<TestMeasure> testMeasures) {
        calibrationPoints = new ArrayList<>();

        int i;
        for (i = 0; i < 3; i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(0)));
        }
        for (i = 3; i < 6; i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(1)));
        }
    }
    public PetrolPumpNozzleCalibration(PetrolPumpNozzleCalibration original) {
        this.calibrationPoints = new ArrayList<>();
        this.name = original.name;
        this.type = original.type;
        this.hourlyRate = original.hourlyRate;
        this.petrolPriceRate = original.petrolPriceRate;
        this.totalizerStart = original.totalizerStart;
        this.totalizerEnd = original.totalizerEnd;
        this.productDispensed = original.productDispensed;
        this.setPetrolUsage = original.setPetrolUsage;
        this.actualPetrolUsage = original.actualPetrolUsage;
        this.petrolCost = original.petrolCost;
        
        List<PetrolPumpNozzleCalibrationPoint> calPoints = original.getCalibrationPoints();
        for (int i = 0; i < original.calibrationPoints.size(); i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint(calPoints.get(i)));
        }
        
        this.calibrationDate = original.calibrationDate;
        this.reCalibrationDate = original.reCalibrationDate;
        this.results = original.results;
        this.dateAndTimeRecorded = original.dateAndTimeRecorded;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setTestCalibrationPointsTestMeasures(ArrayList<TestMeasure> testMeasures) {
        this.calibrationPoints.clear();

        int i;
        for (i = 0; i < 3; i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(0)));
        }
        for (i = 3; i < 6; i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(1)));
        }
    }


    public LocalDateTime getDateAndTimeRecorded() {
        return dateAndTimeRecorded;
    }

    public void setDateAndTimeRecorded(LocalDateTime dateAndTimeRecorded) {
        this.dateAndTimeRecorded = dateAndTimeRecorded;
    }

    public Integer getNumberOfCalPoints() {
        return calibrationPoints.size();
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
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            if (calibrationDoneBy != null) {
                calibrationDoneBy.save(em);
            }

            for (PetrolPumpNozzleCalibrationPoint calibrationPoint : getCalibrationPoints()) {
                calibrationPoint.setOwnerId(id);
                calibrationPoint.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Petrol Pump Nozzle Calibration not saved");
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

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public String getLowestOfFirst3Errors() {
        return getErrorFromSortedErrors(0, 0, 2);
    }

    public String getSecondLowestOfFirst3Errors() {
        return getErrorFromSortedErrors(1, 0, 2);
    }

    public String getLowestOfLast3Errors() {
        return getErrorFromSortedErrors(0, 3, 5);
    }

    public String getSecondLowestOfLast3Errors() {
        return getErrorFromSortedErrors(1, 3, 5);
    }

    public List<PetrolPumpNozzleCalibrationPoint> getReportCalibrationPoints() {
        ArrayList<PetrolPumpNozzleCalibrationPoint> points = new ArrayList<>();

        points.add(getNozzleCalibrationPointBasedOnSortedErrors(0, 0, 2));
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(1, 0, 2));
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(0, 3, 5));
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(1, 3, 5));

        return points;
    }

    public String getErrorFromSortedErrors(int errorIndex, int minIndex, int maxIndex) {
        ArrayList<PetrolPumpNozzleCalibrationPoint> points = new ArrayList<>();

        try {
            if (!getCalibrationPoints().isEmpty()) {
                for (int i = minIndex; i < (maxIndex + 1); i++) {
                    
                    PetrolPumpNozzleCalibrationPoint newPoint = new PetrolPumpNozzleCalibrationPoint(getCalibrationPoints().get(i));
                    if (newPoint.getError() == null) {
                        newPoint.setError(Double.MAX_VALUE);
                    }
                    points.add(newPoint);
                }
            }
            if (!points.isEmpty()) {
                Collections.sort(points, new CalibrationPointErrorComparator());

                PetrolPumpNozzleCalibrationPoint point = getNozzleCalibrationPointBasedOnSortedErrors(errorIndex, minIndex, maxIndex);

                if (point.getError() == Double.MAX_VALUE) {
                    return "--";
                } else {
                    if (point.getError() > 0.0) {
                        return "+" + point.getError().toString();
                    } else if (point.getError() < 0.0) {
                        return point.getError().toString();
                    } else {
                        return point.getError().toString();
                    }
                }
            } else {
                return "--";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "--";
        }
    }

    public PetrolPumpNozzleCalibrationPoint getNozzleCalibrationPointBasedOnSortedErrors(int errorIndex, int minIndex, int maxIndex) {
        ArrayList<PetrolPumpNozzleCalibrationPoint> points = new ArrayList<>();

        try {
            if (!getCalibrationPoints().isEmpty()) {
                for (int i = minIndex; i < (maxIndex + 1); i++) {
                    PetrolPumpNozzleCalibrationPoint newPoint = new PetrolPumpNozzleCalibrationPoint(getCalibrationPoints().get(i));
                    if (newPoint.getError() == null) {
                        newPoint.setError(Double.MAX_VALUE);
                    }
                    points.add(newPoint);
                }
            }
            if (!points.isEmpty()) {
                Collections.sort(points, new CalibrationPointErrorComparator());

                return points.get(errorIndex);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Double getError0() {
        return getCalibrationPoints().get(0).getError();
    }

    public void setError0(Double error) {
        getCalibrationPoints().get(0).setError(error);
    }

    public Double getError1() {
        return getCalibrationPoints().get(1).getError();
    }

    public void setError1(Double error) {
        getCalibrationPoints().get(1).setError(error);
    }

    public Double getError2() {
        return getCalibrationPoints().get(2).getError();
    }

    public void setError2(Double error) {
        getCalibrationPoints().get(2).setError(error);
    }

    public Double getError3() {
        return getCalibrationPoints().get(3).getError();
    }

    public void setError3(Double error) {
        getCalibrationPoints().get(3).setError(error);
    }

    public Double getError4() {
        return getCalibrationPoints().get(4).getError();
    }

    public void setError4(Double error) {
        getCalibrationPoints().get(4).setError(error);
    }

    public Double getError5() {
        return getCalibrationPoints().get(5).getError();
    }

    public void setError5(Double error) {
        getCalibrationPoints().get(5).setError(error);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getResults() {

        results = getResults(getCalibrationPoints());

        return results;
    }

    public String getCalibrationReportResults() {

        results = getResults(getReportCalibrationPoints());

        return results;
    }

    public String getResults(List<PetrolPumpNozzleCalibrationPoint> calPoints) {
        Boolean pass = null;

        for (PetrolPumpNozzleCalibrationPoint calPoint : calPoints) {
            if ((calPoint.getError() != null) && (calPoint.getError() != Double.MAX_VALUE)) {
                if (Math.abs(calPoint.getTestMeasure().getTolerance() * 1000) < Math.abs(calPoint.getError())) {
                    if (pass == null) {
                        pass = false;
                    } else {
                        pass = pass && false;
                    }
                } else {
                    if (pass == null) {
                        pass = true;
                    } else {
                        pass = pass && true;
                    }
                }
            }
        }

        if (pass == null) {
            setResults("");
        } else if (pass) {
            setResults("Accepted");
        } else {
            setResults("Rejected");
        }

        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Double getPetrolCost() {
        petrolCost = petrolPriceRate * actualPetrolUsage;
        return petrolCost;
    }

    public void setPetrolCost(Double petrolCost) {
        this.petrolCost = petrolCost;
    }

    public Double getActualPetrolUsage() {
        return actualPetrolUsage;
    }

    public void setActualPetrolUsage(Double actualPetrolUsage) {
        this.actualPetrolUsage = actualPetrolUsage;
    }

    public Double getSetPetrolUsage() {
        return setPetrolUsage;
    }

    public void setSetPetrolUsage(Double setPetrolUsage) {
        this.setPetrolUsage = setPetrolUsage;
    }

    public Double getPetrolPriceRate() {
        return petrolPriceRate;
    }

    public void setPetrolPriceRate(Double petrolPriceRate) {
        this.petrolPriceRate = petrolPriceRate;
    }

    public List<PetrolPumpNozzleCalibrationPoint> getCalibrationPoints() {
        if (calibrationPoints != null) {
            Collections.sort(calibrationPoints);
        } else {
            calibrationPoints = new ArrayList<>();
        }

        return calibrationPoints;
    }

    public void setCalibrationPoints(List<PetrolPumpNozzleCalibrationPoint> calibrationPoints) {
        this.calibrationPoints = calibrationPoints;
    }

    public String getProductDispensed() {
        return productDispensed;
    }

    public void setProductDispensed(String productDispensed) {
        this.productDispensed = productDispensed;
    }

    public Double getTotalizerEnd() {
        return totalizerEnd;
    }

    public void setTotalizerEnd(Double totalizerEnd) {
        this.totalizerEnd = totalizerEnd;
    }

    public Double getTotalizerStart() {
        return totalizerStart;
    }

    public void setTotalizerStart(Double totalizerStart) {
        this.totalizerStart = totalizerStart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PetrolPumpNozzleCalibration)) {
            return false;
        }
        PetrolPumpNozzleCalibration other = (PetrolPumpNozzleCalibration) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.com.dpbennett.entity.PetrolPumpNozzleCalibration[id=" + id + "]";
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

    @Override
    public Double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public LocalDateTime getCalibrationDate() {
        return calibrationDate;
    }

    @Override
    public void setCalibrationDate(LocalDateTime calibrationDate) {
        this.calibrationDate = calibrationDate;
    }

    @Override
    public Employee getCalibrationDoneBy() {

        if (calibrationDoneBy == null) {
            return new Employee();
        }

        return calibrationDoneBy;
    }

    @Override
    public void setCalibrationDoneBy(Employee calibrationDoneBy) {
        this.calibrationDoneBy = calibrationDoneBy;
    }

    @Override
    public LocalDateTime getReCalibrationDate() {
        return reCalibrationDate;
    }

    @Override
    public void setReCalibrationDate(LocalDateTime reCalibrationDate) {
        this.reCalibrationDate = reCalibrationDate;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(o.toString(), this.toString());
    }

    class CalibrationPointErrorComparator implements Comparator<PetrolPumpNozzleCalibrationPoint> {
        
        @Override
        public int compare(PetrolPumpNozzleCalibrationPoint o1, PetrolPumpNozzleCalibrationPoint o2) {
            if (Math.abs(o1.getError()) < Math.abs(o2.getError())) {
                return -1;
            } else if (Math.abs(o1.getError()) == Math.abs(o2.getError())) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
