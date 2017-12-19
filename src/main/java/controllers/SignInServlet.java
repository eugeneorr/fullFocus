package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("isSignUp", false);
        req.getSession().setAttribute("haveAccountText", "Don't have an account?");
        req.getSession().setAttribute("error", "");
        req.getRequestDispatcher("/views/sign.jsp").forward(req, resp);
    }

}
