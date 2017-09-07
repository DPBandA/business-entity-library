/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.utils;

import java.text.Collator;
import jm.com.dpbennett.business.entity.Job;
import jm.com.dpbennett.business.entity.JobCostingAndPayment;
import jm.com.dpbennett.business.entity.JobSubCategory;
import jm.com.dpbennett.business.entity.Sector;


/**
 *
 * @author dbennett
 */
public class DatePeriodJobReportColumnData extends Job implements Comparable {

    public DatePeriodJobReportColumnData(
            JobSubCategory jobSubCategory,
            Double totalFinalCost,
            Long numberOfTestsOrCalibrations) {

        super.setJobSubCategory(jobSubCategory);
        super.setJobCostingAndPayment(new JobCostingAndPayment());
        super.getJobCostingAndPayment().setFinalCost(totalFinalCost);
        if (numberOfTestsOrCalibrations != null) {
            super.setNoOfTestsOrCalibrations(new Integer(numberOfTestsOrCalibrations.intValue()));
        }
    }

    public DatePeriodJobReportColumnData(
            Sector sector,
            Long numberOfTestsOrCalibrations) {

        super.setSector(sector);
        if (numberOfTestsOrCalibrations != null) {
            super.setNoOfTestsOrCalibrations(new Integer(numberOfTestsOrCalibrations.intValue()));
        }
    }

    public DatePeriodJobReportColumnData(Job job) {
        super.setSector(job.getSector());
        super.setJobCategory(job.getJobCategory());
        super.setJobSubCategory(job.getJobSubCategory());
        super.setJobStatusAndTracking(job.getJobStatusAndTracking());
        super.setJobCostingAndPayment(job.getJobCostingAndPayment());
        super.setNewClient(job.getNewClient());
        super.setNumberOfSamples(job.getNumberOfSamples());
        super.setClient(job.getClient());
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public String toString() {
        return "DatePeriodJobReportColumnData{" + "rowData=" + this.getClass() + '}';
    }
}
