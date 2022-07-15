package util;

import java.util.Scanner;
import java.util.Set;

import logger.Logger;

public class UserInput<T> {
    private static final Logger logger = Logger.getInstance();

    public static String getUserInput(Scanner scanner, String prompt, Set<String> possibleAnswers,
            String defaultAnswer) {
        try {
            logger.log(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.isBlank())
                throw new Exception();

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

    public static Integer getUserInputNumber(Scanner scanner, String prompt, Set<String> possibleAnswers,
            Integer defaultAnswer) {
        try {
            logger.log(prompt);
            while (!scanner.hasNextInt()) {
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().isBlank())
                        continue;
                    else
                        throw new Exception();
                }
            }
            int answer = scanner.nextInt();

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
        String prompt = "Do you want to restore the list of people from file? (Y/n): ";
        return getUserInput(scanner, prompt, Set.of("y", "n"), "y").equalsIgnoreCase("y");
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
