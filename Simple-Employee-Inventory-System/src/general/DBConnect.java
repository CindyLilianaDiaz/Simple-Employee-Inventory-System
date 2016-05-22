package general;
import hr.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import manufacturers.Manufacturer;
import product.Product;
/**
 * Simple HR System - DBConnect
 * This class handles all operations where connection to the database is required.
 * @author Cindy Diaz
 * @version 2.0
 */
public class DBConnect 
{
    private final String DB_URL = "jdbc:mysql://MySQL_DATABASE";
    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private Connection conn = null;
    
    private  Connection getConn()
    {
        try 
        {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }catch(Exception error)
        {
            error.printStackTrace();
        }
        return conn;
    }
    
    public boolean saveSalaryEmp(String firstName, String lastName, int age, String position, String status,
            String address, int year, int month, int day, double salary) throws SQLException
    {
        Employee tempEmp = new SalaryEmployee (firstName, lastName, age, position, status,
            address, year, month, day, salary);
        Connection connection = getConn();
        Statement stmt = null;
        stmt = connection.createStatement();
        String sqlEmp = "INSERT INTO EmployeesTable " + "(`firstName`, `lastName`, `age`, " +
                "`position`, `status`, `address`, `year`, `month`, `day`) " +
                "VALUES('" + firstName + "', '" + lastName + "', '" + age + "', '" + 
                position + "', '" + status + "', '" + address + "', '" + year + "', '" +
                month + "', '" + day + "');";
        stmt.executeUpdate(sqlEmp);
        stmt.close();
        
        int empId = getEmpIdFromDB(tempEmp);
        Statement stmtSalaryEmp = null;
        stmtSalaryEmp = connection.createStatement();
        String sqlSalary = "INSERT INTO SalaryEmployees " + "(`empId`, `salary`) " +
                "VALUES ('" + empId + "', '" + salary + "');" ;
        stmtSalaryEmp.executeUpdate(sqlSalary);
        stmtSalaryEmp.close();
        connection.close();
        
        return true;
    }
    
    public boolean saveHourlyEmp(String firstName, String lastName, int age, String position, String status,
            String address, int year, int month, int day, double hourRate, double hours) throws SQLException
    {
         Employee tempEmp = new HourlyEmployee (firstName, lastName, age, position, status,
            address, year, month, day, hourRate, hours);
        Connection connection = getConn();
        Statement stmt = null;
        stmt = connection.createStatement();
        String sqlEmp = "INSERT INTO EmployeesTable " + "(`firstName`, `lastName`, `age`, " +
                "`position`, `status`, `address`, `year`, `month`, `day`) " +
                "VALUES('" + firstName + "', '" + lastName + "', '" + age + "', '" + 
                position + "', '" + status + "', '" + address + "', '" + year + "', '" +
                month + "', '" + day + "');";
        stmt.executeUpdate(sqlEmp);
        stmt.close();
        
        int empId = getEmpIdFromDB(tempEmp);
        Statement stmtHourlyEmp = null;
        stmtHourlyEmp = connection.createStatement();
        String sqlSalary = "INSERT INTO HourlyEmployees " + "(`empId`, `hourRate`, `hours`) " +
                "VALUES ('" + empId + "', '" + hourRate + "', '"+ hours + "');" ;
        stmtHourlyEmp.executeUpdate(sqlSalary);
        stmtHourlyEmp.close();
        connection.close();
        return true;
    }
    
    public boolean saveCommEmp(String firstName, String lastName, int age, String position, String status,
            String address, int year, int month, int day, double commission, double grossSales) throws SQLException
    {
         Employee tempEmp = new CommissionEmployee (firstName, lastName, age, position, status,
            address, year, month, day, commission, grossSales);
        Connection connection = getConn();
        Statement stmt = null;
        stmt = connection.createStatement();
        String sqlEmp = "INSERT INTO EmployeesTable " + "(`firstName`, `lastName`, `age`, " +
                "`position`, `status`, `address`, `year`, `month`, `day`) " +
                "VALUES('" + firstName + "', '" + lastName + "', '" + age + "', '" + 
                position + "', '" + status + "', '" + address + "', '" + year + "', '" +
                month + "', '" + day + "');";
        stmt.executeUpdate(sqlEmp);
        stmt.close();
        
        int empId = getEmpIdFromDB(tempEmp);
        Statement stmtHourlyEmp = null;
        stmtHourlyEmp = connection.createStatement();
        String sqlSalary = "INSERT INTO CommissionEmployees " + "(`empId`, `commission`, `grossSales`) " +
                "VALUES ('" + empId + "', '" + commission + "', '"+ grossSales + "');" ;
        stmtHourlyEmp.executeUpdate(sqlSalary);
        stmtHourlyEmp.close();
        connection.close();
        return true;
    }
    
