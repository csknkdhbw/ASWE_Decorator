package de.inf22111.eventdecorator;

import de.inf22111.Event;

public abstract class EventDecorator extends Event {

    protected Event wrapperEvent;

    /**
     * Basic constructor to assign wrapper object
     */
    protected EventDecorator(Event wrapperEvent) {
        this.wrapperEvent = wrapperEvent;
        setMessage(wrapperEvent.getMessage());
    }

    public Event getWrapperEvent() {
        return wrapperEvent;
    }

    public void setWrapperEvent(Event wrapperEvent) {
        this.wrapperEvent = wrapperEvent;
    }
}