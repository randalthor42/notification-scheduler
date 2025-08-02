package dev.andreisima.notification_scheduler.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notification implements Serializable {

    private final String id;
    private final String message;
    private final String recipient;
    private final DeliveryChannel channel;
    private final LocalDateTime scheduledAt;
    private final NotificationStatus status;

    public Notification(String id, String message, String recipient, DeliveryChannel channel,
                        LocalDateTime scheduledAt, NotificationStatus status) {
        this.id = id;
        this.message = message;
        this.recipient = recipient;
        this.channel = channel;
        this.scheduledAt = scheduledAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public DeliveryChannel getChannel() {
        return channel;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public NotificationStatus getStatus() {
        return status;
    }
}
