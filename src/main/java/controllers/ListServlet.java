package controllers;

import cookie.Cake;
import model.Task;
import model.ToDoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get Main Task
        String mainTask = req.getParameter("mainTask");

        if (mainTask == null) {
            mainTask = Cake.getMainTask(req);
        } else {
            Cake.addMainTaskCookie(mainTask, resp);
        }

        // Set username & main task
        String username = (String) req.getSession().getAttribute("username");
        req.getSession().setAttribute("mainFocus", mainTask == null ? "" : mainTask);
        req.getSession().setAttribute("user", ToDoList.toDoList.getUser(username));

        String newGroupName = req.getParameter("newGroupName");
        if (newGroupName != null) {
            ToDoList.toDoList.getUser(username).addGroup(newGroupName);
        }

        String newTaskText = req.getParameter("newTaskText");
        String newTaskGroupIndex = req.getParameter("newTaskGroupIndex");
        if (newTaskText != null && newTaskGroupIndex != null) {
            int groupIndex = Integer.parseInt(newTaskGroupIndex);
            ToDoList.toDoList.getUser(username).getGroups().get(groupIndex).addTask(new Task(newTaskText));
        }

        String editedTaskIndex = req.getParameter("editedTaskIndex");
        String editedTaskText = req.getParameter("editedTaskText");
        if (editedTaskText != null && newTaskGroupIndex != null && editedTaskIndex != null) {
            int groupIndex = Integer.parseInt(newTaskGroupIndex);
            ToDoList.toDoList.getUser(username).getGroups().get(groupIndex).updateTask(Integer.parseInt(editedTaskIndex), editedTaskText);
        }

        req.getSession().setAttribute("user", ToDoList.toDoList.getUser(username));

        // Set group index
        String groupIndexString = req.getParameter("groupIndex");
        Integer groupIndex = newTaskGroupIndex == null ? Integer.parseInt(groupIndexString == null ? "0" : groupIndexString) : Integer.parseInt(newTaskGroupIndex);
        req.getSession().setAttribute("groupIndex", groupIndex);

        resp.sendRedirect("/list/");

        ToDoList.toDoList.saveData("toDoList.json");
    }
}

