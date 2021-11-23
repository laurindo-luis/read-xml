package br.com.luis;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        readXML("verbetesWikipedia.xml");
    }

    private static void readXML(String path) {
        try {
            File file = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList pages = document.getElementsByTagName("page");
            for(int i = 0; i < pages.getLength(); i++) {
                Element element = (Element) pages.item(i);
                Integer id = Integer.valueOf(element.getElementsByTagName("id").item(0).getTextContent());
                String title = element.getElementsByTagName("title").item(0).getTextContent();
                System.out.println(String.format("Page Id: %s\nTitle: %s\n", id, title));
            }
        } catch(IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}
