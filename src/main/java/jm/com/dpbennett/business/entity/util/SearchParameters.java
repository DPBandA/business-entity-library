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
package jm.com.dpbennett.business.entity.util;

import java.io.Serializable;
import java.util.ArrayList;
import jm.com.dpbennett.business.entity.rm.DatePeriod;

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
