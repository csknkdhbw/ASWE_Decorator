package de.inf22111;

import java.time.LocalDate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.inf22111.eventdecorator.FileLoggingDecorator;
import de.inf22111.eventdecorator.MailEventDecorator;
import de.inf22111.eventdecorator.PushNotificationDecorator;

/**
 * MAIN
 */
public class App {

    public static void main(String[] args) {
        
        // CREATE BITCOIN EVENT WITH MAIL NOTIFICATION
        Event v2 = new MailEventDecorator(new BitcoinPriceTriggerEvent(60000), 
                                            "BTC at 60k!", 
                                            "inf22111@lehre.dhbw-stuttgart.de"
                                        );
        v2.checkEventAndNotify();

        // CREATE CALENDER EVENT WITH FILE LOGGING AND PUSH NOTIFICATION
        CalenderEvent v3 = new CalenderEvent();
        v3.addCalenderEvent(LocalDate.of(2024, 12, 11), "Für die IT-Sicherheit Klausur lernen.");

        Event v4 = new FileLoggingDecorator(v3, "C:\\Users\\DKUENKE\\Desktop\\test.txt");
        v4 = new PushNotificationDecorator(v4);

        v4.checkEventAndNotify();
    }
}