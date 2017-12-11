package clothes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class MainConsole {
    private static DBController controller;
    private static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args) {
        controller = new DBController();
        try {   
        	int choice = 0;
        	String url = "jdbc:mysql://localhost/contact?useSSL=false";
            controller.dbaseConnect(url,"root",null);
        	while (true) {        		
                choice = showMenu();
                if (choice == 7) {
                	System.out.println("Thank your for trying this program...");
                	break;
                }
                processChoice(choice);
        	}        	
        } catch (SQLException e) {
        	System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
        } catch (Exception e) {
        	System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
        } finally {
            controller.close();
        }
    }
    
    private static void processChoice(int option) {
    	switch (option) {
	    	case 1: // Enter a product
	    		enterProduct();
	    		break;
	    	case 3: // Show all products	    		
	    		printAllProducts();	    		
	    		break;
	    	case 4: // Search product
	    		searchProduct();
	    		break;
	    	case 5: // Edit product
	    		editProduct();
	    		break;
	    	case 6: // Delete contact
	    		deleteProduct();
	    		break;
    	}
    }
    
    private static int showMenu() {    	
    	int choice = 0;
		do {
    		System.out.println("+----------------------------+");
            System.out.println("|   M E N U  O P T I O N S   |");
            System.out.println("+----------------------------+");
            System.out.println("| 1. Enter product name      |");
            System.out.println("| 2. Show all products       |");
            System.out.println("| 3. Search product          |");
            System.out.println("| 4. Edit product            |");
            System.out.println("| 5. Delete product          |");
            System.out.println("| 6. Exit program            |");
            System.out.println("+----------------------------+");
            System.out.print("Enter your choice: ");
            try {
            	choice = Integer.parseInt(kb.nextLine());
            } catch (Exception e) {
            	System.out.println("error: input a valid value...");
            	System.out.print("Press enter key to continue...");
            	kb.nextLine();
            }
        	System.out.println();
    	} while (choice < 1 || choice > 7);
        return choice;
    }
    
    private static void editProduct() {
    	printDivider();
    	try {
    		String productName = null;
    		ResultSet rs = null;
    		do {
    			productName = inputProductName("Enter product name to be edited: ");
        		rs = controller.searchProduct(productName);       		
        		if (getResTotal(rs) == 0) {
        			System.out.println("error: Name not found. Please enter an existing contact name.");
        		}
    		} while (getResTotal(rs) == 0);
    		rs = controller.searchProduct(productName);
    		printProduct(rs);
    		rs.beforeFirst();
    		String col = enterColumn();
    		String rValue = null;
    		if (col.equals("number") || col.equals("home")) {
    			int row = 1;
    			if (getResTotal(rs) > 1) {    			
        			do {
        				System.out.print("Enter the row to be edited: ");
        				row = Integer.parseInt(kb.nextLine());
        			} while (row < 0 || row > getResTotal(rs));
        		}
        		System.out.print("Enter replacement value: ");
        		rValue = kb.nextLine();
        		controller.updateProduct(productName, col, row, rValue);
    		} else {
    			System.out.print("Enter replacement value: ");
        		rValue = kb.nextLine();
        		controller.updateProduct(productName, col, rValue);
    		}
    		rs = null;
    		if (col.equals("name")) {
    			rs = controller.searchProduct(rValue);
    		} else {
    			rs = controller.searchProduct(productName);
    		}    		
    		printProduct(rs);
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    
    private static String enterColumn() {
    	String col = null;
    	boolean valid = false;
    	do {
    		System.out.print("Enter the column name you would like edit: ");
    		col = kb.nextLine().toLowerCase();
    		switch (col) {
    			case "name":
    			case "address":
    			case "e-mail":
    			case "gender":
    			case "contact":
    			case "type":
    				valid = true;
    		}
    	} while (!valid);
    	col = reformatColumn(col);
    	return col;
    }
    
    private static String reformatColumn(String col) {
    	switch(col) {
    	case "address":
    		col = "addr";
    		break;
    	case "e-mail":
    		col = "email";
    		break;
    	case "contact":
    		col = "number";
    		break;
    	case "type":
    		col = "home";
    	}
    	return col;
    }
    
    private static void deleteProduct() {
    	printDivider();
    	try {
    		String productName = inputProductName("Enter the exact contact name to be deleted: ");
    		ResultSet rs = controller.searchProduct(productName);
    		if (getResTotal(rs) == 0) {
    			System.out.println("error: Name not found. Please enter an existing contact name.");
    			deleteProduct();
    		}
			controller.deleteProduct(productName);
			System.out.println(productName + " was successfully deleted from the contact list.");
    		printDivider();
    		System.out.println();
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}    	
    }
    
    private static void enterProduct() {
    	printDivider();
    	System.out.print("Enter product code: ");
    	String productCode = kb.nextLine();
    	System.out.print("Enter product name: ");
    	String productName = kb.nextLine();
    	System.out.print("Enter material type: ");
    	String materialType = kb.nextLine();
    	System.out.print("Enter size: ");
    	String size = kb.nextLine();
    
    	int genderType;
    	do {
    		System.out.println("Select gender:");
    		System.out.println("\t1. M");
    		System.out.println("\t2. F");
    		System.out.println("\t3. U");
    		System.out.print("Enter gender: ");
    		genderType = Integer.parseInt(kb.nextLine());
    	} while (genderType < 1 || genderType > 3);
    	
    	try {
    		controller.createProduct(productCode,productName,materialType,size,genderType == 1 ? "M" : genderType == 2 ? "F" : "U");
    		System.out.println(productCode + " " +  productName + " was added to the product list.");
    		char cho;
    		do {
    			System.out.println("Do you want to add another product [y/n]? ");
    			cho = Character.toLowerCase(kb.nextLine().charAt(0));
    		} while (cho != 'y' && cho != 'n');
    		if (cho == 'y') {
    			// create product: call createProduct
    		}
    		printDivider();
    		System.out.println();
    	} catch (MySQLIntegrityConstraintViolationException e) {
    		// duplicate name
    		System.out.println("error: Name already exists.  Please enter another name.");
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    
    private static void printAllProducts() {
    	printDivider();
    	try {
    		ResultSet rs = controller.getAllProducts();
    		if (getResTotal(rs) == 0) {
    			System.out.println("Error: no records found!!!");
    		} else {   
    			System.out.println("Product list:");    			
    	        System.out.printf("     %-15s %-20s %-15s %-10s %-15s %-10s%n",
    	        		"productCode, productName","materialType","size","genderType");
    	        int row = 1;
    	        while (rs.next()) {
    	            // It is possible to get the columns via name
    	            // also possible to get the columns via the column number
    	            // which starts at 1
    	            // e.g. resultSet.getSTring(2);            
    	            String productCode = rs.getString(1); // 'productCode' column
    	            String productName = filterNull(rs.getString(2)); // 'productName' column
    	            String materialType = filterNull(rs.getString(3));
    	            String size = rs.getString(4);
    	            String genderType = rs.getString(5);
    	            System.out.printf("%-4d %-15s %-20s %-15s %-10s %-15s %-10s%n",
    	            		row++,productCode,productName,materialType,size,genderType);
    	        }
    		}		
    		printDivider();
	        System.out.println();
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    
    private static int getResTotal(ResultSet rs) throws Exception {
    	int count = 0;
    	rs.last();
    	count = rs.getRow();
    	rs.beforeFirst();
    	return count;    	
    }
    
    private static void searchProduct() {
    	try {
    		String productName = inputProductName("Enter name to be searched: ");
    		ResultSet result = controller.searchProduct(productName);
    		printProduct(result);
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}		
    }
    
    private static void printProduct(ResultSet rs) {
    	try {
    		if (getResTotal(rs) == 0) { 
    			System.out.println("Error: name not found!!!");
    		} else {
    			printDivider();
    			System.out.printf("     %-15s %-20s %-15s %-10s %-15s %-10s%n",
    	        		"productCode","productName","materialType","size", "genderType");
    			int row = 1;
    			while (rs.next()) {
    				String productCode = rs.getString("productCode");
    	            String productName = rs.getString("productName");
    	            String materialType = rs.getString("materialType");
    	            String size = rs.getString("size");
    	            String genderType = rs.getString("genderType");
    	            System.out.printf("%-4d %-15s %-20s %-15s %-10s %-15s %-10s%n",
    	            		row++,productCode,productName, materialType, size, genderType);    	        
    			}    			
    		}
    		printDivider();
    		System.out.println();
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    
    private static String filterNull(String str) {
    	return str == null ? "" : str;
    }
    
    private static void printDivider() {
    	for (int i = 1; i <= 90; i++) {
    		System.out.print("*");
    	}
    	System.out.println();
    }
    
    private static String inputProductName(String prompt) {
    	String input = null;
    	boolean valid;
    	do {
    		System.out.print(prompt);
    		input = kb.nextLine();
    		valid = input.matches("[a-zA-Z_ -.]{1,15}");
    	} while (!valid);
    	return input;
    }
}
