/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.util.Date;

/**
 *
 * 
 * @author dbennett 
 */
public interface Calibration {

    public String getName();

    public void setName(String name);

    public String getType();

    public void setType(String type);

    public Double getHourlyRate();

    public void setHourlyRate(Double hourlyRate);

    public Date getCalibrationDate();

    public void setCalibrationDate(Date calibrationDate);

    public Employee getCalibrationDoneBy();

    public void setCalibrationDoneBy(Employee calibrationDoneBy);

    public Date getReCalibrationDate();

    public void setReCalibrationDate(Date reCalibrationDate);
}
