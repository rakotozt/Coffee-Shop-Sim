
/**
 * Write a description of class TestMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestMain
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class TestMain
     */
    public TestMain()
    {
    }

    public static void main (String[] args ) { 
        // Creating Simulation Instance of Launcher 
        TestMain sim = new TestMain(); 
        // for (int i=3; i<4; i++){
        // sim.run(i); }
        sim.run(4);

    } 

    public void run(int cashiers) { 
        // Creating new instance of coffee shop 
        TestSim shop_sim = new TestSim() ; 

        shop_sim.run(cashiers) ; 
    }
}
