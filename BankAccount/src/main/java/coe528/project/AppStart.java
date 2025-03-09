package coe528.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.scene.control.Label;

/**
 *
 * @author Petar Zmak
 */
public class AppStart{
 
   
    private User user;
    private Label myLabel;
    private String nameInput;
    private String pwInput;
    private Scanner scanner;
    private String file;
    
  
    public AppStart(String nameInput, String pwInput, Label myLabel) throws IOException{
        this.nameInput = nameInput;
        this.pwInput = pwInput;
        this.myLabel = myLabel;
        file = Paths.get("src", "main", "java", "coe528", "project", "AllUsers", nameInput) + ".txt";
        logInPage(nameInput, pwInput);
        
    }
    
    public void logInPage(String nameInput, String pwInput) throws IOException{
        
        
        String role;
        String level;
        
        String username = "";
        String password = "";
        
        
        try{
            
            scanner = new Scanner(new File(file));
        } catch(IOException e){
            myLabel.setText("User does not exist");
        }
        
        username = scanner.next();
        password = scanner.next();
        role = scanner.next();
        
        
        if (nameInput.equals("admin") && role.equals("manager")){
            try{
                user = new Manager();
            }catch(IOException e){System.out.println("what??");}
            
        }
        else if (role.equals("customer")){
            level = scanner.next();
            user = new Customer(nameInput, level);
        }
        scanner.close();
        if (nameInput.equals(username) && pwInput.equals(password)){
            
            if (user instanceof Manager){
                App.setRoot("managerHome");
            }
            else if (user instanceof Customer){
                App.setRoot("CustomerHome");
            }
        }
        else{
            myLabel.setText("Incorrect credentials");
        } 
    }
    
}
