package dev.andreisima.notification_scheduler.adapters.in.web;

import dev.andreisima.notification_scheduler.adapters.out.web.jsonapi.JsonApiResponse;
import dev.andreisima.notification_scheduler.application.NotificationService;
import dev.andreisima.notification_scheduler.application.mapper.NotificationMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @Operation(summary = "Schedule a new notification")
    @PostMapping
    public ResponseEntity<JsonApiResponse<Map<String, String>>> schedule(
            @RequestBody ScheduleNotificationRequest request
    ) {
        var notification = NotificationMapper.fromRequest(request);
        service.schedule(notification);

        var response = new JsonApiResponse<>(
                "notification",
                notification.getId(),
                Map.of("message", "Scheduled successfully")
        );

        return ResponseEntity.ok(response);
    }

}
