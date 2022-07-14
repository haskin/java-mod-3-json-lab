package util;

import java.util.Scanner;
import java.util.Set;

import logger.Logger;

public class UserInput {
    private static final Logger logger = Logger.getInstance();

    private static String getUserInput(Scanner scanner, String prompt, Set<String> possibleAnswers,
            String defaultAnswer) {
        try {
            logger.log(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();

            if (possibleAnswers == null)
                return answer;

            if (possibleAnswers.contains(answer))
                return answer;
            else
                throw new Exception();
        } catch (Exception e) {
            logger.log("ERROR: This was an invalid answer." + "\"" + defaultAnswer + "\" will be used instead.", "\n");
            return defaultAnswer;
        }
    }

    public static boolean restoreFromList(Scanner scanner) {
        String prompt = "Do you want to restore the list of people from file? (y/N): ";
        return getUserInput(scanner, prompt, Set.of("y", "n"), "n").equalsIgnoreCase("y");
    }

    public static String chooseOption(Scanner scanner) {
        String prompt = "Please choose from the following three options:\n" +
                "\t1. Add a person to the list.\n" +
                "\t2. Print a list of current persons.\n" +
                "\t3. Exit the program.\n" +
                "Choice(1,2, or 3): ";

        return getUserInput(scanner, prompt, Set.of("1", "2", "3"), "2");
    }

    public static String getPersonName(Scanner scanner) {
        String prompt = "Please enter a new name for the person: ";
        return getUserInput(scanner, prompt, null, "Default");

    }
}
