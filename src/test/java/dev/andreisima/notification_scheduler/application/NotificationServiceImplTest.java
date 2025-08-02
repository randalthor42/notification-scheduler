package dev.andreisima.notification_scheduler.application;

import dev.andreisima.notification_scheduler.domain.*;
import dev.andreisima.notification_scheduler.domain.ports.NotificationRepository;
import dev.andreisima.notification_scheduler.infrastructure.scheduler.QuartzSchedulerAdapter;
import org.junit.jupiter.api.Test;
import dev.andreisima.notification_scheduler.infrastructure.events.DomainEventPublisher;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    @Test
    void shouldScheduleNotification() {
        NotificationRepository repository = mock(NotificationRepository.class);
        QuartzSchedulerAdapter scheduler = mock(QuartzSchedulerAdapter.class);
        DomainEventPublisher eventPublisher = mock(DomainEventPublisher.class);

        NotificationService service = new NotificationServiceImpl(repository, scheduler, eventPublisher);

        Notification notification = new Notification(
                "123", "Message", "test@example.com",
                DeliveryChannel.EMAIL, LocalDateTime.now().plusMinutes(5), NotificationStatus.SCHEDULED
        );

        service.schedule(notification);

        verify(repository).save(notification);
        verify(scheduler).schedule(notification);
        verify(eventPublisher).publish(any());
    }
}
