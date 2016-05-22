package general;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.GregorianCalendar;
/**
 * Simple HR System - ErrorDataLog Class
 * This class creates an error log file, it also implements methods to append errors.
 * @author Cindy Diaz
 * @version 2.0
 */
public class ErrorDataLog 
{
    private void checkDirectory()
    {
        try
        {
            File directory = new File("c:/ErrorLog");//path 
            //check if directory exists
            if(!directory.exists())
            {
                //create directory if it  doesnt
                if(directory.mkdir())
                {
                    System.out.println("Directory created");
                }else
                {
                    System.out.println("Fali to create directory");
                }
            }
            
        }catch(SecurityException error)
        {
            error.printStackTrace();
        }catch(Exception error)
        {
            error.printStackTrace();            
        }
    }
    public String readDataBR()
    {
        BufferedReader br = null;
        try
        {
            String errorList = "";
            String currentLine;
            br = new BufferedReader(new FileReader("c:/ErrorLog/Logs.txt"));
            currentLine = br.readLine();
            
            while(currentLine != null){
                errorList += currentLine + "\n";
                //System.out.println(currentLine);
                currentLine = br.readLine();
            }
            br.close();
            return errorList;
        }catch(FileNotFoundException error)
        {
            DBConnect.saveErrorLogDB(error);
            return "No ErrorLog file found in this computer.";
        }
        catch(IOException error)
        {
            DBConnect.saveErrorLogDB(error);
            //error.printStackTrace();
            return null;
        }catch(Exception error)
        {
            DBConnect.saveErrorLogDB(error);
            //error.printStackTrace();
            return null;
        }
    }
    
    public void appendData(Exception error)
    {
        StringWriter errorsData = new StringWriter();
        error.printStackTrace(new PrintWriter(errorsData));
        //check directory 
        checkDirectory();
        
        try{
            File file = new File("c:/ErrorLog/Logs.txt");
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            //create file writer
            FileWriter fWriter = new FileWriter(file,true);
            BufferedWriter  bWriter = new BufferedWriter(fWriter);
            bWriter.write("***********ERROR LOG START********" + 
                            System.getProperty("line.separator")+
                            new GregorianCalendar().getTime().toString() + 
                            System.getProperty("line.separator") + errorsData.toString() +
                            "***********ERROR LOG END********\n");
            bWriter.newLine();
            bWriter.close();
        }
        catch(IOException IOerror)
        {
            IOerror.printStackTrace();
        }catch(Exception IOerror)
        {
            IOerror.printStackTrace();
        }
    }    
}

