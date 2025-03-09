package coe528.project;

/**
 *
 * @author Petar Zmak
 */
public abstract class User {
 
    protected static BankAccount account;
    protected AccountLevel level;
    
    public void setLevel(AccountLevel level){
        this.level = level;
    }
    
    public AccountLevel getLevel(){
        return this.level;
    }
    
    public static BankAccount getAccount(){
        return account;
    }
    
    public static void setAccount(BankAccount account1){
        account = account1;
    }
    
}
