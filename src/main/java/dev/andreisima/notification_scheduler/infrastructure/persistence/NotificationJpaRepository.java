package dev.andreisima.notification_scheduler.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, String> {
}
