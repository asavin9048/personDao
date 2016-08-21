package dal;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel
{
	ArrayList<Person> rr = null;
	PersonDAO pd = new PersonDAOMySQL();
	public PersonTableModel() 
	{
		rr = pd.read();
	}
	@Override
	public int getColumnCount() 
	{
		return 4;
	}
	@Override
	public int getRowCount() 
	{
		return rr.size();
	}
	@Override
	public String getColumnName(int col) 
	{
		String[] name = {"ID","FName","LName","Age"};
		return name[col];
	}
	@Override
	public Object getValueAt(int row, int col)
	{
		
		Person p = rr.get(row);
		Object ret = null;
		switch (col) 
		{
			case 0:
				ret = p.id;
				break;
			case 1:
				ret = p.fname;
				break;
			case 2: 
				ret = p.lname;
				break;
			case 3:
				ret = p.age;
				break;
		}
		//System.out.println("GETVALUEAT");
		return ret;
	}
}
