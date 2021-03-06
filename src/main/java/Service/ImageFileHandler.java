package Service;

import Persistence.DBFacade;
import Persistence.IDBFacade;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.Part;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ImageFileHandler {

    private static IDBFacade dbf = new DBFacade();
    private static final String UPLOAD_DIR = "img";
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static String working_dir = null;
    private static final Logger LOG = LogManager.getLogger(ImageFileHandler.class);

    public static String uploadImage(Part imagePart) throws Exception {
        String imageUrl = null;
        File temp = uploadTempFile(imagePart);
        String mimeType = getMimeType(temp);
        if (mimeType != null) {
            if (mimeType.equalsIgnoreCase("png")
                    || mimeType.equalsIgnoreCase("jpg")) {
                imageUrl = dbf.uploadImage(temp);
            }
        }
        safelyRemoveFile(temp.toPath());
        return imageUrl;
    }

    private static String getMimeType(File temp) throws Exception {
        String mimeType = null;
        try (ImageInputStream iis = ImageIO.createImageInputStream(temp)) {
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            while (iter.hasNext()) {
                ImageReader reader = iter.next();
                mimeType = reader.getFormatName();
            }
        }
        return mimeType;
    }

    private static File uploadTempFile(Part part) throws Exception {
        if (working_dir == null) { setWorkingDir(); }
        String fileName = UUID.randomUUID().toString();
        File uploadFolder = new File(working_dir + File.separator + UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        part.write(working_dir + File.separator + UPLOAD_DIR
                + File.separator + fileName);

        return new File(working_dir + File.separator
                + UPLOAD_DIR + File.separator + fileName);
    }

    private static void setWorkingDir() {
        if(OS.contains("win") || OS.contains("mac")){
            working_dir = System.getProperty("user.dir");
        }else if(OS.contains("nix") || OS.contains("nux") || OS.contains("aix")){
            working_dir = System.getProperty("catalina.base");
        } else {
            working_dir = "";
        }
    }

    private static void safelyRemoveFile(Path p) {
        try {
            if (p != null) {
                if (!Files.deleteIfExists(p)) {
                    Files.write(p, "-".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
                }
            }
        } catch (Exception e) {
            LOG.warn("Cannot safely remove file! ", e);
        }
    }

}
