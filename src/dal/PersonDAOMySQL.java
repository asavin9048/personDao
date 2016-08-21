package dal;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAOMySQL implements PersonDAO
{
	
	@Override
	public  ArrayList<Person> read() 
	{
		ArrayList<Person> ar = new ArrayList<Person>();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users","adm","jaguar123");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PERSON");

			while(rs.next())
			{
				ar.add( new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)) ); 
			}
			rs.close();
			st.close();
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return ar;
	}
	public static void init(Person[] pp)
	{
		pp[0] = new Person(10, "Vasia", "Pupkin", 11);
		pp[1] = new Person(17, "Kasia", "Popkin", 68);
		pp[2] = new Person(11, "Basia", "Hupkin", 33);
		pp[3] = new Person(24, "Masia", "Suskin", 29);
		pp[4] = new Person(96, "Dasia", "Dupkin", 20);		
	}
	public void update(Person pold,Person pnew)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users","adm","jaguar123");
			Statement st = con.createStatement();
			st.execute("UPDATE person SET id="+pnew.id+",fname = '"+pnew.fname+"'"
					+ ",lname = '"+pnew.lname+"',age = "+pnew.age+" WHERE id='"+pold.id+"'");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("NF");
		}
		catch (SQLException e) {
			System.out.println("SQLE");
			e.printStackTrace();
		}
	}
	public void add(Person p) 
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users","adm","jaguar123");
			Statement st = con.createStatement();
		st.execute("INSERT INTO person(id, fname, lname, age) VALUES ("+p.id+",\""+p.fname+
				"\",\""+p.lname+"\","+p.age+")");}
		catch(ClassNotFoundException e)
		{
			System.out.println("NF");
		}
		catch (SQLException e) {
			System.out.println("SQLE");
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Person p)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users","adm","jaguar123");
			Statement st = con.createStatement();
			st.execute("DELETE FROM person WHERE id = '"+p.id+"'");
		}
		catch(ClassNotFoundException e){
			
		}
		catch(SQLException e)
		{
			
		}
		
	}
//	public static void main(String[] args) 
//	{
//		ArrayList<Person>pp = new ArrayList<>();
//		pp.add(new Person(1, "2", "3", 4));
//		pp.add(new Person(2, "Vasia", "P", 10));
//		System.out.println(toXml(pp));
//	}
	public void initFromH2(ArrayList<Person> pp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users","adm","jaguar123");
		Statement st = con.createStatement();
		st.execute("DELETE FROM person WHERE id = '99'");
		st.execute("INSERT INTO person(id, fname, lname, age) VALUES (99,\"fASIA\",\"gOPKIN\",18)");
		st.execute("DELETE FROM person WHERE id = '129867'");
		st.execute("UPDATE person SET id='129867' WHERE id='99'");

		ResultSet rs = st.executeQuery("SELECT * FROM PERSON");
		
		int i = 0;
		while(rs.next())
		{
			pp.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			//pp[i++] = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		}
		
		rs.close();
		st.close();
		con.close();
		
	}



	public void print(ArrayList<Person>pp)
	{
		for (Person p : pp) 
		{
			System.out.println(p);
		}
		//		for (int i = 0; i < pp.length; i++) 
		//		{
		//			System.out.println( pp[i] );
		//		}		
	}
	public void printMore21(Person[] pp)
	{
		for (Person p : pp) 
		{
			if(p.age > 21)
			{
				System.out.println(p);
			}
		}
	}
	public String toXml(ArrayList<Person>pp) 
	{
		String res = "<?xml>";
		for (Person p : pp) 
		{
			res+=("  <Person id = "+p.id+" name = "+p.fname+" second name = "
					+p.lname+" age = "+p.age+"></Person>");
		}
		res+="</xml>";
		return res;
	}
	public String toCSV(ArrayList<Person>pp) 
	{
		String res = "";
		for (Person p : pp) 
			res+=(p.id+","+p.fname+","+p.lname+","+p.age+";\n\r");
		res+="";
		return res;
	}
	public String toJSON(ArrayList<Person>pp) 
	{
		String res = "";
		for (Person p : pp) 
		{
			res+=("id:"+p.id+" name:"+p.fname+" second_name:"
					+p.lname+" age:"+p.age+";");
		}
		return res;
	}
	@Override
	public void create(Person p) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users","adm","jaguar123");
			Statement st = con.createStatement();
			st.execute("INSERT INTO person(id, fname, lname, age) VALUES ("+p.id+",\""+p.fname+"\",\""
			+p.lname+"\","+p.age+")");
		}
		catch(ClassNotFoundException e){
			
		}
		catch(SQLException e)
		{
			
		}
		
	}
	/*
	@Override
	public ArrayList<Person> read() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
