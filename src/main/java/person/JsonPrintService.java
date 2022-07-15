package person;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonPrintService implements PrintService {

    @Override
    public void print(List<Person> people) {
        System.out.println(outputString(people));
    }

    @Override
    public String outputString(List<Person> people) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(people);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
