package Controllers.Commands;

import Controllers.Command;
import Models.Forum;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ForumPageCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Forum> forums = dbf.getAllForums();
        request.getSession().setAttribute("forums", forums);
        return "forumpage";
    }
}
