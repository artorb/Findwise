import entities.Document;
import io.FileLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.DocumentBuilder;
import util.TfIdfCalculator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Inputs {

    @ParameterizedTest
    @ValueSource(strings = {"fox", "brown", "the", "apple"})
    @DisplayName("Terms found")
    public void test_input_found() {
        DocumentBuilder db = new DocumentBuilder();
        try {
            List<Path> filesFromDir = FileLoader.getFilesFromDir(System.getProperty("user.dir" + "\\documents"));
            List<Document> documents = db.filesToDocuments(filesFromDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"fox", "brown", "the", "apple"})
    @DisplayName("Terms not found")
    public void test_input_not_found(String arg) {
        DocumentBuilder db = new DocumentBuilder();
        try {
            List<Path> filesFromDir = FileLoader.getFilesFromDir(System.getProperty("user.dir" + "\\documents"));
            List<Document> documents = db.filesToDocuments(filesFromDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Path doesn't exists")
    public void path_no_exists() {
        String path = "NO_DIR";
        var result = FileLoader.validatePath(path);
        assertFalse(result);
    }

    @Test
    @DisplayName("Path exists")
    public void path_exists() {
        String path = System.getProperty("user.dir");
        var result = FileLoader.validatePath(path);
        assertTrue(result);
    }

    @Test
    @DisplayName("Correct TF-IDF")
    public void tf_test() {
        var tfidf = TfIdfCalculator.calculateTfIdf(0.429, 0.301);
        assertEquals(0.129129, tfidf);
    }
}
