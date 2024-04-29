package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInteraction {

	static String url="jdbc:mysql://localhost:3306/jeegestionprojet";
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement preparedStatement;
	
	public static void connect() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
	        // Properly handle exceptions (log or rethrow if needed)
	        e.printStackTrace();
	        throw new RuntimeException("Failed to connect to the database.", e);
		}
		
	}
	
	public static void disconnect() {
		
		try {
			
			conn.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int UpdateQuery(String sql) {
		
		int nb = 0;
		try {
			// return the number of rows effected by the query
			nb = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return nb;
	}
	
	public static ResultSet SelectQuery(String sql) {
		
		ResultSet rs = null;
		
		try {
			// return the result of select query in form of *ResultSet* object
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

    // Example of using a prepared statement for a parameterized query
    public static ResultSet selectQueryWithParams(String sql, Object... params) {
        ResultSet rs = null;
        try {
			preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
	        // Close the ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	        }
	        try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
        return rs;
    }
}
