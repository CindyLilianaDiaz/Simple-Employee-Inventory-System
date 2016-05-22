package general;
import gui.BuildMainGUI;
/**
 * Simple HR System - GUIMain Class
 * This is the tester class for the GUI version of the project.
 * @author Cindy Diaz
 * @version 2.0
 */
public class GUIMain 
{
    public static void main(String[] args) {
        //Create a new BuildMainGUI object
        BuildMainGUI main = new BuildMainGUI();
        //Make main visible
        main.setVisible(true);
    }
}
