import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


public class TableScreen extends Screen{
	
	public String curTable = null;
	
	private JScrollPane scroll;
	
	private JLabel Label_Server;
	private JLabel Label_DB;
	private JLabel Label_IP;
	private JLabel Label_DBName;
	private JLabel Label_Table;
	
	private JComboBox<String> Select_Table;
	
	private JTable Table;
	
	public TableScreen(String IP, String TableName){
		super("Table", 720, 480);
		
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
		
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);		
		scroll.setBounds(0,120,700,200);
		add(scroll);
		
		Select_Table = new JComboBox<String>(SQLDriver.TableNames);
		Select_Table.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				curTable = String.valueOf(Select_Table.getSelectedItem());
				SQLDriver.UpdateCurTable(curTable);				
				generateTable();
			}
		});
		Select_Table.setSelectedIndex(0);
		Select_Table.setBounds(120, 70, 150, 25);
		panel.add(Select_Table);
		
		open();
	}
	
	private void generateTable(){
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
		Table.getColumnModel().getColumn(0).setMaxWidth(40);
		if (SQLDriver.curTableCols.size()>9) Table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		scroll.getViewport().add(Table);
	}
}