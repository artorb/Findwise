package io;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple file loader, loads files from provided directory
 */
public class FileLoader {
    private FileLoader() {
    }

    public static List<Path> getFilesFromDir(String path) throws IOException {
        List<Path> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            files = paths
                    .filter(Files::exists)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (AccessDeniedException e) {
            System.out.println("No rights to read the files in provided directory");
            e.printStackTrace(System.err);
        } catch (IOException e) {
            System.out.println("No files in provided directory");
            e.printStackTrace(System.err);
        }

        return files;
    }


    public static boolean validatePath(String path) {
        return pathExists(path);
    }

    private static boolean pathExists(String path) {
        return Files.exists(Paths.get(path)) && Files.isDirectory(Path.of(path));
    }

    private static boolean containsFiles(String path) {
        return false;
    }
}
