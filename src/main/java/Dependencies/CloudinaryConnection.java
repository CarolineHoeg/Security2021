package Dependencies;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CloudinaryConnection implements ICloudinaryConnection {

    private Cloudinary connection;
    private static String cloud_name;
    private static String api_key;
    private static String api_secret;

    @Override
    public Cloudinary connect() {
        if (connection == null) { setConnection(); }
        return connection;
    }

    @Override
    public void disconnect() {
    }

    private void setConnection() {
        getProperties();
        connection = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloud_name,
                "api_key", api_key,
                "api_secret", api_secret));
    }

    private void getProperties() {
        InputStream in = CloudinaryConnection.class.getClassLoader().getResourceAsStream("cloudinary.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            cloud_name = props.getProperty("cloud_name");
            api_key = props.getProperty("api_key");
            api_secret = props.getProperty("api_secret");
        } catch (IOException e) {
            //TODO
        }
    }
}
