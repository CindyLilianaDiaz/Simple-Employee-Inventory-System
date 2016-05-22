package product;
import general.ServiceClass;
import manufacturers.Manufacturer;
/**
 * Simple HR System - Product Class
 * This class is used to create Product objects. Implements HAS-A relationship with Manufacturer class.
 * @author Cindy Diaz
 * @version 2.0
 */
public class Product {
    //Instance Variables
    private String productId, productName, category, description, partNum;
    private int minimumInventory, productIdInt;
    private double productCost, productPrice, productMarkup;
    private Manufacturer manufacturer;
    //Final Variables for validation purposes
    private final int MIN_VALUE = 0;
    private final int MIN_INVENTORY = 0;
    
    //Product Class Constructor
    public Product( int productIdInt, String productName, String category,Manufacturer manufacturer,
            String description, String partNum, double productCost, 
            double productPrice, double productMarkup, int minimumInventory)
    {
        //Validate strings are not empty and numeric values are valid in given range
        if(productCost < MIN_VALUE){
            throw new IllegalArgumentException(
            "Product Cost: " + productCost + " out of range. Must be greater than "+  MIN_VALUE );       
        }
        if(productPrice < productCost){
            throw new IllegalArgumentException(
            "Product Price: " + productPrice + " out of range. Must be greater than your product cost. The cost of this product is: " + productCost );       
        }    
        if(minimumInventory < MIN_INVENTORY){
            throw new IllegalArgumentException(
            "Minimum Inventory: " + minimumInventory + " out of range. Must be greater than "+  MIN_INVENTORY );       
        } 
        if(productName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Product Name");
        }
        if(category.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Category");
        }
        if(description.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Description");
        }
        if(partNum.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Part Number");
        }
        if(partNum.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Part Number");
        }
        this.productName = productName;
        this.category = category;
        this.manufacturer = manufacturer;
        this.description = description;
        this.partNum = partNum;           
        this.productCost = productCost;
        this.productPrice = productPrice;
        this.minimumInventory = minimumInventory;
        this.productIdInt = productIdInt;
        this.productMarkup = productMarkup;//Calculate markup
    }//End of constructor
    
    //Product Class Constructor
    public Product( String productName, String category,Manufacturer manufacturer,
            String description, String partNum, double productCost, 
            double productPrice,int minimumInventory)
    {
        //Validate strings are not empty and numeric values are valid in given range
        if(productCost < MIN_VALUE){
            throw new IllegalArgumentException(
            "Product Cost: " + productCost + " out of range. Must be greater than "+  MIN_VALUE );       
        }
        if(productPrice < productCost){
            throw new IllegalArgumentException(
            "Product Price: " + productPrice + " out of range. Must be greater than your product cost. The cost of this product is: " + productCost );       
        }    
        if(minimumInventory < MIN_INVENTORY){
            throw new IllegalArgumentException(
            "Minimum Inventory: " + minimumInventory + " out of range. Must be greater than "+  MIN_INVENTORY );       
        } 
        if(productName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Product Name");
        }
        if(category.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Category");
        }
        if(description.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Description");
        }
        if(partNum.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Part Number");
        }
        if(partNum.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Part Number");
        }
        this.productName = productName;
        this.category = category;
        this.manufacturer = manufacturer;
        this.description = description;
        this.partNum = partNum;           
        this.productCost = productCost;
        this.productPrice = productPrice;
        this.minimumInventory = minimumInventory;
        //setProductId("P" + ServiceClass.getProductId());
        setProductMarkup(productPrice-productCost);//Calculate markup
    }//End of constructor
    
    //Accessor Methods
    
    //Method to return product id
    public String getProductId() 
    {
        return this.productId;
    }
    
    //Method to return product id
    public int getProductIdInt() 
    {
        return this.productIdInt;
    }


    //Method to return the product name
    public String getProductName() 
    {
        return this.productName;
    }

    //Method to return the product category
    public String getCategory() 
    {
        return this.category;
    }

    //Method to return the product description
    public String getDescription() 
    {
        return this.description;
    }

