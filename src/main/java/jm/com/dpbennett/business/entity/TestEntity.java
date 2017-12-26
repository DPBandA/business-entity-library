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

package jm.com.dpbennett.business.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Desmond
 */
public class TestEntity {

    static EntityManagerFactory EMF;
    //static EntityManager EM;
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
        } catch (Exception e) {
            connection = null;
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static Client getClient(EntityManager em, String clientName) {

        if (clientName != null) {
            String newClientName = clientName.replaceAll("'", "''");

            try {
                List<Client> clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE c.name "
                        + "= '" + newClientName + "'", Client.class).getResultList();

                if (clients.size() > 0) {
                    return clients.get(0);
                }
                return null;
            } catch (Exception e) {

                return null;
            }
        } else {
            return null;
        }
    }

    public static ServiceContract createServiceContract() {
        ServiceContract serviceContract = new ServiceContract();
        // init service contract
        serviceContract.setIntendedMarketLocal(true);
        serviceContract.setAutoAddSampleInformation(true);
        serviceContract.setAdditionalServiceUrgent(false);
        serviceContract.setAdditionalServiceFaxResults(false);
        serviceContract.setAdditionalServiceTelephonePresumptiveResults(false);
        serviceContract.setAdditionalServiceSendMoreContractForms(false);
        serviceContract.setAdditionalServiceOther(false);
        serviceContract.setAdditionalServiceOtherText("");
        serviceContract.setIntendedMarketLocal(false);
        serviceContract.setIntendedMarketCaricom(false);
        serviceContract.setIntendedMarketUK(false);
        serviceContract.setIntendedMarketUSA(false);
        serviceContract.setIntendedMarketCanada(false);
        serviceContract.setIntendedMarketOther(false);
        serviceContract.setIntendedMarketOtherText("");
        serviceContract.setServiceRequestedTesting(false);
        serviceContract.setServiceRequestedCalibration(false);
        serviceContract.setServiceRequestedLabelEvaluation(false);
        serviceContract.setServiceRequestedInspection(false);
        serviceContract.setServiceRequestedConsultancy(false);
        serviceContract.setServiceRequestedTraining(false);
        serviceContract.setServiceRequestedOther(false);
        serviceContract.setServiceRequestedOtherText("");
        serviceContract.setSpecialInstructions("");

        return serviceContract;
    }

    public static Job createNewJob(Boolean autoGenerateJobNumber) {
        Job job = new Job();
        // optional objects for job entry
        // client
        job.setClient(new Client());
        job.getClient().setId(0L);
        // sub-contract dept.
        job.setSubContractedDepartment(new Department());
        job.getSubContractedDepartment().setId(4L); // tk get default id from options
        // classification
        job.setClassification(new Classification());
        job.getClassification().setId(4L); // to be set as option
        // sector
        job.setSector(new Sector());
        job.getSector().setId(4L); // to be set as option
        // contract dept.
        job.setJobCategory(new JobCategory());
        job.getJobCategory().setId(3L); // to be set as option
        // sub-contract dept.
        job.setJobSubCategory(new JobSubCategory());
        job.getJobSubCategory().setId(3L); // to be set as option
        job.setServiceContract(createServiceContract());
        // set default values
        job.setAutoGenerateJobNumber(autoGenerateJobNumber);
        job.setIsEarningJob(Boolean.TRUE);
        job.setYearReceived(Calendar.getInstance().get(Calendar.YEAR));
        job.setBusinessOffice(new BusinessOffice());
        job.getBusinessOffice().setId(1L); // tk to be put as option

        job.setDepartment(new Department());
        job.getDepartment().setId(2L); // tk to be put as option
        job.setAssignedTo(new Employee());
        job.getAssignedTo().setId(0L); // tk to be put as option

        job.setJobCostingAndPayment(new JobCostingAndPayment());

        job.setJobStatusAndTracking(new JobStatusAndTracking());
        job.getJobStatusAndTracking().setDateSubmitted(new Date());

        job.setJobNumber("?");

        job.setNumberOfSamples(0L);

        return job;
    }

    private static Long getNextSequenceNumber(Integer year, EntityManager em) {
        Long last;

        JobSequenceNumber jobSequenceNumber = new JobSequenceNumber();

        try {
            last = em.createNamedQuery("getLastJobSequenceNumber",
                    Long.class).setParameter("yearReceived", year).getSingleResult();
        } catch (Exception e) {
            last = null;
        }

        if (last == null) {
            jobSequenceNumber.setYear(year);
            jobSequenceNumber.setSequentialNumber(1L);
            em.persist(jobSequenceNumber);
        } else {
            jobSequenceNumber.setYear(year);
            jobSequenceNumber.setSequentialNumber(last + 1);
            em.persist(jobSequenceNumber);
        }

        return jobSequenceNumber.getSequentialNumber();
    }

    private static String getFourDigitString(long number) {
        String fourDigitString = "";

        if ((number >= 0L) && (number <= 9L)) {
            fourDigitString = "000" + number;
        }
        if ((number >= 10L) && (number <= 99L)) {
            fourDigitString = "00" + number;
        }
        if ((number >= 100L) && (number <= 999L)) {
            fourDigitString = "0" + number;
        }
        if (number >= 1000L) {
            fourDigitString = "" + number;
        }
        return fourDigitString;
    }

