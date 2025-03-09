package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;

/**
 * FXML Controller class
 * 
 * @author Petar Zmak
 */

public class ManagerHomeController implements Initializable{

    @FXML
    private MenuButton myMenu;
    
    @FXML
    private void switchToAddCus() throws IOException {
        App.setRoot("AddCustomer");
    }
    
    @FXML
    private void switchToDelCus() throws IOException {
        App.setRoot("DelCustomer");
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