package controllers;

import model.ToDoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEditTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newTaskGroupIndex = req.getParameter("newTaskGroupIndex");
        req.getSession().setAttribute("newTaskGroupIndex", Integer.parseInt(newTaskGroupIndex));
        if (req.getParameter("taskText") != null && req.getParameter("taskIndex") != null) {
            req.getSession().setAttribute("taskText", req.getParameter("taskText"));
            req.getSession().setAttribute("taskIndex", Integer.parseInt(req.getParameter("taskIndex")));
            req.getRequestDispatcher("/views/editTask.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/addTask.jsp").forward(req, resp);
        }
    }

}
