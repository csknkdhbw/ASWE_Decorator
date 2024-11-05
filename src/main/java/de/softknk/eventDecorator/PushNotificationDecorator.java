package de.softknk.eventdecorator;

import de.softknk.Event;

public class PushNotificationDecorator extends EventDecorator {
    private static final String mailSubject;
    private static final String pushOverMail;

    static {
        mailSubject = "CALENDER ALERT";
        pushOverMail = "arkxexqata@pomail.net";
    }

    public PushNotificationDecorator(Event wrapperEvent) {
        super(wrapperEvent);
    }

    @Override
    public boolean checkEventAndNotify() {
        // Notify wrapper object
        boolean isNewEvent = wrapperEvent.checkEventAndNotify();
        
        if (isNewEvent) {
            setMessage(wrapperEvent.getMessage());
            // PushOver provides sending push notification via mail
            MailUtil.sendMail(
                    pushOverMail,
                    mailSubject,
                    getMessage()
            );
        }

        return isNewEvent;
    }

}