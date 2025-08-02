package dev.andreisima.notification_scheduler.infrastructure.persistence;

import dev.andreisima.notification_scheduler.domain.Notification;
import dev.andreisima.notification_scheduler.domain.ports.NotificationRepository;
import dev.andreisima.notification_scheduler.application.mapper.NotificationMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final NotificationJpaRepository jpaRepository;
    private final NotificationMapper mapper;

    public NotificationRepositoryAdapter(NotificationJpaRepository jpaRepository, NotificationMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Notification notification) {
        NotificationEntity entity = mapper.toEntity(notification);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Notification> findById(String id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
