import java.io.File;
import java.util.Scanner;

class DisplayManager {

    private FileManager fileManager;

    void startLockMe() {

        fileManager = new FileManager();

        // create a new directory if doesn't exist
        File directory = getRootDirectory();

        displayMainContext(directory.getPath());

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        while (!option.equals("3")) {
            if (!"123".contains(option)) {
                System.out.println("Invalid option! Please select a valid option.");
            }

            if ("1".equals(option)) {
                fileManager.getAllFiles(directory);
            }

            if ("2".equals(option)) {
                getUserContext(directory, scanner);
            }

            option = scanner.nextLine();
        }

    }

    private void getUserContext(File directory, Scanner scanner) {
        String option;
        displayUserContext(directory);

        option = scanner.nextLine();

        while (!"6".equals(option)) {

            if (!"123456".contains(option)) {
                System.out.println("Invalid option! Please select a valid option.");
            }

            if ("1".equals(option)) {
                System.out.println("Please provide the file name:");
                String fileName = scanner.nextLine();
                fileManager.addFileToDirectory(directory, fileName);
            }

            if ("2".equals(option)) {
                fileManager.setCaseSensitive(false);
                System.out.println("Case sensitivity set to: " + fileManager.isCaseSensitive());
            }

            if ("3".equals(option)) {
                System.out.println("Please provide the file name:");
                String fileName = scanner.nextLine();
                fileManager.deleteFile(directory, fileName);
            }

            if ("4".equals(option)) {
                fileManager.setCaseSensitive(true);
                System.out.println("Case sensitivity set to: " + fileManager.isCaseSensitive());
            }

            if ("5".equals(option)) {
                System.out.println("Please provide the file name:");
                String fileName = scanner.nextLine();
                fileManager.searchFile(directory, fileName);
            }

            displayUserContext(directory);
            option = scanner.nextLine();
        }

        displayMainContext(directory.getPath());
    }

    private void displayUserContext(File directory) {
        System.out.println("\n1. Add a file to existing directory list (please provide the full path):");
        System.out.println("2. Ignore case sensitivity of file names");
        System.out.println("3. Delete a file from the existing directory list (please provide the file name):");
        System.out.println("4. Add case sensitivity of file names");
        System.out.println("5. Search a file in the main directory: " + directory.getPath());
        System.out.println("6. Return to main context");
    }

    private void displayMainContext(String directory) {
        System.out.println("Welcome to LockMe.com application. Developer: Javed Khan");
        System.out.println("\nPlease select one of the listed options:");
        System.out.println("1. Display file names in ascending order from the current directory: " + directory);
        System.out.println("2. Display User interface details");
        System.out.println("3. Close application");
    }

    private File getRootDirectory() {
        String projectPath = System.getProperty("user.dir");
        String directoryName = projectPath.concat("//RootDirectory");

        File directory = new File(directoryName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        return directory;
    }
}