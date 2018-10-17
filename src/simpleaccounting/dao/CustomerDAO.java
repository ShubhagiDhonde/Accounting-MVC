package simpleaccounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import simpleaccounting.model.Customer;

public class CustomerDAO {

	public List<Customer> getAllCustomers() throws SQLException {
		
		List<Customer> customers = new ArrayList<Customer>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
			conn = daoFactory.getConnection();
			stmt = conn.prepareStatement(OracleSqlQueries.SELECT_CUSTOMERS);

			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setFirst_name(resultSet.getString(2));
				customer.setLast_name(resultSet.getString(3));
				customer.setAddress(resultSet.getString(4));
				customer.setPhone(resultSet.getString(5));
				customers.add(customer);
			}
			stmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			stmt.close();
			conn.close();
		}

		return customers;
	}

	public void addCustomer(Customer customer) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int generatedKey = -1;
		String generatedKeys[] = { "id" };
		try {
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
			conn = daoFactory.getConnection();
			stmt = conn.prepareStatement(OracleSqlQueries.SAVE_CUSTOMER, generatedKeys);
			stmt.setString(1, customer.getFirst_name());
			stmt.setString(2, customer.getLast_name());
			stmt.setString(3, customer.getAddress());
			stmt.setString(4, customer.getPhone());
			stmt.setString(5,  customer.getEmail());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = (int) rs.getInt(1);
				customer.setId(generatedKey);
			}

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			
			stmt.close();
			conn.close();

		}

	}
	
public Customer getCustomer(int customer_id) {
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Customer customer=null;

		try
		{
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
 			conn = daoFactory.getConnection();
			
		
			stmt = conn.prepareStatement(OracleSqlQueries.SELECT_CUSTOMER);
			stmt.setInt(1, customer_id);
			
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setFirst_name(resultSet.getString(2));
				customer.setLast_name(resultSet.getString(3));
				customer.setAddress(resultSet.getString(4));
				customer.setEmail(resultSet.getString(5));
				customer.setPhone(resultSet.getString(6));
				
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
		
		return customer;
	}
	
	

	


}
