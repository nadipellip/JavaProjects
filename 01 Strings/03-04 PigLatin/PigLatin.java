//name:Pratik Nadipelli      date: 9/6/2018  
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      part_1_using_pig();
     // part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println( p );
      }		
   }
   public static String pig(String s)
   {  
      String punct = s.replaceAll("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]","");
      s = s.replaceAll("[.,?!:;]","");
      s = s.replaceAll("\"","");
      char fletter = s.charAt(0);
      boolean isCap = Character.isUpperCase(fletter);
      s = s.substring(0,1).toLowerCase()+s.substring(1);
      int index = -1;
      for(int i = 0;i<s.length();i++)
      {
         if(s.substring(i,i+1).equalsIgnoreCase("a") || s.substring(i,i+1).equalsIgnoreCase("e") || s.substring(i,i+1).equalsIgnoreCase("i") || s.substring(i,i+1).equalsIgnoreCase("o") || s.substring(i,i+1).equalsIgnoreCase("u"))
         {
            index = i;
            break;
         }
         else if(s.substring(i,i+1).equalsIgnoreCase("y") && i > 0)
         {
            index = i;
            break;
         }
         
         
      }
      if(index < 0)
      {
         return "*** NO VOWEL ***";
      }
      if(index == 0)
      {
         s = s+"way";
      }
      else if(s.substring(index-1,index+1).equalsIgnoreCase("qu"))
      {
         s = s.substring(index+1)+s.substring(0,index+1)+"ay";
      }
      else
      {
         s = s.substring(index)+s.substring(0,index)+"ay";
      }
      if(isCap == true)
      {
         s = s.substring(0,1).toUpperCase()+s.substring(1);
      }
      if(punct.length() >0)
      {
         if(String.valueOf(punct.charAt(0)).equals("\""))
         {
            
            s = "\""+s+punct.replaceAll("\"","")+"\"";
         }
         else
         {
            s = s+punct;
         }
      }

      return s;
   }

   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	
      /*  Enter your code here.  Try to preserve lines and paragraphs. ***/
   
   
   
   
   
      outfile.close();
      infile.close();
   }
}
