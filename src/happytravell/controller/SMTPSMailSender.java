/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import java.util.*;

public class SMTPSMailSender {

    // Send Email Method (Mock implementation)
    public static boolean sendMail(String recipient, String subject, String body) {
        try {
            System.out.println("=== EMAIL SIMULATION ===");
            System.out.println("To: " + recipient);
            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);
            System.out.println("=== EMAIL SIMULATION END ===");
            return true;
        } catch (Exception e) {
            System.err.println("Email simulation failed: " + e.getMessage());
            return false;
        }
    }

    // Send verification code email (Mock implementation)
    public static boolean sendMail(String recipient, String verificationCode) {
        String subject = "Password Reset Verification Code - HappyTravell";
        String body = "Your verification code is: " + verificationCode + "\n\n" +
                     "This code will expire in 15 minutes.\n" +
                     "If you didn't request this password reset, please ignore this email.\n\n" +
                     "Best regards,\nHappyTravell Team";
        
        System.out.println("=== VERIFICATION CODE SENT ===");
        System.out.println("Email: " + recipient);
        System.out.println("Verification Code: " + verificationCode);
        System.out.println("=== COPY THIS CODE TO VERIFY ===");
        
        return sendMail(recipient, subject, body);
    }
}

