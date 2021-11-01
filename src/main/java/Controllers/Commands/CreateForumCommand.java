package Controllers.Commands;

import Controllers.Command;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateForumCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("u_name");
        String title = request.getParameter("f_title");
        String content = request.getParameter("f_content");
        dbf.createForum(username, title, content);
        return "userpage";
    }
}
