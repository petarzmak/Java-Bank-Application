package coe528.project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Petar Zmak
 */

public class LogInPageController {

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
        try{
            AppStart begin = new AppStart(user, pw, myLabel);
        } catch (Exception e){System.out.println(e);}
    }
            
}
