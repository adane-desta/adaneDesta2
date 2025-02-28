package shallomCollege;
	
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;


 
public class Main extends Application implements Initializable{
	
	public static Stage MainStage = new Stage();
	@FXML
	Button loginBtn;
	@FXML
	ScrollPane scc;

	@Override
	public void start(Stage stage) {
		try {
			Parent root ;
			FXMLLoader loader = new  FXMLLoader(getClass().getResource("shallomHomePage.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Shallom College");
			stage.show();
			MainStage = stage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loginBtn.setOnAction(e->{
			try {
				LoginAs.loginas();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
