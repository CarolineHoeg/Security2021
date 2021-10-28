package Controllers.Commands;

import Controllers.Command;
import Models.User;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("r_username");
        String password = request.getParameter("r_password");
        User user = dbf.register(username, password);
        request.setAttribute("user", user);
        return "userpage";
    }
}
