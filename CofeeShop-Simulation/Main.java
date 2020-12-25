
/**
 * Main to run the program 
 *
 * @author Tafita Rakoto
 * @version 1
 */
import java.util.*;

public class Main
{

    /**
     * Constructor for objects of class Main
     */
    public Main()
    {

    }

    public static void main (String[] args ) { 
        Main sim = new Main(); 

        if (args.length>0){
            int i=Integer.parseInt(args[0]);
            sim.run(i);
        }
        else{
            for (int i=1; i<10; i++){
                sim.run(i); }}

    } 

    public void run(int cashiers) { 
        // Creating new instance of coffee shop 
        Simulation shop_sim = new Simulation() ; 

        shop_sim.run(cashiers) ; 
    }
}
