package Controllers.Commands;

import Controllers.Command;
import Models.User;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userCmd = request.getParameter("usercmd");
        String view = null;
        User user = null;
        switch (userCmd) {
            case "login":
                String username = request.getParameter("l_username");
                String password = request.getParameter("l_password");
                user = dbf.login(username, password);
                break;
            case "register":
                username = request.getParameter("r_username");
                password = request.getParameter("r_password");
                user = dbf.register(username, password);
                break;
            case "logout":
                request.getSession().invalidate();
                view = "index";
                break;
            default:
                throw new Exception("Something went wrong.");
        }
        if (user != null) {
            request.getSession().setAttribute("user", user);
            view = "userpage";
        }
        return view;
    }
}
