package dal;

import java.io.*;
import java.util.*;
public class PersonDAO_CSV_h implements PersonDAO 
{
	//Готово
	static File f;
	static FileWriter fw;
	static FileOutputStream fout;
	public static void main(String[] args) {
	}
	
	@Override
	public void create(Person p) 
	{
		//Готово
		try 
		{
			f = new File("csv_hand.csv");
			
			String str = p.id+" , "+p.fname+" , "+p.lname+" , "+p.age+" ; ";
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
		f = new File("csv_hand.csv");
		Scanner sc = null;
		try
		{
			sc = new Scanner(f);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while(sc.hasNext())
		{
			int id = sc.nextInt();
			System.out.println(id);
			sc.next();
			String fname = sc.next();
			System.out.println(fname);
			sc.next();
			String lname = sc.next();
			System.out.println(lname);
			sc.next();
			int age = sc.nextInt();
			System.out.println(age);
			System.out.println("================");
			sc.next();//sc.next();
			Person p1 = new Person(id, fname, lname, age);
			pp.add(p1);
		}
		sc.close();
		return pp;
	}

	@Override
	public void update(Person pold, Person pnew) 
	{
		//готово
		String old = pold.id+" , "+pold.fname+" , "+pold.lname+" , "+pold.age+" ;";
		String newp = pnew.id+" , "+pnew.fname+" , "+pnew.lname+" , "+pnew.age+" ;";
		f = new File("csv_hand.csv");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNext())
			{
				sstart += sc.next()+" ";
				if(sstart.indexOf(old)!=-1)
				{
					System.out.println("FIND");
					//То что надо заменять найдено
					break;
				}
			}
			
			sstart = sstart.substring(0,sstart.lastIndexOf(old));
			
			//System.out.println(sstart);
			sstart+=newp;
			
			while(sc.hasNext())
			{
				sstart+=sc.next()+" ";
			}
			System.out.println(sstart);
			//System.out.println(sstart);
			//System.out.println(sstart);
			byte[]x = sstart.getBytes();
			fout = new FileOutputStream(f);
			fout.write(x, 0, x.length);
			fw = new FileWriter(f,true);
		    fw.write(sstart);
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
		String old = p.id+" , "+p.fname+" , "+p.lname+" , "+p.age+" ;";
		f = new File("csv_hand.csv");
		try 
		{
			String sstart = "";
			Scanner sc = new Scanner(f);
			while(sc.hasNext())
			{
				if(sstart.indexOf(old)!=-1)
				{
					System.out.println("FIND");
					//То что надо удалять найдено
					break;
				}
				sstart += sc.next()+" ";
			}
			sstart = sstart.substring(0,sstart.indexOf(old));
			System.out.println(sstart);
			while(sc.hasNext())
			{
				sstart+=sc.next()+" ";
			}
			System.out.println(sstart);
			byte[]x = sstart.getBytes();
			fout = new FileOutputStream(f);
			fout.write(x, 0, x.length);
			fw = new FileWriter(f,true);
			   fw.write(sstart);
			}catch(Exception e)
			{}
	}
}
