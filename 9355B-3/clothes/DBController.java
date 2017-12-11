package clothes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBController {
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private String sql;
    
    public void dbaseConnect(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }
    
    public ResultSet getAllProducts() throws Exception {
        statement = connection.createStatement();
        sql = "select * from products;";
        return statement.executeQuery(sql);
    }
    
    public ResultSet searchProduct(String productName) throws Exception {
    	sql = "select productCode, productName from products;";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, "%" + productName + "%");
    	return ps.executeQuery();
    }
    
    public void createProduct(String productCode, String productName, String materialType, String size, Object genderType) throws Exception {
		sql = "Insert into person values(?,?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, productCode);
		ps.setString(2, productName);
		ps.setString(3, materialType);
		ps.setString(4, size);
		ps.setObject(5, genderType);
		ps.executeUpdate();
	}
    
    public void deleteProduct(String productName) throws Exception {
    	sql = "delete from person where name = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, productName);
    	ps.executeUpdate();
    }
    
	public void updateProduct(String productName, String col, String replacement) throws Exception {
		sql = "update products set " + col + " = ? where productName = ?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, replacement);
		ps.setString(2, productName);
		ps.executeUpdate();
	}

	public void updateProduct(String productName, String col, int row, String replacement) throws Exception {
		sql = "select * from products where productName = ?";
		ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ps.setString(1, productName);
		resultSet = ps.executeQuery();
		resultSet.absolute(row);
		
		resultSet.updateRow();
	}
	
	public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (ps != null) {
            	ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }
	
}
