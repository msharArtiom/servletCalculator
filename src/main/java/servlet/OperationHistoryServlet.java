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
import java.util.List;

@WebServlet(name = "OperationHistoryServlet", urlPatterns = "/history")
public class OperationHistoryServlet extends HttpServlet {

    OperationService service = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("currentUser");
        List<Operation> operations = service.getHistory(user);

        for (Operation operation: operations){
            resp.getWriter().println(operation);
        }
    }
}
