/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "petrolpumpnozzlecalibration")
public class PetrolPumpNozzleCalibration implements Calibration, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Job job;
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date calibrationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reCalibrationDate;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee calibrationDoneBy;
    private String results;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTimeRecorded;

    public PetrolPumpNozzleCalibration() {
        calibrationPoints = new ArrayList<>();

        int i;
        // init cal point for first 3 points
        for (i = 0; i < 3; i++) { // tk put in options
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    new TestMeasure(5.0, "L", 5.0 * 0.003)));
        }
        // init cal point for last 3 points
        for (i = 3; i < 6; i++) { // tk put in options
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    new TestMeasure(20.0, "L", 20.0 * 0.003)));
        }
    }

    public PetrolPumpNozzleCalibration(ArrayList<TestMeasure> testMeasures) {
        calibrationPoints = new ArrayList<>();


        int i;
        // init cal point for first 3 points
        for (i = 0; i < 3; i++) { // tk put in options
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(0)));
        }
        // init cal point for last 3 points
        for (i = 3; i < 6; i++) { // tk put in options
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(1)));
        }
    }

    public void setTestCalibrationPointsTestMeasures(ArrayList<TestMeasure> testMeasures) {
        this.calibrationPoints.clear();

        int i;
        // init cal point for first 3 points
        for (i = 0; i < 3; i++) { // tk put in options
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(0)));
        }
        // init cal point for last 3 points
        for (i = 3; i < 6; i++) { // tk put in options
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint((long) i,
                    testMeasures.get(1)));
        }
    }

    public PetrolPumpNozzleCalibration(PetrolPumpNozzleCalibration original) {
        this.calibrationPoints = new ArrayList<>();

        this.job = original.job;
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
        // copy cal points
        List<PetrolPumpNozzleCalibrationPoint> calPoints = original.getCalibrationPoints();
        for (int i = 0; i < original.calibrationPoints.size(); i++) {
            this.calibrationPoints.add(new PetrolPumpNozzleCalibrationPoint(calPoints.get(i)));
        }

        this.calibrationDate = original.calibrationDate;
        this.reCalibrationDate = original.reCalibrationDate;
        this.calibrationDoneBy = original.calibrationDoneBy;
        this.results = original.results;
        this.dateAndTimeRecorded = original.dateAndTimeRecorded;
    }

    public Date getDateAndTimeRecorded() {
        return dateAndTimeRecorded;
    }

    public void setDateAndTimeRecorded(Date dateAndTimeRecorded) {
        this.dateAndTimeRecorded = dateAndTimeRecorded;
    }

    public Integer getNumberOfCalPoints() {
        return calibrationPoints.size();
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

        // gatther the points used in the client's report and return
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(0, 0, 2));
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(1, 0, 2));
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(0, 3, 5));
        points.add(getNozzleCalibrationPointBasedOnSortedErrors(1, 3, 5));

        return points;
    }

    public String getErrorFromSortedErrors(int errorIndex, int minIndex, int maxIndex) {
        //Double lowestError = getCalibrationPoints().get(0).getError();
        ArrayList<PetrolPumpNozzleCalibrationPoint> points = new ArrayList<>();

        try {
            if (!getCalibrationPoints().isEmpty()) {
                // get first 3 points for sorting
                for (int i = minIndex; i < (maxIndex + 1); i++) {
                    // create new cal point with non-null error suitable for
                    // sorting
                    PetrolPumpNozzleCalibrationPoint newPoint = new PetrolPumpNozzleCalibrationPoint(getCalibrationPoints().get(i));
                    if (newPoint.getError() == null) {
                        newPoint.setError(Double.MAX_VALUE);
                    }
                    points.add(newPoint);
                }
            }
            // sort and return the lowest error
            if (!points.isEmpty()) {
                // sort by the absolute value of the errors
                Collections.sort(points, new CalibrationPointErrorComparator());

                PetrolPumpNozzleCalibrationPoint point = getNozzleCalibrationPointBasedOnSortedErrors(errorIndex, minIndex, maxIndex);

                // return the error as a string
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
        //Double lowestError = getCalibrationPoints().get(0).getError();
        ArrayList<PetrolPumpNozzleCalibrationPoint> points = new ArrayList<>();

        try {
            if (!getCalibrationPoints().isEmpty()) {
                // get first 3 points for sorting
                for (int i = minIndex; i < (maxIndex + 1); i++) {
                    // create new cal point with non-null error suitable for
                    // sorting
                    PetrolPumpNozzleCalibrationPoint newPoint = new PetrolPumpNozzleCalibrationPoint(getCalibrationPoints().get(i));
                    if (newPoint.getError() == null) {
                        newPoint.setError(Double.MAX_VALUE);
                    }
                    points.add(newPoint);
                }
            }
            // sort and return the lowest error
            if (!points.isEmpty()) {
                // sort by the absolute value of the errors
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

    public Long getId() {
        return id;
    }

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

        // do analysis to determine pass or fail etc.
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
        // set result
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

    public Job getJob() {
        if (job == null) {
            job = new Job();
            job.setJobNumber("");
            return job;
        }
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetrolPumpNozzleCalibration)) {
            return false;
        }
        PetrolPumpNozzleCalibration other = (PetrolPumpNozzleCalibration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.PetrolPumpNozzleCalibration[id=" + id + "]";
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
    public int compareTo(Object o) {
        return Collator.getInstance().compare(o.toString(), this.toString());
    }

    public static PetrolPumpNozzleCalibration findLastPetrolPumpNozzleCalibrationByJobNumber(EntityManager em, String jobNumber) {
        List<PetrolPumpNozzleCalibration> foundPetrolPumpNozzleCalibrations;

        String searchQuery =
                "SELECT PetrolPumpNozzleCalibration FROM PetrolPumpNozzleCalibration petrolPumpNozzleCalibration"
                + " JOIN petrolPumpNozzleCalibration.job job"
                + " WHERE job.jobNumber = '" + jobNumber + "'"
                + " ORDER BY petrolPumpNozzleCalibration.id DESC";

        try {
            // return only one record
            foundPetrolPumpNozzleCalibrations = em.createQuery(searchQuery, PetrolPumpNozzleCalibration.class).getResultList();
            if (foundPetrolPumpNozzleCalibrations != null) {
                // return the latest
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

        String searchQuery =
                "SELECT PetrolPumpNozzleCalibration FROM PetrolPumpNozzleCalibration petrolPumpNozzleCalibration"
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
}
