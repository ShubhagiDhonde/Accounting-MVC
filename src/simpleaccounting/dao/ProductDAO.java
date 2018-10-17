package simpleaccounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import simpleaccounting.model.Product;

public class ProductDAO {

	public List<Product> getAllProducts() throws SQLException {

		List<Product> products = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
			conn = daoFactory.getConnection();
			stmt = conn.prepareStatement(OracleSqlQueries.SELECT_PRODUCTS);

			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt(1));
				product.setCategory(resultSet.getString(2));
				product.setName(resultSet.getString(3));
				product.setPrice(resultSet.getDouble(4));

				products.add(product);
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

		return products;
	}

	public void addProduct(Product product) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int generatedKey = -1;
		String generatedKeys[] = { "id" };
		try {
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
			conn = daoFactory.getConnection();
			stmt = conn.prepareStatement(OracleSqlQueries.SAVE_PRODUCT, generatedKeys);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPrice());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = (int) rs.getInt(1);
				product.setId(generatedKey);
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
public Product getProduct(int product_id) {
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Product product=null;

		try
		{
			final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(AccountMVCConstant.ORACLE);
 			conn = daoFactory.getConnection();
			
		
			stmt = conn.prepareStatement(OracleSqlQueries.SELECT_PRODUCT);
			stmt.setInt(1, product_id);
			
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setCategory(resultSet.getString(3));
				product.setPrice(resultSet.getDouble(4));
			
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
		
		return product;
	}

	
}
