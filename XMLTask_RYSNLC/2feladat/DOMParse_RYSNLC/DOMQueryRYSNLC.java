package DOMParse_RYSNLC;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryRYSNLC {

	public static void main(String[] args) {

		try {

			// DocumentBuilder
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("LEADNI\\1feladat\\XML_RYSNLC.xml");

			document.getDocumentElement().normalize();

			// XPath 
			XPath xPath = XPathFactory.newInstance().newXPath();
						
			// a Pizzeria_RYSNLC root elem alapanyag gyerekelemei
			String expression = "Pizzeria_RYSNLC / ingredients";

			// alapanyagok, amiknek van attribútuma
			// String expression = "//ingredients[@*]";

			// alapanyagok, amik mint 500ba kerülnek
			// String expression = "//ingredients[purchasePrice='500']";

			// alapanyagok típusa, amik több, mint 200Ft-ba kerülnek
			// String expression = "//ingredients[purchasePrice>200]/type";

			// termékek első két eleme
			// String expression = "//product[position()<3]";

			// 2-es azonositójú termék 
			// String expression = "//product[@product_id='2']";

			// a harmadik termék kiválasztáas
			// String expression = "Pizzeria_RYSNLC/product[3]";
			
			// keszitunk egy listat, majd az xPath kifejezest le kell forditani es ki kell ertekelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("\nAktualis elem: " + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("ingredients")) {

					Element elem = (Element) node;

                        String id = elem.getAttribute("ingredient_id");
        
                        Node node1 = elem.getElementsByTagName("name").item(0);
                        String name = node1.getTextContent();
        
                        Node node2 = elem.getElementsByTagName("type").item(0);
                        String type = node2.getTextContent();
        
                        Node node3 = elem.getElementsByTagName("purchasePrice").item(0);
                        String purchaseP = node3.getTextContent();

						Node node4 = elem.getElementsByTagName("stockQuantity").item(0);
                        String stockQ = node4.getTextContent();
        
                        System.out.println("Alapanyag id-je: " + id);
                        System.out.println("Név: " + name);
                        System.out.println("Típus: " + type);
                        System.out.println("PurchasePrice: " + purchaseP);
						System.out.println("StockQuantity: " + stockQ);

				}
				
				// alapanyagok típusa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("type")) {

					Element element = (Element) node;

					System.out.println("Alapanyag típusa: " + element.getTextContent());

				}

				//product kiiratasa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("product")) {

					Element element = (Element) node;

					System.out.println("ID: " + element.getAttribute("product_id"));

					System.out.println(
							"Termék neve: " + element.getElementsByTagName("name").item(0).getTextContent());

					System.out.println(
							"Ára: " + element.getElementsByTagName("price").item(0).getTextContent());
					
							if (nodeList.item(i).getChildNodes().getLength() > 3) {
								int db = 0;
								Node node4 = element.getElementsByTagName("type").item(0);
								while (node4 != null) {
									node4 = element.getElementsByTagName("type").item(db);
									if (node4 != null) {
										String type = node4.getTextContent();
										System.out.println("A típusa: " + type);
									}
									db++;
								}
		
							}
							
					System.out.println(
							"Leírása: " + element.getElementsByTagName("description").item(0).getTextContent());
         
        
                    

				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}