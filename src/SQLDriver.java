import java.sql.*;


public class SQLDriver {
	private static Connection server;
	
	public static Boolean connect(String IP, String port, String table, String username, String password){
		try{
			server = DriverManager.getConnection("jdbc:mysql://"+IP+":"+port+"/"+table+"?autoReconnect=true&useSSL=false",username, password);
			/*
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM city LIMIT 10");
			
			while (myRs.next()){
				System.out.println(myRs.getString("name")+ ", " + myRs.getString("population"));
			}
			*/
			
			return true;			
		}
		catch (Exception exc){
			return false;
		}
	}
}
