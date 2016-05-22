package hr;
import general.ServiceClass;
/**
 * Simple HR System - SalaryyEmployee Class
 * This class is used to create Salary Employee objects. Implements inheritance since it extends Employee class.
 * @author Cindy Diaz
 * @version 2.0
 */
public class SalaryEmployee extends Employee
{
    //Instance Variables
    private double salary;
    //Final variables for validation purposes
    private final double MIN_WEEK_SALARY = 450.0;
    
    //SalaryEmployee Class Constructor
    public SalaryEmployee(int empIdInt, String firstName, String lastName, int age,
            String position, String status, String address,int year, int month, int dayOfMonth, double salary)
    {
        //Super class (Employee Class) constructor
        super(empIdInt, firstName,lastName,age,position,
                status, address, year, month, dayOfMonth);
        //Validate numeric values are valid in given range
        if(salary < MIN_WEEK_SALARY )
        {
            throw new IllegalArgumentException(
                "Salary: " + salary + " out of range. Must greater or equal than "+  MIN_WEEK_SALARY );
        }
        this.salary = salary;       
    }//End of constructor
    
    public SalaryEmployee(String firstName, String lastName, int age,
            String position, String status, String address,int year, int month, int dayOfMonth, double salary)
    {
        //Super class (Employee Class) constructor
        super(firstName,lastName,age,position,
                status, address, year, month, dayOfMonth);
        //Validate numeric values are valid in given range
        if(salary < MIN_WEEK_SALARY )
        {
            throw new IllegalArgumentException(
                "Salary: " + salary + " out of range. Must greater or equal than "+  MIN_WEEK_SALARY );
        }
        this.salary = salary;       
    }//End of constructor
    
    //Method to return employee's weekly salary
    public double getSalary()
    {
        return this.salary;
    }
    
    //Method to set employee's weekly salary, takes a double as a parameter
    public void setSalary(double salary)
    {
        if(salary < MIN_WEEK_SALARY )
        {
            throw new IllegalArgumentException(
                "Salary: " + salary + " out of range. Must greater or equal than "+  MIN_WEEK_SALARY );
        }
        this.salary = salary;
    }
    
    //Override super class earnings method to calculate pay of salary employee
    @Override
    public double earnings()
    {
        return getSalary();
    }
    
    //Override super class toString method and add information about salary employees
    @Override
    public String getEmpInformation()
    {       
        return super.getEmpInformation() + "\nSalary:\t\t" + String.format("%.2f", getSalary());
    }
}//End of SalaryEmployee Class