//    public static Job saveJob(Job job) {
//        EntityManager em = EMF.createEntityManager();
//        Job currentJob;
//        Client currentClient;
//        Client newClient;
//        String clientName = job.getClient().getName();
//
//        //em.getTransaction().begin();
//
//        try {
//            if (job.getId() != null) {
//                currentJob = em.find(Job.class, job.getId());
//            } else {
//                currentJob = job;
//            }
//            // set date and time entered
//            if (currentJob.getJobStatusAndTracking().getDateAndTimeEntered() == null) {
//                job.getJobStatusAndTracking().setDateAndTimeEntered(new Date());
//            }
//            job.getJobStatusAndTracking().setDateStatusEdited(new Date());
//            // modify job number with sequence number if required
//            if (currentJob.getJobSequenceNumber() == null) {
//                job.setJobSequenceNumber(getNextSequenceNumber(currentJob.getYearReceived(), em));
//                //job.setJobNumber(getNewJobNumber(currentJob));
//                job.setJobNumber(job.getJobNumber().replaceFirst("[?]", getFourDigitString(job.getJobSequenceNumber())));
//            }
//            // save new client if required
//            currentClient = getClient(clientName);
//            newClient = new Client();
//            if (currentClient == null) { // new client?
//                // init new client
//                newClient.setName(clientName);
//                newClient.setDateFirstReceived(new Date());
//                newClient.setDateLastAccessed(new Date());
//                if (job.getId() == null) {
//                    em.persist(newClient);
//                    job.setClient(newClient);
//                } else {
//                    job.setClient(newClient);
//                }
//            } else {
//                job.getClient().setId(currentClient.getId());
//            }
//            // save new job status and tracking
//            if ((job.getJobStatusAndTracking().getId() == null)
//                    && (job.getId() == null)) {
//                em.persist(job.getJobStatusAndTracking());
//            } else if (job.getJobStatusAndTracking().getId() != null) {
//                em.merge(job.getJobStatusAndTracking());
//            }
//
//            // Save job costing and payment
//            // save/update cash payments
//            if (job.getJobCostingAndPayment().getCashPayments() != null) {
//                for (CashPayment cashPayment : job.getJobCostingAndPayment().getCashPayments()) {
//                    if (cashPayment.getId() != null) {
//                        em.merge(cashPayment);
//                    } else { // save this new payment if this is a new job
//                        if (job.getId() == null) {
//                            em.persist(cashPayment);
//                        }
//                    }
//                }
//            }
//            // save/update cost components
//            if (job.getJobCostingAndPayment().getCostComponents() != null) {
//                for (CostComponent costComponent : job.getJobCostingAndPayment().getCostComponents()) {
//                    if (costComponent.getId() != null) {
//                        em.merge(costComponent);
//                    } else { // save this new payment if this is a new job
//                        if (job.getId() == null) {
//                            em.persist(costComponent);
//                        }
//                    }
//                }
//            }
//            // now save job costing
//            if ((job.getJobCostingAndPayment().getId() == null)
//                    && (job.getId() == null)) {
//                em.persist(job.getJobCostingAndPayment());
//            } else if (job.getJobCostingAndPayment().getId() != null) {
//                em.merge(job.getJobCostingAndPayment());
//            }
//
//
//            // save new service conract if required
//            if ((job.getServiceContract().getId() == null)
//                    && (job.getId() == null)) {
//                // save new service contract
//                em.persist(job.getServiceContract());
//            } else if (job.getServiceContract().getId() != null) {
//                em.merge(job.getServiceContract());
//            }
//            // save/update job samples
//            if (job.getJobSamples() != null) {
//                for (JobSample jobSample : job.getJobSamples()) {
//                    if (jobSample.getId() != null) {
//                        em.merge(jobSample);
//                    } else { // save this new sample if this is a new job
//                        if (job.getId() == null) {
//                            em.persist(jobSample);
//                        }
//                    }
//
//                }
//            }
//
//            // save job
////            if (job.getId() != null) {
////                em.merge(job);
////            } else {
////                em.persist(job);
////            }
//
//            //em.getTransaction().commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return job;
//    }
    public static Department getDepartmentByName(EntityManager em, String departmentName) {

        if (departmentName == null) {
            return null;
        }

        String newDepartmentName = departmentName.replaceAll("'", "''");

        try {
            List<Department> departments = em.createQuery("SELECT d FROM Department d "
                    + "WHERE d.name "
                    + "= '" + newDepartmentName + "'", Department.class).getResultList();
            if (departments.size() > 0) {
                return departments.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public static Department getDepartmentBySubGroupCode(EntityManager em, String subGroupCode) {
        try {
            Query query = em.createNamedQuery("findBySubGroupCode");
            query.setParameter("subGroupCode", subGroupCode);
            return (Department) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public static Employee getEmployeeByName(EntityManager em, String firstName, String lastName) {

        String newFirstName = firstName.replaceAll("'", "''");
        String newLastName = lastName.replaceAll("'", "''");
        try {
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e "
                    + "WHERE e.firstName "
                    + "= '" + newFirstName + "' AND e.lastName = '" + newLastName + "'",
                    Employee.class).getResultList();
            if (employees.size() > 0) {
                return employees.get(0);
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    public static boolean setupDatabaseConnections() {
        try {
            EMF = Persistence.createEntityManagerFactory("JMTSPU");
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    System.out.println("Connected to JMTS!");
                }
                if (initMSAccessDatabase()) {
                    System.out.println("Connected to Access DB!");
                } else {
                    System.out.println("NOT Connected Access DB!");
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error getting records from MS Access.: " + ex);
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

    public static String getDateString(Calendar c, String delim, String format, String sep) {
        // date delimiter
        if (delim == null) {
            delim = "'";
        }
        // date format, YMD, MDY etc
        if (format == null) {
            format = "YMD";
        }
        // separator of date fields
        if (sep == null) {
            sep = "-";
        }

        if (c != null) {
            String str;
            String year = getFourDigitString(c.get(Calendar.YEAR));
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            if (format.equals("YMD")) {
                str = delim + year + sep + month + sep + day + delim;
            } else if (format.equals("MDY")) {
                str = delim + month + sep + day + sep + year + delim;
            } else if (format.equals("DMY")) {
                str = delim + day + sep + month + sep + year + delim;
            } else {
                str = delim + year + sep + month + sep + day + delim;
            }
            return str;
        }

        return null;
    }

    public static String getDateString(Date d, String delim, String format, String sep) {
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return getDateString(c, delim, format, sep);
        } else {
            return "";
        }
    }

    public static List<Job> findJobsByDateSearchField(
            String dateSearchField,
            String searchText,
            Date startDate,
            Date endDate) {

        EntityManager em = EMF.createEntityManager();
        List<Job> foundJobs;

        String searchQuery
                = "SELECT job FROM Job job"
                + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                + " JOIN job.department department"
                + " JOIN job.subContractedDepartment subContractedDepartment"
                + " JOIN job.classification classification"
                + " JOIN job.sector sector"
                + " JOIN job.client client"
                + " JOIN job.jobCategory jobCategory"
                + " JOIN job.jobSubCategory jobSubCategory"
                + " JOIN job.assignedTo assignedTo"
                + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                + " WHERE (jobStatusAndTracking." + dateSearchField + " >= " + getDateString(startDate, "'", "YMD", "-")
                + " AND jobStatusAndTracking." + dateSearchField + " <= " + getDateString(endDate, "'", "YMD", "-") + ")"
                + " AND ("
                + " UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.reportNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(client.clientName) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobCostingAndPayment.invoiceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobCostingAndPayment.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " )"
                + " ORDER BY job.jobSequenceNumber DESC";

        try {
            System.out.println(searchQuery);
            foundJobs = em.createQuery(searchQuery, Job.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundJobs;
    }

    public static void importEmpoyees() {
        EntityManager em = EMF.createEntityManager();

        int numEmployees = 0;

        if (setupDatabaseConnections()) {
            // import
            try {
                em.getTransaction().begin();
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM tblEmployee";
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    ++numEmployees;
                    Employee employee = new Employee();
                    Department department = getDepartmentByName(em, rs.getString("DepartmentName").replaceAll("\"", "''"));
                    if (department != null) {
                        employee.setDepartment(department);
                    }
                    employee.setUsername(rs.getString("BSJUsername").replaceAll("\"", "''"));
                    employee.setFirstName(rs.getString("FirstName").replaceAll("\"", "''"));
                    employee.setLastName(rs.getString("LastName").replaceAll("\"", "''"));
                    System.out.println(saveBusinessEntity(em, employee));
                }
                em.getTransaction().commit();
                System.out.println("# of employees: " + numEmployees);
            } catch (SQLException ex) {
                System.out.println("Error getting records from MS Access.: " + ex);
            }
        }
    }

    public static BusinessOffice getBusinessOfficeByName(EntityManager em, String name) {

        String newName = name.replaceAll("'", "''");

        try {
            List<BusinessOffice> offices = em.createQuery("SELECT b FROM BusinessOffice b "
                    + "WHERE b.businessOfficeName "
                    + "= '" + newName + "'", BusinessOffice.class).getResultList();
            if (offices.size() > 0) {
                return offices.get(0);
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

//    public static void importJobs_org() {
//        int numJobs = 0;
//        int numJobsImported = 0;
//
//        if (setupDatabaseConnections()) {
//            // client importation
//            // get all clients from the Access database
//            try {
//                Statement statement = connection.createStatement();
//                String query = "SELECT * FROM tblJobs ORDER BY JobNumber;";
//                ResultSet rs = statement.executeQuery(query);
//                if (rs.next()) { // tk change to while to import all jobs
//                    ++numJobs;
//                    System.out.println("Importing..." + rs.getInt("ID"));
//                    Job job = new Job();//createNewJob(Boolean.TRUE);
//                    // business office
//                    BusinessOffice office = getBusinessOfficeByName("Head Office");
//                    job.setBusinessOffice(office);
//                    // job number
//                    String jobNumber = rs.getString("JobNumber");
//                    if (jobNumber != null) {
//                        job.setJobNumber(jobNumber);
//                    } else {
//                        job.setJobNumber("");
//                    }
//                    // Client
//                    String clientName = rs.getString("Client");
//                    Client client = getClient(clientName);
//                    if (client == null) {
//                        client = getClient("-- select or enter a client --");
//                        job.setClient(client);
//                    }
//                    job.setJobCostingAndPayment(new JobCostingAndPayment());
//                    job.setJobStatusAndTracking(new JobStatusAndTracking());
//                    job.setServiceContract(createServiceContract());
//                    job.setAutoGenerateJobNumber(Boolean.TRUE);
//                    Department department = getDepartmentByName(rs.getString("Department"));
//                    if (department != null) {
//                        job.setDepartment(department);
//                    }
//                    // sequence num
//                    job.setJobSequenceNumber(new Long(rs.getInt("JobSequenceNumber")));
//                    // year received
//                    job.setYearReceived(rs.getInt("YearReceived"));
//                    // department
//                    // subcont dept
//                    Department subConDept = getDepartmentBySubGroupCode(rs.getInt("SubContractedDepartmentCode"));
//                    if (subConDept != null) {
//                        job.setSubContractedDepartment(subConDept);
//                    }
//                    // job entered by
//                    String jobEnteredBy = rs.getString("JobEnteredBy");
//                    job.getJobStatusAndTracking().setJobEnteredBy(jobEnteredBy);
//                    // AssignedTo
//                    String assignedTo = rs.getString("AssignedTo");
//                    // ...with lastname first
//                    if (assignedTo != null) {
//                        String names[] = assignedTo.split(",");
//                        if (names.length > 1) {
//                            Employee e = getEmployeeByName(names[1].trim(), names[0].trim());
//                            if (e != null) {
//                                job.setAssignedTo(e);
//                            }
//                        } else { // ...with lastname last
//                            String names2[] = assignedTo.split(",");
//                            if (names.length == 3) {
//                                Employee e = getEmployeeByName(names2[0].trim(), names2[1].trim() + " " + names2[2].trim());
//                                if (e != null) {
//                                    job.setAssignedTo(e);
//                                }
//                            } else if (names.length == 2) {
//                                Employee e = getEmployeeByName(names2[0].trim(), names2[1].trim());
//                                if (e != null) {
//                                    job.setAssignedTo(e);
//                                }
//                            }
//                        }
//                        if (job.getAssignedTo() == null) {  // assign to teamleader
//                            Employee e = getEmployeeByName("Garfield", "Morgan");
//                            job.setAssignedTo(e);
//                        }
//                    }
//                    // Category
//                    JobCategory cat = getJobCategoryByName(rs.getString("Category"));
//                    job.setJobCategory(cat);
//                    // SubCategory
//                    JobSubCategory subcat = getJobSubCategoryByName(rs.getString("SubCategory"));
//                    job.setJobSubCategory(subcat);
//                    // Comment
//                    job.setComment(rs.getString("Comment"));
//                    // StatusNote
//                    job.getJobStatusAndTracking().setStatusNote(rs.getString("StatusNote"));
//                    // NumberOfSamples
//                    job.setNumberOfSamples(new Long(rs.getInt("NumberOfSamples")));
//                    // DateAndTimeEntered
//                    job.getJobStatusAndTracking().setDateAndTimeEntered(rs.getDate("DateAndTimeEntered"));
//                    // DateSubmitted
//                    job.getJobStatusAndTracking().setDateSubmitted(rs.getDate("DateSubmitted"));
//                    // ExpectedDateOfCompletion
//                    job.getJobStatusAndTracking().setExpectedDateOfCompletion(rs.getDate("ExpectedDateOfCompletion"));
//                    // DateOfLastPayment
//                    job.getJobCostingAndPayment().setDateOfLastPayment(rs.getDate("DateOfLastPayment"));
//                    // DateOfCompletion
//                    job.getJobStatusAndTracking().setDateOfCompletion(rs.getDate("DateOfCompletion"));
//                    // DateStatusEdited
//                    job.getJobStatusAndTracking().setDateStatusEdited(rs.getDate("DateStatusEdited"));
//                    // EstimatedTurnAroundTimeInDays
//                    job.setEstimatedTurnAroundTimeInDays(rs.getInt("EstimatedTurnAroundTimeInDays"));
//                    // EstimatedCost
//                    job.getJobCostingAndPayment().setEstimatedCost(rs.getDouble("EstimatedCost"));
//                    // EstimatedCostDoneBy
//                    job.getJobCostingAndPayment().setEstimatedCostDoneBy(rs.getString("EstimatedCostDoneBy"));
//                    // FinalCost
//                    job.getJobCostingAndPayment().setFinalCost(rs.getDouble("FinalCost"));
//                    // FinalCostDoneBy
//                    job.getJobCostingAndPayment().setFinalCostDoneBy(rs.getString("FinalCostDoneBy"));
//                    // PaymentReceivedToDate
//                    job.getJobCostingAndPayment().setPaymentReceivedToDate(rs.getDouble("PaymentReceivedToDate"));
//                    // Deposit
//                    job.getJobCostingAndPayment().setDeposit(rs.getDouble("Deposit"));
//                    // NewClient
//                    job.setNewClient(rs.getBoolean("NewClient"));
//                    // Locked
//                    job.setLocked(rs.getBoolean("Locked"));
//                    // DocumentCollected
//                    job.getJobStatusAndTracking().setDocumentCollected(rs.getBoolean("DocumentCollected"));
//                    // Completed
//                    job.getJobStatusAndTracking().setCompleted(rs.getBoolean("Completed"));
//                    // NoOfTestsOrCalibrations
//                    job.setNoOfTestsOrCalibrations(rs.getInt("NoOfTestsOrCalibrations"));
//                    // IsEarningJob
//                    job.setIsEarningJob(rs.getBoolean("IsEarningJob"));
//                    //saveJob(job);
//                    // save tracking and status
//                    // save new job status and tracking
//                    EntityManager em = EMF.createEntityManager();
//                    em.getTransaction().begin();
//                    em.persist(job.getServiceContract());
//                    em.persist(job.getJobCostingAndPayment());
//                    em.persist(job.getJobStatusAndTracking());
//                    em.persist(job);
//                    em.getTransaction().commit();
//                    //saveBusinessEntity(job);
//                    ++numJobsImported;
//                    //break; // tk
//                    //}
//                }
//                System.out.println("Jobs found: " + numJobs);
//                System.out.println("Jobs found imported: " + numJobsImported);
//            } catch (SQLException ex) {
//                System.out.println("Error getting records from MS Access.: " + ex);
//            }
//        }
//    }
//    public static void importJobs() {
//        int numJobs = 0;
//        int numJobsImported = 0;
//
//        if (setupDatabaseConnections()) {
//            // client importation
//            // get all clients from the Access database
//            try {
//                EntityManager em = EMF.createEntityManager();
//                Statement statement = connection.createStatement();
//                String query = "SELECT * FROM tblJobs ORDER BY JobNumber;";
//                ResultSet rs = statement.executeQuery(query);
//                while (rs.next()) { // tk change to while to import all jobs
//                    ++numJobs;
//                    System.out.println("Importing..." + rs.getInt("ID"));
//                    Job job = new Job();//createNewJob(Boolean.TRUE);
//                    // business office
//                    BusinessOffice office = getBusinessOfficeByName("Head Office");
//                    job.setBusinessOffice(office);
//                    // job number
//                    String jobNumber = rs.getString("JobNumber");
//                    if (jobNumber != null) {
//                        job.setJobNumber(jobNumber);
//                    } else {
//                        job.setJobNumber("?");
//                    }
//                    // Client
//                    String clientName = rs.getString("Client");
//                    Client client = getClient(clientName);
//                    if (client == null) {
//                        // create new client if possible
//                        if ((clientName != null) && (!clientName.equals(""))) {
//                            client = new Client();
//                            client.setName(clientName);
//                            client.setDateFirstReceived(new Date());
//                            client.setDateLastAccessed(new Date());
//                            saveBusinessEntity(client);
//                        } else {
//                            client = getClient("-- select or enter a client --");
//                        }
//                        job.setClient(client);
//                    } else {
//                        job.setClient(client);
//                    }
//                    // job tracking
//                    job.setJobStatusAndTracking(new JobStatusAndTracking());
//                    // DateSubmitted
//                    job.getJobStatusAndTracking().setDateSubmitted(rs.getDate("DateSubmitted"));
//                    // department
////                    Department department = getDepartmentByName(rs.getString("Department"));
////                    if (department != null) {
////                        job.setDepartment(department);
////                    }
////                    else {
//                    Department department = getDepartmentByName("Electrical/Electronic Engineering");
//                    job.setDepartment(department);
////                    }
//                    // subcontracted department
//                    Department subConDept = getDepartmentBySubGroupCode(rs.getInt("SubContractedDepartmentCode"));
//                    if (subConDept != null) {
//                        job.setSubContractedDepartment(subConDept);
//                    } else {
//                        //job.setSubContractedDepartment(new Department());
//                        //job.getSubContractedDepartment().setId(30L); // tk get default id from options
//                        job.setSubContractedDepartment(getDepartmentByName("Inspectorate"));
//                    }
//                    // AssignedTo
//                    String assignedTo = rs.getString("AssignedTo");
//                    // ...with lastname first
//                    if (assignedTo != null) {
//                        String names[] = assignedTo.split(",");
//                        if (names.length > 1) {
//                            Employee e = getEmployeeByName(names[1].trim(), names[0].trim());
//                            if (e != null) {
//                                job.setAssignedTo(e);
//                            }
//                        } else { // ...with lastname last
//                            String names2[] = assignedTo.split(",");
//                            if (names.length == 3) {
//                                Employee e = getEmployeeByName(names2[0].trim(), names2[1].trim() + " " + names2[2].trim());
//                                if (e != null) {
//                                    job.setAssignedTo(e);
//                                }
//                            } else if (names.length == 2) {
//                                Employee e = getEmployeeByName(names2[0].trim(), names2[1].trim());
//                                if (e != null) {
//                                    job.setAssignedTo(e);
//                                }
//                            }
//                        }
//                        if (job.getAssignedTo() == null) {  // assign to teamleader
//                            Employee e = getEmployeeByName("Garfield", "Morgan");
//                            job.setAssignedTo(e);
//                        }
//                    } else {
//                        Employee e = getEmployeeByName("Garfield", "Morgan");
//                        job.setAssignedTo(e);
//                    }
//                    // Category
//                    JobCategory cat = getJobCategoryByName(rs.getString("Category"));
//                    if (cat != null) {
//                        job.setJobCategory(cat);
//                    } else {
//                        job.setJobCategory(new JobCategory());
//                        job.getJobCategory().setId(184L); // to be set as option
//                    }
//                    // SubCategory
//                    JobSubCategory subcat = getJobSubCategoryByName(rs.getString("SubCategory"));
//                    if (subcat != null) {
//                        job.setJobSubCategory(subcat);
//                    } else {
//                        job.setJobSubCategory(new JobSubCategory());
//                        job.getJobSubCategory().setId(185L); // to be set as option
//                    }
//                    // classification
//                    job.setClassification(new Classification());
//                    job.getClassification().setId(1L); // to be set as option
//                    // sector
//                    job.setSector(new Sector());
//                    job.getSector().setId(1L); // to be set as option
//                    // job costing
//                    job.setJobCostingAndPayment(new JobCostingAndPayment());
//                    // service contract
//                    job.setServiceContract(createServiceContract());
//                    job.setAutoGenerateJobNumber(Boolean.TRUE);
//                    // sequence num
//                    job.setJobSequenceNumber(new Long(rs.getInt("JobSequenceNumber")));
//                    // year received
//                    job.setYearReceived(rs.getInt("YearReceived"));
//                    // job entered by
//                    String jobEnteredBy = rs.getString("JobEnteredBy");
//                    job.getJobStatusAndTracking().setJobEnteredBy(jobEnteredBy);
//                    // Comment
//                    job.setComment(rs.getString("Comment"));
//                    // StatusNote
//                    job.getJobStatusAndTracking().setStatusNote(rs.getString("StatusNote"));
//                    // NumberOfSamples
//                    job.setNumberOfSamples(new Long(rs.getInt("NumberOfSamples")));
//                    // DateAndTimeEntered
//                    job.getJobStatusAndTracking().setDateAndTimeEntered(rs.getDate("DateAndTimeEntered"));
//                    // ExpectedDateOfCompletion
//                    job.getJobStatusAndTracking().setExpectedDateOfCompletion(rs.getDate("ExpectedDateOfCompletion"));
//                    // DateOfLastPayment
//                    job.getJobCostingAndPayment().setDateOfLastPayment(rs.getDate("DateOfLastPayment"));
//                    // DateOfCompletion
//                    job.getJobStatusAndTracking().setDateOfCompletion(rs.getDate("DateOfCompletion"));
//                    // DateStatusEdited
//                    job.getJobStatusAndTracking().setDateStatusEdited(rs.getDate("DateStatusEdited"));
//                    // EstimatedTurnAroundTimeInDays
//                    job.setEstimatedTurnAroundTimeInDays(rs.getInt("EstimatedTurnAroundTimeInDays"));
//                    // EstimatedCost
//                    job.getJobCostingAndPayment().setEstimatedCost(rs.getDouble("EstimatedCost"));
//                    // EstimatedCostDoneBy
//                    job.getJobCostingAndPayment().setEstimatedCostDoneBy(rs.getString("EstimatedCostDoneBy"));
//                    // FinalCost
//                    job.getJobCostingAndPayment().setFinalCost(rs.getDouble("FinalCost"));
//                    // FinalCostDoneBy
//                    job.getJobCostingAndPayment().setFinalCostDoneBy(rs.getString("FinalCostDoneBy"));
//                    // PaymentReceivedToDate
//                    job.getJobCostingAndPayment().setPaymentReceivedToDate(rs.getDouble("PaymentReceivedToDate"));
//                    // Deposit
//                    job.getJobCostingAndPayment().setDeposit(rs.getDouble("Deposit"));
//                    // NewClient
//                    job.setNewClient(rs.getBoolean("NewClient"));
//                    // Locked
//                    job.setLocked(rs.getBoolean("Locked"));
//                    // DocumentCollected
//                    job.getJobStatusAndTracking().setDocumentCollected(rs.getBoolean("DocumentCollected"));
//                    // Completed
//                    job.getJobStatusAndTracking().setCompleted(rs.getBoolean("Completed"));
//                    // NoOfTestsOrCalibrations
//                    job.setNoOfTestsOrCalibrations(rs.getInt("NoOfTestsOrCalibrations"));
//                    // IsEarningJob
//                    job.setIsEarningJob(rs.getBoolean("IsEarningJob"));
//                    //saveJob(job);
//                    // save tracking and status
//                    // save new job status and tracking
//                    EntityManager em = EMF.createEntityManager();
//                    em.getTransaction().begin();
//                    em.persist(job.getServiceContract());
//                    em.persist(job.getJobCostingAndPayment());
//                    em.persist(job.getJobStatusAndTracking());
//                    em.persist(job);
//                    em.getTransaction().commit();
//                    //saveBusinessEntity(job);
//                    ++numJobsImported;
//                    //break; // tk
//                    //}
//                }
//                System.out.println("Jobs found: " + numJobs);
//                System.out.println("Jobs found imported: " + numJobsImported);
//            } catch (SQLException ex) {
//                System.out.println("Error getting records from MS Access.: " + ex);
//            }
//        }
//    }
    private static Long saveBusinessEntity(EntityManager em, BusinessEntity businessEntity) {

        if (businessEntity.getId() != null) {
            em.merge(businessEntity);
        } else {
            em.persist(businessEntity);
        }

        return businessEntity.getId();
    }

//    public static void importClients() {
//        int numClients = 0;
//        int numClientsImported = 0;
//
//        if (setupDatabaseConnections()) {
//            // import
//            try {
//                Statement statement = connection.createStatement();
//                String query = "SELECT * FROM tblClients";
//                ResultSet rs = statement.executeQuery(query);
//                // import clients they don't already exist
//                while (rs.next()) {
//                    ++numClients;
//                    String clientName = rs.getString("ClientName");
//                    Client client = getClient(clientName);
//                    if (client == null) {
//                        client = new Client();
//                        client.getContacts().add(new Contact());
//                        client.getAddresses().add(new Address());
//                        client.setName(clientName);
//                        client.getContacts().get(0).setContactFirstName(rs.getString("ContactFirstName"));
//                        client.getContacts().get(0).setContactLastName(rs.getString("ContactLastName"));
//                        client.getAddresses().get(0).setAddressLine1(rs.getString("BillingAddress"));
//                        client.getContacts().get(0).setPhoneNumber(rs.getString("PhoneNumber"));
//                        client.getContacts().get(0).setExtension(rs.getString("Extension"));
//                        client.getContacts().get(0).setFaxNumber(rs.getString("FaxNumber"));
//                        client.getContacts().get(0).setEmailAddress(rs.getString("EmailAddress"));
//                        client.setNotes(rs.getString("Notes"));
//                        client.setDateFirstReceived(rs.getDate("DateFirstReceived"));
//                        System.out.println(saveBusinessEntity(client));
//                        ++numClientsImported;
//                    }
//                }
//                System.out.println("# of clients found: " + numClients);
//                System.out.println("# of clients imported: " + numClientsImported);
//            } catch (SQLException ex) {
//                System.out.println("Error getting records from MS Access.: " + ex);
//            }
//        }
//    }
//    public static void importAllJobCategories() {
//        int numJobSubCategories = 0;
//
//        if (setupDatabaseConnections()) {
//            // import
//            try {
//                Statement statement = connection.createStatement();
//                String query = "SELECT * FROM tblJobSubCategories";
//                ResultSet rs = statement.executeQuery(query);
//                // import clients they don't already exist
//                while (rs.next()) {
//                    ++numJobSubCategories;
//                    String categoryName = rs.getString("Category");
//                    JobCategory category = getJobCategoryByName(categoryName);
//                    if (category == null) {
//                        category = new JobCategory();
//                        category.setCategory(categoryName);
//                        Boolean isEarning = rs.getBoolean("IsEarning");
//                        category.setIsEarning(isEarning);
//                        // save category
//                        Long catId = saveBusinessEntity(category);
//                        // create and save sub category
//                        JobSubCategory subCat = new JobSubCategory();
//                        subCat.setCategoryId(catId);
//                        subCat.setSubCategory(rs.getString("SubCategory"));
//                        subCat.setIsEarning(isEarning);
//                        saveBusinessEntity(subCat);
//                    } else {
//                        // create and save sub category
//                        JobSubCategory subCat = new JobSubCategory();
//                        subCat.setCategoryId(category.getId());
//                        subCat.setSubCategory(rs.getString("SubCategory"));
//                        subCat.setIsEarning(category.getIsEarning());
//                        saveBusinessEntity(subCat);
//                    }
//                }
//                System.out.println("# of categories found: " + numJobSubCategories);
//            } catch (SQLException ex) {
//                System.out.println("Error getting records from MS Access.: " + ex);
//            }
//        }
//    }
//    public static JobCategory getJobCategoryByName(String name) {
//        EntityManager em = EMF.createEntityManager();
//
//        if (name == null) {
//            return null;
//        }
//
//        String newCategory = name.replaceAll("'", "''");
//
//        try {
//            List<JobCategory> jobCategories = em.createQuery("SELECT c FROM JobCategory c "
//                    + "WHERE c.category "
//                    + "= '" + newCategory + "'", JobCategory.class).getResultList();
//
//            if (jobCategories.size() > 0) {
//                return jobCategories.get(0);
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
    public static JobSubCategory getJobSubCategoryByName(String name) {
        EntityManager em = EMF.createEntityManager();

        if (name == null) {
            return null;
        }

        String newSubCategory = name.replaceAll("'", "''");

        try {
            List<JobSubCategory> jobSubCategories
                    = em.createQuery("SELECT c FROM JobSubCategory c "
                            + "WHERE c.subCategory "
                            + "= '" + newSubCategory + "'", JobSubCategory.class).getResultList();
            if (jobSubCategories.size() > 0) {
                return jobSubCategories.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

//    public static void importElectricalDepartmentData() {
//        importDepartments();
//        importEmpoyees();
//        importClients();
//        importAllJobCategories();
//        importJobs();
//    }
    public static Manufacturer getManufacturerByName(EntityManager em, String name) {

        String newName = name.replaceAll("'", "''");

        try {
            List<Manufacturer> manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                    + "WHERE m.name "
                    + "= '" + newName + "'", Manufacturer.class).getResultList();
            if (manufacturers.size() > 0) {
                return manufacturers.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Sticker getStickerByNumber(EntityManager em, String number) {

        String newNumber = number.replaceAll("'", "''");

        try {
            List<Sticker> stickers = em.createQuery("SELECT s FROM Sticker s "
                    + "WHERE s.number "
                    + "= '" + newNumber + "'", Sticker.class).getResultList();
            if (stickers.size() > 0) {
                return stickers.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Seal getSealByNumber(EntityManager em, String number) {

        String newNumber = number.replaceAll("'", "''");

        try {
            List<Seal> seals = em.createQuery("SELECT s FROM Seal s "
                    + "WHERE s.number "
                    + "= '" + newNumber + "'", Seal.class).getResultList();
            if (seals.size() > 0) {
                return seals.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Client getClientById(EntityManager em, Long Id) {
        try {
            Client client = em.find(Client.class, Id);
            return client;
        } catch (Exception e) {

            return null;
        }
    }

    public static void initClassifications(EntityManager em) {
        em.getTransaction().begin();

//        String classifications[] = {
//            "Service",
//            "Work",
//            "--"};
        String classifications[] = {
            "Lease",
            "Contract",
            "Mutual Recognition Agreement",
            "Service Agreement",
            "Letter of Agreement"};

        for (int i = 0; i < classifications.length; i = i + 1) {
            Classification classification = new Classification();
            classification.setName(classifications[i]);
            saveBusinessEntity(em, classification);
        }

        em.getTransaction().commit();
    }

    public static void initDocumentTypes(EntityManager em) {
        em.getTransaction().begin();

        String types[] = {"Agreement", "A",
            "Certificate", "Ce",
            "Circulars", "Ci",
            "Decision", "D",
            "Email", "E",
            "Form", "F",
            "Invoice", "I",
            "Legistration", "ACT",
            "Letter", "L",
            "Memorandum", "Mo",
            "Memorandum of Understanding", "MOU",
            "Notice", "No",
            "Policy", "Po",
            "Procedure", "Pr",
            "Procurement Submission", "PS",
            "Receipt", "Re",
            "Report", "R",
            "Request for Proposal", "RFP",
            "Terms of Reference", "TOR",
            "Quotation", "Qu",
            "--", "--"};
        for (int i = 0; i < types.length; i = i + 2) {
            DocumentType type = new DocumentType();
            type.setName(types[i]);
            type.setCode(types[i + 1]);
            saveBusinessEntity(em, type);
        }

        em.getTransaction().commit();
    }

    public static void initDocumentReports(EntityManager em) {
        em.getTransaction().begin();

        String reports[] = {
            "Incomming documents",
            "Completed documents"
        };
        for (int i = 0; i < reports.length; i = i + 1) {
            DocumentReport report = new DocumentReport();
            report.setName(reports[i]);
            saveBusinessEntity(em, report);
        }

        em.getTransaction().commit();
    }

    public static void initJMTSDatabase(EntityManager em) {

        em.getTransaction().begin();

        // manufacturer
        Manufacturer m = new Manufacturer();
        m.setName("--");
        saveBusinessEntity(em, m);

        // petrol pump sticker
        Sticker s = new Sticker();
        s.setNumber("--");
        saveBusinessEntity(em, s);

        // petrol pump seal
        Seal seal = new Seal();
        seal.setNumber("--");
        saveBusinessEntity(em, seal);

        // business offices
        System.out.println("Inserting offices");
        BusinessOffice headOffice = new BusinessOffice();
        headOffice.setName("Head Office");
        saveBusinessEntity(em, headOffice);
        BusinessOffice mandeville = new BusinessOffice();
        mandeville.setName("Mandeville");
        mandeville.setCode("46");
        saveBusinessEntity(em, mandeville);

        // bureau
        System.out.println("Inserting standards organization");
        StandardsOrganization bsj = new StandardsOrganization();
        bsj.setName("Bureau of Standards, Jamaica");
        bsj.getBusinessOffices().add(headOffice);
        bsj.getBusinessOffices().add(mandeville);
        saveBusinessEntity(em, bsj);

        // departments
        Department select = new Department();
        select.setName("--");
        saveBusinessEntity(em, select);

        // employees
        Employee dash = new Employee();
        dash.setFirstName("--");
        dash.setLastName("--");
        saveBusinessEntity(em, dash);

        // job manager users
        System.out.println("Inserting user(s)");
        Employee dash2 = getEmployeeByName(em, "--", "--");
        JobManagerUser dbuser = new JobManagerUser();
        dbuser.setEmployee(dash2);
        dbuser.setUsername("admin");
        saveBusinessEntity(em, dbuser);

        // add sectors, classification etc.
        Sector sector = new Sector();
        sector.setName("--");
        saveBusinessEntity(em, sector);

        JobCategory category = new JobCategory();
        category.setCategory("--");
        saveBusinessEntity(em, category);

        JobSubCategory subCategory = new JobSubCategory();
        subCategory.setSubCategory("--");
        saveBusinessEntity(em, subCategory);

        em.getTransaction().commit();

    }

    public static JobManagerUser getJMTSUserByUsername(EntityManager em, String username) {

        String newUsername = username.replaceAll("'", "''");
        try {
            List<JobManagerUser> users
                    = em.createNamedQuery("findByJobManagerUsername",
                            JobManagerUser.class).
                            setParameter("username", newUsername).getResultList();
            //System.out.println("users: " + users.size());
            if (users.size() > 0) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    /*
     public static void buildFoodFactoryDatabase(EntityManager em, String sheetName) {
     // cell indices
     final int PARISH = 0;
     final int FACTORY = 1;
     final int ADDRESS = 2;
     final int CONTACT_PERSON = 3;
     final int TELE = 4;
     final int FAX = 5;
     final int EMAIL_ADDRESS = 6;
     final int LAST_DATE_REGISTERED = 7;
     final int DUE_NEXT = 8;
     final int STATUS_1 = 9;
     final int COMMENT_ = 10;
     final int STATUS_2 = 11;
     final int PRODUCTS_MANUFACTURED = 12;
     final int BRAND = 13;
     final int ASSIGNED_INSPECTOR = 14;

     int rowCount = 0;
     int cellCount = 0;

     try {
     POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("Factory Database 2011.xls"));
     HSSFWorkbook wb = new HSSFWorkbook(fs);
     em.getTransaction().begin();
     for (int i = 0; i < wb.getNumberOfSheets(); i++) {
     Sheet sheet = wb.getSheetAt(i);
     if (sheetName != null) {
     if (sheet.getSheetName().equals(sheetName)) {
     for (Row row : sheet) {
     if (rowCount > 0) { // make sure this is not the header
     cellCount = 0;
     // create new factory
     FoodFactory factory = new FoodFactory();
     for (Cell cell : row) {
     // populate petrol company and stations with data
     switch (cellCount) {
     case PARISH:
     if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
     if (factory.getDefaultAddress() == null) {
     factory.setMainBillingAddress(new Address());
     }
     factory.getDefaultAddress().setStateOrProvince(cell.getStringCellValue());
     }
     break;
     case FACTORY: // add station only if name if valid
     if ((cell.getCellType() == Cell.CELL_TYPE_STRING)
     && (!cell.getStringCellValue().trim().equals(""))) {
     System.out.println("Importing factory: " + cell.getStringCellValue());
     factory.setName(cell.getStringCellValue());
     }
     break;
     case ADDRESS:
     if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
     factory.getDefaultAddress().setAddressLine1(cell.getStringCellValue());
     }
     break;
     //                                        case CONTACT_PERSON:
     //                                             if ((cell.getCellType() == Cell.CELL_TYPE_STRING)
     //                                                    && (!cell.getStringCellValue().trim().equals(""))) {
     //                                                // try to parse cell value and get contact name in parts
     //                                                 if (!factory.getContacts().isEmpty()) {
     //                                                     String fullName = cell.getStringCellValue();
     //                                                     factory.getContacts().get(0).setLastName();
     //                                                 }
     //                                            }
     //                                            break;
     //                                        case REJECTED_OR_NOT_WORKING:
     //                                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
     //                                                int nozzlesRejected = (int) cell.getNumericCellValue();
     //                                                if (nozzlesRejected > 0) {
     //                                                    int pumpsToUpdate = (int) (nozzlesRejected / NOZZLES_PER_PUMP);
     //                                                    int oddNozzlesToUpdate = nozzlesRejected % NOZZLES_PER_PUMP;
     //
     //                                                    // fill out all pump nozzles here
     //                                                    if (pumpsToUpdate > 0) {
     //                                                        for (int j = 0; j < pumpsToUpdate; j++) {
     //                                                            PetrolPump pertrolPump = petrolStation.getPetrolPumps().get(j);
     //                                                            for (PetrolPumpNozzle nozzle : pertrolPump.getNozzles()) {
     //                                                                nozzle.setComments("Rejected/Not working");
     //                                                            }
     //                                                        }
     //                                                        // fill out the odd number pump nozzles
     //                                                        if (oddNozzlesToUpdate > 0) {
     //                                                            System.out.println("Bad nozzles: " + oddNozzlesToUpdate);
     //                                                            PetrolPump pertrolPump = petrolStation.getPetrolPumps().get(pumpsToUpdate);
     //                                                            for (int j = 0; j < oddNozzlesToUpdate; j++) {
     //                                                                pertrolPump.getNozzles().get(j).setComments("Rejected/Not working");
     //                                                            }
     //                                                        }
     //                                                    } else if (oddNozzlesToUpdate > 0) {
     //                                                        System.out.println("Bad nozzles: " + oddNozzlesToUpdate);
     //                                                        PetrolPump pertrolPump = petrolStation.getPetrolPumps().get(0);
     //                                                        if (!pertrolPump.getNozzles().isEmpty()) {
     //                                                            for (int j = 0; j < oddNozzlesToUpdate; j++) {
     //                                                                pertrolPump.getNozzles().get(j).setComments("Rejected/Not working");
     //                                                            }
     //                                                        }
     //                                                    }
     //                                                }
     //                                            }
     //                                            break;
     //                                        case CERTIFIED_ON:
     //                                            // set the date in all pumps for now
     //                                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
     //                                                Date certifiedOn = cell.getDateCellValue();
     //                                                if (certifiedOn != null) {
     //                                                    int numberOfPumps = petrolStation.getPetrolPumps().size();
     //                                                    for (int j = 0; j < numberOfPumps; j++) {
     //                                                        PetrolPump petrolPump = petrolStation.getPetrolPumps().get(j);
     //                                                        petrolPump.getCertification().setDateIssued(certifiedOn);
     //                                                    }
     //                                                }
     //                                            }
     //                                            break;
     //                                        case EXPIRY_DATE:
     //                                            // set the date in all pumps for now
     //                                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
     //                                                Date expiryDate = cell.getDateCellValue();
     //                                                if (expiryDate != null) {
     //                                                    int numberOfPumps = petrolStation.getPetrolPumps().size();
     //                                                    for (int j = 0; j < numberOfPumps; j++) {
     //                                                        PetrolPump petrolPump = petrolStation.getPetrolPumps().get(j);
     //                                                        petrolPump.getCertification().setExpiryDate(expiryDate);
     //                                                    }
     //                                                }
     //                                            }
     //                                            break;
     //                                        case STATUS:
     //                                            break;
     //                                        case RATE:
     //                                            break;
     //                                        case FINAL_AMOUNT:
     //                                            break;
     default:
     break;
     }
     ++cellCount;
     }
     System.out.println("Saving factory: " + factory.getName());
     saveBusinessEntity(em, factory);
     }

     ++rowCount;

     }
     // save the petrol company
     em.getTransaction().commit();
     }
     } else {
     }
     }
     } catch (Exception ex) {
     System.out.println(ex);
     }
     }
     */

    public static Employee getEmployeeById(EntityManager em, Long Id) {

        return em.find(Employee.class, Id);
    }

    public static Department getDepartmentById(EntityManager em, Long Id) {

        return em.find(Department.class, Id);
    }

    public static DocumentType getDocumentTypeByName(EntityManager em, String documentTypeName) {

        String newDocumentTypeName = documentTypeName.replaceAll("'", "''");

        try {
            List<DocumentType> documentTypes = em.createQuery("SELECT d FROM DocumentType d "
                    + "WHERE d.name "
                    + "= '" + newDocumentTypeName + "'", DocumentType.class).getResultList();
            if (documentTypes.size() > 0) {
                return documentTypes.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public static Classification getClassificationByName(EntityManager em, String classificationName) {

        String newClassificationName = classificationName.replaceAll("'", "''");

        try {
            List<Classification> classifications = em.createQuery("SELECT c FROM Classification c "
                    + "WHERE c.name "
                    + "= '" + newClassificationName + "'", Classification.class).getResultList();
            if (classifications.size() > 0) {
                return classifications.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public static String getLegalDocumentNumber(LegalDocument legalDocument, String prefix) {
        String number = prefix;

        // append department code
        number = number + legalDocument.getResponsibleDepartment().getSubGroupCode();
        // append doc type
        if (legalDocument.getType() != null) {
            number = number + "_" + legalDocument.getType().getCode();
        }
        // append doc form
        if (legalDocument.getDocumentForm() != null) {
            number = number + "/" + legalDocument.getDocumentForm();
        }
        // append doc form
        if (legalDocument.getSequenceNumber() != null) {
            NumberFormat formatter = DecimalFormat.getIntegerInstance();
            formatter.setMinimumIntegerDigits(2);
            number = number + "/" + formatter.format(legalDocument.getSequenceNumber());
        } else {
            number = number + "/?";
        }

        return number;
    }

    public static LegalDocument createNewLegalDocument(EntityManager em) {
        LegalDocument legalDocument = new LegalDocument();
        Date now = new Date();
        Calendar c = Calendar.getInstance();

        legalDocument.setAutoGenerateNumber(Boolean.FALSE);

        // department, employee & business office
        legalDocument.setResponsibleOfficer(getEmployeeByName(em, "Sharon", "Hylton"));
        legalDocument.setResponsibleDepartment(getDepartmentByName(em, "Legal"));
        legalDocument.setRequestingDepartment(getDepartmentByName(em, "--"));
        // set date and month received
        legalDocument.setDateReceived(now);
        c.setTime(legalDocument.getDateReceived());
        legalDocument.setMonthReceived(c.get(Calendar.MONTH));
        // submitted by
        legalDocument.setSubmittedBy(getEmployeeByName(em, "--", "--"));
        // doc type
        legalDocument.setType(getDocumentTypeByName(em, "--"));
        // doc classification
        legalDocument.setClassification(getClassificationByName(em, "--"));
        // doc for
        legalDocument.setDocumentForm("H"); // hard copy
        // get number
        //legalDocument.setNumber(getLegalDocumentNumber(legalDocument, "ED"));

        return legalDocument;
    }

    public static void importDepartmentsFromAccessDatabase(EntityManager em) {

        // if (setupDatabaseConnections()) {
        int numDepts = 0;
        // import
        try {
            em.getTransaction().begin();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tblDepartment";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ++numDepts;
                Department department = new Department();
                department.setName(rs.getString("Name").replaceAll("\"", "''"));
                department.setSubGroupCode(rs.getString("SubGroupCode"));
                System.out.println(saveBusinessEntity(em, department));
            }
            em.getTransaction().commit();
            System.out.println("# of departments: " + numDepts);
        } catch (SQLException ex) {
            System.out.println("Error getting records from MS Access.: " + ex);
        }
//        }

    }

    public static void importEmployeesFromExcel(
            EntityManager em,
            int sheetNumber) {
        // cell indices
        final int DEPT_NO = 0;
        final int LASTNAME = 1;
        final int FIRSTNAME = 2;
        int rowCount = 0, numEmployees = 0;
        int cellCount = 0;

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("Employee listing.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            em.getTransaction().begin();
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                if (i == sheetNumber) {
                    HSSFSheet sheet = wb.getSheetAt(i);

                    for (rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
                        HSSFRow row = sheet.getRow(rowCount);
                        cellCount = 0;
                        // create new employee
                        Employee employee = new Employee();
                        for (cellCount = 0; cellCount < row.getPhysicalNumberOfCells(); cellCount++) {
                            {
                                // populate petrol company and stations with data
                                HSSFCell cell = row.getCell(cellCount);
                                switch (cellCount) {
                                    case DEPT_NO:
                                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                            Department dept = getDepartmentBySubGroupCode(em, new Integer((int) cell.getNumericCellValue()).toString());
                                            employee.setDepartment(dept);
                                        }
                                        break;
                                    case LASTNAME:
                                        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                            employee.setLastName(cell.getRichStringCellValue().getString().replaceAll("\"", "''"));
                                        }
                                        break;
                                    case FIRSTNAME: // add station only if name if valid
                                        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                            employee.setFirstName(cell.getRichStringCellValue().getString().replaceAll("\"", "''"));
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }

                        // save the petrol company
                        if ((employee.getFirstName() != null) && (employee.getLastName() != null)) {
                            System.out.println("Saving employee: " + employee.getLastName() + ", " + employee.getFirstName());
                            saveBusinessEntity(em, employee);
                            numEmployees++;
                        }
                    }
                    em.getTransaction().commit();
                }
            }

            // stat
            System.out.println("Number of rows processed: " + rowCount);
            System.out.println("Number of employees imported: " + numEmployees);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static Employee getEmployee(EntityManager em, String fullName) {
        String names[] = fullName.split(" ");
        if (names.length == 3) {
            // name without hyphen
            Employee e = getEmployeeByName(em, names[0].trim(), names[1].trim() + " " + names[2].trim());
            if (e != null) {
                return e;
            }
            // get hyphenated name
            e = getEmployeeByName(em, names[0].trim(), names[1].trim() + "-" + names[2].trim());
            return e;
        } else if (names.length == 2) {
            return getEmployeeByName(em, names[0].trim(), names[1].trim());
        }

        return null;
    }

    public static void importLegalDocumentsFromExcel(
            EntityManager em,
            String file,
            int sheetNumber) {
        // cell indices
        final int DOC_NO = 0;
        final int DATE_RECEIVED = 1;
        final int SUBMITTED_BY = 2;
        final int PARTICULARS = 3;
        final int NATURE_OF_REQUEST = 4;
        final int DELIVERY_DATE = 5;
        final int ACTUAL_DELIVERY_DATE = 6;
        final int COMMENTS = 7;

        int rowCount = 0, numDocs = 0;
        int cellCount = 0;

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                if (i == sheetNumber) {
                    HSSFSheet sheet = wb.getSheetAt(i);

                    for (rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
                        if (rowCount > 1) {
                            //  em.getTransaction().begin();
                            HSSFRow row = sheet.getRow(rowCount);
                            cellCount = 0;
                            //if (row != null) {
                            // create new doc
                            LegalDocument doc = createNewLegalDocument(em);
                            doc.setNumber(null);
                            for (cellCount = 0; cellCount < row.getPhysicalNumberOfCells(); cellCount++) {
                                HSSFCell cell = row.getCell(cellCount);
                                if (cell != null) {
                                    switch (cellCount) {
                                        case DOC_NO:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                doc.setNumber(cell.getRichStringCellValue().getString());
                                            }
                                            break;
                                        case DATE_RECEIVED:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                                doc.setDateReceived(cell.getDateCellValue());
                                            }
                                            break;
                                        case SUBMITTED_BY:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                Employee employee = getEmployee(em, cell.getRichStringCellValue().getString());
                                                if (employee != null) {
                                                    doc.setSubmittedBy(employee);
                                                }
                                            }
                                            break;
                                        case PARTICULARS:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                doc.setDescription(cell.getRichStringCellValue().getString());
                                            }
                                            break;
                                        case NATURE_OF_REQUEST:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                doc.setWorkPerformedOnDocument(cell.getRichStringCellValue().getString());
                                            }
                                            break;
                                        case DELIVERY_DATE:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                                doc.setExpectedDateOfCompletion(cell.getDateCellValue());
                                            }
                                            break;
                                        case ACTUAL_DELIVERY_DATE:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                                doc.setDateOfCompletion(cell.getDateCellValue());
                                            }
                                            break;
                                        case COMMENTS:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                doc.setComments(cell.getRichStringCellValue().getString());
                                            }
                                            break;
                                        default:
                                            break;

                                    }
                                }
                            }
                            // save doc                            
                            if (doc.getNumber() != null) {
                                if (!doc.getNumber().trim().equals("")) {
                                    System.out.println("Saving doc: " + doc.getNumber());
                                    em.getTransaction().begin();
                                    saveBusinessEntity(em, doc);
                                    em.getTransaction().commit();
                                    numDocs++;
                                }
                            }

                        }
                    }
                    // stat
                    System.out.println("Number of rows processed: " + rowCount);
                    System.out.println("Number of docs imported: " + numDocs);

                    return;
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void importClientsFromExcel(
            EntityManager em,
            String file,
            int sheetNumber) {
        // cell indices

        final int CLIENT = 6;
        int rowCount = 0, numClients = 0;
        int cellCount = 0;

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                if (i == sheetNumber) {
                    System.out.println("Importing from: " + file + ", sheet: " + sheetNumber);
                    HSSFSheet sheet = wb.getSheetAt(i);

                    for (rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
                        if (rowCount > 1) { // ignore header row
                            //  em.getTransaction().begin();
                            HSSFRow row = sheet.getRow(rowCount);
                            cellCount = 0;
                            // create new client
                            Client client = new Client();
                            for (cellCount = 0; cellCount < row.getPhysicalNumberOfCells(); cellCount++) {
                                HSSFCell cell = row.getCell(cellCount);
                                if (cell != null) {
                                    switch (cellCount) {
                                        case CLIENT:
                                            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                client.setName(cell.getRichStringCellValue().getString().trim().replaceAll("\"", "''"));
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            // save client
                            if (client.getName() != null) {
                                if (!client.getName().equals("")) {
                                    // Check if client exists before trying to import
                                    if (getClient(em, client.getName()) == null) {
                                        System.out.println("Saving client: " + client.getName());
                                        em.getTransaction().begin();
                                        saveBusinessEntity(em, client);
                                        em.getTransaction().commit();
                                        numClients++;
                                    }
                                }
                            }

                        }
                    }
                    // stat
                    System.out.println("Number of rows processed: " + rowCount);
                    System.out.println("Number of clients imported: " + numClients);

                    return;
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void buildGasStationDatabase(
            EntityManager em,
            String fileName,
            int sheetNumber,
            PetrolCompany company, // petrol company eg. TOTAL(ESSO)            
            String station,
            Client defaultClient,
            Employee defaultAssignee) { // when set to null the each row on sheet is used as company branch. Otherwise each row is company.
        // cell indices
        final int NO = 0;
        final int PARISH = 1;
        final int STATION_NAME = 2;
        final int ADDRESS = 3;
        final int NUMBER_OF_PUMPS = 4;
        final int REJECTED_OR_NOT_WORKING = 5;
        final int CERTIFIED_ON = 6;
        final int EXPIRY_DATE = 7;
        final int STATUS = 8;
        final int RATE = 9;
        final int FINAL_AMOUNT = 10;
        final int NOZZLES_PER_PUMP = 2;
        int rowCount;
        int cellCount;
        PetrolCompany petrolCompany = company;

        //
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName)); // eg. "GAS STATION DATABASE 2009.xls"
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            em.getTransaction().begin();
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                if (i == sheetNumber) {
                    HSSFSheet sheet = wb.getSheetAt(i);
                    //petrolCompany.setName(company);
                    for (rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
//                        for (HSSFRow row : sheet.g) {
                        if (rowCount > 0) { // make sure this is not the header
                            HSSFRow row = sheet.getRow(rowCount);
                            cellCount = 0;
                            // create new station
                            PetrolStation petrolStation = new PetrolStation();
                            // add default objects
                            petrolStation.setClient(defaultClient);
                            petrolStation.setLastAssignee(defaultAssignee);

                            if (row != null) {
                                for (cellCount = 0; cellCount < row.getPhysicalNumberOfCells(); cellCount++) {
                                    // populate petrol company and stations with data
                                    HSSFCell cell = row.getCell(cellCount);
                                    if (cell != null) {
                                        switch (cellCount) {
                                            case NO:
                                                break;
                                            case PARISH:
                                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                    petrolStation.getDefaultAddress().setStateOrProvince(cell.getStringCellValue());
                                                }
                                                break;
                                            case STATION_NAME: // add station only if name if valid
                                                if ((cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                                                        && (!cell.getRichStringCellValue().getString().trim().equals(""))) {
                                                    System.out.println("Importing: " + cell.getRichStringCellValue().getString());
                                                    petrolCompany.getPetrolStations().add(petrolStation);
                                                    petrolStation.setName(cell.getRichStringCellValue().getString());
                                                }
                                                break;
                                            case ADDRESS:
                                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                                    petrolStation.getDefaultAddress().setAddressLine1(cell.getStringCellValue());
                                                }
                                                break;
                                            case NUMBER_OF_PUMPS:
                                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                                    int numberOfNozzles = (int) cell.getNumericCellValue();
                                                    // create petrol pumps and nozzles. use max 2 nozzles per pump.
                                                    int numberOfPumps = (int) (numberOfNozzles / NOZZLES_PER_PUMP);
                                                    int oddNozzles = numberOfNozzles % NOZZLES_PER_PUMP;

                                                    if (numberOfPumps > 0) {
                                                        for (int j = 0; j < numberOfPumps; j++) {
                                                            PetrolPump petrolPump = new PetrolPump();
                                                            petrolPump.setManufacturer(getManufacturerByName(em, "-- enter name --"));
                                                            petrolStation.getPetrolPumps().add(petrolPump);
                                                            // create NOZZLES_PER_PUMP nozzles
                                                            for (int k = 0; k < NOZZLES_PER_PUMP; k++) {
                                                                PetrolPumpNozzle nozzle = new PetrolPumpNozzle();
                                                                nozzle.setManufacturer(getManufacturerByName(em, "-- enter name --"));
                                                                petrolPump.getNozzles().add(nozzle);
                                                            }
                                                        }
                                                        // create a pump with odd no. nozzles if any
                                                        if (oddNozzles > 0) {
                                                            PetrolPump petrolPump = new PetrolPump();
                                                            petrolPump.setManufacturer(getManufacturerByName(em, "-- enter name --"));
                                                            petrolStation.getPetrolPumps().add(petrolPump);
                                                            // create NOZZLES_PER_PUMP nozzles
                                                            petrolPump.setNozzles(new ArrayList<PetrolPumpNozzle>());
                                                            for (int k = 0; k < oddNozzles; k++) {
                                                                PetrolPumpNozzle nozzle = new PetrolPumpNozzle();
                                                                nozzle.setManufacturer(getManufacturerByName(em, "-- enter name --"));
                                                                petrolPump.getNozzles().add(nozzle);
                                                            }
                                                        }
                                                    } else if (oddNozzles > 0) {
                                                        PetrolPump petrolPump = new PetrolPump();
                                                        petrolPump.setManufacturer(getManufacturerByName(em, "-- enter name --"));
                                                        petrolStation.getPetrolPumps().add(petrolPump);
                                                        // create NOZZLES_PER_PUMP nozzles
                                                        petrolPump.setNozzles(new ArrayList<PetrolPumpNozzle>());
                                                        for (int k = 0; k < oddNozzles; k++) {
                                                            PetrolPumpNozzle nozzle = new PetrolPumpNozzle();
                                                            nozzle.setManufacturer(getManufacturerByName(em, "-- enter name --"));
                                                            petrolPump.getNozzles().add(nozzle);
                                                        }
                                                    } else {
                                                        System.out.println("Station has no pump!!");
                                                    }
                                                    System.out.println("No. of 2 nozzle pumps: " + numberOfPumps + ", Odd nozzles: " + oddNozzles);
                                                }
                                                break;
                                            case REJECTED_OR_NOT_WORKING:
                                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                                    int nozzlesRejected = (int) cell.getNumericCellValue();
                                                    if (nozzlesRejected > 0) {
                                                        int pumpsToUpdate = (int) (nozzlesRejected / NOZZLES_PER_PUMP);
                                                        int oddNozzlesToUpdate = nozzlesRejected % NOZZLES_PER_PUMP;
                                                        // fill out all pump nozzles here
                                                        if (pumpsToUpdate > 0) {
                                                            for (int j = 0; j < pumpsToUpdate; j++) {
                                                                PetrolPump pertrolPump = petrolStation.getPetrolPumps().get(j);
                                                                for (PetrolPumpNozzle nozzle : pertrolPump.getNozzles()) {
                                                                    nozzle.setComments("Rejected/Not working");
                                                                }
                                                            }
                                                            // fill out the odd number pump nozzles
                                                            if (oddNozzlesToUpdate > 0) {
                                                                System.out.println("Bad nozzles: " + oddNozzlesToUpdate);
                                                                PetrolPump pertrolPump = petrolStation.getPetrolPumps().get(pumpsToUpdate);
                                                                for (int j = 0; j < oddNozzlesToUpdate; j++) {
                                                                    pertrolPump.getNozzles().get(j).setComments("Rejected/Not working");
                                                                }
                                                            }
                                                        } else if (oddNozzlesToUpdate > 0) {
                                                            System.out.println("Bad nozzles: " + oddNozzlesToUpdate);
                                                            PetrolPump pertrolPump = petrolStation.getPetrolPumps().get(0);
                                                            if (!pertrolPump.getNozzles().isEmpty()) {
                                                                for (int j = 0; j < oddNozzlesToUpdate; j++) {
                                                                    pertrolPump.getNozzles().get(j).setComments("Rejected/Not working");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case CERTIFIED_ON:
                                                // set the date in all pumps for now
                                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                                    Date certifiedOn = cell.getDateCellValue();
                                                    if (certifiedOn != null) {
                                                        int numberOfPumps = petrolStation.getPetrolPumps().size();
                                                        for (int j = 0; j < numberOfPumps; j++) {
                                                            PetrolPump petrolPump = petrolStation.getPetrolPumps().get(j);
                                                            petrolPump.getCertification().setDateIssued(certifiedOn);
                                                            petrolStation.setCertification(petrolPump.getCertification());
                                                        }
                                                    }
                                                }
                                                break;
                                            case EXPIRY_DATE:
                                                // set the date in all pumps for now
                                                //if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {

                                                //if (expiryDate != null) {
                                                int numberOfPumps = petrolStation.getPetrolPumps().size();
                                                for (int j = 0; j < numberOfPumps; j++) {
                                                    PetrolPump petrolPump = petrolStation.getPetrolPumps().get(j);
//                                                            Date expiryDate = BusinessEntityUtils.getModifiedDate(petrolPump.getCertification().getDateIssued(), Calendar.DAY_OF_MONTH, 185);
                                                    Date expiryDate = BusinessEntityUtils.getModifiedDate(petrolPump.getCertification().getDateIssued(), Calendar.MONTH, 6);
                                                    petrolPump.getCertification().setExpiryDate(expiryDate);
                                                }
                                                //}
                                                //}
                                                break;
                                            case STATUS:
                                                break;
                                            case RATE:
                                                break;
                                            case FINAL_AMOUNT:
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // save the petrol company
                    System.out.println("Saving Petrol company: " + company);
                    saveBusinessEntity(em, petrolCompany);
                    em.getTransaction().commit();
                    // print stats
                    System.out.println("Number of petrol company stations: " + petrolCompany.getPetrolStations().size());

                    return;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static byte[] readImageOldWay(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        // Get the size of the file
        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    //TK: the following code are in BusinessEntityUtils. They are copied here
    // to be used for testing.
    public static String convertNumberToAlphabet(long num) {
        long numeric = (num) % 26;
        String letter = "" + (char) (65 + numeric);
        long num2 = (long) ((num) / 26);

        if (num2 > 0) {
            return convertNumberToAlphabet(num2 - 1) + letter;
        } else {
            return letter;
        }
    }
    public static String ALPHABET[] = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static int getLetterIndex(String letter) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i].equals(letter)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Determines the corresponding index for a maximum sequence of characters.
     * The sequence of characters are those that are obtained from a spreadsheet
     * column heading (eg AR).
     *
     * @param alphabet
     * @param len
     * @param num
     * @return
     */
    public static long convertAlphabetToNumber(String alphabet, int len, long num) {
        int letterIndex, previousLetterIndex;

        char chars[] = alphabet.toCharArray();
        letterIndex = getLetterIndex("" + chars[len - 1]);
        if ((len - 1) > 0) {
            previousLetterIndex = getLetterIndex("" + chars[len - 2]);
        } else {
            previousLetterIndex = -1;
        }
        num = num + letterIndex;
        if (previousLetterIndex != -1) {
            num = num + (previousLetterIndex + len - 1) * 26;
        }

        if ((len - 2) > 0) {
            String newAlphabet = alphabet.substring(0, len - 1);
            //System.out.println("str len: " + newAlphabet.length());
            return convertAlphabetToNumber(newAlphabet, newAlphabet.length(), num);
        } else {
            return num;
        }
    }

    public static void main(String[] args) {
        if (setupDatabaseConnection("PU")) {
            EntityManager em = EMF.createEntityManager();
            
            Client client = Client.findClientById(em, 1088587L);
            System.out.println("Client name: " + client.getName());
            System.out.println("Addresses: " + client.getAddresses().size());
            
            System.out.println("Client address found: " + 
                    Address.findClientAddressById(em, 
                            "Another address for testing;  line 2;  city; Saint Elizabeth", 
                            client.getId()));
            
            // Another address for testing;  line 2;  city; Saint Elizabeth
        }
    }
}
