/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import jm.com.dpbennett.business.entity.DatePeriod;
import jm.com.dpbennett.business.entity.Department;
import jm.com.dpbennett.business.entity.JobReportItem;
import jm.com.dpbennett.business.entity.JobSubCategory;
import jm.com.dpbennett.business.entity.Sector;


/**
 *
 * @author dbennett
 */
public class DatePeriodJobReport {

    private String reportPeriod;
    private Department reportingDepartment;
    private DatePeriod datePeriods[];
    private HashMap<String, JobReportItem> jobReportItems;
    private HashMap<Object, List> reportColumnData;
    private Double earningJobsCompletedOnTime = 0.0;
    private Double earningJobsScheduledForCompletionInPeriod = 0.0;
    //private Double earningsFromNewClients = 0.0;

    public DatePeriodJobReport(
            Department reportingDepartment,
            List<JobSubCategory> jobSubCategories,
            List<Sector> sectors,
            List<JobReportItem> jobReportItems,
            DatePeriod datePeriods[]) {

        this.reportingDepartment = reportingDepartment;
        this.datePeriods = datePeriods;
        this.reportPeriod = BusinessEntityUtils.getMonthAndYearString(datePeriods[0].getEndDate());
        this.reportColumnData = new HashMap<Object, List>();
        this.jobReportItems = new HashMap<String, JobReportItem>();

        if (jobSubCategories != null) {
            initSubCategoriesReport(jobSubCategories);
        } else if (sectors != null) {
            initSectorsReport(sectors);
        } else if (jobReportItems != null) {
            initJobQuantitiesAndServicesReport(jobReportItems);
        }

    }

    public List<JobReportItem> getJobReportItems() {
        List<JobReportItem> items = new ArrayList(jobReportItems.values());

        Collections.sort(items);

        return items;
    }

    public Object getReportItemValue(
            JobReportItem item,
            DatePeriod datePeriod,
            List<DatePeriodJobReportColumnData> reportColumnData) {

        Double value = 0.0;


        if (item.getName().equals("Jobs received in period")) {

            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (BusinessEntityUtils.isDateWithinPeriod(
                        datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                        datePeriod.getStartDate(),
                        datePeriod.getEndDate())) {
                    value = value + 1.0;
                }
            }
            return value;
        }

