package DOMParse_RYSNLC;


import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadRYSNLC {

    public static void main(String[] args) {
        try {

            String xmlFilePath = "1feladat\\XML_RYSNLC.xml";

            // XML dokumentum létrehozása és beolvasása
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Dokumentum elemeinek kiírása a konzolra
            printNode(doc.getDocumentElement(), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurzív metódus az XML elemek kiírására
    private static void printNode(Node node, String behúzás) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
                
            //elemek előtt enter    
            if (node.getParentNode().getNodeType() == Node.ELEMENT_NODE) {

                System.out.println("");

            }

            //tagek kiírása
            System.out.println(behúzás + "<" + node.getNodeName() + ">");

            //tagnév kiírása
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                printNode(nodeList.item(i), behúzás + "  ");
            }

            //tagek lezárása
            System.out.println(behúzás + "</" + node.getNodeName() + ">");

            //tag után üres sor
            System.out.println("");

        } else if (node.getNodeType() == Node.TEXT_NODE) {

            //tag szöveg tartalmának kiírása
            String text = node.getNodeValue().trim();
            if (!text.isEmpty()) {
                System.out.println(behúzás + text);
            }

        } else if (node.getNodeType() == Node.COMMENT_NODE) {

            //comment kiírása    
            System.out.println("");
            System.out.println(behúzás + "<!-- " + node.getNodeValue() + " -->");

        }
    }
}
