package de.softknk;

import de.softknk.eventdecorator.FileLoggingDecorator;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Event v = new TestEvent("Was läuft heute?\nWas geeeeeeeeht abbbbb");
    //    v = new MailEventDecorator(v, "Whaaaaats uppp", "daniel.kuenkel2003@gmail.com");
        v = new FileLoggingDecorator(v, "C:\\Users\\DKUENKE\\Desktop\\test.txt");

        v.eventNotify();
    }
}
