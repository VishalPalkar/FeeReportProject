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

public class AccountantDaoImpl implements AccountantDao {

	Accountant accountant=new Accountant();
	Scanner sc=new Scanner(System.in);
	ArrayList<Accountant> accnt=new ArrayList<Accountant>();
	File file=new File("/home/vishal/eclipse-workspace/Fee Report Project/AccountantData.txt");
	ObjectOutputStream os=null;
	ObjectInputStream is=null;
	ListIterator<Accountant> li=null;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void addAccountant() {
		System.out.println(file.exists());
		System.out.println("Please provide following details.");
		System.out.println("Name:");
		String aname = sc.next();
		accountant.setAName(aname);
		//accountant.setAName(sc.next());
		System.out.println("Password:");
		String apass = sc.next();
		accountant.setAPassword(apass);
		//accountant.setAPassword(sc.next());
		System.out.println("Email:");
		String aemail = sc.next();
		accountant.setAEmail(aemail);
		//accountant.setAEmail(sc.next());
		System.out.println("Contact:");
		int acontact = sc.nextInt();
		accountant.setAContact(acontact);
		//accountant.setAContact(sc.nextInt());
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

	@SuppressWarnings("unchecked")
	@Override
	public void viewAccountant() {
		try
		{
			is=new ObjectInputStream(new FileInputStream(file));
			accnt=(ArrayList<Accountant>)is.readObject();
			is.close();
		}
		catch(Exception e){e.printStackTrace();}
		for(int i=0;i<accnt.size();i++)
		{
			System.out.print("Name = "+accnt.get(i).getAName()+"\t");
			System.out.print("Password = "+accnt.get(i).getAPassword()+"\t");
			System.out.print("Email = "+accnt.get(i).getAEmail()+"\t");			
			System.out.print("Contact = "+accnt.get(i).getAContact()+"\n");
		}
		System.out.println();

	}

	@Override
	public boolean checkLoginA(String Name, String Password) {
		boolean a=false;
		try {
			is=new ObjectInputStream(new FileInputStream(file));
		
		
			accnt=(ArrayList<Accountant>)is.readObject();
			
			is.close();
			li=accnt.listIterator();
			while(li.hasNext())
			{
				Accountant b=(Accountant)li.next();
				if(b.getAName().equals(Name))
					if(b.getAPassword().equals(Password))
						a=true;
				else
				{
					a=false;
					System.out.println(b.getAName());
					System.out.println(b.getAPassword());
				}
				
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
	
	
		
		return a;
	}

}
