package project.report.fee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class StudentDaoImpl implements StudentDao {
	Student student=new Student();
	Scanner sc=new Scanner(System.in);
	ArrayList<Student> stdnt=new ArrayList<Student>();
	File file=new File("/home/vishal/eclipse-workspace/Fee Report Project/StudentData.txt");
	ObjectOutputStream os=null;
	ObjectInputStream is=null;
	ListIterator<Student> li=null;
	@Override
	public void addStudentFile() {
		System.out.println("\n Enter student information \n");
		System.out.println("Enter name");student.setName(sc.next());
		System.out.println("Enter Email");student.setEmail(sc.next());
		System.out.println("Enter course");student.setCourse(sc.next());
		System.out.println("Enter fee");student.setFee(sc.nextInt());
		System.out.println("Enter paid");student.setPaid(sc.nextInt());
		System.out.println("Enter due");student.setDue(sc.nextInt());
		System.out.println("Enter address");student.setAddress(sc.next());
		System.out.println("Enter city");student.setCity(sc.next());
		System.out.println("Enter state");student.setState(sc.next());
		System.out.println("Enter country");student.setCountry(sc.next());
		System.out.println("Enter contact");student.setContact(sc.nextInt());
		
		try
		   {
			is=new ObjectInputStream(new FileInputStream(file));
			stdnt=(ArrayList<Student>)is.readObject();
			stdnt.add(student);
			os=new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(stdnt);
			os.close();
         System.out.println("\n student added in File \n");
         }
		catch(Exception e){e.printStackTrace();}

	}
		
		
	

	@SuppressWarnings("unchecked")
	@Override
	public void displayStudentFile() {
		try
		{
			is=new ObjectInputStream(new FileInputStream(file));
			stdnt=(ArrayList<Student>)is.readObject();
			is.close();
		}
		catch(Exception e)
		{System.out.println(e);}
		for(int i=0;i<stdnt.size();i++)
		{
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

	@Override
	public void editStudentFile() {
		try
		{
			System.out.println("Enter Student name for update");
			student.setName(sc.next());
			is=new ObjectInputStream(new FileInputStream(file));
			stdnt=(ArrayList<Student>)is.readObject();
			is.close();
			li=stdnt.listIterator();
			int a=0;
			while(li.hasNext())
			{
				Student s=(Student)li.next();
				if((s.getName()).equals(student.getName()))
				{
					li.remove();
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
					System.out.print("Contact = "+s.getContact()+"\t");
					System.out.println("\n"); 
					 	
					os=new ObjectOutputStream(new FileOutputStream(file));
					os.writeObject(stdnt);
					os.close();
		            System.out.println("\n student data update in File \n");
		            a++;
				}
				if((s.getName()).equals(student.getName()))
				{
					System.out.println("\n Enter student information for update \n");
					System.out.println("Enter Email");student.setEmail(sc.next());
					System.out.println("Enter course");student.setCourse(sc.next());
					System.out.println("Enter fee");student.setFee(sc.nextInt());
					System.out.println("Enter paid");student.setPaid(sc.nextInt());
					System.out.println("Enter due");student.setDue(sc.nextInt());
					System.out.println("Enter address");student.setAddress(sc.next());
					System.out.println("Enter city");student.setCity(sc.next());
					System.out.println("Enter state");student.setState(sc.next());
					System.out.println("Enter country");student.setCountry(sc.next());
					System.out.println("Enter contact");student.setContact(sc.nextInt());
					try
					   {
						is=new ObjectInputStream(new FileInputStream(file));
						stdnt=(ArrayList<Student>)is.readObject();
						stdnt.add(student);	
						os=new ObjectOutputStream(new FileOutputStream(file));
						os.writeObject(stdnt);
						os.close();
			            System.out.println("\n student data update in File \n");
			            }
					catch(Exception e){System.out.println(e);}
				}
					
			}
			if(a==0)
				System.out.println("This Student Data Not Found");
			
		}
		catch(Exception e)
		{System.out.println(e);}

	}

	@Override
	public void feeStudentFile() {
		try
		{
			is=new ObjectInputStream(new FileInputStream(file));
			stdnt=(ArrayList<Student>)is.readObject();
			is.close();
		}
		catch(Exception e)
		{System.out.println(e);}
		for(int i=0;i<stdnt.size();i++)
		{
			System.out.print("Name = "+stdnt.get(i).getName()+"\t");
			System.out.print("Fee = "+stdnt.get(i).getFee()+"\t");
			System.out.print("Paid = "+stdnt.get(i).getPaid()+"\t");
			System.out.print("Due = "+stdnt.get(i).getDue()+"\t");
			System.out.println("\n");
		}

	}

}
