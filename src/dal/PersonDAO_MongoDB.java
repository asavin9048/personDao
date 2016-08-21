package dal;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.*;
public class PersonDAO_MongoDB implements PersonDAO
{
	public static void main(String[] args) throws UnknownHostException 
	{
		Person p = new Person(70, "Вася", "Пупкин", 30);
		PersonDAO_MongoDB n = new PersonDAO_MongoDB();
		n.create(p);
		p = new Person(90, "1", "2", 12);
		n.create(p);
		p = new Person(108, "66", "7", 172);
		n.create(p);
		Person pn = new Person(203, "Имя", "Фамилия", 8);
		n.update(p, pn);
		ArrayList<Person> persons = n.read();
		for (Person person : persons) 
		{
			System.out.println("pp"+person);
		}
	}
	@Override
	public void create(Person p)
	{
		MongoClient m = null;
		try {
			m = new MongoClient("127.0.0.1", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        DB db = m.getDB( "hellomongodb1" );
        DBCollection collection = db.getCollection( "hello" );
        DBObject helloDbObject = BasicDBObjectBuilder.start()
        .append("_id", p.id).append("fname", p.fname)
        .append("lname",p.lname).append("age", p.age).get();
        collection.save(helloDbObject);
        DBCursor newobj = collection.find();
        System.out.println(newobj.toString());
	}
	@Override
	public ArrayList<Person> read() 
	{
		ArrayList<Person> p = new ArrayList<>();
		MongoClient m = null;
		try 
		{
			m = new MongoClient("127.0.0.1", 27017);
		}catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
        DB db = m.getDB( "hellomongodb1" );
        DBCollection collection = db.getCollection( "hello" );
		DBCursor cur = collection.find();
        int id = Integer.parseInt((cur.next().get("_id").toString()));
        String fname = (cur.getQuery().toString());
        System.out.println(fname);
        System.out.println("_id"+id);
        cur = collection.find();
        while(cur.hasNext()) 
        {
        	DBObject dbo = cur.next();
        	Person per = new Person(
        		Integer.parseInt((dbo.get("_id").toString())),
    			dbo.get("fname").toString(),
    			dbo.get("lname").toString(), 
    			Integer.parseInt(dbo.get("age").toString()));
        	p.add(per);
        	System.out.println("per "+per);
        }	
		return p;
	}
	@Override
	public void update(Person pold, Person pnew) 
	{
		MongoClient m = null;
		try {
			m = new MongoClient("127.0.0.1", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        DB db = m.getDB( "hellomongodb1" );
        DBCollection collection = db.getCollection( "hello" );
        BasicDBObject newdb = new BasicDBObject();
        newdb.put("fname", pnew.fname);
        newdb.put("lname", pnew.lname);
        newdb.put("age", pnew.age);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", pold.id);
        collection.update(searchQuery,newdb);
	}
	@Override
	public void delete(Person p) 
	{
		MongoClient m = null;
		try 
		{
			m = new MongoClient("127.0.0.1", 27017);
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
        DB db = m.getDB( "hellomongodb1" );
        DBCollection collection = db.getCollection( "hello" );
        BasicDBObject query = new BasicDBObject();
        query.put("_id", p.id);
        collection.find();
        collection.remove(query);
		DBCursor cur = collection.find();
		while(cur.hasNext()) 
		{
			System.out.println("del"+cur.next());
		}  
	}
}
