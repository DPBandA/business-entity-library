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

public class Message implements Serializable {

    public static final String SEVERITY_INFO_NAME = "INFO";
    public static final String SEVERITY_WARN_NAME = "WARN";
    public static final String SEVERITY_ERROR_NAME = "ERROR";
    public static final String SEVERITY_FATAL_NAME = "FATAL";
    private String severity;
    private String header;
    private String message;

    public Message() {
        this.header = "";
        this.message = "";
        this.severity = SEVERITY_INFO_NAME;
    }

    public Message(String header) {
        this.header = header;
        this.message = "";
        this.severity = SEVERITY_INFO_NAME;
    }

    public Message(String header, String message) {
        this.header = header;
        this.message = message;
        this.severity = SEVERITY_INFO_NAME;
    }

    public Message(String header, String message, String severity) {
        this.header = header;
        this.message = message;
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
