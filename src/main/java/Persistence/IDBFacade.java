package Persistence;

import Models.User;

public interface IDBFacade {
    public User login(String username, String password) throws Exception;
    public User register(String username, String password) throws Exception;
}
