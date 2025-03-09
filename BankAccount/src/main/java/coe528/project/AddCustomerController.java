package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Petar Zmak
 */
public class AddCustomerController implements Initializable{

    @FXML
    private MenuButton myMenu;
    
    @FXML
    private TextField myTextField;
    
    @FXML
    private TextField myTextField2;
    
    @FXML
    private Label myLabel;
    
    @FXML
    private void takeInfo() throws IOException{
        String pw = myTextField.getText();
        String user = myTextField2.getText();
        
        User.getAccount().addCus(user, pw, myLabel);
    }
    
    @FXML
    private void back(){
        try{
            App.setRoot("managerHome");
        }catch(IOException e){
            System.out.println("Cant go back");
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
        myMenu.setText("" + Manager.getName());
    }    
    
}
