package dev.andreisima.notification_scheduler.infrastructure.scheduler.job;

import dev.andreisima.notification_scheduler.domain.Notification;
import dev.andreisima.notification_scheduler.domain.ports.NotifierPort;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendNotificationJob implements Job {

    private static Map<String, NotifierPort> notifierMap;

    @Autowired
    public void setNotifierMap(Map<String, NotifierPort> map) {
        SendNotificationJob.notifierMap = map;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Notification notification = (Notification) context.getMergedJobDataMap().get("notification");
            String channel = notification.getChannel().name().toLowerCase();

            NotifierPort notifier = notifierMap.get(channel);
            if (notifier != null) {
                notifier.send(notification);
            } else {
                System.err.printf("No notifier registered for channel '%s'%n", channel);
            }
        } catch (Exception e) {
            throw new JobExecutionException("Failed to execute SendNotificationJob", e);
        }
    }
}
