package frc.API.type;

import lombok.Data;

/**
 * Data class representing a frc event, using lombok @Data.
 */
@Data
public class Event {

    private final int week;
    private final String event_code;

    public Event(int week, String event_code) {
        this.week = week;
        this.event_code = event_code;
    }

}
