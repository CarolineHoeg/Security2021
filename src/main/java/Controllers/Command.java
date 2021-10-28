package Controllers;

import Controllers.Commands.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterCommand());
        commands.put("create_forum", new CreateForumCommand());
        commands.put("forumpage", new ForumPageCommand());
        commands.put("userpage", new UserPageCommand());
    }

    static Command from(HttpServletRequest request) {
        String cmd = request.getParameter("cmd");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(cmd, new UnknownCommand());
    }

    protected abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}