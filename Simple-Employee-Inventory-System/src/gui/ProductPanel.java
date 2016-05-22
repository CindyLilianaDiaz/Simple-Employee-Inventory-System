package gui;
import general.DBConnect;
import general.ErrorDataLog;
import general.ServiceClass;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.InputMismatchException;
import javax.swing.border.EmptyBorder;
import manufacturers.Manufacturer;
import product.Product;
/**
 * Simple HR System - ProductPanel Class
 * This class builds the Products panel, to be implemented in BuildMainGUI class. .
 * @author Cindy Diaz
 * @version 2.0
 */
public class ProductPanel extends JPanel{
    //Declare JLabels for Manufacturer Information
    private JLabel lblManufacturerName, lblManufacturerContact, lblManufacturerPhone, lblManufacturerAddress, lblSelectManufacturer;
    //Declare JTextFields for Manufacturer Information
    private JTextField txtManufacturerName, txtManufacturerContact, txtManufacturerPhone,txtManufacturerAddress;
    private JComboBox<Manufacturer> ddSelectManufacturer;
    //Declare JLabels for Products Information
    private JLabel lblProductName, lblProductCategory,lblProductDescription,lblProductPartNumber,lblProductCost,lblProductPrice,lblProductMinInventory;
    //Declare JTextFields for Products Information
    private JTextField txtProductName, txtProductCategory,txtProductDescription, txtProductPartNumber,txtProductCost, txtProductPrice, txtProductMinInventory;
    //Declare JButtons
    private JButton btnSubmit, btnClearAll;
    //Declare JPanels
    private JPanel mainPanel, southPanel;
    
    private final ErrorDataLog errorLog;
    private StringWriter exceptionData;

    
    public ProductPanel()
    {
        errorLog = new ErrorDataLog();
        //Set layout to be border layout
        setLayout(new BorderLayout(10,10));
        //Set the for this panel to EmptyBorder with padding
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        //Build the mainPanel - Product and Manufacturer information
        buildMainPanel();
        //Add the mainPanel to ProductPanel in position centre
        add(mainPanel, BorderLayout.CENTER);
        //Build southPanel
        buildSouthPanel();
        //Add the southPanel to ProductPanel in position south
        add(southPanel, BorderLayout.SOUTH);
        
        //errLog = new ErrorDataLog();
        exceptionData = new StringWriter();

    }
    
