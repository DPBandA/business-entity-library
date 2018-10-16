/*
LabelPrint - A general purpose energy label printing application 
Copyright (C) 2018  D P Bennett & Associates Limited

This program is free software: you can  redistribute it and/or modify
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
package jm.com.dpbennett.business.entity.fileutils;

import java.io.*;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * This class manages a properties file.
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class PropertiesFile {

    private String filePath;
    private Properties props;

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
     * Reads the properties from the properties file.
     * @return 
     */
    public final boolean read() {

        try {

            try (FileInputStream fis
                    = new FileInputStream(FileUtils.getAbsoluteFilePath(filePath, getClass()))) {
                props.load(fis);
            }

            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return false;
    }

    /**
     * Writes the properties to the properties file.
     * @return 
     */
    public boolean write() {

        try {

            props.store(new FileOutputStream(FileUtils.getAbsoluteFilePath(filePath, getClass())),
                    "Properties File");
            
            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return false;

    }
    
    /**
     * Writes the properties to the properties file with the given header.
     * @param header
     * @return 
     */
    public boolean write(String header) {

        try {

            props.store(new FileOutputStream(FileUtils.getAbsoluteFilePath(filePath, getClass())),
                    header);
            
            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return false;

    }

}
