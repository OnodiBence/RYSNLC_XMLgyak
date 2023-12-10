package DOMParse_RYSNLC;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMWriteRYSNLC {

	public static void main(String argv[]) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.newDocument();

		Element root = doc.createElementNS("RYSNLC", "Pizzeria_RYSNLC");
		doc.appendChild(root);

		// ingredients

		root.appendChild(createIngredients(doc, "1", "Flour", "Base", "500", "1000"));
		root.appendChild(createIngredients(doc, "2", "Sugar", "Sweetener", "600", "800"));
		root.appendChild(createIngredients(doc, "3", "Milk", "Dairy", "300", "500"));

		Element element = (Element) doc.getElementsByTagName("ingredients").item(0);
		Comment comment = doc.createComment("Ingredients");
		element.getParentNode().insertBefore(comment, element);

		// products

		String[] t = {"pasta", "italian"};
        root.appendChild(createProduct(doc, "1", "Spaghetti", "2400", t, "Classic spaghetti with Bolognese sauce."));
        String[] t2 = {"salad"};
        root.appendChild(createProduct(doc, "2", "Ceasar salad", "2200", t2, "Healthy salad with grilled chicken and Caesar dressing."));
        String[] t3 = {"japanese", "sushi"};
        root.appendChild(createProduct(doc, "3", "Sushi box", "3600", t3, "Assorted sushi rolls and sashimi in a combo."));

		element = (Element) doc.getElementsByTagName("product").item(0);
		comment = doc.createComment("Products");
		element.getParentNode().insertBefore(comment, element);

		// made_of

		root.appendChild(createMadeOf(doc, "1", "1"));
		root.appendChild(createMadeOf(doc, "2", "2"));
		root.appendChild(createMadeOf(doc, "3", "3"));

		element = (Element) doc.getElementsByTagName("made_of").item(0);
		comment = doc.createComment("Made of kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// orders

		String[] i = {"Spaghetti Bolognese", "Ceasar salad"};
		root.appendChild(createOrder(doc, "1", "2023.01.15", "4600", i,"Completed"));
		
		String[] i2 = {"Sushi box"};
		root.appendChild(createOrder(doc, "2", "2023.06.15", "3600", i2,"Processing"));
		
		String[] i3 = {"Spaghetti Bolognese"};
		root.appendChild(createOrder(doc, "3", "2023.07.15", "2400", i3,"Shipping"));

		element = (Element) doc.getElementsByTagName("order").item(0);
		comment = doc.createComment("Orders");
		element.getParentNode().insertBefore(comment, element);

		// ordered_items

		root.appendChild(createOrderedItems(doc, "1", "1", "2023.06.12"));
		root.appendChild(createOrderedItems(doc, "2", "2", "2023.08.12"));
		root.appendChild(createOrderedItems(doc, "3", "3", "2023.09.12"));
		
		element = (Element) doc.getElementsByTagName("ordered_items").item(0);
		comment = doc.createComment("Order items kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// customer

        root.appendChild(createCustomer(doc, "1", "1", "John Doe", "12345", "Main street", "10", "2", "06304565434", "john.doe@example.com", "true"));
        root.appendChild(createCustomer(doc, "2", "2", "Alice Smith", "12345", "Maple street", "1", "75", "06304565434", "alice.doe@example.com", "false"));
        root.appendChild(createCustomer(doc, "3", "3", "Bob Johnson", "12345", "Pine street", "10", "24", "06304566666", "bob.johnson@example.com", "true"));

		element = (Element) doc.getElementsByTagName("customer").item(0);
		comment = doc.createComment("Customers");
		element.getParentNode().insertBefore(comment, element);

		// courier

		root.appendChild(createCourier(doc, "1", "1", "Mike Johnson", "06705556665", "Car", "true" ));
		root.appendChild(createCourier(doc, "2", "2", "Sara Davis", "06705557777", "Car", "true" ));
		root.appendChild(createCourier(doc, "3", "3", "Chris Martinez", "06705558888", "Motorbike", "true" ));

		element = (Element) doc.getElementsByTagName("courier").item(0);
		comment = doc.createComment("Couriers");
		element.getParentNode().insertBefore(comment, element);

		// Transform

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

		// File letrehozas

		DOMSource source = new DOMSource(doc);
		File myFile = new File("LEADNI\\2feladat\\DOMParse_RYSNLC\\XML_RYSNLC1.xml");

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);

		transf.transform(source, console);
		transf.transform(source, file);

	}

	private static Node createIngredients(Document doc, String ingredient_id, String name, String type, String purchasePrice,
			String stockQuantity) {

		Element ing = doc.createElement("ingredients");

		ing.setAttribute("ingredient_id", ingredient_id);
		ing.appendChild(createElement(doc, "name", name));
		ing.appendChild(createElement(doc, "type", type));
		ing.appendChild(createElement(doc, "purchasePrice", purchasePrice));
		ing.appendChild(createElement(doc, "stockQuantity", stockQuantity));
		
		return ing;

	}

	private static Node createProduct(Document doc, String product_id, String name,
			String price, String[] type, String description) {

		Element pr = doc.createElement("product");

		pr.setAttribute("product_id", product_id);
		pr.appendChild(createElement(doc, "name", name));
		pr.appendChild(createElement(doc, "price", price));
		
		Node[] node = appendArray(doc, "type", type);

		for (int i = 0; i < type.length; i++) {
			pr.appendChild(node[i]);
		}

		pr.appendChild(createElement(doc, "description", description));
		
		return pr;

	}

	private static Node createMadeOf(Document doc, String ingredient_id, String product_id) {

		Element mo = doc.createElement("made_of");

		mo.setAttribute("ingredient_id", ingredient_id);
		mo.setAttribute("product_id", product_id);

		return mo;

	}

	private static Node createOrder(Document doc, String order_id, String date, 
        String price, String[] item, String status) {

		Element or = doc.createElement("order");

		or.setAttribute("order_id", order_id);
        or.appendChild(createElement(doc, "date", date));
		or.appendChild(createElement(doc, "price", price));

		Node[] node = appendArray(doc, "item", item);

		for (int i = 0; i < item.length; i++) {
			or.appendChild(node[i]);
		}

		or.appendChild(createElement(doc, "status", status));

		return or;

	}

	private static Node createOrderedItems(Document doc, String order_id, String product_id, String orderingDate) {

		Element oi = doc.createElement("ordered_items");

		oi.setAttribute("order_id", order_id);
		oi.setAttribute("product_id", product_id);
		oi.appendChild(createElement(doc, "orderingDate", orderingDate));

		return oi;

	}

	private static Node createCustomer(Document doc, String customer_id, String create_order, String name,
			String postalcode, String street, String houseNumber, String doorNumber, String phoneNumber, String email,
			String regularCustomer) {

		Element cElement = doc.createElement("customer");

		cElement.setAttribute("customer_id", customer_id);
		cElement.setAttribute("create_order", create_order);
		cElement.appendChild(createElement(doc, "name", name));

		Element cim = doc.createElement("address");
		cim.appendChild(createElement(doc, "postalcode", postalcode));
        cim.appendChild(createElement(doc, "street", street));
		cim.appendChild(createElement(doc, "houseNumber", houseNumber));
		cim.appendChild(createElement(doc, "doorNumber", doorNumber));

        cElement.appendChild(cim);

		cElement.appendChild(createElement(doc, "phoneNumber", phoneNumber));
		cElement.appendChild(createElement(doc, "email", email));
		cElement.appendChild(createElement(doc, "regularCustomer", regularCustomer));

		return cElement;

	}

	private static Node createCourier(Document doc, String courier_id, String courier_demand,
	String name, String phoneNumber, String transportType, String isActive) {

		Element c = doc.createElement("courier");

		c.setAttribute("courier_id", courier_id);
		c.setAttribute("courier_demand", courier_demand);
		c.appendChild(createElement(doc, "name", name));
		c.appendChild(createElement(doc, "phoneNumber", phoneNumber));
		c.appendChild(createElement(doc, "transportType", transportType));
		c.appendChild(createElement(doc, "isActive", isActive));

		return c;

	}

	private static Node createElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;

	}

	private static Node[] appendArray(Document doc, String name, String[] value) {

		Element nodes[] = new Element[value.length];

		for (int i = 0; i < value.length; i++) {

			nodes[i] = doc.createElement(name);
			nodes[i].appendChild(doc.createTextNode(value[i]));

		}

		return nodes;

	}

}