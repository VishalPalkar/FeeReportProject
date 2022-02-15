package project.report.fee;

import java.io.Serializable;

public class AdminLogin implements Serializable {

private String admin_name;
private String admin_pass;
public String getAdmin_name() {
	return admin_name;
}
public void setAdmin_name(String admin_name) {
	this.admin_name = admin_name;
}
public String getAdmin_pass() {
	return admin_pass;
}
public void setAdmin_pass(String admin_pass) {
	this.admin_pass = admin_pass;
}


}
