package Models;

import java.security.SecureRandom;

public class ValidationCode {

    private String username;
    private String code;
    private boolean isValid;

    public ValidationCode(String username, String code, boolean isValid) {
        this.username = username;
        this.code = code;
        this.isValid = isValid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
