package coe528.project;
/**
 *
 * @author Petar Zmak
 */
public class Platinum extends AccountLevel{
    public Platinum (Customer customer){
        super(customer);
    }
    
   @Override
    public void button(){
        customer.setLevel(new Silver(customer));
    }
    
    @Override
    public void button2(){
        customer.setLevel(new Gold(customer));
    }
    
    @Override
    public void button3(){
        customer.setLevel(new Platinum(customer));
    }
}