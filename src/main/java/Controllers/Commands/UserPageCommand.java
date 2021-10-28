package Controllers.Commands;

import Controllers.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPageCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "userpage";
    }
}
