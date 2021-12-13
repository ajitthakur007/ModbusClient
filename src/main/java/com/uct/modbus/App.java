package com.uct.modbus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author AJIT KUMAR  THAKUR
 * Project Details : Modbus TCP Client Simulator
 *
 */
public class App extends Application
{
	
	  public static void main( String[] args ) 
	  { 
		  launch(args); 
		  
	  }
	 

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view/ModbusConfiguration.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Configuration");
		primaryStage.show();
		
	}
	
	
}
