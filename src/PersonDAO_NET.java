package dal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

public class PersonDAO_NET implements PersonDAO 
{
	//Порт 6666
	PersonDAO pd;
	DataOutputStream out;
	Socket soket;
	DataInputStream in;
	public PersonDAO_NET() 
	{
		// запускаем сокет
		try 
		{
			soket = new Socket("127.0.01",6666);
			out = new DataOutputStream(soket.getOutputStream());
			in = new DataInputStream(soket.getInputStream());
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String get()
	{
		String res = "";
		try {
			res = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public void send(String s)
	{
		try {
			out.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String toJSON(Person p)
	{
		String res = "";
		Gson g = new Gson();
		res = g.toJson(p, Person.class);
		return res;
	}
	public Person fromJSON(String s)
	{
		Person res = null;
		Gson g = new Gson();
		res = g.fromJson(s, Person.class);
		return res;
	}
	public static void main(String[] args) 
	{
		PersonDAO_NET p = new PersonDAO_NET();
		p.create(new Person());
	}
	@Override
	public void create(Person p) 
	{
		// конвертируем p в JSON и ,вызываем send(JSON)#отправляем данные на сервер#и получаем их обратно(send)
		
		send("CREATE");
		send(toJSON(p));
		String s = get();
		Person pers = fromJSON(s);
		System.out.println(pers);
		
	}

	@Override
	public ArrayList<Person> read() 
	{
		ArrayList<Person>p = new ArrayList<>();
		send("READ");
		send("");
		String s = get();
		while(!s.equals(""))
		{
			String onePersJSON = s.substring(0,s.indexOf("}")+1);
			p.add(fromJSON(onePersJSON));
			s = s.substring(s.indexOf("}")+1);
		}
		return p;
	}
	@Override
	public void update(Person pold, Person pnew) 
	{
		send("UPDATE");
		send(toJSON(pold));
		send(toJSON(pnew));
		String s = get();
		Person pRes = fromJSON(s);
		System.out.println(pRes);
	}
	@Override
	public void delete(Person p) 
	{
		send("DELETE");
		send(toJSON(p));
		String s = get();
		Person pRes = fromJSON(s);
		System.out.println(pRes);	
	}

}
