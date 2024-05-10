package com.job;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.job.model.Organization;
import com.job.services.OrganizationService;

public class OrganizationApi extends HttpServlet {
    private OrganizationService organizationService;
    
    public void init() throws ServletException {
        super.init();
        organizationService = new OrganizationService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Name and description parameters are required");
            return;
        }

        Organization organization = new Organization(name, description);
        organizationService.addOrganization(organization); 
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("Organization created successfully");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Organization> organizations = organizationService.getAllOrganizations();
        resp.setContentType("application/json");

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < organizations.size(); i++) {
            Organization org = organizations.get(i);
            json.append("{");
            json.append("\"name\": \"" + org.getName() + "\",");
            json.append("\"description\": \"" + org.getDescription() + "\"");
            json.append("}");
            if (i < organizations.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        resp.getWriter().write(json.toString());
    }
}
