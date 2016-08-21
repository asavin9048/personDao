package dal;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
public class xmlexample 
{
	public static String[][] tutorials = {{"Abstract factory","Factory method"},{"Point to Point","Publich/Subcribe"}};
	public static String generate() throws IOException
	{
		Document doc = new Document();
		Element chanelE = new Element("Persons");
		Element person = new Element("Person");
		doc.setRootElement(chanelE);
		Element id = new Element("id");
		id.addContent(""+1);
		person.setAttribute(new Attribute("id", ""+1));
		person.setAttribute(new Attribute("name", "Vasia"));
		person.setAttribute(new Attribute("second_name", "Pupkin"));
		person.setAttribute(new Attribute("age", "18"));
		chanelE.addContent(person);
		person = new Element("Person");
		person.setAttribute(new Attribute("id", ""+2));
		person.setAttribute(new Attribute("name", "2"));
		person.setAttribute(new Attribute("second_name", "3"));
		person.setAttribute(new Attribute("age", "4"));
		chanelE.addContent(person);
		XMLOutputter outputter= null;
		outputter = new XMLOutputter(Format.getPrettyFormat());
		FileWriter writer = new FileWriter("example.xml");
		outputter.output(doc, writer);
		return(new XMLOutputter(Format.getPrettyFormat())).outputString(doc);
	}
	public static void main(String[] args) throws IOException 
	{
		System.out.println(generate());
	}

}
