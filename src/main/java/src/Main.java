package src;

import entities.Document;
import io.FileLoader;
import search.SearchEngine;
import util.DocumentBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\u001B[40m Please provide file path: \u001B[0m\n");
        Scanner scan = new Scanner(System.in);

        var path = scan.nextLine();
        if (!FileLoader.pathIsValid(path)) {
            System.out.println("Path " + path + " doesn't exist or incorrect!");
            System.exit(1);
        }

        System.out.println("Scanning...");
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\u001B[40m Provide searched term: \u001B[0m\n");
        var query = scan.nextLine();
        if (!validQuery(query)) {
            System.out.println("Invalid query");
            System.exit(1);
        }

        DocumentBuilder db = new DocumentBuilder();
        try {
            List<Path> filesFromDir = FileLoader.getFilesFromDir(path);
            List<Document> documents = db.filesToDocuments(filesFromDir);

            var result = SearchEngine.search(documents, query);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }

        scan.close();
    }

    private static boolean validQuery(String query) {
        return !query.isEmpty() && !query.isBlank();
    }
}
