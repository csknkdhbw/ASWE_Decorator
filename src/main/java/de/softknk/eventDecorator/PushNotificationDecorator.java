package de.softknk.eventdecorator;

import de.softknk.Event;

public class PushNotificationDecorator extends EventDecorator {

    public PushNotificationDecorator(Event wrapperEvent) {
        super(wrapperEvent);
    }

    @Override
    public void eventNotify() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}