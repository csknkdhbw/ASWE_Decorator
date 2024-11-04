package de.softknk;

public class TestEvent extends Event implements EventProducer {

    public TestEvent(String message) {
        this.setMessage(message);
    }

    @Override
    public void eventNotify() {
        System.out.println("Event received.");
    }

    @Override
    public void produceEvent() {
        //throw new UnsupportedOperationException("Not supported yet.");
        eventNotify();
    }


}