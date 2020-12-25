
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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

    /**
     * Compares Arrival time  
     *
     */  
    @Test 
    public void TestCostumerReturn() {
        Customer s = new Customer(3600, 4, 200,100); 
        assertEquals(s.getArrivalTime(),3600);
        assertEquals(s.getServingTime(),200);
        assertEquals(s.getProfit(),100);
  
    }
}
