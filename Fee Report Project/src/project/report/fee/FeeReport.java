package project.report.fee;
import java.util.ArrayList;

import java.util.Scanner;

public class FeeReport{
	static AccountantDao ac=new AccountantDaoImpl();
    static StudentDao st=new StudentDaoImpl();
    static AdminDao ad= new AdminDaoImpl();
    Scanner sc=new Scanner(System.in);  
    Accountant accountant=new Accountant();
	Student student=new Student();
	ArrayList<Accountant> accnt=new ArrayList<Accountant>();
	ArrayList<Student> stdnt=new ArrayList<Student>();
	public static boolean check;
	FeeReport()
	{
		
		fileOrDB();
	}
	public void Admin_login()
	{
		System.out.println("Enter Admin name");
		String aname=sc.next();
		System.out.println("Enter Admin password");
		String apassword=sc.next();
		if(ad.checkAdminLogin(aname, apassword))
		{
			int j=0;
			while(j==0)
			{
				System.out.println("\n wellcome to Admin Section \n");
				System.out.println("1. Add Accountant");
				System.out.println("2. Display Accountant");
				System.out.println("3. exit");
				int k=sc.nextInt();
				if(k==1)
					{
					// this method add accountant data in database
					String name,password,email,contact;
					System.out.println("\n Enter Accountant information \n");
					
					System.out.println("Enter Name");
					while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")) 
					{System.out.print("\n Invalid re-enter : ");}accountant.setAName(name);
					
					System.out.println("Enter Password");
					while(!((password=sc.nextLine()).trim().length()>4)) {
						System.out.print("\nShort password re-enter : ");}accountant.setAPassword(password);
					
					System.out.println("Enter Email");
					while(!(email=sc.nextLine()).matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
						System.out.print("\nInvalid email re-enter : ");}accountant.setAEmail(email);
					
					System.out.println("Enter contact");
					while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid fee re-enter : ");}accountant.setAContact(Long.parseLong(contact));
					if(ac.addAccountant(accountant))
						System.out.println("Accountant add Succrssfully");
					else
						System.out.println("Accountant not added !!!!");
					}
				else if(k==2)
					{
					accnt.clear();
					accnt=ac.viewAccountant();
					for(Accountant a : accnt)
					{
						System.out.print("Name = "+a.getAName()+"\t");
						System.out.print("Password = "+a.getAPassword()+"\t");
						System.out.print("Email = "+a.getAEmail()+"\t");			
						System.out.print("Contact = "+a.getAContact()+"\n");
					}
					System.out.println();
					}
				else if(k==3)
					j++;
				else
					System.out.println("number is invalid");
			}
			
		}
		else
			System.out.println("Invalid Name Or Password");
	}
		public void Accountant_login()
		{	
			System.out.println("Enter Accountant name");
			String sname=sc.next();
			System.out.println("Enter Accountant password");
			String spassword=sc.next();
			if(ac.checkLoginA(sname, spassword))
			{
				int j=0;
				while(j==0)
				{
					System.out.println("\n wellcome to Accountant Section \n");
					System.out.println("1. Add Student");
					System.out.println("2. Display student");
					System.out.println("3. Load student");
					System.out.println("4. Edit student");
					System.out.println("5. fee student");
					System.out.println("6. exit");
					int k=sc.nextInt();
					if(k==1)
						{
						String name,email,course,fee,paid,address,city,state,country,contact;
						System.out.println("\n Enter student information \n");
						
						System.out.println("Enter name");
						while (!(name = sc.nextLine()).trim().matches("[A-Za-z]+")) {
							System.out.print("Invalid re-enter : ");}student.setName(name);
						
						System.out.println("Enter Email");
						while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
							System.out.print("Invalid e-mail re-enter : ");}student.setEmail(email);
							
						System.out.println("Enter course");
						while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
							System.out.print("Invalid re-enter : ");}student.setCourse(course);
						
						System.out.println("Enter fee");
						while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
							System.out.print("Invalid fee re-enter : ");}student.setFee(Integer.parseInt(fee));
						System.out.println("Enter paid");
						
						student.setPaid(sc.nextInt());
						while (!(paid = sc.nextLine()).trim().matches("[0-9]+")) {
							System.out.print("Invalid paid re-enter : ");}student.setPaid(Integer.parseInt(paid));
				
						student.setDue(student.getFee()-student.getPaid());
						
						System.out.println("Enter address");
						while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
							System.out.print("Invalid characters re-enter : ");}student.setAddress(address);
						
