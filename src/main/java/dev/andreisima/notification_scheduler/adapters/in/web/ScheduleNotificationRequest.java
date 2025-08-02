package dev.andreisima.notification_scheduler.adapters.in.web;

import dev.andreisima.notification_scheduler.common.validators.EnumValidator;
import dev.andreisima.notification_scheduler.domain.DeliveryChannel;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ScheduleNotificationRequest {

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @NotBlank(message = "Recipient cannot be blank")
    private String recipient;

    @NotBlank(message = "Channel is required")
    @EnumValidator(enumClass = DeliveryChannel.class, message = "Channel must be one of: EMAIL, SMS, IN_APP")
    private String channel;

    @NotNull(message = "Scheduled time is required")
    @Future(message = "Scheduled time must be in the future")
    private LocalDateTime scheduledAt;

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getChannel() {
        return channel;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }
}
