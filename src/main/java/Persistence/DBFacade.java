package Persistence;

import Models.User;
import java.sql.SQLException;

public class DBFacade implements IDBFacade{

    private UserMapper uMapper;

    @Override
    public User login(String username, String password) throws Exception {
        try {
            uMapper = UserMapper.getInstance();
            return uMapper.login(username, password);
        } catch (Exception e) {
            throw new Exception("Username or password incorrect.");
        }
    }

    @Override
    public User register(String username, String password) throws Exception {
        User user = new User(username, password);
        try {
            uMapper = UserMapper.getInstance();
            uMapper.register(user);
            return user;
        } catch (SQLException e) {
            throw new Exception("Username already in use.");
        }
    }
}
