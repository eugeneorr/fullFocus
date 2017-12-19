package controllers;

import auth.Auth;
import cookie.Cake;
import model.ToDoList;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class MainTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = null;
        try {
            username = Cake.getCookie("username", req).getValue();
        } catch (NullPointerException e) {
            System.out.println("Falied to get user cookie: " + e);
        }
        if(username != null && !username.equals("")) {
            req.getSession().setAttribute("username", username);
            req.getRequestDispatcher("/views/mainTask.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("isSignUp", true);
            req.getSession().setAttribute("haveAccountText", "Already have an account?");
            req.getSession().setAttribute("error", "");
            req.getRequestDispatcher("/views/sign.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isSignUp = Boolean.parseBoolean(req.getParameter("isSignUp"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (isSignUp) {
            if (Auth.auth.isVacantUsername(username)) {
                Cake.refreshAuthCookie(username, resp);
                req.getSession().setAttribute("username", username);
                ToDoList.toDoList.addUser(new User(username));
                Auth.auth.addUser(username, password, false);
                req.getRequestDispatcher("/views/mainTask.jsp").forward(req, resp);
            } else {
                if (username == null || username.equals("")) {
                    req.getSession().setAttribute("error", "Invalid username.");
                } else {
                    req.getSession().setAttribute("error", "User with login \"" + username + "\" is already exists.");
                }
                req.getRequestDispatcher("/views/sign.jsp").forward(req, resp);
            }
        } else if (Auth.auth.authenticate(username, password)) {
            Cake.refreshAuthCookie(username, resp);
            req.getSession().setAttribute("username", username);
            req.getRequestDispatcher("/views/mainTask.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("error", "Failed to authenticate.");
            req.getSession().setAttribute("isSignUp", false);
            req.getRequestDispatcher("/views/sign.jsp").forward(req, resp);
        }
        ToDoList.toDoList.saveData("toDoList.json");
    }
}
