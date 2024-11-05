package de.softknk;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class CalenderEvent extends Event {
    private final Map<LocalDate, String> calenderEvents;

    public CalenderEvent() {
        this.calenderEvents = new HashMap<>();
    }

    public void addCalenderEvent(LocalDate date, String description) {
        calenderEvents.put(date, description);
    }

    @Override
    public boolean checkEventAndNotify() {
        boolean isNewEvent = false;

        // Assumption: Only max. one event per day
        for (Map.Entry<LocalDate, String> calenderEntry : calenderEvents.entrySet()) {
            if (LocalDate.now().isEqual(calenderEntry.getKey())) {
                setMessage(calenderEntry.getValue());
                isNewEvent = true;
                // Delete event from calender map
                calenderEvents.remove(calenderEntry.getKey());
                break;
            }
        }

        return isNewEvent;
    }

    public Map<LocalDate, String> getCalenderEvents() {
        return calenderEvents;
    }


}