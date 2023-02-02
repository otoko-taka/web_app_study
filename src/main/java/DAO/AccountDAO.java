package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
	private final String JDBC_URL = "jdbc:mysql://127.0.0.1/web_app_study";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysql";
	
	public Account findByLogin(Login login) {
		Account account = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,login.getUserId());
			pStmt.setString(2,login.getPass());
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
                String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
		        int age = rs.getInt("AGE");
		        account = new Account(userId, pass, mail, name, age);
			  }
			} catch (SQLException e){
				e.printStackTrace();
				return null;
			}
			return account;
	}

}
