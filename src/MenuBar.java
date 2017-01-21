import javax.swing.*;
import java.awt.event.*;

public class MenuBar extends JMenuBar implements ActionListener{
	
	private JMenu file;
	private JMenuItem newDB;
	private JMenuItem refresh;
	private JMenuItem close;
	
	public MenuBar(){
		file = new JMenu("File");
		newDB = new JMenuItem("Connect to new Database");
		newDB.addActionListener(this);
		
		refresh = new JMenuItem("Refresh table");
		refresh.addActionListener(this);
		
		close = new JMenuItem("Close");
		close.addActionListener(this);
		
		file.add(newDB);
		file.add(refresh);
		file.add(close);
		add(file);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		if (ev.getSource() == newDB){
			new LoginScreen();
		}
		else if (ev.getSource() == refresh){
			if (null != TableScreen.CurrentTableScreen){
				TableScreen.CurrentTableScreen.refreshTable(TableScreen.curTable);
			}			
		}
		else if (ev.getSource() == close){
			if (null != TableScreen.CurrentTableScreen){
				TableScreen.CurrentTableScreen.close();
			}						
		}
	}
	
}
