package shallomCollege;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class YourCourses {

	
	SimpleStringProperty yourCoursecoCode;

	SimpleStringProperty yourCoursecoName;

	SimpleIntegerProperty yourCourseyrs;

	SimpleIntegerProperty yourCoursesems; 

	SimpleStringProperty yourCoursedept;

	SimpleIntegerProperty yourCoursecredit;
	
	
	public YourCourses(String coCode , String CoName , String dept , int credits , int yrs , int sems) {
		
		this.yourCoursecredit = new SimpleIntegerProperty(credits);
		this.yourCoursecoCode = new SimpleStringProperty(coCode);
		this.yourCoursecoName = new SimpleStringProperty(CoName);
		this.yourCourseyrs = new SimpleIntegerProperty(yrs);
		this.yourCoursesems = new SimpleIntegerProperty(sems);
		this.yourCoursedept = new SimpleStringProperty(dept); 
		
	}
	
	
//getters
	
	public String getYourCoursecourseCode() {
		return yourCoursecoCode.get();
	}
	public String getYourCoursecourseName() {
		return yourCoursecoName.get();
	}

	public String getYourCoursedepartment() {
		return yourCoursedept.get();
	}

	public int getYourCoursecredit() {
		return yourCoursecredit.get();
	}
	public int getYourCoursesemster() {
		return yourCoursesems.get();
	}
	public int getYourCourseyear() {
		return yourCourseyrs.get();
	}

	
//setters
	
	
	public void setYourCoursecoCode(String coCode) {
		this.yourCoursecoCode.set(coCode);
	}
	public void setYourCoursecoName(String coName) {
		this.yourCoursecoName.set(coName);
	}
	public void setYourCoursedept(String dept) {
		this.yourCoursedept.set(dept);
	}
	public void setYourCoursesems(int sems) {
		this.yourCoursesems.set(sems);
	}
	public void setYourCoursecredit(int coCredit) {
		this.yourCoursecredit.set(coCredit);
	}
	public void setYourCourseyrs(int yr) {
		this.yourCourseyrs.set(yr);
	}
	
	

	
	
}
