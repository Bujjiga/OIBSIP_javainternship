package ATM;

import java.util.Scanner;
import java.sql.*;
class BankAccount {
	
	String Ac_HolderName;
	String userName;
	String password;
	float balance = 1000f;
	int transactions = 0;
	String transactionHistory = "";
	public static Connection conn1 = null;
	
	
	
	public void connection() throws SQLException {
		 String url1 = "jdbc:mysql://localhost:3306/hari";
         String user = "root";
         String pass = "abhi";

         conn1 = DriverManager.getConnection(url1, user, pass);
         if (conn1 != null)
             System.out.println("Connected to the database test1");
	}
	
	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Your Name - ");
		this.Ac_HolderName = sc.nextLine();
		System.out.print("\nEnter Your Username - ");
		this.userName = sc.nextLine();
		System.out.print("\nEnter Your Password - ");
		this.password = sc.nextLine();	
		try {
           
            Statement st=conn1.createStatement();
            
            String insertQuery = "INSERT INTO bankcustomerdetails VALUES (?,?,?,?)";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = conn1.prepareStatement(insertQuery);

            // Set values for the placeholders
            
            preparedStatement.setString(1,Ac_HolderName);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, password);
            preparedStatement.setFloat(4, balance);


            // Execute the insert query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check the number of rows affected
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
		System.out.println("\n-----------Registration completed..kindly login-----------");
	}
	
	public boolean login() throws SQLException {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<3;i++) {
		    System.out.print("\nEnter Your Username - ");
		    String Username = sc.nextLine();
		    System.out.println("\n Be Carefull While you are using Entering Password1\n");
		    System.out.print("\nEnter Your Password - ");
		    String Password = sc.nextLine();
		    
		    try {
			   Statement st=conn1.createStatement();
		       String insertQuery = "select userName,password from bankcustomerdetails where password=? AND userName=?";

      //  Create a PreparedStatement
		       PreparedStatement prepared = conn1.prepareStatement(insertQuery);

        // Set values for the placeholders
        
               prepared.setString(1, Password);
               prepared.setString(2, Username);
      
		       ResultSet res = prepared.executeQuery();
		
		       if(res.next()) {
        	      System.out.print("\nLogin successful!!");
				   isLogin = true;
              } 
              else {
        	     System.out.print("\n----YOU ENTERED A WRONG PASSWORD OR WRONG USERNAME-------");
              }
		     }
		  catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
		if(isLogin) {
			break;
		}
		}
		if(!isLogin) {
			System.out.println("\n  ---You entered soo many wrong attempts. Try after some time------");
		}
	
		return isLogin;
	}
	
	public void withdraw() {
		
		System.out.print("\nEnter amount to withdraw - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {
			
			if ( balance >= amount ) {
				transactions++;
				balance -= amount;
				System.out.println("\nWithdraw Successfully");
				String str = amount + " Rs Withdrawed\n";
				transactionHistory = transactionHistory.concat(str);
				
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void deposit() {
		
		System.out.print("\nEnter amount to deposit - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		
		try {
			if ( amount <= 100000f ) {
				transactions++;
				balance += amount;
				System.out.println("\nSuccessfully Deposited");
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nSorry...Limit is 100000.00");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void transfer() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Receipent's Name - ");
		String receipent = sc.nextLine();
		System.out.print("\nEnter amount to transfer - ");
		float amount = sc.nextFloat();
		
		try {
			if ( balance >= amount ) {
				if ( amount <= 50000f ) {
					transactions++;
					balance -= amount;
					System.out.println("\nSuccessfully Transfered to " + receipent);
					String str = amount + " Rs transfered to " + receipent + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\nSorry...Limit For One Day is 50000.00");
				}
			}
			else {
				System.out.println("\nInsufficient Funds");
			}
		}
		catch ( Exception e) {
		}
	}
	
	public void checkBalance() {
		System.out.println("\n" + balance + " Rs");
	}
	
	public void transHistory() {
		if ( transactions == 0 ) {
			System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + transactionHistory);
		}
	}
}




package ATM;
import java.sql.SQLException;
import java.util.Scanner;
public class AtmInterface {
	
	
	public static int input(int limit) {
		int input = 0;
		boolean flag = false;
		
		while ( !flag ) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;
				
				if ( flag && input > limit || input < 1 ) {
					System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.println("Enter only integer ");
				flag = false;
			}
		};
		return input;
	}
	
	
	public static void main(String[] args) throws SQLException {

		
		System.out.println("\n**********WELCOME TO STATE BANK  ATM SYSTEM**********\n");
        BankAccount b = new BankAccount();
		
		b.connection();
		while(true) {
		System.out.println("1.Register \n2.Login \n 3.exit");
		System.out.print("Enter Your Choice - ");
		int choice = input(3);
		

		if ( choice == 1 ) {
			b.register();
		}
		else if(choice==2) {
					if (b.login()) {
						System.out.println("\n\n**********WELCOME BACK ARE LOGGED IN **********\n");
					
						boolean isCompleted = false;
						while (!isCompleted) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int choice3 = input(6);
							switch(choice3) {
								case 1:
								b.withdraw();
								break;
								case 2:
								b.deposit();
								break;
								case 3:
								b.transfer();
								break;
								case 4:
								b.checkBalance();
								break;
								case 5:
								b.transHistory();
								break;
								case 6:
									System.out.println("_______Logged out from your Account________");
								isCompleted = true;
								break;
							}
						}
					}
			
				else {
					
					System.exit(0);
				}
			}
		
		else if(choice==3){
			System.out.println("Thank you Visit Our Bank Again");
			System.exit(0);
		}
		
		}
		
	}
}
