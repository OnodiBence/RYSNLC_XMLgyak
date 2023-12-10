package DOMParse_RYSNLC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadRYSNLC {

	public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

		File xmlFile = new File("LEADNI\\1feladat\\XML_RYSNLC.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		System.out.println("");

		NodeList ingList = doc.getElementsByTagName("ingredients");

		for (int i = 0; i < ingList.getLength(); i++) {

			Node nNode = ingList.item(i);
			System.out.println("\nCurrent element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String id = elem.getAttribute("ingredient_id");

				Node node1 = elem.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("type").item(0);
				String type = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("purchasePrice").item(0);
				String pp = node3.getTextContent();

				Node node4 = elem.getElementsByTagName("stockQuantity").item(0);
				String sq = node4.getTextContent();

				System.out.println("Az alapanya id-je: " + id);
				System.out.println("Az alapanyag neve: " + name);
				System.out.println("Az alapanyag típusa: " + type);
				System.out.println("Az alapanyag beszerzési ára: " + pp);
				System.out.println("Az alapanyag mennyisége raktáron: " + sq);

			}
		}

		NodeList pList = doc.getElementsByTagName("product");

		for (int i = 0; i < pList.getLength(); i++) {

			Node pNode = pList.item(i);

			System.out.println("");
			System.out.println("");

			System.out.println("\nCurrent element: " + pNode.getNodeName());

			if (pNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) pNode;
				String id = elem.getAttribute("product_id");

				Node node1 = elem.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("price").item(0);
				String nev = node2.getTextContent();

				System.out.println("A termék id-je: " + id);
				System.out.println("A termék neve: " + name);
				System.out.println("A termék ára: " + nev);

				if (pList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node3 = elem.getElementsByTagName("type").item(0);
					while (node3 != null) {
						node3 = elem.getElementsByTagName("type").item(db);
						if (node3 != null) {
							String type = node3.getTextContent();
							System.out.println("A termék típusa: " + type);
						}
						db++;
					}

				}

				Node node3 = elem.getElementsByTagName("description").item(0);
				String desc = node3.getTextContent();

				System.out.println("A termék leírása: " + desc);

			}
		}
		
		NodeList moList = doc.getElementsByTagName("made_of");

		for (int i = 0; i < moList.getLength(); i++) {

			Node moNode = moList.item(i);

			System.out.println("");
			System.out.println("");

			System.out.println("\nCurrent element: " + moNode.getNodeName());

			if (moNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) moNode;
				String iid = elem.getAttribute("ingredient_id");
				String pid = elem.getAttribute("product_id");

				System.out.println("Az alapanyag kulcsa: " + iid);
				System.out.println("A termék kulcsa: " + pid);

			}
		}
		
		NodeList oList = doc.getElementsByTagName("order");

		for (int i = 0; i < oList.getLength(); i++) {

			Node oNode = oList.item(i);

			System.out.println("");
			System.out.println("");

			System.out.println("\nCurrent element: " + oNode.getNodeName());

			if (oNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) oNode;
				String o_id = elem.getAttribute("order_id");

				Node node1 = elem.getElementsByTagName("date").item(0);
				String date = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("price").item(0);
				String price = node2.getTextContent();

				System.out.println("A rendelés azonosítója: " + o_id);
				System.out.println("A rendelés dátuma: " + date);
				System.out.println("Ára: " + price);

				if (pList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node3 = elem.getElementsByTagName("item").item(0);
					while (node3 != null) {
						node3 = elem.getElementsByTagName("item").item(db);
						if (node3 != null) {
							String item = node3.getTextContent();
							System.out.println("A rendelt termék: " + item);
						}
						db++;
					}

				}

				Node node3 = elem.getElementsByTagName("status").item(0);
				String status = node3.getTextContent();
				
				System.out.println("A rendelés státusza: " + status);
				
			}
		}
		
		NodeList oiList = doc.getElementsByTagName("ordered_items");

		for (int i = 0; i < oiList.getLength(); i++) {

			Node oiNode = oiList.item(i);

			System.out.println("");
			System.out.println("");

			System.out.println("\nCurrent element: " + oiNode.getNodeName());

			if (oiNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) oiNode;
				String pid = elem.getAttribute("product_id");
				String oid = elem.getAttribute("order_id");

				Node node1 = elem.getElementsByTagName("orderingDate").item(0);
				String od = node1.getTextContent();

				System.out.println("A termék azonosítója: " + pid);
				System.out.println("A rendelés egyedi azonosítója: " + oid);
				System.out.println("Rendelés dátuma: " + od);

			}
		}
		
		NodeList cList = doc.getElementsByTagName("customer");

		for (int i = 0; i < cList.getLength(); i++) {

			Node cNode = cList.item(i);

			System.out.println("");
			System.out.println("");

			System.out.println("\nCurrent element: " + cNode.getNodeName());

			if (cNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) cNode;
				String c_id = elem.getAttribute("customer_id");
				String co_id = elem.getAttribute("create_order");

				Node node1 = elem.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				System.out.println("A vevő egyedi azonosítója: " + c_id);
				System.out.println("A create_order azonosítója: " + co_id);
				System.out.println("A rendelő neve: " + name);

				if (cList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node2 = elem.getElementsByTagName("address").item(0);
					while (node2 != null) {
						node2 = elem.getElementsByTagName("address").item(db);
						if (node2 != null) {

							Node n = elem.getElementsByTagName("postalcode").item(db);
							String pc = n.getTextContent();
							System.out.println("A cím irányítószáma: " + pc);

							Node n2 = elem.getElementsByTagName("street").item(db);
							String street = n2.getTextContent();
							System.out.println("A cím utcája: " + street);

							Node n3 = elem.getElementsByTagName("houseNumber").item(db);
							String hn = n3.getTextContent();
							System.out.println("A cím házszáma: " + hn);

							Node n4 = elem.getElementsByTagName("doorNumber").item(db);
							String dn = n4.getTextContent();
							System.out.println("A cím ajtószáma: " + dn);

						}
						db++;
					}
				}

				Node node2 = elem.getElementsByTagName("phoneNumber").item(0);
				String phoneNumber = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("email").item(0);
				String email = node3.getTextContent();

				Node node4 = elem.getElementsByTagName("regularCustomer").item(0);
				String rC = node4.getTextContent();

				System.out.println("A vevő telefonszáma: " + phoneNumber);
				System.out.println("A vevő emailje: " + email);
				System.out.println("A vevő regularCustomer e: " + rC);

			}
		}
		
		NodeList courList = doc.getElementsByTagName("courier");

		for (int i = 0; i < courList.getLength(); i++) {
			
			Node courNode = courList.item(i);

			System.out.println("");
			System.out.println("");
			
			System.out.println("\nCurrent element: " + courNode.getNodeName());

			if (courNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) courNode;
				String cid = elem.getAttribute("courier_id");
				String cdid = elem.getAttribute("courier_demand");

				Node node1 = elem.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("phoneNumber").item(0);
				String phoneNumber = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("transportType").item(0);
				String transport = node3.getTextContent();

				Node node4 = elem.getElementsByTagName("isActive").item(0);
				String isActive = node4.getTextContent();

				System.out.println("A futár azonosítója: " + cid);
				System.out.println("A rendelés azonosítója: " + cdid);
				System.out.println("A futár nev: " + name);
				System.out.println("A futár telefonszáma: " + phoneNumber);
				System.out.println("A futár mivel viszi ki a rendelést: " + transport);
				System.out.println("A futár aktív: " + isActive);

			}
		}

	}

}