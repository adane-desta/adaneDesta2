package shallomCollege;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class YourStudents {
	
	 
	SimpleStringProperty studentNam;
	SimpleStringProperty corName;
	SimpleIntegerProperty yr;
	SimpleIntegerProperty sm;
	
	public YourStudents( String studentName ,String courseName , int yr ,  int semster) {
		
		this.corName = new SimpleStringProperty(courseName);
		this.studentNam = new SimpleStringProperty(studentName);
		this.yr = new SimpleIntegerProperty(yr);
		this.sm = new SimpleIntegerProperty(semster);
		
	}
	
//getters
	
	public String getStudentNam(){
		return studentNam.get();
	}
	public String getCorName(){
		return corName.get();
	}
	public int getYr(){
		return yr.get();
	}
	public int getSm(){
		return sm.get();
	}

//setters
	
	
	public void setStudentNam(String name){
		 this.studentNam.set(name);
	}
	public void setCorName(String coName){
		this.corName.set(coName);
	}
	public void setYr(int yr){
		this.yr.set(yr);
	}
	public void setSm(int sm){
		this.sm.set(sm);
	}
	
}
