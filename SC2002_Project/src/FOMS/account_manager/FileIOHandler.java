/**
 * The FileIOHandler class provides static methods for reading from and writing to files.
 */
package FOMS.account_manager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileIOHandler {

    /**
     * Reads all lines from a file and returns them as a list of strings.
     * @param fileName The name of the file to read from.
     * @return A list of strings containing the lines read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static List<String> readLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    /**
     * Writes content to a file.
     * @param fileName The name of the file to write to.
     * @param content The content to write to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void writeToFile(String fileName, String content) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println(content);
        }
    }
}
