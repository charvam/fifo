package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> commandQueue = new ArrayBlockingQueue<>(2);
        DatabaseManager databaseManager = new DatabaseManager();
        CommandProcessor commandProcessor = new CommandProcessor(commandQueue, databaseManager);
        Thread processorThread = new Thread(commandProcessor);
        processorThread.start();

        try {
            commandQueue.put("Add 1 a1 Robert");
            commandQueue.put("Add 2 a2 Martin");
            commandQueue.put("PrintAll");
            commandQueue.put("DeleteAll");
            commandQueue.put("PrintAll");
            commandQueue.put("Close");
            processorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}