    //Method to return the product part number
    public String getPartNum() 
    {
        return this.partNum;
    }

    //Method to return the product minimum inventory
    public int getMinimumInventory() 
    {
        return this.minimumInventory;
    }

    //Method to return the product cost
    public double getProductCost() 
    {
        return this.productCost;
    }

    //Method to return the product price
    public double getProductPrice() 
    {
        return this.productPrice;
    }

    //Method to return the product markup
    public double getProductMarkup() 
    {
        return this.productMarkup;
    }

    //Method to return the product manufacurer (object)
    public Manufacturer getManufacturer() 
    {
        return this.manufacturer;
    }
    
    //Method to return the product manufacurer name (string)
    public String getManufacturerInfo()
    {
        return manufacturer.getManufacturerName();
    }
    
    
    //Mutator Methods
    
    //Method to set product id, private since it should only be called in the constructor of this class
    private void setProductId(String productId) 
    {
        this.productId = productId;
    }

    //Method to set product id, takes a string as a parameter
    public void setProductName(String productName) 
    {
        if(productName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Product Name");
        }
        this.productName = productName;
    }

    //Method to set product category, takes a string as a parameter
    public void setCategory(String category) 
    {
        if(category.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Category");
        }
        this.category = category;
    }

    //Method to set product description, takes a string as a parameter
    public void setDescription(String description) 
    {
        if(description.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Description");
        }
        this.description = description;
    }

    //Method to set product id, takes a string as a parameter
    public void setPartNum(String partNum) 
    {
        if(partNum.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Part Number");
        }
        this.partNum = partNum;
    }

    //Method to set product minimum inventory, takes an integer as a parameter
    public void setMinimumInventory(int minimumInventory) 
    {
        if(minimumInventory < MIN_INVENTORY){
            throw new IllegalArgumentException(
            "Minimum Inventory: " + minimumInventory + " out of range. Must be greater than "+  MIN_INVENTORY );       
        }        
        this.minimumInventory = minimumInventory;
    }

     //Method to set product cost, takes a double as a parameter
    public void setProductCost(double productCost) 
    {
        if(productCost < MIN_VALUE){
            throw new IllegalArgumentException(
            "Product Cost: " + productCost + " out of range. Must be greater than "+  MIN_VALUE );       
        }
        this.productCost = productCost;
    }

     //Method to set product price, takes a double as a parameter
    public void setProductPrice(double productPrice) 
    {
        if(productPrice < this.productCost){
            throw new IllegalArgumentException(
            "Product Price: " + productPrice + " out of range. Must be greater than the product cost. The cost of this product is" + this.productCost );       
        }    
        this.productPrice = productPrice;
    }

     //Method to set product markup, takes a double as a parameter. Private since is the calculation of price - cost
    private void setProductMarkup(double productMarkup) 
    {

        this.productMarkup = productMarkup;
    }

     //Method to set product manufacturer, takes a Manufacturer object as a parameter
    public void setManufacturer(Manufacturer manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    //Override toString method to display product information
    @Override
    public String toString()
    {
        String productInfo = "";
        productInfo += getProductName();        
        return productInfo;  
    }     
    public String getProductInfo()
    {
        String productInfo = "";
        productInfo += "Product Name:\t\t" + getProductName();
        productInfo += "\nProduct Id:\t\t" + getProductIdInt();
        productInfo += "\nCategory:\t\t" + getCategory();
        productInfo += "\nManufacturer:\t\t" + getManufacturerInfo();
        productInfo += "\nDescription:\t\t" + getDescription(); 
        productInfo += "\nPartNum:\t\t" + getPartNum();
        productInfo += "\nProduct Cost:\t\t" + String.format("%.2f", getProductCost());
        productInfo += "\nProduct Price:\t\t" + String.format("%.2f", getProductPrice());
        productInfo += "\nProduct Markup(dollars):\t" + String.format("%.2f", getProductMarkup());
        productInfo += "\nMinimum Inventory:\t" + getMinimumInventory();
        
        return productInfo;  
    }   
}