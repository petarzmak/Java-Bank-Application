package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;


/**
 * FXML Controller class
 *
 * @author Petar Zmak
 */
public class CustomerHomeController implements Initializable{

    @FXML
    private Label myLabel;
    
    @FXML
    private Label myBalance;
    
    @FXML
    private Label myLevel;
    
    @FXML
    private MenuButton myMenu;
    
    
    @FXML
    private void withdraw(){
        try{
            App.setRoot("withdraw");
        }catch(IOException e){
            System.out.println("Cant go to withdraw");
        }
    }
    
    @FXML
    private void deposit(){
        try{
            App.setRoot("Deposit");
        }catch(IOException e){
            System.out.println("Cant go to deposit");
        }
    }
    
    @FXML
    private void onlinePurch(){
        try{
            App.setRoot("OnlinePurch");
        }catch(IOException e){
            System.out.println("Cant go to Online Purchase");
        }
    }
    
    @FXML
    private void logOut(){
        try{
            User.setAccount(null);
            App.setRoot("logInPage");
        }catch(IOException e){
            System.out.println("Cant log out");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myLabel.setText("Welcome " + Customer.getName() + "!");
        
        try{
            myBalance.setText("Your current balance is: $" + User.getAccount().getBalance());
            myLevel.setText("You account level is: " + User.getAccount().getLevel());
        } catch (IOException e){
          System.out.println("Input Problem");
        }
        myMenu.setText("" + Customer.getName());
        
        
    }
    
   
    
}
