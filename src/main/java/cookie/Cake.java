package cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Cake {

    public static void clearAuthCookie(HttpServletRequest req, HttpServletResponse resp) {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("username") || cookie.getName().equals("mainTask")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
    }

    public static Cookie getCookie(String key, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getMainTask(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mainTask")) {
                    try {
                        return cookie.getValue().replaceAll("_", " ");
                    } catch (NullPointerException e) {
                        System.out.println("Failed to get mainTask in cake: " + e);
                    }
                    return "";
                }
            }
        }
        return "";
    }

    public static String getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    try {
                        return cookie.getValue();
                    } catch (NullPointerException e) {
                        System.out.println("Failed to get mainTask in cake: " + e);
                    }
                    return "";
                }
            }
        }
        return "";
    }

    public static void refreshAuthCookie(String username, HttpServletResponse resp) throws IOException {
        Cookie loginCookie = new Cookie("username", username);
        loginCookie.setPath("/");
        resp.addCookie(loginCookie);
    }

    public static void addMainTaskCookie(String mainTask, HttpServletResponse resp) throws IOException {
        String mainTaskWithoutSpaces = mainTask.replaceAll(" ", "_");
        Cookie mainTaskCookie = new Cookie("mainTask", mainTaskWithoutSpaces);
        mainTaskCookie.setPath("/");
        resp.addCookie(mainTaskCookie);
    }

}
