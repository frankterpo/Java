import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
// replace import org.json.simple.*; with;
import org.json.*; // download this: "json-20171018.jar" from http://central.maven.org/maven2/org/json/json/20171018/
import java.io.FileWriter;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class StockQuote extends JFrame
{
    public static String[] ticker = {"KO", "AAPL", "TSLA","MSFT","SNEJF","FB","AMZN","GOOGL","INTC","LOGI"};
    public static JSONObject obj = new JSONObject();
    public static JSONArray arr = new JSONArray();
    private JPanel contentPane;
    
    public static void main(String[] args) throws IOException
    {
        System.out.println("---------------------");
        update();
        saveFile(obj);
        BuildGUI();
        
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //try - catch block
                try 
                {
                    //Create object of Inventory
                    StockQuote frame = new StockQuote();
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
    
    public static void update() throws IOException{
        for(int i = 0; i < ticker.length; i++){
            URL url = new URL("https://finance.google.co.uk/finance?q=" + ticker[i]);
            URLConnection urlConn = url.openConnection();
            InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
            BufferedReader buff = new BufferedReader(inStream);
            String price = "not found";
            String line = buff.readLine ();
            
            while(line != null){
                if(line.contains("[\""+ ticker[i] +"\",")){
                    int target = line.indexOf("[\""+ ticker[i] +"\",");
                    int deci = line.indexOf(".", target);
                    int start = deci;
                    while(line.charAt(start) != '\"'){
                        start--;
                    }
                    if(ticker[i] == "KO"){
                        price = line.substring(start + 1, deci + 3);
                    }else if (ticker[i] == "AAPL"){
                        price = line.substring(start + 14, deci + 10);
                    }else if (ticker[i] == "TSLA"){
                        price = line.substring(start + 1, deci + 2);
                    }
                    else if (ticker[i] == "MSFT"){
                        price = line.substring(start + 1, deci + 3);
                    }
                    else if (ticker[i] == "SNEJF"){
                        price = line.substring(start + 1, deci + 3);
                    }
                    else if (ticker[i] == "FB"){
                        price = line.substring(start + 1, deci + 3);
                    }
                    else if (ticker[i] == "AMZN"){
                        price = line.substring(start + 20, deci + 21);
                    }
                    else if (ticker[i] == "GOOGL"){
                        price = line.substring(start + 1, deci + 3);
                    }
                    else if (ticker[i] == "INTC"){
                        price = line.substring(start + 1, deci + 3);
                    }
                    else if (ticker[i] == "LOGI"){
                        price = line.substring(start + 27, deci + 18);
                    }
                }
                line = buff.readLine();
               
            }  
            //STORE TICKER AND PRICE IN JSON ARRAY
            JSONObject stockObj = new JSONObject();
     
            stockObj.put("ticker", ticker[i]);
            stockObj.put("price", price);
            arr.put(stockObj);
        }
        obj.put("stocks", arr);
        System.out.println("Update Done");
    }
    
    public static void saveFile(JSONObject obj){
        //SAVE ARRAY INSIDE JSON FILE
        try(FileWriter file = new FileWriter("myJSON.json")){
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("File Saved");
    }
    
    public static void BuildGUI(){
        JFrame guiFrame = new JFrame();
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Stock Quotes");
        guiFrame.setSize(600, 250);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        //Create a two dimensional array to hold the data for the JTable.
        Object[][] data = {};
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        
        //A string array containing the column names for the JTable.
        String[] columnNames = {"Ticker","Price", "Last Updated"};
        
        //Create a DeafultTableModel object for another JTable
        DefaultTableModel defTableModel = new DefaultTableModel(data, columnNames);
        JTable anotherJTable = new JTable(defTableModel);
        
        //Create a JScrollPane to contain for the JTable
        JScrollPane anotherSP = new JScrollPane(anotherJTable);
        
        //Create a JButton to refresh JTable
        
        for(int i = 0; i < arr.length(); i++){
            //get ticker and price from stock array
            String ticker = arr.getJSONObject(i).getString("ticker");
            String price = arr.getJSONObject(i).getString("price");
            
            //add ticker and price to newData object
            Object[] newData = {ticker, price, dateFormat.format(date)};
            
            //add newData object to new row of the table
            defTableModel.addRow(newData);
        }
        
        
        guiFrame.add(anotherSP);
        
        guiFrame.setVisible(true);
        
        System.out.println("GUI built");
    }
    
    public StockQuote() throws IOException{
        //set frame title
        System.out.println("---------------------");
        update();
        saveFile(obj);
        BuildGUI();                      
    }
}


/* Not used table protperties and examples
 * //an array holding data for a new column
 * //Object[] newData = {1,2};

 * //an array holding data for a new row
 * //Object[] newRowData = {5,5};

 * //Add a row
 * //defTableModel.addRow(newRowData);

 * //an array holding data for a new row
 * //Object[] insertRowData = {2.5,2.5};

 * //Insert a row
 * //defTableModel.insertRow(2,insertRowData);

 * //Change a cell value
 * //defTableModel.setValueAt(8888, 3, 1);

 * //Add the JScrollPanes to the JFrame.
 * //guiFrame.add(sp, BorderLayout.NORTH);
 */
