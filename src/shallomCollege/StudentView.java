package shallomCollege;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentView {
	
	
	SimpleIntegerProperty no;
	SimpleIntegerProperty id;
	SimpleIntegerProperty yr;
	SimpleStringProperty fName;
	SimpleStringProperty lName;
	SimpleStringProperty department;
	
	
	StudentView(int no , int id , int yr , String fName , String lName , String dept){
		
	   this.no = new SimpleIntegerProperty(no);
	   this.id = new SimpleIntegerProperty(id);
	   this.yr = new SimpleIntegerProperty(yr);
	   this.fName = new SimpleStringProperty(fName);
	   this.lName = new SimpleStringProperty(lName);
	   this.department = new SimpleStringProperty(dept);
	   
	}
	

	//getters
	
	public int getNo() {
		return no.get();
	}
	public int getYr() {
		return yr.get();
	}
	public int getId() {
		return id.get();
	}
	public String getFName() {
		return fName.get();
	}
	public String getLName() {
		return lName.get();
	}
	public String getDepartment() {
		return department.get();
	}
	
	//setters
	
	public void setNo(int no) {
		this.no.set(no);
	}
	public void setYr(int yr) {
		this.yr.set(yr);
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public void setFName(String fName) {
		this.fName.set(fName);
	}
	public void setLName(String lName) {
		this.setLName(lName);
	}
	public void setDepartment(String dept) {
		this.department.set(dept);
	}
	

	
}
