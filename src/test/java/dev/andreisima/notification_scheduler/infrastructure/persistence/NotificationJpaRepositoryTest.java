package dev.andreisima.notification_scheduler.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NotificationJpaRepositoryTest {

    @Autowired
    private NotificationJpaRepository repository;

    @Test
    void shouldSaveAndFindNotification() {
        NotificationEntity entity = new NotificationEntity();
        entity.setId("1");
        entity.setMessage("Hello");
        entity.setRecipient("user@test.com");
        entity.setChannel("EMAIL");
        entity.setScheduledAt(LocalDateTime.now().plusMinutes(1));
        entity.setStatus("SCHEDULED");

        repository.save(entity);
        var found = repository.findById("1");

        assertThat(found).isPresent();
        assertThat(found.get().getMessage()).isEqualTo("Hello");
    }

}
