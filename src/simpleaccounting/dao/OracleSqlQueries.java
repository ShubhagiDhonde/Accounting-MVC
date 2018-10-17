package simpleaccounting.dao;

public final class OracleSqlQueries {
	
	public static final String SELECT_USER="Select username, password, role, address, email, first_name, last_name FROM user_master WHERE username=? and password=?";
	public static final String SAVE_CUSTOMER="INSERT INTO customer(first_name, last_name, address, phone, email) VALUES(?,?,?,?,?)";
	public static final String SAVE_PRODUCT="INSERT INTO product(name, category, price ) VALUES(?,?,?)";
	public static final String SELECT_CUSTOMERS="SELECT * FROM CUSTOMER";
	public static final String SELECT_PRODUCTS="SELECT * FROM PRODUCT";
	public static final String ADD_ORDER="INSERT INTO Orders(customer_id, user_id, order_date) VALUES(?,?,?)";
	public static final String SAVE_INVENTORY="INSERT INTO Inventory(id, order_id, product_id, quantity) VALUES(?,?,?,?)";
	public static final String SELECT_CUSTOMER="SELECT id, first_name, last_name, address, email, phone FROM customer WHERE id=?";
	public static final String SELECT_PRODUCT="SELECT id, name, category, price FROM product WHERE id=?";
	public static final String SELECT_USER_ByID="SELECT id, username, password, first_name, last_name, address, phone, email, role FROM user_master WHERE id=?";
}
