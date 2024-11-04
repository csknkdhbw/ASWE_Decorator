package de.softknk;

public interface EventProducer {

    /**
     * Defines the method for producing a new event which is then
     * stored in the <message> attribute
     */
    public abstract void produceEvent();
}
