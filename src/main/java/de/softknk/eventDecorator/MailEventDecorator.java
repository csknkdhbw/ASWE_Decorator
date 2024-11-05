package de.softknk.eventdecorator;

import de.softknk.Event;

public class MailEventDecorator extends EventDecorator {

    private String mailSubject;
    private String recipientAddress;

    public MailEventDecorator(Event wrapperEvent, String mailSubject, String recipientAddress) {
        super(wrapperEvent);

        this.mailSubject = mailSubject;
        this.recipientAddress = recipientAddress;
    }

    @Override
    public boolean checkEventAndNotify() {
        // Notify wrapper object
        boolean isNewEvent = wrapperEvent.checkEventAndNotify();

        if (isNewEvent) {
            setMessage(wrapperEvent.getMessage());
            MailUtil.sendMail(
                    recipientAddress,
                    mailSubject,
                    getMessage()
            );
        }

        return isNewEvent;
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
