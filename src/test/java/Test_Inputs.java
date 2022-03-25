import entities.Document;
import io.FileLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import search.SearchEngine;
import search.SearchResultFailed;
import util.DocumentBuilder;
import util.TfIdfCalculator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Inputs {

    @ParameterizedTest
    @ValueSource(strings = {"fox"})
    @DisplayName("Terms found")
    public void test_input_found(String arg) {
        DocumentBuilder db = new DocumentBuilder();
        try {
            List<Path> filesFromDir = FileLoader.getFilesFromDir(System.getProperty("user.dir") + "\\documents");
            List<Document> documents = db.filesToDocuments(filesFromDir);
            var result = SearchEngine.search(documents, arg);
            assertEquals("Term: fox found in [ doc3.txt doc1.txt  ]", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"iamnotfound"})
    @DisplayName("Terms not found")
    public void test_input_not_found(String arg) {
        DocumentBuilder db = new DocumentBuilder();
        try {
            List<Path> filesFromDir = FileLoader.getFilesFromDir(System.getProperty("user.dir") + "\\documents");
            List<Document> documents = db.filesToDocuments(filesFromDir);
            var result = SearchEngine.search(documents, arg);
            var message = new SearchResultFailed(arg).toString();
            System.out.println(message);
            assertEquals(message, result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Path doesn't exists")
    public void path_no_exists() throws IOException {
        String path = "NO_DIR";
        var result = FileLoader.pathIsValid(path);
        assertFalse(result);
    }

    @Test
    @DisplayName("Path exists")
    public void path_exists() throws IOException {
        String path = System.getProperty("user.dir");
        var result = FileLoader.pathIsValid(path);
        assertTrue(result);
    }

    @Test
    @DisplayName("Correct TF-IDF")
    public void test_tf_idf() {
        var tfidf = TfIdfCalculator.calculateTfIdf(0.429, 0.301);
        assertEquals(0.129129, tfidf);
    }

    @Test
    @DisplayName("Correct IDF")
    public void test_idf() {
        var idf = TfIdfCalculator.calculateIdf(2, 2);
        var idf2 = TfIdfCalculator.calculateIdf(2, 1);
        assertEquals(0.0, idf);
        assertEquals(0.3010299956639812, idf2);
    }
}
