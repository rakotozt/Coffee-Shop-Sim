import java.util.*;
import java.util.Scanner ;
import java.io.FileReader;
/**
 * Cofee Shop Simulation Main 
 *
 * @author Tafita Rakotozandry
 * @version 1
 */
public class Simulation 
{
    //time 
    private String CofeeShop_StartTime="06:00:00";// cofee shop start time 06:00 pm 
    private String CofeeShop_EndTime="21:00:00"; //cofee shop closing time 09:00 pm 

    //variables 
    private int num_cashiers;//number of cashiers  
    private int num_cashiers_available; //number of cashiers available 
    private int avg_estimated_profit ; // average of the estimated profit
    private float cost; // cost of staffing a cashier per day 
    private int avg_tspent_on_cust=0 ; //average time spent on the customer 
    private int num_cust_served=0; //num customer server 
    private int overflow=0;// overflow number 
    private int total_profit=0;
    private long seed=1521321;


    //time 
    private Integer max_wait_time; // Varaible to hold the max wait time 
    private int avg_wait_time ; // Varaible to hold the average wait time ; 

    // Prioity Queue of the waiting events 
    private PriorityQueue<Event> waitingEvents= new PriorityQueue<Event>() ;  

    // Queue of Customers In Line 
    private Queue<Event> line = new LinkedList<>() ; 

    // ArrayList to store the customer's waiting time 
    private ArrayList<Customer> waittime = new ArrayList<Customer>(); 
    private int linefactor=8; // define the line factor times the cashier number 
    /**
     * Constructor for objects of class Cf
     */
    public Simulation()
    {

    }

    public  void fileReading(int cashiers){
        try { 
            //Asking the user for the number of cashier 
            Scanner reader = new Scanner(System.in) ; 

            // System.out.println("How many cashiers would you like to test?  "); 
            //num_cashiers= reader.nextInt();
            num_cashiers=cashiers; 
            //System.out.println("Number of cashiers "+num_cashiers); 
            // Reading the file 
            Scanner sc = null; 
            sc = new Scanner( new FileReader("input.txt"));
            // sc = new Scanner( new FileReader("testSample.txt"));
            int line_count = 0; // variable to count the current line number 
            int cust_name=1 ; // variable to keep track of the identity of the costumer  
            // variable to store the lower and upper bound of the estimated profit and time to serve a costumer
            float profitMin=0;
            float profitMax=0;
            float timeMin=0; 
            float timeMax=0;

            Random rand= new Random(seed);
            while(sc.hasNextLine() ) {
                if(line_count==0) { //check the first line 
                    //fetch the estimated profit p1 and p2 
                    String [] profits =sc.nextLine().split(" ");
                    profitMin = Float.parseFloat(profits[0]);                              
                    profitMax =  Float.parseFloat(profits[1]);  

                }
                else if (line_count==1) {//check the second line 
                    //fetch the data on cost of staffing a cashier counter per day in dollars.
                    cost= Float.parseFloat(sc.nextLine()); 

                } 
                else if (line_count == 2) { //check the third line 
                    //fetch the data on the lower and upper bounds of the average time for a cashier to serve a customer in seconds.
                    String [] time =sc.nextLine().split(" ");
                    timeMin = Float.parseFloat(time[0]);   //low bound of time 
                    timeMax = Float.parseFloat(time[1]);   //upper bound of time

                    //avg_tspent_on_cust=(int) (timeMax+timeMin)/2; // taking their average 
                } 
                else if( line_count > 2 ){ //check the lines more than the third line 
                    String time = sc.nextLine(); 
                    String arrival_time [] = timeReading(time); //Process the time input using the time reading function 
                    avg_estimated_profit=(int) (rand.nextInt( (int) (profitMax - profitMin + 1)) + profitMin);
                    //avg_estimated_profit=(int) profitMax;
                    //System.out.println(avg_estimated_profit);
                    avg_tspent_on_cust=(int) (rand.nextInt( (int) (timeMax - timeMin + 1)) + timeMin);
                    //avg_tspent_on_cust=(int) timeMin;
                    Customer c = new Customer(hours_to_seconds(arrival_time), cust_name,avg_tspent_on_cust,avg_estimated_profit); //create a new Costuner 
                    Event e = new Event(c,hours_to_seconds(arrival_time),Event.ARRIVAL,cust_name); //create a new event corresponding to the arrival of the customer 
                    cust_name++; // Incrementing the customer name to deferentiante their name 
                    addToWaitingEvents(e);// add the new event to the waiting event priority queue 
                }   
                line_count ++ ; //move to the next line 

            }

        }
        catch (Exception e) {
            System.out.println ("Exception occured " + e); //check the exception 

        }
    }

