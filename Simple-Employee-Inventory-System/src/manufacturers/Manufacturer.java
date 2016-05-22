package manufacturers;
import general.ServiceClass;
/**
 * Simple HR System - Manufacturer Class
 * This class is used to create Manufacturer objects.
 * @author Cindy Diaz
 * @version 2.0
 */
public class Manufacturer {
    //Instance Variables
    private String manufacturerName, address, phoneNumber, contact, manufacturerId;
    private int manufacturerIdInt;
    
    //Empty construcotr
    public Manufacturer()
    {}
    public Manufacturer(int manufacturerIdInt,String manufacturerName, String address, String phoneNumber, String contact) 
    {
         //Validate strings are not empty
        if(manufacturerName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Manufacturer Name");
        }
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }
        if(phoneNumber.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Phone Number");
        }
        if(contact.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Contact");
        }
        this.manufacturerName = manufacturerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contact = contact;
        
        this.manufacturerIdInt = manufacturerIdInt;
    }//End of constructor
    
    //Manufacturer Class constructor
    public Manufacturer(String manufacturerName, String address, String phoneNumber, String contact) 
    {
         //Validate strings are not empty
        if(manufacturerName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Manufacturer Name");
        }
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }
        if(phoneNumber.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Phone Number");
        }
        if(contact.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Contact");
        }
        this.manufacturerName = manufacturerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contact = contact;
        //setManufacturerId("M" + ServiceClass.getManufcturerId());
    }//End of constructor

    //Accessor Methods
    
    //Method to return manufacturer name
    public String getManufacturerName() 
    {
        return this.manufacturerName;
    }

    //Method to return manufacturer id
    public String getManufacturerId() 
    {
        return this.manufacturerId;
    }
    
    //Method to return manufacturer id
    public int getManufacturerIdInt() 
    {
        return this.manufacturerIdInt;
    }
    
    //Method to return manufacturer address
    public String getAddress() 
    {
        return this.address;
    }

    //Method to return manufacturer phone number
    public String getPhoneNumber() 
    {
        return this.phoneNumber;
    }

    //Method to return manufacturer contact
    public String getContact() 
    {
        return this.contact;
    }

    //Mutator Methods
    
    //Method to set product id, private since it should only be called in the constructor of this class
    private void setManufacturerId(String manufacturerId)
    {
        this.manufacturerId = manufacturerId;
    }
    
    //Method to set manufacturer name, takes a String as a parameter
    public void setManufacturerName(String manufacturerName) 
    {
        if(manufacturerName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Manufacturer Name");
        }
        this.manufacturerName = manufacturerName;
    }

    //Method to set manufacturer address, takes a String as a parameter
    public void setAddress(String address) {
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }
        this.address = address;
    }

    //Method to set manufacturer phone number, takes a String as a parameter
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Phone Number");
        }
        this.phoneNumber = phoneNumber;
    }

    //Method to set manufacturer contact, takes a String as a parameter
    public void setContact(String contact) {
        if(contact.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Contact");
        }
        this.contact = contact;
    }

    //Override toString method to display manufacturer infomation
    @Override
    public String toString()
    {
        String manufacturerInfo = "";
        manufacturerInfo += getManufacturerName();
               
        return manufacturerInfo;  
    }
    
    public String getManufacturerInfo()
    {
        String manufacturerInfo = "";
        manufacturerInfo += "\nManufacturer Name:\t" + getManufacturerName();
        manufacturerInfo += "\nManufacturer Id:\t" + getManufacturerId();
        manufacturerInfo += "\nContact:\t\t" + getContact();
        manufacturerInfo += "\nPhone Number:\t\t" + getPhoneNumber();
        manufacturerInfo += "\nAddress:\t\t" + getAddress();
        
        return manufacturerInfo;  
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.manufacturerName.hashCode();
        hash = 7 * hash + this.address.hashCode();
        hash = 7 * hash + this.phoneNumber.hashCode();
        hash = 7 * hash + this.contact.hashCode();
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Manufacturer.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Manufacturer other = (Manufacturer) obj;
        if ((this.manufacturerName == null) ? (other.manufacturerName != null) : !this.manufacturerName.equals(other.manufacturerName)) {
            return false;
        }
        if (this.address == null ? other.address != null : !this.address.equals(other.address)) {
            return false;
        }
        if (this.phoneNumber == null ? other.phoneNumber != null : !this.phoneNumber.equals(other.phoneNumber)) {
            return false;
        }
        if (this.contact == null ? other.contact != null : !this.contact.equals(other.contact)) {
            return false;
        }
        return true;
    }
    
}
