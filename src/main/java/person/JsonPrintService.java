package person;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonPrintService implements PrintService {

    @Override
    public void print(List<Person> people) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(people));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        people.stream().map(person -> {
//            try {
//                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
//            } catch (Exception e) {
//                return "";
//            }
//
//        }).forEach(System.out::println);
    }
}
