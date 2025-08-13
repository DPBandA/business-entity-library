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
package jm.com.dpbennett.business.entity.fu;

import java.io.*;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * This class manages a properties file.
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class PropertiesFile {

    private final String filePath;
    private final Properties props;

    /**
     * Constructs the PropertiesFile with a new file path.
     * @param filePath 
     */
    public PropertiesFile(String filePath) {
        this.filePath = filePath;
        this.props = new Properties();
    }

    /**
     * Sets the value of property.
     * @param name
     * @param value 
     */
    public void setProperty(String name, String value) {
        props.setProperty(name, value);
    }

    /**
     * Gets the value of a property.
     * @param name
     * @return 
     */
    public String getProperty(String name) {
        return props.getProperty(name);
    }
    
    /**
     * Gets a property as a double value.
     * @param name
     * @return 
     */
    public Double getDoubleProperty(String name) {
        try {
            
            return Double.valueOf(props.getProperty(name));            
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid double value");
        }
        
        return 0.0;
    }
    
    /**
     * Gets a property as a long value.
     * 
     * @param name
     * @return 
     */
    public Long getLongProperty(String name) {
        try {
            
            return Long.valueOf(props.getProperty(name));            
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid long value");
        }
        
        return 0L;
    }
    
    /**
     * Get a property as boolean value.
     * 
     * @param name
     * @return 
     */
    public Boolean getBooleanProperty(String name) {
        try {
            
            return Boolean.valueOf(props.getProperty(name));            
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid boolean value");
        }
        
        return false;
    }

    /**
     * Loads the properties from the properties file.
     * 
     * @return 
     */
    public final boolean load() {

        try {

            try (FileInputStream fis
//                    = new FileInputStream(FileUtils.getAbsoluteFilePath(filePath, getClass()))) {
                    = new FileInputStream(filePath)) {
                props.load(fis);
            }

            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return false;
    }

    /**
     * Saves the properties file.
     * @return 
     */
    public boolean save() {

        try {
       
//            props.store(new FileOutputStream(FileUtils.getAbsoluteFilePath(filePath, getClass())),
//                    "Properties File");
            props.store(new FileOutputStream(filePath), "Properties File");
            
            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return false;

    }
    
    /**
     * Saves the properties file with the given header.
     * @param header
     * @return 
     */
    public boolean save(String header) {

        try {

//            props.store(new FileOutputStream(FileUtils.getAbsoluteFilePath(filePath, getClass())),
//                    header);
             props.store(new FileOutputStream(filePath), header);  
            
            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return false;

    }

}
