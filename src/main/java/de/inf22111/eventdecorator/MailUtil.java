package de.inf22111.eventdecorator;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    protected static final Properties mailProps;
    protected static final String mailAddress;
    protected static final String mailPassword;

    static {
        // Set up the SMTP server properties
        mailProps = new Properties();

        mailProps.put("mail.smtp.host", "smtp.web.de"); // Using WEB.DE host
        mailProps.put("mail.smtp.port", "587"); // or 465 for SSL
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");

        mailAddress = "test-aswe@web.de";
        mailPassword = "aswe!test006";
    }

    private MailUtil() {
        //
    }

    public static void sendMail(
            String recipientAddress,
            String mailSubject,
            String mailMessage
    ) {
        // Create a session with an authenticator
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailAddress, mailPassword);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
            message.setSubject(mailSubject);
            message.setText(mailMessage);

            // Send the message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
