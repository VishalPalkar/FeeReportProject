package project.report.fee;

import java.util.ArrayList;

public interface AccountantDao {

	public boolean addAccountant(Accountant accountant);
	public ArrayList<Accountant> viewAccountant();
	public boolean checkLoginA(String Name, String Password);
}
