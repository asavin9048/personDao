package dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

//import com.google.gson.stream.JsonWriter;

public class PersonDAO_JsonLib implements PersonDAO 
{
	private static final String FILENAME = "file.json";
	public static void main(String[] args) throws IOException 
	{
////		 DataObject object = new DataObject();
////		 Object c = new Object();
////		
////	        Gson gson = new Gson();
////	        //gson.fromJson(str, class.class)
////	        String string = gson.toJson(object);
////	        try (FileWriter fileWriter = new FileWriter(FILENAME,true)) {
////	            fileWriter.write(string);
////	            fileWriter.write(string);
////	            fileWriter.write(string);
////	            
////	        } catch (IOException ex) {
////	            Logger.getLogger(PersonDAO_JsonLib.class.getName())
////	                    .log(Level.SEVERE, null, ex);
////	        }
//		Person p = new Person(1, "2", "3", 4);
//		Gson gson = new Gson();
//        //gson.fromJson(str, class.class)
//        String string = gson.toJson(p);
//        System.out.println(string);
		Person pold = new Person(1,"2","3",4);
		PersonDAO_JsonLib pd = new PersonDAO_JsonLib();
		pd.create(pold);
		pd.create(pold);
		pd.create(pold);
		//{\"id\":"+pold.id+",";
//		String old = "{\"id\":"+pold.id+",";
//		ArrayList<Person>pp = new ArrayList<>();
//		Gson gson = new Gson();
//		String s = "";
//		Scanner sc = new Scanner(new File(FILENAME));
//		while(sc.hasNextLine())
//		{
//			
//			BufferedReader reader;
//			String s1 = sc.nextLine();
//			Person object = gson.fromJson(s1, Person.class);
//			System.out.println(object);
//			pp.add(object);
//			s+=s1;
//			if(s.indexOf(old)!=-1)
//			{
//				System.out.println("Found");
//				break;
//			}
//	            
//		}
//		s = s.substring(0,s.lastIndexOf("{"));
//		while(sc.hasNextLine())
//		{
//			
//			BufferedReader reader;
//			String s1 = sc.nextLine();
//			Person object = gson.fromJson(s1, Person.class);
//			//System.out.println(object);
//			pp.add(object);
//			s+=s1;
////			if(s.indexOf(old)!=-1)
////			{
////				System.out.println("Found");
////				break;
////			}
//	            
//		}
//		System.out.println(s);
//		 try (FileWriter fileWriter = new FileWriter(FILENAME)) {
//         fileWriter.write(s);
//        // fileWriter.write(string);
//        // fileWriter.write(string);
//       //  
//     } catch (IOException ex) {
//         ex.printStackTrace();
//     }
	    }
		
//	public static void main(String[] args) {
//		PersonDAO_JsonLib pd = new PersonDAO_JsonLib();
//	}
	@Override
	public void create(Person p) 
	{
		Gson gson = new Gson();
        //gson.fromJson(str, class.class)
        String string = gson.toJson(p)+"\n";
        System.out.print(string);
        try (FileWriter fileWriter = new FileWriter(FILENAME,true)) 
        {
            fileWriter.write(string);
            fileWriter.write(string);
            fileWriter.write(string);
        } catch (IOException ex) 
        {
            Logger.getLogger(PersonDAO_JsonLib.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
	}
	@Override
	public ArrayList<Person> read() 
	{
		ArrayList<Person> pp = new ArrayList<>();
		Gson gson = new Gson();
		String s = " ";
		Scanner sc = null;
		try {
			sc = new Scanner(new File(FILENAME));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(sc.hasNextLine())
		{
			BufferedReader reader;
			String s1 = sc.nextLine();
			Person object = gson.fromJson(s1, Person.class);
			System.out.println(object);
			pp.add(object);
	            
		}
		return pp;
	}

	@Override
	public void update(Person pold, Person pnew) 
	{
		String newp = "{\"id\":"+pnew.id+",\"fname\":\""+pnew.fname
				+"\",\"lname\":\""+pold.lname+"\",\"age\":"+pnew.age+"}";
		//{\"id\":"+pold.id+",";
		String old = "{\"id\":"+pold.id+",";
		ArrayList<Person>pp = new ArrayList<>();
		Gson gson = new Gson();
		String s = "";
		Scanner sc = null;
		try 
		{
			sc = new Scanner(new File(FILENAME));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while(sc.hasNextLine())
		{
			String s1 = sc.nextLine();
			Person object = gson.fromJson(s1, Person.class);
			System.out.println(object);
			pp.add(object);
			s+=s1;
			if(s.indexOf(old)!=-1)
				break;
		}
		s = s.substring(0,s.lastIndexOf("{"));
		s+=newp;
		while(sc.hasNextLine())
		{
			
			BufferedReader reader;
			String s1 = sc.nextLine();
			Person object = gson.fromJson(s1, Person.class);
			pp.add(object);
			s+=s1;
		}
		System.out.println(s);
		try(FileWriter fileWriter = new FileWriter(FILENAME)) 
		{
         fileWriter.write(s);
        } catch (IOException ex) {
         ex.printStackTrace();
     }

	}

	@Override
	public void delete(Person p) {
		Person pold = new Person(1,"2","3",4);
		//{\"id\":"+pold.id+",";
		String old = "{\"id\":"+p.id+",";
		ArrayList<Person>pp = new ArrayList<>();
		Gson gson = new Gson();
		String s = "";
		Scanner sc = null;
		try {
			sc = new Scanner(new File(FILENAME));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(sc.hasNextLine())
		{
			
			BufferedReader reader;
			String s1 = sc.nextLine();
			Person object = gson.fromJson(s1, Person.class);
			System.out.println(object);
			pp.add(object);
			s+=s1;
			if(s.indexOf(old)!=-1)
			{
				System.out.println("Found");
				break;
			}
	            
		}
		s = s.substring(0,s.lastIndexOf("{"));
		while(sc.hasNextLine())
		{
			
			BufferedReader reader;
			String s1 = sc.nextLine();
			Person object = gson.fromJson(s1, Person.class);
			//System.out.println(object);
			pp.add(object);
			s+=s1;
//			if(s.indexOf(old)!=-1)
//			{
//				System.out.println("Found");
//				break;
//			}
	            
		}
		System.out.println(s);
		 try (FileWriter fileWriter = new FileWriter(FILENAME)) {
         fileWriter.write(s);
        // fileWriter.write(string);
        // fileWriter.write(string);
       //  
     } catch (IOException ex) {
         ex.printStackTrace();
     }

	}

}
