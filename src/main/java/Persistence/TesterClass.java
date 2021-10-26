package Persistence;

import Models.User;

public class TesterClass {
    private static IDBFacade dbf = new DBFacade();

    public static void main(String[] args) throws Exception {
        String username = "user1";
        String password = "password123";
        //User uRegister = dbf.register(username, password);
        //System.out.println(uRegister);
        User uLogin = dbf.login(username, password);
        System.out.println(uLogin);
    }
}
