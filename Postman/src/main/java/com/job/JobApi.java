package com.job;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.job.services.JobService;
import com.job.model.Job;

@SuppressWarnings("serial")
public class JobApi extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String jobId = req.getParameter("id");
            Job job = JobService.getInstance().getJobById(Long.parseLong(jobId));
            JSONObject obj = new JSONObject();
            if (job != null) {
                obj.put("jobName", job.getJobName());
            } else {
                obj.put("error", "Job not found");
            }
            res.getWriter().append(obj.toString());
        } catch (Exception e) {
            res.getWriter().append("invalid");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            BufferedReader reader = req.getReader();
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            JSONObject obj = new JSONObject(requestBody.toString());
            Job m = new Job();
            m.setJobId(obj.getLong("id")); 
            m.setJobName(obj.getString("jobName"));
            m.setDescription(obj.getString("description"));
            JobService.getInstance().addJob(m);
            res.getWriter().append("Job added successfully");
        } catch (Exception e) {
            res.getWriter().append("Error adding job: " + e.getMessage());
        }
    }

}
