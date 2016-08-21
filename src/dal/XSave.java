package dal;

import java.util.ArrayList;

public class XSave 
{
	public static String toXml(ArrayList<Person>pp) 
	{
		String res = "<?xml>\n";
		for (Person p : pp) 
		{
			res+=("  <Person id = "+p.id+" name = "+p.fname+" second name = "
					+p.lname+" age = "+p.age+"></Person>\n");
		}
		res+="</xml>";
		return res;
	}
	public static String toCSV(ArrayList<Person>pp) 
	{
		String res = "";
		for (Person p : pp) 
			res+=(p.id+","+p.fname+","+p.lname+","+p.age+";\n\r");
		res+="";
		return res;
	}
	public static String toJSON(ArrayList<Person>pp) 
	{
		String res = "";
		for (Person p : pp) 
		{
			res+=("id:"+p.id+" name:"+p.fname+" second_name:"
					+p.lname+" age:"+p.age+";");
		}
		return res;
	}
	public static String toYaml(ArrayList<Person>pp) 
	{
		String res = "";
		for (Person p : pp) 
		{
			res+=("id:"+p.id+"\nname:"+p.fname+"\nsecond_name:"
					+p.lname+"\nage:"+p.age+"\n===================================\n");
		}
		return res;
	}
}
