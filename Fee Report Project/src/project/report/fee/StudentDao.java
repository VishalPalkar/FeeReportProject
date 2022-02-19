package project.report.fee;

import java.util.ArrayList;

public interface StudentDao {
	public boolean addStudent(Student student);
	public ArrayList<Student> displayStudent();
	public ArrayList<Student> loadStudent(int id);
	public boolean editStudent(int id,Student stud);
	public ArrayList<Student> feeStudent();
}
