package simpleaccounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import simpleaccounting.model.Customer;
import simpleaccounting.model.User;

public class OrderDAO {

	
	public void addOrder(Customer customer, User user, Date order_date) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int generatedKey=-1;
		String generatedKeys[]= {"id"};
		try
		{
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
			conn = daoFactory.getConnection();
			stmt = conn.prepareStatement(OracleSqlQueries.ADD_ORDER, generatedKeys);
			stmt.setInt(1, customer.getId());
			stmt.setInt(2, user.getId());
			stmt.setDate(3, order_date);
				
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()){
	            generatedKey=(int)rs.getInt(1);
	            customer.setId(generatedKey);
	        }
			
			stmt.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
		
				stmt.close();
				conn.close();
		
		}
	}
	
	
}

