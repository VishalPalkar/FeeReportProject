package project.report.fee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AccountantDaoImpl implements AccountantDao {

	
	ArrayList<Accountant> accnt=new ArrayList<Accountant>();
	File file=new File("/home/vishal/eclipse-workspace/Fee Report Project/AccountantData.txt");
	ObjectOutputStream os=null;
	ObjectInputStream is=null;
	ListIterator<Accountant> li=null;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAccountant(Accountant accountant) {

		boolean b=false;
		if(FeeReport.check)
		{
		try
		   {
			is=new ObjectInputStream(new FileInputStream(file));
			accnt=(ArrayList<Accountant>)is.readObject();
			is.close();
			accnt.add(accountant);
			os=new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(accnt);
			os.close();
			System.out.println("\n Accountant add in File \n");
      }
		catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
				String sqlq = "INSERT INTO accountant(name,password,email,contact) values (?, ?, ?, ?)";
	            PreparedStatement st = c.prepareStatement(sqlq);
	            st.setString(1,accountant.getAName());
	            st.setString(2,accountant.getAPassword());
	            st.setString(3,accountant.getAEmail());
	            st.setLong(4,accountant.getAContact());
	            b=true;
			}
			catch(Exception k){System.out.println(k);}
		}
		return b;
	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Accountant> viewAccountant() {
		accnt.clear();
		if(FeeReport.check)
		{
		try
		{
			is=new ObjectInputStream(new FileInputStream(file));
			accnt=(ArrayList<Accountant>)is.readObject();
			is.close();
		}
		catch(Exception e){e.printStackTrace();}
		}

		else
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
			    java.sql.Statement selectStmt = c.createStatement();
			    ResultSet rs = ((java.sql.Statement) selectStmt).executeQuery("SELECT * FROM accountant");
			    while(rs.next()) { 
			    	Accountant accountant=new Accountant();
					  accountant.setAName(rs.getString(1));
					  accountant.setAPassword(rs.getString(2));
					  accountant.setAEmail(rs.getString(3));
					  accountant.setAContact(rs.getLong(4));
					  accnt.add(accountant);
			    }    
			}
			catch(Exception e){e.printStackTrace();}
		}
return accnt;
	}

	@Override
	public boolean checkLoginA(String Name, String Password) {
		boolean a=false;
		String databasepass=null;
		if(FeeReport.check)
		{
		try {
			ObjectInputStream is=new ObjectInputStream(new FileInputStream(file));
		
		
			accnt=(ArrayList<Accountant>)is.readObject();
			
			is.close();
			ListIterator<Accountant>
			li=accnt.listIterator();
			while(li.hasNext())
			{
				Accountant b=(Accountant)li.next();
				if(b.getAName().equals(Name))
					if(b.getAPassword().equals(Password))
						a=true;

				
			}
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
				String sqlq =("SELECT password FROM accountant WHERE name=?");
				PreparedStatement st= c.prepareStatement(sqlq);
				st.setString(1, Name);
				ResultSet rs = st.executeQuery(); 
			    while(rs.next())
		    	{    
			    	databasepass=rs.getString(1);   	  
		    	}
			}
			catch(Exception k){k.printStackTrace();}
			if(databasepass.equals(Password))
				a=true;
		}
	
		
		return a;
	}

}
