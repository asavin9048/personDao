package dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class PersonDAO_xml_handmade implements PersonDAO
{
	static File f;
	static FileWriter fw;
	static FileOutputStream fout;
	public static void main(String[] args) 
	{
		
	}
	@Override
	public void create(Person p) 
	{
		
		f = new File("xml_hand.xml");
		//создаем копию без конца(</xml>)
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String str = "";
		while(sc.hasNextLine())
		{
			String s1 = sc.nextLine();
			if(s1.equals("</xml>"))
			{
				break;
			}
			str += s1+"\n";
		}
		//и замен€ем...
		System.out.println(str);
//		String b = "<?xml >\n";
		try 
		{
			if(str.indexOf("</xml>")!=-1){
				System.out.println("GGGGGGGGGGGGGGGGGGGG");
				str = str.substring(0,str.indexOf("</xml>")-1);}
	        str += "  <Person id = "+p.id+" name = "+p.fname+" second name = "+p.lname+" age = "+p.age+"></Person>\n</xml>";
//	        fout = new FileOutputStream(f,true);
//			fout.write(xbuf, 0, xbuf.length);
	        fw = new FileWriter(f);
	        fw.write(str);
	        fw.close();
		}catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<Person> read() {
		
		ArrayList<Person> pp = new ArrayList<>();
		f = new File("xml_hand.xml");
		Scanner sc = null;
		try
		{
			sc = new Scanner(f);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		sc.nextLine();
		while(sc.hasNextLine())
		{
			String s1 = sc.nextLine();
			if(s1.equals("</xml>"))
			{
				break;
			}
			int id = Integer.parseInt(s1.substring(s1.indexOf("id = ")+5, s1.indexOf(" name")));
			String fname = s1.substring(s1.indexOf("name = ")+7,s1.indexOf("second"));
			String lname = s1.substring(s1.indexOf("second name = ")+14,s1.indexOf("age"));
			int age = Integer.parseInt(s1.substring(s1.indexOf(" age = ")+7,s1.indexOf(">")));
			Person p = new Person(id, fname, lname, age);
			pp.add(p);
			System.out.println(p);
		}
		return pp;
	}

	@Override
	public void update(Person pold, Person pnew) {
		String old = "  <Person id = "+pold.id;
		String newp = "<Person id = "+pnew.id+" name = "+pnew.fname+" second name = "
				+ pnew.lname+" age = "+pnew.age+"></Person>\n";
		f = new File("xml_hand.xml");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				sstart += sc.nextLine()+"\n";
				if(sstart.indexOf(old)!=-1)
				{
					//“о что надо замен€ть найдено
					break;
				}
			}
			sstart = sstart.substring(0,sstart.lastIndexOf("</Person>")+9);
			sstart+="\n  "+newp;
			sc.nextLine();
			while(sc.hasNextLine())
			{
				sstart+=sc.nextLine()+"\n";
			}
			System.out.println(sstart);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Person p) 
	{
		String old = "<Person id = "+p.id;
		f = new File("xml_hand.xml");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				sstart += sc.nextLine()+"\n";
				if(sstart.indexOf(old)!=-1)
				{
					//“о что надо замен€ть найдено
					break;
				}
			}
			sstart = sstart.substring(0,sstart.lastIndexOf("  "));
			while(sc.hasNextLine())
			{
				sstart+=sc.nextLine()+"\n";
			}
			System.out.println(sstart);
	        fw = new FileWriter(f);
	        fw.write(sstart);
	        fw.close();
			sc.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void del(Person p)
	{
		String old = "<Person id = "+p.id;
		f = new File("xml_hand.xml");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				sstart += sc.nextLine()+"\n";
				if(sstart.indexOf(old)!=-1)
				{
					//“о что надо замен€ть найдено
					break;
				}
			}
			sstart = sstart.substring(0,sstart.lastIndexOf("  "));
			while(sc.hasNextLine())
			{
				sstart+=sc.nextLine()+"\n";
			}
			System.out.println(sstart);
	        fw = new FileWriter(f);
	        fw.write(sstart);
	        fw.close();
			sc.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
