package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static void main(String[] args) {
		Connection conn = new ConnectionFactory().getConnection();
		System.out.println("Conexão Aberta");
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		String url = "jdbc:mysql://localhost/livraria";
		try{         
		return DriverManager.getConnection(url, "root","root");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
