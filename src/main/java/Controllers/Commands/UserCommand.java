package Controllers.Commands;

import Controllers.Command;
import Models.User;
import Persistence.DBFacade;
import Persistence.IDBFacade;
import Service.EmailSender;
import Service.SecureCodeHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand extends Command {

    private IDBFacade dbf = new DBFacade();
    private SecureCodeHandler handler = SecureCodeHandler.getInstance();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userCmd = request.getParameter("usercmd");
        String view = null;
        User user = null;
        switch (userCmd) {
            case "login":
                user = login(request);
                break;
            case "register":
                user = register(request);
                break;
            case "validate":
                user = validate(request);
                break;
            case "resendValidation":
                user = resendValidation(request);
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

    private User login(HttpServletRequest request) throws Exception {
        String username = request.getParameter("l_username");
        String password = request.getParameter("l_password");
        return dbf.login(username, password);
    }

    private User register(HttpServletRequest request) throws Exception {
        String username = request.getParameter("r_username");
        String email = request.getParameter("r_email");
        String password = request.getParameter("r_password");
        User user = dbf.register(username, email, password);
        if (user != null) {
            EmailSender.sendValidationEmail(email, username);
        }
        return user;
    }

    private User validate(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String code = request.getParameter("code");
        boolean isValid = handler.validateCode(user.getUsername(), code);
        if (isValid) {
            user.setValidated(true);
            dbf.updateValidatedUser(user);
        }
        return user;
    }

    private User resendValidation(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        EmailSender.sendValidationEmail(user.getEmail(), user.getUsername());
        return user;
    }
}
