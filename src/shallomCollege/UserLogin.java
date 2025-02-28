package shallomCollege;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserLogin implements Initializable{
	
	private static String userType;
	
	
	@FXML
	Button loginBtn;
	@FXML
	TextField userName;
	@FXML
	PasswordField passWord;
	
	
	public static void login(String usertype) throws IOException {
		userType = usertype;
		System.out.println(userType);
		Parent root = FXMLLoader.load(UserLogin.class.getResource("shallomLoginPage.fxml"));
		Scene scene = new Scene(root);
		Main.MainStage.setScene(scene);
		Main.MainStage.show();
	}
	
	public void validateUser() throws SQLException, IOException {
		
		if(passWord.getText().isEmpty() || userName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "please enter username and password");
		}
		
		else {
		String username = userName.getText();
		String password = passWord.getText();
		String uName = "";
		String pWord = "";
		String sql = "select * from "+userType+" where username = ? and password = ?";
	
		PreparedStatement myst = ConnectToDataBase.getConnected().prepareStatement(sql);
		
		myst.setString(1, username);
		myst.setString(2, password);
		ResultSet rst = myst.executeQuery();
		
		while(rst.next()) {
			uName = rst.getString("username");
			pWord = rst.getString("password");
		}
		
		if(uName.equals(username)&& pWord.equals(password)) {
			
			if(userType.equals("Student")) {
				
				Student.student(uName);
			}
			if(userType.equals("Instructor")) {

				Instructor.instructor(uName);

			}
			if(userType.equals("Admin")) {

				Admin.admin(uName);
			} 
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Wrong username or password \n\t\ttry Again");
			userName.clear();
			passWord.clear();
		}
	
		
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
