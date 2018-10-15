/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2018  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.fileutils.PropertiesFile;
import jm.com.dpbennett.business.entity.utils.Security;

/**
 *
 * @author Desmond Bennett
 */
public class TestEntity {

    static EntityManagerFactory EMF;
    static Connection connection;

    public static boolean initMSAccessDatabase() {
        // Get database file and check if it exists before trying to connect to it
        File dbFile = new File("C:\\Projects\\JobManagementAndTracking\\Data\\BureauBase_V.3_be.mdb");
        if (!dbFile.exists()) {
            return false;
        }
        //File dbFile = new File("C:\\Projects\\JobManagementAndTracking\\Data\\BureauBase_EE_V.2_be - 2011-03-18.mdb");
        String databaseFile = "C:\\Projects\\JobManagementAndTracking\\Data\\BureauBase_V.3_be.mdb";
        String username = "admin";
        String password = "";
        String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};Dbq=" + databaseFile
                + ";Uid=" + username
                + ";Pwd=" + password + ";";
        try {
            Class.forName("sun.jdbc.odb.JdbcOdbcDriver");
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            connection = null;
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static boolean setupDatabaseConnection(String PU) {
        try {
            EMF = Persistence.createEntityManagerFactory(PU);
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    System.out.println("Connected!");
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex);
            return false;
        }

        return true;
    }

    public static boolean setupDatabaseConnection(String PU, HashMap prop) {
        try {
            EMF = Persistence.createEntityManagerFactory(PU, prop);
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    System.out.println("Connected!");
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

//        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:mysql://DESKTOP50.BOS.local:3306/activiti")
//                .setJdbcUsername("root")
//                .setJdbcPassword("")
//                .setJdbcDriver("com.mysql.jdbc.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
//
//        ProcessEngine processEngine = cfg.buildProcessEngine();
//        String pName = processEngine.getName();
//        String ver = ProcessEngine.VERSION;
//        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
        PropertiesFile propertiesFile = new PropertiesFile("LabelPrint.properties");
        HashMap prop = new HashMap();

        prop.put("javax.persistence.jdbc.user",
                propertiesFile.getProperty("ConnectionUserName"));
        prop.put("javax.persistence.jdbc.password",
               Security.decrypt(propertiesFile.getProperty("ConnectionPassword")));
        prop.put("javax.persistence.jdbc.url",
                propertiesFile.getProperty("ConnectionURL"));
        prop.put("javax.persistence.jdbc.driver",
                propertiesFile.getProperty("ConnectionDriverName"));
        
        if (setupDatabaseConnection("PU", prop)) {

            EntityManager em = EMF.createEntityManager();
            em.getTransaction().begin();
//            Business business = new Business("NCRA2");
//            business.getDepartments().add(new Department("Test Dept2."));
//            BusinessEntityUtils.saveBusinessEntity(em, business);
            em.getTransaction().commit();
        }
    }
}
