package de.softknk;

import java.time.LocalDate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.softknk.eventdecorator.FileLoggingDecorator;
import de.softknk.eventdecorator.MailEventDecorator;

/**
 * MAIN
 */
public class App {

    private static Event v2;
    private static ScheduledExecutorService scheduler;

    public static void main(String[] args) {
        Event v = new TestEvent("Was läuft heute?\nWas geeeeeeeeht abbbbb");
    //    v = new MailEventDecorator(v, "Whaaaaats uppp", "daniel.kuenkel2003@gmail.com");
    //    v = new FileLoggingDecorator(v, "C:\\Users\\DKUENKE\\Desktop\\test.txt");

        v.checkEventAndNotify();



        v2 = new MailEventDecorator(new BitcoinPriceTriggerEvent(60000), "BTC at 60k!", "daniel.kuenkel2003@gmail.com");

        // Create a scheduled executor to run the task every minute
        scheduler = Executors.newScheduledThreadPool(1);
     //   scheduler.scheduleAtFixedRate(App::btcTriggerSchedule, 0, 5, TimeUnit.MINUTES);

        CalenderEvent v3 = new CalenderEvent();
        v3.addCalenderEvent(LocalDate.of(2024, 11, 5), "Heute ist die US-Wahl!");

        Event v4 = new FileLoggingDecorator(v3, "C:\\Users\\DKUENKE\\Desktop\\test.txt");
        v4.checkEventAndNotify();
    }

    private static void btcTriggerSchedule() {
        // We only want to get the price trigger once
        if (v2.checkEventAndNotify())
            scheduler.shutdown();
    }
}