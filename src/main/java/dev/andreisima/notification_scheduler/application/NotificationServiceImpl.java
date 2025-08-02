package dev.andreisima.notification_scheduler.application;

import dev.andreisima.notification_scheduler.domain.Notification;
import dev.andreisima.notification_scheduler.domain.events.NotificationScheduledEvent;
import dev.andreisima.notification_scheduler.domain.ports.NotificationRepository;
import dev.andreisima.notification_scheduler.infrastructure.events.DomainEventPublisher;
import dev.andreisima.notification_scheduler.infrastructure.scheduler.QuartzSchedulerAdapter;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final QuartzSchedulerAdapter scheduler;
    private final DomainEventPublisher eventPublisher;

    public NotificationServiceImpl(
            NotificationRepository repository,
            QuartzSchedulerAdapter scheduler,
            DomainEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.scheduler = scheduler;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void schedule(Notification notification) {
        repository.save(notification);
        scheduler.schedule(notification);
        eventPublisher.publish(new NotificationScheduledEvent(notification.getId()));
    }
}

