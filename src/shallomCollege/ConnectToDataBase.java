package shallomCollege;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ConnectToDataBase {
	
	
      private static Connection connection;
      
	  
      private ConnectToDataBase() {
    	  
      }
      
      
//	public static Connection getConnected() {
//		
//		
//		try { 
//			
//			if(conn == null) {
//				
//				conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/shallomcollege" , "root", "");
//			}
//			
//			
//		} 
//		
//		
//		catch (SQLException e) {
//			e.printStackTrace();
//			Label label = new Label("unable to connect");
//			Button btn  = new Button("Ok");
//			AnchorPane root = new AnchorPane();
//			label.setStyle("-fx-font-size:20px; -fx-text-alignment:center; -fx-text-fill:white;");
//			btn.setMaxSize(50, 30);
//			label.setLayoutX(50);
//			label.setLayoutY(50); 
//			btn.setLayoutX(120);
//			btn.setLayoutY(120);
//			root.getChildren().add(label);
//			root.getChildren().add(btn);
//			root.setStyle("-fx-background-color:gray;");
//			Scene scene = new Scene(root , 300 , 180);
//			Stage stage = new Stage();
//			stage.setScene(scene);
//			stage.setTitle("Connection problem");
//			stage.setResizable(false);
//			stage.initModality(Modality.APPLICATION_MODAL);
//			stage.show();
//			btn.setOnAction(event ->{
//				stage.close();
//			});
//		}
//		
//		return conn;
//		
//	}
      
      public static Connection getConnected() {
    	  
          if (connection == null) {
              try {
                  
                  
            	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/shallomcollege" , "root", "");
                  System.out.println("Database connected successfully!");
              } catch (SQLException e) {
            	  unableToConnect();
              }
          }
          return connection;
      } 
      
      public static void  unableToConnect() {
    	  Label label = new Label("unable to connect");
			Button btn  = new Button("Ok");
			AnchorPane root = new AnchorPane();
			label.setStyle("-fx-font-size:20px; -fx-text-alignment:center; -fx-text-fill:white;");
			btn.setMaxSize(50, 30);
			label.setLayoutX(50);
			label.setLayoutY(50); 
			btn.setLayoutX(120);
			btn.setLayoutY(120);
			root.getChildren().add(label);
			root.getChildren().add(btn);
			root.setStyle("-fx-background-color:gray;");
			Scene scene = new Scene(root , 300 , 180);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Connection problem");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			btn.setOnAction(event ->{
				stage.close();
			});
      }

}
