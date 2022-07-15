package person;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonIOService implements PersonIOService {
    PrintService printService = new JsonPrintService();

    @Override
    public void writePeopleList(String filePath, List<Person> people) {
        FileUtil.writeFile(filePath,
                Arrays.stream(printService.outputString(people).split("\n")).collect(Collectors.toList()));
    }

    @Override
    public List<Person> readPeopleList(String filePath) {
        File peopleFile = FileUtil.readFile(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person[] people = objectMapper.readValue(peopleFile, Person[].class);
            return Arrays.stream(people).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
