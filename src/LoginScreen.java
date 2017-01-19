import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends Screen {
	
	private JPanel panel;
	
	private JLabel Label_Prompt;
	private JLabel Label_Server;
	private JLabel Label_IP;
	private JLabel Label_Port;
	private JLabel Label_Table;
	private JLabel Label_User;
	private JLabel Label_Username;
	private JLabel Label_Password;
	
	private JTextField Field_IP;
	private JTextField Field_Port;
	private JTextField Field_Table;
	private JTextField Field_Username;
	
	private JPasswordField Field_Password;
	
	private JButton Button_Connect;
	
	public LoginScreen(int width, int height){
		super("Connect to DB", width, height);
		
		panel = new JPanel();
		panel.setLayout(null);
		add(panel);
		
		Label_Prompt = new JLabel("Please enter fields below to acess the database.");
		Label_Prompt.setBounds(10, 10, 640, 25);
		panel.add(Label_Prompt);
		
		Label_Server = new JLabel("Server Info");
		Label_Server.setBounds(10, 50, 640, 25);
		panel.add(Label_Server);
		
		Label_IP = new JLabel("IP :");
		Label_IP.setBounds(40, 80, 80, 25);
		panel.add(Label_IP);		
		Field_IP = new JTextField(20);
		Field_IP.setBounds(130, 80, 120, 25);
		panel.add(Field_IP);
		
		Label_Port = new JLabel("Port :");
		Label_Port.setBounds(40, 110, 80, 25);
		panel.add(Label_Port);		
		Field_Port = new JTextField(20);
		Field_Port.setBounds(130, 110, 120, 25);
		panel.add(Field_Port);
		
		Label_Table = new JLabel("Table Name :");
		Label_Table.setBounds(40, 140, 80, 25);
		panel.add(Label_Table);		
		Field_Table = new JTextField(20);
		Field_Table.setBounds(130, 140, 120, 25);
		panel.add(Field_Table);
		
		Label_User = new JLabel("User Info");
		Label_User.setBounds(10, 200, 640, 25);
		panel.add(Label_User);

		Label_Username = new JLabel("Username :");
		Label_Username.setBounds(40, 230, 80, 25);
		panel.add(Label_Username);		
		Field_Username = new JTextField(20);
		Field_Username.setBounds(130, 230, 120, 25);
		panel.add(Field_Username);
		
		Label_Password = new JLabel("Password :");
		Label_Password.setBounds(40, 260, 80, 25);
		panel.add(Label_Password);		
		Field_Password = new JPasswordField(20);
		Field_Password.setBounds(130, 260, 120, 25);
		panel.add(Field_Password);
		
		Button_Connect = new JButton("Connect");
		Button_Connect.setBounds(190, 320, 100, 25);
		panel.add(Button_Connect);
		
		ActionHandler handler = new ActionHandler();
		Button_Connect.addActionListener(handler);
		
		open();
	}
	
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Field_IP.setEditable(false);
			Field_Port.setEditable(false);
			Field_Table.setEditable(false);
			Field_Username.setEditable(false);			
			Field_Password.setEditable(false);
			Button_Connect.setEnabled(false);
			
			String IP = Field_IP.getText();
			String Port = Field_Port.getText();
			String Table = Field_Table.getText();
			String Username = Field_Username.getText();
			String Password = String.valueOf(Field_Password.getPassword());
			
			if (SQLDriver.connect(IP, Port, Table, Username, Password)){
				JOptionPane.showMessageDialog(null, "Connection Successful");
			}
			else{
				JOptionPane.showMessageDialog(null, "Connection Unsuccessful. Please try again.");
				Field_Password.setText("");
				
				Field_IP.setEditable(true);
				Field_Port.setEditable(true);
				Field_Table.setEditable(true);
				Field_Username.setEditable(true);			
				Field_Password.setEditable(true);	
				Button_Connect.setEnabled(true);			
			}
		}
	}
}
