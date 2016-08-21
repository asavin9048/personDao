package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import p1.ConfigReader;

public class PersonDAO_xml_auto implements PersonDAO
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		ArrayList<Person> pp = new ArrayList<>();
		File f = new File("xml.txt");
		XStream xs = new XStream(new DomDriver());
//		xs.aliasField("datasource-name", ConfigReader.class, "datasourcename");
        xs.alias("Person", Person.class);
        FileInputStream fis = null;
        
		try {
			Scanner sc = new Scanner(f);
			String s = "";
			while(sc.hasNextLine())
			{
				s+=sc.nextLine()+"\n";
			}
			while(!s.equals(""))
			{
				Person p = (Person)xs.fromXML(s.substring(s.indexOf("<Person>")
						,s.indexOf("</Person>")+9));
				System.out.println(p);
				s = s.substring(s.indexOf("</Person>")+10);

			}
//			fis = new FileInputStream("config.txt");
//			 xs.aliasField("datasource-name", ConfigReader.class, "datasourcename");
//		        xs.alias("Person", ConfigReader.class);
//		        String s = xs.toXML(p1);
//		        System.out.println(s);
//		        FileOutputStream fout = new FileOutputStream(f,true);
//		        fout.write(s.getBytes(), 0, s.getBytes().length);
//		        FileWriter fw = new FileWriter(f,true);
//		        fw.write(s);
//		        fw.close();
//		        fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void create(Person p1) 
	{
		File f = new File("xml.txt");
		XStream xs = new XStream(new DomDriver());
        FileInputStream fis = null;
		try {
			fis = new FileInputStream("config.txt");
			 xs.aliasField("datasource-name", ConfigReader.class, "datasourcename");
		        xs.alias("Person", ConfigReader.class);
		        String s = xs.toXML(p1);
		        System.out.println(s);
		        FileOutputStream fout = new FileOutputStream(f,true);
		        fout.write(s.getBytes(), 0, s.getBytes().length);
		        FileWriter fw = new FileWriter(f,true);
		        fw.write(s);
		        fw.close();
		        fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public ArrayList<Person> read() 
	{
		ArrayList<Person> pp = new ArrayList<>();
		File f = new File("xml.txt");
		XStream xs = new XStream(new DomDriver());
//		xs.aliasField("datasource-name", ConfigReader.class, "datasourcename");
        xs.alias("Person", Person.class);
        FileInputStream fis = null;
        
		try {
			Scanner sc = new Scanner(f);
			String s = "";
			while(sc.hasNextLine())
			{
				s+=sc.nextLine()+"\n";
			}
			while(!s.equals(""))
			{
				Person p = (Person)xs.fromXML(s.substring(s.indexOf("<Person>")
						,s.indexOf("</Person>")+9));
				System.out.println(p);
				s = s.substring(s.indexOf("</Person>")+10);
				pp.add(p);

			}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return pp;
	}
	@Override
	public void update(Person pold, Person pnew) 
	{

	}
	@Override
	public void delete(Person p) 
	{
		// TODO Auto-generated method stub

	}
}
