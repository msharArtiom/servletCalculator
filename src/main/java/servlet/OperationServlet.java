package servlet;

import entity.Operation;
import entity.User;
import service.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OperationServlet", urlPatterns = "/calculator")
public class OperationServlet extends HttpServlet {

    private final OperationService service = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("currentUser");

        double num1 = Double.parseDouble(req.getParameter("num1"));
        double num2 = Double.parseDouble(req.getParameter("num2"));
        String type = req.getParameter("type");

        Operation operation = new Operation(num1, num2, type, user.getId());

        try {
            Operation result = service.calculate(operation);
            resp.getWriter().println(user.getName() + " " +num1 + " " + type + " " + num2 + " = " + result.getResult());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
