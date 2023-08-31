package servlet;
import service.UserService;
import validate.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService service = new UserService();
    private final Validate validate = new Validate();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (service.canCreate(name)) {
            if(validate.validateLogin(name) && validate.validatePassword(password)){
                service.createUser(name, password);
                resp.getWriter().println(String.format("User [%s] created", name));
            } else {
                resp.getWriter().println("Login or password is not valid");
            }
        } else {
            resp.getWriter().println("User with this name already exist. Enter unique name");
        }
    }
}
