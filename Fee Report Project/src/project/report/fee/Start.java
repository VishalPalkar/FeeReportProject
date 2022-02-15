package project.report.fee;

import java.util.Scanner;

class Run{
	static AccountantDao ac=new AccountantDaoImpl();
    static StudentDao st=new StudentDaoImpl();
    static AdminDao ad= new AdminDaoImpl();
    Scanner sc=new Scanner(System.in);  
    
    public void login()
    {
    	int a=0;
    while(a==0)
    {
    	System.out.println("Enter 1 for Admin Login");
    	System.out.println("Enter 2 for Accountant Login");
    	System.out.println("Enter 3 to Exit");
    	int t=sc.nextInt();
    	if(t==1)
    	{
    		loginAdmin();
    	}	
    	else if(t==2)
    	{
    		loginAccountant();
    	}
    	else
    	{
    	a++;
    	}	
    } 	
    }
    
    public void loginAdmin()
    {
    	
    	System.out.println("Please Enter Admin Name");
		String name=sc.next();
		System.out.println("Please Enter Admin Password");
		String password=sc.next();
		if(ad.checkAdminLogin(name, password))
		{
			int j=0;
			while(j==0)
			{
				System.out.println("\nPlease choose an operation \n");
				System.out.println("Enter 1 to Add Accountant");
				System.out.println("Enter 2 to Display Accountant");
				System.out.println("Enter 3 to exit");
				int k=sc.nextInt();
				if(k==1)
					ac.addAccountant();
				else if(k==2)
					ac.viewAccountant();
				else if(k==3)
					j++;
				else
					System.out.println("Please enter valid choice.");
			}
			
		}
		else
			System.out.println("Invalid Name Or Password.");
	
    }
    
    public void loginAccountant()
    {
    	System.out.println("Please Enter Accountant name.");
		String name=sc.next();
		System.out.println("Please Enter Accountant password.");
		String password=sc.next();
		if(ac.checkLoginA(name, password))
		{
			int j=0;
			while(j==0)
			{
				System.out.println("\n Please choose an operation \n");
				System.out.println("Enter 1 to Add Student.");
				System.out.println("Enter 2 to Display student.");
				System.out.println("Enter 3 to Edit student.");
				System.out.println("Enter 4 to get fees details.");
				System.out.println("Enter 5 to exit.");
				int k=sc.nextInt();
				if(k==1)
					st.addStudentFile();
				else if(k==2)
					st.displayStudentFile();
				else if(k==3)
					st.editStudentFile();
				else if(k==4)
					st.feeStudentFile();
				else if(k==5)
					j++;
				else
					System.out.println("Please enter valid choice.");
			}
			
		}
		else
			System.out.println("Invalid Name Or Password");
    	
    	
    }     	
}
public class Start {
	public static void main(String[] args) {
		Run r=new Run();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 to work on file\nEnter 2 to work on database");
		int x=0;
		while(x==0) {
		int b=sc.nextInt();
		//sc.close();
		if(b==1)
		{
			r.login();
			x++;
	    }
		else if((b==2))
		{
			System.out.println("Database module yet to be completed.");
			x++;
		}
		else
		{
			System.out.println("wrong choice..!\n try again.");
			
		}
		}
	}
}
