package de.softknk.eventDecorator;

import de.softnk.Event;

public abstract class EventDecorator extends Event {

    protected Event wrapperEvent;

    public Event getWrapperEvent() {
        return wrapperEvent;
    }

    public void setWrapperEvent(Event wrapperEvent) {
        this.wrapperEvent = wrapperEvent;
    }
}