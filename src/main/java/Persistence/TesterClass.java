package Persistence;

import Models.Forum;
import Models.User;

import java.util.ArrayList;

public class TesterClass {
    private static IDBFacade dbf = new DBFacade();

    public static void main(String[] args) throws Exception {
        ArrayList<User> test = new ArrayList<>();
        System.out.println(test.size());
    }
}
