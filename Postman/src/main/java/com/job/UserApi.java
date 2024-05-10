package com.job;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.job.model.User;
import com.job.services.UserService;
public 
class UserApi extends HttpServlet {
    private UserService userService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equals(action)) 
        {
            handleRegistration(req, resp);
        } else if ("login".equals(action)) 
        {
            handleLogin(req, resp);
        } else 
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {
        String action = req.getParameter("action");

        if ("getDetails".equals(action)) 
        {
            handleGetDetails(req, resp);
        } else 
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void handleRegistration(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User newUser = new User(username, password);
            userService.registerUser(newUser);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) 
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User loggedInUser = userService.loginUser(username, password);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Login successful");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write(e.getMessage());
        }
    }

    private void handleGetDetails(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = userService.getUserDetails(username);

        if (user != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("User details: " + user.getUsername());
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("User not found");
        }
    }
}
