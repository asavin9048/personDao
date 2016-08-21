package dal;

import java.io.*;
import java.util.*;

public class PersonDAO_yaml_hm implements PersonDAO{
	static File f;
	static FileWriter fw;
	static FileOutputStream fout;
	public static void main(String[] args) 
	{
		
	}
	@Override
	public void create(Person p) 
	{
		//Готово
		try 
		{
			f = new File("yaml_hand.yaml");
			String str =""
			+ "id: "+p.id+"\n"
			+ "name:"+p.fname+"\n"
			+ "second_name:"+p.lname+"\n"
			+ "age:"+p.age+"\n"
			+ "===================================\n";
	        fw = new FileWriter(f,true);
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
	public ArrayList<Person> read() 
	{
		ArrayList<Person> pp = new ArrayList<>();
		f = new File("yaml_hand.yaml");
		Scanner sc = null;
		try
		{
			sc = new Scanner(f);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while(sc.hasNextLine())
		{
			String i = sc.nextLine();
			int id = Integer.parseInt(i.substring(i.indexOf(":")+1).trim());
			System.out.println(id);
			i = sc.nextLine();
			String fname = i.substring(i.indexOf(":")+1);
			System.out.println(fname);
			i = sc.nextLine();
			String lname = i.substring(i.indexOf(":")+1);
			System.out.println(lname);
			i = sc.nextLine();
			int age = Integer.parseInt(i.substring(i.indexOf(":")+1).trim());
			System.out.println(age);
			sc.nextLine();
			System.out.println("============");
			Person p = new Person(id, fname, lname, age);
			pp.add(p);
		}
		sc.close();
		return pp;
	}
	@Override
	public void update(Person pold, Person pnew) 
	{
		String old = "id:"+pold.id;
		String newp = ""
		+ "id:"+pnew.id+"\n"
		+ "name:"+pnew.fname+"\n"
		+ "second_name:"+pnew.lname+"\n"
		+ "age:"+pnew.age+"\n"
		+ "===================================\n";
		f = new File("yaml_hand.yaml");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				sstart += sc.nextLine()+"\n";
				if(sstart.indexOf(old)!=-1)
				{
					System.out.println("FIND");
					//То что надо заменять найдено
					break;
				}
			}
			for(int i = 0;i<4;i++)
			{
				sc.nextLine();
			}
			sstart = sstart.substring(0,sstart.lastIndexOf(old));
			sstart+=newp;
			while(sc.hasNextLine())
			{
				sstart+=sc.nextLine()+"\n";
			}
			System.out.println(sstart);
			byte[]x = sstart.getBytes();
			fout = new FileOutputStream(f);
			fout.write(x, 0, x.length);
			fw = new FileWriter(f,true);
		    fw.write(sstart);
		    sc.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Person p) 
	{
		String old = "id:"+p.id;
		f = new File("yaml_hand.yaml");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				sstart += sc.nextLine()+"\n";
				if(sstart.indexOf(old)!=-1)
				{
					System.out.println("FIND");
					//То что надо заменять найдено
					break;
				}
			}
			
			for(int i = 0;i<4;i++)
			{
				sc.nextLine();
			}
			sstart = sstart.substring(0,sstart.lastIndexOf(old));
			while(sc.hasNextLine())
			{
				sstart+=sc.nextLine()+"\n";
			}
			sc.close();
			System.out.println(sstart);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
