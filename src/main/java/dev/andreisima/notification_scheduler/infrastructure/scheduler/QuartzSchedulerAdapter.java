package dev.andreisima.notification_scheduler.infrastructure.scheduler;

import dev.andreisima.notification_scheduler.domain.Notification;
import dev.andreisima.notification_scheduler.infrastructure.scheduler.job.SendNotificationJob;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class QuartzSchedulerAdapter {

    private final Scheduler scheduler;

    public QuartzSchedulerAdapter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(Notification notification) {
        try {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("notification", notification);

            JobDetail jobDetail = JobBuilder.newJob(SendNotificationJob.class)
                    .withIdentity("notification-" + notification.getId(), "notifications")
                    .usingJobData(dataMap)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .startAt(Date.from(notification.getScheduledAt().atZone(ZoneId.systemDefault()).toInstant()))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to schedule job", e);
        }
    }
}
