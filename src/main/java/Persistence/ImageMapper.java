package Persistence;

import Dependencies.CloudinaryConnection;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageMapper {

    private static ImageMapper instance;
    private static Cloudinary cloudinary;
    private static CloudinaryConnection connection = new CloudinaryConnection();

    public static ImageMapper getInstance() {
        if (instance == null) { instance = new ImageMapper(); }
        cloudinary = connection.connect();
        return instance;
    }

    public String upload(File file) throws Exception {
        Map result;
        try {
            result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new Exception("Could not upload image.");
        }
        return (String) result.get("url");
    }

}
