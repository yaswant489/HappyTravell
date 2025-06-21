/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//

//package happytravell.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SMTPSMailSender {
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;
    private static final String USERNAME = "bbhawana@gmail.com";
    private static final String PASSWORD = "peqp gcsq kubd rpej";
    
    public static boolean sendVerificationEmail(String recipientEmail, String verificationCode) {
        String subject = "Password Reset Verification Code";
        String body = "Your verification code is: " + verificationCode + 
                     "\n\nThis code will expire in 15 minutes." +
                     "\n\nIf you didn't request this, please ignore this email.";
        
        return sendEmail(recipientEmail, subject, body);
    }
    
    private static boolean sendEmail(String recipient, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}



//public class SMTPSMailSender {
//
//    private static final String host = "smtp.gmail.com";
//
//    private static final String port = "587"; 
//
//    private static final String email = "bbhawana@gmail.com";
//
//    private static String password = "peqp gcsq kubd rpej";
//
//
//
//    // Send Email Method
//
//    public static boolean sendMail(String recipient, String subject, String body) {
//
//        // Set up properties for the email session
//
//        Properties properties = new Properties();
//
//        properties.setProperty("mail.smtp.host", host);
//
//        properties.setProperty("mail.smtp.port", port);  
//
//        properties.setProperty("mail.smtp.auth", "true");
//
//        properties.setProperty("mail.smtp.starttls.enable", "true");  // Enable STARTTLS
//
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Forces TLSv1.2
//
//        // Create a session with the properties
//
//        Session session = Session.getInstance(properties, new Authenticator() {
//
//            @Override
//            
//            protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication(email, password);
//
//            }
//
//        });
//
//
//
//        try {
//
//            // Create the message
//
//            MimeMessage message = new MimeMessage(session);
//
//            message.setFrom(new InternetAddress(email));
//
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//
//            message.setSubject(subject);
//
//            message.setText(body);
//
//            // Send the message
//
//            Transport.send(message);
//
//            System.out.println("Mail sent successfully!"+body);
//
//            return true;
//
//        } catch (MessagingException mex) {
//
//            mex.printStackTrace();
//
//            return false;
//
//        }
//
//    }
//
//}