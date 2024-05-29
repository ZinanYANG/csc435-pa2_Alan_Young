package csc435.app;
import java.lang.System;
import java.util.Scanner;

public class AppInterface {
    private ProcessingEngine engine;

    public AppInterface(ProcessingEngine engine) {
        this.engine = engine;
    }

    public void readCommands() {
        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            System.out.print("> ");
            command = sc.next();

            if ("quit".equals(command)) {
                engine.stopWorkers();
                break;
            } else if (command.startsWith("index")) {
                String indexPath = sc.nextLine().trim(); 
                String indexResult = engine.indexFiles(indexPath); 
                System.out.println(indexResult);
            } else if (command.startsWith("search")) {
                String searchQuery = sc.nextLine().trim(); 
                String searchResult = engine.searchFiles(searchQuery); 
                System.out.println(searchResult);
            } else {
                System.out.println("Unrecognized command!");
            }
        }
        sc.close(); 
    }
}
