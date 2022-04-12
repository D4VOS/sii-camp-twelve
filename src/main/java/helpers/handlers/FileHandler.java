package helpers.handlers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    public static final String RESOURCE_PATH = "src/main/resources/";
    public final static String OUTPUT_PATH = "src/main/resources/output/";
    private static final Logger logger = LoggerFactory.getLogger(FileHandler.class);

    protected static String readTextFile(String fileName) {
        try {
            Path filePath = Path.of(RESOURCE_PATH + fileName);
            String fileContent = Files.readString(filePath);
            logger.info("File {} ({}) was read successfully.\n", fileName, filePath);

            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected static void writeTextFile(String content, String fileName) {
        try {
            Path filePath = Path.of(OUTPUT_PATH + fileName);
            Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            logger.info("Successfully saved {} ({}).\n", fileName, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File content, String fileName) {
        try {
            FileUtils.copyFile(content, new File(OUTPUT_PATH + fileName));
            logger.info("File saved in location: " + OUTPUT_PATH + fileName);
        } catch (IOException err) {
            logger.error("Error occurred during saving file in location: " + OUTPUT_PATH + fileName);
            err.printStackTrace();
        }
    }

    public static String getResourceAbsolutePath(String fileName) {
        URL resource = FileHandler.class.getClassLoader().getResource(fileName);
        try {
            assert resource != null;
            return Paths.get(resource.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> getDirectoryFiles(String directoryPath, String regexFilter) {
        return FileUtils.listFiles(
                        new File(RESOURCE_PATH + directoryPath),
                        new RegexFileFilter(regexFilter),
                        DirectoryFileFilter.DIRECTORY
                ).stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
    }
}
