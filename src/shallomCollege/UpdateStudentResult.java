package shallomCollege;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateStudentResult implements Initializable{
   
	
	private String studentName , studentDept , studentGrade , studentStatus , courseCode;
	
	private double studentQuize1Result , studentQuize2Result , studentMidResult , studentAsstResult ,
	
	studentFinalExamResult , studentTotalPoint , gradeValue = 0.0, creditPoint = 0.0;
	
	private int studentId , studentClass , studentSemster , courseCreditt ; 
	
	
	public UpdateStudentResult(){
		
	}
	
	
	public UpdateStudentResult(Assesment selectedStudent){
		
		this.studentName = selectedStudent.getStudentName();
		this.studentDept = selectedStudent.getDepartment();
		this.studentId = selectedStudent.getStudentid();
		this.studentClass = selectedStudent.getYr();
		this.studentSemster = selectedStudent.getSemster();
		this.studentGrade = selectedStudent.getGrade();
		this.studentStatus = selectedStudent.getStatus();
		this.studentQuize1Result = selectedStudent.getQu1();
		this.studentQuize2Result = selectedStudent.getQu2();
		this.studentMidResult = selectedStudent.getMid();
		this.studentAsstResult = selectedStudent.getAsst();
		this.studentFinalExamResult = selectedStudent.getFinal();
		this.studentTotalPoint = selectedStudent.getTotal();
		this.courseCode = selectedStudent.getCourseCode();
		this.courseCreditt = selectedStudent.getCredit();
		
	}
	
	private Stage updateStage = new Stage();
	
	@FXML
	private TextField quize1Result , quize2Result , midResult , asstResult , finalExamResult , totalPoint;
	@FXML
	private Label selectedStudentName , selectedStudentDept , gradePoint , status;
	@FXML
	private Button saveResultUpdate;
	@FXML
	ImageView studentPicture;
	

	public void setResult() throws IOException, SQLException {
	 	
		System.out.println("This Is from setResult");
        int stuId = this.studentId;
        
		String sql = "select image from student where studentid = ?";
		Connection conn = ConnectToDataBase.getConnected();
		
		if(conn != null ) {
			PreparedStatement myst = conn.prepareStatement(sql);
			myst.setInt(1, stuId);
			ResultSet rst = myst.executeQuery();
			InputStream studentImage = null;
			while(rst.next()) {
				studentImage = rst.getBinaryStream("image");
			}

			FXMLLoader loader = new FXMLLoader(getClass().getResource("updateResult.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Instructor inst = new Instructor();
			
			selectedStudentName = (Label)loader.getNamespace().get("selectedStudentName");
			gradePoint = (Label)loader.getNamespace().get("gradePoint");
			status = (Label)loader.getNamespace().get("status");
			quize1Result = (TextField)loader.getNamespace().get("quize1Result");
			quize2Result = (TextField)loader.getNamespace().get("quize2Result");
			midResult = (TextField)loader.getNamespace().get("midResult");
			asstResult = (TextField)loader.getNamespace().get("asstResult");
			finalExamResult = (TextField)loader.getNamespace().get("finalExamResult");
			totalPoint = (TextField)loader.getNamespace().get("totalPoint");
			saveResultUpdate = (Button)loader.getNamespace().get("saveResultUpdate");
			ImageView studentPicture = (ImageView)loader.getNamespace().get("studentPicture");

			if(studentImage !=null) {
				Image image = new Image(studentImage);
				studentPicture.setImage(image);
			}
            
			selectedStudentName.setText(inst.getStudentName(this.studentId));
			quize1Result.setText(Double.toString(this.studentQuize1Result));
			quize2Result.setText(Double.toString(this.studentQuize2Result));
			midResult.setText(Double.toString(this.studentMidResult));
			asstResult.setText(Double.toString(this.studentAsstResult));
			finalExamResult.setText(Double.toString(this.studentFinalExamResult));
			totalPoint.setText(Double.toString(this.studentTotalPoint));
			gradePoint.setText(this.studentGrade);
			status.setText(this.studentStatus);
			
			updateStage.setScene(scene);
			updateStage.initModality(Modality.APPLICATION_MODAL);
			updateStage.setResizable(false);
			updateStage.show();

		}
		
		else {
			ConnectToDataBase.unableToConnect();
		}
	
		this.initialize(null, null);
	} 
 

	private String setGradePoint(double totalScore) {
        System.out.println("this is from set grade " + this.studentId + " "+this.courseCode + " " + this.courseCreditt);
		String grade = "";

		if(Double.parseDouble(totalPoint.getText())>=90) {
			grade ="A+";
			this.gradeValue = 4.00;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<90 && Double.parseDouble(totalPoint.getText())>=85 ) {
			grade ="A";
			this.gradeValue = 4.00;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())>=80 && Double.parseDouble(totalPoint.getText())<85 ) {
			grade ="A-";
			this.gradeValue = 3.75;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<80 && Double.parseDouble(totalPoint.getText())>=75 ) {
			grade ="B+";
			this.gradeValue = 3.50;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<75 && Double.parseDouble(totalPoint.getText())>=70 ) {
			grade ="B";
			this.gradeValue = 3.00;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<70 && Double.parseDouble(totalPoint.getText())>=65 ) {
			grade ="B-";
			this.gradeValue = 2.75;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<65 && Double.parseDouble(totalPoint.getText())>=60 ) {
			grade ="C+";
			this.gradeValue = 2.50;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<60 && Double.parseDouble(totalPoint.getText())>=55 ) {
			grade ="C";
			this.gradeValue = 2.00;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<55 && Double.parseDouble(totalPoint.getText())>=50 ) {
			grade ="C-";
			this.gradeValue = 1.75;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<50 && Double.parseDouble(totalPoint.getText())>=40 ) {
			grade ="D";
			this.gradeValue = 1.75;
			this.creditPoint = this.courseCreditt * this.gradeValue ;
		}
		if(Double.parseDouble(totalPoint.getText())<40) {
			grade ="F";
			this.gradeValue = 0.00;
			this.creditPoint = this.courseCreditt * this.gradeValue ;

		}
		
		return grade;
	}
    
	private void allInputsAreValid(double totalScore) {
 

		totalPoint.setText(Double.toString(totalScore)); 
		totalPoint.setVisible(true); 
		gradePoint.setText(this.setGradePoint(Double.parseDouble(totalPoint.getText())));
		gradePoint.setVisible(true);
		

		if(gradePoint.getText().equals("F")) {
			status.setStyle("-fx-background-color:red;");
			gradePoint.setStyle("-fx-background-color:red;");
			status.setText("FAIL");
		}

		else {
			status.setStyle("-fx-background-color:green;");
			gradePoint.setStyle("-fx-background-color:green;");
			status.setText("PASS");
		}     
			saveResultUpdate.setDisable(false);
		
		
	}
 
	private void allInputsAreNotValid() {
		
		totalPoint.setText(null);
		gradePoint.setText(null);
		gradePoint.setVisible(false);
		status.setStyle("-fx-background-color:red;");
		status.setText("Incomplete");
		
		if(
				!finalExamResult.getText().isEmpty()
				&& !quize1Result.getText().isEmpty()
				&& !quize2Result.getText().isEmpty()
				&& !midResult.getText().isEmpty()
				&& !asstResult.getText().isEmpty()

				) 

		{
			saveResultUpdate.setDisable(true);
		}


	}

	
	
	private void saveTheResult() throws SQLException {
		
		
		Connection conn = ConnectToDataBase.getConnected();
		
		if(conn != null) {
			
			String sql =   "update studentenrollement "
					        + "set quize1 = ? , "
					        + "quize2 = ? , "
					        + "midExam = ? , "
					        + "assignment = ? , "
					        + "finalExam = ? , "
					        + "totalPoint = ?  ,"
					        + "grade = ? ,"
					        + "status = ? ,"
					        + "gradePoint = ? ,"
					        + "creditpoint = ? "
				        	+ "where studentid = ?  and "
				        	+ "coursecode = ? ; ";
				        	
			
			PreparedStatement myst = conn.prepareStatement(sql);
			
			if(!quize1Result.getText().isEmpty()) {
				
				myst.setDouble(1, Double.parseDouble(quize1Result.getText()));
			}
			else {
				myst.setDouble(1, 0.0);
			}

			if(!quize2Result.getText().isEmpty()) {
				
				myst.setDouble(2, Double.parseDouble(quize2Result.getText()));
			}
			else {
				myst.setDouble(2, 0.0);
			}

			if(!midResult.getText().isEmpty()) {
				
				myst.setDouble(3, Double.parseDouble(midResult.getText()));
			}
			else {
				myst.setDouble(3, 0.0);
			}

			if(!asstResult.getText().isEmpty()) {
				
				myst.setDouble(4, Double.parseDouble(asstResult.getText()));
			}
			else {
				myst.setDouble(4, 0.0);
			}

			if(!finalExamResult.getText().isEmpty()) {
				
				myst.setDouble(5, Double.parseDouble(finalExamResult.getText()));
			}
			else {
				myst.setDouble(5, 0.0);
			}
			if(totalPoint.getText() != null) {
				myst.setDouble(6, Double.parseDouble(totalPoint.getText()));
			}
			else {
				myst.setDouble(6, 0.0);
			}
			
			myst.setString(7, gradePoint.getText());
			myst.setString(8, status.getText());
			myst.setDouble(9, this.gradeValue);
			myst.setDouble(10, this.creditPoint);
			myst.setInt(11 , this.studentId);
			myst.setString(12, this.courseCode);
			 
			
			myst.executeUpdate();
			
			updateStage.close();
			
			System.out.println("doneeeee");
			
			
		}
		
		else {
			ConnectToDataBase.unableToConnect();
		}
	
		
	}
	
	private boolean isDouble(String text) {

		if (text == null || text.isEmpty()) {
			return false;
		}


		String doubleRegex = "^\\d*(\\.\\d+)?$"; 
		if (!text.matches(doubleRegex)) {
			return false;
		}


		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("This Is from Intialize");
		System.out.println(UpdateStudentResult.this.studentId);
		try {

			   
			saveResultUpdate.setOnAction(e ->{
				try {
					this.saveTheResult();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			String errorStyle = "-fx-border-color:red;"
					+ "-fx-border-width:2px;"
					+ "-fx-text-fill:red;";
			String normalStyle = "-fx-border-color:green;"
					+ "-fx-border-width:2px;"
					+ "-fx-text-fill:black;";

			quize1Result.setOnKeyTyped(e ->{
				if(quize1Result.getText().isEmpty()) {
					quize1Result.setStyle(errorStyle);
					allInputsAreNotValid();
					quize1Result.setAccessibleText("invalid");
				}
				else {
					if(!isDouble(quize1Result.getText())) {
						quize1Result.setStyle(errorStyle);
						allInputsAreNotValid();
						quize1Result.setAccessibleText("invalid");


					}
					else {
						if(Double.parseDouble(quize1Result.getText())>5 || Double.parseDouble(quize1Result.getText())<0) {
							quize1Result.setStyle(errorStyle);
							allInputsAreNotValid();
							quize1Result.setAccessibleText("invalid");
						}
						else {
							quize1Result.setStyle(normalStyle);	
							quize1Result.setAccessibleText("valid");

							if(quize1Result.getAccessibleText().equals("valid")&& quize2Result.getAccessibleText().equals("valid")&& finalExamResult.getAccessibleText().equals("valid")
									&&asstResult.getAccessibleText().equals("valid")&&midResult.getAccessibleText().equals("valid"))
							{

								this.allInputsAreValid(Double.parseDouble(finalExamResult.getText())+Double.parseDouble(asstResult.getText())+Double.parseDouble(midResult.getText())
								+Double.parseDouble(quize1Result.getText())+Double.parseDouble(quize2Result.getText()));

							}

						}

					}
				}

			});

			quize2Result.setOnKeyTyped(e ->{
				if(quize2Result.getText().isEmpty()) {
					quize2Result.setStyle(errorStyle);
					allInputsAreNotValid();
					quize2Result.setAccessibleText("invalid");
				}
				else {
					if(!isDouble(quize2Result.getText())) {
						quize2Result.setStyle(errorStyle);
						allInputsAreNotValid();
						quize2Result.setAccessibleText("invalid");

					}
					else {
						if(Double.parseDouble(quize2Result.getText())>5 || Double.parseDouble(quize2Result.getText())<0) {
							quize2Result.setStyle(errorStyle);
							allInputsAreNotValid();
							quize2Result.setAccessibleText("invalid");
						}
						else {
							quize2Result.setStyle(normalStyle);	
							quize2Result.setAccessibleText("valid");

							if(quize1Result.getAccessibleText().equals("valid")&& quize2Result.getAccessibleText().equals("valid")&& finalExamResult.getAccessibleText().equals("valid")
									&&asstResult.getAccessibleText().equals("valid")&&midResult.getAccessibleText().equals("valid"))
							{

								this.allInputsAreValid(Double.parseDouble(finalExamResult.getText())+Double.parseDouble(asstResult.getText())+Double.parseDouble(midResult.getText())
								+Double.parseDouble(quize1Result.getText())+Double.parseDouble(quize2Result.getText()));

							}
						}

					}
				}
			});


			midResult.setOnKeyTyped(e ->{
				if(midResult.getText().isEmpty()) {
					midResult.setStyle(errorStyle);
					allInputsAreNotValid();
					midResult.setAccessibleText("invalid");
				}
				else {
					if(!isDouble(midResult.getText())) {
						midResult.setStyle(errorStyle);
						allInputsAreNotValid();
						midResult.setAccessibleText("invalid");

					}
					else {
						if(Double.parseDouble(midResult.getText())>30 || Double.parseDouble(midResult.getText())<0) {
							midResult.setStyle(errorStyle);
							allInputsAreNotValid();
							midResult.setAccessibleText("invalid");
						}
						else {
							midResult.setStyle(normalStyle);	
							midResult.setAccessibleText("valid");

							if(quize1Result.getAccessibleText().equals("valid")&& quize2Result.getAccessibleText().equals("valid")&& finalExamResult.getAccessibleText().equals("valid")
									&&asstResult.getAccessibleText().equals("valid")&&midResult.getAccessibleText().equals("valid"))
							{

								this.allInputsAreValid(Double.parseDouble(finalExamResult.getText())+Double.parseDouble(asstResult.getText())+Double.parseDouble(midResult.getText())
								+Double.parseDouble(quize1Result.getText())+Double.parseDouble(quize2Result.getText()));

							}
						}

					}
				}

			});

			asstResult.setOnKeyTyped(e ->{
				if(asstResult.getText().isEmpty()) {
					asstResult.setStyle(errorStyle);
					allInputsAreNotValid();
					asstResult.setAccessibleText("invalid");
				}
				else {
					if(!isDouble(asstResult.getText())) {
						asstResult.setStyle(errorStyle);
						allInputsAreNotValid();
						asstResult.setAccessibleText("invalid");

					}
					else {
						if(Double.parseDouble(asstResult.getText())>10 || Double.parseDouble(asstResult.getText())<0) {
							asstResult.setStyle(errorStyle);
							allInputsAreNotValid();
							asstResult.setAccessibleText("invalid");
						}
						else {
							asstResult.setStyle(normalStyle);
							asstResult.setAccessibleText("valid");

							if(quize1Result.getAccessibleText().equals("valid")&& quize2Result.getAccessibleText().equals("valid")&& finalExamResult.getAccessibleText().equals("valid")
									&&asstResult.getAccessibleText().equals("valid")&&midResult.getAccessibleText().equals("valid"))
							{

								this.allInputsAreValid(Double.parseDouble(finalExamResult.getText())+Double.parseDouble(asstResult.getText())+Double.parseDouble(midResult.getText())
								+Double.parseDouble(quize1Result.getText())+Double.parseDouble(quize2Result.getText()));

							}
						}

					}
				}

			});

			finalExamResult.setOnKeyTyped(e ->{
				if(finalExamResult.getText().isEmpty()) {
					finalExamResult.setStyle(errorStyle);
					allInputsAreNotValid();
					finalExamResult.setAccessibleText("invalid");
				}
				else {
					if(!isDouble(finalExamResult.getText())) {
						finalExamResult.setStyle(errorStyle);
						allInputsAreNotValid();
						finalExamResult.setAccessibleText("invalid");

					}
					else {
						if(Double.parseDouble(finalExamResult.getText())>50 || Double.parseDouble(finalExamResult.getText())<0) {
							finalExamResult.setStyle(errorStyle);
							allInputsAreNotValid();
							finalExamResult.setAccessibleText("invalid");
						}
						else {
							finalExamResult.setStyle(normalStyle);
							finalExamResult.setAccessibleText("valid");

							if(quize1Result.getAccessibleText().equals("valid")&& quize2Result.getAccessibleText().equals("valid")&& finalExamResult.getAccessibleText().equals("valid")
									&&asstResult.getAccessibleText().equals("valid")&&midResult.getAccessibleText().equals("valid"))
							{

								this.allInputsAreValid(Double.parseDouble(finalExamResult.getText())+Double.parseDouble(asstResult.getText())+Double.parseDouble(midResult.getText())
								+Double.parseDouble(quize1Result.getText())+Double.parseDouble(quize2Result.getText()));

							} 
						}
					}
				}
			});


		
		}
		catch(Exception exc){
				exc.printStackTrace();
			}
		
	}

}
