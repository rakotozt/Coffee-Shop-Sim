import java.util.*; 
/**
 * Contains all the different functions to make calculation such as netProfit,overflow percentage 
 *
 * @author Tafita Rakoto
 * @version 1
 */
public class Calculation
{

    /**
     * Constructor for objects of class Calculation
     */
    public Calculation()
    {

    }

    /**
     *  calulates net profit 
     * 
     * @param total_profit total profit 
     * @param  c  cost 
     * @param  s salary of the cashiers 
     * @return netprofit 
     * 
     */
    public float calculateNetProfit(int total_profit , float c, int s)
    {
        float netprofit = total_profit - ((c*s)) ; 
        return netprofit ; 

    }

    /**
     * Calculate Total Profit 
     * 
     * @param  p profit 
     * @param  num_of_served number of costumer served 
     * @return totalprofit 
     * 
     */
    public float calculateTotalProfit ( float p , int num_of_served) { 
        float  totalprofit = p*num_of_served; 

        return totalprofit; 
    }

    /**
     * calulates the total cost 
     * 
     * @param c number of cashiers 
     * @param s number of salary  
     * @return totalcost 
     * 
     */
    public float calculateTotalCost ( int s, float c)  {
        float totalcost = s*c ;
        return totalcost; 
    } 

    /**
     * calulates the the overflow rate of the coffee shop
     * 
     * @param int num_of_served @param int overflow
     * @return overflow_rate
     * 
     */
    public float calculateOverFlow ( int num_of_served, int overflow)  {

        float overflow_rate = ((float)(overflow)/(overflow + num_of_served))*100  ; 

        return overflow_rate ; 
    }

    /**
     *returns the max waiting time from the arraylist of waitimes 
     * 
     * @param ArrayList <Customer> waittime
     * @return max_wait_time
     * 
     */
    public int maxWaitTime( ArrayList<Customer> waittime ) { 

        int max_wait_time = 0 ; 
        for (int i=0; i<waittime.size()-1; i++) {

            if(max_wait_time < waittime.get(i).getWaitTime()) {

                max_wait_time = (waittime.get(i)).getWaitTime();
            }
        }
        return max_wait_time;

    } 

    /**
     * returns the average waittime 
     * 
     * @param ArrayList <Customer> waittime
     * @return avg_wait_time
     * 
     */
    public int avgWaitTime( ArrayList<Customer> waittime ) { 
        int total = 0; 
        for ( int i =0 ; i<waittime.size() ; i++) { 
            total +=  (waittime.get(i)).getWaitTime();
        }

        int  avg_wait_time = total / waittime.size() ; 

        return avg_wait_time ; 
    }
}
