package p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ConfigReader 
{
    int id = 0;
    String fname = "Vasia";
    String lname = "Fedia";
    int age = 0;
    @Override
    public String toString() 
    {
        return "Id : "+id+
            " \nFName : "+fname+
            " \nLname : "+lname+
            " \nAge : "+age;
    }
    public static void main(String[] args) throws FileNotFoundException 
    {
        XStream xs = new XStream(new DomDriver());
        FileInputStream fis = new FileInputStream("config.txt");
        xs.aliasField("datasource-name", ConfigReader.class, "datasourcename");
        xs.alias("Person", ConfigReader.class);
        //   System.out.println(xs.toXML(fis));
        ConfigReader r = (ConfigReader)xs.fromXML(fis);
        ConfigReader w = new ConfigReader();
        String s = xs.toXML(w);
        System.out.println(xs.toXML(w));
        String st = "123";
      //  System.out.println(xs.toXML(r));
     //  System.out.println(r.toString());
    }
}
