package general;
import hr.Employee;
import java.util.ArrayList;
import manufacturers.Manufacturer;
import product.Product;
/**
 * Simple HR System - ServiceClass Class
 * This class contains different elements to be used across all packages.
 * @author Cindy Diaz
 * @version 2.0
 */
public class ServiceClass {
    public static ArrayList<Employee> listEmployees = new ArrayList<>();
    public static ArrayList<Manufacturer> listManufacturer = new ArrayList<>();
    public static ArrayList<Product> listProducts = new ArrayList<>();
    
    private static int empId = 10000;
    private static int productId = 10000;
    private static int manufacturerId = 10000;
    private static int invoiceNumber = 100000;
    
    public static int getEmpId(){
        return empId++;//sends 10 000 first
    }
    
    public static int getProductId(){
        return productId++;
    }
    
    public static int getManufcturerId(){
        return manufacturerId++;
    }
    public static int getInvoiceNumber(){
        return invoiceNumber++;
    }
    //Method to retrieve an arraylist of strings containing product name
    public static ArrayList<String> getProductsArray()
    {  
        ArrayList<String> products = new ArrayList<>();
        //loop through our list of products
        for(Product temp : listProducts ){
            //Add to aeeay listt string
            products.add(temp.getProductName());
        }
        //return the string arraylist
        return products;
    }
}
