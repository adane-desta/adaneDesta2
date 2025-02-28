package shallomCollege;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class Assesment {
	
	
	
	
	SimpleStringProperty CourseCode = new SimpleStringProperty("");
	SimpleStringProperty CourseName = new SimpleStringProperty("");
	SimpleStringProperty StudentName = new SimpleStringProperty("");
	SimpleStringProperty department = new SimpleStringProperty("");
	SimpleStringProperty instructorName = new SimpleStringProperty("");
	SimpleIntegerProperty Credit = new SimpleIntegerProperty(0);
	SimpleIntegerProperty Studentid = new SimpleIntegerProperty(0);
	SimpleIntegerProperty no = new SimpleIntegerProperty(0);
	SimpleIntegerProperty yrs = new SimpleIntegerProperty(0);
	SimpleIntegerProperty Semster = new SimpleIntegerProperty(0);
	SimpleDoubleProperty AssQ1 = new SimpleDoubleProperty(0.0);
	SimpleDoubleProperty AssQ2 = new SimpleDoubleProperty(0.0);
	SimpleDoubleProperty AssMid = new SimpleDoubleProperty(0.0);
	SimpleDoubleProperty AssAsst = new SimpleDoubleProperty(0.0);
	SimpleDoubleProperty AssFinal = new SimpleDoubleProperty(0.0);
	SimpleDoubleProperty AssTotal = new SimpleDoubleProperty(0.0);
	SimpleStringProperty AssStatus = new SimpleStringProperty("");
	SimpleStringProperty AssGrade = new SimpleStringProperty("");
	SimpleIntegerProperty Yr = new SimpleIntegerProperty(0);
	

	
	
	
	
	public Assesment() {
		
	}
	
	public Assesment(int no ,String assCourseCode,String assCourseName, String instName, int  assCredit ,Double assQ1 
			, Double assQ2,Double assMid,Double assAsst , Double assFinal ,Double assTotal , String assStatus , String assGrade)
	{
		this.no = new SimpleIntegerProperty(no);
		this.CourseCode = new SimpleStringProperty(assCourseCode);
		this.CourseName = new SimpleStringProperty(assCourseName);
		this.instructorName = new SimpleStringProperty(instName);
		this.Credit = new SimpleIntegerProperty(assCredit);
		this.AssQ1 = new SimpleDoubleProperty(assQ1);
		this.AssQ2 = new SimpleDoubleProperty(assQ2);
		this.AssMid = new SimpleDoubleProperty(assMid);
		this.AssAsst = new SimpleDoubleProperty(assAsst);
		this.AssFinal = new SimpleDoubleProperty(assFinal);
		this.AssTotal = new SimpleDoubleProperty(assTotal);
		this.AssStatus = new SimpleStringProperty(assStatus);
		this.AssGrade = new SimpleStringProperty(assGrade);
		
		
	}

	public Assesment(int stuId , String stuName ,String assCourseCode,int  assCredit ,String assCourseName ,Double assQ1 
			, Double assQ2,Double assMid,Double assAsst , Double assFinal ,Double assTotal  , String assGrade, String assStatus)
	{
		this.Studentid = new SimpleIntegerProperty(stuId);
		this.CourseCode = new SimpleStringProperty(assCourseCode);
		this.CourseName = new SimpleStringProperty(assCourseName);
		this.StudentName = new SimpleStringProperty(stuName);
		this.Credit = new SimpleIntegerProperty(assCredit);
		this.AssQ1 = new SimpleDoubleProperty(assQ1);
		this.AssQ2 = new SimpleDoubleProperty(assQ2);
		this.AssMid = new SimpleDoubleProperty(assMid);
		this.AssAsst = new SimpleDoubleProperty(assAsst);
		this.AssFinal = new SimpleDoubleProperty(assFinal);
		this.AssTotal = new SimpleDoubleProperty(assTotal);
		this.AssStatus = new SimpleStringProperty(assStatus);
		this.AssGrade = new SimpleStringProperty(assGrade);
		
		
	}
	public Assesment(String assCourseCode,String assCourseName, String instructorName, int  assCredit ,Double assQ1 
			, Double assQ2,Double assMid,Double assAsst , Double assFinal ,Double assTotal , String assStatus , String assGrade)
	{
		
		this.CourseCode = new SimpleStringProperty(assCourseCode);
		this.CourseName = new SimpleStringProperty(assCourseName);
		this.StudentName = new SimpleStringProperty(instructorName);
		this.Credit = new SimpleIntegerProperty(assCredit);
		this.AssQ1 = new SimpleDoubleProperty(assQ1);
		this.AssQ2 = new SimpleDoubleProperty(assQ2);
		this.AssMid = new SimpleDoubleProperty(assMid);
		this.AssAsst = new SimpleDoubleProperty(assAsst);
		this.AssFinal = new SimpleDoubleProperty(assFinal);
		this.AssTotal = new SimpleDoubleProperty(assTotal);
		this.AssStatus = new SimpleStringProperty(assStatus);
		this.AssGrade = new SimpleStringProperty(assGrade);
		
		
	}
	
	public Assesment(int no , String CoCode , String coName , int yr , int semster) {
		this.no = new SimpleIntegerProperty(no);
		this.Yr = new SimpleIntegerProperty(yr);
		this.Semster = new SimpleIntegerProperty(semster);
		this.CourseCode = new SimpleStringProperty(CoCode);
		this.CourseName = new SimpleStringProperty(coName);
		
		
	}
	
	
	public Assesment(int studentid , String studentName , int yr , String courseName , int semster) {
		
		this.Studentid = new SimpleIntegerProperty(studentid);
		this.CourseName = new SimpleStringProperty(courseName);
		this.StudentName = new SimpleStringProperty(studentName);
		this.Credit = new SimpleIntegerProperty(yr);
		this.Semster = new SimpleIntegerProperty(semster);
		
	}
	
	
	
	//getters
	
	public String getCourseCode(){
		return CourseCode.get();
	}
	public String getCourseName(){
		return CourseName.get();
	}
	public String getStudentName(){
		return StudentName.get();
	}
	public String getInstructorName(){
		return instructorName.get();
	}
	public int getCredit(){
		return Credit.get();
	}
	public int getStudentid(){
		return Studentid.get();
	}
	public Double getQu1(){
		return AssQ1.get();
	}
	public Double getQu2(){
		return AssQ2.get();
	}
	public Double getMid(){
		return AssMid.get();
	}
	public Double getAsst(){
		return AssAsst.get();
	}
	public Double getFinal(){
		return AssFinal.get();
	}
	public Double getTotal(){
		return AssTotal.get();
	}
	
	public String getStatus(){
		return AssStatus.get();
	}
	public String getDepartment(){
		return department.get();
	}
	public String getGrade(){
		return AssGrade.get();
	}
	public int getYr() {
		return Yr.get();
	}
	public int getSemster() {
		return Semster.get();
	}
	public int getNo() {
		return no.get();
	}




//setters


public void setCourseCode(String assCourseCode){
	 this.CourseCode.set(assCourseCode);
}
public void setNo(int no) {
	this.no.set(no);
}
public void setCourseName(String assCourseName){
	 this.CourseName.set(assCourseName);
}
public void setInstructorName(String instName){
	 this.instructorName.set(instName);
}
public void setStudentName(String studentName){
	 this.StudentName.set(studentName);
}
public void setDepartment(String studentName){
	 this.department.set(studentName);
}
public void setCredit(int assCredit){                      
	 this.Credit.set(assCredit);
}
public void setStudentid(int Id){
	 this.Studentid.set(Id);
}
public void setQu1(Double assQ1){
	 this.AssQ1.set(assQ1);
}
public void setQu2(Double assQ2){
	 this.AssQ2.set(assQ2);
}
public void setMid(Double assMid){
	 this.AssMid.set(assMid);
}
public void setAsst(Double assAsst){
	 this.AssAsst.set(assAsst);
}
public void setFinal(Double assFinal){
	 this.AssFinal.set(assFinal);
}
public void setTotal(Double assTotal){
	 this.AssTotal.set(assTotal);
}
public void setStatus(String assStatus){
	 this.AssStatus.set(assStatus);
}
public void setGrade(String assGrade){
	 this.AssGrade.set(assGrade);
}
public void setYear(int yrs) {
	this.Yr.set(yrs);
}

public void setSemster(int semster) {
	this.Yr.set(semster);
}



}


