						System.out.println("Enter city");
						while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
							System.out.print("Invalid characters re-enter : ");}student.setCity(city);
						
						System.out.println("Enter state");
						while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
							System.out.print("Invalid characters re-enter : ");}student.setState(state);
						
						System.out.println("Enter country");
						while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
							System.out.print("Invalid characters re-enter : ");}student.setCountry(country);
						
						System.out.println("Enter contact");
						while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
							System.out.print("Invalid contact re-enter : ");}student.setContact(Long.parseLong(contact));
						if(st.addStudent(student))
							System.out.println("Student add Succrssfully");
						else
							System.out.println("Student not added !!!!");
						}
					else if(k==2)
						{
						stdnt.clear();
						stdnt=st.displayStudent();
						for(int i=0;i<stdnt.size();i++)
						{
							System.out.print("id = "+stdnt.get(i).getId()+"\t");
							System.out.print("Name = "+stdnt.get(i).getName()+"\t");
							System.out.print("Email = "+stdnt.get(i).getEmail()+"\t");
							System.out.print("Course = "+stdnt.get(i).getCourse()+"\t");
							System.out.print("Fee = "+stdnt.get(i).getFee()+"\t");
							System.out.print("Paid = "+stdnt.get(i).getPaid()+"\t");
							System.out.print("Due = "+stdnt.get(i).getDue()+"\t");
							System.out.print("Address = "+stdnt.get(i).getAddress()+"\t");
							System.out.print("City = "+stdnt.get(i).getCity()+"\t");
							System.out.print("State = "+stdnt.get(i).getState()+"\t");
							System.out.print("Country = "+stdnt.get(i).getCountry()+"\t");
							System.out.print("Contact = "+stdnt.get(i).getContact()+"\n");
						}
						System.out.println();
						}
					else if(k==3)
						{
						String id;
						System.out.println("Enter student id");
						while (!(id = sc.nextLine()).trim().matches("[0-9]+")) {
							System.out.print("Invalid id re-enter : ");}
						stdnt=st.loadStudent(Integer.parseInt(id));
						if(stdnt.size()>0)
						{
							for(Student s : stdnt)
							{
								System.out.print("id = "+s.getId()+"\t");
								System.out.print("Name = "+s.getName()+"\t");
								System.out.print("Email = "+s.getEmail()+"\t");
								System.out.print("Course = "+s.getCourse()+"\t");
								System.out.print("Fee = "+s.getFee()+"\t");
								System.out.print("Paid = "+s.getPaid()+"\t");
								System.out.print("Due = "+s.getDue()+"\t");
								System.out.print("Address = "+s.getAddress()+"\t");
								System.out.print("City = "+s.getCity()+"\t");
								System.out.print("State = "+s.getState()+"\t");
								System.out.print("Country = "+s.getCountry()+"\t");
								System.out.print("Contact = "+s.getContact()+"\n");
							}
							System.out.println();
						}
						else
							System.out.println("Record not Found");
						}
					else if(k==4)
						{
						stdnt.clear();
						String id;
						System.out.println("Enter student id");
						while (!(id = sc.nextLine()).trim().matches("[0-9]+")) {
							System.out.print("Invalid id re-enter : ");}
						stdnt=st.loadStudent(Integer.parseInt(id));
						if(stdnt.size()>0)
						{
							for(Student s : stdnt)
							{
								System.out.print("id = "+s.getId()+"\t");
								System.out.print("Name = "+s.getName()+"\t");
								System.out.print("Email = "+s.getEmail()+"\t");
								System.out.print("Course = "+s.getCourse()+"\t");
								System.out.print("Fee = "+s.getFee()+"\t");
								System.out.print("Paid = "+s.getPaid()+"\t");
								System.out.print("Due = "+s.getDue()+"\t");
								System.out.print("Address = "+s.getAddress()+"\t");
								System.out.print("City = "+s.getCity()+"\t");
								System.out.print("State = "+s.getState()+"\t");
								System.out.print("Country = "+s.getCountry()+"\t");
								System.out.print("Contact = "+s.getContact()+"\n");
							}
							System.out.println();
							student=stdnt.get(0);
							System.out.println("1. Update Name");
							System.out.println("2. Update Email");
							System.out.println("3. Update Course");
							System.out.println("4. Update Fee");
							System.out.println("5. Update Paid");
							System.out.println("6. Update Address");
							System.out.println("7. Update City");
							System.out.println("8. Update State");
							System.out.println("9. Update Country");
							System.out.println("10. Update Contact");
							String CH;
							System.out.println("Enter option");
							while (!(CH = sc.nextLine()).trim().matches("[0-9]+")) {
								System.out.print("Invalid number re-enter : ");}
							int ch=Integer.parseInt(CH);
							String name,email,course,fee,paid,address,city,state,country,contact;
							switch(ch)
							{
							case 1:{  
								System.out.println("Enter name");
								while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")) 
								{System.out.print("\n Invalid re-enter : ");}student.setName(name);
								break;}
							case 2:{
								System.out.println("Enter Email");
								while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
									System.out.print("Invalid e-mail re-enter : ");}student.setEmail(email);
									break;}
							case 3:{
								System.out.println("Enter course");
								while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
									System.out.print("Invalid re-enter : ");}student.setCourse(course);
							break;}
							case 4:{
								System.out.println("Enter fee");
								while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
									System.out.print("Invalid fee re-enter : ");}student.setFee(Integer.parseInt(fee));
									student.setDue(student.getFee()-student.getPaid());
								break;}
							case 5:{
								System.out.println("Enter paid");
								student.setPaid(sc.nextInt());
								while (!(paid = sc.nextLine()).trim().matches("[0-9]+")|student.getFee()<Integer.parseInt(paid)) {
									System.out.print("Invalid paid re-enter : ");}student.setPaid(Integer.parseInt(paid));
									student.setDue(student.getFee()-student.getPaid());
								break;}
							case 6:{
								System.out.println("Enter address");
								while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
									System.out.print("Invalid characters re-enter : ");}student.setAddress(address);
								break;}
							case 7:{
								System.out.println("Enter city");
								while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
									System.out.print("Invalid characters re-enter : ");}student.setCity(city);
								break;}
							case 8:{
								System.out.println("Enter state");
								while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
									System.out.print("Invalid characters re-enter : ");}student.setState(state);
								break;}
							case 9:{
								System.out.println("Enter country");
								while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
									System.out.print("Invalid characters re-enter : ");}student.setCountry(country);
								break;}
							case 10:{
								System.out.println("Enter contact");
								while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
									System.out.print("Invalid contact re-enter : ");}student.setContact(Long.parseLong(contact));
								break;}
							default:
								System.out.println("invalied number entre");
							}
							if(st.editStudent(Integer.parseInt(id), student))
								System.out.println("Record edit Succrssfully");
						}
						else
							System.out.println("Record not Found");
						
						}
					else if(k==5)
					{
						stdnt.clear();
						stdnt=st.feeStudent();
						for(Student s : stdnt)
						{
							System.out.print("id = "+s.getId()+"\t");
							System.out.print("Name = "+s.getName()+"\t");
							System.out.print("Due = "+s.getDue()+"\t");
							System.out.println();
						}
						
					}
					else if(k==6)
						j++;
					else
						System.out.println("number is invalid");
				}
				
			}
			else
				System.out.println("Invalid Name Or Password");
		}
		public void start()
		{
			int j=0;
			while(j==0)
			{
				System.out.println("1. Admin Login");
				System.out.println("2. Accountant Login");
				System.out.println("3. Exit");
				int i=sc.nextInt();
				if(i==1)
					Admin_login();
				else if(i==2)
					Accountant_login();
				else if(i==3)
					j++;
				else
					System.out.println("number is invalid");
			}
		}
		public void fileOrDB()
		{
			int a=0;
			while(a==0)
			{
				System.out.println("1. Operation on File");
				System.out.println("2. Operation on Database");
				System.out.println("3. Exit");
				int b=sc.nextInt();
				if(b==1)
					{FeeReport.check=true;start();}
				else if(b==2)
					{FeeReport.check=false;start();}
				else if(b==3)
					a++;
				else
					System.out.println("Enter number is invalid");
			}
			
		}
		public static void main(String[] args) {

			new FeeReport();
		}
}

