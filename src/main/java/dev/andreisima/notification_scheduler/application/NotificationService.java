package dev.andreisima.notification_scheduler.application;

import dev.andreisima.notification_scheduler.domain.Notification;

public interface NotificationService {
    void schedule(Notification notification);
}
