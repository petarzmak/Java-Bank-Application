package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Petar Zmak
 */
public class DepositController implements Initializable {

    @FXML
    private MenuButton myMenu;
    
    @FXML
    private TextField myAmount;
    
    @FXML
    private Label myLabel;
    
    @FXML
    private void logOut(){
        try{
            User.setAccount(null);
            App.setRoot("logInPage");
        }catch(IOException e){
            System.out.println("Cant log out");
        }
    }
    
    @FXML
    private void back(){
        try{
            App.setRoot("CustomerHome");
        }catch(IOException e){
            System.out.println("Cant go back");
        }
    }
    
    @FXML
    private void depositMon(){
        try{
            double amount = Double.parseDouble(myAmount.getText());
            
            Paint painter = new Color(0,0,0,1.0);
            myLabel.setTextFill(painter);
  
            if(amount < 0){
                myLabel.setText("You cannot deposit a negative amount");
            }else {
                
                User.getAccount().deposit(amount);
                
                Paint paint = new Color(0,0,1,1.0);
                myLabel.setTextFill(paint);
                myLabel.setText("$" + amount + " was deposited\nYour new balance is: $" + User.getAccount().getBalance());
            }
        }catch (NumberFormatException e){
            myLabel.setText("You must input a number");
        }catch (IOException d){
            myLabel.setText("Error depositing");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myMenu.setText("" + Customer.getName());
        myLabel.setText(" ");
    }    
    
}
