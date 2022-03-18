// Name: Pratik Nadipelli        Date: 9/18/18
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      System.out.println(""+numEntries);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
      //for (int i = 0; i < cemetery.length; i++) 
        //System.out.println(cemetery[i]);
      //System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /* Counts and returns the number of entries in File f.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(f);
         int count = 0;
         String[] s = new String[700];
         int i = 0;
         while(infile.hasNextLine())
         {
            s[i] = infile.nextLine();
            count++;
         }
         return count;
      }
      catch(Exception e)
      {
         System.out.println("File not found");
         return -1;   
      }
      
   }

   /* Reads the data.
      Fills the array with Person objects.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(f);
         String[] lines = new String[num];
         for(int i = 0; i<num;i++)
         {
            lines[i] = infile.nextLine();
            
         }
         Person[] people = new Person[num];
         //int j = 0;
         for(int i = 0; i<num;i++)
         {
           people[i] = makeObjects(lines[i]);
           
         }
         return people;
      }   
      catch(IOException e)
      {
         System.out.println("File not found");
         return null;   
      }
      
      
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)
   {
      String name = entry.substring(0,25).trim();
      String bdate = entry.substring(25,36).trim();
      String age = entry.substring(36,40).trim();
      Person p = new Person(name,bdate,age);
      return p;
      
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int index = 0;
      for(int i = 0; i < arr.length ; i++)
      {
         if(Double.parseDouble(arr[i].getAge())<Double.parseDouble(arr[index].getAge()))
         {
            index = i;
         }
         
      }
      return index;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int index = 0;
      for(int i = 0; i < arr.length ; i++)
      {
         if(Double.parseDouble(arr[i].getAge())>Double.parseDouble(arr[index].getAge()))
         {
            index = i;
         }
         
      }
      return index;
   }        
} 

class Person
{
   
   /* private fields  */
   private DecimalFormat df = new DecimalFormat("0.0000");
   private String name;
   private String burialdate;
   private double deathAge;
   private String address;   
   /* a three-arg constructor  */
   public Person(String n, String bd, String da )
   {
      name = n;
      burialdate = bd;
      deathAge = calculateAge(da);
   }
   /* any necessary accessor methods */
   public String getName()
   {
      return name;
   }
   public String getAge()
   {
      return df.format(deathAge);
   } 
   
   public double calculateAge(String a)
   {
      double age = 0;
      if(a.contains("d"))
      {
         age = Double.parseDouble(a.replaceAll("d",""));
         age = age/365;
         df = new DecimalFormat("0.0000");
      }
      else if(a.contains("w"))
      {
         age = Double.parseDouble(a.replaceAll("w",""));
         age = age/52;
         df = new DecimalFormat("0.0000");
         
      }
      else
      {
         age = Double.parseDouble(a);
         df = new DecimalFormat("0.0");
        
      }
      return age;
   }
}