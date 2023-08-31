package servlet;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (service.getUser(name, password).isPresent()){
            User user = service.getUser(name);
            req.getSession(true).setAttribute("currentUser", user);
            resp.sendRedirect("/");

        } else resp.getWriter().println("Invalid login or password. Try again");
    }
}
