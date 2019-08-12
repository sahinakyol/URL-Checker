package org.quark.utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.w3c.dom.*;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class XmlParser implements FileParser{

    private Logger logger = Logger.getLogger(XmlParser.class.getName());

    @ConfigProperty(name = "file.xml")
    private String fileUrl;

    public List parser() {
        List list = new ArrayList();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File(fileUrl);
            if (file.exists()) {
                Document doc = documentBuilder.parse(file);
                Element docEle = doc.getDocumentElement();
                NodeList urlList = docEle.getElementsByTagName("url");
                if (urlList != null && urlList.getLength() > 0) {
                    list = IntStream.range(0, urlList.getLength())
                            .mapToObj(urlList::item)
                            .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                            .map(Node::getTextContent).collect(Collectors.toList());
                }
            }
        } catch (Exception exception) {
            logger.info("Parser Exception : " + exception);
        }
        return list;
    }

}
