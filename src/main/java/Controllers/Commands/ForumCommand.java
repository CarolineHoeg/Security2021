package Controllers.Commands;

import Controllers.Command;
import Models.Forum;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForumCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forumCmd = request.getParameter("forumcmd");
        String view;
        switch (forumCmd) {
            case "create":
                String username = request.getParameter("u_name");
                String title = request.getParameter("f_title");
                String content = request.getParameter("f_content");
                dbf.createForum(username, title, content);
                view = "userpage";
                break;
            default:
                throw new Exception("Something went wrong.");
        }
        return view;
    }
}
