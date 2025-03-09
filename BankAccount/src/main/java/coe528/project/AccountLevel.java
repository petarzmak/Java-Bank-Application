package coe528.project;

import java.io.IOException;

/**
 *
 * @author Petar Zmak
 */
public abstract class AccountLevel {
    protected Customer customer;
    
    public AccountLevel(Customer customer){
        this.customer = customer;
    }
    
    public abstract void button() throws IOException;
    
    public abstract void button2() throws IOException;
    
    public abstract void button3() throws IOException;
    
}
