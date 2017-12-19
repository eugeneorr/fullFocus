package controllers;

import model.ToDoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WatchUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("watchUsername");
        req.getSession().setAttribute("watchUser", ToDoList.toDoList.getUser(username));
        String groupIndexString = req.getParameter("groupIndex");
        Integer groupIndex = Integer.parseInt(groupIndexString == null ? "0" : groupIndexString);
        req.getSession().setAttribute("groupIndex", groupIndex);
        req.getRequestDispatcher("/views/watchUser.jsp").forward(req, resp);
    }

}
