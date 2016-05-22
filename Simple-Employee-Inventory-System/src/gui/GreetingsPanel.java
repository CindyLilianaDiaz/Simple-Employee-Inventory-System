package gui;
import java.awt.*;
import javax.swing.*;
/**
 * Simple HR System - GreetingsPanel Class
 * This class builds the Greetings panel, to be implemented in BuildMainGUI class. .
 * @author Cindy Diaz
 * @version 2.0
 */
public class GreetingsPanel extends JPanel
{
    //Declare JLabel
    private JLabel lblGreeting;
    
    public GreetingsPanel()
    {
        //Declare and initialize an Icon object with my logo
        Icon logo = new ImageIcon(("images/logo2.jpg"));
        //Create JLabel - constructor with logo 
        lblGreeting = new JLabel("",logo,SwingConstants.CENTER);
        //Set font of label (Currently there is no text)
        lblGreeting.setFont(new Font("Arial", Font.BOLD,24));
        //Add component to GreetingsPanel
        add(lblGreeting);
        //Set the border (Currently no effect since we use only an image)
        setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
