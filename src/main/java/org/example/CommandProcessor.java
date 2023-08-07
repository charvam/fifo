package org.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class CommandProcessor implements Runnable {
    public static final String ADD = "Add";
    public static final String PRINT_ALL = "PrintAll";
    public static final String DELETE_ALL = "DeleteAll";
    public static final String CLOSE = "Close";

    private final BlockingQueue<String> commandQueue;
    private final DatabaseManager databaseManager;

    public CommandProcessor(BlockingQueue<String> commandQueue, DatabaseManager databaseManager) {
        this.commandQueue = commandQueue;
        this.databaseManager = databaseManager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String command = commandQueue.take();
                if (CLOSE.equals(command)) {
                    break;
                }
                processCommand(command);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0];
        System.out.println("command:" + command);
        switch (action) {
            case ADD -> {
                int userId = Integer.parseInt(parts[1]);
                String userGuid = parts[2];
                String userName = parts[3];
                User user = new User(userId, userGuid, userName);
                databaseManager.addUser(user);
            }
            case PRINT_ALL -> {
                List<User> users = databaseManager.getAllUsers();
                for (User u : users) {
                    System.out.println(u);
                }
            }
            case DELETE_ALL -> databaseManager.deleteAllUsers();
            case CLOSE -> databaseManager.closeConnection();
            default -> System.out.println("Unknown command: " + command);
        }
    }
}
