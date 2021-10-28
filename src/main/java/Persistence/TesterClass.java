package Persistence;

import Models.Forum;
import Models.User;

public class TesterClass {
    private static IDBFacade dbf = new DBFacade();

    public static void main(String[] args) throws Exception {
        dbf.createForum("user1", "From test", "Lorem ipsum");
    }
}
