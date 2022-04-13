package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentActivity {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yy, HH:mm");
    private static final DateTimeFormatter FORMATTER_TO_STRING = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

    private LocalDateTime dateTime;
    private String eventContext;
    private String component;
    private String eventName;
    private String description;

    public StudentActivity() {}

    public StudentActivity(String dateTime, String eventContext, String component, String eventName, String description) {
        setDateTime(dateTime);
        this.eventContext = eventContext;
        this.component = component;
        this.eventName = eventName;
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, FORMATTER);
    }

    public String getEventContext() {
        return eventContext;
    }

    public void setEventContext(String eventContext) {
        this.eventContext = eventContext;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StudentActivity{" +
                "dateTime='" + dateTime.format(FORMATTER_TO_STRING) + '\'' +
                ", eventContext='" + eventContext + '\'' +
                ", component='" + component + '\'' +
                ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
