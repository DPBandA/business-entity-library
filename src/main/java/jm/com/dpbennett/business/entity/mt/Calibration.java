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

package jm.com.dpbennett.business.entity.mt;

import java.util.Date;
import jm.com.dpbennett.business.entity.hrm.Employee;

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
