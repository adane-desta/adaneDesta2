package shallomCollege;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CurrentCourses {
	
	 
	SimpleStringProperty CourseCode;
	SimpleStringProperty CourseName;
	SimpleStringProperty InstructorName;
	SimpleIntegerProperty Credit; 
	SimpleStringProperty Prerequest;
	SimpleIntegerProperty NumericOrder;
	 
	
	public CurrentCourses(String courseC,String CourseN,String InstructorName,int credits,String Prerequest, int numericOrder) {
		this.CourseCode = new SimpleStringProperty(courseC);
		this.CourseName = new SimpleStringProperty(CourseN);
		this.InstructorName = new SimpleStringProperty(InstructorName);
		this.Credit = new SimpleIntegerProperty(credits);
		this.Prerequest = new SimpleStringProperty(Prerequest);
		this.NumericOrder = new SimpleIntegerProperty(numericOrder);
	}
	
	
	
	//getters
	
	public String getCourseCode() {
		return CourseCode.get();
	}
	public String getCourseName() {
		return CourseName.get();
	}
	public String getInstructorName() {
		return InstructorName.get();
	}
	public int getCredit() {
		return Credit.get();
	}
	public String getPrerequest() {
		return Prerequest.get();
	} 
	public int getNumericOrder() {
		return NumericOrder.get();
	}
	
	 
	//setters
	
	public void setCourseCode(String courseCode) {
		this.CourseCode.set(courseCode);
	}
	public void setCourseName(String CourseName) {
		this.CourseName.set(CourseName);
	}
	public void setInstructorName(String InstructorName) {
		this.InstructorName.set(InstructorName);
	}
	public void setCredit(int credit) {
		this.Credit.set(credit);
	}
	public void setPrerequest(String Prerequest) {
		this.Prerequest.set(Prerequest);
	}
	public void setNumericOrder(int numericOrder) {
		this.NumericOrder.set(numericOrder);
	}

}
