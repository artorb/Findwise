package io;

import java.io.IOException;
import java.nio.file.*;
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
        if (path.isBlank() || path.isEmpty()) {
            return files;
        }
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


    /**
     * Checks that a path is valid
     *
     * @param path provided by user
     * @return true if path exists and is not empty
     * @throws IOException
     */
    public static boolean pathIsValid(String path) throws IOException {
        if (path.isEmpty() || path.isBlank())
            return false;
        for (char c : path.toCharArray()) {
            if (c == '<' || c == '>')
                return false;
        }
        return pathExists(path) && containsFiles(path);
    }

    private static boolean pathExists(String path) {
        return Files.exists(Paths.get(path)) && Files.isDirectory(Path.of(path));
    }

    private static boolean containsFiles(String path) throws IOException {
        if (Files.isDirectory(Path.of(path))) {
            try (Stream<Path> entries = Files.list(Path.of(path))) {
                return entries.findFirst().isPresent();
            }
        }
        return false;
    }
}
