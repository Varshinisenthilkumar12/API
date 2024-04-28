package com.job;

import java.io.IOException;
import java.io.BufferedReader;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

@SuppressWarnings("serial")
public class JobApi extends HttpServlet {
	public void doGet(HttpServletRequestWrapper req, HttpServletResponseWrapper res)throws IOException{
		try{
			String jobId=req.getParameter("id");
			
			Job job=JobService.getInstance().getJobById(Long.parseLong(jobId));
			JSONObject  obj = new JSONObject();
			obj.put("jobName",job.getJobName());
			
			res.getWriter().append(obj.toString());
			
		}catch(Exception e) {
			res.getWriter().append("invalid");
		}
}

	public void doPost(HttpServletRequestWrapper req,HttpServletResponseWrapper res)
		try {
		BufferedReader reader = req.getReader();
		StringBuilder requestBody = new StringBuilder();
		
		String line;
		while(line = reader.readLine()) !=null){
			requestBody.append(line);
		}
		
		JSONObject  obj = new JSONObject(requestBody);
		
		Job m = new Job();
		m.setJobName(obj.getString("job name"));
		m.setDescription(obj.getString("desc"));
		
		JobService.getInstance().addJob(m);
		
		
		}catch(Exception e) {
			
		}
}
