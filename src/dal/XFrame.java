package dal;

import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class XFrame extends JFrame
{
	public XFrame() throws FileNotFoundException
	{
		setSize(new Dimension(660,660));
		add(new XPanel());
		setTitle("DB manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//pack();
	}
}
