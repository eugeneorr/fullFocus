package controllers.backend;

import cookie.Cake;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cake.clearAuthCookie(req, resp);
        HttpSession session = req.getSession(false);
        session.invalidate();
        resp.sendRedirect("/signUp/");
    }
}
