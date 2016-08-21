import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Timer;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import dal.Person;
import dal.PersonDAO;
import dal.PersonDAOMySQL;

public class XServer 
{
	public static void main(String[] args) 
	{
//		Gson g = new Gson();
//		String s ="{\"id\":0,\"fname\":\"123\",\"lname\":\"456\",\"age\":0}{\"id\":1,\"fname\":\"123\",\"lname\":\"456\",\"age\":0}";
//		String onePersJSON = s.substring(0,s.indexOf("}")+1);
//		System.out.println(onePersJSON);
		XServer x = new XServer();
//		x.translate("READ","{\"id\":0,\"fname\":\"123\",\"lname\""
//				+ ":\"456\",\"age\":0}{\"id\":1,\"fname\":\"123\",\"lname\":\"456\",\"age\":0}");
//		Person p = g.fromJson( p1, Person.class);
//		System.out.println(p);
//		new XServer();
	}
	ArrayList<Socket> sokets = new ArrayList<Socket>();
	PersonDAO pd;
	ServerSocket serverS;
	DataInputStream in;
	public XServer()
	{
		//Тут запуск сервера(сокета),DAO и вызов get()
		try 
		{
			serverS = new ServerSocket(6666);
			pd = new PersonDAOMySQL();
			get();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	class TAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			try 
			{
				for (Socket s : sokets) 
				{
					in = new DataInputStream(s.getInputStream());
					if( s.getInputStream().available() > 0 )
					{
						String cmd =  in.readUTF();
						String per = in.readUTF();
						String res = translate(cmd,per);
						System.out.println(res);
//						for(int i = 0; i < sokets.size(); i++)
//						{
//							DataOutputStream out = new DataOutputStream(sokets.get(i).getOutputStream());
//							out.writeUTF(res);
//						}
					}
				}
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	void get()
	{
		//Тут сервер ожидает,переводит(translate(Полученое))и переотправляет(send(Переработаное))
		try 
		{
			while(true)
			{
				Timer tm = new Timer(50, new TAction());
				tm.start();
				Socket soket = serverS.accept();
				sokets.add(soket);
			}
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String translate(String cmd,String personJSON)
	{
		String res = "";
		try{
		//Обрабатываем запрос
		//Переводим из JSON в Person
		Gson g = new Gson();
		Person p = g.fromJson(personJSON, Person.class);
		//И выполняем действия CRUD
		switch(cmd)
		{
			case"CREATE":
				pd.create(p);
				res = g.toJson(p);
				break;
			case"READ":
				ArrayList<Person>persons = pd.read();
				for(Person person:persons)
				{
					res+=g.toJson(person,Person.class);
				}
				System.out.println(res);
				break;
			case"UPDATE":
				String pnew = in.readUTF();
				pd.update(p,g.fromJson(pnew, Person.class));
				res = pnew;
				break;
			case"DELETE":
				pd.delete(p);
				res = g.toJson(p);
				break;
		}
		}
		catch(IOException e)
		{
			
		}
		//И отправляем запрос обратно клиенту
		send(res);
		return res;
	}
	public void send(String s)
	{
		for(int i = 0; i < sokets.size(); i++)
		{
			DataOutputStream out = null;
			try 
			{
				out = new DataOutputStream(sokets.get(i).getOutputStream());
				out.writeUTF(s);
			} catch (IOException e) 
			{
				System.out.println("ERR");
			}
		}
		//Отправляем обработаный запрос клиенту
	}
}
