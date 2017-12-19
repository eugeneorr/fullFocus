package controllers.backend;

import model.ToDoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String sharedListUsername = req.getParameter("sharedListUsername");
        String stringTaskIndex = req.getParameter("taskIndex");
        String stringGroupIndex = req.getParameter("groupIndex");
        if (stringTaskIndex == null && sharedListUsername == null) {
            Integer groupIndex = Integer.parseInt(stringGroupIndex);
            ToDoList.toDoList.getUser(username).removeGroup(groupIndex);
            req.getSession().setAttribute("groupIndex", 0);
            resp.sendRedirect("/list/");
        } else if (sharedListUsername == null) {
            Integer groupIndex = Integer.parseInt(stringGroupIndex);
            Integer taskIndex = Integer.parseInt(stringTaskIndex);
            ToDoList.toDoList.getUser(username).getGroups().get(groupIndex).removeTask(taskIndex);
            req.getSession().setAttribute("groupIndex", groupIndex);
            resp.sendRedirect("/list/");
        } else {
            System.out.println("SSUsername:" + sharedListUsername);
            System.out.println("username: " + username);
            ToDoList.toDoList.getUser(username).removeSharedUser(sharedListUsername);
            resp.sendRedirect("/profile/");
        }
        ToDoList.toDoList.saveData("toDoList.json");
    }
}
