package com.job.model;

public class Job {
    private Long jobId;
    private String jobName;
    private String description;

    public Job() {}

    public Job(Long jobId, String jobName, String description) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.description = description;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
