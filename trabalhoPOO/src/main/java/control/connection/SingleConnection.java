
package control.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/trabalhoPoo";
	private static String password = "postgres";
	private static String user = "postgres";
	private static Connection connection = null;
	
	public SingleConnection() {
		conectar();
	}
	
	static {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
						
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnection() {
		return connection;
		
	}

}
