package hr;
import general.ServiceClass;
/**
 * Simple HR System - HourlyEmployee Class
 * This class is used to create Hourly Employee objects. Implements inheritance since it extends Employee class.
 * @author Cindy Diaz
 * @version 2.0
 */
public class HourlyEmployee extends Employee
{
    //Instance Variables
    private double hoursOfWork, payRate;
    //Final variables for validation purposes and calculations
    private final double MIN_PAY_RATE = 11.25;//Min hourly wage Ontario
    private final double MIN_HOURS = 0.0;
    private final double MAX_HOURS = 48.0;//Max hours per week normal rate
    private final double MAX_HOURS_WEEK = 60.0;//Limit after weekly maximum hours have been exceeded (to calculate overtime rate)
    private final double OVERTIME_RATE = 1.5;
    
    //HourlyEmployee Class Constructor
    public HourlyEmployee (int empIdInt,String firstName, String lastName, int age,
            String position, String status, String address,int year, int month, int dayOfMonth, double payRate, double hoursOfWork)
    {
        //Super class (Employee Class) constructor
        super(empIdInt,firstName,lastName,age,position,
                status,address, year, month, dayOfMonth);
        //Validate numeric values are valid in given range
        if(payRate < MIN_PAY_RATE){
            throw new IllegalArgumentException(
                "Pay Rate: " + payRate + " out of range. Must greater than "+  MIN_PAY_RATE );
        }
        if(hoursOfWork < MIN_HOURS || hoursOfWork > MAX_HOURS_WEEK){
            throw new IllegalArgumentException(
                "Hours of work: " + hoursOfWork + " out of range. Must in the greater than: "+  MIN_HOURS + " and less than " + MAX_HOURS_WEEK);
        }
        this.payRate = payRate;
        this.hoursOfWork = hoursOfWork;
    }//End of constructor
    
    public HourlyEmployee (String firstName, String lastName, int age,
            String position, String status, String address,int year, int month, int dayOfMonth, double payRate, double hoursOfWork)
    {
        //Super class (Employee Class) constructor
        super(firstName,lastName,age,position,
                status,address, year, month, dayOfMonth);
        //Validate numeric values are valid in given range
        if(payRate < MIN_PAY_RATE){
            throw new IllegalArgumentException(
                "Pay Rate: " + payRate + " out of range. Must greater than "+  MIN_PAY_RATE );
        }
        if(hoursOfWork < MIN_HOURS || hoursOfWork > MAX_HOURS_WEEK){
            throw new IllegalArgumentException(
                "Hours of work: " + hoursOfWork + " out of range. Must in the greater than: "+  MIN_HOURS + " and less than " + MAX_HOURS_WEEK);
        }
        this.payRate = payRate;
        this.hoursOfWork = hoursOfWork;
    }//End of constructor
    
    //Method to return how many hours the employee has worked
    public double getHoursOfWork() 
    {
        return hoursOfWork;
    }

    //Method to return employee's pay rate
    public double getPayRate() 
    {
        return payRate;
    }

    //Method to set how many hours the employee has worked
    public void setHoursOfWork(double hoursOfWork) 
    {
        if(hoursOfWork < MIN_HOURS || hoursOfWork > MAX_HOURS_WEEK){
            throw new IllegalArgumentException(
                "Hours of work: " + hoursOfWork + " out of range. Must in the greater than: "+  MIN_HOURS + " and less than " + MAX_HOURS_WEEK);
        }
        this.hoursOfWork = hoursOfWork;
    }
    
    //Method to set employee's pay rate, takes a double as a parameter
    public void setPayRate(double payRate)
    {
        if(payRate < 0.0){
            throw new IllegalArgumentException(
                "Pay Rate: " + payRate + " out of range. Must greater than "+  MIN_PAY_RATE );
        }
        this.payRate = payRate;
    }
    
    //Override super class earnings method to calculate pay of hourly employees
   @Override
    public double earnings()
    {
        //Check how many hours they work per week, if greater than 40 calculate pay with overtime rate
        if (getHoursOfWork() <= MAX_HOURS)
        {
            return getHoursOfWork() * getPayRate();
        }
        else
        {
            return MAX_HOURS * getPayRate() + (getHoursOfWork()- MAX_HOURS) * getPayRate() * OVERTIME_RATE;
        }
    }
    
    //Override super class toString method and add information about hourly employees
    @Override
    public String getEmpInformation()
    {       
        return super.getEmpInformation() +
                "\nPay Rate:\t\t" + String.format("%.2f", getPayRate())+
                "\nWeekly Hours:\t\t"+ String.format("%.2f", getHoursOfWork());
    }

}//End of HourlyEmployee Class
