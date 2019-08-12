package org.quark.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class CsvParser {

    @ConfigProperty(name = "file.csv")
    private String CSV_FILE_PATH;
    private static int FIRST_COLUMN = 0;

    public List parser() {
        LinkedList urlList = new LinkedList();
        Reader reader;
        CSVParser csvParser;
        try {
            reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            csvParser.forEach(item -> urlList.add(item.get(FIRST_COLUMN)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }
}
