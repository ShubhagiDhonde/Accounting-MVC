package simpleaccounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import simpleaccounting.model.User;

public class UserDAO {

	public static User validateUser(String username, String password) {

		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
			conn = daoFactory.getConnection();

			stmt = conn.prepareStatement(OracleSqlQueries.SELECT_USER);
			stmt.setString(1, username);
			stmt.setString(2, password);

			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setRole(resultSet.getString(3));
				user.setAddress(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setFirst_name(resultSet.getString(6));
				user.setLast_name(resultSet.getString(7));

			}
			stmt.close();

		}
		catch(SQLException e) {
			e.getMessage();
		}
		catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				resultSet.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}

		return user;
	}
	
public User getUser(int user_id) {
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		User user=null;

		try
		{
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
 			conn = daoFactory.getConnection();
			
		
			stmt = conn.prepareStatement(OracleSqlQueries.SELECT_USER_ByID);
			stmt.setInt(1, user_id);
			
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setFirst_name(resultSet.getString(4));
				user.setLast_name(resultSet.getString(5));
				user.setAddress(resultSet.getString(6));
				user.setPhone(resultSet.getString(7));
				user.setEmail(resultSet.getString(8));
				user.setRole(resultSet.getString(9));
			
			}
			stmt.close();
		
		
		}
		catch (SQLException e)
		{
			e.getMessage();
		}
		catch(Exception e)
		{
			e.getMessage();
		} 
		finally
		{
			try
			{
				resultSet.close();
				stmt.close();
				conn.close();
			}
			catch (SQLException e)
			{
			}
		}
		
		return user;
	}


}
