package texoit.com.goldenraspberryawards.util;

import org.apache.commons.csv.CSVFormat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class CSVUtils {

    public static final String TYPE = "text/csv";

    public static final String[] HEADERS = {"year", "title", "studios", "producers", "winner"};

    public static CSVFormat buildCSVFortmat() {
        return CSVFormat.RFC4180
                .withDelimiter(';')
                .withHeader(CSVUtils.HEADERS)
                .withSkipHeaderRecord(true)
                .withIgnoreEmptyLines(true);
    }

    public static Reader loadFile(String fileName) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (Objects.isNull(inputStream)) throw new RuntimeException("File to migrate database Not Found!");

        return new InputStreamReader(inputStream);
    }

}
