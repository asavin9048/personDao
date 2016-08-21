package dal;
import java.io.*;
import java.util.*;
public class PersonDAO_JS_hm implements PersonDAO
{
	static File f;
	static FileWriter fw;
	static FileOutputStream fout;
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException 
	{}
	static void c(Person p)
	{
		try 
		{
			f = new File("json_hand.JSON");
			
			String str = " { id: "+p.id+" fname: "+p.fname+" lname: "+p.lname+" age: "+p.age+" }; ";
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
	public void create(Person p) 
	{
		try 
		{
			f = new File("json_hand.JSON");
			
			String str = " { id: "+p.id+" fname: "+p.fname+" lname: "+p.lname+" age: "+p.age+" }; ";
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
	public ArrayList<Person> read() {
		
		ArrayList<Person> pp = new ArrayList<>();
		f = new File("json_hand.JSON");
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
			sc.next();sc.next();
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
		String old = " { id: "+pold.id;
		String newp = " { id: "+pnew.id+" fname: "+pnew.fname+" lname: "+pnew.lname+" age: "
		+pnew.age+" }; ";
		f = new File("json_hand.JSON");
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
			sstart = sstart.substring(0,sstart.lastIndexOf(";"));
			System.out.println(sstart);
			sstart+=";"+newp;
			while(sc.hasNext())
			{
				if(sc.next().equals("};"))
				{
					break;
				}
			}
			while(sc.hasNext())
			{
				sstart+=sc.next()+" ";
			}
			System.out.println(sstart);
			System.out.println(sstart);
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
		String old = "id: "+p.id;
		f = new File("json_hand.JSON");
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
			sstart = sstart.substring(0,sstart.lastIndexOf(";"));
			for(int i = 0;i<7;i++)
			{
				sc.next();
			}
			System.out.println("!!!!!!!!");
			while(sc.hasNext())
			{
				sstart+=sc.next()+" ";
			}
			sstart = sstart.substring(0,sstart.lastIndexOf(";"));
			System.out.println(sstart);
			byte[]x = sstart.getBytes();
			fout = new FileOutputStream(f);
			fout.write(x, 0, x.length);
			fw = new FileWriter(f,true);
		    fw.write(sstart);
			}catch(Exception e)
			{	
				
			}
	}
}