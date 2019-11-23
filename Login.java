import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.io.FileReader;

public class Login extends JFrame {

public static void main(String[] args) {
    Login frameTabel = new Login();
}

JButton blogin = new JButton("Login");
JPanel panel = new JPanel();
JTextField txuser = new JTextField("username");
JPasswordField pass = new JPasswordField("password");
JLabel lblWelcome = new JLabel("Welcome to myStocks");
Login(){
super("Login Authentication");
setSize(300,200);
setLocation(500,280);
panel.setLayout (null); 

panel.setBackground(Color.BLACK);
lblWelcome.setForeground(Color.white);


lblWelcome.setBounds(76,10,160,20);
txuser.setBounds(70,30,150,20);
pass.setBounds(70,65,150,20);
blogin.setBounds(110,100,80,20);


panel.add(blogin);
panel.add(txuser);
panel.add(pass);
panel.add(lblWelcome);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionLogin();
}
public void actionLogin(){
blogin.addActionListener(new ActionListener() {    
public void actionPerformed(ActionEvent ae) {
String puname = txuser.getText();
String ppaswd = pass.getText();

//JSON file
JSONParser parser = new JSONParser();
try {     
    Object obj = parser.parse(new FileReader("/Users/pablote/Desktop/myStock#2/users.json"));

    JSONObject jsonObject = (JSONObject) obj;
    JSONArray users = (JSONArray) jsonObject.get("users");
    boolean loggedIn = false;
    for(int i = 0; i < users.size(); i++){
        JSONObject jobj = (JSONObject) users.get(i);
        String correctUsername = String.valueOf(jobj.get("username"));
        String correctPassword = String.valueOf(jobj.get("password"));
        if(puname.equals(correctUsername) && ppaswd.equals(correctPassword)) {
            loggedIn = true;
            HomePage2 regFace =new HomePage2();
            regFace.setVisible(true);
            dispose();
        }
    }
    if(!loggedIn){
        JOptionPane.showMessageDialog(null,"Wrong Password / Username");
        txuser.setText("");
        pass.setText("");
        txuser.requestFocus();
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
} catch(ParseException e) {
    e.printStackTrace();
}

}
});
}
    
}

/* Login code
 * 
 * String username = “”; //input by user
 * String password = “”; //input by user
 * for(int I = 0; I < usersArray.length; I++){
 *      if(username == usersArray.username && password == usersArray.password){
 *          System.out.println(“Logged In”);
 *      } else{
 *          System.out.println(“Wrong username or password”)
 *      }
 * }
 */
