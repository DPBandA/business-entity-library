/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.sm.SystemOption;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class MailUtils {

    public static Session getDefaultEmailSession(EntityManager em) {
        Session session;
        Properties prop = new Properties();

        if (SystemOption.getBoolean(em, "mail.smtp.auth")) {
            prop.put("mail.smtp.auth", SystemOption.getBoolean(em, "mail.smtp.auth"));
            prop.put("mail.smtp.host", SystemOption.getString(em, "mail.smtp.host"));
            prop.put("mail.smtp.port", SystemOption.getInteger(em, "mail.smtp.port"));
            prop.put("mail.smtp.ssl.trust", SystemOption.getString(em, "mail.smtp.ssl.trust"));

            session = Session.getInstance(prop,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(
                            SystemOption.getString(em, "mail.smtp.user"),
                            SystemOption.getString(em, "mail.smtp.password"));
                }
            });
        } else {
            prop.put("mail.smtp.host", SystemOption.getString(em, "mail.smtp.host"));

            session = Session.getDefaultInstance(prop, null);
        }

        return session;
    }

    public static void sendErrorEmail(final String subject,
            final String message,
            final EntityManager em) {
        try {
            if ((Boolean) SystemOption.getOptionValueObject(em,
                    "developerEmailAlertActivated")) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            postMail(null,
                                    SystemOption.getString(em, "jobManagerEmailAddress"),
                                    "Job Manager",
                                    SystemOption.getString(em, "softwareDeveloperEmailAddress"),
                                    subject, message,
                                    "text/plain", em);
                        } catch (Exception e) {
                            System.out.println("Error sending error mail!");
                        }
                    }

                }.start();
            }
        } catch (Exception ex) {
            System.out.println("Error sending error mail!");
        }
    }

    /*
    public static ReturnMessage postMail(
            Session mailSession,
            Employee fromEmployee,
            Employee toEmployee,
            String subject,
            String message,
            String contentType,
            EntityManager em) {

        InternetAddress addressFrom;
        InternetAddress[] addressTo = null;
        MimeMessage msg;

        try {
            // use default session if none was provided
            if (mailSession == null) {
                //Set the host smtp address
//                Properties props = new Properties();
//                props.put("mail.smtp.host", (String) SystemOption.getOptionValueObject(em, "mail.smtp.host"));

                // create some properties and get the default Session
                Session session = getDefaultEmailSession(em);
                        //Session.getDefaultInstance(props, null);
                //session.setDebug(debug);
                msg = new MimeMessage(session);
            } else {
                msg = new MimeMessage(mailSession);
            }

            // set the from and to address
            if (fromEmployee == null) {
                addressFrom = new InternetAddress(
                        (String) SystemOption.getOptionValueObject(em, "jobManagerEmailAddress"),
                        (String) SystemOption.getOptionValueObject(em, "jobManagerEmailName"));
            } else {
                addressFrom = new InternetAddress(
                        fromEmployee.getInternet().getEmail1(),
                        fromEmployee.getFirstName() + " " + fromEmployee.getLastName());
            }
            msg.setFrom(addressFrom);

            addressTo = new InternetAddress[1];
            if (toEmployee != null) {
                addressTo[0] = new InternetAddress(toEmployee.getInternet().getEmail1());
            } else {
                addressTo[0] = new InternetAddress(
                        (String) SystemOption.getOptionValueObject(em, "administratorEmailAddress"));
            }

            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(message, contentType);
            Transport.send(msg);

            return new ReturnMessage();

        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println("An error occurred while posting an email to: " + Arrays.toString(addressTo));
            return new ReturnMessage(false, "An error occurred while posting an email.");
        }

    }
     */
    public static ReturnMessage postMail(
            Session mailSession,
            String from,
            String fromName,
            String to,
            String subject,
            String message,
            String contentType,
            EntityManager em) {

        MimeMessage msg;
        InternetAddress addressFrom;
        InternetAddress[] addressTo;

        try {

            if (mailSession == null) {
                Session session = getDefaultEmailSession(em);
                msg = new MimeMessage(session);
            } else {
                msg = new MimeMessage(mailSession);
            }

            addressFrom = new InternetAddress(from, fromName);
            msg.setFrom(addressFrom);

            addressTo = new InternetAddress[1];
            addressTo[0] = new InternetAddress(to);

            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);
            msg.setSubject(subject);
            if (contentType == null) {
                contentType = "text/plain";
            }
            msg.setContent(message, contentType);

            Transport.send(msg);

            return new ReturnMessage();

        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println("An error occurred while posting an email: " + e);
            return new ReturnMessage(false, "An error occurred while posting an email.");
        }

    }

}
