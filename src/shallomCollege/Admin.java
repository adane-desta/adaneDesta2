package shallomCollege;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Admin implements Initializable{
	
	private static FXMLLoader fxmlLoader;
	
	private static String  fname, lname, gender, email, phone, officenumber,depart , newStudentGender ;
	private static InputStream ProfilePicture;
	
	private static int adminid, department; 
	
	@FXML
	private  Label labelAdminName,labelDept,labelId,labelRespo,labelPhone,labelEmail,labelReporterUserType,labelReporterName ,labelAdminSetYourImage;
	@FXML
	VBox  reportVbox;
	@FXML
	TextArea reportBox;
	@FXML
	private ImageView adminProfilePicture;
	@FXML
	private TextField instFirstName , instLastName , courseCode , courseName , departmentName , semster , year ;
	
	@FXML
	private TextField newStudentFName , newStudentLName , newStudentAge , newStudentPhone , newStudentEmail  , 
	newStudentSemster , newStudentYear , newStudentGpa , newStudentCgpa;
	
	@FXML
	ChoiceBox<String> genderChoiceBox , newStudentDepartment;
	
	@FXML
	Button assignInstructor , addTheStudent , deleteStudentButton , setAdminImage;
	
	@FXML
	TableView<StudentView> studentView;
	@FXML
	TableColumn<Integer , StudentView> studentVNo;
	@FXML
	TableColumn<Integer , StudentView> studentVStudentId;
	@FXML
	TableColumn<String , StudentView> studentVFName;
	@FXML
	TableColumn<String , StudentView> studentVLName;
	@FXML
	TableColumn<String , StudentView> studentVDpt;
	@FXML
	TableColumn<String , StudentView> studentVClass;
	
	@FXML 
	TableView<StudentView> deleteStudentTable;
	@FXML
	TableColumn<Integer , StudentView> studentVNo1;
	@FXML
	TableColumn<Integer , StudentView> studentVStudentId1;
	@FXML
	TableColumn<String , StudentView> studentVFName1;
	@FXML
	TableColumn<String , StudentView> studentVLName1;
	@FXML
	TableColumn<String , StudentView> studentVDpt1;
	@FXML
	TableColumn<String , StudentView> studentVClass1;
	
	private ObservableList<StudentView>allStudents = FXCollections.observableArrayList();
	private ObservableList<StudentView>deleteStudent = FXCollections.observableArrayList();
	
	
	public static void admin(String usName) throws IOException, SQLException {
		fxmlLoader = new FXMLLoader(Admin.class.getResource("shallomAdminPage.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Main.MainStage.setScene(scene);
		setAdminInfo(usName);
		Main.MainStage.show();
		
	}
	
	private static void setAdminInfo(String uname) throws SQLException {
		String sql = "select * from admin where username = ? ";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		myst.setString(1, uname);
		ResultSet rst = myst.executeQuery();
		
		while(rst.next()) {
			fname = rst.getString("fname");
			lname = rst.getString("lname"); 
			gender = rst.getString("gender");
			email = rst.getString("email");
			phone = rst.getString("phone");
			officenumber = rst.getString("officenumber");
			adminid = rst.getInt("adminid");
			department = rst.getInt("department"); 
			ProfilePicture = rst.getBinaryStream("image");
            
		}
		depart = "";
		sql = "select deptname from department where deptid = ?";
	    myst = ConnectToDataBase.getConnected().prepareStatement(sql);
	    myst.setInt(1, department);
	    rst = myst.executeQuery();
	    while(rst.next()) {
	    	depart = rst.getString("deptname");
	    }
	    Label labelAdminName = (Label)fxmlLoader.getNamespace().get("labelAdminName");
	    Label labelDept = (Label)fxmlLoader.getNamespace().get("labelDept");
	    Label labelId = (Label)fxmlLoader.getNamespace().get("labelId");
	    Label labelRespo = (Label)fxmlLoader.getNamespace().get("labelRespo");
	    Label labelPhone = (Label)fxmlLoader.getNamespace().get("labelPhone");
	    Label labelEmail = (Label)fxmlLoader.getNamespace().get("labelEmail");
	    ImageView adminProfilePicture = (ImageView)fxmlLoader.getNamespace().get("adminProfilePicture");
	    
	    System.out.println(department);
	    Image image = new Image(ProfilePicture);
	    adminProfilePicture.setImage(image);
	    labelAdminName.setText(fname + " " + lname);
	    labelDept.setText("Head of "+depart +" "+"Department");
	    labelId.setText("AdmId/"+depart.substring(0, 2)+depart.substring(depart.lastIndexOf(" ")+1,depart.lastIndexOf(" ")+3)+"/"+Integer.toString(adminid));
	    labelRespo.setText("coordinating" +" "+depart+" department including assigning instructur and adding and deleting students");
		labelPhone.setText("Phone :"+phone);
		labelEmail.setText("contact mail :"+email); 
		
		
	}
	
	
	public void adminHome(Event event) {

		
		setVisibility("ancHome");
		indicateActiveMenuButn(event);
	}
	public void students(Event event) throws SQLException{
		
		setVisibility("ancStudents");
		indicateActiveMenuButn(event);
		allStudents.clear();
		
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery("select * from student;");
		Instructor inst = new Instructor();
		
		int no = 0;
		while(rst.next()) {
			
			no++;
			StudentView Student = new StudentView(no, rst.getInt("studentid"),rst.getInt("class"), rst.getString("fname"), rst.getString("lname"),inst.getDepartmentName(rst.getInt("dept")));
			
			allStudents.add(Student);
		}
		
		allStudents = studentView.getItems();
		
		
	
	}
	
	public void studentDetail() throws IOException, SQLException {
		
		studentDetail2(studentView.getSelectionModel().getSelectedItem());
		
	}
	
	private void studentDetail2(StudentView selectedStudent) throws IOException, SQLException {
		
		FXMLLoader loader = new FXMLLoader(Admin.class.getResource("studentDetail.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		Label studentDetailName = (Label)loader.getNamespace().get("studentDetailName");
		Label studentDetailDept = (Label)loader.getNamespace().get("studentDetailDept");
		Label studentDetailYearSemster = (Label)loader.getNamespace().get("studentDetailYearSemster");
		TextField studentDetailGpa = (TextField)loader.getNamespace().get("studentDetailGpa");
		TextField studentDetailCgpa = (TextField)loader.getNamespace().get("studentDetailCgpa");
		TextField studentDetailAge = (TextField)loader.getNamespace().get("studentDetailAge");
		TextField studentDetailPhone = (TextField)loader.getNamespace().get("studentDetailPhone");
		TextField studentDetailEmail = (TextField)loader.getNamespace().get("studentDetailEmail");
		TextArea studentDetailMessageArea = (TextArea)loader.getNamespace().get("studentDetailMessageArea");
		Button studentDetailBtnSendMessage = (Button)loader.getNamespace().get("studentDetailBtnSendMessage");
		Button studentDetailBtnSetImage = (Button)loader.getNamespace().get("studentDetailBtnSetImage");
		ImageView studentDetailImageView = (ImageView)loader.getNamespace().get("studentDetailImageView");
		
		String sql = "select * from student where studentid = ?";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		myst.setInt(1, selectedStudent.getId());
		ResultSet rst = myst.executeQuery();
		InputStream studentPicture = null;
		
		studentDetailName.setText(getStudentName(selectedStudent.getId()));
		studentDetailDept.setText(selectedStudent.getDepartment());
		
		while(rst.next()) {
			studentDetailGpa.setText(Double.toString(rst.getDouble("gpa")));
			studentDetailCgpa.setText(Double.toString(rst.getDouble("cgpa")));
			studentDetailAge.setText(Integer.toString(rst.getInt("age")));
			studentDetailEmail.setText(rst.getString("email"));
			studentDetailPhone.setText(rst.getString("phone"));
			studentDetailYearSemster.setText("Year  "+rst.getInt("class")+"  "+"Semster  "+rst.getInt("semster"));
			studentPicture = rst.getBinaryStream("image");
			
		}
		if(studentPicture !=null) {
			Image image = new Image(studentPicture);
		    studentDetailImageView.setImage(image);
		}
		
		stage.show();
		
		studentDetailBtnSendMessage.setOnAction(e ->{
			if(!(studentDetailMessageArea.getText().isBlank())) {
				JOptionPane.showConfirmDialog(null, "Send The Following Message To "+studentDetailName.getText()+"\n"+studentDetailMessageArea.getText() ,
				"CONFIRM", JOptionPane.YES_NO_OPTION);
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Write Down The Message First");
				studentDetailMessageArea.clear();
			}
		});
		
		studentDetailBtnSetImage.setOnAction(e ->{
			FileChooser setImage = new FileChooser();
			setImage.getExtensionFilters().add(new FileChooser.ExtensionFilter("setImage", "*.jpg" , "*.png" , "*.gif"));
			File studentImage = setImage.showOpenDialog(null);
			if(studentImage !=null) {
				if(studentImage.length()/(1024.0*1024.0)>7) {
					JOptionPane.showMessageDialog(null, "The Image Is Larger Than 7MB Please Choose Smaller File");
				}
				else {
					try {
						Image image2 = new Image(studentImage.toURI().toString());
						studentDetailImageView.setImage(image2);
						FileInputStream inputImage = new FileInputStream(studentImage.getAbsolutePath());
						String sql2 = "update student set image = ? where studentid = "+selectedStudent.getId();
						PreparedStatement myst2 = ConnectToDataBase.getConnected().prepareStatement(sql2);
						myst2.setBinaryStream(1, inputImage);
						myst2.executeUpdate();
						System.out.println("done!!!!!!!!!!!!");

					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				    
				}
				
			}
			
		});
	}
	
    public void assignInstructor(Event event){
		
		setVisibility("ancAssignInstructor");
		indicateActiveMenuButn(event);
	}
    public void addStudent(Event event){
		
		setVisibility("ancAddStudent");
		indicateActiveMenuButn(event);
	}
    
    public void deleteStudent(Event event) throws SQLException{
		
		setVisibility("ancDeleteStudent");
		indicateActiveMenuButn(event);
		deleteStudent.clear();
		
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery("select * from student;");
		Instructor inst = new Instructor();
		
		int no = 0;
		while(rst.next()) {
			
			no++;
			StudentView Student = new StudentView(no, rst.getInt("studentid"),rst.getInt("class"), rst.getString("fname"), rst.getString("lname"),inst.getDepartmentName(rst.getInt("dept")));
			
			deleteStudent.add(Student);
		}
		
		deleteStudent = deleteStudentTable.getItems();
		
	}
  
    public void  reports(Event event) throws SQLException, IOException{
		
		setVisibility("ancReport");
		indicateActiveMenuButn(event);
		reportVbox.getChildren().clear();
		String sql = "select * from report\r\n"
				+ "ORDER BY date DESC;";
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery(sql);
		
		
		while(rst.next()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("report.fxml"));
			Parent root = loader.load();
			Label labelReporterUserType = (Label)loader.getNamespace().get("labelReporterUserType");
			Label labelReporterName = (Label)loader.getNamespace().get("labelReporterName");
			Label reportDate = (Label)loader.getNamespace().get("reportDate");
			TextArea reportBox = (TextArea)loader.getNamespace().get("reportBox");
			ImageView reporterImage = (ImageView)loader.getNamespace().get("reporterImage");
			labelReporterUserType.setText(rst.getString("usertype"));
			reportDate.setText(rst.getString("date"));
			labelReporterName.setText(getStudentName(rst.getInt("reporterId")));
			reportBox.setText(rst.getString("reportContent"));
			
			if(getImage(rst.getInt("reporterId")) != null) {
				reporterImage.setImage(getImage(rst.getInt("reporterId")));
			}
				
			
			reportVbox.getChildren().add(root);
			
		
		}
	}
    
    
    
    public String getStudentName(int id) throws SQLException {
    	String sql = "select concat(fname , \" \" , lname) as \"stuName\" from student where studentid = ?;";
    	PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
    	myst.setInt(1, id);
    	ResultSet rst = myst.executeQuery();
    	
    	String name = "";
    	while(rst.next()) {
    		name = rst.getString("stuName");
    	}
    	
    	return name;
    	
    }
    
    public void  ManageRegistration(Event event) throws SQLException{
		
		setVisibility("ancRegistration");
		indicateActiveMenuButn(event);
		Label status;
		Button openClose;
		String sql = "select * from regstration where dept = "+department+" ORDER BY dept , class , semster";
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery(sql);
		while(rst.next()) {
			
			status = (Label)fxmlLoader.getNamespace().get("st"+Integer.toString(rst.getInt("class"))+Integer.toString(rst.getInt("semster")));
			status.setText(rst.getString("regStatus"));
			openClose = (Button)fxmlLoader.getNamespace().get("op"+Integer.toString(rst.getInt("class"))+Integer.toString(rst.getInt("semster")));
		    
		    if(rst.getString("regStatus").equals("open")) {
		    	
		    	openClose.setText("Close Registration");
		    	status.setStyle("-fx-background-color:#06f206");
		    	
		    }
		    else {
		    	
		    	openClose.setText("open Registration");
		    	status.setStyle("-fx-background-color:red");
		    	
		    }
		}
	} 
    
    public void openOrCloseRegistration(Event event) throws SQLException {
    	
    	
    	Button btn = (Button)event.getSource();
        
    	String text , sql ,regStatus = "";

    	int year , sems;     
    	
    	String sql2 = "select * from regstration where dept = ? and class = ? and semster = ?";

    	text = btn.getAccessibleText();
    	year = Integer.parseInt(text)/10;
    	sems = Integer.parseInt(text)%10;
    	PreparedStatement myst2 = ConnectToDataBase.getConnected().prepareStatement(sql2);
    	myst2.setInt(1, department);
    	myst2.setInt(2, year);
    	myst2.setInt(3, sems);
    	ResultSet rst2 = myst2.executeQuery();
    	
    	
    	while(rst2.next()) {             
    		regStatus = rst2.getString("regStatus");
    	}
    	
    	int answer = JOptionPane.showConfirmDialog(null, "Are you sure?","Confirm" ,JOptionPane.YES_NO_OPTION);
    	
    	if(answer == 0) {
    		
    	
    	
    	if(regStatus.equals("open")) {
    		String Setdb = "closed";
    		sql = "update regstration "
        			+ "set regStatus = ?"
        			+ "where class = ? and semster = ? and dept = ?";
    		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
    		myst.setString(1, Setdb);
    		myst.setInt(2, year);
        	myst.setInt(3, sems);
        	myst.setInt(4, department);
        	        	
        	int rst = myst.executeUpdate();
        	JOptionPane.showMessageDialog(null, "The Registration has been successfully closed"+"\n"+ "for "+depart+" year "+year+" semster "+sems );
        	btn.setText("open Registration");
        	Label stat = (Label)fxmlLoader.getNamespace().get("st"+text);
			stat.setText("closed");
			stat.setStyle("-fx-background-color:red");
    	}
    	else {
    		String Setdb = "open";
    		sql = "update regstration "
        			+ "set regStatus = ?"
        			+ "where class = ? and semster = ? and dept = ?";
    		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
    		myst.setString(1, Setdb);
    		myst.setInt(2, year);
        	myst.setInt(3, sems);
        	myst.setInt(4, department);
        	
        	int rst = myst.executeUpdate();
        	JOptionPane.showMessageDialog(null, "The Registration has been successfully opened"+"\n"+ "for "+depart+" year "+year+" semster "+sems );
        	btn.setText("close Registration");
        	Label stat = (Label)fxmlLoader.getNamespace().get("st"+text);
			stat.setText("open");
			stat.setStyle("-fx-background-color:#06f206");
    	}
    	
    	}
    	
    	else {
    		JOptionPane.showMessageDialog(null, "the action has been Canceld");
    	}
    	
    	
    	
    	
    

    }
   	
	private void setVisibility(String nodeId) {
		
		AnchorPane ancHome = (AnchorPane)fxmlLoader.getNamespace().get("ancHome");
		AnchorPane ancStudents = (AnchorPane)fxmlLoader.getNamespace().get("ancStudents");
		AnchorPane ancAssignInstructor = (AnchorPane)fxmlLoader.getNamespace().get("ancAssignInstructor");
		AnchorPane ancAddStudent = (AnchorPane)fxmlLoader.getNamespace().get("ancAddStudent");
		AnchorPane ancDeleteStudent = (AnchorPane)fxmlLoader.getNamespace().get("ancDeleteStudent");
		AnchorPane ancReport = (AnchorPane)fxmlLoader.getNamespace().get("ancReport");
		AnchorPane ancRegistration = (AnchorPane)fxmlLoader.getNamespace().get("ancRegistration");
		ancHome.setVisible(false);
		ancStudents.setVisible(false);
		ancAssignInstructor.setVisible(false);
		ancAddStudent.setVisible(false);
		ancDeleteStudent.setVisible(false);
		ancReport.setVisible(false);
		ancRegistration.setVisible(false);
		
		AnchorPane TheNodeSetToBeVisible = (AnchorPane)fxmlLoader.getNamespace().get(nodeId);
		TheNodeSetToBeVisible.setVisible(true);
		
	}
	
	private Image getImage(int id) throws SQLException {
		
		String sql = "select image from student where studentid = ?";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		myst.setInt(1, id);
		ResultSet rst = myst.executeQuery();
		InputStream input = null;
		
		while(rst.next()) {
			input = rst.getBinaryStream("image");
		}
		
		return new Image(input);
				
	}
	
	private void indicateActiveMenuButn(Event event) {
		Parent root = fxmlLoader.getRoot();
		Button activeBtn = (Button)root.lookup("#activeBtn");
		activeBtn.setId("menuBtn");
		Button btn = (Button)event.getSource();
		btn.setId("activeBtn");
		
	}

	private void assignInstructor() {
		JOptionPane.showMessageDialog(null, "the Instructor Has Been Successfully Assigned");
	}
	
	private void addTheStudent() throws SQLException {
		
		
		String Sql = "insert into student("
				+ "fname, "
				+ "lname, "
				+ "gender,"
				+ "age, "
				+ "username, "
				+ "password, "
				+ "email, "
				+ "phone, "
				+ "dept,"
				+ "class, "
				+ "semster , "
				+ "gpa ,"
				+ "cgpa)"
		        +"VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		Random random = new Random();
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(Sql);
		myst.setString(1, newStudentFName.getText());
		myst.setString(2, newStudentLName.getText());
		myst.setString(3, genderChoiceBox.getValue());
		myst.setInt(4, Integer.parseInt(newStudentAge.getText()));
		myst.setString(5, newStudentFName.getText()+Integer.toString(random.nextInt(9000)+1000)+newStudentLName.getText());
		myst.setString(6, newStudentFName.getText()+Integer.toString(random.nextInt(9000)+1000));
		myst.setString(7, newStudentEmail.getText());
		myst.setString(8, newStudentPhone.getText());
		myst.setInt(9, Integer.parseInt(newStudentDepartment.getValue().substring(0, newStudentDepartment.getValue().indexOf(" "))));
		myst.setInt(11, Integer.parseInt(newStudentSemster.getText()) );
		myst.setInt(10, Integer.parseInt(newStudentYear.getText()) );
		
		if(!newStudentGpa.getText().isEmpty() && !newStudentCgpa.getText().isEmpty()) {
			myst.setDouble(12, Double.parseDouble(newStudentGpa.getText()) );
			myst.setDouble(13, Double.parseDouble(newStudentCgpa.getText()));
		}
		else {
			myst.setDouble(12, 0.0 );
			myst.setDouble(13, 0.0);
		}
		
		
		myst.executeUpdate();
		
		int recentStudentId = 0;
		String sql12 = "select studentid from student where phone = ?";
		PreparedStatement myst12 = ConnectToDataBase.getConnected().prepareStatement(sql12);
		myst12.setString(1, newStudentPhone.getText());
		ResultSet rst12 = myst12.executeQuery();
		while(rst12.next()) {
			recentStudentId = rst12.getInt("studentid");
		}
		
		String Sql4 = "select course_department.coursecode ,course_department.semster , course_department.class , instructorenrollment.instid\r\n"
				+ " from course_department , instructorenrollment\r\n "
				+ " where course_department.class = "+Integer.parseInt(newStudentYear.getText())
				+ " and  course_department.semster = "+Integer.parseInt(newStudentSemster.getText())
				+ " and course_department.deptid = "
				+Integer.parseInt(newStudentDepartment.getValue().substring(0, newStudentDepartment.getValue().indexOf(" ")))
				+ " and instructorenrollment.coursecode = course_department.coursecode and instructorenrollment.semster ="
				+Integer.parseInt(newStudentSemster.getText())
				+ " and instructorenrollment.class = "+Integer.parseInt(newStudentYear.getText())
				+ " and instructorenrollment.dept = "
				+Integer.parseInt(newStudentDepartment.getValue().substring(0, newStudentDepartment.getValue().indexOf(" ")));
		
		Statement myst14 = ConnectToDataBase.getConnected().createStatement();
		ResultSet CurrentCoursesRegistered =myst14.executeQuery(Sql4);
		
		String sql4 = " select * from course_department where semster = ? and class = ? and deptid = ?;";
		
		PreparedStatement myst4 = ConnectToDataBase.getConnected().prepareStatement(sql4);
		myst4.setInt(1, Integer.parseInt(newStudentSemster.getText()));
		myst4.setInt(2, Integer.parseInt(newStudentYear.getText()));
		myst4.setInt(3, Integer.parseInt(newStudentDepartment.getValue().substring(0, newStudentDepartment.getValue().indexOf(" "))));
		ResultSet currentCoursesRegistered = myst4.executeQuery();
		
		String sql5 = "insert into studentenrollement(studentid , coursecode ,semster , class, instructor)\r\n"
				+ "values(? ,? ,? ,? ,?);";
		PreparedStatement mypreState = ConnectToDataBase.getConnected().prepareStatement(sql5);
		
		while(currentCoursesRegistered.next()&& CurrentCoursesRegistered.next() ) {
			
			mypreState.setInt(1, recentStudentId);
			mypreState.setString(2, currentCoursesRegistered.getString("coursecode"));
			mypreState.setInt(3, Integer.parseInt(newStudentSemster.getText()));
			mypreState.setInt(4, Integer.parseInt(newStudentYear.getText()));
			mypreState.setInt(5, CurrentCoursesRegistered.getInt("instid"));
			int enter = mypreState.executeUpdate();
			
		}
		
		JOptionPane.showMessageDialog(null, "The Student Has Been Added successfully");
		
	 newStudentFName.clear(); 
	 newStudentLName.clear();  
	 newStudentAge.clear();  
	 newStudentPhone.clear(); 
	 newStudentEmail.clear(); 
	 newStudentDepartment.setValue(null); 
	 newStudentSemster.clear(); 
	 newStudentYear.clear(); 
	 newStudentGpa.clear(); 
	 newStudentCgpa.clear();
	 genderChoiceBox.setValue(null);
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Button logout = (Button)fxmlLoader.getNamespace().get("logout");
		
		logout.setOnAction(e ->{
			Main main = new Main();
			main.start(Main.MainStage);
		});
		
		studentVNo.setCellValueFactory( new PropertyValueFactory<>("no"));
		studentVStudentId.setCellValueFactory( new PropertyValueFactory<>("id"));
		studentVClass.setCellValueFactory( new PropertyValueFactory<>("yr"));
		studentVFName.setCellValueFactory( new PropertyValueFactory<>("fName"));
		studentVLName.setCellValueFactory( new PropertyValueFactory<>("lName"));
		studentVDpt.setCellValueFactory( new PropertyValueFactory<>("department"));
		
		//deleteStudentTable columns
		
		studentVNo1.setCellValueFactory( new PropertyValueFactory<>("no"));
		studentVStudentId1.setCellValueFactory( new PropertyValueFactory<>("id"));
		studentVClass1.setCellValueFactory( new PropertyValueFactory<>("yr"));
		studentVFName1.setCellValueFactory( new PropertyValueFactory<>("fName"));
		studentVLName1.setCellValueFactory( new PropertyValueFactory<>("lName"));
		studentVDpt1.setCellValueFactory( new PropertyValueFactory<>("department"));
		
		deleteStudentButton.setOnAction(e ->{
			if(deleteStudentTable.getSelectionModel().getSelectedItem()== null) {
				JOptionPane.showMessageDialog(null, "Please Select The Student To Be Deleted");
			}
			else {
				System.out.println(deleteStudentTable.getSelectionModel().getSelectedItem().getId());
				deleteStudentTable.setEditable(true);
			}
		});
		
		String nameRegEx = "^[A-Za-z]{3,35}$";
		String CourseNameRegx = "^[A-Za-z]{3,50}.*\s*";
		String courseCodeRegEx = "^[A-Za-z0-9]{4,10}$";
		String semsterRegEx = "^[1-2]{1}$";
		String yearRegEx = "^[1-5]{1}$";
		String ageRegEx = "^[1-9]{1}\\d{1,2}$";
		String emailRegEx = "^[A-Za-z]{3,20}[A-Za-z0-9]{0,20}@{1}[A-Za-z]{3,20}\\.com{1}$";
		String gpaRegEx = "^[0-4]{0,1}(\\.\\d+){0,5}$";
		String PhoneRegEx = "^(\\+2519|09|07|\\+2517){1}[0-9]{8}$";
		
		instFirstName.setOnKeyTyped(e ->{
			if(instFirstName.getText().matches(nameRegEx)) {
				instFirstName.setId("validInput");
				instFirstName.setAccessibleText("valid");
			}
			else {
				instFirstName.setAccessibleText("invalid");
				instFirstName.setId("invalidInput");
			}
		});
		instLastName.setOnKeyTyped(e ->{
			if(instLastName.getText().matches(nameRegEx)) {
				instLastName.setId("validInput");
				instLastName.setAccessibleText("valid");
			}
			else {
				instLastName.setAccessibleText("invalid");
				instLastName.setId("invalidInput");
			}
		});
		courseName.setOnKeyTyped(e ->{
			if(courseName.getText().matches(CourseNameRegx)) {
				courseName.setId("validInput");
				courseName.setAccessibleText("valid");
			}
			else {
				courseName.setAccessibleText("invalid");
				courseName.setId("invalidInput");
			}
		});
		departmentName.setOnKeyTyped(e ->{
			if(departmentName.getText().matches(CourseNameRegx)) {
				departmentName.setId("validInput");
				departmentName.setAccessibleText("valid");
			}
			else {
				departmentName.setAccessibleText("invalid");
				departmentName.setId("invalidInput");
			}
		});
		courseCode.setOnKeyTyped(e ->{
			if(courseCode.getText().matches(courseCodeRegEx)) {
				courseCode.setId("validInput");
				courseCode.setAccessibleText("valid");
			}
			else {
				courseCode.setAccessibleText("invalid");
				courseCode.setId("invalidInput");
			}
		});
		semster.setOnKeyTyped(e ->{
			if(semster.getText().matches(semsterRegEx)) {
				semster.setId("validInput");
				semster.setAccessibleText("valid");
			}
			else {
				semster.setAccessibleText("invalid");
				semster.setId("invalidInput");
			}
		});
		year.setOnKeyTyped(e ->{
			if(year.getText().matches(yearRegEx)) {
				year.setId("validInput");
				year.setAccessibleText("valid");
			}
			else {
				year.setAccessibleText("invalid");
				year.setId("invalidInput");
			}
		});
		
		assignInstructor.setOnAction(e ->{
			if(instFirstName.getAccessibleText().equals("valid")&&instLastName.getAccessibleText().equals("valid")&&departmentName.getAccessibleText().equals("valid")&&courseName.getAccessibleText().equals("valid")
					&&courseCode.getAccessibleText().equals("valid")&&year.getAccessibleText().equals("valid")&&semster.getAccessibleText().equals("valid"))
			{
				assignInstructor();
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Fill Up All Fields Correctly");
			}
		});
        
		try {
			
		ArrayList<String>departments = new ArrayList<String>();
        String[] gender = {"male" , "female"};
        
        String getDepartSql = "select * from department;";
        
		Statement mystGetDepart = ConnectToDataBase.getConnected().createStatement();
		ResultSet rstGetDepart = mystGetDepart.executeQuery(getDepartSql);
		
		while(rstGetDepart.next()) {
			
			departments.add(Integer.toString(rstGetDepart.getInt("deptid"))+"     "+ rstGetDepart.getString("deptname"));
		}
		
		genderChoiceBox.getItems().addAll(gender);
		newStudentDepartment.getItems().addAll(departments);
		
		} 
		
		catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		System.out.println(genderChoiceBox.getValue());
		
		newStudentFName.setOnKeyTyped(e ->{
			if(newStudentFName.getText().matches(nameRegEx)) {
				newStudentFName.setId("validInput");
				newStudentFName.setAccessibleText("valid");
			}
			else {
				newStudentFName.setAccessibleText("invalid");
				newStudentFName.setId("invalidInput");
			}
		});
		newStudentLName.setOnKeyTyped(e ->{
			if(newStudentLName.getText().matches(nameRegEx)) {
				newStudentLName.setId("validInput");
				newStudentLName.setAccessibleText("valid");
			}
			else {
				newStudentLName.setAccessibleText("invalid");
				newStudentLName.setId("invalidInput");
			}
		});
		newStudentAge.setOnKeyTyped(e ->{
			if(newStudentAge.getText().matches(ageRegEx)) {
				if(Integer.parseInt(newStudentAge.getText())>120) {
					newStudentAge.setAccessibleText("invalid");
			     	newStudentAge.setId("invalidInput");
				}
				else {
				newStudentAge.setId("validInput");
				newStudentAge.setAccessibleText("valid");
				}
			}
			else {
				newStudentAge.setAccessibleText("invalid");
		     	newStudentAge.setId("invalidInput");
			}
		});
		newStudentPhone.setOnKeyTyped(e ->{
			try {
				if(newStudentPhone.getText().matches(PhoneRegEx) && CheckForExistence.checkPhone(newStudentPhone.getText())) {
				newStudentPhone.setId("validInput");
				newStudentPhone.setAccessibleText("valid");
			}
			else {
				if(!CheckForExistence.checkPhone(newStudentPhone.getText())) {
					JOptionPane.showMessageDialog(null, "The Student With This Phone Number Is Already Exist");
					newStudentPhone.clear();
				}
				newStudentPhone.setAccessibleText("invalid");
				newStudentPhone.setId("invalidInput");
			}
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
			
		});
		newStudentEmail.setOnKeyTyped(e ->{
			
			
			try {
			if(newStudentEmail.getText().matches(emailRegEx) && CheckForExistence.checkEmail(newStudentEmail.getText())) {
				newStudentEmail.setId("validInput");
				newStudentEmail.setAccessibleText("valid");
			}
			else {
				if(!CheckForExistence.checkEmail(newStudentEmail.getText())) {
					JOptionPane.showMessageDialog(null, "The Student With This Email Address Is Already Exist");
					newStudentEmail.clear();
				}
				newStudentEmail.setAccessibleText("invalid");
				newStudentEmail.setId("invalidInput");
			}
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
		});
		newStudentSemster.setOnKeyTyped(e ->{
			
		
			
			if(newStudentSemster.getText().matches(semsterRegEx)) {

				if(!newStudentYear.getText().isEmpty()) {

					if(Integer.parseInt(newStudentYear.getText()) == 1 && Integer.parseInt(newStudentSemster.getText()) == 1 ) {
						newStudentGpa.setDisable(true);
						newStudentCgpa.setDisable(true);
						newStudentGpa.clear();
						newStudentCgpa.clear();
						newStudentCgpa.setAccessibleText("valid");
						newStudentGpa.setAccessibleText("valid");
					}

					else {
						System.out.println("from else");
						newStudentGpa.setDisable(false);
						newStudentCgpa.setDisable(false);
						newStudentGpa.clear();
						newStudentCgpa.clear();
						newStudentCgpa.setAccessibleText("invalid");
						newStudentGpa.setAccessibleText("invalid");
					}
				}
				newStudentSemster.setId("validInput");
				newStudentSemster.setAccessibleText("valid");
			} 
			else {
				newStudentSemster.setAccessibleText("invalid");
				newStudentSemster.setId("invalidInput");
			}
		});
		newStudentYear.setOnKeyTyped(e ->{
			
			
			if(newStudentYear.getText().matches(yearRegEx)) {

				if(!newStudentSemster.getText().isEmpty()) {

					if(Integer.parseInt(newStudentYear.getText()) == 1 && Integer.parseInt(newStudentSemster.getText()) == 1 ) {
						newStudentGpa.setDisable(true);
						newStudentCgpa.setDisable(true);
						newStudentGpa.clear();
						newStudentCgpa.clear();
						newStudentCgpa.setAccessibleText("valid");
						newStudentGpa.setAccessibleText("valid");
					}

					else {
						System.out.println("from else");
						newStudentGpa.setDisable(false);
						newStudentCgpa.setDisable(false);
						newStudentGpa.clear();
						newStudentCgpa.clear();
						newStudentCgpa.setAccessibleText("invalid");
						newStudentGpa.setAccessibleText("invalid");
					}
				}

				newStudentYear.setId("validInput");
				newStudentYear.setAccessibleText("valid");
			}
			else {
				newStudentYear.setAccessibleText("invalid");
				newStudentYear.setId("invalidInput");
			}
		});
		newStudentGpa.setOnKeyTyped(e ->{
			if(newStudentGpa.getText().matches(gpaRegEx)) {
				
				if(Double.parseDouble(newStudentGpa.getText())>4) {
					newStudentGpa.setAccessibleText("invalid");
					newStudentGpa.setId("invalidInput");
				} 
				else {
					newStudentGpa.setId("validInput");
				    newStudentGpa.setAccessibleText("valid");
				}
				
			}
			else {
				newStudentGpa.setAccessibleText("invalid");
				newStudentGpa.setId("invalidInput");
			}
		}); 
		newStudentCgpa.setOnKeyTyped(e ->{
			if(newStudentCgpa.getText().matches(gpaRegEx)) {
				if(Double.parseDouble(newStudentCgpa.getText())>4) {
					newStudentCgpa.setAccessibleText("invalid");
					newStudentCgpa.setId("invalidInput");
				}
				else {
					newStudentCgpa.setId("validInput");
			    	newStudentCgpa.setAccessibleText("valid");
				}
				
			}
			else {
				newStudentCgpa.setAccessibleText("invalid");
				newStudentCgpa.setId("invalidInput");
			}
		});

		addTheStudent.setOnAction(e ->{
			if(newStudentFName.getAccessibleText().equals("valid")&&
					newStudentLName.getAccessibleText().equals("valid")&&
					newStudentAge.getAccessibleText().equals("valid")&&
					newStudentPhone.getAccessibleText().equals("valid")
					&&newStudentEmail.getAccessibleText().equals("valid")&&
					newStudentSemster.getAccessibleText().equals("valid")&&
					newStudentYear.getAccessibleText().equals("valid")&&
					newStudentGpa.getAccessibleText().equals("valid")&&
					newStudentCgpa.getAccessibleText().equals("valid")&& 
					newStudentDepartment.getValue() != null &&
					genderChoiceBox.getValue() != null
					)
			{
				try {
					addTheStudent();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			
			else {
				
				JOptionPane.showMessageDialog(null, "Please Fill UP All Informations Correctly");
			}
		});
		
		setAdminImage.setOnMouseEntered(e ->{
			labelAdminSetYourImage.setVisible(true);
			
		});
		setAdminImage.setOnMouseExited(e ->{
			labelAdminSetYourImage.setVisible(false);
		});
		
		setAdminImage.setOnAction(e ->{
			
			try {

				String sql = "update admin set image = ? where adminid = "+adminid;

				PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);

				FileChooser chooseImage = new FileChooser();
				chooseImage.getExtensionFilters().add(new FileChooser.ExtensionFilter("imageFile", "*.png" , "*.jpg" , "*.gif" ));

				File adminImage = chooseImage.showOpenDialog(null);

				if(adminImage !=null) {
					if(adminImage.length()/(1024.0*1024.0)>7) {
						JOptionPane.showMessageDialog(null, "The Image is larger than 7 MB, please choose a smaller file.");
					}
					else {
						Image image = new Image(adminImage.toURI().toString());
						adminProfilePicture.setImage(image);

						FileInputStream input = new FileInputStream(adminImage.getAbsolutePath());
						myst.setBinaryStream(1, input);
						myst.executeUpdate();
						
					}
					

				}
			} 
			
			catch (SQLException e1) {
				
				System.out.println("some thing went wrong1");
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				
				System.out.println("some thing went wrong2");
				e1.printStackTrace();
			}
			
			
		});
	}
	
}
