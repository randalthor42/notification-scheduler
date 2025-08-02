package dev.andreisima.notification_scheduler.application.mapper;

import dev.andreisima.notification_scheduler.adapters.in.web.ScheduleNotificationRequest;
import dev.andreisima.notification_scheduler.domain.DeliveryChannel;
import dev.andreisima.notification_scheduler.domain.Notification;
import dev.andreisima.notification_scheduler.domain.NotificationStatus;
import dev.andreisima.notification_scheduler.infrastructure.persistence.NotificationEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationMapper {

    public static Notification fromRequest(ScheduleNotificationRequest request) {
        return new Notification(
                UUID.randomUUID().toString(),
                request.getMessage(),
                request.getRecipient(),
                DeliveryChannel.valueOf(request.getChannel().toUpperCase()),
                request.getScheduledAt(),
                NotificationStatus.SCHEDULED
        );
    }

    public NotificationEntity toEntity(Notification domain) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(domain.getId());
        entity.setMessage(domain.getMessage());
        entity.setRecipient(domain.getRecipient());
        entity.setChannel(domain.getChannel().name());
        entity.setScheduledAt(domain.getScheduledAt());
        entity.setStatus(domain.getStatus().name());
        return entity;
    }

    public Notification toDomain(NotificationEntity entity) {
        return new Notification(
                entity.getId(),
                entity.getMessage(),
                entity.getRecipient(),
                DeliveryChannel.valueOf(entity.getChannel()),
                entity.getScheduledAt(),
                NotificationStatus.valueOf(entity.getStatus())
        );
    }
}
