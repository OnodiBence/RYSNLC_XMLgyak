package RYSNLC_1025.SaxRYSNLC;

import java.io.File;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;


public class XsdRYSNLC {

	public static void main(String[] args) {

		if(validateXMLSchema())
			System.out.println("XSD Validation successful!");
		else
			System.out.println("Unsuccessful validation!");
		
	}

	public static boolean validateXMLSchema() {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File("RYSNLC_kurzusfelvetel.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File("RYSNLC_kurzusfelvetel.xml")));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

}