package person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.FileUtil;
import util.RandomUtil;
import util.UserInput;

// Adding a person to the list:
// Ask the user for the person's information.
// Add the new person to your person's list.
// Return the user to the 3 options from before.

public class PersonFileService {

    public static void writePeopleList(String filePath, List<Person> people) {
        FileUtil.writeFile(filePath,
                people.stream().map(person -> person.formatAsCSV()).collect(Collectors.toList()));
    }

    public static List<Person> readPeopleList(String filePath) {
        File peopleFile = FileUtil.readFile(filePath);
        List<Person> people = new ArrayList<>();
        try (FileReader fileReader = new FileReader(peopleFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.lines().forEach(line -> people.add(new Person(line)));

            return people;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Person getPerson(Scanner scanner) {
        String firstName = UserInput.getUserInput(scanner, "Choose a first name: ", null, RandomUtil.getRandomName());
        String lastName = UserInput.getUserInput(scanner, "Choose a last name: ", null, RandomUtil.getRandomName());
        int birthYear = UserInput.getUserInputNumber(scanner, "Choose a birth year: ", null,
                RandomUtil.getRandomYear());
        int birthMonth = UserInput.getUserInputNumber(scanner, "Choose a birth month: ", null,
                RandomUtil.getRandomMonth());
        int birthDay = UserInput.getUserInputNumber(scanner, "Choose a birth day: ", null, RandomUtil.getRandomDay());

        return new Person(firstName, lastName, birthYear, birthMonth, birthDay);
    }

}
