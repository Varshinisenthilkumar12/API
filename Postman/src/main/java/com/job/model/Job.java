package com.job.model;
import java.util.Random;

public class Job {
	private String jobName;
	private String description;
	private long jobId;
	
	Job(){
		Random rand = new Random();
		int rand_int1 = rand.nextInt(1000);
		this.jobId= (long) rand_int1;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getJobId() {
		return jobId;
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void add(Job job) {
		
	}

}
