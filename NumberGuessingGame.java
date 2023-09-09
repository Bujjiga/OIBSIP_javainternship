package NumberGuessing;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class NumberGuessingGame{
	public int random;
	public int assumption;
	public static int no_Guesses = 0;
	public static Scanner sc = new Scanner(System.in);
	public static  List<Double> Score = new ArrayList<>();;
	public static int ar[]=new int[3];
	void genarate(){
        Random rand = new Random();
        random = rand.nextInt(100);
        
	}
	
	public double totalScore() {
		double sum = 0.0;
		for(Double score : Score)
			sum += score;
		return sum;
	}
	
	 void UserInput() {
		
		System.out.println("Guess the number");
		
		assumption = sc.nextInt();
	}
	
	 boolean IsTrueNumber(int a) {
	    
		if(assumption == random ) {
			System.out.format("Yes you get it right , it was %d \nYou guessed the Number in %d chances",assumption,a);
		    return true;
		}
		else if( assumption < random) {
			System.out.println(" It's Too low ...");
		}
		else if(assumption > random) {
			System.out.println("It's Too high ...");
		}
		return false;
	}

	 public static void displayScoreBoard(int b,int y) {
   Double d=( double)(no_Guesses-b)/no_Guesses;
		 if(y==1) {
		 System.out.println("Your Score for this game is:::"+d*100);
		 Score.add(d*100);
		 System.out.println("Your MAXIMUM Score Amoung these games are:::"+ findMax());
		 }
		 else {
			 Score.add(d*100);
		 }
	 }
	 public static Double findMax()
	    {
	       
	        Double max = 0.0;
	        for (Double i : Score) {
	            if (max < i) {
	                max = i;
	            }
	        }
	  
	        return max;
	    }

	public static void round(int u ) {
			int a=1,round_no=u;no_Guesses=ar[u];
			g.genarate();
			System.out.println("____________________Your playing "+(u+1)+"round__________________________");
			System.out.println("--------you are having "+(no_Guesses)+" chances-------------");
				boolean b = false;
			    while(!b && a<=no_Guesses) {
	           g.UserInput();
	            b = g.IsTrueNumber(a);
	            a++;
	            if(b==true) {System.out.println("\n your Qualified the"+(round_no+1)+" Round");round_no++;
	               if(round_no==3) {
	            	  round_no-=1;
	                }
	              }
			    }
			     
			    System.out.println(" \n Enter a Option \n 1) View Score \n 2)continue the Game \n 3)exit the Game");
			    
			    System.out.println("\n Choose any one option:: ");
			    
			    int b1=sc.nextInt();
			    if(b1==1) {
			    displayScoreBoard(a-1,b1);
			     round(round_no);
			    }
			    
			    else if(b1==2) {
			    	displayScoreBoard(a-1,b1);
			    	round(round_no);
			    }
			    else  if(b1==3) {
			    	displayScoreBoard(a-1,b1);
			    	return;
			    }
	     }
	public static NumberGuessingGame g = new NumberGuessingGame();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Score.add(0.0);
		
		ar[0]=10;
		ar[1]=7;
		ar[2]=5;
		
		 System.out.println("--------------------"); 
         System.out.println("Welcome to the number game"); 
         System.out.println("1) Play the Number Game");
         System.out.println("2) Exit the Number game"); 
         System.out.println("--------------------"); 
		
            System.out.print("What action would you like to do from the above actions? "); 
            int option= sc.nextInt(); 
            switch (option) { 
                case 1:
                	round(0);
                    break; 
                case 2: 
                	
                    System.out.println("\n"+"Thank you for visiting the game!"); 
                    System.out.println("\n"+"Your total score is : " + g.totalScore());
                    System.exit(1); 
                    break; 
                default: 
                   System.out.println("Kindly Enter a Valid Input");
                   
            } 
             
            System.out.println("\n"+"Your total score is : " + g.totalScore()); 
		    System.out.println("\n"+"Thanks for Playing the game!"); 
		    
         }
}