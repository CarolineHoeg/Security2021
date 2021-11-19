package Models;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class User {

    private String username;
    private String email;
    private String password;
    private boolean isValidated = false;
    private static String pepper;

    public User(String username, String password) throws Exception {
        this.username = username;
        pepper = setPepper();
        this.password = BCrypt.hashpw(pepper + password, BCrypt.gensalt(16));
    }

    public User(String username, String email, String password) throws Exception {
        this.username = username;
        this.email = email;
        pepper = setPepper();
        this.password = BCrypt.hashpw(pepper + password, BCrypt.gensalt(16));
    }

    private static String setPepper() throws Exception {
        InputStream in = User.class.getClassLoader().getResourceAsStream("pepper.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            return props.getProperty("pepper");
        } catch (IOException e) {
        }
        throw new Exception("Could not get pepper");
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(pepper + password, BCrypt.gensalt(16));
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public boolean validatePassword(String plainPass, String storedPass) {
        return BCrypt.checkpw(pepper + plainPass, storedPass);
    }

    public void setValidated(boolean validated) {
        this.isValidated = validated;
    }

    public boolean isValidated() { return isValidated; }
}
