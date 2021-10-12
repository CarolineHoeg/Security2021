package Controllers.Commands;

import Controllers.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        return "Unknown command. Please contact IT.";
    }
}
