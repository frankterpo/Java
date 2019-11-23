import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.awt.*;

public class ReadRSS extends JFrame {
    public static ArrayList<String> news = new ArrayList<String>();
   
    public static void main(String[] args){
        System.out.println("----------------------------");
        //update();
        //BuildGUI();
        
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //try - catch block
                try 
                {
                    //Create object of Inventory
                    ReadRSS frame = new ReadRSS();
                    //set frame visible true
                    frame.setVisible(true);                 
                }
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void update(){
        String urlAddress = "http://feeds.reuters.com/reuters/businessNews";
        try{
            URL rssUrl = new URL (urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String line;
            while((line=in.readLine())!=null){
                if(line.contains("<title>")){
                    //System.out.println(line);
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp=temp.replace("<title>","");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0,lastPos);
                    news.add(temp);
                }
            }
            in.close();
        } catch (MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe){
            System.out.println("Something went wrong reading the contents");
        }
        System.out.println("Update Done");
    }
    
    public static void BuildGUI(){
        JFrame guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Market News");
        guiFrame.setSize(600, 250);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        //Create a two dimensional array to hold the data for the JTable.
        Object[][] data = {};
        
        //A string array containing the column names for the JTable.
        String[] columnNames = {"Latest News"};
        
        //Create a DeafultTableModel object for another JTable
        DefaultTableModel defTableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(defTableModel);
        
        //Create a JScrollPane to contain for the JTable
        JScrollPane anotherSP = new JScrollPane(table);
        
        for(int i = 0; i < news.size(); i++){
            if(i > 1){ //checks if it is getting he news and not the Reuters name
                //get ticker and price from stock array
                String newsTitle = news.get(i);
                
                //add ticker and price to newData object
                Object[] newData = {newsTitle};
                
                //add newData object to new row of the table
                defTableModel.addRow(newData);
            }
        }
        
        guiFrame.add(anotherSP);
        
        guiFrame.setVisible(true);
        
        System.out.println("GUI built");
    }
    
    public ReadRSS() throws IOException{
        //set frame title
        
        update();
        BuildGUI();
    }   
}