    //Method to buidl mainPanel - Contains Product and Manufacturer information
    private void buildMainPanel()
    {
        //Initialize mainPanel
        mainPanel = new JPanel();
        //Set layout of mainPanel to grid layout
        mainPanel.setLayout(new GridLayout(12,2));       
        //Create Labels for Manufacturer information
        lblSelectManufacturer = new JLabel("SelectManufacturer:");
        lblManufacturerName = new JLabel("Manufacturer Name:");
        lblManufacturerContact = new JLabel("Manufacturer Contact:"); 
        lblManufacturerPhone = new JLabel("Manufacturer Phone:");
        lblManufacturerAddress = new JLabel("Manufacturer Address:");
        //Create Labels for Product information
        lblProductName = new JLabel("Product Name:");
        lblProductCategory = new JLabel("Product Category:");
        lblProductDescription = new JLabel("Product Description:");
        lblProductPartNumber = new JLabel("Product Part Number:");
        lblProductCost = new JLabel("ProductCost:");
        lblProductPrice = new JLabel("ProductPrice:");
        lblProductMinInventory = new JLabel("Min Inventory:");       
        //Create dropdown for Manufacturers and populate it with available manufacturers from the ArrayList
        new DBConnect().populateManufacturerArray();
        ddSelectManufacturer = new JComboBox(ServiceClass.listManufacturer.toArray());
        ddSelectManufacturer.setSelectedIndex(-1);
        //Add ItemListener for the dropdown
        ddSelectManufacturer.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event){
                //Populate Manufacturer information with the manufacturer selected
                if(event.getStateChange() == ItemEvent.SELECTED)
                {
                    txtManufacturerName.setText(ServiceClass.listManufacturer.get(ddSelectManufacturer.getSelectedIndex()).getManufacturerName());
                    txtManufacturerContact.setText(ServiceClass.listManufacturer.get(ddSelectManufacturer.getSelectedIndex()).getContact());
                    txtManufacturerPhone.setText(ServiceClass.listManufacturer.get(ddSelectManufacturer.getSelectedIndex()).getPhoneNumber());
                    txtManufacturerAddress.setText(ServiceClass.listManufacturer.get(ddSelectManufacturer.getSelectedIndex()).getAddress());
                }
            }
        
        });
        //Create Text Boxes for Manufacturer information
        txtManufacturerName = new JTextField(15);
        txtManufacturerContact = new JTextField(15);
        txtManufacturerPhone = new JTextField(15);
        txtManufacturerAddress = new JTextField(15);
        //Create Text Boxes for Product information
        txtProductName = new JTextField(15);
        txtProductCategory = new JTextField(15);
        txtProductDescription = new JTextField(15);
        txtProductPartNumber = new JTextField(15);
        txtProductCost = new JTextField(15);
        txtProductPrice = new JTextField(15);
        txtProductMinInventory = new JTextField(15);
        //Set the border form mainPanel
        mainPanel.setBorder(BorderFactory.createTitledBorder("Product Information"));
        //Add Manufacturer components to mainPanel
        mainPanel.add(lblSelectManufacturer);
        mainPanel.add(ddSelectManufacturer);
        mainPanel.add(lblManufacturerName);
        mainPanel.add(txtManufacturerName);
        mainPanel.add(lblManufacturerContact);
        mainPanel.add(txtManufacturerContact);
        mainPanel.add(lblManufacturerPhone);
        mainPanel.add(txtManufacturerPhone);
        mainPanel.add(lblManufacturerAddress);
        mainPanel.add(txtManufacturerAddress);
        //Add Product components to mainPanel
        mainPanel.add(lblProductName);
        mainPanel.add(txtProductName);
        mainPanel.add(lblProductCategory);
        mainPanel.add(txtProductCategory);
        mainPanel.add(lblProductDescription);
        mainPanel.add(txtProductDescription);
        mainPanel.add(lblProductPartNumber);
        mainPanel.add(txtProductPartNumber);
        mainPanel.add(lblProductCost);
        mainPanel.add(txtProductCost);
        mainPanel.add(lblProductPrice);
        mainPanel.add(txtProductPrice);
        mainPanel.add(lblProductMinInventory);
        mainPanel.add(txtProductMinInventory);
    }
    
    //Method tobuild southPanel - Contains submit and clear buttons
    private void buildSouthPanel()
    {
        //Initialize southPanel
        southPanel = new JPanel();
        //Create buttons
        btnSubmit = new JButton("Submit");
        btnClearAll = new JButton("Clear All");
        //Add action listener
        btnSubmit.addActionListener(new SubmitHandler());
        btnClearAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                clearAllFields();
            }
        });
        //Add components to southPanel
        southPanel.add(btnSubmit);
        southPanel.add(btnClearAll);
    }
    
    private void clearAllFields(){
        txtManufacturerName.setText("");
                txtManufacturerContact.setText("");
                txtManufacturerPhone.setText("");
                txtManufacturerAddress.setText("");
                ddSelectManufacturer.setSelectedIndex(-1);
                txtProductName.setText("");
                txtProductCategory.setText("");
                txtProductDescription.setText("");
                txtProductPartNumber.setText("");
                txtProductCost.setText("");
                txtProductPrice.setText("");
                txtProductMinInventory.setText("");
    }
    
    //Private inner class to handle submit button
    private class SubmitHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        { 
            try
            {
                //Make sure user enter information on all fields
                if(txtManufacturerName.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Manufacturer Name");
                }
                if(txtManufacturerContact.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Manufacturer Contact");
                }
                if(txtManufacturerPhone.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Manufacturer Phone");
                }
                if(txtManufacturerAddress.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Manufacturer Address");
                }
                if(txtProductName.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Product Name");
                }
                if(txtProductCategory.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Product Category");
                }
                if(txtProductDescription.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Product Description");
                }
                if(txtProductPartNumber.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Product Part Number");
                }
                if(txtProductCost.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Product Cost");
                }
                if(txtProductPrice.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Product Price");
                }
                if(txtProductMinInventory.getText().trim().length() == 0){
                    throw new IllegalArgumentException("Invalid input. You need to provide an argument for field: Min Inventory");
                }
                if(!tryParse(txtProductCost.getText())){
                    throw new InputMismatchException("Invalid input. Field Product Cost must be numeric value");
                } 
                if(!tryParse(txtProductPrice.getText())){
                    throw new InputMismatchException("Invalid input. Field Product Price must be numeric value");
                } 
                if(!tryParse(txtProductMinInventory.getText())){
                    throw new InputMismatchException("Invalid input. Field Minimmum Inventory must be numeric value");
                } 
                if(!checkIfPriceGreaterThanCost(Double.parseDouble(txtProductPrice.getText()), Double.parseDouble(txtProductCost.getText()))){
                    throw new IllegalArgumentException("Invalid input. Price should be greater than Cost");
                }
                //Confirmation of creat a new object
                int response = JOptionPane.showConfirmDialog(null, "Is the information correct?\n"
                                    + "\nManufacturer Name: " + txtManufacturerName.getText()
                                    + "\nManufacturer Contact: " + txtManufacturerContact.getText()
                                    + "\nManufacturer Phone: " + txtManufacturerPhone.getText()
                                    + "\nManufacturer Address: " + txtManufacturerAddress.getText()
                                    + "\nProduct Name: " + txtProductName.getText()   
                                    + "\nProduct Category: " + txtProductCategory.getText() 
                                    + "\nProduct Description: " + txtProductDescription.getText() 
                                    + "\nProduct Part Number: " + txtProductPartNumber.getText() 
                                    + "\nProduct Cost: " + txtProductCost.getText() 
                                    + "\nProduct Price: " + txtProductPrice.getText() 
                                    + "\nMin Inventory: " + txtProductMinInventory.getText() 
                                    , "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                //If yes create object
                if (response == JOptionPane.YES_OPTION) 
                {
                      //Create a manufacturer object
                      Manufacturer tempMan = new Manufacturer(txtManufacturerName.getText(),
                                                              txtManufacturerAddress.getText(),
                                                              txtManufacturerPhone.getText(),
                                                              txtManufacturerContact.getText());
                    //If manufacturer entered doesn't exist in our records
                    if(!ServiceClass.listManufacturer.contains(tempMan))
                    {
                        //Call method to insert manufacturer to db, check if it was succesfull
                        if(new DBConnect().insertManufacturerDB(tempMan.getManufacturerName(),tempMan.getAddress() ,tempMan.getPhoneNumber() , tempMan.getContact())){
                            System.out.println("Added Manufacturer to DB");
                            //Add the manufacturer object to ArrayList of manufacturers
                            ServiceClass.listManufacturer.add(tempMan);
                            //Add maunfacturer to dropdown
                            ddSelectManufacturer.addItem(tempMan);
                            //Create and add Product object to ArrayLIst of products
                            Product prod = new Product(txtProductName.getText(),
                                        txtProductCategory.getText(),
                                        tempMan,
                                        txtProductDescription.getText(),
                                        txtProductPartNumber.getText(),
                                        Double.parseDouble(txtProductCost.getText()),
                                        Double.parseDouble(txtProductPrice.getText()),
                                        Integer.parseInt(txtProductMinInventory.getText())
                            );
                            //Retrieve the id generated by the database of the manufacturer just added
                            int value = new DBConnect().manufacturerIdFromDB(tempMan);
                            //Cal method to insert product to database, check if it was successfully added
                            if(new DBConnect().inserProductDB(prod.getProductName(), prod.getCategory(), value, prod.getDescription(),
                                                          prod.getPartNum(), prod.getProductCost(), prod.getProductPrice(), prod.getProductMarkup(), prod.getMinimumInventory()))
                            {
                                System.out.println("Product Data Inserted");
                                //If added to db add it to our temporary list of products
                                ServiceClass.listProducts.add(prod);
                                //Show message succesfuly added
                                JOptionPane.showMessageDialog(null, "Product and Manufacturer Added","Success",JOptionPane.INFORMATION_MESSAGE);
                                clearAllFields();
                            }
                        }
                    //If manufacturer does exist in our records 
                    }else if(ServiceClass.listManufacturer.contains(tempMan))
                    {
                          System.out.println(ServiceClass.listManufacturer);
                          //Create and add Product object to ArrayLIst of products
                            Product prod = new Product(txtProductName.getText(),
                                        txtProductCategory.getText(),
                                        tempMan,
                                        txtProductDescription.getText(),
                                        txtProductPartNumber.getText(),
                                        Double.parseDouble(txtProductCost.getText()),
                                        Double.parseDouble(txtProductPrice.getText()),
                                        Integer.parseInt(txtProductMinInventory.getText())
                            );
                            //Call method to retrieve the id of the manufacturer selected
                            int value = new DBConnect().manufacturerIdFromDB(tempMan);
                            //Call method to insert Product object, check if it was succesfull
                            if(new DBConnect().inserProductDB(prod.getProductName(), prod.getCategory(), value, prod.getDescription(),
                                                          prod.getPartNum(), prod.getProductCost(), prod.getProductPrice(), prod.getProductMarkup(), prod.getMinimumInventory()))
                            {
                                System.out.println("Product Data Inserted");
                                //If added to db add it to our temporary list of products
                                ServiceClass.listProducts.add(prod); 
                                //Show message succesfuly added
                                JOptionPane.showMessageDialog(null, "Product and Manufacturer Added","Success",JOptionPane.INFORMATION_MESSAGE);
                                clearAllFields();
                            }       
                    }
                    //Testing purposes - See what data is in our arraylists
                    System.out.println(ServiceClass.listManufacturer);
                    System.out.println(ServiceClass.listProducts);
                }                
            }catch(IllegalArgumentException | InputMismatchException error){
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                JOptionPane.showMessageDialog(null, error.getMessage(),"Invalid Input",JOptionPane.WARNING_MESSAGE);
            }
            catch(SQLException error){
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                JOptionPane.showMessageDialog(null, "Product/Manufacturer could not be created","Error",JOptionPane.WARNING_MESSAGE);
            }
            catch(Exception error){
                DBConnect.saveErrorLogDB(error);
                errorLog.appendData(error);
                JOptionPane.showMessageDialog(null, "There was a proble processing information","Error",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    //Method to check if value is numeric
    private boolean tryParse(String value) 
    {
        try{
        Double.parseDouble(value);
            return true;
        }catch(Exception error){
            DBConnect.saveErrorLogDB(error);
            errorLog.appendData(error);
            return false;
        }      
    }
    //Method to check if price is greater than cost
    private boolean checkIfPriceGreaterThanCost(double price, double cost)
    {
        return (price > cost);
    }
}
