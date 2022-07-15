package person;

import logger.Logger;

import java.util.List;

public class CsvPrintService implements PrintService{
    @Override
    public void print(List<Person> people) {
        System.out.print(outputString(people));
    }

    @Override
    public String outputString(List<Person> people) {
        StringBuilder stringBuilder = new StringBuilder();
        people.stream().map(person -> "\t" + person.formatAsCSV() + "\n")
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

}