    private int getEmpIdFromDB(Employee employee) throws SQLException
    {
        Connection connection = getConn();
        Statement stmtEmpId = null;
        ResultSet resultSet = null;
        int empId = 0;
        
        stmtEmpId = connection.createStatement();
        String sqlEmpId = "SELECT id FROM EmployeesTable WHERE firstName = '" + employee.getFirstName() + 
                "' AND lastName = '" + employee.getLastName() + "';";
        resultSet = stmtEmpId.executeQuery(sqlEmpId);
        while(resultSet.next())
        {
            empId = resultSet.getInt(1);
        }
        resultSet.close();
        stmtEmpId.close();
        connection.close();
        System.out.println("Retrieved Emp Id: " + empId);
        
        return empId;     
    }
    
    public void populateEmployeeArraylist() throws SQLException
    {
        ServiceClass.listEmployees.clear();
        
        Connection connection = getConn();
        Statement stmt = null;
        stmt = connection.createStatement();
        String query = "SELECT * FROM EmployeesTable;";
        ResultSet rsEmp = null;
        rsEmp = stmt.executeQuery(query);
        while(rsEmp.next())
        {
            //Salary Employee
            Statement stmtSalaryEmp = null;
            stmtSalaryEmp = connection.createStatement();
            ResultSet rsSalaryEmp = null;
            SalaryEmployee tempSalaryEmp = null;
            String querySalaryEmp = "SELECT * FROM SalaryEmployees WHERE empId = '" + rsEmp.getInt("id") + "';";
            rsSalaryEmp = stmtSalaryEmp.executeQuery(querySalaryEmp);
            while(rsSalaryEmp.next())
            {
                tempSalaryEmp = new SalaryEmployee (rsEmp.getInt("id"), rsEmp.getString("firstName"),
                    rsEmp.getString("lastName"), rsEmp.getInt("age"), rsEmp.getString("position"),
                    rsEmp.getString("status"), rsEmp.getString("address"), rsEmp.getInt("year"),
                    rsEmp.getInt("month"),rsEmp.getInt("day"),rsSalaryEmp.getDouble("salary"));
                ServiceClass.listEmployees.add(tempSalaryEmp);
                System.out.println("Salary emp: " + rsEmp.getInt("id"));
            }
            rsSalaryEmp.close();
            stmtSalaryEmp.close();
            //Hourly Employee
            Statement stmtHourlyEmp = null;
            stmtHourlyEmp = connection.createStatement();
            ResultSet rsHourlyEmp = null;
            HourlyEmployee tempHourlyEmp = null;
            String queryHourlyEmp = "SELECT * FROM HourlyEmployees WHERE empId = '" + rsEmp.getInt("id") + "';";
            rsHourlyEmp = stmtHourlyEmp.executeQuery(queryHourlyEmp);
            while(rsHourlyEmp.next())
            {
                tempHourlyEmp = new HourlyEmployee (rsEmp.getInt("id"), rsEmp.getString("firstName"),
                    rsEmp.getString("lastName"), rsEmp.getInt("age"), rsEmp.getString("position"),
                    rsEmp.getString("status"), rsEmp.getString("address"), rsEmp.getInt("year"),
                    rsEmp.getInt("month"),rsEmp.getInt("day"),rsHourlyEmp.getDouble("hourRate"), rsHourlyEmp.getDouble("hours"));
                ServiceClass.listEmployees.add(tempHourlyEmp);
                System.out.println("Hourly emp: " + rsEmp.getInt("id"));
            }
            rsHourlyEmp.close();
            stmtHourlyEmp.close();
            //commission employee
            Statement stmtCommEmp = null;
            stmtCommEmp = connection.createStatement();
            ResultSet rsCommEmp = null;
            CommissionEmployee tempCommEmp = null;
            String queryCommEmp = "SELECT * FROM CommissionEmployees WHERE empId = '" + rsEmp.getInt("id") + "';";
            rsCommEmp = stmtCommEmp.executeQuery(queryCommEmp);
            while(rsCommEmp.next())
            {
                tempCommEmp = new CommissionEmployee (rsEmp.getInt("id"), rsEmp.getString("firstName"),
                    rsEmp.getString("lastName"), rsEmp.getInt("age"), rsEmp.getString("position"),
                    rsEmp.getString("status"), rsEmp.getString("address"), rsEmp.getInt("year"),
                    rsEmp.getInt("month"),rsEmp.getInt("day"),rsCommEmp.getDouble("commission"), rsCommEmp.getDouble("grossSales"));
                ServiceClass.listEmployees.add(tempCommEmp);
                 System.out.println("Comm emp: " + rsEmp.getInt("id"));
            }
            rsCommEmp.close();
            stmtCommEmp.close();
        }
        rsEmp.close();
        stmt.close();
        connection.close();
    }
    //Method to populate the Arraylist with manufacurers, for dropdown selection
    public void populateManufacturerArray()
    {
        try
        {
            //Start connections, statements, resultsets
           Connection connection = getConn();
           Statement stmtGetManufacturers = null;
           stmtGetManufacturers = connection.createStatement();
           //Create query
           String query = "SELECT * FROM ManufacturersTable";
           ResultSet rsManufacturers = null;
           //Store results of query en resultsset
           rsManufacturers = stmtGetManufacturers.executeQuery(query);
           //loop in the results
           while(rsManufacturers.next())
           {
               //For every result create a manufacturer object to add to arraylist
               Manufacturer currentManufacturer = new Manufacturer(rsManufacturers.getInt("manufacturerId"),
                       rsManufacturers.getString("manufacturerName"), rsManufacturers.getString("manufacturerAddress"), 
                       rsManufacturers.getString("manufacturerPhoneNumber"),rsManufacturers.getString("manufacturerContact"));
                       ServiceClass.listManufacturer.add(currentManufacturer);
           }
           //Close connection, statemet, result set
           rsManufacturers.close();
           stmtGetManufacturers.close();
           connection.close();       
        }
        //catch exception
        catch(SQLException error)
        {
            DBConnect.saveErrorLogDB(error);
        }
        catch(Exception error)
        {
            DBConnect.saveErrorLogDB(error);
        }
    }
    //Mehod to populate the arraylist of products
    public void populateProductsArray() throws SQLException
    {
        //Clear data stored to update with current one
        ServiceClass.listProducts.clear();
        try
        {
            //Start connections, statements, resultsets
            Connection connection = getConn();
            Statement stmtGetManufacturers = null;
            stmtGetManufacturers = connection.createStatement();
            //SQ query
            String query = "SELECT * FROM ProductsTable";
            //Store results of query en resultsset
            ResultSet rsProducts = null;
            rsProducts = stmtGetManufacturers.executeQuery(query);
            //loop through results
            while(rsProducts.next())
            {
                //Every product is associated with a manufacturer
                //To create an arraylist of products we need a manufcturer object
                //Retrieve the manufacturer information corresponding to the foreign key located in Products table
                Statement statM = null;
                ResultSet rs = null;
                int value = 0;
                statM = connection.createStatement();
                String query2 = "SELECT * FROM ManufacturersTable WHERE manufacturerId = '" + rsProducts.getInt("productManufacturer") + "';";
                rs =statM.executeQuery(query2);
                //Temporary manufacturer object creation with result found by query
                Manufacturer tempManufacturer = null;
                while(rs.next())
                {
                    //create temp manufacturer object
                    tempManufacturer = new Manufacturer(rs.getInt("manufacturerId"), rs.getString("manufacturerName"), rs.getString("manufacturerAddress"), rs.getString("manufacturerPhoneNumber"),rs.getString("manufacturerContact"));
                }
                //Close statement, en resultset
                rs.close();
                statM.close();
                //Creade product with corresponding manufacturer object
                Product currentProduct = new Product (rsProducts.getInt("productId"),
                        rsProducts.getString("productName"), rsProducts.getString("productCategory"), 
                        tempManufacturer,rsProducts.getString("productDescription"),
                        rsProducts.getString("productPartNum"),rsProducts.getDouble("productCost"),
                        rsProducts.getDouble("productPrice"), rsProducts.getDouble("productMarkup"), rsProducts.getInt("minInventory"));
                //Add the current product to arraylist
                ServiceClass.listProducts.add(currentProduct);
            }
            //Close connection, statement, en resultset
            rsProducts.close();
            stmtGetManufacturers.close();
            connection.close();        
        }
        catch(SQLException error)
        {
            //error.printStackTrace();
            DBConnect.saveErrorLogDB(error);
        }
        catch(Exception error)
        {
            DBConnect.saveErrorLogDB(error);
        }
    }
    
