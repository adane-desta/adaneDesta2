package shallomCollege;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class LoginAs implements Initializable{
	
	public static String userType;
	
	@FXML
	private ToggleGroup logInAs;
	@FXML
	private Button loginAsNext;
	
	
	public static void loginas() throws IOException {
		
		Parent root = FXMLLoader.load(LoginAs.class.getResource("shallomLoginAsPage.fxml"));
		Scene scene = new Scene(root);
		Main.MainStage.setScene(scene);
		Main.MainStage.show();
		
	}
		 
	
	public void getUserType() {

		RadioButton selectedUserType = (RadioButton)logInAs.getSelectedToggle();
		if(selectedUserType != null) {
			userType = selectedUserType.getText();
			loginAsNext.setDisable(false);
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loginAsNext.setOnAction(e->{
			try {
				UserLogin.login(userType);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	
		
	}
	

}
