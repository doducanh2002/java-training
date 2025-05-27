package org.aibles.quartz.service;

import java.text.ParseException;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;

public interface QuartzService {

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @param jobClass - class that implement job logic
     * @param triggerTime - must be in format yyyy-MM-dd HH:mm
     * @throws Exception
     */
    void createTimeJob(String jobGroup, String jobId, Class<? extends Job> jobClass, String triggerTime) throws ParseException, SchedulerException;

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @param jobClass - class that implement job logic
     * @param cron - cron to run schedule job
     * @throws SchedulerException
     */
    void createCronJob(String jobGroup, String jobId, Class<? extends Job> jobClass, String cron) throws SchedulerException;

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @throws SchedulerException
     */
    void deleteJob(String jobGroup, String jobId) throws SchedulerException;

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @return boolean
     * @throws SchedulerException
     */
    boolean checkExists(String jobGroup, String jobId) throws SchedulerException;

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @return detail of the job
     * @throws SchedulerException
     */
    JobDetail getJobDetail(String jobGroup, String jobId) throws SchedulerException;

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @throws SchedulerException
     */
    void pauseJob(String jobGroup, String jobId) throws SchedulerException;

    /**
     *
     * @param jobGroup - Name of the job
     * @param jobId - id of that job, so we can query by id to get job content
     * @throws SchedulerException
     */
    void resumeJob(String jobGroup, String jobId) throws SchedulerException;
}
