// Name:
// Date:
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
     
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.disembark(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
    
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
    
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
          
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
      
        //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
    /* enter your code below */
   private double balance;
   private int zone;
   private boolean presented;
   private String stop;
   public SmartCard(double b)
   {
      balance = b;
   }
   public void addMoney(double d)
   {
      balance += d;
   }
   public String getBalance()
   {
      return ("" + balance);
   }
   public boolean isBoarded()
   {
      return presented;
   }
   public void board(Station s)
   {
      if(presented == true)
      {
         System.out.println("Error:  Already boarded?!");
         return;
      }
      else if(balance < 0.5)
      {
         System.out.println("Insuffient funds to board. Please add more money.");
         return;
      }
      else
      {
         zone = s.getZone();
         presented = true;
         stop = s.getName();
         System.out.println("Boarded at " + s.getName() + ". SmartCard has " + df.format(balance));
      }
   }
   public double cost(Station s)
   {
      double cost = 0.5 + (Math.abs(zone - s.getZone())*0.75);
      return cost;
         
   }
   public void disembark(Station s)
   {
      double price = cost(s);
      if(balance >= price && presented == true)
      {
         balance = balance - price;
         System.out.println("From " + stop + " to " + s.getName()+ " costs " + df.format(price) + ". SmartCard has " + df.format(balance));
         presented = false;
      }
      else if(balance < price && presented == true)
      {
         System.out.println("Insuffient funds to exit. Please add more money.");
         return;
      }
      else
      {
         System.out.println("Error:  did not board?!");
         return;
      }
   }
      
}
   
//Note Station is not a public class.  Why?
class Station
{
   private String name;
   private int zone;
   public Station(String n, int z)
   {
      name = n;
      zone = z;
   }
      
   public String getName()
   {
      return name;
   }
   public int getZone()
   {
      return zone;
   }
      
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/