package dal;

import java.util.ArrayList;
import java.util.List;

public class DataObject 
{
	private String data1 = "Some value";
	 
    private int data2 = 123;
 
    private List<String> list = new ArrayList<String>() {
        {
            add("List item 1");
            add("List item 2");
            add("List item 3");
        }
    };
 
    public String getData1() {
        return data1;
    }
 
    public void setData1(String data1) {
        this.data1 = data1;
    }
 
    public int getData2() {
        return data2;
    }
 
    public void setData2(int data2) {
        this.data2 = data2;
    }
 
    public List<String> getList() {
        return list;
    }
 
    public void setList(List<String> list) {
        this.list = list;
    }
     
    @Override
    public String toString() {
        return "[DataObject: data1=" + data1 + ", data2=" + data2 + ", list="
                + list + "]";
    }
}
