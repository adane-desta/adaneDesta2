package shallomCollege;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.sun.prism.paint.Color;

import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Student implements Initializable{
	
	
	 private static FXMLLoader fxmlLoader;
	
	 private static String  StudentUsername ,fname, lname,department, email, phone;
	
	 private static int  studentId , year , deptId , semster ;
	
	 private static double gpa , cgpa ; 
	 private static InputStream ProfilePicture;
	
	@FXML
    private TextArea reportBox;
	@FXML
    private Button submitReport;
	@FXML
	private  Label labelStudentName,labelDepartment,labelGpa,labelCgpa,labelClass,labelPhone,labelEmail,labelStudentId;
	@FXML
	private TableView<CurrentCourses> tableCurrentCourses; 
	@FXML
	private TableColumn<String ,CurrentCourses> colCorseCode;
	@FXML
	private TableColumn<String ,CurrentCourses> colCourseName;
	@FXML
	private TableColumn<String ,CurrentCourses> colInstructor;
	@FXML
	private TableColumn<Integer ,CurrentCourses> colCredits;
	@FXML
	private TableColumn<String ,CurrentCourses> colPrerequest;
	@FXML
	private TableColumn<Integer ,CurrentCourses> colNo;
	
	private ObservableList<CurrentCourses>data = FXCollections.observableArrayList();
	
	
	@FXML
	private TableView<Assesment> assesmentTable ;
	@FXML
	private TableColumn<String , Assesment> assCourseCode;
	@FXML
	private TableColumn<Assesment ,String> assCourseName;
	@FXML
	private TableColumn<String , Assesment> assInstructor;
	@FXML
	private TableColumn<Integer , Assesment> assCredit;
	@FXML
	private TableColumn<Double , Assesment> assQ1;
	@FXML
	private TableColumn<Double , Assesment> assQ2;
	@FXML
	private TableColumn<Double , Assesment> assMid;
	@FXML
	private TableColumn<Double , Assesment> assAsst;
	@FXML
	private TableColumn<Double , Assesment> assFinal;
	@FXML
	private TableColumn<Double , Assesment> assTotal;
	@FXML
	private TableColumn<Assesment ,String> assStatus;
	@FXML
	private TableColumn<String , Assesment> assGrade;
	@FXML
	private TableColumn<Integer , Assesment> stuId;
	
	@FXML
	private TableView<CurrentCourses> tableCurrentCourses1; 
	@FXML
	private TableColumn<String ,CurrentCourses> colCorseCode1;
	@FXML
	private TableColumn<String ,CurrentCourses> colCourseName1;
	@FXML
	private TableColumn<String ,CurrentCourses> colInstructor1;
	@FXML
	private TableColumn<Integer ,CurrentCourses> colCredits1;
	@FXML
	private TableColumn<String ,CurrentCourses> colPrerequest1;
	@FXML
	private TableColumn<Integer ,CurrentCourses> colNo1;
	
	private ObservableList<CurrentCourses>data2 = FXCollections.observableArrayList();
	private ObservableList <Assesment> gradeReport = FXCollections.observableArrayList();
	
	
	
	public static void student(String usName) throws IOException, SQLException {
		
		fxmlLoader = new FXMLLoader(Student.class.getResource("studentProfile.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Main.MainStage.setScene(scene);
		setStudentInfo(usName);
		Main.MainStage.show();
	    
	}
	
	private static void setStudentInfo(String uname) throws SQLException {
		StudentUsername = uname;
		String sql = "select * from student where username = ? ";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		myst.setString(1, uname);
		ResultSet rst = myst.executeQuery();
	 	
		while(rst.next()) {
			fname = rst.getString("fname");
			lname = rst.getString("lname"); 
			email = rst.getString("email");
			phone = rst.getString("phone");
			studentId = rst.getInt("studentid");
			year = rst.getInt("class"); 
			deptId = rst.getInt("dept"); 
			semster = rst.getInt("semster");
			gpa = rst.getDouble("gpa");
			cgpa = rst.getDouble("cgpa");
		    ProfilePicture = rst.getBinaryStream("image");
		}
		
	    department = "";
		sql = "select deptname from department where deptid = ?"; 
	    myst = ConnectToDataBase.getConnected().prepareStatement(sql);
	    myst.setInt(1, deptId);
	    rst = myst.executeQuery();
	    while(rst.next()) {
	    	department = rst.getString("deptname");
	    }
	    
	    Label labelDepartment = (Label)fxmlLoader.getNamespace().get("labelDepartment");
	    Label labelGpa = (Label)fxmlLoader.getNamespace().get("labelGpa");
	    Label labelCgpa = (Label)fxmlLoader.getNamespace().get("labelCgpa");
	    Label labelClass = (Label)fxmlLoader.getNamespace().get("labelClass");
	    Label labelPhone = (Label)fxmlLoader.getNamespace().get("labelPhone");
	    Label labelEmail = (Label)fxmlLoader.getNamespace().get("labelEmail");
	    Label labelStudentName = (Label)fxmlLoader.getNamespace().get("labelStudentName");
	    Label labelStudentId = (Label)fxmlLoader.getNamespace().get("labelStudentId");
	    ImageView studentProfileImage = (ImageView)fxmlLoader.getNamespace().get("studentProfileImage");
	    
	    System.out.println(department);
	    Image image = new Image(ProfilePicture);
	    studentProfileImage.setImage(image);
	    labelDepartment.setText(department);
		labelGpa.setText("GPA :     "+Double.toString(gpa));
		labelCgpa.setText("CGPA :   "+Double.toString(cgpa));
		labelClass.setText("Year    "+Integer.toString(year)+"    Semster   "+Integer.toString(semster));
		labelPhone.setText("Phone : "+phone);
		labelEmail.setText("contact mail :"+email); 
		labelStudentName.setText(fname + " " + lname);
		labelStudentId.setText("StId/"+department.substring(0, 2)+department.substring(department.lastIndexOf(" ")+1,department.lastIndexOf(" ")+3)+"/"+Integer.toString(studentId));
	    
		
	}
	
	
    public void studentHome(Event event) throws SQLException {
		
    	    setVisibility("ancHome");
			indicateActiveMenuButn(event);
			
			data.clear();

		String Sql = "select  coursename , courses.coursecode , concat(instructor.fname,\"  \", instructor.lname) as \"instructor\", credits, prerequest \r\n"
				+ "from course_department, courses,instructor\r\n"
				+ "where deptid = ? and course_department.class = ? and course_department.semster = ? and "
				+ "courses.coursecode = course_department.coursecode and instid in (SELECT instid from"
				+ " instructorenrollment where instructorenrollment.coursecode = courses.coursecode"
				+ " and dept = ? and class = ? and semster= ?)";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(Sql);
		myst.setInt(1, deptId);
		myst.setInt(2, year);
		myst.setInt(3, semster);
		myst.setInt(4, deptId);
		myst.setInt(5, year);
		myst.setInt(6, semster);
		ResultSet rst = myst.executeQuery();
		
		int numericOrder = 0;
		
		while(rst.next()) {
			numericOrder++;
			CurrentCourses  newRow = new CurrentCourses(rst.getString("coursecode"),rst.getString("coursename"),rst.getString("instructor"),rst.getInt("credits"),rst.getString("prerequest"),numericOrder);
			data.add(newRow);
			
		}
		 data = tableCurrentCourses.getItems();
		 
		    
	}
      
    public void studentRegistration(Event event) throws SQLException {
		
		setVisibility("ancRegister");
		indicateActiveMenuButn(event);
		
		int  regYear = 1 ,regSemster = 1;
	    final int currentYear , currentSemster;
		if(year < 4 && semster == 2) {
			regYear = year +1;
			regSemster = semster-1;
			
		}
	    if(year <= 4 && semster == 1) {
	    	regYear = year;
	    	regSemster = semster + 1;
	    	
	    }
	    currentYear =regYear;
	    currentSemster = regSemster;
		
		FadeTransition fade = new FadeTransition();
		Button regBtn = (Button)fxmlLoader.getNamespace().get("op"+Integer.toString(regYear*10+regSemster));
		
		Label status;
		String sql = "select * from regstration where dept = "+deptId+" ORDER BY dept , class , semster";
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery(sql);
		while(rst.next()) {
			
			status = (Label)fxmlLoader.getNamespace().get("st"+Integer.toString(rst.getInt("class"))+Integer.toString(rst.getInt("semster")));
			status.setText(rst.getString("regStatus"));		    
		    if(rst.getString("regStatus").equals("open")) {
		    status.setStyle("-fx-background-color:#06f206");
		    	
		    }
		    else {
		    	
		    	status.setStyle("-fx-background-color:red");
		    }
		    
		}
		regBtn.setText("Request to Register");
		fade.setNode(regBtn);
		fade.setFromValue(0.23);
		fade.setToValue(1);
		fade.setCycleCount(FadeTransition.INDEFINITE);
		fade.setAutoReverse(true);
		fade.setDuration(Duration.millis(900));
		fade.play();
		regBtn.setVisible(true);
		
		regBtn.setOnAction(e ->{
			String currentStatus = "";
			String sql2 = "select regStatus from regstration where dept = "+deptId+" and class = "+currentYear+" and "
				       	+ "semster = "+currentSemster; 
			try {
				Statement myst2 = ConnectToDataBase.getConnected().createStatement();
				ResultSet rst2 = myst2.executeQuery(sql2);
				while(rst2.next()) {
					currentStatus = rst2.getString("regStatus");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			if(currentStatus.equals("closed")) {
				JOptionPane.showMessageDialog(null, "The Registration is Currently closed For year "
						+ ""+currentYear+" semster "+currentSemster);
			} 
			else {
				try {
					String sql3 = "update student set class = "+currentYear+","+"semster = "+currentSemster +" where studentid = "+studentId +";";
					Statement myst3 = ConnectToDataBase.getConnected().createStatement();
					int rst3 = myst3.executeUpdate(sql3);
					
					String Sql4 = "select course_department.coursecode ,course_department.semster , course_department.class , instructorenrollment.instid\r\n"
							+ " from course_department , instructorenrollment\r\n "
							+ " where course_department.class = "+currentYear+ " and  course_department.semster = "+currentSemster+ " and course_department.deptid = "+deptId
							+ " and instructorenrollment.coursecode = course_department.coursecode and instructorenrollment.semster ="+currentSemster
							+ " and instructorenrollment.class = "+currentYear+ " and instructorenrollment.dept = "+deptId+";";
							
					 
					Statement myst4 = ConnectToDataBase.getConnected().createStatement();
					ResultSet currentCoursesRegistered = myst4.executeQuery(Sql4);
					
					String sql5 = "insert into studentenrollement(studentid , coursecode ,semster , class, instructor)\r\n"
							+ "values(? ,? ,? ,? ,?);";
					PreparedStatement mypreState = ConnectToDataBase.getConnected().prepareStatement(sql5);
					
					while(currentCoursesRegistered.next()) {
						
						mypreState.setInt(1, studentId);
						mypreState.setString(2, currentCoursesRegistered.getString("coursecode"));
						mypreState.setInt(3, currentSemster);
						mypreState.setInt(4, currentYear);
						mypreState.setInt(5, currentCoursesRegistered.getInt("instid"));
						int enter = mypreState.executeUpdate();
						
					}
					
					
					
					
					
					
//					
		
					
				} 
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "you have successfully Registerd For year "+currentYear+" semster "+currentSemster);
			}
			
		});

	} 
    
    public void getNotification(Event event) {
    	    indicateActiveMenuButn(event);
			setVisibility("ancNotification");
    }
    
    public void showGrades(Event event) throws SQLException {
    	
		    indicateActiveMenuButn(event);
			setVisibility("ancGrades");
	}

    public void showGrades2(Event event) throws SQLException {

    	
		
		Button btn = (Button)event.getSource();
		String text = btn.getAccessibleText();
		int requiredYear = 0 , requiredSemster = 0;
		
		
		
			requiredYear = Integer.parseInt(text)/10;
			requiredSemster = Integer.parseInt(text)%10;
			stuId.setText("No.");
		
		String sql = "select * from studentenrollement "
				+ "where studentid = "+studentId+" and class = "+requiredYear+" and semster = "+requiredSemster;
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery(sql); 
		
		gradeReport.clear();
		
		int no = 0;
		while(rst.next()) {
			no++;
			String cCode = rst.getString("coursecode");
			
			Assesment assesmentRow = new Assesment(
					no,
					cCode,
					getCourseName(cCode),
					getInstructorName(rst.getInt("instructor")),
					getCourseCredit(cCode),
					rst.getDouble("quize1"),
					rst.getDouble("quize2"),
					rst.getDouble("midExam"),
					rst.getDouble("assignment"),
					rst.getDouble("finalExam"),
					rst.getDouble("totalPoint"),
					rst.getString("status"),
					rst.getString("grade"));
			
			gradeReport.add(assesmentRow);
		}
		
        TableView<Assesment> assesmentTable2 = new TableView<Assesment>();
		assesmentTable2 = assesmentTable;
		gradeReport = assesmentTable2.getItems();
		
		
		Label label = new Label("Your Grade In Year "+requiredYear +" Semster "+requiredSemster);
		
		label.setStyle("-fx-background-color: #87CEEB; "
				+ "-fx-text-fill: white; "
				+ "-fx-padding: 10px;"
				+ "-fx-font-size:20px;"
				+ "-fx-font-family:arial;"
				);
		label.setLayoutX(500);
		AnchorPane root = new AnchorPane();
		root.snapSpaceY(20);
		root.getChildren().add(label);
		root.getChildren().add(assesmentTable2);
		Scene scene = new Scene(root ,1300, 500);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setX(200);
		stage.setTitle("Your Grade In Year "+requiredYear +" Semster "+requiredSemster);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		
		
    }
        
    public void showMessages(Event event) {
    	
    	indicateActiveMenuButn(event);
		setVisibility("ancMessages");
		
    }
    
    public String getInstructorName(int id) throws SQLException {
    	
    	String sql = "select concat(fname,' ',lname) as 'instructor' from instructor where instid =  "+id;
    	Statement myst = ConnectToDataBase.getConnected().createStatement();
    	ResultSet rst = myst.executeQuery(sql);
    	
    	String name = "" ;
    	while(rst.next()) {
    		name = rst.getString("instructor");
    	}
    	return name;
    }
    
    public String getCourseName(String courseCod) throws SQLException {
    	
    	String sql = "select coursename from courses where coursecode  = ?";
    	PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
    	myst.setString(1, courseCod);
    	ResultSet rst = myst.executeQuery();

    	String name = "" ;
    	while(rst.next()) {
    		name = rst.getString("coursename");
    	}
    	return name;
    }
    public int getCourseCredit(String courseCod) throws SQLException {
    	
    	String sql = "select credits from courses where coursecode = ?";
    	PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
    	myst.setString(1, courseCod);
    	ResultSet rst = myst.executeQuery();

    	int credit = 0 ;
    	while(rst.next()) {
    		credit = rst.getInt("credits");
    	}
    	return credit;
    }
    
    public void showCurriculum(Event event) throws SQLException {
		
		indicateActiveMenuButn(event);
		
		setVisibility("ancCurriculum");
	
	}
    
    public void showCurriculum2(Event event) throws SQLException {
        data2.clear();
        Button btn = (Button) event.getSource();
        String text = btn.getAccessibleText();
        int requiredYear = Integer.parseInt(text) / 10;
        int requiredSemster = Integer.parseInt(text) % 10;

        String sql = "SELECT coursename, courses.coursecode, "
                   + "CONCAT(instructor.fname, ' ', instructor.lname) AS instructor, "
                   + "credits, prerequest "
                   + "FROM course_department, courses, instructor "
                   + "WHERE deptid = ? AND course_department.class = ? "
                   + "AND course_department.semster = ? "
                   + "AND courses.coursecode = course_department.coursecode "
                   + "AND instid IN (SELECT instid FROM instructorenrollment "
                   + "WHERE instructorenrollment.coursecode = courses.coursecode "
                   + "AND dept = ? AND class = ? AND semster= ?)";
        
        PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
        myst.setInt(1, deptId);
        myst.setInt(2, requiredYear);
        myst.setInt(3, requiredSemster);
        myst.setInt(4, deptId);
        myst.setInt(5, requiredYear);
        myst.setInt(6, requiredSemster);

        ResultSet rst = myst.executeQuery();

        int numericOrder = 0;
        while (rst.next()) {
            numericOrder++;
            CurrentCourses newRow = new CurrentCourses(
                rst.getString("coursecode"),
                rst.getString("coursename"),
                rst.getString("instructor"),
                rst.getInt("credits"),
                rst.getString("prerequest"),
                numericOrder
            );
            data2.add(newRow);
        }

        
        tableCurrentCourses1.setItems(data2);
        
        Label label = new Label("Academic Calendar For " + department + " year " + requiredYear + " Semester " + requiredSemster);
		
		label.setStyle("-fx-background-color: #87CEEB; "
				+ "-fx-text-fill: white; "
				+ "-fx-padding: 10px;"
				+ "-fx-font-size:20px;"
				+ "-fx-font-family:areal;"
				);
		label.setLayoutX(500);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().add(label);
        root.getChildren().add(tableCurrentCourses1);

        Scene scene = new Scene(root, 1300, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Academic Calendar For " + department + " year " + requiredYear + " Semester " + requiredSemster);
        stage.setX(200);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
   
    public void showDepartement(Event event) {
		
		setVisibility("ancDepartement");
		indicateActiveMenuButn(event);
	}
    public void Report(Event event) {
		
		setVisibility("ancReport");
		indicateActiveMenuButn(event);
	}
    
    public void submitReport() throws SQLException {
    	
    	if(!reportBox.getText().isEmpty()) {
    		String report = reportBox.getText();
    	String sql = "insert into report(reporterid , usertype , reportcontent)\r\n"
    			+ "VALUE(? , ? , ?)";
    	PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
    	myst.setInt(1, studentId);
    	myst.setString(2, "student");
    	myst.setString(3, report);
    	
    	int rst = myst.executeUpdate();
    	JOptionPane.showMessageDialog(null, "your report has been submited successfully");
    	reportBox.clear();
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "please enter your report");
    	}
    	
    }
    
	public void changeUsernameOrPassword(Event event) throws IOException, SQLException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("settingPage.fxml"));
		Parent root = loader.load();
		
		AnchorPane anchChangePassword = (AnchorPane)loader.getNamespace().get("anchChangePassword");
		AnchorPane ancChangeUsername = (AnchorPane)loader.getNamespace().get("ancChangeUsername");
		Button changeYourUsername = (Button)loader.getNamespace().get("changeYourUsername");
		Button ChangeYourPassword = (Button)loader.getNamespace().get("ChangeYourPassword");
		Button saveTheChange = (Button)loader.getNamespace().get("saveTheChange");
		PasswordField oldPassword = (PasswordField)loader.getNamespace().get("oldPassword");
		PasswordField newPassword = (PasswordField)loader.getNamespace().get("newPassword");
		PasswordField confirmNewPassword = (PasswordField)loader.getNamespace().get("confirmNewPassword");
		Label oldPasswordLabel = (Label)loader.getNamespace().get("oldPasswordLabel");
		Label newPasswordLabel = (Label)loader.getNamespace().get("newPasswordLabel");
		Label ConfirmNewPasswordLabel = (Label)loader.getNamespace().get("ConfirmNewPasswordLabel");
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.show();
		
		String passwordRegEx = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
;
		String sql = "select password from student where username = ?";
		Connection conn = ConnectToDataBase.getConnected();
	
		changeYourUsername.setOnAction(e ->{
			
			anchChangePassword.setVisible(false);
			ancChangeUsername.setVisible(true);
			saveTheChange.setVisible(true);
			
		});
		
		ChangeYourPassword.setOnAction(e ->{
			ancChangeUsername.setVisible(false);
			anchChangePassword.setVisible(true);
			saveTheChange.setVisible(true);
			
		});
		
		oldPassword.setOnKeyTyped(e -> {
			
		    try (PreparedStatement myst = conn.prepareStatement(sql)) {  
		        myst.setString(1, StudentUsername);
		        try (ResultSet rst = myst.executeQuery()) {  
		            if (rst.next()) {
		                String oldPass = rst.getString("password");
		                if (oldPassword.getText().equals(oldPass)) {
		                    System.out.println(oldPass);
		                    newPassword.setDisable(false);
		                    oldPassword.setId("validInput");
		                    oldPasswordLabel.setVisible(false);
		                } else {
		                    newPassword.setDisable(true);
		                    oldPassword.setId("invalidInput");
		                    oldPasswordLabel.setId("invalidInput");
		                    oldPasswordLabel.setText("Incorrect Password");
		                    oldPasswordLabel.setVisible(true);
		                }
		            }
		        }
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		});


		
		newPassword.setOnKeyTyped(e ->{
			
			if(newPassword.getText().matches(passwordRegEx)&& newPassword.getText().length()>8) {
				newPassword.setId("validInput");
				confirmNewPassword.setDisable(false);
				newPasswordLabel.setVisible(false);
			}
			
			else {
				
				newPasswordLabel.setId("invalidInput");
				newPasswordLabel.setText("The Password Must Be 8 or More Characters long and Contains At Least 1 Upercase letter 1 Digit and 1 special Character");
				newPasswordLabel.setVisible(true);
				confirmNewPassword.setDisable(true);
				
				
			}
			
		});
		
		confirmNewPassword.setOnKeyTyped(e ->{
			
			if(confirmNewPassword.getText().equals(newPassword.getText())) {
				saveTheChange.setDisable(false);
				confirmNewPassword.setId("validInput");
				ConfirmNewPasswordLabel.setVisible(false);
			}
			
			else {
				saveTheChange.setDisable(true);
				confirmNewPassword.setId("invalidInput");
				ConfirmNewPasswordLabel.setId("invalidInput");
				ConfirmNewPasswordLabel.setText("Password does not match");
				ConfirmNewPasswordLabel.setVisible(true);
			}
		});
		
		
	}
    
	private void setVisibility(String nodeId) {
		 
		AnchorPane ancHome = (AnchorPane)fxmlLoader.getNamespace().get("ancHome");
		AnchorPane ancGrades = (AnchorPane)fxmlLoader.getNamespace().get("ancGrades");
		AnchorPane ancCurriculum = (AnchorPane)fxmlLoader.getNamespace().get("ancCurriculum");
		AnchorPane ancDepartement = (AnchorPane)fxmlLoader.getNamespace().get("ancDepartement");
		AnchorPane ancReport = (AnchorPane)fxmlLoader.getNamespace().get("ancReport");
		AnchorPane ancRegister = (AnchorPane)fxmlLoader.getNamespace().get("ancRegister");
		AnchorPane ancNotification = (AnchorPane)fxmlLoader.getNamespace().get("ancNotification");
		AnchorPane ancMessages = (AnchorPane)fxmlLoader.getNamespace().get("ancMessages");
		
		ancHome.setVisible(false);
		ancGrades.setVisible(false);
		ancCurriculum.setVisible(false);
		ancDepartement.setVisible(false);
		ancReport.setVisible(false);
		ancRegister.setVisible(false); 
		ancNotification.setVisible(false);
		ancMessages.setVisible(false);
		
		AnchorPane TheNodeSetToBeVisible = (AnchorPane)fxmlLoader.getNamespace().get(nodeId);
		TheNodeSetToBeVisible.setVisible(true);
		
	}
	
	private void indicateActiveMenuButn(Event event) {
		Parent root = fxmlLoader.getRoot();
		Button activeBtn = (Button)root.lookup("#activeBtn");
		activeBtn.setId("menuBtn");
		Button btn = (Button)event.getSource();
		btn.setId("activeBtn");
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
        Button logout = (Button)fxmlLoader.getNamespace().get("logout");
		
		logout.setOnAction(e ->{
			Main main = new Main();
			main.start(Main.MainStage);
		}); 
		
		colCorseCode.setCellValueFactory( new PropertyValueFactory<>("courseCode"));
		colCourseName.setCellValueFactory( new PropertyValueFactory<>("CourseName"));
		colInstructor.setCellValueFactory( new PropertyValueFactory<>("instructorName"));
		colCredits.setCellValueFactory( new PropertyValueFactory<>("credit"));
		colPrerequest.setCellValueFactory( new PropertyValueFactory<>("Prerequest"));
		colNo.setCellValueFactory(new PropertyValueFactory<>("numericOrder"));
		
		   
		
		assCourseCode.setCellValueFactory( new PropertyValueFactory<>("courseCode"));
		assCourseName.setCellValueFactory( new PropertyValueFactory<>("courseName"));
		assInstructor.setCellValueFactory( new PropertyValueFactory<>("instructorName"));
		assCredit.setCellValueFactory( new PropertyValueFactory<>("credit"));
		assQ1.setCellValueFactory( new PropertyValueFactory<>("qu1"));
		assQ2.setCellValueFactory( new PropertyValueFactory<>("qu2"));
		assMid.setCellValueFactory( new PropertyValueFactory<>("mid"));
		assAsst.setCellValueFactory( new PropertyValueFactory<>("asst"));
		assFinal.setCellValueFactory( new PropertyValueFactory<>("final"));
		assTotal.setCellValueFactory( new PropertyValueFactory<>("total"));
		assStatus.setCellValueFactory( new PropertyValueFactory<>("status"));
		assGrade.setCellValueFactory( new PropertyValueFactory<>("grade"));
		stuId.setCellValueFactory( new PropertyValueFactory<>("no"));
        
		
		colCorseCode1.setCellValueFactory( new PropertyValueFactory<>("courseCode"));
		colCourseName1.setCellValueFactory( new PropertyValueFactory<>("CourseName"));
		colInstructor1.setCellValueFactory( new PropertyValueFactory<>("instructorName"));
		colCredits1.setCellValueFactory( new PropertyValueFactory<>("credit"));
		colPrerequest1.setCellValueFactory( new PropertyValueFactory<>("Prerequest"));
		colNo1.setCellValueFactory(new PropertyValueFactory<>("numericOrder"));
		
		
		assCourseName.setCellFactory(column -> new TableCell<>() {
		    private final Text text;

		    {
		        text = new Text();
		        text.wrappingWidthProperty().bind(column.widthProperty().subtract(10)); 
		        setGraphic(text); 
		    }
             
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            text.setText(null);
		        } else {
		            text.setText(item); 
		        }
		    }
		});
		
		assStatus.setCellFactory(column -> {
		    return new TableCell<Assesment ,String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty || item == null) {
		                setText(null);
		                setStyle("");
		            } else {
		                setText(item);
		                if ("incomplete".equalsIgnoreCase(item)) {
		                    setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size:20px");
		                } else if ("complete".equalsIgnoreCase(item)) {
		                    setStyle("-fx-background-color: green; -fx-text-fill: white;");
		                } else {
		                    setStyle("");
		                }
		            }
		        }
		    };
		});
		
	
		
	    

	}
	
	
}























