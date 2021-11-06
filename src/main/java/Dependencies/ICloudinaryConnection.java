package Dependencies;

import com.cloudinary.Cloudinary;

public interface ICloudinaryConnection {
    public Cloudinary connect();
    public void disconnect();
}
