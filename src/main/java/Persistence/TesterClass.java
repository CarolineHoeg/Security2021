package Persistence;

import Models.User;

public class TesterClass {
    private static IDBFacade dbf = new DBFacade();

    public static void main(String[] args) throws Exception {
        String username = "test";
        String password = "test123";
        User user = dbf.register(username, password);
        System.out.println(user);

    }
}
