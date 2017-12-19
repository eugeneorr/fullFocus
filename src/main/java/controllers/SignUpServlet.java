package controllers;

import auth.Auth;
import cookie.Cake;
import model.ToDoList;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = null;
        try {
            username = Cake.getCookie("username", req).getValue();
        } catch (NullPointerException e) {
            System.out.println("Filed to get user cookie: " + e);
        }

        System.out.println("Getted from cook: " + username);

        if(username != null && !username.equals("")) {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("/mainTask/");
        } else {
            req.getSession().setAttribute("isSignUp", true);
            req.getSession().setAttribute("haveAccountText", "Already have an account?");
            req.getSession().setAttribute("error", "");
            req.getRequestDispatcher("/views/sign.jsp").forward(req, resp);
        }

    }

}