    /**
     * Run the program 
     * 
     * @param cashiers the number of cashiers 
     */
    public void run(int cashiers){
        fileReading(cashiers); 
        // Convert the opening and closing of the cofeeShop 
        String [] t1= timeReading(CofeeShop_StartTime);
        String [] t2= timeReading(CofeeShop_EndTime);

        //starting time of the simulation 
        int startTime=(int) hours_to_seconds(t1);
        int endTime=(int) hours_to_seconds(t2);

        num_cashiers_available = num_cashiers; //number of cashiers available  
        int current_time=0 ; // current time 

        Event evt = new Event(); //create a new even instance to be used in this simulation 
        int wait_time; // save waittime of the customers
        int depart_time ; // holds the departure time of the customers

        while( !waitingEvents.isEmpty()) {//as long as the waiting events is not empty 
            evt= waitingEvents.remove(); 
            //System.out.println("Time : "+ evt.getTime() +  " ==> Customer" + evt.getName() +" "+ evt.getStatus() );

            // First check if the customer arrival time is within tht bounds of the shop's 
            // opening and closing time

            if (( evt.getTime() >= startTime && evt.getTime() <= endTime) || (evt.getType() == Event.DEPARTURE)) { 
                current_time= evt.getTime();  
                if (evt.getType() == Event.ARRIVAL) { // If the event is an arrival type 
                    if (num_cashiers_available >0) { //Checking if there are available cashiers 
                        num_cashiers_available -- ; // Decrement the number of cashiers 
                        num_cust_served ++ ; //Increase the number of people served 
                        total_profit=total_profit+evt.getProfit();//add the profit generated by the customer
                        //System.out.println( "Time: "+current_time+" =>Customer: " + evt.getName() + " is served" + " Number Of Cashier Available: " + num_cashiers_available ); 

                        //Calculate Depature time 
                        depart_time = evt.departTime(current_time,evt.getServingTime());

                        // Wait time should be zero 
                        wait_time= 0 ; 

                        // Setting the event time equal to the departure time of that event
                        evt.setTime(depart_time);
                        // Setting the customer customer departue time 
                        evt.customer.setDepartTime(depart_time); 

                        //Setting Customers Waittime 
                        evt.customer.setWaitTime(wait_time);

                        // Adding To The arraylist of customers
                        waittime.add(evt.customer);

                        // Changing the event type to a departure after it is served
                        evt.setType(Event.DEPARTURE) ; 

                        addToWaitingEvents(evt); // Adding the event back to the queue as a departure event

                        //    System.out.println( "Customer " + evt.getName() + " Departure Time: " + evt.getTime()); 
                        //   System.out.println( "Customer " + evt.getName() + " Current Time: " + current_time ); 

                        //System.out.println( "Customer " + evt.getName() + " Departure Time: " + evt.getTime());

                    } 

                    else { // if cashiers are unavailable add the customer to the line 
                        // check if the line is full 
                        if (line.size() < (linefactor*num_cashiers)) {

                            line.add(evt) ; 
                            // System.out.println("Time : "+ evt.getTime() +  " ==> Customer" + evt.getName() + " joins the line");
                            // System.out.println("Linesize: " + line.size()); 

                        }
                        else  { // if the line is full, we set the result to overflow 
                            overflow ++ ; // Increment Overflow 

                            //System.out.println("Time: "+evt.getTime()+"==> Customer: " + evt.getName()+" rejected because the line is full"); 

                        }
                    }
                }

                else if (evt.getType() == Event.DEPARTURE) { // If the event is a departure type

                    //System.out.println( "Customer" + evt.getName() + " leaves" + " Number of Cashiers Available: " + num_cashiers_available );

                    //Get a new person 
                    if (line.size() != 0) { //as long as the line is not empty 
                        //System.out.println("Here");
                        //System.out.println(total_profit);
                        evt = line.remove();  //take someone from the line 

                        num_cust_served ++ ; // increase the number of customer served 
                        total_profit=total_profit+evt.getProfit();//add the profit generated by the customer 
                        //System.out.println( "Time: "+current_time+" =>Customer" + evt.getName() + " is served" + " Number Of Cashier Available: " + num_cashiers_available ); 

                        wait_time = evt.waitTime(current_time,evt.getTime()); // get wait time 
                        // Printing Out Waittime 
                        //System.out.println ( "Customer: " + evt.getName() + " Wait Time: " + wait_time); 

                        depart_time = evt.departTime(current_time,evt.getServingTime());// generate the departure time 
                        evt.setTime( depart_time); // set the event departure time   
                        evt.customer.setDepartTime(depart_time); // set the customer customer departure time 

                        evt.setType(Event.DEPARTURE) ; //se the the departure time 
                        addToWaitingEvents(evt); // Adding the event back to the queue as a departure event

                        //  System.out.println( "Customer " + evt.getName() + " is served" );
                        //     System.out.println( "Customer " + evt.getName() + " Departure Time: " + evt.getTime()); 
                        //     System.out.println( "Customer " + evt.getName() + " Current Time: " + current_time );

                        evt.customer.setWaitTime(wait_time);  //Set the customers waittime 
                        waittime.add(evt.customer); //wdd the waittime to the waittime arraylist 
                    }
                    else {
                        num_cashiers_available ++; // increase the number of cashier 
                        //System.out.println("New cashier available."+"Current number of Cashier Available "+num_cashiers_available);
                    } 
                }

            }
            else { 
                // System.out.println("Time: "+evt.getTime()+"==> Customer: " + evt.getName()+" turned away"); 
            }

        }
        
        //Uncomment the followings to look at different output results 
        //System.out.println ("Num of Overflow: " + overflow); 
        //System.out.println ("Num of Customers Served  " + num_of_served); 
        //System.out.println ("Number of Available Cashier: " + s_available ); 
        // System.out.println ("Num of cashiers: "+num_cashiers+" Netprofit: " + calc.calculateNetProfit(total_profit ,cost, num_cashiers) );

        Calculation calc = new Calculation(); 
        System.out.println ("Num of cashiers: "+num_cashiers+" Netprofit: " + calc.calculateNetProfit(total_profit ,cost, num_cashiers) );
        System.out.println ("Num of cashiers: "+num_cashiers+" OverFlow Percentage: " + calc.calculateOverFlow(num_cust_served,overflow) );
    
 
        //  System.out.println ("Costumer Served: " + num_cust_served );
        //  System.out.println ("Overflow: " + overflow );
        //System.out.println ("Total Profit: " + total_profit ); 
     
        // System.out.println ("Total Cost: " + calc.calculateTotalCost(num_cashiers,cost)); 
        System.out.println ("Num cashiers: "+num_cashiers+" Avg Waittime: " + calc.avgWaitTime(waittime) );
        System.out.println ("Max Wait-Time: " + calc.maxWaitTime(waittime)); 
 

    }

