package de.softknk;

public class TestEvent extends Event {

    public TestEvent(String message) {
        this.setMessage(message);
    }

    @Override
    public boolean checkEventAndNotify() {
        System.out.println("Event received.");
        return true;
    }

}