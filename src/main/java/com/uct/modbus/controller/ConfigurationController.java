package com.uct.modbus.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.uct.modbus.service.GetConnectionTask;
import com.uct.modbus.service.ModbusConnection;

import de.re.easymodbus.modbusclient.ModbusClient;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfigurationController implements Initializable  {

    @FXML
    private TextField port;

    @FXML
    private TextField ip;

    @FXML
    private TextField unitID;
    
    @FXML
    private AnchorPane config;
    
    @FXML
    private AnchorPane connectionData;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label connecting;

    @FXML
    private Label percentage;
    
    private   String ipAddress;
    private int portNo;
    private byte unitId;
    
    @Override
  	public void initialize(URL location, ResourceBundle resources) {
      	progressBar.setVisible(false);
      	connecting.setVisible(false);
      	percentage.setVisible(false);
  		
  	}
    
    @FXML
    void config(ActionEvent event) 
    {
    	getData();
     	getService();
    }

	private void getService() {
		Service <ModbusClient>service= new Service<ModbusClient>() {

			@Override
			protected Task<ModbusClient> createTask() {
				
				 return new GetConnectionTask(ipAddress,portNo,unitId) ;
			}
    		
    	};
    	//connectionData.disableProperty().bind(service.runningProperty());
    	progressBar.visibleProperty().bind(service.runningProperty());
    	percentage.visibleProperty().bind(service.runningProperty());
    	connecting.visibleProperty().bind(service.runningProperty());
    	progressBar.progressProperty().bind(service.progressProperty());
    	percentage.textProperty().bind(service.messageProperty());
    	service.start();
    	service.setOnSucceeded( e -> {
    		getView();
    	    
    	});
    	service.setOnFailed(e->
    	{
    		System.out.println("Error: Connection not established....");
    	});

	}

	private void getData() {

		ipAddress=ip.getText();
    	portNo=Integer.parseInt(port.getText());
    	unitId=(byte) Integer.parseInt(unitID.getText());
		
	}

	private void getView() {
		config.getScene().getWindow().hide();
		Stage primaryStage= new Stage();
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/com/uct/modbus/view/Home.fxml"));
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
