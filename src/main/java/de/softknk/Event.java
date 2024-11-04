package de.softknk;

public abstract class Event {
    private String message;

    /**
     * To be defined by decorator classes in order to
     * notify someone about the occuring event
     */
    public abstract void eventNotify();

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}