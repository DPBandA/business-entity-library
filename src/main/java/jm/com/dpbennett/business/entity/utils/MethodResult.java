/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.utils;

/**
 *
 * @author desbenn
 */
public class MethodResult {
    private final boolean success;
    private final String message;

    public MethodResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MethodResult() {
        this.success = true;
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }    
    
  }
