package gui;
import general.DBConnect;
import general.ErrorDataLog;
import general.ServiceClass;
import hr.Employee;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.border.EmptyBorder;
import product.Product;
/**
 * Simple HR System - SearchPanel Class
 * This class builds the Search panel, to be implemented in BuildMainGUI class. .
 * @author Cindy Diaz
 * @version 2.0
 */
public class SearchPanel extends JPanel
{
    //Declare JLabels
    private JLabel lblSearch, lblResult, lblEmployee, lblProducts;
    //Declare JTextFields
    private JTextField txtSearch;
    //Declare JTextArea
    private JTextArea txtAreaResult;
    //Declare JButtons
    private JButton btnSearch,btnSearchProducts,btnSearchEmployee, btnRefresh;
    //Declare JComboBoxes
    //private JComboBox ddProducts,ddEmployees;
    private JComboBox<Employee> ddEmployees;
    private JComboBox<Product> ddProducts;
    private DefaultComboBoxModel empModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel productModel = new DefaultComboBoxModel();
    //Declare JPanels
    private JPanel mainPanel, resultPanel, buttonsPanel, informationPanel;
    
    private ErrorDataLog errorLog;
    
    public SearchPanel()
    {
        errorLog = new ErrorDataLog();
        //Set layout to be border layout
        setLayout(new BorderLayout());
        //Set the for this panel to EmptyBorder with padding
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        //Build the mainPanel - Search options
        buildMainPanel();
        //Add the mainPanel to SearchPanel in position north
        add(mainPanel, BorderLayout.NORTH);
        //Build resultPanel
        buildResultPanel();
        //Add resultPanel to SearchPanel in position centre
        add(resultPanel, BorderLayout.CENTER);
    }
    
    //Method to build mainPanel - Containsa Search Options
    private void buildMainPanel()
    {   
        //Initialize mainPanel
        mainPanel =  new JPanel();
        //Set layout of mainPanel to grid layout 
        mainPanel.setLayout(new GridLayout(5,2));
        //Labels
        lblEmployee = new JLabel("Search employee:");
        ddEmployees = new JComboBox();
        
        ddEmployees.addActionListener(new EmpDropBoxHandler());
        lblProducts = new JLabel("Search Product:");
        ddProducts = new JComboBox();
        ddProducts.addActionListener(new ProductDropBoxHandler());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Search employee or product"));
        mainPanel.add(lblEmployee);
        mainPanel.add(ddEmployees);
        mainPanel.add(lblProducts);
        mainPanel.add(ddProducts);
        updateDDEmpList();
        updateDDProductList();
    }
    
    //Method to build ressultPanel
    private void buildResultPanel()
    {
        // Create the information panel
        informationPanel = new JPanel();
        informationPanel.setLayout(new GridLayout(1,1));
        //Create TextArea
        txtAreaResult = new JTextArea(10,15);
        //Set TextArea to be read-only
        txtAreaResult.setEditable(false);
        //Set TextArea to wrap lines
        txtAreaResult.setLineWrap(true);
        informationPanel.add(new JScrollPane(txtAreaResult));
        
        //Create panel that contains refresh button
        buttonsPanel = new JPanel();       
        buttonsPanel.setLayout(new FlowLayout());
        btnRefresh = new JButton("Refresh List");
        btnRefresh.addActionListener(new RefreshButtonHandler());
        buttonsPanel.add(btnRefresh);

        //Initialize resultPanel
        resultPanel = new JPanel();
        //Set layout of resultPanel to grid layout
        resultPanel.setLayout(new BorderLayout());
        //Add panels to resultPanel       
        resultPanel.add(buttonsPanel, BorderLayout.NORTH);
        resultPanel.add(informationPanel, BorderLayout.CENTER);
    }
    private void updateDDEmpList()
    {
        try
        {
            empModel.removeAllElements();
            new DBConnect().populateEmployeeArraylist();
            for(Employee emp: ServiceClass.listEmployees)
            {
                empModel.addElement(emp);
            }
            ddEmployees.setModel(empModel);
        }
        catch(SQLException error)
        {
            DBConnect.saveErrorLogDB(error);
            errorLog.appendData(error);
        }
        catch(Exception error)
        {
            DBConnect.saveErrorLogDB(error);
            errorLog.appendData(error);
        }
    }
    
    private void updateDDProductList()
    {
        try{
            //reset the model
            productModel.removeAllElements();
            
            ServiceClass.listProducts.clear();
            new DBConnect().populateProductsArray();
            //store the data into the model
            for(Product product : ServiceClass.listProducts){
                productModel.addElement(product);
            }           
            //set combobox to the model
            ddProducts.setModel(productModel);
        }
        catch(SQLException error){
            DBConnect.saveErrorLogDB(error);
            errorLog.appendData(error);
            //error.printStackTrace();
        }
        catch(Exception error){
            errorLog.appendData(error);
            DBConnect.saveErrorLogDB(error);
            //error.printStackTrace();
        }
    }
    
    private class EmpDropBoxHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(ddEmployees.getSelectedIndex() >= 0){
                Employee selectedEmp = (Employee) ddEmployees.getSelectedItem();
                txtAreaResult.setText("****************** Employee Information *******************\n" + selectedEmp.getEmpInformation());    
            }
        }
    }
    private class ProductDropBoxHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(ddProducts.getSelectedIndex() >= 0){
            Product selectedProduct = (Product) ddProducts.getSelectedItem();
            txtAreaResult.setText("********* Product Information **********\n" + selectedProduct.getProductInfo());
            }
        }
    }
    //Private inner class to habdle search button
    private class RefreshButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                updateDDEmpList();
                updateDDProductList();
                
            }catch (Exception error)
            {
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                error.printStackTrace();
            }
        }
    }
}
