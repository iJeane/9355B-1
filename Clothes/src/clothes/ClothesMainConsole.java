package clothes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class ClothesMainConsole {
    private static ClothesController controller;
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        controller = new ClothesController();
        try {
            String url = "jdbc:mysql://localhost/clothes2?useSSL=false";
            controller.dbaseConnect(url,"root",null);
            
            System.out.println("Choose your pokemon");
            System.out.print("1.Add 2.Delete 3.Update :");
            int a = input.nextInt();
            
            switch(a){
                case 1: 
                    System.out.println("Enter product name,product material,product gender :");
                    String pname = input.next();
                    String mname = input.next();
                    String gname = input.next();
                    controller.insertEntry(pname,mname,gname);
                    break;
                case 2:
                    System.out.println("Enter product name to delete: ");
                    String pname1 = input.next();
                    controller.deleteEntry(pname1);
                    break;
                
                case 3:
                    System.out.println("Enter product name to update");
                    String pname2 = input.nextLine();
                    
                    System.out.println("Enter number of columns to update");
                    int aa = input.nextInt();
                    
                    ArrayList<String> b = new ArrayList();
                    ArrayList<String> c = new ArrayList();
                    
                   
                    if(aa != 0 && aa < 4){
                        for(int i = 0;i<aa;i++){
                            System.out.println("Enter name of product to update");
                            String u = input.next();
                            
                            System.out.println("Choose column name to update");
                            System.out.println("1.Product Name,2.Material Type, 3.Gender Type");
                            int l = input.nextInt();
                            
                            
                            //Ditoy Ti kUrang ,materialtype,gender type .etc...
                            switch(l){
                                case 1:
                                    
                                        System.out.println("Enter new name");
                                        String z = input.next();
                                        
                                        controller.updateProductName(u,z);
                                        break;
                            }
                            
                        }
                    }else{
                        System.out.println("Invalid Input");
                    }
                        
                    
                    break;
                 
                
            }
            
            
            
//            viewProducts();
//            viewProductLines();
//            viewCategories();
//            viewSuppliers();
 // controller.insertEntry("Johnosky", "Leather", "M");
//            controller.deleteEntry(n);
  
        } catch (SQLException e) {
        	System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
        } catch (Exception e) {
        	System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
        } finally {
            controller.close();
        }
        
    }
    
    public static void Menu() {
        
    }
    
    public static void viewProducts() {
        try {
            ResultSet rs = controller.showInformation("select * from products;");
                System.out.println("PRODUCTS");
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", "Product Code", "Product Name", "Gender Type", "Material Type", "Product Size", "Product Line", "Supplier ID", "Category ID");
    	        while (rs.next()) {
                    String code = rs.getString("productCode");
                    String name = rs.getString("productName");
                    String gender = rs.getString("genderType");
                    String material = rs.getString("materialType");
                    String size = rs.getString("productSize");
                    String productLine = rs.getString("productLine");
                    String supplier = rs.getString("supplierID");
                    String category = rs.getString("categoryID");
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", code, name, gender, material, size, productLine, supplier, category);
                }
        } catch (Exception e) {
            
        }
        
    }
    
    public static void viewProductLines() {
        try {
            ResultSet rs = controller.showInformation("select * from productlines;");
                System.out.println("PRODUCTLINES");
                System.out.printf("%-20s%-20s%n", "Product Line", "Description");
    	        while (rs.next()) {
                    String productline = rs.getString("productline");
                    String desc = rs.getString("description");
                    System.out.printf("%-20s%-20s%n", productline, desc);
                }
        } catch (Exception e) {
            
        }
        
    }
    
    public static void viewCategories() {
        try {
            ResultSet rs = controller.showInformation("select * from categories;");
                System.out.println("CATEGORIES");
                System.out.printf("%-20s%-20s%n", "Category ID", "Category Name");
    	        while (rs.next()) {
                    String productline = rs.getString("categoryID");
                    String desc = rs.getString("categoryName");
                    System.out.printf("%-20s%-20s%n", productline, desc);
                }
        } catch (Exception e) {
            
        }
        
    }
    
    public static void viewSuppliers() {
        try {
            ResultSet rs = controller.showInformation("select * from suppliers;");
                System.out.println("SUPPLIERS");
                System.out.printf("%-20s%-20s%-20s%n", "Supplier ID", "Supplier Name", "Supplier Location");
    	        while (rs.next()) {
                    String supplierID = rs.getString("supplierID");
                    String supplierName = rs.getString("supplierName");
                    String supplierLocation = rs.getString("supplierLocation");
                    System.out.printf("%-20s%-20s%-20s%n", supplierID, supplierName, supplierLocation);
                }
        } catch (Exception e) {
            
        }
        
    }
    
}