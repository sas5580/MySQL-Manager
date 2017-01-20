import java.awt.*;
import javax.swing.*;

public class Screen extends JFrame{

	public JPanel panel;	
	
	public Screen(String title, int width, int height){
		super(title);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - width/2, dim.height/2 - height/2);
		
		panel = new JPanel();
		panel.setLayout(null);
		add(panel);
	}
	
	public void open(){
		setVisible(true);
	}
	
	public void close(){
		setVisible(false);
		dispose();
	}
}
