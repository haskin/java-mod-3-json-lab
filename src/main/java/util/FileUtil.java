package util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import logger.Logger;

public class FileUtil {
    private static final Logger logger = Logger.getInstance();
    private static final String ABSOLUTE_PATH = Paths.get("").toAbsolutePath().toString();

    public static boolean fileExists(String filePath) {
        File file = new File(ABSOLUTE_PATH + filePath);
        return file.exists();
    }

    public static void createNewFile(String filePath) {
        File file = new File(ABSOLUTE_PATH + filePath);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(ABSOLUTE_PATH + filePath);
        if (file.delete())
            logger.log("File deleted successfully", "\n");
    }

    public static File readFile(String filePath) {
        return new File(ABSOLUTE_PATH + filePath);
    }

    public static void writeFile(String filePath, List<String> lines) {
        File file = new File(ABSOLUTE_PATH + filePath);
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            lines.stream().forEach(line -> {
                try {
                    bufferedWriter.write(line + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
