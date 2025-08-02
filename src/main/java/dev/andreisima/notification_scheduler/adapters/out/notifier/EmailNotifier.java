package dev.andreisima.notification_scheduler.adapters.out.notifier;

import dev.andreisima.notification_scheduler.domain.Notification;
import dev.andreisima.notification_scheduler.domain.ports.NotifierPort;
import org.springframework.stereotype.Component;

@Component("email")
public class EmailNotifier implements NotifierPort {
    @Override
    public void send(Notification notification) {
        System.out.printf("[Email] Sent to %s: %s%n", notification.getRecipient(), notification.getMessage());
    }
}
