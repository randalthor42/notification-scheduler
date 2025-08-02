package dev.andreisima.notification_scheduler.infrastructure.events.listeners;

import dev.andreisima.notification_scheduler.domain.events.NotificationScheduledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationAuditListener {

    @EventListener
    public void handle(NotificationScheduledEvent event) {
        System.out.printf("Notification scheduled: %s%n", event.getNotificationId());
    }
}
