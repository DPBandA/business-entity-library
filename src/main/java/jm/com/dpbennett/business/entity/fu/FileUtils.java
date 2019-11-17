/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
