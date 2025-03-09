package coe528.project;

import java.io.IOException;


/**
 *
 * @author Petar Zmak
 */
public class Customer extends User{

    private static String name;
    
    public Customer(String name, String lev){
        this.name = name;
        try{
            account = new BankAccount(name, this, lev);
        } catch(IOException e){System.out.println("Bank account ioexception");}
        
        if(lev.equals("silver"))
            level = new Silver(this);
        else if(lev.equals("gold"))
            level = new Gold(this);
        else
            level = new Platinum(this);
    }
    
    public static String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return "customer";
    }
    
}
