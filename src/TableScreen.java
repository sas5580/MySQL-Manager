import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


public class TableScreen extends Screen{
	public static TableScreen CurrentTableScreen = null;
	
	public static String curTable = null;
	
	private JScrollPane Scroll_Query;
	private JScrollPane scroll;
	
	private JLabel Label_Server;
	private JLabel Label_DB;
	private JLabel Label_IP;
	private JLabel Label_DBName;
	private JLabel Label_Table;
	private JLabel Label_Query;
	
	private JTextArea Field_Query;
	
	private JButton Button_Query;
		
	private JComboBox<String> Select_Table;
	
	private JTable Table;
	
	public TableScreen(String IP, String TableName){		
		super("Table", 720, 480);
		
		if (null != CurrentTableScreen){
			CurrentTableScreen.close();
		}
		CurrentTableScreen = this;
		
		setJMenuBar(new MenuBar());
		
		Label_Server = new JLabel("Database IP :");
		Label_Server.setBounds(10, 10, 80, 25);
		panel.add(Label_Server);
		
		Label_IP = new JLabel(IP);
		Label_IP.setBounds(120, 10, 80, 25);
		panel.add(Label_IP);
		
		Label_DB = new JLabel("Schema :");
		Label_DB.setBounds(10, 40, 80, 25);
		panel.add(Label_DB);
		
		Label_DBName = new JLabel(TableName);
		Label_DBName.setBounds(120, 40, 80, 25);
		panel.add(Label_DBName);
		
		Label_Table = new JLabel("Table :");
		Label_Table.setBounds(10, 70, 80, 25);
		panel.add(Label_Table);
		
		Label_Query = new JLabel("Enter query :");
		Label_Query.setBounds(340, 40, 80, 25);
		panel.add(Label_Query);		
		
		Field_Query = new JTextArea();
		Field_Query.setLineWrap(true);
		Field_Query.setWrapStyleWord(true);
		
		Scroll_Query = new JScrollPane(Field_Query, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Scroll_Query.setBounds(420, 10, 200, 85);
		panel.add(Scroll_Query);
		
		Button_Query = new JButton("Go");
		Button_Query.setBounds(630, 40, 50, 25);
		panel.add(Button_Query);		
		
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		scroll.setBounds(0,120,700,200);
		add(scroll);
		
		Select_Table = new JComboBox<String>(SQLDriver.TableNames);
		Select_Table.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				if (String.valueOf(Select_Table.getSelectedItem()) != curTable){
					curTable = String.valueOf(Select_Table.getSelectedItem());
					refreshTable(curTable);
				}				
			}
		});
		Select_Table.setSelectedIndex(0);
		Select_Table.setBounds(120, 70, 150, 25);
		panel.add(Select_Table);
		
		open();
	}
	
	public void refreshTable(String tableName){
		SQLDriver.Query("SELECT * FROM "+tableName);				
		generateTable();
	}
	
	public void generateTable(){
		if (null != scroll && scroll.isDisplayable()){
			scroll.getViewport().remove(Table);
		}
		
		Table = new JTable(SQLDriver.curTableData, SQLDriver.curTableCols){
			public boolean isCellEditable(int row, int column){ 
				return false;
			}
		};
		
		Table.setPreferredScrollableViewportSize(new Dimension(700, 200));
		Table.setFillsViewportHeight(true);
		if (SQLDriver.curTableCols.size()>9) Table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		scroll.getViewport().add(Table);
	}
	
	
}
