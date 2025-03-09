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
public class OnlinePurchController implements Initializable {

    @FXML
    private MenuButton myMenu;
    
    @FXML
    private TextField myAmount;
    
    @FXML
    private TextField myItem;
    
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
    private void PurchaseOnline(){
        try{
            String item = myItem.getText();
            double purchase = Double.parseDouble(myAmount.getText());
            
            double currbal = User.getAccount().getBalance();
            Paint painter = new Color(1,0,0,1.0);
            myLabel.setTextFill(painter);
           
            
            if(purchase < 50){
                myLabel.setTextFill(painter);
                myLabel.setText("Your purchase must be at least $50");
            }
            else if(purchase > currbal && currbal >= 50){
                myLabel.setTextFill(painter);
                myLabel.setText("You do not have enough money in your account\nYour balance is $" + currbal);
            }else if(currbal <= 0 || currbal < 50){
                myLabel.setTextFill(painter);
                myLabel.setText("You do not have enough money in your account\nYour balance is $" + currbal);
            }else {
                double fee = User.getAccount().purchaseOnline(purchase);
                
                Paint paint = new Color(0,0,1,1.0);
                myLabel.setTextFill(paint);
                myLabel.setText("$" + purchase + " + $" + fee + " processing fee was withdrawn to purchase the " + item + "\nYour new balance is: $" + User.getAccount().getBalance() + "\nThank you for Shopping");
            }
        }catch (NumberFormatException e){
            myLabel.setText("You must input a number");
        }catch (IOException d){
            myLabel.setText("Error with online Purchase");
        }catch (IllegalArgumentException f){
            myLabel.setText("You don't have enough money in your acconut");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myMenu.setText("" + Customer.getName());
        myLabel.setText(" ");
    }    
    
}
