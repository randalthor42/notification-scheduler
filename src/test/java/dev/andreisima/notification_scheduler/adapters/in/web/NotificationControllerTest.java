package dev.andreisima.notification_scheduler.adapters.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.andreisima.notification_scheduler.application.NotificationService;
import dev.andreisima.notification_scheduler.application.mapper.NotificationMapper;
import dev.andreisima.notification_scheduler.domain.Notification;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
@Import(NotificationControllerTest.MockConfig.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotificationService notificationService;

    @Test
    void shouldScheduleNotificationSuccessfully() throws Exception {
        var request = new ScheduleNotificationRequest();
        request.setMessage("Test message");
        request.setRecipient("test@example.com");
        request.setChannel("EMAIL");
        request.setScheduledAt(LocalDateTime.now().plusMinutes(10));

        var fakeId = UUID.randomUUID();
        var mockNotification = mock(Notification.class);
        when(mockNotification.getId()).thenReturn(String.valueOf(fakeId));

        try (MockedStatic<NotificationMapper> mocked = mockStatic(NotificationMapper.class)) {
            mocked.when(() -> NotificationMapper.fromRequest(any())).thenReturn(mockNotification);
            doNothing().when(notificationService).schedule(mockNotification);

            mockMvc.perform(post("/api/notifications")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.type").value("notification"))
                    .andExpect(jsonPath("$.data.id").value(fakeId.toString()))
                    .andExpect(jsonPath("$.data.attributes.message").value("Scheduled successfully"));
        }
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public NotificationService notificationService() {
            return mock(NotificationService.class);
        }
    }
}