        if (item.getName().equals("Earning jobs received in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (BusinessEntityUtils.isDateWithinPeriod(
                        datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                        datePeriod.getStartDate(),
                        datePeriod.getEndDate())) {
                    if (datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                        value = value + 1.0;
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Non-earning jobs received in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (BusinessEntityUtils.isDateWithinPeriod(
                        datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                        datePeriod.getStartDate(),
                        datePeriod.getEndDate())) {
                    if (!datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                        value = value + 1.0;
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Earning jobs received and completed in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (BusinessEntityUtils.isDateWithinPeriod(
                        datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                        datePeriod.getStartDate(),
                        datePeriod.getEndDate())) {
                    if (datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                        if (datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion() != null) {
                            if (BusinessEntityUtils.isDateWithinPeriod(
                                    datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion(),
                                    datePeriod.getStartDate(),
                                    datePeriod.getEndDate())) {
                                value = value + 1.0;
                            }
                        }
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Earning jobs received and unfinished in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (BusinessEntityUtils.isDateWithinPeriod(
                        datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                        datePeriod.getStartDate(),
                        datePeriod.getEndDate())) {
                    if (datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                        if (datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion() != null) {
                            if (!BusinessEntityUtils.isDateWithinPeriod(
                                    datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion(),
                                    datePeriod.getStartDate(),
                                    datePeriod.getEndDate())) {
                                value = value + 1.0;
                            }
                        } else {
                            value = value + 1.0;
                        }
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Jobs completed in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion() != null) {
                    if (BusinessEntityUtils.isDateWithinPeriod(
                            datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion(),
                            datePeriod.getStartDate(),
                            datePeriod.getEndDate())) {
                        value = value + 1.0;
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Earning jobs completed on time")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                    if (datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion() != null) {
                        if (BusinessEntityUtils.isDateWithinPeriod(
                                datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion(),
                                datePeriod.getStartDate(),
                                datePeriod.getEndDate())) {
                            if (datePeriodJobReportColumnData.getJobStatusAndTracking().getExpectedDateOfCompletion() != null) {
                                if (datePeriodJobReportColumnData.getJobStatusAndTracking().getDateOfCompletion().
                                        compareTo(datePeriodJobReportColumnData.getJobStatusAndTracking().getExpectedDateOfCompletion()) <= 0) {
                                    value = value + 1.0;
                                }
                            }
                        }
                    }
                }
            }

            // saved for use in calculating cotif
            earningJobsCompletedOnTime = value;
            return value;
        }

        if (item.getName().equals("Earning jobs scheduled for completion in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                    if (datePeriodJobReportColumnData.getJobStatusAndTracking().getExpectedDateOfCompletion() != null) {
                        if (BusinessEntityUtils.isDateWithinPeriod(
                                datePeriodJobReportColumnData.getJobStatusAndTracking().getExpectedDateOfCompletion(),
                                datePeriod.getStartDate(),
                                datePeriod.getEndDate())) {
                            value = value + 1.0;
                        }
                    }
                }
            }

            // saved for use in calculating cotif
            earningJobsScheduledForCompletionInPeriod = value;
            return value;
        }

        if (item.getName().equals("COTIF (%)")) {
            if (earningJobsScheduledForCompletionInPeriod != 0.0) {
                value = (earningJobsCompletedOnTime / earningJobsScheduledForCompletionInPeriod) * 100.0;
            } else {
                value = 0.0;
            }

            return value;
        }

        if (item.getName().equals("New clients served in period")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (datePeriodJobReportColumnData.getNewClient() != null) {
                    if (datePeriodJobReportColumnData.getNewClient()) {
                        if (BusinessEntityUtils.isDateWithinPeriod(
                                datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                                datePeriod.getStartDate(),
                                datePeriod.getEndDate())) {
                            value = value + 1.0;
                        }
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Earnings from new clients")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (datePeriodJobReportColumnData.getNewClient() != null) {
                    if (datePeriodJobReportColumnData.getNewClient()) {
                        if (BusinessEntityUtils.isDateWithinPeriod(
                                datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                                datePeriod.getStartDate(),
                                datePeriod.getEndDate())) {

                            if (datePeriodJobReportColumnData.getJobCostingAndPayment().getFinalCost() != null) {
                                value = value + datePeriodJobReportColumnData.getJobCostingAndPayment().getFinalCost();
                            }
                        }
                    }
                }
            }
            return value;
        }

        if (item.getName().equals("Samples received")) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                if (datePeriodJobReportColumnData.getJobSubCategory().getIsEarning()) {
                    if (BusinessEntityUtils.isDateWithinPeriod(
                            datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                            datePeriod.getStartDate(),
                            datePeriod.getEndDate())) {
                        value = value + datePeriodJobReportColumnData.getNumberOfSamples();
                    }
                }
            }

            return value;
        }

        if (item.getName().equals("Clients served in period")) {
            HashSet<String> clients = new HashSet<String>();

            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : reportColumnData) {
                // collect clients
                if (datePeriodJobReportColumnData.getClient() != null) {
                    // filter based on date eg. date submitted/job completed
                    if (BusinessEntityUtils.isDateWithinPeriod(
                            datePeriodJobReportColumnData.getJobStatusAndTracking().getDateSubmitted(),
                            datePeriod.getStartDate(),
                            datePeriod.getEndDate())) {
                        clients.add(datePeriodJobReportColumnData.getClient().getName());
                    }
                }
            }

            value = Double.parseDouble("" + clients.size());

            return value;
        }

