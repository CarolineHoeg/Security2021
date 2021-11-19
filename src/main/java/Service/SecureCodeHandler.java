package Service;

import Models.ValidationCode;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SecureCodeHandler {

    private static SecureCodeHandler instance;
    private static SecureRandom random = new SecureRandom();
    private static final String chars = "0123456789abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static ArrayList<ValidationCode> codes = new ArrayList<>();

    private SecureCodeHandler() {
    }

    public static SecureCodeHandler getInstance() {
        if (instance == null) {
            instance = new SecureCodeHandler();
        }
        return instance;
    }

    public String generateCode(String username) {
        String codeStr = random.ints(18, 0,
                chars.length()).mapToObj(i -> chars.charAt(i))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        ValidationCode code = new ValidationCode(username, codeStr, true);
        codes.add(code);
        Thread thread = new Thread(invalidateUnusedCodes(code));
        thread.start();
        return code.getCode();
    }

    public boolean validateCode(String username, String userCode) {
        for (ValidationCode code: codes) {
            if (code.getUsername().equals(username)
                    && code.getCode().equals(userCode)
                    && code.isValid()) {
                return true;
            }
        }
        return false;
    }

    private Runnable invalidateUnusedCodes(ValidationCode code) {
        return () -> {
            try {
                TimeUnit.MINUTES.sleep(10);
                code.setValid(false);
                codes.remove(code);
            } catch (InterruptedException e) {
                //TODO LOG
            }
        };
    }
}
