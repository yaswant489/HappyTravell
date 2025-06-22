/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPSMailSender {

    private static final String CONFIG_FILE = "email_config.properties";
    private static Properties emailProps = new Properties();

    static {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            emailProps.load(input);
        } catch (IOException ex) {
            System.err.println("Error loading email configuration file: " + CONFIG_FILE);
            ex.printStackTrace();
        }
    }

    private static final String SENDER_EMAIL = emailProps.getProperty("SENDER_EMAIL");
    private static final String SENDER_APP_PASSWORD = emailProps.getProperty("SENDER_APP_PASSWORD");

    public static boolean sendMail(String recipient, String subject, String body) {
        if (SENDER_EMAIL == null || SENDER_APP_PASSWORD == null || SENDER_EMAIL.isEmpty() || SENDER_APP_PASSWORD.isEmpty()) {
            System.err.println("Email credentials are not configured in " + CONFIG_FILE);
            return false;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_APP_PASSWORD.replace(" ", ""));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully to " + recipient);
            return true;

        } catch (MessagingException e) {
            System.err.println("Failed to send email.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVerificationCode(String recipient, String verificationCode) {
        String subject = "Password Reset Verification Code - HappyTravell";
        String body = "Hello,\n\n" +
                      "Your verification code is: " + verificationCode + "\n\n" +
                      "This code will expire in 15 minutes.\n" +
                      "If you didn't request this password reset, please ignore this email.\n\n" +
                      "Best regards,\nThe HappyTravell Team";
        
        return sendMail(recipient, subject, body);
    }
}

