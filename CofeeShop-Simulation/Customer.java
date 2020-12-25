
/**
 * Costumer Class 
 *
 * @author Tafita Rakotozandry 
 * @version 1
 */
public class Customer  
{

    private  int arrival_time; 
    private  int wait_time=0;  
    private  int departure_time; 
    private  int name ; 
    private  int servingTime; 
    private  int profit; 

    /**
     * Constructor for objects of class Customer
     * 
     * @param arrival_time the time that the costumer arrived 
     * @param name the name of the costumer 
     */
    public Customer( int arrival_time, int name,int serving_time, int profit )
    {
        this.arrival_time = arrival_time ; 
        this.name = name; 
        this.servingTime=serving_time; 
        this.profit=profit; 
    }

    /**
     *  set the departure time of the costumer 
     *
     * @param time departure time 
     */
    public void setDepartTime(int time)
    {
        departure_time = time;       
    }

    /**
     *  set the waiting time of the costumer 
     *
     * @param time wait time 
     */
    public void setWaitTime(int time)
    {
        wait_time = time; 
    }

    /**
     * get the waiting time of the costumer 
     *
     * @return int wait_time
     */
    public int getWaitTime()
    {
        return wait_time; 

    }

    /**
     *  set the waiting time of the costumer 
     *
     * @param time wait time 
     */
    public void setProfit(int profit)
    {
        this.profit=profit; 
    }

    /**
     * get the waiting time of the costumer 
     *
     * @return int wait_time
     */
    public int getProfit()
    {
        return this.profit; 
    }

    /**
     *  set the waiting time of the costumer 
     *
     * @param time wait time 
     */
    public void setServingTime(int time)
    {
        this.servingTime=time; 
    }

    /**
     * get the waiting time of the costumer 
     *
     * @return int wait_time
     */
    public int getServingTime()
    {
        return this.servingTime; 
    }
    /**
     * get the waiting time of the costumer 
     *
     * @return int wait_time
     */
    public int getArrivalTime()
    {
        return this.arrival_time; 
    }

} 
