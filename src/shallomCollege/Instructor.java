package shallomCollege;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.sun.prism.paint.Color;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Instructor implements Initializable{


	private static FXMLLoader fxmlLoader;

	private static String  fname, lname, phone, email;

	private static int instid;
	
	private static InputStream profilePicture;
	
	@FXML
	private Button setInstImage;
	
	@FXML
	ImageView instructorProfileImage;
	
	@FXML
	TextField FilterStudent;

	@FXML
	private  Label labelInstName,labelId,labelCourse1,labelCourse2,labelPhone,labelEmail,labelInstSetYourImage;

    


	@FXML
	private TableView<Assesment> assesmentTable ;
	@FXML
	private TableColumn<String , Assesment> assCourseCode;
	@FXML
	private TableColumn<String , Assesment> assCourseName;
	@FXML
	private TableColumn<String , Assesment> assStudentName;
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
	private TableColumn<String , Assesment> assStatus;
	@FXML
	private TableColumn<String , Assesment> assGrade;
	@FXML
	private TableColumn<Integer , Assesment> stuId;


	@FXML
	TableView<YourCourses> yourCourse;
	@FXML
	TableColumn<String, YourCourses>yourCoursecoCode;
	@FXML
	TableColumn<String, YourCourses>yourCoursecoName;
	@FXML
	TableColumn<Integer, YourCourses>yourCourseyrs;
	@FXML
	TableColumn<Integer, YourCourses>yourCoursesems;
	@FXML
	TableColumn<String, YourCourses>yourCoursedept;
	@FXML
	TableColumn<Integer, YourCourses>yourCoursecredit;
 
	
	@FXML
	TableView<YourStudents> yourStudent;
	@FXML
	TableColumn<String, YourStudents>studentName;
	@FXML
	TableColumn<String, YourStudents>corName;
	@FXML
	TableColumn<Integer, YourStudents>yr;
	@FXML
	TableColumn<Integer, YourStudents>sm;


	private ObservableList <Assesment> assesmentData = FXCollections.observableArrayList();
	private ObservableList <YourStudents> yourStudents = FXCollections.observableArrayList();
	private ObservableList <YourCourses> yorCourseData = FXCollections.observableArrayList();


	public static void instructor(String usName) throws IOException, SQLException {
		fxmlLoader = new FXMLLoader(Admin.class.getResource("shallomInstructorsProfile.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Main.MainStage.setScene(scene);
		setInstructorInfo(usName);
		Main.MainStage.show();

	}

	private static void setInstructorInfo(String uname) throws SQLException {

		String sql = "select * from instructor where username = ? ";
		Connection conn = ConnectToDataBase.getConnected();
		if(conn !=null) {
			PreparedStatement myst = conn.prepareStatement(sql);
		myst.setString(1, uname);
		ResultSet rst = myst.executeQuery();
      
		while(rst.next()) {
			fname = rst.getString("fname");
			lname = rst.getString("lname"); 
			email = rst.getString("email");
			phone = rst.getString("phone");
			instid = rst.getInt("instid");
			profilePicture = rst.getBinaryStream("image");

		}
		
		Image image = new Image(profilePicture);

		Label labelInstName = (Label)fxmlLoader.getNamespace().get("labelInstName");
		Label labelId = (Label)fxmlLoader.getNamespace().get("labelId");
		Label labelCourse1 = (Label)fxmlLoader.getNamespace().get("labelCourse1");
		Label labelCourse2 = (Label)fxmlLoader.getNamespace().get("labelCourse2");
		Label labelPhone = (Label)fxmlLoader.getNamespace().get("labelPhone");
		Label labelEmail = (Label)fxmlLoader.getNamespace().get("labelEmail");
		ImageView instructorProfileImage = (ImageView)fxmlLoader.getNamespace().get("instructorProfileImage");

		labelInstName.setText(fname + " " + lname);
		labelId.setText("InsId/"+fname.substring(0, 2)+lname.substring(0, 2)+Integer.toString(instid));
		labelCourse1.setText("TBA");
		labelCourse2.setText("TBA");
		labelPhone.setText("Phone :"+phone);
		labelEmail.setText("contact mail :"+email); 
		instructorProfileImage.setImage(image);
		 
		 
		}
		else {
			ConnectToDataBase.unableToConnect();
		}
		
		
		

	}

	public void instructorHome(Event event) throws SQLException {

		setVisibility("ancHome");
		indicateActiveMenuButn(event);
		yorCourseData.clear();

		String Sql = "SELECT * from instructorenrollment where instid = ?";
		Connection conn  = ConnectToDataBase.getConnected();
		
		
		if(conn != null) {
			
			PreparedStatement myst = conn.prepareStatement(Sql);
			myst.setInt(1, instid);

			ResultSet rst = myst.executeQuery();

			while(rst.next()) {

				YourCourses newRow = new YourCourses(rst.getString("coursecode") , getCourseName(rst.getString("coursecode"))
						,getDepartmentName(rst.getInt("dept")),getCourseCredit(rst.getString("coursecode")),rst.getInt("class"),rst.getInt("semster"));

				yorCourseData.add(newRow);
			}

			yorCourseData = yourCourse.getItems();
			 
			 
		}
		else {
			ConnectToDataBase.unableToConnect();
		}
		
		
		

	}

	public void updateStudentResult(Event event) throws SQLException {
        
		
		setVisibility("ancUpdateResult");
		indicateActiveMenuButn(event);
		
		assesmentData.clear();
		
		
		Connection conn = ConnectToDataBase.getConnected();
		
		if(conn != null) {
				
		String sql = "select * from studentenrollement;";
	
		Statement myst = conn.createStatement();
		ResultSet rst = myst.executeQuery(sql); 

		


		while(rst.next()) {

			String cCode = rst.getString("coursecode");

			Assesment assesmentRow = new Assesment(
					
					rst.getInt("studentid") ,
					getStudentName(rst.getInt("studentid")),
					cCode,
					getCourseCredit(cCode),
					getCourseName(cCode),
					rst.getDouble("quize1"),
					rst.getDouble("quize2"),
					rst.getDouble("midExam"),
					rst.getDouble("assignment"),
					rst.getDouble("finalExam"),
					rst.getDouble("totalPoint"),
					rst.getString("grade"),
					rst.getString("status"));

			assesmentData.add(assesmentRow);
		}


		assesmentTable.setItems(assesmentData);
		
		 
		
		}
		
		else {
			
			ConnectToDataBase.unableToConnect();
		}
	
		
		
		
		

	}

	public void udateResult2() throws IOException, SQLException {
		
		
		Assesment selectedStudent = assesmentTable.getSelectionModel().getSelectedItem();
		
		if(selectedStudent != null) {
					UpdateStudentResult selectedstudent = new UpdateStudentResult(selectedStudent);
		
		selectedstudent.setResult();
		}
	

	}
	
	public String getCourseName(String courseCod) throws SQLException {

		String sql = "select coursename from courses where coursecode  = ?";
		Connection conn = ConnectToDataBase.getConnected();
		
		if(conn != null) {
			PreparedStatement myst = conn.prepareStatement(sql);
			myst.setString(1, courseCod);
			ResultSet rst = myst.executeQuery();

			String name = "" ;
			while(rst.next()) {
				name = rst.getString("coursename");
			}
			 
			return name;
		}
		else {
			ConnectToDataBase.unableToConnect();
		}
		
		return "";
		
	
	}
	public int getCourseCredit(String courseCod) throws SQLException {

		String sql = "select credits from courses where coursecode = ?";
		Connection conn = ConnectToDataBase.getConnected();
		
		if(conn != null) {
			PreparedStatement myst = conn.prepareStatement(sql);
		myst.setString(1, courseCod);
		ResultSet rst = myst.executeQuery();

		int credit = 0 ;
		while(rst.next()) {
			credit = rst.getInt("credits");
		}
		 
		return credit;
		}
		else {
			ConnectToDataBase.unableToConnect();
			return 0;
		}
		
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
	public String getStudentName(int id) throws SQLException {

		String sql = "select concat(fname,' ',lname) as 'student' from student where studentid =  "+id;
		Statement myst = ConnectToDataBase.getConnected().createStatement();
		ResultSet rst = myst.executeQuery(sql);

		String name = "" ;
		while(rst.next()) {
			name = rst.getString("student");
		}
		return name;
	}

	public String getDepartmentName(int id) throws SQLException {

		String sql = "select deptname from department where deptid = ?";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		myst.setInt(1, id);
		ResultSet rst = myst.executeQuery();

		String name = "" ;
		while(rst.next()) {
			name = rst.getString("deptname");
		}
		return name;
	}

	public void yourStudents(Event event) throws SQLException {

		setVisibility("ancYourStudent");
		indicateActiveMenuButn(event);
		yourStudents.clear();

		String sql = "select * from studentenrollement where instructor = ?";
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		myst.setInt(1, instid);
		ResultSet rst = myst.executeQuery();
		while(rst.next()) {

			YourStudents newRow = new YourStudents(

                    getStudentName(rst.getInt("studentid")),
					getCourseName(rst.getString("coursecode")),
					rst.getInt("class"),
					rst.getInt("semster"));
            
			yourStudents.add(newRow); 
		}

		yourStudents = yourStudent.getItems();


	}
	public void report(Event event) {

		setVisibility("ancReport");
		indicateActiveMenuButn(event);
	}

	private void setVisibility(String nodeId) {

		AnchorPane ancHome = (AnchorPane)fxmlLoader.getNamespace().get("ancHome");
		AnchorPane ancUpdateResult = (AnchorPane)fxmlLoader.getNamespace().get("ancUpdateResult");
		AnchorPane ancYourStudent = (AnchorPane)fxmlLoader.getNamespace().get("ancYourStudent");
		AnchorPane ancReport = (AnchorPane)fxmlLoader.getNamespace().get("ancReport");
		ancHome.setVisible(false);
		ancUpdateResult.setVisible(false);
		ancYourStudent.setVisible(false);
		ancReport.setVisible(false);

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
		
		assCourseCode.setCellValueFactory( new PropertyValueFactory<>("courseCode"));
		assCourseName.setCellValueFactory( new PropertyValueFactory<>("courseName"));
		assStudentName.setCellValueFactory( new PropertyValueFactory<>("studentName"));
		assCredit.setCellValueFactory( new PropertyValueFactory<>("credit"));
		assQ1.setCellValueFactory( new PropertyValueFactory<>("qu1"));
		assQ2.setCellValueFactory( new PropertyValueFactory<>("qu2"));
		assMid.setCellValueFactory( new PropertyValueFactory<>("mid"));
		assAsst.setCellValueFactory( new PropertyValueFactory<>("asst"));
		assFinal.setCellValueFactory( new PropertyValueFactory<>("final"));
		assTotal.setCellValueFactory( new PropertyValueFactory<>("total"));
		assStatus.setCellValueFactory( new PropertyValueFactory<>("status"));
		assGrade.setCellValueFactory( new PropertyValueFactory<>("grade"));
		stuId.setCellValueFactory( new PropertyValueFactory<>("studentid"));




		yourCoursecoCode.setCellValueFactory( new PropertyValueFactory<>("yourCoursecourseCode"));

		yourCoursecoName.setCellValueFactory( new PropertyValueFactory<>("yourCoursecourseName"));

		yourCourseyrs.setCellValueFactory( new PropertyValueFactory<>("yourCourseyear"));

		yourCoursesems.setCellValueFactory( new PropertyValueFactory<>("yourCoursesemster"));

		yourCoursedept.setCellValueFactory( new PropertyValueFactory<>("yourCoursedepartment"));

		yourCoursecredit.setCellValueFactory( new PropertyValueFactory<>("yourCoursecredit"));



		studentName.setCellValueFactory( new PropertyValueFactory<>("studentNam"));

		corName.setCellValueFactory( new PropertyValueFactory<>("corName"));

		yr.setCellValueFactory( new PropertyValueFactory<>("yr"));

		sm.setCellValueFactory( new PropertyValueFactory<>("sm"));
		
		
		setInstImage.setOnMouseEntered(e ->{
			labelInstSetYourImage.setVisible(true);
		});
		setInstImage.setOnMouseExited(e ->{
			labelInstSetYourImage.setVisible(false);
		});

		setInstImage.setOnAction(e ->{
			try {
			
			
			    String sql = "update instructor set image = ? where instid = "+instid;
				PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
				
				FileChooser chooseImage = new FileChooser();
				chooseImage.getExtensionFilters().add(new FileChooser.ExtensionFilter("imageFile", "*.png", "*.jpg" , "*.gif"));
				
				File instructorImage  = chooseImage.showOpenDialog(null);
				
				if(instructorImage !=null) {
					
					if(instructorImage.length()/(1024.0*1024.0)>7) {
						JOptionPane.showMessageDialog(null, "The Image is larger than 7 MB, please choose a smaller file.");
					}
					else {
						
						Image image = new Image(instructorImage.toURI().toString());
						instructorProfileImage.setImage(image);
						
						FileInputStream input = new FileInputStream(instructorImage.getAbsolutePath());
						
						myst.setBinaryStream(1, input);
						myst.executeUpdate();
						
					}
				}
			}
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			} 
			catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		
		//adding filtration for the table 
		
		 
        FilteredList<Assesment> filteredData = new FilteredList<>(assesmentData, p -> true);

        
        assesmentTable.setItems(filteredData);

        
        FilterStudent.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(assesment -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                
                String lowerCaseFilter = newValue.toLowerCase();

                
                if (String.valueOf(assesment.getStudentid()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (assesment.getCourseCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (assesment.getCourseName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (assesment.getInstructorName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                  else if(assesment.getStudentName().toLowerCase().contains(lowerCaseFilter)) {
                	return true;
                }
                
                
                
                return false; 
            });
        });


	}



}

