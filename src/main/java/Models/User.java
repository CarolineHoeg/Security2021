package Models;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class User {
    private String username;
    private String password;
    private static String pepper;

    public User(String username, String password) {
        this.username = username;
        if(pepper == null) { setPepper(); }
        this.password = BCrypt.hashpw(pepper + password, BCrypt.gensalt(16));
    }

    private static void setPepper() {
        InputStream in = User.class.getClassLoader().getResourceAsStream("pepper.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            pepper = props.getProperty("pepper");
        } catch (IOException e) {
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
