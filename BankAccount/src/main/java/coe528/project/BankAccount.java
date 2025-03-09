package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Label;



/**
 *
 * @author Petar Zmak
 */
public class BankAccount {

    // Overview: BankAccount is a mutable class that provides every function that a user can do in their account.
    
    /** The abstraction function is:
     *      user is the specific User that is logged in to this BankAccount
     *      file is a String representation of the file path where the  users information is stored
     *      listfile is a String representation of the file path where the list of customers is located
     *      input is the scanner that will read from a user file
     *      customers is an ArrayList of all the existing customers
     *      level is a String representation of the customers level
     */
   
    /** The rep invariant is:
     *      user cannot be null and must be either an instance of Customer or Manager
     *      file cannot be null and specified file must exist
     *      listfile cannot be null and specified file must exist
     *      input cannot be null
     *      customers cannot be null, and must only contain strings
     *      level must not be null and must be either ("silver", "gold", "platinum", "admin")
     *      BankAccount must not be null
     */
  
    //the rep
    private User user;
    private String file;
    private String listfile;
    private Scanner input;
    private ArrayList<String> customers = new ArrayList <String> ();
    private String level;
    
    //constructor
    public BankAccount(String username, User user, String level) throws IOException{
        // EFFECTS: Creates a new Bank Account Object and initializes all instance variables in the rep
        
        file = Paths.get("src", "main", "java", "coe528", "project", "AllUsers", username) + ".txt";
        
        listfile = Paths.get("src", "main", "java", "coe528", "project", "Customers") + ".txt";
       
        try{
            input = new Scanner(new File(listfile));
        } catch(IOException e){System.out.println("you suck");}
        
        while(input.hasNext()){
            customers.add(input.nextLine());
        }
        input.close();
        
        this.user = user;
        this.level = level;
    }
    
    public void deposit(double amount) throws IOException{
        //REQUIRES: a double value
        //MODIFIES: the users file located at the instance variable String file
        //EFFECTS: increases the users balance and updates their level (see writeBal() and updateLevel() for more information)
        
        
        String balance = "";
        balance += this.getBalance() + amount;
        writeBal(balance);
        updateLevel();
    }
    
    public void withdraw(double amount) throws IOException{
        //REQUIRES: a double value amount less than this.getBalance()
        //MODIFIES: the users file located at the instance variable String file
        //EFFECTS: decreases the users balance and updates their level (see writeBal() and updateLevel() for more information)
        
        String balance = "";
        balance += this.getBalance() - amount;
        writeBal(balance);
        updateLevel();
    }
    
    public double purchaseOnline(double purchase) throws IOException, IllegalArgumentException{
        //REQUIRES: a double value purhcase less than this.getBalance() but greater than 50
        //MODIFIES: the users file located at the instance variable String file
        //EFFECTS: decreases the users balance and updates their level (see writeBal() and updateLevel() for more information)
        
        double currbal = this.getBalance();
        double fee = 0;
        double truePurchase = purchase;
        String balance = "";
        
  
        if(user.getLevel() instanceof Silver){
            fee = 20;
            truePurchase += fee;
        }
        else if(user.getLevel() instanceof Gold){
            fee = 10;
            truePurchase += fee;
        }
        
        if (truePurchase > currbal){
            throw new IllegalArgumentException("WRONGGGGG");
        }
        
        balance += currbal - purchase - fee;
        
        writeBal(balance);
        updateLevel();
        
        return fee;
    }
    
    public double getBalance() throws IOException{
        //REQUIRES: none
        //MODIFIES: none
        //EFFECTS: returns the users balance as a double as read from the file located at "file"
        
        input = new Scanner(new File(file));
        int i = 0;
        
        while(input.hasNext()){
            String balance = input.nextLine();
            if (i != 4){
                i++;
                continue;
                }
            input.close();
            return Double.parseDouble(balance);
        }
        input.close();
        return 0;
    }
    
    public void writeBal(String balance) throws IOException{
        //REQUIRES: a String value balance representing a new balance
        //MODIFIES: the users file located at the instance variable String file
        //EFFECTS: writes the String balance to the file updating the balance
        
        String[] full = {"","","","",""};
        FileWriter write;
        
        input = new Scanner(new File(file));
        int i = 0;
        while(input.hasNext()){
            String value = input.nextLine();
            full[i] = value;
            i++;
        }
        full[4] = balance;
       
        write = new FileWriter(file, false);
        write.close();

        write = new FileWriter(file,true);
        write.write(full[0]+"\n");
        write.write(full[1]+"\n");
        write.write(full[2]+"\n");
        write.write(full[3]+"\n");
        write.write(full[4]+"\n");
        write.close();
        input.close();
    }   
    