    //Method to insert Manufacturer objects to database, takes String manufacturerName, String address, String phoneNumber, String contact as parameters
    public boolean insertManufacturerDB(String manufacturerName, String address, String phoneNumber, String contact) throws SQLException//Throw possible SQL Exception
    {
        //Get connection
        Connection connection = getConn();     
        //Declare and create statement
        Statement stmtManufacurer = null;
        stmtManufacurer = connection.createStatement();
        //Sql query
        String sql = "INSERT INTO ManufacturersTable " + "(`manufacturerName`, `manufacturerAddress`, `manufacturerPhoneNumber`, `manufacturerContact`) " +
                     "VALUES('" + manufacturerName + "', '" + address + "', '" + phoneNumber + "', '" + contact + "');";
        //Execute statement with the query
        stmtManufacurer.executeUpdate(sql);
        //return true if no excpetion was thrown
        return true;
    }
    //Method to retrieve the id generated by the database of a manufacturer object
    //Needed to create product object, foreign key
    //Takes a manufacturer object as parameter
    public int manufacturerIdFromDB(Manufacturer manufacturer) throws SQLException//Throw possible SQL Exception
    {
        //Get connection
        Connection connection = getConn();
        //Declare statements and resultsets          
        Statement stmtManufacurerId = null;
        ResultSet rs = null;
        //Variable to store Manufacturer Id retrieved from database
        int value = 0;
        stmtManufacurerId = connection.createStatement();
        //Get manufacturerId from database of the object passed 
        String query = "SELECT manufacturerId FROM ManufacturersTable WHERE manufacturerName = '" + manufacturer.getManufacturerName() + "';";
        rs = stmtManufacurerId.executeQuery(query);
        //Loop through results, in this case is only one
        while(rs.next())
        {
            value = rs.getInt(1);//Store the result from the query in column 1
        }
        //Close result sets, statements and connections
        rs.close();
        stmtManufacurerId.close();
        connection.close();
        //Testing purposes
        System.out.println("Retrieved Manufacturer Id: " + value);
        //return the value retrieved        
        return value;
    }
    //Method to insert product into database
    public boolean inserProductDB(String productName, String category, int manufacturerID,
                    String description, String partNum, double productCost, 
                    double productPrice, double productMarkup, int minimumInventory) throws SQLException
    {
        //Get connection
        Connection connection = getConn();
        //Declaare statement
        Statement stmtProduct = null;
        stmtProduct = conn.createStatement();
        //Query to insert product object
        String sqlProduct = "INSERT INTO ProductsTable " + "(`productName`, `productCategory`, `productManufacturer`, `productDescription`,"
                              + " `productPartNum`, `productCost`, `productPrice`, `productMarkup`, `minInventory`) " +
                              "VALUES('" + productName + "', '" + category + "', '" + manufacturerID + "', '" 
                              + description + "', '" + partNum + "', '" + productCost + "', '" 
                              + productPrice + "', '" + productMarkup + "', '" + minimumInventory+ "');";            
        //Execute query
        stmtProduct.executeUpdate(sqlProduct);
        //Close connection        
        stmtProduct.close();
        connection.close();
        //return true if no excpetions were thrown
        return true;
    }
    
    public static void saveErrorLogDB(Exception error)
    {
        StringWriter errorsData = new StringWriter();
        error.printStackTrace(new PrintWriter(errorsData));
        try {
            Connection connection = new DBConnect().getConn();
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO ErrorLog (`errorTime`, `errorMessage`) "
                    + "VALUES(CURRENT_TIMESTAMP, '" + errorsData.toString() + "');";
            stmt.executeUpdate(query);
            System.out.println("Error Log Succesfully inserted.");
            stmt.close();
            connection.close();
        } catch (SQLException errorSQL) {
            errorSQL.printStackTrace();
        }
        
    }
}