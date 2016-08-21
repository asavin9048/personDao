package dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.thoughtworks.xstream.XStream;

public class PersonDAO_CASV implements PersonDAO
{

//	public static void main(String[] args) throws IOException 
//	{
//		//File f = new File("csv_hand.csv");
//	//	CSVWriter writer = new CSVWriter(new FileWriter("csv_hand.csv"),true, ' '));
//		CSVWriter w = new CSVWriter(new FileWriter(new File("csv_hand.csv")));
//		//Person p = new Person();
//		Person p = new Person();
////		CSVWriter писатель = новый CSVWriter (новый Fi
//	//	CSVWriter reader = new CSVWriter(new FileWriter(arg0));
//		//CSleWriter ( "yourfile.csv"), '\ т');
////	      // Подкормки в массиве (или преобразовать данные в массив)
////	      String [] записи = "первый # второй # третий" .split ( "#");
////	      writer.writeNext (записей);
////		  writer.close ();
//		String[] entries = ("id:"+p.id+" fname:"+p.fname+" lname:"+p.lname
//			+" age:"+p.age).split(" ");
//		w.writeNext(entries);
//		w.close();
//	  //  writer.writeNext(entries);
//		// writer.close();
////		CSVReader reader = null;
////		try 
////		{
////			reader = new CSVReader(new FileReader("csv_hand.csv"));
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		}
////		String [] nextLine;
////	     try 
////	     {
////			while ((nextLine = reader.readNext()) != null) 
////			{
//////			     nextLine[] is an array of values from the line
////				String age = nextLine[3].trim();
////				age = age.substring(0,age.length()-2);
////				Person p = new Person(Integer.parseInt(nextLine[0].trim()),
////						nextLine[1].trim(),
////						nextLine[2].trim(),Integer.parseInt(age));
////				System.out.println(p);
////				
//////			    System.out.println(nextLine[0]);
//////			    break;
////			 }
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
//	}
    private static final String ADDRESS_FILE = "csv_hand.csv";

    public static void main(String[] args) throws IOException 
    {
//    	//Метод update(po,pne)
//    	Person pold = new Person();
//    	Person pnew = new Person(1,"Name","Second_name",23);
//    	File f = new File("f1.txt");
//    	//Создаем новый файл
//    	
//    	if(f.createNewFile())
//    	{
//    		System.out.println("yes");
//    	}
//    	else{
//    		System.out.println("no");
//    	}
//    	
//    	Person p = new Person();
//        //List<String[]> allElements = reader2.readAll();
//        ArrayList<String[]> allElements = new ArrayList<>();
//        String[] entries = ("id:"+p.id+" fname:"+p.fname+" lname:"+p.lname
//    			+" age:"+p.age).split(" ");
//        allElements.add(entries);
//        String old = "id:"+pold.id;
//        String newp = "id:"+pnew.id+" fname:"+pnew.fname+" lname:"+pnew.lname
//    			+" age:"+pnew.age+"\n";
////        FileReader reader = new FileReader(new File("io.txt"));
////        String[] nextLine;
////        CSVReader reader2 = new CSVReader(new FileReader(ADDRESS_FILE));
//
////        String s = "";
////        while ((nextLine = reader.n) != null) {
////          // System.out.println("Name: [" + nextLine[0] + "]\nAddress: [" + nextLine[1] + "]\nEmail: [" + nextLine[2] + "]");
////        }
//        CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE));
//        String[] nextLine;
//        String s = "";
//        while ((nextLine = reader.readNext()) != null) 
//        {
////            System.out.println("Name: [" + nextLine[0] + "]\nAddress: "
////            		+ "[" + nextLine[1] + "]\nEmail: [" + nextLine[2] + "]");
//        	s+=""+nextLine[0]+nextLine[1]+nextLine[2]+nextLine[3];
//        	System.out.println(s);
//        	if(s.indexOf(old)!=-1)
//        	{
//        		System.out.println("Find");
//        		break;
//        	}
//        }
//        s = s.substring(0,s.lastIndexOf(old));
//        s+=newp;
//        while ((nextLine = reader.readNext()) != null) 
//        {
//        	s+=nextLine[0]+nextLine[1]+nextLine[2]+nextLine[3];
//        	System.out.println(s);
//        	
//        }
//        System.out.println("\n\n\n"+s);
//
//
//       // String s = "";
//       // while(sw.)
//        CSVWriter writer = new CSVWriter(new FileWriter(new File(ADDRESS_FILE)));
//        ArrayList<String[]> per = new ArrayList<>();
//        per.add(new String[]{s});
//      //  per[0] = s;
//        //writer.w
//        writer.writeAll(per);
//        writer.close();
////        writer.close();
////        System.out.println("\n\nGenerated CSV File:\n\n");
////        System.out.println(sw.toString());
//
//      //И удаляем его....
//      f.delete();
    	try
    	{
    		XStream xstream = new XStream(); // require XPP3 library

    		xstream.alias("serializableclass",Person.class);
    		Person sclassOut = new Person(100, "1","2",5);
    		String xml = xstream.toXML(sclassOut);}
    		catch(NoClassDefFoundError e)
    		{
    			e.printStackTrace();
    		}
//    	Person p = new Person(1,"Joe", "Walnes",19);
//    	
//    	String xml = xs.toXML(p);
//    	System.out.println(xml);
    	
    }

	@Override
	public void create(Person p) 
	{
		ArrayList<String[]> allElements = new ArrayList<>();
	    String[] entries = ("id:"+p.id+" fname:"+p.fname+" lname:"+p.lname
	    +" age:"+p.age).split(" ");
	    allElements.add(entries);
	    FileWriter sw;
		try 
		{
			sw = new FileWriter(new File("io.txt"));
			CSVWriter writer = new CSVWriter(sw);
		    writer.writeAll(allElements);
		    sw.close();
		    writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<Person> read() {
		ArrayList<Person> pp = new ArrayList<>();
		CSVReader reader = null;
		try 
		{
			
			reader = new CSVReader(new FileReader("csv_hand.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String [] nextLine;
		System.out.println(new Person());
	     try 
	     {
			while ((nextLine = reader.readNext()) != null) 
			{
				String age = nextLine[3].trim();
				age = age.substring(0,age.length()-2);
				Person p = new Person(Integer.parseInt(nextLine[0].trim()),nextLine[1],
						nextLine[2],Integer.parseInt(age));
				System.out.println(p);
				pp.add(p);
			 }
		} catch (IOException e) 
	     {
			e.printStackTrace();
		}
		return pp;
	}

	@Override
	public void update(Person pold, Person pnew) {
		try{
		//Метод update(po,pne)
    	File f = new File("f1.txt");
    	//Создаем новый файл
    	
    	if(f.createNewFile())
    	{
    		System.out.println("yes");
    	}
    	else{
    		System.out.println("no");
    	}
       
        String old = "id:"+pold.id;
        String newp = "id:"+pnew.id+" fname:"+pnew.fname+" lname:"+pnew.lname
    			+" age:"+pnew.age+"\n";
        CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE));
        String[] nextLine;
        String s = "";
        while ((nextLine = reader.readNext()) != null) 
        {
        	s+=""+nextLine[0]+nextLine[1]+nextLine[2]+nextLine[3];
        	System.out.println(s);
        	if(s.indexOf(old)!=-1)
        	{
        		System.out.println("Find");
        		break;
        	}
        }
        s = s.substring(0,s.lastIndexOf(old));
        s+=newp;
        while ((nextLine = reader.readNext()) != null) 
        {
        	s+=nextLine[0]+nextLine[1]+nextLine[2]+nextLine[3];
        	System.out.println(s);
        	
        }
        System.out.println("\n\n\n"+s);
        CSVWriter writer = new CSVWriter(new FileWriter(new File(ADDRESS_FILE)));
        ArrayList<String[]> per = new ArrayList<>();
        per.add(new String[]{s});
        writer.writeAll(per);
        writer.close(); 
        //И удаляем его....
        f.delete();
        reader.close();
        }catch(IOException e)
		{
    	  
		}
		
	}
	@Override
	public void delete(Person p) 
	{
		try{
			//Метод update(po,pne)
	    	File f = new File("f1.txt");
	    	//Создаем новый файл
	    	if(f.createNewFile())
	    	{
	    		System.out.println("yes");
	    	}
	    	else{
	    		System.out.println("no");
	    	}
	        ArrayList<String[]> allElements = new ArrayList<>();
	        String[] entries = ("id:"+p.id+" fname:"+p.fname+" lname:"+p.lname
	    			+" age:"+p.age).split(" ");
	        allElements.add(entries);
	        String old = "id:"+p.id;
	        CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE));
	        String[] nextLine;
	        String s = "";
	        while ((nextLine = reader.readNext()) != null) 
	        {
	        	s+=""+nextLine[0]+nextLine[1]+nextLine[2]+nextLine[3];
	        	System.out.println(s);
	        	if(s.indexOf(old)!=-1)
	        	{
	        		System.out.println("Find");
	        		break;
	        	}
	        }
	        s = s.substring(0,s.lastIndexOf(old));
	        while ((nextLine = reader.readNext()) != null) 
	        {
	        	s+=nextLine[0]+nextLine[1]+nextLine[2]+nextLine[3];
	        	System.out.println(s);
	        	
	        }
	        System.out.println("\n\n\n"+s);
	        CSVWriter writer = new CSVWriter(new FileWriter(new File(ADDRESS_FILE)));
	        ArrayList<String[]> per = new ArrayList<>();
	        per.add(new String[]{s});
	        writer.writeAll(per);
	        writer.close(); //И удаляем его....
	      f.delete();
	      reader.close();
	      }catch(IOException e)
			{
	    	  
			}
		
	}

}
