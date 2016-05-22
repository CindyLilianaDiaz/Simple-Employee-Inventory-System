package gui;
import general.DBConnect;
import general.ErrorDataLog;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.border.EmptyBorder;
/**
 * Simple HR System - EmployeePanel Class
 * This class builds the Employee panel, to be implemented in BuildMainGUI class. .
 * @author Cindy Diaz
 * @version 2.0
 */
public class EmployeePanel extends JPanel
{
    //Declare JLabels
    private JLabel lblFirstName, lblLastName, lblAge, lblPosition, lblStatus, lblAddress, lblYear, lblMonth,lblDay,lblEmpType;
    private JLabel lblCommission, lblGrossSales;// For Commission Employee
    private JLabel lblHourRate, lblHours;//For Hourly Employee
    private JLabel lblSalary;//For Salary Employee
    //Declare JRadioButtos
    private JRadioButton rbSalaryEmp, rbHourlyEmp, rbCommEmp;
    private ButtonGroup bgEmpType;//ButtonGroup for RadioButtons
    //Declare JTextFields
    private JTextField txtFirstName, txtLastName, txtAge,txtPosition,txtStatus, txtAddress, txtSalary, txtCommission, txtGrossSales, txtHourRate, txtHours;
    //Declare JComboBoxes
    private JComboBox<Integer> ddYears;
    private JComboBox<Integer> ddMonths;
    private JComboBox<Integer> ddDays;
    //Declare and create the arrays for Combo boxes
    private final Integer[] YEARS = {1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2016};
    private final Integer[] MONTHS = {1,2,3,4,5,6,7,8,9,10,11,12};
    private final Integer[] DAYS = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    //Declare JButtons
    private JButton btnSubmit, btnClearAll;
    //Declare JPanels
    private JPanel commEmp, salaryEmp, hourlyEmp, mainPanel, southPanel, tempPanel;
    private ErrorDataLog errorLog;
    
    //Final Variables, for validation purposes
    private final int MIN_YEAR =1800;     
    private final int MAX_YEAR = 2016;     
    private final int MIN_MONTH =1;    
    private final int MAX_MONTH = 12;   
    private final int MIN_DAYOFMONTH =1;     
    private final int MAX_DAYOFMONTH = 31; 
    private final int MIN_AGE =14;     
    private final int MAX_AGE = 90;
    private final double MIN_WEEK_SALARY = 450.0;
    private final double MIN_PAY_RATE = 11.25;//Min hourly wage Ontario
    private final double MIN_HOURS = 0.0;
    private final double MAX_HOURS = 48.0;//Max hours per week normal rate
    private final double MAX_HOURS_WEEK = 60.0;//Limit after weekly maximum hours have been exceeded (to calculate overtime rate)
    private final double MIN_COMMISSION_RATE = 0.0;
    private final double MAX_COMMISSION_RATE = 1.0;
    private final double MIN_GROSS_SALES = 0.0;
    
