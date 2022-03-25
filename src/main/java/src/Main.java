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
    public static void main(String[] args) {
        System.out.println("\u001B[40m Please provide file path: \u001B[0m\n");
        DocumentBuilder db = new DocumentBuilder();
        Scanner scan = new Scanner(System.in);

        var path = scan.nextLine();
        if (!FileLoader.validatePath(path) || path.isEmpty()) {
            System.out.println("Path " + path + " doesn't exist!");
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
}