    public void updateLevel() throws IOException{
        //REQUIRES: none
        //MODIFIES: the users file located at the instance variable file and the users level
        //EFFECTS: writes the users new level to the file and sets Accountlevel level to its new level
        
        String[] full = {"","","","",""};
        FileWriter write;
        
        double balance = getBalance();
        input = new Scanner(new File(file));
        int i = 0;
        
        while(input.hasNext()){
            String value = input.nextLine();
            full[i] = value;
            i++;
        }
        if (balance < 10000){
            full[3] = "silver";
            level = "silver";
            user.getLevel().button();
        } else if(balance < 20000){
            full[3] = "gold";
            level = "gold";
            user.getLevel().button2();
        } else {
            full[3] = "platinum";
            level = "platinum";
            user.getLevel().button3();
        }
       
        write = new FileWriter(file, false);
        write.close();

        write = new FileWriter(file,true);
        write.write(full[0]+"\n");
        write.write(full[1]+"\n");
        write.write(full[2]+"\n");
        write.write(full[3]+"\n");
        write.write(full[4]+"\n");
        write.close();
        input.close();
    }   
    
    public void addCus(String username, String password, Label myLabel) throws IOException{
        //REQUIRES: a String value username, String value password, and Label myLabel
        //MODIFIES: the customer file located at the instance variable String listfile 
        //          modifies myLabel
        //          customers array
        //EFFECTS:  creates a new file located at String newFile and writes a username, password, user, level, and balance to it. 
        //          updates myLabel based on any error messages or success messages
        //          adds username to customers array and writes username to customer file
        
        String newFile;
        FileWriter write;
        
        boolean check = false;
        
        
        for (String e : customers){
            if (username.equals(e)){
                myLabel.setText("A user with that username already exists");
                check = true;
            }else if(username.equals("admin")){
                myLabel.setText("Cannot create admin. Admin already exists");
                check = true;
            }
        }
            if(!check){
                
                Path userpath = Paths.get("src", "main", "java", "coe528", "project", "AllUsers", username);
                newFile = userpath + ".txt";
                
                write = new FileWriter(newFile, false);
                write.close();

                write = new FileWriter(newFile, true);

                write.write(username + "\n");
                write.write(password + "\n");
                write.write("customer\n");
                write.write("silver\n");
                write.write("100");
                write.close();

                customers.add(username);
                write = new FileWriter(listfile, true);
                write.write(username + "\n");
                write.close();
                myLabel.setText("Successful Creation!");
                input.close();
            }
    }
    
    public void delCus(String username, Label myLabel) throws IOException{
        //REQUIRES: a String value username, and Label myLabel
        //MODIFIES: the customer file located at the instance variable String listfile 
        //          modifies myLabel
        //          customers array
        //EFFECTS:  deletes file with name username 
        //          updates myLabel based on any error messages or success messages
        //          removes username to customers array and deletes username from customer file
        
        boolean check = false;
        FileWriter write;
        
        if(username.equals("admin")){
            myLabel.setText("Cannot delete admin");
            check = true;
        }

        if (!check){
            
            Path userpath = Paths.get("src", "main", "java", "coe528", "project", "AllUsers", username);
            String userfile = userpath + ".txt";

            customers.remove(username);
            write = new FileWriter(listfile, false);
            write.close();

            write = new FileWriter(listfile, true);
            for (String e : customers){
                write.write(e + "\n");
            }
            write.close();

            File delFil = new File(userfile);
            delFil.delete();

            if(delFil.exists()){
                myLabel.setText("Failed to delete");
            } else
                myLabel.setText("Successfully deleted");
            
        }
        input.close();
    }
    
    public String getLevel(){
        //REQUIRES: none
        //MODIFIES: none
        //EFFECTS:  returns the users level as a string
        return level;
    }
       
    @Override
    public String toString(){
        // EFFECTS: Returns a string that contains the information of this BankAccount. Implements the abstraction function.
        String thing;
        thing = "\nThis Bank Account is being used by a: " + user.toString();
        thing += "\nThe users file is located at: " + file;
        thing += "\nThe list of customers file is located at: " + listfile;
        thing += "\nThe scanner is: " + input.toString();
        thing += "\nThe list of Customers is: ";
       
        for(String e : customers)
            thing += "\n    " + e;
      
        thing += "\nThis user level is: " + level + "\n";
        
        return thing;
    }
    
    public boolean repOk(){
        // EFFECTS: Returns true if the rep invariant holds for this object; otherwise returns false. Implements the rep invariant
        File fileCheck;
        
        if(user.equals(null) || (!(user instanceof Manager) && !(user instanceof Customer)))
            return false;
        fileCheck = new File(file);
        if (file.equals(null) || !(fileCheck.exists()))    
            return false;
        fileCheck = new File(listfile);
        if (file.equals(null) || !(fileCheck.exists()))    
            return false;
        if (input.equals(null))
            return false;
        if (customers.equals(null))
            return false;
        for(String e : customers)
            if(e.equals(null)) return false;
        if (level.equals(null) || (!(level.equals("silver")) && !(level.equals("gold")) && !(level.equals("platinum")) && !(level.equals("admin"))))
            return false;
        if(this.equals(null))
            return false;
        
        return true;
    }
}
