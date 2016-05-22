package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Simple HR System - BuildMainGUI Class
 * This class builds up the main GUI of the application .
 * @author Cindy Diaz
 * @version 2.0
 */
public class BuildMainGUI extends JFrame
{
    //Declare Jtab
    private JTabbedPane tabPaneMain;
    //Declare Jbutton
    private JButton btnExit;
    //Declare JPanels
    private JPanel  northPanel, southPanel;
    
    //Constructor to buil GUI
    public BuildMainGUI()
    {
        //Call contructor to set title for frame
        super("HR Software");
        //Set a border layout for frame
        setLayout(new BorderLayout(10,10));
        //Create tab interface
        tabPaneMain = new JTabbedPane();        
        //Add Employee tab, Product Tab, Search Tab
        tabPaneMain.addTab("Employee", null, new EmployeePanel());
        tabPaneMain.addTab("Product", null, new ProductPanel());
        tabPaneMain.addTab("Search", null, new SearchPanel());   
        tabPaneMain.addTab("Error Log", null, new ErrorLogPanel()); 
        //Create and add north panel to frame in north position - Greetings Panel
        northPanel = new GreetingsPanel();
        add(northPanel,BorderLayout.NORTH);
        //Add the tabbed interface to frame in center position
        add(tabPaneMain, BorderLayout.CENTER);
        //Build south panel - Exit button
        buildSouthPanel();
        //Add the south panel to frame in south position
        add(southPanel, BorderLayout.SOUTH);
        //Set up window features
        windowFeatures();
    }
   
    //Method to build south panel which contains exit button
    private void buildSouthPanel()
    {
        //Initialize south panel
        southPanel = new JPanel();
        //Initialize exit button
        btnExit = new JButton("Exit");
        //Add action listener to exit button
        btnExit.addActionListener(new ActionListener(){//Anonymous inner class
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);//Close application
            }
        });
        //Add button to south panel
        southPanel.add(btnExit);        
    }
    //Method to set up windows features
    private void windowFeatures()
    {
        //Pack the content
        pack();
        //Set the size
        setSize(500,750);
        //Make it no resizable
        setResizable(false);
        //Make start location centre
        setLocationRelativeTo(null);
        //Set close operations
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Make frame visible
        setVisible(true);
    }
}
