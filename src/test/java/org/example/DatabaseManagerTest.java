package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class DatabaseManagerTest {
    @Test
    public void testAddAndGetAllUsers() {
        DatabaseManager databaseManager = new DatabaseManager();

        User user1 = new User(1, "a1", "Robert");
        User user2 = new User(2, "a2", "Martin");

        databaseManager.addUser(user1);
        databaseManager.addUser(user2);

        List<User> users = databaseManager.getAllUsers();
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(user1.toString(), users.get(0).toString());
        Assertions.assertEquals(user2.toString(), users.get(1).toString());
    }

    @Test
    public void testDeleteAllUsers() {
        DatabaseManager databaseManager = new DatabaseManager();

        User user1 = new User(1, "a1", "Robert");
        User user2 = new User(2, "a2", "Martin");

        databaseManager.addUser(user1);
        databaseManager.addUser(user2);

        databaseManager.deleteAllUsers();

        List<User> users = databaseManager.getAllUsers();
        Assertions.assertTrue(users.isEmpty());
    }
}