package org.quark.service;

import org.quark.utils.CsvParser;
import org.quark.utils.XmlParser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ParserService {

    @Inject
    XmlParser xmlParser;

    @Inject
    CsvParser csvParser;

    public List XMLParser() {
      return xmlParser.parser();
    }
    public List CSVParser() {
        return csvParser.parser();
    }
}
