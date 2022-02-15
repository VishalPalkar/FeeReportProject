package project.report.fee;

public class AdminDaoImpl implements AdminDao {
AdminLogin al=new AdminLogin();
	@Override
	public boolean checkAdminLogin(String Username, String Password) {
		al.setAdmin_name("tom");
		al.setAdmin_pass("jerry");
		boolean c=false; 
		if(Username.equals(al.getAdmin_name())&&Password.equals(al.getAdmin_pass()))
			c=true;
		else
			c=false;
		return c;
		
	}

}
