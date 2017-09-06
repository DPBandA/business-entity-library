/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.util.Date;

/**
 *
 * @author dbennett
 */
public interface Test {

    public String getName();

    public void setName(String name);

    public String getType();

    public void setType(String type);

    public String getCateogy();

    public void setCategory(String category);

    public Double getHourlyRate();

    public void setHourlyRate(Double hourlyRate);

    public Date getTestDate();

    public void setTestDate(Date testDate);

    public Employee getTestDoneBy();

    public void setTestDoneBy(Employee testDoneBy);

    public Date getReTestDate();

    public void setReCalibrationDate(Date reTestDate);
}
