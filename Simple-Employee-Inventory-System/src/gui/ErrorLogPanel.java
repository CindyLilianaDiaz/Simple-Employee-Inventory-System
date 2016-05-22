package gui;
import general.ErrorDataLog;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * Simple HR System - ErrorLogPanel Class
 * This class builds the Error Log panel, to be implemented in BuildMainGUI class. .
 * @author Cindy Diaz
 * @version 2.0
 */
public class ErrorLogPanel extends JPanel
{
    //JLabels
    private JLabel lblErrors;
    //Text Are
    private JTextArea txtAreaErrors;
    //Buttons
    private JButton btnRefresh;
    //Panels
    private JPanel mainPanel, northPanel, southPanel;
    private ErrorDataLog errorLog;
    
    public ErrorLogPanel()
    {
        errorLog = new ErrorDataLog();
        setLayout(new BorderLayout(10,10));
        //Set the for this panel to EmptyBorder with padding
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        buildMainPanel();
        add(mainPanel, BorderLayout.CENTER);
        buildSouthPanel();
        add(southPanel, BorderLayout.SOUTH);
    }
    private void buildMainPanel()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,1));
        txtAreaErrors = new JTextArea();
        txtAreaErrors.setEditable(false);
        txtAreaErrors.setLineWrap(true);
        txtAreaErrors.setText(errorLog.readDataBR());
        mainPanel.add(new JScrollPane(txtAreaErrors));
    }
    private void buildSouthPanel()
    {
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        btnRefresh = new JButton("Refresh Error Log");
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                refreshTextAreaErrorLog();
            }
        });
        southPanel.add(btnRefresh);
    }
    private void refreshTextAreaErrorLog()
    {
        txtAreaErrors.setText(errorLog.readDataBR());
    }
    
}
