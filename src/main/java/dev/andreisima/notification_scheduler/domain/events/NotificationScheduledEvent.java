package dev.andreisima.notification_scheduler.domain.events;

public class NotificationScheduledEvent {
    private final String notificationId;

    public NotificationScheduledEvent(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationId() {
        return notificationId;
    }
}
