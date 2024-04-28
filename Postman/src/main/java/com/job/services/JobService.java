package com.job.services;

import java.util.ArrayList;
import java.util.List;
import com.job.model.Job;

public class JobService {
	private static JobService jobService;
	
	private static ArrayList<Job>jobs = new ArrayList<Job>();
	
	public static JobService getInstance() {
		if(jobService == null) {
			jobService= new JobService();
			return jobService;
			
	}else {
		return jobService;
	}
		
	}
	
	public boolean addJob(Job job) {
		if(job.getJobName()==null) {
			return false;
		}else {
			jobs.addJob(job);
		}
		return true;
	}
	public Job getJobById(Long id) {
		for(Job job:jobs) {
			if(job.getJobId()==id) {
				return job;
			}
		}
		return null;
	}
}
