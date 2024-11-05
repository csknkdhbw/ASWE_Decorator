package de.softknk;

public abstract class Event {
    private String message;

    /**
     * To be defined by decorator classes in order to
     * notify someone about the occuring event.
     * Returns true, if new event is available, false otherwise
     */
    public abstract boolean checkEventAndNotify();

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}