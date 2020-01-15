package auxiliary;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("unused")
public class RateImport {

    public static float[] getRate() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream());

        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("Cube");
        float[] temp = new float[nodeList.getLength()-1];

        for (int i = 0; i < nodeList.getLength()-2; i ++) {
            if (i == nodeList.getLength()-1) {
                temp[i] = (float) 1.000;
            } else {
                Node nNode = nodeList.item(i + 2);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    temp[i] = (Float.parseFloat(eElement.getAttribute("rate")));
                }
            }
        }
        return temp;
    }

    public static String[] getNames() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream());

        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("Cube");
        String[] temp = new String[nodeList.getLength()-1];

        for (int i = 0; i < nodeList.getLength()-2; i ++) {
            if (i == nodeList.getLength()-1) {
                temp[i] = "EUR";
            } else {
                Node nNode = nodeList.item(i + 2);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    temp[i] = (eElement.getAttribute("currency"));
                }
            }
        }
        return temp;
    }
}
