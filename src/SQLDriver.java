import java.sql.*;
import java.util.Vector;

public class SQLDriver {
	private static Connection server;
	public static Vector<String> TableNames = new Vector<String>();
	
	private static ResultSet curTableRS;
	public static Vector<String> curTableCols = new Vector<String>(); 
	public static Vector<Vector<String>> curTableData = new Vector<Vector<String>>();
	
	
	public static boolean Query(String stmtString){
		try{
			Statement stmt = server.createStatement();
			curTableRS =  stmt.executeQuery(stmtString);
			
			if (!UpdateCurTable()){
				return false;
			}
			
			return true;
		}
		catch (Exception exc){
			return false;
		}
	}
	
	public static Boolean connect(String IP, String port, String table, String username, String password){
		try{
			server = DriverManager.getConnection("jdbc:mysql://"+IP+":"+port+"/"+table+"?autoReconnect=true&useSSL=false",username, password);
			DatabaseMetaData DBmeta = server.getMetaData();
			
			TableNames.clear();		
			
			ResultSet tables = DBmeta.getTables(server.getCatalog(), null, "%", null);			
			
			while (tables.next()){
				TableNames.add(tables.getString(3));
			}
			
			return true;			
		}
		catch (Exception exc){
			return false;
		}
	}
	
	public static boolean UpdateCurTable(){
		try{			
			ResultSetMetaData rsMeta = curTableRS.getMetaData();
			
			curTableCols.clear();			
			for (int i=1; i<=rsMeta.getColumnCount(); i++){
				curTableCols.addElement(rsMeta.getColumnName(i));
			}
			
			curTableData.clear();
			while(curTableRS.next()){
				Vector<String> row = new Vector<String>();
				for (int i=0; i < curTableCols.size(); i++){
					row.addElement(curTableRS.getString( curTableCols.elementAt(i) ));					
				}				
				System.out.println();
				curTableData.add(row);
			}
		}
		catch (Exception exc){
			return false;
		}
		
		return true;		
	}
}
