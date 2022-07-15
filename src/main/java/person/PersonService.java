package person;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PersonService {
    static void printPersonListAsJSON(List<Person> personList) throws Exception {
        String json = new ObjectMapper().writeValueAsString(personList);
        System.out.println(json);
    }
}
