package p1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;

public class Writer 
{
	public static void main(String[] args) {
        Employee e = new Employee();

        //Установка значений свойств через set-методы
        //Замечание: это также можно было сделать через конструктор
        //Однако, нашей целью является демонстрация того, что XStream
        //способна сериализовывать объекты без конструктора
        e.setName("Jack");
        e.setDesignation("Manager");
        e.setDepartment("Finance");

        //Объект-сериализатор
        XStream xs = new XStream();

        //Write to a file in the file system
        try {
            FileOutputStream fs = new FileOutputStream("c:/temp/employeedata.txt");
            xs.toXML(e, fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
