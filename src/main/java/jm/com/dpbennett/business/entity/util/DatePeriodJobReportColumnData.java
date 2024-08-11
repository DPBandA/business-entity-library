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
package jm.com.dpbennett.business.entity.util;

import java.text.Collator;
import jm.com.dpbennett.business.entity.jmts.Job;
import jm.com.dpbennett.business.entity.jmts.JobCostingAndPayment;
import jm.com.dpbennett.business.entity.fm.JobSubCategory;
import jm.com.dpbennett.business.entity.fm.Sector;


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
            super.setNoOfTestsOrCalibrations(numberOfTestsOrCalibrations.intValue());
        }
    }

    public DatePeriodJobReportColumnData(
            Sector sector,
            Long numberOfTestsOrCalibrations) {

        super.setSector(sector);
        if (numberOfTestsOrCalibrations != null) {
            super.setNoOfTestsOrCalibrations(numberOfTestsOrCalibrations.intValue());
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
