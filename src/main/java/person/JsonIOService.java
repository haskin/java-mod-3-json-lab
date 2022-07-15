package person;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonIOService implements PersonIOService{
    @Override
    public void writePeopleList(String filePath, List<Person> people) {
        FileUtil.writeFile(filePath,
                people.stream().map(person -> person.formatAsCSV()).collect(Collectors.toList()));
    }

    @Override
    public List<Person> readPeopleList(String filePath) {
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
}
