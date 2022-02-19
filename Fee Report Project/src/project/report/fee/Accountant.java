package project.report.fee;
import java.io.Serializable;
public class Accountant implements Serializable {

	private String AName;
	private String APassword;
	private String AEmail;
	private long AContact;
	public String getAName()
	{
		return AName;
	}
	public void setAName(String aname)
	{
		AName=aname;
		
	}
	public String getAPassword()
	{
	return APassword;	
		
		
	}
	public void setAPassword(String apassword)
	{
		APassword=apassword;
	}
	public String getAEmail()
	{
		return AEmail;
		
	}
	public void setAEmail(String aemail)
	{
		AEmail=aemail;
		
		
	}
	public long getAContact()
	{
		return AContact;
	}
	public void setAContact(long acontact)
	{
		AContact=acontact;
		
	}
	
	
	
	
	
}

