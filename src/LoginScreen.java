import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends Screen {	
	private JLabel Label_Prompt;
	private JLabel Label_Server;
	private JLabel Label_IP;
	private JLabel Label_Port;
	private JLabel Label_DB;
	private JLabel Label_User;
	private JLabel Label_Username;
	private JLabel Label_Password;
	
	private JTextField Field_IP;
	private JTextField Field_Port;
	private JTextField Field_DB;
	private JTextField Field_Username;
	
	private JPasswordField Field_Password;
	
	private JButton Button_Connect;
	
	//debugging members
	public static Boolean autoLogin = false;
	
	public LoginScreen(){
		super("Connect to DB", 350, 400);
		
		if (null != TableScreen.CurrentTableScreen){
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
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
		
		Label_DB = new JLabel("Schema :");
		Label_DB.setBounds(40, 140, 80, 25);
		panel.add(Label_DB);		
		Field_DB = new JTextField(20);
		Field_DB.setBounds(130, 140, 120, 25);
		panel.add(Field_DB);
		
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
		Button_Connect.setBounds(125, 320, 100, 25);
		panel.add(Button_Connect);
		
		ActionHandler handler = new ActionHandler();
		Button_Connect.addActionListener(handler);
		getRootPane().setDefaultButton(Button_Connect);
		
		open();
	}
	
	private void resetFields(){
		Field_Password.setText("");
		
		Field_IP.setEditable(true);
		Field_Port.setEditable(true);
		Field_DB.setEditable(true);
		Field_Username.setEditable(true);			
		Field_Password.setEditable(true);	
		Button_Connect.setEnabled(true);	
	}
	
	private void disableFields(){
		Field_IP.setEditable(false);
		Field_Port.setEditable(false);
		Field_DB.setEditable(false);
		Field_Username.setEditable(false);			
		Field_Password.setEditable(false);
		Button_Connect.setEnabled(false);
	}
	
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			disableFields();
			
			String IP = Field_IP.getText().replaceAll("\\s+","");
			String Port = Field_Port.getText().replaceAll("\\s+","");
			String DB = Field_DB.getText().replaceAll("\\s+","");
			String Username = Field_Username.getText().replaceAll("\\s+","");
			String Password = String.valueOf(Field_Password.getPassword());
			
			if (autoLogin){
				IP = "localhost";
				Port = "3306";
				DB = "world";
				Username = "test";
				Password = "password";
			}
			
			if (IP.isEmpty() || Port.isEmpty() || DB.isEmpty() || Username.isEmpty() || Password.isEmpty()){
				JOptionPane.showMessageDialog(null, "Please fill out all fields.");
				resetFields();
			}
			
			else if (SQLDriver.connect(IP, Port, DB, Username, Password)){
				JOptionPane.showOptionDialog(null, "Connection successful.", "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (null != TableScreen.CurrentTableScreen){
					TableScreen.CurrentTableScreen.close();
				}
				new TableScreen(IP, DB);
				close();
			}
			else{				
				JOptionPane.showMessageDialog(null, "Connection Unsuccessful. Please try again.");
				resetFields();
			}
		}
	}
}
