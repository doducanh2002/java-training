package org.aibles.quartz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

@Slf4j
public class QuartzServiceImpl implements QuartzService {

    private final Scheduler scheduler;

    public QuartzServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void createTimeJob(String jobGroup, String jobId, Class<? extends Job> jobClass, String triggerTime) throws ParseException, SchedulerException {
        log.info("(createTimeJob)jobGroup : {}, jobId: {}, triggerTime: {}", jobGroup, jobId, triggerTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime = dateFormat.parse(triggerTime);
        Date now = new Date();
        if (startTime.before(now)) {
            log.warn("(createTimeTrigger) Trigger time {} is in the past", triggerTime);
        } else {
            JobDetail jobDetail = createJobDetail(jobGroup, jobId, jobClass);
            Trigger trigger = createTimeTrigger(jobGroup, jobId, triggerTime);
            if (scheduler.checkExists(jobDetail.getKey())) {
                scheduler.rescheduleJob(trigger.getKey(), trigger);
            } else {
                scheduler.scheduleJob(jobDetail, trigger);
            }
        }
    }

    @Override
    public void createCronJob(String jobGroup, String jobId, Class<? extends Job> jobClass, String cron) throws SchedulerException {
        log.info("(createCronJob)jobGroup : {}, jobId: {}, cron: {}", jobGroup, jobId, cron);
        JobDetail jobDetail = createJobDetail(jobGroup, jobId, jobClass);
        Trigger trigger = createCronTrigger(jobGroup, jobId, cron);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void deleteJob(String jobGroup, String jobId) throws SchedulerException {
        log.info("(deleteJob)jobGroup : {}, jobId: {}", jobGroup, jobId);
        scheduler.deleteJob(JobKey.jobKey(jobId, jobGroup));
    }

    @Override
    public boolean checkExists(String jobGroup, String jobId) throws SchedulerException {
        log.info("(checkExists)jobGroup : {}, jobId: {}", jobGroup, jobId);
        return scheduler.checkExists(JobKey.jobKey(jobId, jobGroup));
    }

    @Override
    public JobDetail getJobDetail(String jobGroup, String jobId) throws SchedulerException {
        log.info("(getJobDetail)jobGroup : {}, jobId: {})", jobGroup, jobId);
        return scheduler.getJobDetail(JobKey.jobKey(jobId, jobGroup));
    }

    @Override
    public void pauseJob(String jobGroup, String jobId) throws SchedulerException {
        log.info("(pauseJob)jobGroup : {}, jobId: {}", jobGroup, jobId);
        scheduler.pauseJob(JobKey.jobKey(jobId, jobGroup));
    }

    @Override
    public void resumeJob(String jobGroup, String jobId) throws SchedulerException {
        log.info("(resumeJob)jobGroup : {}, jobId: {}", jobGroup, jobId);
        scheduler.resumeJob(JobKey.jobKey(jobId, jobGroup));
    }

    private JobDetail createJobDetail(String jobGroup, String jobId, Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobId, jobGroup)
                .usingJobData("jobId", jobId)
                .storeDurably()
                .build();
    }

    private Trigger createTimeTrigger(String jobGroup, String jobId, String triggerTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime = dateFormat.parse(triggerTime);

        return TriggerBuilder.newTrigger()
                .withIdentity(jobId + "_trigger", jobGroup)
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();

    }

    private Trigger createCronTrigger(String jobGroup, String jobId, String cron) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobId + "_trigger", jobGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }
}
