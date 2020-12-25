import java.util.*; 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CalculationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalculationTest
{
    /**
     * Default constructor for test class CalculationTest
     */
    public CalculationTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test 
    public void TestNestProfit() {
        Calculation calc=new Calculation();
        int a=0;
        System.out.println(calc.calculateOverFlow (100,5));
        if(calc.calculateOverFlow (100,5)==0.05) a=1;
        assertEquals(a,0);

    }

    @Test 
    public void TestMaxWaitTime() {
        Calculation calc=new Calculation();
        Customer s = new Customer(3600, 1, 200,100); 
        s.setWaitTime(53);
        Customer p = new Customer(7500, 2, 200,100);
        p.setWaitTime(74);
        Customer q = new Customer(7500, 3, 200,100); 
        q.setWaitTime(500);
        Customer r = new Customer(8000, 4, 200,100);
        r.setWaitTime(20);
        ArrayList<Customer> wait=new ArrayList<Customer>();
        wait.add(s);
        wait.add(p);
        wait.add(q);
        wait.add(r);
        assertEquals(calc.maxWaitTime(wait),500);
    }
}
