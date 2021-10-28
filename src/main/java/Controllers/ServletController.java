package Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletController", urlPatterns = {"/ServletController"})
public class ServletController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        try {
            Command cmd = Command.from(request);
            String view = cmd.execute(request, response);
            if(view == "index") {
                rd = request.getRequestDispatcher("index.jsp");
            } else {
                rd = request.getRequestDispatcher("/WEB-INF/" + view + ".jsp");
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage() + " Please try again.");
            rd = request.getRequestDispatcher("index.jsp");
        }
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            //TODO log it if forward fails
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

}
