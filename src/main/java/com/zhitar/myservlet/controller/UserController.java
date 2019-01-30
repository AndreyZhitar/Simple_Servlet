package com.zhitar.myservlet.controller;

import com.zhitar.myservlet.dao.UserDAO;
import com.zhitar.myservlet.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String INSERT_OR_EDIT = "/user.jsp";

    private static final String LIST_USER = "/listUser.jsp";

    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            userDAO.deleteUser(id);
            forward = LIST_USER;
            req.setAttribute("users", userDAO.getAll());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(req.getParameter("id"));
            User user = userDAO.getUserById(id);
            req.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            req.setAttribute("users", userDAO.getAll());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(req.getParameter("email"));
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            userDAO.addUser(user);
        } else {
            user.setId(Integer.parseInt(id));
            userDAO.updateUser(user);
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_USER);
        req.setAttribute("users", userDAO.getAll());
        view.forward(req, resp);
    }
}
