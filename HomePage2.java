import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
//create class and extend with JFrame
public class HomePage2 extends JFrame 
{
   
    //declare variable
    private JPanel contentPane;
    /**
     * Launch the application.
     */
    //main method
    public static void main(String[] args)
    {
        HomePage2 frameTabel = new HomePage2();
        /* It posts an event (Runnable)at the end of Swings event list and is
        processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //try - catch block
                try 
                {
                    //Create object of HomePage2
                    HomePage2 frame = new HomePage2();
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
 
    /**
     * Create the frame.
     */
     public HomePage2()//constructor 
    {
        //set frame title
        setTitle("Home Page");
        //set default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //set bounds of the frame
        setBounds(200, 200, 450, 300);
        //create object of JPanel
        contentPane = new JPanel();
        //set border
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //set ContentPane
        setContentPane(contentPane);
        //set null
        contentPane.setLayout(null);
        //create object of JButton and set label on it
        JButton bNews = new JButton("News");
        JButton binvent = new JButton("Inventory");
        JButton bStocks = new JButton("Stocks");
        JButton bStockQuote = new JButton("Stock Quotes");
        JLabel lblHomePage = new JLabel("THIS IS YOUR HOME PAGE");
        //adding colours
        lblHomePage.setForeground(Color.white);
        contentPane.setBackground(Color.BLACK);

        //add actionListener
        bNews.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            //set default close operation
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //call the object of NewsPage and set visible true
                try 
            {
                //Create object of ReadRSS
                //call the object of ReadRSS and set visible true
                ReadRSS frame = new ReadRSS();
                frame.setVisible(true);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            }
        });
       
        bStockQuote.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //call the object of NewsPage and set visible true
                try 
            {
                //Create object of StockQuote
                StockQuote frame = new StockQuote();
                frame.setVisible(true);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }        
                //set default close operation
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        //set bounds of the JButton and Jlabel
        bNews.setBounds(120, 90, 78, 25);
        //binvent.setBounds(115, 90, 100, 25);
        //bStocks.setBounds(202,90,78,25);
        bStockQuote.setBounds(202,90,125,25);
        lblHomePage.setBounds(135, 20, 180, 50);
        //add Button & Lable into contentPane
        contentPane.add(bNews);
        contentPane.add(binvent);
        contentPane.add(bStocks);
        contentPane.add(lblHomePage);
        contentPane.add(bStockQuote);
        
        
        
    }
}
