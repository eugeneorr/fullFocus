package controllers;

import auth.Auth;
import cookie.Cake;
import model.ToDoList;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = Cake.getUsername(req);
        String newSharedUsername = req.getParameter("newSharedUsername");
        if (newSharedUsername != null) {
            if (!Auth.auth.isVacantUsername(newSharedUsername)) {
                ToDoList.toDoList.getUser(username).addSharedUser(newSharedUsername);
            } else {
                req.getSession().setAttribute("error", "User with name \"" + newSharedUsername + "\"" + "doesn't exist.");
                req.getRequestDispatcher("/views/shareUser.jsp").forward(req, resp);
            }
        }
        HashSet<String> sharedUsers = ToDoList.toDoList.getUser(username).getSharedUsers();
        ArrayList<User> usersToWatch = new ArrayList<>();
        for (User user: ToDoList.toDoList.getUsers()) {
            if (user.getSharedUsers().contains(username)) {
                usersToWatch.add(user);
            }
        }
        req.getSession().setAttribute("sharedUsers", sharedUsers);
        req.getSession().setAttribute("usersToWatch", usersToWatch);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);

        ToDoList.toDoList.saveData("toDoList.json");
    }
}
