package Controllers.Commands;

import Controllers.Command;
import Models.Forum;
import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;
import java.io.File;

public class ForumCommand extends Command {

    private IDBFacade dbf = new DBFacade();
    private static final String UPLOAD_DIR = "img";
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static String working_dir = null;

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forumCmd = request.getParameter("forumcmd");
        String view;
        switch (forumCmd) {
            case "create":
                String username = request.getParameter("u_name");
                String title = request.getParameter("f_title");
                String content = request.getParameter("f_content");
                Part imagePart = request.getPart("f_image");
                if (imagePart.getSize() > 0) {
                    File file = uploadImage(imagePart);
                    String imageUrl = dbf.uploadImage(file);
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

    private File uploadImage(Part part) throws Exception {
        if (working_dir == null) { setWorkingDir(); }
        String fileName = UUID.randomUUID().toString();
        try {
            File uploadFolder = new File(working_dir + File.separator + UPLOAD_DIR);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }
            part.write(working_dir + File.separator + UPLOAD_DIR
                    + File.separator + fileName);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        return new File(working_dir + File.separator
                + UPLOAD_DIR + File.separator + fileName);
    }

    private void setWorkingDir() {
        if(OS.contains("win") || OS.contains("mac")){
            working_dir = System.getProperty("user.dir");
        }else if(OS.contains("nix") || OS.contains("nux") || OS.contains("aix")){
            working_dir = System.getProperty("catalina.base");
        } else {
            working_dir = "";
        }
    }
}
