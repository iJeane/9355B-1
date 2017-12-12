package clothes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClothesController {
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private String sql;
    
    public void dbaseConnect(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }
    
    public ResultSet showInformation(String retrieveQuery) throws Exception {
        statement = connection.createStatement();
        return statement.executeQuery(retrieveQuery);
    }
    
    public void insertEntry(String name, String gender, String material) throws Exception {
		sql = "Insert into product(productName,materialType,genderType) values(?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, gender);
		ps.setString(3, material);
		ps.executeUpdate();
    }
    public void deleteEntry(String name) throws Exception {
		sql = "DELETE from product WHERE productName = ?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		ps.executeUpdate();
    }
    
    public void updateEntry(String name,String material,String gender) throws Exception {
		sql = "UPDATE product set productName = ?,materialType = ?,genderType = ? WHERE productName = name";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
                ps.setString(2, material);
                ps.setString(3, gender);
		ps.executeUpdate();
    }
    public void updateProductName(String oldname , String newname) throws Exception {
		sql = "UPDATE product set productName = ? WHERE productName = ? ";
		ps = connection.prepareStatement(sql);
		ps.setString(1, newname);
                ps.setString(2, oldname);
		ps.executeUpdate();
    }
    
    public void insertProductLinesInfo() throws Exception {
		sql = "Insert into categories values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, "7");
		ps.setString(2, "AAA");
		ps.executeUpdate();
    }
    
    public void insertCategoriesInfo() throws Exception {
		sql = "Insert into categories values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, "7");
		ps.setString(2, "AAA");
		ps.executeUpdate();
    }
    
    public void insertSuppliersInfo() throws Exception {
		sql = "Insert into categories values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, "7");
		ps.setString(2, "AAA");
		ps.executeUpdate();
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