    //Constructor for EmployeePanel
    public EmployeePanel()
    {
        errorLog = new ErrorDataLog();
        //Set layout to be border layout
        setLayout(new BorderLayout(10,10));
        //Set the for this panel to EmptyBorder with padding
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        //Build the mani panel - Employee general information
        buildMainPanel();
        //Add the main panel to EmployeePanel in position centre
        add(mainPanel, BorderLayout.CENTER);
        //Initialize tempPanel - It will be build after selection of Employee tab
        tempPanel = new JPanel();
        //Add tempPanel to EmployeePanel in position ssouth
        add(tempPanel, BorderLayout.SOUTH);
    }
    //Method to build the main panel - Contains Employee general information
    private void buildMainPanel()
    {
        //Initialize mainPanel
        mainPanel = new JPanel();
        //Set layout of mainPanel to grid layout
        mainPanel.setLayout(new GridLayout(11,2));
        //Create Labels for Employee information
        lblFirstName = new JLabel("First Name:");
        lblLastName = new JLabel("Last Name:");
        lblAge = new JLabel("Age:");
        lblPosition = new JLabel("Position:"); 
        lblStatus = new JLabel("Status:"); 
        lblAddress = new JLabel("Addres:"); 
        lblYear = new JLabel("Year:"); 
        lblMonth = new JLabel("Month:");
        lblDay = new JLabel("Day:");
        lblEmpType = new JLabel("Employee Type:");
        //Create text boxes
        txtFirstName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtAge = new JTextField(2);
        txtPosition = new JTextField(15);
        txtStatus = new JTextField(15);
        txtAddress = new JTextField(15);
        //Create dorpdown and populate them
        ddYears = new JComboBox<>(YEARS);
        ddYears.setSelectedIndex(-1);
        ddMonths = new JComboBox<>(MONTHS);
        ddMonths.setSelectedIndex(-1);
        ddDays = new JComboBox<>(DAYS);
        ddDays.setSelectedIndex(-1);
        //Create radio button
        rbSalaryEmp  = new JRadioButton("Salary",false);
        rbHourlyEmp = new JRadioButton("Hourly",false);
        rbCommEmp = new JRadioButton("Commission",false);
        //Add RadioButtonHandler to radio buttons
        rbSalaryEmp.addItemListener(new RadioButtonHandler("Salary"));
        rbHourlyEmp.addItemListener(new RadioButtonHandler("Hourly"));
        rbCommEmp.addItemListener(new RadioButtonHandler("Commission"));
        //Initliaze buttonGroup  
        bgEmpType = new ButtonGroup();
        //Add radio buttons to button group
        bgEmpType.add(rbCommEmp);
        bgEmpType.add(rbHourlyEmp);
        bgEmpType.add(rbSalaryEmp);                
        //Set border of the main panel
        mainPanel.setBorder(BorderFactory.createTitledBorder("Employee Information"));
        //Add components to mainPanel
        mainPanel.add(lblFirstName);
        mainPanel.add(txtFirstName);
        mainPanel.add(lblLastName);
        mainPanel.add(txtLastName);
        mainPanel.add(lblAge);
        mainPanel.add(txtAge);
        mainPanel.add(lblPosition);
        mainPanel.add(txtPosition);
        mainPanel.add(lblStatus);
        mainPanel.add(txtStatus);
        mainPanel.add(lblAddress);
        mainPanel.add(txtAddress);
        mainPanel.add(lblYear);
        mainPanel.add(ddYears);
        mainPanel.add(lblMonth);
        mainPanel.add(ddMonths);
        mainPanel.add(lblDay);
        mainPanel.add(ddDays);
        mainPanel.add(lblEmpType);
        mainPanel.add(rbSalaryEmp);
        mainPanel.add(rbHourlyEmp);
        mainPanel.add(rbCommEmp); 
        //Make mainPanel visible
        mainPanel.setVisible(true);
    }
    
