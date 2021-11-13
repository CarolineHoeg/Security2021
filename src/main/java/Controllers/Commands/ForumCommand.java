package Controllers.Commands;

import Controllers.Command;
import Service.ImageFileHandler;
import Models.Forum;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;

public class ForumCommand extends Command {

    private IDBFacade dbf = new DBFacade();

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forumCmd = request.getParameter("forumcmd");
        String view;
        switch (forumCmd) {
            case "search":
                String searchStr = request.getParameter("search");
                List<Forum> forums = dbf.searchForums(searchStr);
                request.getSession().setAttribute("forums", forums);
                view = "forumpage";
            break;
            case "create":
                String username = request.getParameter("u_name");
                String title = request.getParameter("f_title");
                String content = request.getParameter("f_content");
                Part imagePart = request.getPart("f_image");
                if (imagePart.getSize() > 0) {
                    String imageUrl = ImageFileHandler.uploadImage(imagePart);
                    dbf.createForum(username, title, content, imageUrl);
                } else {
                    dbf.createForum(username, title, content, null);
                }
                view = "userpage";
                break;
            default:
                throw new Exception("Something went wrong.");
        }
        return view;
    }
}
