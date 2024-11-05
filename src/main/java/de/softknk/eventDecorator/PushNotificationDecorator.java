package de.softknk.eventdecorator;

import de.softknk.Event;

public class PushNotificationDecorator extends EventDecorator {

    public PushNotificationDecorator(Event wrapperEvent) {
        super(wrapperEvent);
    }

    @Override
    public boolean checkEventAndNotify() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}