        return value;
    }

    private void initSubCategoriesReport(List<JobSubCategory> jobSubCategories) {

        for (int i = 0; i < datePeriods.length; i++) {
            // add report data for each date period
            List<DatePeriodJobReportColumnData> data = new ArrayList<DatePeriodJobReportColumnData>();
            for (JobSubCategory subCategory : jobSubCategories) {
                data.add(new DatePeriodJobReportColumnData(subCategory, 0.0, 0L));
            }
            setReportColumnData(datePeriods[i].getName(), data);
        }
    }

    private void initSectorsReport(List<Sector> sectors) {

        for (int i = 0; i < datePeriods.length; i++) {
            // add report data for each date period
            List<DatePeriodJobReportColumnData> data = new ArrayList<DatePeriodJobReportColumnData>();
            for (Sector sector : sectors) {
                data.add(new DatePeriodJobReportColumnData(sector, 0L));
            }
            setReportColumnData(datePeriods[i].getName(), data);
        }
    }

    private void initJobQuantitiesAndServicesReport(List<JobReportItem> jobReportItems) {
        for (JobReportItem jobReportItem : jobReportItems) {
            this.jobReportItems.put(jobReportItem.getName(), jobReportItem);
        }
    }

    public HashMap<Object, List> getReportColumnData() {
        return reportColumnData;
    }

    public void setReportColumnData(HashMap<Object, List> reportColumnData) {
        this.reportColumnData = reportColumnData;
    }

    public Department getReportingDepartment() {
        return reportingDepartment;
    }

    public void setReportingDepartment(Department reportingDepartment) {
        this.reportingDepartment = reportingDepartment;
    }

    public DatePeriod[] getDatePeriods() {
        return datePeriods;
    }

    public void setDatePeriods(DatePeriod[] datePeriods) {
        this.datePeriods = datePeriods;
    }

    public DatePeriod getDatePeriod(int index) {
        return datePeriods[index];
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public Iterator getReportColumnDataIterator() {
        return reportColumnData.entrySet().iterator();
    }

    public List<DatePeriodJobReportColumnData> getReportColumnData(Object key) {
        if (reportColumnData.containsKey(key)) {
            return (List<DatePeriodJobReportColumnData>) reportColumnData.get(key);
        } else {
            return new ArrayList<DatePeriodJobReportColumnData>();
        }
    }

    public void setReportColumnData(Object key, List<DatePeriodJobReportColumnData> rowData) {
        reportColumnData.put(key, rowData);
//
//        if (jobReportItems != null) {
//        }
    }

    public void updateSubCategoriesReportColumnData(Object key, List<DatePeriodJobReportColumnData> rowData) {

        List<DatePeriodJobReportColumnData> currentRowData = getReportColumnData(key);
        if (currentRowData != null) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : currentRowData) {
                for (DatePeriodJobReportColumnData datePeriodJobReportColumnData1 : rowData) {
                    if (datePeriodJobReportColumnData1.getJobSubCategory().getSubCategory().
                            equals(datePeriodJobReportColumnData.getJobSubCategory().getSubCategory())) {

                        datePeriodJobReportColumnData.getJobCostingAndPayment().setFinalCost(
                                datePeriodJobReportColumnData1.getJobCostingAndPayment().getFinalCost());

                        datePeriodJobReportColumnData.setNoOfTestsOrCalibrations(
                                datePeriodJobReportColumnData1.getNoOfTestsOrCalibrations());
                    }
                }
            }
        }
    }

    public void updateSectorsReportColumnData(Object key, List<DatePeriodJobReportColumnData> rowData) {

        List<DatePeriodJobReportColumnData> currentRowData = getReportColumnData(key);
        if (currentRowData != null) {
            for (DatePeriodJobReportColumnData datePeriodJobReportColumnData : currentRowData) {
                for (DatePeriodJobReportColumnData datePeriodJobReportColumnData1 : rowData) {
                    if (datePeriodJobReportColumnData1.getSector().getName().
                            equals(datePeriodJobReportColumnData.getSector().getName())) {

                        datePeriodJobReportColumnData.setNoOfTestsOrCalibrations(
                                datePeriodJobReportColumnData1.getNoOfTestsOrCalibrations());
                    }
                }
            }
        }
    }
}
