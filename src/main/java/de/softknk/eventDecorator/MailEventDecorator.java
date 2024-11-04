package de.softknk.eventdecorator;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.softknk.Event;

public class MailEventDecorator extends EventDecorator {

    private static final String MAIL_ADDRESS = "test-aswe@web.de";
    private static final String MAIL_PASSWORD = "aswe!test006";

    private Properties mailProps;
    private String mailSubject;
    private String recipientAddress;

    public MailEventDecorator(Event wrapperEvent, String mailSubject, String recipientAddress) {
        super(wrapperEvent);

        this.mailSubject = mailSubject;
        this.recipientAddress = recipientAddress;

        // Set up the SMTP server properties
        mailProps = new Properties();

        mailProps.put("mail.smtp.host", "smtp.web.de"); // Using WEB.DE host
        mailProps.put("mail.smtp.port", "587"); // or 465 for SSL
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");
    }

    @Override
    public void eventNotify() {
        sendMail();
        // Notify wrapper object
        wrapperEvent.eventNotify();
    }

    private void sendMail() {
        // Create a session with an authenticator
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_ADDRESS, MAIL_PASSWORD);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
            message.setSubject(mailSubject);
            message.setText(getMessage());

            // Send the message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Properties getMailProps() {
        return mailProps;
    }

    public void setMailProps(Properties mailProps) {
        this.mailProps = mailProps;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

}
