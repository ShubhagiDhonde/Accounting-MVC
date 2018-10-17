package simpleaccounting.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;



public abstract class AbstractDAOFactory {
	public static AbstractDAOFactory getDaoFactory(final String value) {
		AbstractDAOFactory abstractDao = null;
		if (value != null && value.equals(AccountMVCConstant.ORACLE)) {
			abstractDao = new OracleConnection();
		}
		return abstractDao;
	}

	/**
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 */
	public abstract Connection getConnection() throws ClassNotFoundException,
			IOException, SQLException;
}