    //Method to build south panel - Contains submit and clear buttons
    private void buildSouthPanel()
    {
        //Initialize southPanel
        southPanel = new JPanel();
        //Create buttons
        btnSubmit = new JButton("Submit");              
        btnClearAll = new JButton("Clear All");
        btnClearAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                clearAllFields();
            }
        });
        //Add action listener
        btnSubmit.addActionListener(new SubmitHandler());
        //Add components to southPanel
        southPanel.add(btnSubmit);
        southPanel.add(btnClearAll);
    }
    
    //Method to build commEmp panel - Contains additional information of a Commission Employee
    private void panelCommissionEmp()
    {
        //Initialize commEmp panel
        commEmp = new JPanel();
        //Set the layout of commEmp panel to grid layout
        commEmp.setLayout(new GridLayout(2,2));
        //Create label
        lblCommission = new JLabel("Commission:"); 
        lblGrossSales = new JLabel("Gross Sales:");
        //Create textboxes
        txtCommission = new JTextField(5);
        txtGrossSales = new JTextField(15);
        //Set border for commEmp panel
        commEmp.setBorder(BorderFactory.createTitledBorder("Commission Employee Information"));
        //Add components to commEmp panel
        commEmp.add(lblCommission);
        commEmp.add(txtCommission);
        commEmp.add(lblGrossSales);
        commEmp.add(txtGrossSales);
    }
    
    //Method to buil hourlyEmp panel - Contains additional information of an Hourly Employee
    private void panelHourlyEmp()
    {
        //Initialize hourlyEmp panel
        hourlyEmp = new JPanel();
        //Set layout for hourlyEmmp to grid layout
        hourlyEmp.setLayout(new GridLayout(2,2));
        //Create labels
        lblHourRate = new JLabel("Hourly Rate:"); 
        lblHours = new JLabel("Hours:"); 
        //Create textboxes
        txtHourRate = new JTextField(15);
        txtHours = new JTextField(15);
        //Set border for hourlyEmp panel
        hourlyEmp.setBorder(BorderFactory.createTitledBorder("Hourly Employee Information"));
        //Add components to hourlyEmp panel
        hourlyEmp.add(lblHourRate);
        hourlyEmp.add(txtHourRate);
        hourlyEmp.add(lblHours);
        hourlyEmp.add(txtHours);
    }
    
    //Method to buil salaryEmp panel - Contains additional information of a Salary Employee
    private void panelSalaryEmp()
    {
        //Initialize salaryEmp panel
        salaryEmp = new JPanel();
        //Set layout for salaryEmp to grid layout
        salaryEmp.setLayout(new GridLayout(1,2));
        //Create label
        lblSalary = new JLabel("Salary:");
        //Create textbox
        txtSalary = new JTextField(15);
        //Set border for salaryEmp panel
        salaryEmp.setBorder(BorderFactory.createTitledBorder("Salry Employee Info"));
        //Add components to salaryEmp panel
        salaryEmp.add(lblSalary);
        salaryEmp.add(txtSalary);       
    }
    
    //Method to get the text of the radio button slected
    public String getSelectedButtonText(ButtonGroup buttonGroup) 
    {
        //Loop through the radio buttons
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) 
        {
            //Get the next button
            AbstractButton button = buttons.nextElement();
            //Check if the button is selected
            if (button.isSelected()) 
            {
                return button.getText();//return the text of the radio button selected
            }
        }
        return null;//return null if there was no button selected
    }
    
    //Private inner class to handle radio buttons
    private class RadioButtonHandler implements ItemListener
    {
        private String type;
        public RadioButtonHandler(String type)
        {
            this.type = type;
        }
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            //Populate tempPanel with salaryEmp panel if Salry RadioButton is selected
            if("Salary".equals(type))
            {
                //Remove all components from tempPanel in case other type was previously selected
                tempPanel.removeAll();
                //Set the layout of tempPanel to border layout
                tempPanel.setLayout(new BorderLayout());
                //Revalidate tempPanel to show changes
                tempPanel.revalidate();
                //Build salaryEmp Panel
                panelSalaryEmp();
                //Add salaryEmp panel to tempPanel in position centre
                tempPanel.add(salaryEmp, BorderLayout.CENTER);
                //Build southPanel
                buildSouthPanel();
                //Add southPanel to tempPanel in position south
                tempPanel.add(southPanel, BorderLayout.SOUTH);
                //Make tempPanel visible
                tempPanel.setVisible(true);
            }
            //Populate tempPanel with hourlyEmp panel if Hourly RadioButton is selected
            else if("Hourly".equals(type))
            {
                //Remove all components from tempPanel in case other type was previously selected
                tempPanel.removeAll();
                //Set the layout of tempPanel to border layout
                tempPanel.setLayout(new BorderLayout());
                //Revalidate tempPanel to show changes
                tempPanel.revalidate();
                //Build hourlyEmp Panel
                panelHourlyEmp();
                //Add hourlyEmp panel to tempPanel in position centre
                tempPanel.add(hourlyEmp, BorderLayout.CENTER);
                //Build southPanel
                buildSouthPanel();
                //Add southPanel to tempPanel in position south
                tempPanel.add(southPanel, BorderLayout.SOUTH);
                 //Make tempPanel visible
                tempPanel.setVisible(true);
            }
            //Populate tempPanel with hourlyEmp panel if Hourly RadioButton is selected
            else if("Commission".equals(type))
            {
                //Remove all components from tempPanel in case other type was previously selected
                tempPanel.removeAll();
                //Set the layout of tempPanel to border layout
                tempPanel.setLayout(new BorderLayout());
                //Revalidate tempPanel to show changes
                tempPanel.revalidate();
                //Build commEmp Panel
                panelCommissionEmp();
                //Add commEmp panel to tempPanel in position centre
                tempPanel.add(commEmp, BorderLayout.CENTER);
                //Build southPanel
                buildSouthPanel();
                //Add southPanel to tempPanel in position south
                tempPanel.add(southPanel, BorderLayout.SOUTH);
                 //Make tempPanel visible
                tempPanel.setVisible(true);
            }
            //Make tempPanel not visible if no radio button is selected
            else
            {
                tempPanel.setVisible(false);
            }
        }
    }//end of RadioButtonHandler
    
    //Clear fields
    private void clearAllFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        txtPosition.setText("");
        ddYears.setSelectedIndex(-1);
        ddMonths.setSelectedIndex(-1);
        ddDays.setSelectedIndex(-1);
        txtStatus.setText("");
        txtAddress.setText("");
        String type = getSelectedButtonText(bgEmpType);
        switch (type)
        {
            case "Salary":
                txtSalary.setText("");
                break;
            case "Hourly":
                txtHourRate.setText("");
                txtHours.setText("");
                break;
            case "Commission":
                txtCommission.setText("");
                txtGrossSales.setText("");
                break;
            default:
                break;
        }
            
    }
    //Private inner class to handle submit button
    private class SubmitHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //Varaible that holds what type of employee they are submitting
            String type = getSelectedButtonText(bgEmpType);//call getSelectedButtonText method to see the type of employee user selected
            try
            {
                empValidation();
                //Handle which type of Employee user is submitting
                if ("Salary".equals(type))
                {
                    if(confirmSalaryEmpInformation())
                    {
                        //Make sure user enter information on Salary Employee information field
                        salaryEmpValidation();
                        new DBConnect().saveSalaryEmp(txtFirstName.getText(),
                                txtLastName.getText(),
                                Integer.parseInt(txtAge.getText()),
                                txtPosition.getText(),
                                txtStatus.getText(),
                                txtAddress.getText(),
                                Integer.parseInt(ddYears.getSelectedItem().toString()),
                                Integer.parseInt(ddMonths.getSelectedItem().toString()),
                                Integer.parseInt(ddDays.getSelectedItem().toString()),
                                Double.parseDouble(txtSalary.getText()));
                        //Show Employee was created
                        JOptionPane.showMessageDialog(null, "Created");
                        clearAllFields();
                    }
                }
                else if("Hourly".equals(type))
                {
                    if(confirmHourlyEmpInformation())
                    {
                        //Make sure user enter information on Hourly Employee information fields                 
                        hourlyEmpValidation();
                        new DBConnect().saveHourlyEmp(txtFirstName.getText(),
                                txtLastName.getText(),
                                Integer.parseInt(txtAge.getText()),
                                txtPosition.getText(),
                                txtStatus.getText(),
                                txtAddress.getText(),
                                Integer.parseInt(ddYears.getSelectedItem().toString()),
                                Integer.parseInt(ddMonths.getSelectedItem().toString()),
                                Integer.parseInt(ddDays.getSelectedItem().toString()),
                                Double.parseDouble(txtHourRate.getText()),
                                Double.parseDouble(txtHours.getText()));
                        JOptionPane.showMessageDialog(null, "Created");
                        clearAllFields();
                    }
                }
                else if("Commission".equals(type))
                {
                    if(confirmCommEmpInformation())
                    {
                        //Make sure user enter information on Commission Employee information fields
                        commEmpValidation();
                        new DBConnect().saveCommEmp(txtFirstName.getText(),
                                txtLastName.getText(),
                                Integer.parseInt(txtAge.getText()),
                                txtPosition.getText(),
                                txtStatus.getText(),
                                txtAddress.getText(),
                                Integer.parseInt(ddYears.getSelectedItem().toString()),
                                Integer.parseInt(ddMonths.getSelectedItem().toString()),
                                Integer.parseInt(ddDays.getSelectedItem().toString()),
                                Double.parseDouble(txtCommission.getText()),
                                Double.parseDouble(txtGrossSales.getText()));
                        //Show Employee was created
                        JOptionPane.showMessageDialog(null, "Created");
                        clearAllFields();
                    }
                }
            }
            catch(SQLException error)
            {
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                JOptionPane.showMessageDialog(null, error.getMessage(),"Database Error",JOptionPane.WARNING_MESSAGE);
            }
            catch(NumberFormatException  error)
            {
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                JOptionPane.showMessageDialog(null, error.getMessage(),"Invalid Input",JOptionPane.WARNING_MESSAGE);
            }
            
            catch(Exception error)
            {               
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                JOptionPane.showMessageDialog(null, error.getMessage(),"Invalid Input",JOptionPane.WARNING_MESSAGE);
            }
                        
        }
    }//End of SubmitHandler Class
    
    /**
     * 
     */
    private boolean empValidation() throws IllegalArgumentException {
        //Make sure user enter information on all the general Employee information fields
        if (txtFirstName.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: First Name");
        }
        if (txtLastName.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Last Name");
        }
        if (txtAge.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Age");
        }
        if(!tryParseInteger(txtAge.getText()) || !tryParseInteger(txtAge.getText())){
            throw new IllegalArgumentException("Invalid input. You need to provide a numeric argument for field: Age");       
        }
        if(Integer.parseInt(txtAge.getText()) < MIN_AGE || Integer.parseInt(txtAge.getText()) > MAX_AGE){
            throw new IllegalArgumentException(
            "Age: " + txtAge.getText() + " out of range. Must be in the range "+  MIN_AGE + " to " + MAX_AGE);       
        }   
        if (txtPosition.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Age");
        }
        if (txtStatus.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Staus");
        }
        if (txtAddress.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Address");
        }
        if(Integer.parseInt(ddYears.getSelectedItem().toString()) <= MIN_YEAR || Integer.parseInt(ddYears.getSelectedItem().toString()) > MAX_YEAR){
            throw new IllegalArgumentException(
            "Year: " + Integer.parseInt(ddYears.getSelectedItem().toString()) + " out of range. Must be in the range "+  MIN_YEAR + " to " + MAX_YEAR);       
        }
        if(Integer.parseInt(ddMonths.getSelectedItem().toString()) < MIN_MONTH || Integer.parseInt(ddMonths.getSelectedItem().toString()) > MAX_MONTH) {
            throw new IllegalArgumentException(
                "Month: " + Integer.parseInt(ddMonths.getSelectedItem().toString()) + " out of range. Must be in the range "+  MIN_MONTH + " to " + MAX_MONTH);
        }
        if(Integer.parseInt(ddDays.getSelectedItem().toString()) < MIN_DAYOFMONTH || Integer.parseInt(ddDays.getSelectedItem().toString()) > MAX_DAYOFMONTH) {
            throw new IllegalArgumentException(
                "Day: " + Integer.parseInt(ddDays.getSelectedItem().toString()) + " out of range. Must be in the range "+  MIN_DAYOFMONTH + " to " + MAX_DAYOFMONTH);
        }
        return true;
    }
    
    private boolean salaryEmpValidation() throws IllegalArgumentException 
    {
        if (txtSalary.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Salary");
        }
        if (!tryParseDouble(txtSalary.getText())) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Salary");
        }
        if(Double.parseDouble(txtSalary.getText()) < MIN_WEEK_SALARY )
        {
            throw new IllegalArgumentException(
                "Salary: " + txtSalary.getText() + " out of range. Must greater or equal than "+  MIN_WEEK_SALARY );
        }
        return true;
    }
    
    private boolean hourlyEmpValidation() throws IllegalArgumentException 
    {
        if (txtHourRate.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Hourly Rate");
        }
        if (!tryParseDouble(txtHourRate.getText())) {
            throw new IllegalArgumentException("Invalid input. You need to provide a numeric value for field: Hourly Rate");
        }
        if(Double.parseDouble(txtHourRate.getText()) < MIN_PAY_RATE){
            throw new IllegalArgumentException(
                "Hourly Rate: " + txtHourRate.getText() + " out of range. Must greater than "+  MIN_PAY_RATE );
        }
        if (txtHours.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Hours");
        }
        if (!tryParseDouble(txtHours.getText())) {
            throw new IllegalArgumentException("Invalid input. You need to provide a numeric value for field: Hourls");
        }
        if(Double.parseDouble(txtHours.getText()) < MIN_HOURS || Double.parseDouble(txtHours.getText()) > MAX_HOURS_WEEK){
            throw new IllegalArgumentException(
                "Hours of work: " + txtHours.getText() + " out of range. Must in the greater than: "+  MIN_HOURS + " and less than " + MAX_HOURS_WEEK);
        }
        return true;
    }
    
    private boolean commEmpValidation() throws IllegalArgumentException 
    {
        if (txtCommission.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Commission Rate");
        }
        if (!tryParseDouble(txtCommission.getText())) {
            throw new IllegalArgumentException("Invalid input. You need to provide a numeric value for field: Commission Rate");
        }
        if(Double.parseDouble(txtCommission.getText()) <= MIN_COMMISSION_RATE || Double.parseDouble(txtCommission.getText()) >= MAX_COMMISSION_RATE)
        {
            throw new IllegalArgumentException(
                "Commission Rate: " + txtCommission.getText() + " out of range. Must be in the greater than "+  MIN_COMMISSION_RATE + " and less or equal than " + MAX_COMMISSION_RATE );
        }
        if (txtGrossSales.getText().trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Gross Sales");
        }
        if (!tryParseDouble(txtGrossSales.getText())) {
            throw new IllegalArgumentException("Invalid input. You need to provide a numeric value for field: Gross Sales");
        }
        if(Double.parseDouble(txtGrossSales.getText()) < MIN_GROSS_SALES )
        {
            throw new IllegalArgumentException(
                "Gross Sales: " + txtGrossSales.getText() + " out of range. Must greater or equal than "+  MIN_GROSS_SALES );
        }
        return true;
    }
    
    private boolean tryParseDouble(String value)  
    {
        try{           
            Double.parseDouble(value);
            return true;
        }
        catch(NumberFormatException error){
            return false;
        }    
    }
    private boolean tryParseInteger(String value)  
    {
        try{           
            Integer.parseInt(value);
            return true;
        }
        catch(NumberFormatException error){
            return false;
        }    
    }
    
    private boolean confirmSalaryEmpInformation()
    {
        //Confirmation of creat a new object
        int response = JOptionPane.showConfirmDialog(null, "Is the information correct?\n"
                + "\nFirst Name: " + txtFirstName.getText()
                + "\nLast Name: " + txtLastName.getText()
                + "\nAge: " + txtAge.getText()
                + "\nPosition: " + txtPosition.getText()
                + "\nStatus: " + txtStatus.getText()
                + "\nAddress: " + txtAddress.getText()
                + "\nYear hired: " + ddYears.getSelectedItem().toString()
                + "\nMonth hired: " + ddMonths.getSelectedItem().toString()
                + "\nDay hired: " + ddDays.getSelectedItem().toString()
                + "\nSalary: " + txtSalary.getText(), "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //If yes create object
        return response == JOptionPane.YES_OPTION;
    }
    
    private boolean confirmHourlyEmpInformation()
    {
        //Confirmation of creat a new object
        int response = JOptionPane.showConfirmDialog(null, "Is the information correct?\n"
                + "\nFirst Name: " + txtFirstName.getText()
                + "\nLast Name: " + txtLastName.getText()
                + "\nAge: " + txtAge.getText()
                + "\nPosition: " + txtPosition.getText()
                + "\nStatus: " + txtStatus.getText()
                + "\nAddress: " + txtAddress.getText()
                + "\nYear hired: " + ddYears.getSelectedItem().toString()
                + "\nMonth hired: " + ddMonths.getSelectedItem().toString()
                + "\nDay hired: " + ddDays.getSelectedItem().toString()
                + "\nHourly Rate: " + txtHourRate.getText()
                + "\nHours: " + txtHours.getText(), "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //If yes create object
        return response == JOptionPane.YES_OPTION;
    }
    
    private boolean confirmCommEmpInformation()
    {
        //Confirmation of creat a new object
        int response = JOptionPane.showConfirmDialog(null, "Is the information correct?\n"
                + "\nFirst Name: " + txtFirstName.getText()
                + "\nLast Name: " + txtLastName.getText()
                + "\nAge: " + txtAge.getText()
                + "\nPosition: " + txtPosition.getText()
                + "\nStatus: " + txtStatus.getText()
                + "\nAddress: " + txtAddress.getText()
                + "\nYear hired: " + ddYears.getSelectedItem().toString()
                + "\nMonth hired: " + ddMonths.getSelectedItem().toString()
                + "\nDay hired: " + ddDays.getSelectedItem().toString()
                + "\nCommission: " + txtCommission.getText()
                + "\nGross Sales: " + txtGrossSales.getText(), "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //If yes create object
        return response == JOptionPane.YES_OPTION;
    }
}
