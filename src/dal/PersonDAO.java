package dal;

import java.util.ArrayList;

public interface PersonDAO 
{
	void create(Person p);
	ArrayList<Person> read();
	void update(Person pold,Person pnew);
	void delete(Person p);
}
