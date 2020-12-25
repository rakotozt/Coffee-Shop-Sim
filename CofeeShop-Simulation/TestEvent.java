
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestEvent.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestEvent
{
    /**
     * Default constructor for test class TestEvent
     */
    public TestEvent()
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
     * Compares Arrival time  
     *
     */ 
    @Test 
    public void EventArrivalComparison() { 
        int name1 = 1 ; 
        int name2 = 2 ; 
        int name3 = 3;
        int name4=4;
        Customer s = new Customer(3600, name1, 200,100); 
        Customer p = new Customer(7500, name2, 200,100); 
        Customer q = new Customer(7500, name3, 200,100); 
        Customer r = new Customer(8000, name4, 200,100);

        Event a = new Event(s,s.getArrivalTime(),1, name1) ;
        Event b = new Event(p,p.getArrivalTime(),1, name2) ; 
        Event c = new Event(q,q.getArrivalTime(),1, name3) ; 
        Event d = new Event(r,r.getArrivalTime(),1, name4) ; 

        assertEquals(a.compareTo(b),-1);
        assertEquals(b.compareTo(c),0);
        assertEquals(d.compareTo(a),1);
    } 

    /**
     * Compares Arrival time  
     *
     */  
    @Test 
    public void departimeTest() {
        int current_time = 1600 ; 
        int time_served= 120 ; 

        Event a = new Event() ;

        int the_ans= a.departTime(current_time,time_served); 
        int tru_ans= current_time + time_served; 

        assertEquals(the_ans,tru_ans);
    }

    //Testing the Wait Time method 
    @Test 
    public void waittimeTest() {
        int current_time = 11600 ;
        int name1 = 1; 
        int time = 9600; 

        Customer s = new Customer(3600, name1, 200,100); // Creating an instance of customer 
        Event a = new Event (s,time,1, name1) ; // Creating an instance of Event

        int the_ans = a.waitTime(current_time, a.getTime()); 
        int tru_ans = current_time - time; 

        assertEquals(the_ans,tru_ans);

    }
    
        @Test 
    public void TestAssignEvent() {
        int current_time = 11600 ;
        int name1 = 1; 
        int time = 9600; 

        Customer s = new Customer(3600, name1, 200,100); // Creating an instance of customer 
        Event a = new Event (s,time,0, name1) ; // Creating an instance of Event

        assertEquals(a.getType(),0);
        
       a.setType(1); 
       assertEquals(a.getType(),1);
       assertEquals(a.getTime(),9600);

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
}
