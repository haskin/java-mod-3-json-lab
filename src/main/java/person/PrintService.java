package person;

import java.util.List;

public interface PrintService {
    void print(List<Person> people);
    String outputString(List<Person> people);
}
