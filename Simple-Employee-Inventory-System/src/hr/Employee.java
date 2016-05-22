
package hr;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Assignment 1 - Employee Abstract Class 
 * @version 1.0
 * @author Cindy Diaz
 */
public abstract class Employee {
    //Instance Variables
    private String firstName, lastName, address, position, status, empId;   
    private int age, year, month, dayOfMonth, empIdInt;
    private Date dateOfHire;
    
    //Final Variables, for validation purposes
    private final int MIN_YEAR =1800;     
    private final int MAX_YEAR = 2016;     
    private final int MIN_MONTH =1;    
    private final int MAX_MONTH = 12;   
    private final int MIN_DAYOFMONTH =1;     
    private final int MAX_DAYOFMONTH = 31; 
    private final int MIN_AGE =14;     
    private final int MAX_AGE = 90;
    
    //Employee Class Constructor
    public Employee(int empIdInt, String firstName, String lastName, int age,String position, 
            String status, String address,int year, int month, int dayOfMonth)
    {
        //Validate strings are not empty and numeric values are valid in given range
        if(age < MIN_AGE || age > MAX_AGE){
            throw new IllegalArgumentException(
            "Age: " + age + " out of range. Must be in the range "+  MIN_AGE + " to " + MAX_AGE);       
        }        
        if(year <= MIN_YEAR || year > MAX_YEAR){
            throw new IllegalArgumentException(
            "Year: " + year + " out of range. Must be in the range "+  MIN_YEAR + " to " + MAX_YEAR);       
        }
        if(month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException(
                "Month: " + month + " out of range. Must be in the range "+  MIN_MONTH + " to " + MAX_MONTH);
        }
        if(dayOfMonth < MIN_DAYOFMONTH || dayOfMonth > MAX_DAYOFMONTH) {
            throw new IllegalArgumentException(
                "Day: " + dayOfMonth + " out of range. Must be in the range "+  MIN_DAYOFMONTH + " to " + MAX_DAYOFMONTH);
        }
        if(firstName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: First Name");
        }
        if(lastName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Last Name");
        }
        if(position.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Position");
        }
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }      
        if(status.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Status");
        } 
        this.age = age;
        this.year = year;
        this.month = month;
        this.dayOfMonth =dayOfMonth;
        this.dateOfHire = new GregorianCalendar(year,month-1, dayOfMonth).getTime();        
        this.firstName = firstName;
        this.lastName = lastName;       
        this.position = position;
        this.address = address;
        this.status = status; 
        this.empIdInt = empIdInt;       
    }//End of constructor
    public Employee(String firstName, String lastName, int age,String position, 
            String status, String address,int year, int month, int dayOfMonth)
    {
        //Validate strings are not empty and numeric values are valid in given range
        if(age < MIN_AGE || age > MAX_AGE){
            throw new IllegalArgumentException(
            "Age: " + age + " out of range. Must be in the range "+  MIN_AGE + " to " + MAX_AGE);       
        }        
        if(year <= MIN_YEAR || year > MAX_YEAR){
            throw new IllegalArgumentException(
            "Year: " + year + " out of range. Must be in the range "+  MIN_YEAR + " to " + MAX_YEAR);       
        }
        if(month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException(
                "Month: " + month + " out of range. Must be in the range "+  MIN_MONTH + " to " + MAX_MONTH);
        }
        if(dayOfMonth < MIN_DAYOFMONTH || dayOfMonth > MAX_DAYOFMONTH) {
            throw new IllegalArgumentException(
                "Day: " + dayOfMonth + " out of range. Must be in the range "+  MIN_DAYOFMONTH + " to " + MAX_DAYOFMONTH);
        }
        if(firstName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: First Name");
        }
        if(lastName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Last Name");
        }
        if(position.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Position");
        }
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }      
        if(status.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Status");
        } 
        this.age = age;
        this.year = year;
        this.month = month;
        this.dayOfMonth =dayOfMonth;
        this.dateOfHire = new GregorianCalendar(year,month-1, dayOfMonth).getTime();        
        this.firstName = firstName;
        this.lastName = lastName;       
        this.position = position;
        this.address = address;
        this.status = status; 
             
    }//End of constructor
    
    public Employee(String firstName, String lastName, int age,String position, 
            String status, String address,int year, int month, int dayOfMonth,String empId)
    {
        //Validate strings are not empty and numeric values are valid in given range
        if(age < MIN_AGE || age > MAX_AGE){
            throw new IllegalArgumentException(
            "Age: " + age + " out of range. Must be in the range "+  MIN_AGE + " to " + MAX_AGE);       
        }        
        if(year <= MIN_YEAR || year > MAX_YEAR){
            throw new IllegalArgumentException(
            "Year: " + year + " out of range. Must be in the range "+  MIN_YEAR + " to " + MAX_YEAR);       
        }
        if(month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException(
                "Month: " + month + " out of range. Must be in the range "+  MIN_MONTH + " to " + MAX_MONTH);
        }
        if(dayOfMonth < MIN_DAYOFMONTH || dayOfMonth > MAX_DAYOFMONTH) {
            throw new IllegalArgumentException(
                "Day: " + dayOfMonth + " out of range. Must be in the range "+  MIN_DAYOFMONTH + " to " + MAX_DAYOFMONTH);
        }
        if(firstName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: First Name");
        }
        if(lastName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Last Name");
        }
        if(position.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Position");
        }
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }      
        if(status.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Status");
        } 
        this.age = age;
        this.year = year;
        this.month = month;
        this.dayOfMonth =dayOfMonth;
        this.dateOfHire = new GregorianCalendar(year,month-1, dayOfMonth).getTime();        
        this.firstName = firstName;
        this.lastName = lastName;       
        this.position = position;
        this.address = address;
        this.status = status; 
        this.empId = empId;       
    }//End of constructor

    //Accessor Methods
    
    //Method to return employee's frist name
    public String getFirstName() 
    {
        return this.firstName;
    }
    
    //Method to return employee's last name
    public String getLastName() 
    {
        return this.lastName;
    }
    
    //Method to return employee's full name
    public String getFullName() 
    {
        return firstName + " " + lastName;
    }
    
    //Method to return employee's frist name
    public String getAddress() {
        return this.address;
    }
    
    //Method to return employee's position
    public String getPosition() 
    {
        return this.position;
    }
    
    //Method to return employee' status
    public String getStatus() 
    {
        return this.status;
    }
    //Method to return employee's age
    public int getAge() 
    {
        return this.age;
    }
    //Method to return employee's id number
    public String getEmpId() 
    {
        return this.empId;
    }
    public int getEmpIdInt() 
    {
        return this.empIdInt;
    }
    
    //Method to return the year the employee was hired
    public int getYear()
    {
        return this.year;
    }
    
    //Method to return the month the employee was hired
    public int getMonth()
    {
        return this.month;
    }
    
    //Method to return the day of the month the employee was hired
    public int getDayOfMonth()
    {
        return this.dayOfMonth;
    }
    
    //Method to return the date the employee was hired
    public Date getDateOfHire() 
    {
        return this.dateOfHire;
    }

    //Mutator Methods
    
    //Method to set employee's frist name, takes a string as a parameter
    public void setFirstName(String firstName) 
    {
        if(firstName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: First Name");
        }
        this.firstName = firstName;
    }

    //Method to set employee's last name, takes a string as a parameter
    public void setLastName(String lastName) 
    {
        if(lastName.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Last Name");
        }
        this.lastName = lastName;
    }

    //Method to set employee's address, takes a string as a parameter
     public void setAddress(String address) 
    {
        if(address.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Address");
        }         
        this.address = address;
    }
    
     //Method to set employee's position, takes a string as a parameter
    public void setPosition(String position) 
    {
        if(position.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Position");
        }
        this.position = position;
    }
    
    //Method to set employee's status, takes a string as a parameter
    public void setStatus(String status) 
    {
        if(status.trim().length() == 0){
            throw new IllegalArgumentException(
                    "Need to provide an argument for field: Status");
        }
        this.status = status;
    }

    //Method to set employee's age,, takes an integer as a parameter
    public void setAge(int age) 
    {
        if(age <= MIN_AGE || age > MAX_AGE){
            throw new IllegalArgumentException(
            "Age: " + age + " out of range. Must be in the range "+  MIN_AGE + " to " + MAX_AGE);       
        }
        this.age = age;
    }

    //Method to set employee's id, takes a string as a parameter
    protected void setEmpId(String empId) 
    {
        this.empId = empId;
    }

    //Method to set employee's date og hire, takes three integers as parameter
    public void setDateOfHire(int year, int month, int dayOfMonth) 
    {
        if(year <= MIN_YEAR || year > MAX_YEAR){
            throw new IllegalArgumentException(
            "Year: " + year + " out of range. Must be in the range "+  MIN_YEAR + " to " + MAX_YEAR);       
        }
        if(month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException(
                "Month: " + month + " out of range. Must be in the range "+  MIN_MONTH + " to " + MAX_MONTH);
        }
        if(dayOfMonth < MIN_DAYOFMONTH || dayOfMonth > MAX_DAYOFMONTH) {
            throw new IllegalArgumentException(
                "Day: " + dayOfMonth + " out of range. Must be in the range "+  MIN_DAYOFMONTH + " to " + MAX_DAYOFMONTH);
        }
        this.dateOfHire = new GregorianCalendar(year,month-1, dayOfMonth).getTime();
    }
    //Abstract mehod to calculate the pay for employees depending what type thery are
    public abstract double earnings();
    
    @Override
    public String toString()
    {
        String employeeInfo = "";
        employeeInfo += getFullName();        
        return employeeInfo;                       
    }
    public String getEmpInformation()
    {
        String employeeInfo = "";
        employeeInfo += "Employee Type:\t\t" + this.getClass().getSimpleName();
        employeeInfo += "\nName:\t\t" + getFullName();
        employeeInfo += "\nId:\t\t" + getEmpIdInt();
        employeeInfo += "\nAge:\t\t" + getAge();
        employeeInfo += "\nPosition:\t\t" + getPosition();
        employeeInfo += "\nHire-Date:\t\t" + getDateOfHire(); 
        
        return employeeInfo; 
    }
    
    
}//End of Employee Class
