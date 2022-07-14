import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logger.Logger;
import model.Person;
import model.PersonFileService;
import util.FileUtil;
import util.UserInput;

/* Ask the user if they want to restore the list of people from file.
/* If yes, restore from the file you might have saved from a previous run of your program.
* If no, start a brand-new list. */

public class Main {
    private static final Logger logger = Logger.getInstance();

    private static final String FILE_PATH = "/src/main/java/person.csv";

    private static List<Person> people = new ArrayList<>();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            // boolean restoreFromList = UserInput.restoreFromList(scanner);
            boolean restoreFromList = UserInput.restoreFromList(scanner);

            if (restoreFromList) {
                logger.log("Restoring file.", "\n");
                if (!FileUtil.fileExists(FILE_PATH)) {
                    logger.log("File not found. Creating new file", "\n");
                    FileUtil.createNewFile(FILE_PATH);
                } else {
                    logger.log("Reading from file.", "\n");
                    people = PersonFileService.readPeopleList(FILE_PATH);
                }

            } else { // Do not restore file
                logger.log("File not restored. New file will be used.", "\n");
                if (FileUtil.fileExists(FILE_PATH)) {
                    // FileUtil.createNewFile(FILE_PATH);
                    logger.log("Previous file found. File will be deleted.", "\n");

                }
                FileUtil.deleteFile(FILE_PATH);
                FileUtil.createNewFile(FILE_PATH);
            }
            while (true) {
                String choice = UserInput.chooseOption(scanner);
                switch (choice) {
                    case "1":
                        logger.log("Adding a new person", "\n");
                        people.add(PersonFileService.getPerson(scanner));
                        break;
                    case "2":
                        logger.log("The current people in the list are:", "\n");
                        StringBuilder stringBuilder = new StringBuilder();
                        people.stream().map(person -> person.getName() + ", ").forEach(stringBuilder::append);
                        logger.log(stringBuilder.toString(), "\n");
                        break;
                    case "3":
                        logger.log("Writing to file", "\n");
                        PersonFileService.writePeopleList(FILE_PATH, people);
                        logger.log("Exiting program");
                        return;
                    default:
                        return;
                }
            }

        } catch (Exception e) {

        }
    }

}
