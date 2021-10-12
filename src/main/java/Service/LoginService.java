package Service;

public class LoginService implements ILoginService {

    @Override
    public boolean verifyCredentials(String username, String password) {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public boolean isLoggedin() {
        return false;
    }
}
