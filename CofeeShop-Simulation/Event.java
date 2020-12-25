
/**
 * The Event Class 
 *
 * @author Tafita Rakotozandry 
 * @version 1
 */
public class Event implements Comparable <Event> 
{

    static final int ARRIVAL = 0 ; 
    static final int DEPARTURE = 1 ;

    private  int type;  //event type 
    private int  time; // Holds the time in which the event occurs
    private int departure_time; // Holds the departure time 
    private int wait_time; // Holds the waittime 
    private int name ; // Holds the name of the customer

    //Customer customer= new Customer(time,name); 
 Customer customer= new Customer(time,name,0,0); 
    /**
     * Constructor for objects of class Event
     */
    public Event () { 
    }

    public Event(Customer customer, int time, int what, int name )
    {
        this.customer=customer; 
        this.time= time; 
        this.type= what; 
        this.name = name ; 

    }

    /**
     * compareTo method compares one instance of event to another
     *  @param Event test 
     */
    public int compareTo(Event evt)
    {

        int out= 0; 

        // if (this.time - evt.time < 0) { // Less than 
            // out =-1; 
        // } 
        // else if (this.time - evt.time >0) { // Greater than 
            // out= 1 ; 
        // } 
        // else if (this.time - evt.time == 0 ){ // Equal To 
            // out=  0 ; 
        // } 
        
        
                if (this.time - evt.getTime() < 0) { // Less than 
            out =-1; 
        } 
        else if (this.time - evt.getTime() >0) { // Greater than 
            out= 1 ; 
        } 
        else if (this.time - evt.getTime() == 0 ){ // Equal To 
            out=  0 ; 
        } 
        return out ; 
    }

    /**
     *  sets the time of the event 
     *
     * @param set_time the set time 
     */
    public void setTime(int set_time) 
    { 
        time= set_time ; 
    } 

    /**
     *   gets time of the event 
     *
     * @return time
     */
    public int getTime() { 
        return time ; 
    } 

    /**
     *  get the name of the Event 
     *
     * @return name
     */
    public int getName() { 
        return name ; 
    } 

    /**
     *  get the type of the Event 
     *
     * @return type
     */
    public int getType() { 
        return type ; 
    } 

    /**
     *  sets the type of the Event 
     *
     * @param int set_type
     */
    public void setType(int set_type) { 
        // Changing the type of the event to type passed into the method
        type = set_type ; 
    } 

    /**
     *  returns the departure of time of the costumer 
     *
     * @param  current_time 
     *  @param int time_served 
     * @return    depart_time
     */
    public int departTime(int current_time,int time_served)
    {
        // Calculating departure time 
        departure_time= current_time + time_served ; 

        return departure_time;       

    }

    /**
     *  returns wati time 
     *
     * @param current_time  current time 
     * @param arrival_time arrival time 
     * @return    wait time 
     */
    public int waitTime(int current_time, int arrival_time) { 

        wait_time = current_time - arrival_time;  

        return wait_time;         
    }

    /**
     *  returns servingTime 
     *  
     * @return   serving time
     */
    public int getServingTime(){
        return this.customer.getServingTime();
    }

    /**
     *  returns profit 
     *  
     * @return   profit 
     */
    public int getProfit()
    {
        return this.customer.getProfit(); 

    }
    /**
     *  returns profit 
     *  
     * @return   profit 
     */
    public String  getStatus()
    {
       if(this.type==0) return "Arrives";
       else return "Leaves";

    }
}
