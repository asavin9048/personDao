package dal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class XPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	protected JButton Create;
	protected JButton Read;
	protected JButton Update;
	protected JButton Delete;
	protected JTextField id;
	protected JTextField fname;
	protected JTextField lname;
	protected JTextField age;
	protected JTable tbl;
	protected PersonTableModel ptm ;
	protected JScrollPane scr;
	protected JLabel Fields;
	protected JButton imp;
	protected JButton exp;
	protected JButton net;
	public XPanel() throws FileNotFoundException
	{
		exp = new JButton("Export");
		imp = new JButton("Import");
		setSize(new Dimension(640, 640));
		setLayout(new GridBagLayout());
		ptm = new PersonTableModel();
		tbl = new JTable(ptm);
		scr = new JScrollPane(tbl);
		scr.setBounds(20, 20, 300, 200);
		id = new JTextField(2);
		fname = new JTextField(6);
		lname = new JTextField(6);
		age = new JTextField(2);
		Fields = new JLabel("Заполните поля");
		add(scr,new GridBagConstraints(0,0
				,4,1,4,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		add(Fields,new GridBagConstraints(0,1,4,1,4,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		Create = new JButton("Create");
		Create.addActionListener(new Create());
		Read = new JButton("Read");
		Read.addActionListener(new Read());
		Update = new JButton("Update");
		Update.addActionListener(new Update());
		Delete = new JButton("Delete");
		Delete.addActionListener(new Delete());
		
		//imp.addActionListener(new xs);
		imp.addActionListener(new Importer());
		String[] strs = {"ID","FName","LName","age"};
		for(int i = 0;i<strs.length;i++)
			add(new JLabel(strs[i]),new GridBagConstraints(i,2
					,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(5, 50, 5, 5),0,0));
		add(fname,new GridBagConstraints(1,3,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 10, 10, 0),0,0));
		add(lname,new GridBagConstraints(2,3,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 10, 10, 0),0,0));
		add(age,new GridBagConstraints(3,3,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 10, 10, 0),0,0));
		add(id,new GridBagConstraints(0,3
				,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(
						0, 10, 10, 0),0,0));
		add(Create,new GridBagConstraints(0,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0,0, 0),0,0));
		add(Read,new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		add(Update,new GridBagConstraints(2,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		add(Delete,new GridBagConstraints(3,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		add(imp,new GridBagConstraints(0,5,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		add(exp,new GridBagConstraints(1,5,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
		//ListenerImport l = new ListenerImport("xml");
		//l.read();
		//l.getJSON();
		//System.out.println(l.text);
		//System.out.println("text"+l.getXml());
		exp.addActionListener(new Exporter());
	}
	class design
	{
		JButton xml;
		JButton JSON;
		JButton CSV;
		JFrame mainframe;
		public design(String s)
		{
			
		}
	}
	public class Importer implements ActionListener
	{
		JButton xml;
		JButton JSON;
		JButton CSV;
		JButton yaml;
		JFrame mainframe;
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			mainframe = new JFrame("Importer");
			mainframe.setLayout(new FlowLayout());
			mainframe.setBounds(600, 600, 50, 150);
			
			JSON = new JButton("JSON");
			xml = new JButton("XML");
			CSV = new JButton("CSV");
			yaml = new JButton("Yaml");		
			mainframe.add(JSON);
			mainframe.add(xml);
			mainframe.add(CSV);
			mainframe.add(yaml);
			
			JSON.addActionListener(new ListenerImport("json"));
			CSV.addActionListener(new ListenerImport("csv"));
			xml.addActionListener(new ListenerImport("xml"));
			yaml.addActionListener(new ListenerImport("yaml"));

			mainframe.setVisible(true);
		}	
	}
	public class Exporter implements ActionListener
	{
		JButton xml;
		JButton JSON;
		JButton CSV;
		JButton yaml;
		JFrame mainframe;
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			mainframe = new JFrame("Export");
			mainframe.setLayout(new FlowLayout());
			mainframe.setBounds(600, 600, 50, 150);
			
			JSON = new JButton("JSON");
			xml = new JButton("xml");
			CSV = new JButton("CSV");
			yaml = new JButton("Yaml");
			XSave.toJSON(ptm.rr);
			JSON.addActionListener(new ListenerExport( XSave.toJSON(ptm.rr),"json"));
			CSV.addActionListener(new ListenerExport( XSave.toCSV(ptm.rr),"csv"));
			xml.addActionListener(new ListenerExport(XSave.toXml(ptm.rr),"xml"));
			yaml.addActionListener(new ListenerExport(XSave.toYaml(ptm.rr),"yaml"));
			mainframe.add(JSON);
			mainframe.add(xml);
			mainframe.add(CSV);
			mainframe.add(yaml);
			mainframe.setVisible(true);
		}	
	}
	public class ListenerImport implements ActionListener
	{
		String type;
		Scanner keyboard;
		File file;
		public String text;
		public ListenerImport(String s) 
		{
			text = "";
			type = s;
			}
		public void read()
		{
			text = "";
			Scanner keyboard = null;
			try {
				keyboard = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while(keyboard.hasNext())
			{
				text+=keyboard.next();
			}
			keyboard.close();
		}
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			
			JFileChooser fc = new JFileChooser("Открыть");
			fc.showOpenDialog(null);
			file = fc.getSelectedFile();
			read();
			read();
			switch (type) {
			case "xml":
				try {
					getXml();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "csv":
				try {
					getCSV();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "json":
				try {
					getJSON();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "yaml":
				getYaml();
				break;
			default:
				JOptionPane.showMessageDialog(null,"Неизвестная ошибка!", "Error",JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		public void getXml() throws FileNotFoundException
		{
			String s = text;
			ArrayList<Person>pp = new ArrayList<>();
			while(s!=null&&!s.equals("")&&!s.equals("</xml>"))
			{
				s = s.substring(s.indexOf("n")+1);
				int id = Integer.parseInt(s.substring(s.indexOf("=")+1, s.indexOf("n")));
				String fname = s.substring(s.indexOf("e=")+2,s.indexOf("s"));
				String lname = s.substring(s.indexOf("secondname=")+11,s.indexOf("age"));
				int age = Integer.parseInt(s.substring(s.indexOf("age=")+4,s.indexOf(">")));
				Person p = new Person(id, fname, lname, age);
				
				pp.add(p);
				s = s.substring(s.indexOf("n>")+2);
			}
			for (Person p : pp) 
			{
				System.out.println(p);
				
				read();
			}
		}
		void getCSV() throws FileNotFoundException
		{
			String s = text;
			ArrayList<Person>pp = new ArrayList<>();
			while(s!=null&&!s.equals("")&&!s.equals("</xml>"))
			{
				int id = Integer.parseInt(s.substring(0,s.indexOf(",")));
				s = s.substring(s.indexOf(",")+1);
				String fname = s.substring(0,s.indexOf(","));
				s = s.substring(s.indexOf(",")+1);
				String lname = s.substring(0,s.indexOf(","));
				s = s.substring(s.indexOf(",")+1);
				int age = Integer.parseInt(s.substring(0,s.indexOf(";")));
				s = s.substring(s.indexOf(";")+1);
				Person p = new Person(id, fname, lname, age);
				pp.add(p);
			}
			for (Person p : pp) 
			{
				System.out.println(p);
				ptm.pd.create(p);
				read();
			}
		}
		void getJSON() throws FileNotFoundException
		{
			String s = text;
			ArrayList<Person>pp = new ArrayList<>();
			while(s!=null&&!s.equals("")&&!s.equals("</xml>"))
			{
				int id = Integer.parseInt(s.substring(s.indexOf(":")+1,s.indexOf("name")));
				s = s.substring(s.indexOf("name")+1);
				String fname = s.substring(s.indexOf(":")+1,s.indexOf("s"));
				s = s.substring(s.indexOf("second_name:")+1);
				String lname = s.substring(s.indexOf(":")+1,s.indexOf("age"));
				s = s.substring(s.indexOf("age")+1);
				int age = Integer.parseInt(s.substring(s.indexOf(":")+1,s.indexOf(";")));
				s = s.substring(s.indexOf(";")+1);
				Person p = new Person(id, fname, lname, age);
				pp.add(p);
				ptm.pd.create(p);
				ptm.pd.read();
			}
			for (Person p : pp) 
			{
				ptm.pd.create(p);
				ptm.pd.read();
				read();
			}
		}
		void getYaml()
		{
			System.out.println("LOLOL");
			String s = text;
			ArrayList<Person>pp = new ArrayList<>();while(!s.equals(""))
			{
				int id = Integer.parseInt(s.substring(s.indexOf("id")+3,s.indexOf("name:")));
				String fname = s.substring(s.indexOf("name:")+5,s.indexOf("second_name:"));
				String lname =  s.substring(s.indexOf("second_name:")+12,s.indexOf("age:"));
				String split = "===================================";
				int age = Integer.parseInt(s.substring(s.indexOf("age:")+4,s.indexOf(split)));
							
				s = s.substring(s.indexOf(split)+split.length());
				
				Person p = new Person(id, fname, lname, age);
				pp.add(p);
				//System.out.println(i++);
				//System.out.println(s);
				//pp.add(p);
//				ptm.pd.create(p);
//				ptm.pd.read();
				//break;
			}
			for (Person p : pp) 
			{
				System.out.println(p);
				ptm.pd.create(p);
				ptm.pd.read();
				tbl.repaint();
				tbl.revalidate();
				read();
			}
		}
	}
	public class ListenerExport implements ActionListener
	{
		String type;
		String res;
		public ListenerExport(String s1,String s2)
		{
			this.res = s1;
			this.type = s2;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			File newFile = new File("newfile.txt");
			FileOutputStream fout;
			try 
			{
				//очистка
				fout = new FileOutputStream(newFile);
				byte[] buffer = res.getBytes();
				fout.write(buffer, 0, buffer.length);
				//работа с файлом
				byte[] xbuf = res.getBytes();
				fout.write(xbuf, 0, xbuf.length);
		        JFileChooser fc = new JFileChooser();
		        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
		            try ( FileWriter fw = new FileWriter(fc.getSelectedFile()) ) {
		                fw.write(res);
		            }
		            catch ( IOException e ) {
		                System.out.println("Всё погибло!");
		            }
		        }
			}
			catch (FileNotFoundException e2) 
			{
				e2.printStackTrace();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	public class Create implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int id1 = 0;
			String fname1 = "";
			String lname1 = "";
			int age1 = 0;
			try
			{
				id1 = Integer.parseInt(id.getText());
				age1 = Integer.parseInt(age.getText());
			}
			catch(NumberFormatException exc)
			{
				JOptionPane.showMessageDialog(null,"miss");
				JLabel lbl = new JLabel("Что-то не так введено");
				lbl.setForeground(Color.red);
				add(lbl,new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.NORTH,
						GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
				return;
			}
			System.out.println("norm");
			fname1 = fname.getText();
			lname1 = lname.getText();
			Person pp = new Person(id1,fname1,lname1,age1);
			ptm.pd.create(pp);
			ptm.pd.read();
		}	
	}
	public class Read implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ptm.pd.read();
			ptm.rr = ptm.pd.read();
			tbl.repaint();
		}
	}
	public class Update implements ActionListener
	{
		protected int id12;
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			id12 = 0;
			String fname1 = fname.getText();
			String lname1 = lname.getText();
			int age1 = 0;
			int id1;
			try
			{
				id1 = Integer.parseInt(id.getText());
				age1 = Integer.parseInt(age.getText());
			}
			catch(NumberFormatException exc)
			{
				JOptionPane.showMessageDialog(null,"miss");
				JLabel lbl = new JLabel("Что-то не так введено");
				lbl.setForeground(Color.red);
				add(lbl,new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.NORTH,
						GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
				return;
			}
			Person PersonOld = new Person(id1, "", "", 0);
			Person PersonNew = new Person(id1, fname1, lname1, age1);
			ptm.pd.update(PersonOld, PersonNew);
			tbl.repaint();
		}
	}
	public class Delete implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int id1 = 0;
			try
			{
				id1 = Integer.parseInt(id.getText());
				
			}
			catch(NumberFormatException exc)
			{
				JOptionPane.showMessageDialog(null,"Ошибка");
				JLabel lbl = new JLabel("Что-то не так введено");
				lbl.setForeground(Color.red);
				add(lbl,new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.NORTH,
						GridBagConstraints.HORIZONTAL,new Insets(0, 0, 0, 0),0,0));
				return;
			}
			Person p = new Person(id1, "", "", 0);
			ptm.pd.delete(p);
			tbl.repaint();
		}
	}
}