package Controllers.Commands;

import Controllers.Command;
import Models.User;
import Persistence.DBFacade;
import Persistence.IDBFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("l_username");
        String password = request.getParameter("l_password");
        User user = dbf.login(username, password);
        request.getSession().setAttribute("user", user);
        return "userpage";
    }
}
