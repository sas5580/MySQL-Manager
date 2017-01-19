import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Display extends JFrame {
	private JLabel item1;
	
	private JTextField item2;
	private JTextField item3;
	private JTextField item4;
	private JPasswordField passwordField;	
	
	public Display(String title, int width, int height){
		super(title);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);	
		//Set Location on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - width/2, dim.height/2 - height/2);
		
		item1 = new JLabel("this is a sentance", SwingConstants.CENTER);
		item1.setToolTipText("This is for hovering");
		add(item1);
		
		///////////
		
		item2 = new JTextField(10);
		add(item2);
		
		item3 = new JTextField("enter text here");
		add(item3);
		
		item4 = new JTextField("uneditable", 20);
		item4.setEditable(false);
		add(item4);
		
		passwordField = new JPasswordField("mypass");
		add(passwordField);		
		
		ActionHandler handler = new ActionHandler();
		item2.addActionListener(handler);
		item3.addActionListener(handler);
		item4.addActionListener(handler);
		passwordField.addActionListener(handler);
		
		
		setVisible(true);
	}
	
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			String string = "";
			if (event.getSource() == item2){
				string = String.format("field 1: %s", event.getActionCommand());
			}
			else if (event.getSource() == item3){
				string = String.format("field 2: %s", event.getActionCommand());
			}
			else if (event.getSource() == item4){
				string = String.format("field 3: %s", event.getActionCommand());
			}
			else if (event.getSource() == passwordField){
				string = String.format("password field is: %s", event.getActionCommand());
			}
			
			JOptionPane.showMessageDialog(null, string);
		}
	}
}
