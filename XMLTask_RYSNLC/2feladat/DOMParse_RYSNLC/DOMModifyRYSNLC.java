package DOMParse_RYSNLC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyRYSNLC {

	public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

		try {

			File inputFile = new File("LEADNI\\2feladat\\DOMParse_RYSNLC\\XML_RYSNLC_2.xml");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(inputFile);

			// order attribútumának módosítása
			Node csoport = doc.getElementsByTagName("order").item(0);

			NamedNodeMap attr = csoport.getAttributes();
			Node nodeAttr = attr.getNamedItem("order_id");
			nodeAttr.setTextContent("4");

			// order date módosítása
			NodeList list = csoport.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if ("date".equals(eElement.getNodeName())) {
						if ("2023.01.15".equals(eElement.getTextContent())) {
							eElement.setTextContent("2023.12.12");
						}
					}
				}
			}

			// order item módosítás
			NodeList kszList = doc.getElementsByTagName("ordered_items");

			for (int i = 0; i < kszList.getLength(); i++) {
				Node node = kszList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					String tagValue = eElement.getAttribute("order_id");

					if ("1".equals(tagValue)) {
						eElement.setAttribute("order_id", "4");
					}

				}
			}

			// customer items módosítás
			NodeList cList = doc.getElementsByTagName("customer");

			for (int i = 0; i < cList.getLength(); i++) {
				Node node = cList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					String tagValue = eElement.getAttribute("create_order");

					if ("1".equals(tagValue)) {
						eElement.setAttribute("create_order", "4");
					}

				}
			}

			// courier item módosítás
			NodeList courList = doc.getElementsByTagName("courier");

			for (int i = 0; i < courList.getLength(); i++) {
				Node node = courList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					String tagValue = eElement.getAttribute("courier_demand");

					if ("1".equals(tagValue)) {
						eElement.setAttribute("courier_demand", "4");
					}

				}
			}

			// Tartalom írása
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			System.out.println("-Módosított fájl-");

			StreamResult consoleResult = new StreamResult(System.out);
			StreamResult file = new StreamResult(inputFile);

			transformer.transform(source, consoleResult);
			transformer.transform(source, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}