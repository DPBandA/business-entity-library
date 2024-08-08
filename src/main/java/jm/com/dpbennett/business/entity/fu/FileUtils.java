/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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

/**
 *
 * @author desbenn
 */
public class FileUtils {

    public static String getAbsoluteFilePath(String fileName, Class runtimeClass) {
        
        String absolute = runtimeClass.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        absolute = absolute.substring(0, absolute.length() - 1);
        absolute = absolute.substring(0, absolute.lastIndexOf("/") + 1);
        String absoluteFilePath = absolute + fileName;
        String os = System.getProperty("os.name");
        
        if (os.contains("Windows")) {
            absoluteFilePath = absoluteFilePath.replace("/", "\\\\");
            if (absoluteFilePath.contains("file:\\\\")) {
                absoluteFilePath = absoluteFilePath.replace("file:\\\\", "");
            }
        } else if (absoluteFilePath.contains("file:")) {
            absoluteFilePath = absoluteFilePath.replace("file:", "");
        }

        return absoluteFilePath;
    }
}
