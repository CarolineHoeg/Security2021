package Controllers.Commands;

import Controllers.Command;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String commentCmd = request.getParameter("commentcmd");
        String view;
        switch (commentCmd) {
            case "create":
                String username = request.getParameter("u_name");
                int forumId = Integer.parseInt(request.getParameter("f_id"));
                String content = request.getParameter("content");
                dbf.createComment(username, forumId, content);
                view = "userpage";
                break;
            default:
                throw new Exception("Something went wrong.");
        }
        return view;
    }
}
