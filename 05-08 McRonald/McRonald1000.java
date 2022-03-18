// Name: Pratik Nadipelli 
// Date: 1/17/2019

import java.util.*;

public class McRonald1000
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   
   public static void main(String[] args)
   {
      Queue<Customer> line = new LinkedList<Customer>();
      display(line,TIME);
   }
   
   //public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      int randentry,randorder,wait;
      int t = TIME;
      int maxQueue = 0;
      int queuel = 0;
      int maxWait = 0;
      int sumWait = 0;
      int customers = 0;
      for(int j = 0; j < 1000; j++)
      {
      for(int i = 0; i < TIME; i++)
      {
         
         randentry = (int)(5*Math.random()+1);
         if(randentry == 1)
         {
            q.add(new Customer(i));
            customers++;
            queuel++;
            maxQueue = Math.max(queuel,maxQueue);
         }
         if(!q.isEmpty())
         {
         
            if(q.peek().getOrderTime() == 0)
            {
               randorder = (int)(6*Math.random()+2);
               q.peek().setOrderTime(i , randorder); 
              
            }
            else if(q.peek().getOrderArrivalTime() == i)
            {
               //q.peek().setWaitTime(i - q.peek().getArrivalTime());
               wait = i - q.remove().getArrivalTime()-1;
               maxWait = Math.max(wait, maxWait);
               sumWait += wait;
               queuel--;
            }
         }
         //System.out.println(i + ": " + q.toString());
         
      }
      while(!q.isEmpty())
      {
         
         
         if(q.peek().getOrderTime() == 0)
         {
            randorder = (int)(6*Math.random()+2);
            q.peek().setOrderTime(t , randorder);   
         }
         else if(q.peek().getOrderArrivalTime() == t)
         {
            //q.peek().setWaitTime(t - q.peek().getArrivalTime());
            wait = t - q.remove().getArrivalTime()-1;
            maxWait = Math.max(wait, maxWait);
            sumWait += wait;
            queuel--;
         }
         //System.out.println(t + ": " + q.toString()); 
         t++;  
      }
         t = TIME;
      }
      double avgtime = (new Double(sumWait))/customers;
      System.out.println("Total Customers served = " + customers );
      System.out.println("Average Wait Time = " + avgtime );
      System.out.println("Longest Wait Time = " + maxWait);
      System.out.println("Longest Queue = " + (maxQueue - 1));
      
   }
}

class Customer      // if you want a Customer class
{
   private int atime;
   private int otime;
   private int oatime;
   public Customer(int arrival)
   {
      atime = arrival;
      otime = 0;
      oatime = 0;
   
   }
   public void setOrderTime(int o , int a)
   {
      otime = o;
      oatime = o + a;
   }
   public int getArrivalTime()
   {
      return atime;
   }
   public int getOrderTime()
   {
      return otime;
   }
   public int getOrderArrivalTime()
   {
      return oatime;
   }
   public String toString()
   {
      return atime + "";
   }
}