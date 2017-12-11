package dblabfinals;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBcontroller {
	
	private Connection connection;
	private PreparedStatement pstatement;
    private Statement statement;
    private ResultSet result;
    private String query;
    
    public void dbConnect(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }
    
    /**
     * Read/Retrieve Operation
     */
    public ResultSet showAllTables() throws Exception {
        statement = connection.createStatement();
        sql = "select * from products natural join productlines natural join suppliers natural join categories;";
        return statement.executeQuery(sql);
    }
    
    public ResultSet getAllProducts() throws Exception {
        statement = connection.createStatement();
        sql = "select * from products;";
        return statement.executeQuery(sql);
    }
    
    public ResultSet getAllCategories() throws Exception {
        statement = connection.createStatement();
        sql = "select * from categories;";
        return statement.executeQuery(sql);
    }
    
    public ResultSet getAllProductLines() throws Exception {
        statement = connection.createStatement();
        sql = "select * from productlines;";
        return statement.executeQuery(sql);
    }
    
    public ResultSet getAllSuppliers() throws Exception {
        statement = connection.createStatement();
        sql = "select * from suppliers;";
        return statement.executeQuery(sql);
    }
    
    /**
     * Create Operation
     */
    public void addProduct(String code, String name, char gender, String type, String size, String line, int supID, int catID) throws Exception {
		sql = "Insert into products values(?,?,?,?,?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, code);
		ps.setString(2, name);
		ps.setString(3, gender);
		ps.setString(4, type);
		ps.setString(5, size);
		ps.setString(6, line);
		ps.setString(7, supID);
		ps.setString(8, catID);
		ps.executeUpdate();
	}
    
    public void addProductLine(String line, String desc) throws Exception {
		sql = "Insert into products values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, line);
		ps.setString(2, desc);
		ps.executeUpdate();
	}
    
    public void addCategory(int catID, String catName) throws Exception {
		sql = "Insert into products values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, catID);
		ps.setString(2, catName);
		ps.executeUpdate();
	}
    
    public void addSupplier(int supID, String supName, String supLocation) throws Exception {
		sql = "Insert into products values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, supID);
		ps.setString(2, supName);
		ps.setString(2, supLocation);
		ps.executeUpdate();
	}
    
    /**
     * Delete Operation
     */
    public void deleteProduct(String name) throws Exception {
    	sql = "delete from products where productName = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, name);
    	ps.executeUpdate();
    }
    
    public void deleteProductLine(String line) throws Exception {
    	sql = "delete from productlines where productLine = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, line);
    	ps.executeUpdate();
    }
    
    public void deleteSuppliers(String supName) throws Exception {
    	sql = "delete from suppliers where supplierName = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, supName);
    	ps.executeUpdate();
    }
    
    public void deleteCategory(String catName) throws Exception {
    	sql = "delete from categories where categoryName = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, catName);
    	ps.executeUpdate();
    }
    
    
}
