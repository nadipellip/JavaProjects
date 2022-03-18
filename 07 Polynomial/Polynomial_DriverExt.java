 // Name: Pratik Nadipelli   
 // Date: 3/19/2019

import java.util.*;

public class Polynomial_DriverExt
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4.0);
      poly.makeTerm(3, 2.0);
      poly.makeTerm(0, 2.0);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5.0);
      poly2.makeTerm(4, 2.0);
      poly2.makeTerm(0, -3.0);
      poly2.makeTerm(2, 1.0);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      // System.out.println("===========================");
      // Polynomial poly3 = new Polynomial();   // (x+1)
      // poly3.makeTerm(1, 1);
      // poly3.makeTerm(0, 1);
      // System.out.println("Map:  " + poly3.getMap());
      // System.out.println("String:  " + poly3.toString());
   //       
      // Polynomial poly4 = new Polynomial();    // (x-1)
      // poly4.makeTerm(1, 1);
      // poly4.makeTerm(0, -1);
      // System.out.println("Map:  " + poly4.getMap());
      // System.out.println("String:  " + poly4.toString());
      // System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      // System.out.println("==========================="); 
      // Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      // System.out.println("Map:  " + poly5.getMap());  
      // System.out.println(poly5);
   
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Double coef);
   public Map<Integer, Double> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Double> poly;
   public Polynomial()
   {
      poly = new TreeMap<Integer,Double>();
   }
   public Polynomial(Map<Integer, Double> p)
   {
      poly = p;
   } 
   public void makeTerm(Integer exp, Double coef)
   {
      poly.put(exp,coef);
   }
   public Map<Integer, Double> getMap()
   {
      return poly;
   }
   public double evaluateAt(double x)
   {
      double eval = 0;
      for(Integer i: poly.keySet())
      {
         if(poly.get(i) != null)
         {
            eval += poly.get(i) * Math.pow(x,i);
         }
      }
      return eval;
      
   }
   public Polynomial add(Polynomial other)
   {
      Polynomial p = new Polynomial();
      for(Integer i: other.getMap().keySet())
      {
         if(poly.get(i) == null)
         {
            p.getMap().put(i,other.getMap().get(i));
         }
         else
         {
            p.getMap().put(i, poly.get(i) + other.getMap().get(i));
         }   
      }
      return new Polynomial(poly);
   }
   public Polynomial multiply(Polynomial other)
   {
      Polynomial multi = new Polynomial();
      for(int i: poly.keySet())
      {
         for(int j : other.getMap().keySet())
         {
            
            int s = i + j;
            if(multi.getMap().containsKey(i+j))
            {
               multi.makeTerm(s, multi.getMap().get(s) + (poly.get(i) * other.getMap().get(j)));
            }
            else
            {
               multi.makeTerm(s, other.getMap().get(j) * poly.get(i));
            }      
         }
      }
      return multi;
   }
   public String toString()
   {
      String str = "";
      for(Integer i : poly.keySet())
      {
         if(i > 1)
         {
            if(poly.get(i) != 1)
            {
               str = poly.get(i) + "x^"+ i + " + " + str;
            }
            else
            {
               str = "x^"+ i +" + " + str;
            }
         }
         else if(i == 1)
         {
            str = poly.get(i) + "x" + " + "+str;
         }
         else
         {
            str = poly.get(i) + str;   
         }  
      }
      return str;
   }
   
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: -2271.25
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/