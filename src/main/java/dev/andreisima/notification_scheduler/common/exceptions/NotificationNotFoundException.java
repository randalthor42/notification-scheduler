package dev.andreisima.notification_scheduler.common.exceptions;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(String id) {
        super("Notification with ID " + id + " not found.");
    }
}
