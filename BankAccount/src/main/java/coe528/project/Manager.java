package coe528.project;


import java.io.IOException;
/**
 *
 * @author Petar Zmak
 */
public class Manager extends User{
    
    public Manager() throws IOException{
        account = new BankAccount("admin", this, "admin");
    }
    
    public static String getName(){
        return "admin";
    }
    
    @Override
    public String toString(){
        return "manager";
    }
    
}
