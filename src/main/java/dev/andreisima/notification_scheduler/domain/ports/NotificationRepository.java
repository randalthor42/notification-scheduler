package dev.andreisima.notification_scheduler.domain.ports;

import dev.andreisima.notification_scheduler.domain.Notification;

import java.util.Optional;

public interface NotificationRepository {
    void save(Notification notification);
    Optional<Notification> findById(String id);
}
