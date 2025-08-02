package dev.andreisima.notification_scheduler.domain.ports;

import dev.andreisima.notification_scheduler.domain.Notification;

public interface NotifierPort {
    void send(Notification notification);
}
