package Controllers.Commands;

import Controllers.Command;
import Models.Comment;
import Models.Forum;
import Models.User;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownServiceException;
import java.util.List;

public class ViewPageCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        if (page.equals("forumpage")) {
            List<Forum> forums = dbf.getAllForums();
            request.getSession().setAttribute("forums", forums);
        }
        return page;
    }
}
