import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileManager {

    private boolean isCaseSensitive;

    FileManager() {
    }

    boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    void setCaseSensitive(boolean caseSensitive) {
        isCaseSensitive = caseSensitive;
    }

    void addFileToDirectory(File directory, String fileName) {

        try {
            Files.copy(Paths.get(fileName), Paths.get(directory.getPath() + "/" + Paths.get(fileName).getFileName().toString()));
            System.out.println("File successfully added to root directory.");
            System.out.println(Paths.get(fileName)+directory.getPath()+"-"+Paths.get(fileName).getFileName().toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not added. An error occurred."+Paths.get(fileName)+directory.getPath()+"/"+Paths.get(fileName).getFileName().toString());
        }
    }

    void deleteFile(File directory, String fileName) {
        try (Stream<Path> walk = Files.walk(Paths.get(directory.toURI()))) {
            Optional<Path> file = walk.filter(path -> !Files.isDirectory(path)).filter(path -> {
                if (isCaseSensitive()) {
                    return path.getFileName().toString().equals(fileName);
                } else {
                    return path.getFileName().toString().equalsIgnoreCase(fileName);
                }
            }).findFirst();
            if (file.isPresent()) {
                boolean deleted = file.get().toFile().delete();
                if (deleted) {
                    System.out.println("File successfully deleted!");
                } else {
                    System.out.println("File not deleted. An error occurred.");
                }
            } else {
                System.out.println("File: " + fileName + " not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void searchFile(File directory, String fileName) {
        try (Stream<Path> walk = Files.walk(Paths.get(directory.toURI()))) {
            Optional<Path> file = walk.filter(path -> !Files.isDirectory(path)).filter(path -> {
                if (isCaseSensitive()) {
                    return path.getFileName().toString().equals(fileName);
                } else {
                    return path.getFileName().toString().equalsIgnoreCase(fileName);
                }
            }).findFirst();
            if (file.isPresent()) {
                System.out.println("File: " + fileName + " was found!");
            } else {
                System.out.println("File: " + fileName + " not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getAllFiles(File directory) {
        try (Stream<Path> walk = Files.walk(Paths.get(directory.toURI()))) {
            List<String> result = walk.filter(path -> !Files.isDirectory(path)).map(path -> path.getFileName().toString()).sorted().collect(Collectors.toList());
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            //test
        }
    }
}