    /**
     * stringSplit returns an array that has the arrival time of the customer
     * split into the indexes of the array  
     * 
     * @param String time
     * @return String [] time_a
     * 
     */
    public String [] timeReading (String input) {

        //Stroring the split values of time in an array
        String a [] = input.split(":"); 

        return a ; 
    }

    /**
     * Convert time from Hours to Seconds
     * 
     * @param time array of time containeing hours,minute and second 
     * @return int time_conv
     * 
     */
    public int hours_to_seconds( String[] time) { 
        int hours_to_sec = (int)Float.parseFloat(time[0]) * 3600 ; 
        int mins_to_sec = (int)Float.parseFloat(time[1]) * 60 ; 
        int sec = (int)Float.parseFloat(time[2]) ; 

        int time_conv = hours_to_sec + mins_to_sec + sec; 
        //Returning the converted time 
        return time_conv;
    }

    /**
     * addToPQ methods adds an instance of event to Priority Queue
     * 
     * @param Event e 
     */
    public void addToWaitingEvents(Event e ) {
        // Adds Instance of Event Passed Into The Prp
        waitingEvents.add(e) ; 

    }

    /**
     * Returns waiting Events
     * 
     * @return waiting events 
     */
    public PriorityQueue<Event> getWaitingEvents() { 
        return waitingEvents ; 
    }

}
