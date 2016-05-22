package hr;
import general.ServiceClass;
/**
 * Simple HR System - CommissionEmployee Class
 * This class is used to create Commission Employee objects. Implements inheritance since it extends Employee class.
 * @author Cindy Diaz
 * @version 2.0
 */
public class CommissionEmployee extends Employee
{
    //Instance Variables
    private double commissionRate, grossSales;
    //Final Variables, for validation purposes
    private final double MIN_COMMISSION_RATE = 0.0;
    private final double MAX_COMMISSION_RATE = 1.0;
    private final double MIN_GROSS_SALES = 0.0;
    
    // CommissionEmployee Class Constructor
    public CommissionEmployee(int empIdInt,String firstName, String lastName, int age,
            String position, String status, String address,int year, int month, int dayOfMonth, double commissionRate, double grossSales)
    {
        //Super class (Employee Class) constructor
        super(empIdInt, firstName,lastName,age,position,
                status, address, year, month, dayOfMonth);
        //Validate numeric values are valid in given range
        if(commissionRate <= MIN_COMMISSION_RATE || commissionRate >= MAX_COMMISSION_RATE)
        {
            throw new IllegalArgumentException(
                "Commission Rate: " + commissionRate + " out of range. Must be in the greater than "+  MIN_COMMISSION_RATE + " and less or equal than " + MAX_COMMISSION_RATE );
        }
        if(grossSales < MIN_GROSS_SALES )
        {
            throw new IllegalArgumentException(
                "Gross Sales: " + grossSales + " out of range. Must greater or equal than "+  MIN_GROSS_SALES );
        }
        this.commissionRate = commissionRate;
        this.grossSales = grossSales;
    }//End of constructor
    
    public CommissionEmployee(String firstName, String lastName, int age,
            String position, String status, String address,int year, int month, int dayOfMonth, double commissionRate, double grossSales)
    {
        //Super class (Employee Class) constructor
        super(firstName,lastName,age,position,
                status, address, year, month, dayOfMonth);
        //Validate numeric values are valid in given range
        if(commissionRate <= MIN_COMMISSION_RATE || commissionRate >= MAX_COMMISSION_RATE)
        {
            throw new IllegalArgumentException(
                "Commission Rate: " + commissionRate + " out of range. Must be in the greater than "+  MIN_COMMISSION_RATE + " and less or equal than " + MAX_COMMISSION_RATE );
        }
        if(grossSales < MIN_GROSS_SALES )
        {
            throw new IllegalArgumentException(
                "Gross Sales: " + grossSales + " out of range. Must greater or equal than "+  MIN_GROSS_SALES );
        }
        this.commissionRate = commissionRate;
        this.grossSales = grossSales;
    }//End of constructor
    
    //Method to return employee's commission rate
    public double getCommissionRate()
    {
        return this.commissionRate;
    }
    
    //Method to return employee's gross sales
    public double getGrossSales()
    {
        return this.grossSales;
    }
    
    //Method to set employee's commission rate, takes a doble as a parameter
    public void setCommissionRate(double commissionRate) {
        if(commissionRate <= MIN_COMMISSION_RATE || commissionRate > MAX_COMMISSION_RATE)
        {
            throw new IllegalArgumentException(
                "Commission Rate: " + commissionRate + " out of range. Must be in the greater than "+  MIN_COMMISSION_RATE + " and less or equal than " + MAX_COMMISSION_RATE );
        }
        this.commissionRate = commissionRate;
    }
    
    //Method to set employee's gross sales, takes a doble as a parameter
    public void setGrossSales(double totalSales) {
        if(grossSales < MIN_GROSS_SALES )
        {
            throw new IllegalArgumentException(
                "Gross Sales: " + grossSales + " out of range. Must greater or equal than "+  MIN_GROSS_SALES );
        }
        this.grossSales = totalSales;
    }
    //Override super class earnings method to calculate pay of commission employees
    @Override
    public double earnings()
    {
        return getCommissionRate() * getGrossSales();
    }
    
    //Override super class toString method and add information about commission employees
    @Override
    public String getEmpInformation()
    {       
        return super.getEmpInformation() +
                "\nCommission:\t\t" + String.format("%.2f", getCommissionRate()) +
                "\nGross Sales:\t\t" + String.format("%.2f", getGrossSales());
    }
    
}//End of CommissionEmployee Class
