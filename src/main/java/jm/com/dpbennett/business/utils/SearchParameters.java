/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.utils;

import java.io.Serializable;
import java.util.ArrayList;
import jm.com.dpbennett.business.entity.DatePeriod;

/**
 *
 * @author dbennett
 */
public class SearchParameters implements Serializable {

    private String name;
     private String jobType;
    private ArrayList jobTypes;
    private Boolean showJobTypes;
    private ArrayList searchTypes;
    private Boolean showSearchTypes;
    private String dateField;
    private Boolean showDateField;
    private ArrayList searchDateFields;
    private String searchType;
    private DatePeriod datePeriod;
    private String searchText;

    public SearchParameters (
            String name,
            ArrayList jobTypes,
            Boolean showJobTypes,
            ArrayList searchTypes,
            Boolean showSearchTypes,
            String dateField,
            Boolean showDateField,
            ArrayList searchDateFields,
            String searchType,
            DatePeriod datePeriod,
            String searchText) {

        this.name = name;
        this.jobTypes = jobTypes;
        this.showJobTypes = showJobTypes;
        this.searchTypes = searchTypes;
        this.showSearchTypes = showSearchTypes;
        this.dateField = dateField;
        this.showDateField = showDateField;
         this.searchDateFields = searchDateFields;
        this.searchType = searchType;
        this.datePeriod = datePeriod;
        this.searchText = searchText;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Boolean getShowJobTypes() {
        return showJobTypes;
    }

    public void setShowJobTypes(Boolean showJobTypes) {
        this.showJobTypes = showJobTypes;
    }

    public Boolean getShowSearchTypes() {
        return showSearchTypes;
    }

    public void setShowSearchTypes(Boolean showSearchTypes) {
        this.showSearchTypes = showSearchTypes;
    }

    public Boolean getShowDateField() {
        return showDateField;
    }

    public void setShowDateField(Boolean showDateField) {
        this.showDateField = showDateField;
    }
    
    public ArrayList getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(ArrayList jobTypes) {
        this.jobTypes = jobTypes;
    }

    public ArrayList getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(ArrayList searchTypes) {
        this.searchTypes = searchTypes;
    }

    public ArrayList getSearchDateFields() {
        return searchDateFields;
    }

    public void setSearchDateFields(ArrayList searchDateFields) {
        this.searchDateFields = searchDateFields;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public DatePeriod getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(DatePeriod datePeriod) {
        this.datePeriod = datePeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchText() {
        if (searchText == null) {
            searchText = "";
        }
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
