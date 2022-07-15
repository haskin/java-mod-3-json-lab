package person;

import logger.Logger;

import java.util.List;

public class CsvPrintService implements PrintService{
    private static final Logger logger = Logger.getInstance();
    @Override
    public void print(List<Person> people) {
        StringBuilder stringBuilder = new StringBuilder();
        people.stream().map(person -> "\t" + person.formatAsCSV() + "\n")
                .forEach(stringBuilder::append);
        logger.log(stringBuilder.toString(), "\n");
    }
}
