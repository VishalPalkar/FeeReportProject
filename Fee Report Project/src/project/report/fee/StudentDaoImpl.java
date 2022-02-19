package project.report.fee;

import java.io.File;
import java.io.FileInputStream;
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

public class StudentDaoImpl implements StudentDao {
	
	ArrayList<Student> stdnt=new ArrayList<Student>();
	File file=new File("/home/vishal/eclipse-workspace/Fee Report Project/StudentData.txt");
	ObjectOutputStream os=null;
	ObjectInputStream is=null;
	@Override
	public boolean addStudent(Student student) {
	
		boolean b=false;
		if(FeeReport.check)
		{
		try
		   {
			is=new ObjectInputStream(new FileInputStream(file));
			stdnt=(ArrayList<Student>)is.readObject();
			is.close();
			student.setId(stdnt.size()+1);
			stdnt.add(student);
			os=new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(stdnt);
			os.close();
         b=true;
         }
		catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
		        String sqlq = "INSERT INTO student(name,email,course,fee,paid,due,address,city,state,country,contact) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement st = c.prepareStatement(sqlq);
	            st.setString(1,student.getName());
	            st.setString(2,student.getEmail());
	            st.setString(3,student.getCourse());
	            st.setInt(4,student.getFee());
	            st.setInt(5,student.getPaid());
	            st.setInt(6,student.getDue());
	            st.setString(7,student.getAddress());
	            st.setString(8,student.getCity());
	            st.setString(9,student.getState());
	            st.setString(10,student.getCountry());
	            st.setLong(11,student.getContact());
	            st.executeUpdate();
			}
			catch(Exception e){e.printStackTrace();}
		}
		return b;
	}
		
		
	

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> displayStudent() {
		if(FeeReport.check)
		{
		try
		{   stdnt.clear();
			is=new ObjectInputStream(new FileInputStream(file));
			stdnt=(ArrayList<Student>)is.readObject();
			is.close();
		}
		catch(Exception e)
		{System.out.println(e);}
		}
		else
		{
			try
			{
				stdnt.clear();
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
			    java.sql.Statement selectStmt = c.createStatement();
			    ResultSet rs = ((java.sql.Statement) selectStmt).executeQuery("SELECT * FROM student");
				  while(rs.next()) { 
					  Student student=new Student();
					  student.setId(rs.getInt(1));
					  student.setName(rs.getString(2));
				      student.setEmail(rs.getString(3));
				      student.setCourse(rs.getString(4));
				      student.setFee(rs.getInt(5));
				      student.setPaid(rs.getInt(6));
				      student.setDue(rs.getInt(7));
				      student.setAddress(rs.getString(8));
				      student.setCity(rs.getString(9));
				      student.setState(rs.getString(10));
				      student.setCountry(rs.getString(11));
				      student.setContact(rs.getLong(12));
				      stdnt.add(student);}			
			}
			catch(Exception e){e.printStackTrace();}
		}
		return stdnt;

	}

	@Override
	public boolean editStudent(int id,Student stud) {
		boolean a=false;
		if(FeeReport.check)
		{
			stdnt.clear();
			stdnt=displayStudent();
			for(Student s : stdnt)
				if(id==s.getId())
				{
					s.setName(stud.getName());
					s.setEmail(stud.getEmail());
					s.setCourse(stud.getCourse());
					s.setFee(stud.getFee());
					s.setDue(stud.getDue());
					s.setPaid(stud.getPaid());
					s.setAddress(stud.getAddress());
					s.setCity(stud.getCity());
					s.setState(stud.getState());
					s.setCountry(stud.getCountry());
					s.setContact(stud.getContact());
					
					try {
						os = new ObjectOutputStream(new FileOutputStream(file));
						os.writeObject(stdnt);
						os.close();
						a=true;
					} 
					catch (IOException e) {e.printStackTrace();}
					
				}
			}
		else
		{
			int count=0;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
				String sqlq =("SELECT count(*) FROM student WHERE id=?");
				PreparedStatement st= c.prepareStatement(sqlq);
				st.setInt(1,id);
				ResultSet rs = st.executeQuery();
				rs.next();
				count = rs.getInt(1);
			}
			catch(Exception f){f.printStackTrace();}
			if(count>0)
			{
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
			        String sql = "UPDATE student SET name=?,email=?,course=?,fee=?,paid=?,due=?,address=?,city=?,state=?,country=?,contact=? WHERE id=?";
		            PreparedStatement pst = c.prepareStatement(sql);
		            pst.setString(1,stud.getName());
		            pst.setString(2,stud.getEmail());
		            pst.setString(3,stud.getCourse());
		            pst.setInt(4,stud.getFee());
		            pst.setInt(5,stud.getPaid());
		            pst.setInt(6,stud.getDue());
		            pst.setString(7,stud.getAddress());
		            pst.setString(8,stud.getCity());
		            pst.setString(9,stud.getState());
		            pst.setString(10,stud.getCountry());
		            pst.setLong(11,stud.getContact());
		            pst.setInt(12,id);
		            pst.executeUpdate();
		            a=true;
				}
				catch(Exception f){f.printStackTrace();}
			}
		}
		return a;
		}
		
	

	

	@Override
	public ArrayList<Student> feeStudent() {
		stdnt.clear();
		if(FeeReport.check)
		{
			try {
				@SuppressWarnings("resource")
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
				stdnt=(ArrayList<Student>)ois.readObject();
			} catch (IOException | ClassNotFoundException e) {e.printStackTrace();} 
		}
		else
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
			    java.sql.Statement selectStmt = c.createStatement();
			    ResultSet rs = ((java.sql.Statement) selectStmt).executeQuery("SELECT id,name,due FROM student");
				 while(rs.next()) { 
					 if(rs.getInt(3)>0)
					 {
						 Student s=new Student();
						 s.setId(rs.getInt(1));
						 s.setName(rs.getString(2));
						 s.setDue(rs.getInt(3));
						 stdnt.add(s);
					 }
					 }
			}
			catch(Exception e){System.out.println(e);}
		}
		return stdnt;
	}
	public ArrayList<Student> loadStudent(int id) {
		Student stud=new Student();
		stdnt.clear();
		ArrayList<Student> studentss=new ArrayList<Student>();
		if(FeeReport.check)
		{
			try
			{
				is=new ObjectInputStream(new FileInputStream(file));
				stdnt=(ArrayList<Student>)is.readObject();
				is.close();
				studentss.clear();
				ListIterator<Student> li=stdnt.listIterator();
				while(li.hasNext())
				{
					Student s=(Student)li.next();
					if(id==s.getId())
					{
						studentss.add(s);
					}
				}
			}
			catch(Exception e){e.printStackTrace();}
			
		}
		else
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/feereport","root","password");
				String sqlq =("SELECT * FROM student WHERE id=?");
				PreparedStatement st= c.prepareStatement(sqlq);
				st.setInt(1,id);
				ResultSet rs = st.executeQuery(); 
				 while(rs.next()) { 
					 stud.setId(rs.getInt(1));
					  stud.setName(rs.getString(2));
				      stud.setEmail(rs.getString(3));
				      stud.setCourse(rs.getString(4));
				      stud.setFee(rs.getInt(5));
				      stud.setPaid(rs.getInt(6));
				      stud.setDue(rs.getInt(7));
				      stud.setAddress(rs.getString(8));
				      stud.setCity(rs.getString(9));
				      stud.setState(rs.getString(10));
				      stud.setCountry(rs.getString(11));
				      stud.setContact(rs.getLong(12));
				      studentss.add(stud);}
			}
			catch(Exception e){e.printStackTrace();}
		}
		return studentss;
	}
}
