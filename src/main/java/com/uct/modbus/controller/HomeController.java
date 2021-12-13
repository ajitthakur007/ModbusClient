package com.uct.modbus.controller;


import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;

import com.uct.modbus.module.Coils;
import com.uct.modbus.module.DiscreteInput;
import com.uct.modbus.module.HoldingRegister;
import com.uct.modbus.module.InputRegister;
import com.uct.modbus.service.ModbusConnection;

import de.re.easymodbus.modbusclient.ModbusClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeController  implements Initializable {

    @FXML
    private TextField readHoldingQuantity;

    @FXML
    private Button disconnect;

    @FXML
    private TextField writeSingleCoilsAddress;

    @FXML
    private Pane writeCoilsPane;

    @FXML
    private Pane readDiscretePane;

    @FXML
    private Pane readInputRegisterPane;

    @FXML
    private RadioButton writeSingleCoilsRadio;

    @FXML
    private TextField readDiscreteAddress;

    @FXML
    private TextField writeSingleHoldingAddress;

    @FXML
    private TextField readDiscreteQuantity;

    @FXML
    private TextField writeMultipleHoldingSize;

    @FXML
    private ListView<?> readCoilsListView;

    @FXML
    private TextField writeMultipleCoilsAddress;

    @FXML
    private Pane writeHoldingPane;

    @FXML
    private RadioButton writeMultipleHoldingRadio;

    @FXML
    private Circle red;

    @FXML
    private Pane readCoilsPane;

    @FXML
    private ListView<?> readDiscreteListView;

    @FXML
    private TextField readInputRegisterAddress;

    @FXML
    private Pane writeSinglHoldingPane;

    @FXML
    private Label writeSingleCoilsMsg;

    @FXML
    private ListView<?> readHoldingListView;

    @FXML
    private TextField writeSingleCoilsValue;

    @FXML
    private Pane writeMultipleCoilsPane;

    @FXML
    private Label writeMutipleHoldingMsg;

    @FXML
    private TextField readCoilsAddress;

    @FXML
    private Button connect;

    @FXML
    private Pane writeMultipleCoilsValuesPane;

    @FXML
    private Pane writeMultipleCoilsValuesPane1;

    @FXML
    private Circle green;

    @FXML
    private RadioButton writeMultipleCoilsRadio;

    @FXML
    private TextField readInputRegisterQuantity;

    @FXML
    private Pane readHoldingPane;

    @FXML
    private TextField ip;

    @FXML
    private Label writeMutipleCoilsMsg;

    @FXML
    private TextField readCoilsQuantity;

    @FXML
    private Pane writeMultipleHoldingPane;

    @FXML
    private ListView<?> readInputRegisterListView;

    @FXML
    private Label wri;

    @FXML
    private TextField writeMultipleHoldingAddress;

    @FXML
    private Label wri1;

    @FXML
    private TextField port;

    @FXML
    private RadioButton writeSingleHoldingRadio;

    @FXML
    private Label writeSingleHoldingMsg;

    @FXML
    private Pane writeSinglCoilsPane;

    @FXML
    private TextField writeSingleHoldingValue;

    @FXML
    private TextField readHoldingAddress;

    @FXML
    private TextField writeMultipleCoilsSize;
    
    @FXML
    private Button readAndWriteHoldingRegister;
    
    
    @FXML
    private TextField unitId;

    private ModbusClient client;
    private Coils coils;
    private InputRegister inputRegister;
    private DiscreteInput discreteInput ;
    private HoldingRegister holdingRegister;
    
    public void initialize(URL location, ResourceBundle resources) {
		
    	client=ModbusConnection.getModbusClient();
    	int unitID=Byte.toUnsignedInt(client.getUnitIdentifier());
    	ip.setText( client.getipAddress());
		port.setText(Integer.toString(client.getPort()));
		unitId.setText(Integer.toString(unitID));
		getView("");
		if(client.isConnected())
		{
			connect.setDisable(true);
			red.setVisible(false);
		}
		readAndWriteHoldingRegister.setVisible(false);
		writeSingleCoilsValue.setPromptText("only true or false");
		writeSingleHoldingValue.setPromptText("only number");
	}



	@FXML
    void connect(ActionEvent event) throws UnknownHostException, IOException {
    	String ipAddress=ip.getText();
    	int portNo=Integer.parseInt(port.getText());
    	byte id=(byte) Integer.parseInt(unitId.getText());
    	 ModbusConnection connection = new ModbusConnection();
    	 connection.connect(ipAddress, portNo, id);
    	client = ModbusConnection.getModbusClient();
    	if(client.isConnected())
		{
			connect.setDisable(true);
			green.setVisible(true);
			disconnect.setDisable(false);
			red.setVisible(false);
		}
		ip.setText( client.getipAddress());
		port.setText(Integer.toString(client.getPort()));

    }

    @FXML
    void disconnect(ActionEvent event) {
    	getView("");
    	try {
			client.Disconnect();
			green.setVisible(false);
			red.setVisible(true);
			disconnect.setDisable(true);
			connect.setDisable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/*
	 * @FXML void x1f80e(ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML void f70219(ActionEvent event) {
	 * 
	 * }
	 */
    @FXML
    void readCoils(ActionEvent event) 
    {
    	getView("readCoilsPane");
    }

    @FXML
    void writeCoils(ActionEvent event) 
    {
    	getView("writeCoilsPane");
    	writeSingleCoilsRadio.setSelected(true);
    	writeSingleCoilsRadio.setDisable(true);
    }

    @FXML
    void readDiscreteInput(ActionEvent event) 
    {
    	getView("readDiscretePane");
    	
    }

    @FXML
    void readHoldingRegister(ActionEvent event) {
    	
    	getView("readHoldingPane");
    }

    @FXML
    void writeHoldingResgister(ActionEvent event) {
    	
    	getView("writeHoldingPane");
    	writeSingleHoldingRadio.setSelected(true);
    	writeSingleHoldingRadio.setDisable(true);
    	writeMultipleHoldingRadio.setSelected(false);
    	writeMultipleHoldingRadio.setDisable(false);
    }

    @FXML
    void readAndWriteHoldingRegister(ActionEvent event) {

    }

    @FXML
    void readInputRegister(ActionEvent event) {
    
    	getView("readInputRegisterPane");
    }

    
	@FXML
    void readCoilsShow(ActionEvent event) {
    	getCoilsData(true);
    	readCoilsAddress.clear();
    	readCoilsQuantity.clear();
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
    private void getCoilsData(boolean flag)
    {
		coils= new Coils();
		List<Boolean> data;
    	if(flag==true)
    	{
    		int address = Integer.parseInt(readCoilsAddress.getText());
        	int quantity = Integer.parseInt(readCoilsQuantity.getText());
        	 data= coils.getData(address, quantity);
    	}
    	else
    	{
    		data=coils.getRefreshData();
    	}
    	System.out.println(data);
    	ObservableList obList = FXCollections.observableArrayList();
    	obList.addAll(data);
    	readCoilsListView.setItems(obList);	
    }

    @FXML
    void readCoilsRefresh(ActionEvent event) {
    	getCoilsData(false);
    }

    @FXML
    void writeSingleCoilsRadio(ActionEvent event) {
    	getView("writeSinglCoilsPane");
    	writeMultipleCoilsRadio.setSelected(false);
    	writeMultipleCoilsRadio.setDisable(false);
    	writeSingleCoilsRadio.setSelected(true);
    	writeSingleCoilsRadio.setDisable(true);
    }

    @FXML
    void writeMultipleCoilsRadio(ActionEvent event) {
    	getView("writeMultipleCoilsPane");
    	writeSingleCoilsRadio.setSelected(false);
    	writeSingleCoilsRadio.setDisable(false);
    	writeMultipleCoilsRadio.setSelected(true);
    	writeMultipleCoilsRadio.setDisable(true);
    }
    
   

    @FXML
    void writeSingleCoilsWriteButton(ActionEvent event) 
    {
    	int address = Integer.parseInt(writeSingleCoilsAddress.getText());
    	boolean value = Boolean.parseBoolean(writeSingleCoilsValue.getText());
    	coils= new Coils();
    	coils.writeSingleCoil(address, value);
    	writeSingleCoilsMsg.setVisible(true);
    	writeSingleCoilsMsg.setText("Value successfully written in device.");
    	writeSingleCoilsAddress.clear();
    	writeSingleCoilsValue.clear();
    }

    void writeMultipleCoilsWriteButton(TextField [] textFields) {

    	int address = Integer.parseInt(writeMultipleCoilsAddress.getText());
    	int size = Integer.parseInt(writeMultipleCoilsSize.getText());
    	boolean [] b= new boolean[size];
    	int i=0;
    	for(TextField text:textFields)
    	{
    		b[i]=Boolean.parseBoolean(text.getText());
    		i++;
    	}
    	coils= new Coils();
    	coils.writeMultipleCoils(address, b);
    	writeMutipleCoilsMsg.setText("Values successfully written in device");
    }
    
    
    
    @FXML
    void writeMultipleCoilsAddValues(ActionEvent event) 
    {
    	int size = Integer.parseInt(writeMultipleCoilsSize.getText());
    	GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        ScrollPane pane= new ScrollPane(root);
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
        TextField textField[] = new TextField[size];
       
	    for(int i=0;i<size;i++)
	    {
	    	Label label = new Label("Value "+i+" : ");
	    	textField[i] = new TextField();
	    	textField[i].setPromptText("only true or false");
	    	root.add(label, 4, i+1);
	    	root.add(textField[i], 5, i+1);
	    }
	    Button button= new Button("Write");
	    button.setFont(new Font(15));
	    root.add(button, 5, size+1);
	    button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				writeMultipleCoilsWriteButton(textField);
				button.getScene().getWindow().hide();
			}
		});
	    Scene scene = new Scene(pane, 500, 400);
        Stage primaryStage= new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @FXML
    void readDiscreteShow(ActionEvent event) {
    	getDiscreteData(true);
    	
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void getDiscreteData(boolean flag)
    {
    	 discreteInput = new DiscreteInput();
    	List<Boolean> data;
    	if(flag==true)
    	{
    		int address = Integer.parseInt(readDiscreteAddress.getText());
        	int quantity = Integer.parseInt(readDiscreteQuantity.getText());
        	data=discreteInput.getData(address, quantity);
    	}
    	else
    	{
    		data=discreteInput.getRefreshData();
    	}
    	System.out.println(data);
    	ObservableList obList = FXCollections.observableArrayList();
    	obList.addAll(data);
    	readDiscreteListView.setItems(obList);
    	
    }

    @FXML
    void readDiscreteRefresh(ActionEvent event) {

    	getDiscreteData(false);
    }

    @FXML
    void readHoldingShow(ActionEvent event) {
    	
    	readHoldingRegisterData(true);
    	readHoldingAddress.clear();
    	readHoldingQuantity.clear();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void readHoldingRegisterData(boolean flag)
    {
    	holdingRegister= new HoldingRegister();
    	List<Integer> data;
    	if(flag==true)
    	{
    		int address = Integer.parseInt(readHoldingAddress.getText());
    		int quantity = Integer.parseInt(readHoldingQuantity.getText());
    		data=holdingRegister.getData(address, quantity);
    	}
    	else
    	{
    		data=holdingRegister.getRefreshData();
    	}
    	
    	ObservableList obList = FXCollections.observableArrayList();
    	obList.addAll(data);
    	readHoldingListView.setItems(obList);
    }

    @FXML
    void readHoldingRefresh(ActionEvent event) {
    	
    	readHoldingRegisterData(false);
    }

    @FXML
    void readInputRegisterShow(ActionEvent event) 
    {
    	getInputRegisterData(true);
    	readInputRegisterAddress.clear();
    	readInputRegisterQuantity.clear();
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void getInputRegisterData(boolean flag)
    {
    	inputRegister= new InputRegister();
    	List<Integer> data;
    	if(flag==true)
    	{
    		int address = Integer.parseInt(readInputRegisterAddress.getText());
        	int quantity = Integer.parseInt(readInputRegisterQuantity.getText());
        	data = inputRegister.getData(address, quantity);
    	}
    	else
    	{
    		data=inputRegister.getRefreshData();
    	}
    	ObservableList obList = FXCollections.observableArrayList();
    	System.out.println(data);
    	obList.addAll(data);
    	readInputRegisterListView.setItems(obList);
    }

    @FXML
    void readInputRegisterRefresh(ActionEvent event) {
    	
    	getInputRegisterData(false);
    }

    @FXML
    void writeSingleHoldingRadio(ActionEvent event) {
    	
    	getView("writeSinglHoldingPane");
    	writeSingleHoldingRadio.setSelected(true);
    	writeSingleHoldingRadio.setDisable(true);
    	writeMultipleHoldingRadio.setSelected(false);
    	writeMultipleHoldingRadio.setDisable(false);
    }
    
    @FXML
    void writeMultipleHoldingRadio(ActionEvent event) 
    {
    	getView("writeMultipleHoldingPane");
    	writeMultipleHoldingRadio.setSelected(true);
    	writeMultipleHoldingRadio.setDisable(true);
    	writeSingleHoldingRadio.setSelected(false);
    	writeSingleHoldingRadio.setDisable(false);
    	
    	
    }

    @FXML
    void writeSingleHoldingWriteButton(ActionEvent event) 
    {
    	int address = Integer.parseInt(writeSingleHoldingAddress.getText());
    	int value = Integer.parseInt(writeSingleHoldingValue.getText());
    	holdingRegister= new HoldingRegister();
    	holdingRegister.writeSingleRegister(address, value);
    	writeSingleHoldingAddress.clear();
    	writeSingleHoldingValue.clear();
    	writeSingleHoldingMsg.setText("Value successfullly written in device.");
    }
    
    @FXML
    void writeMultipleHoldingValuesButton(ActionEvent event)
    {
    	int size = Integer.parseInt(writeMultipleHoldingSize.getText());
    	GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        ScrollPane pane= new ScrollPane(root);
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
        TextField textField[] = new TextField[size];
       
	    for(int i=0;i<size;i++)
	    {
	    	Label label = new Label("Value "+i+" : ");
	    	textField[i] = new TextField();
	    	root.add(label, 4, i+1);
	    	root.add(textField[i], 5, i+1);
	    }
	    Button button= new Button("Write");
	    button.setFont(new Font(20));
	    root.add(button, 5, size+1);
	    button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				writeMultipleHoldingWriteButton(textField);
				button.getScene().getWindow().hide();
			}
		});
	    Scene scene = new Scene(pane, 500, 400);
        Stage primaryStage= new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void writeMultipleHoldingWriteButton(TextField [] textFields) 
    {
    	int size = Integer.parseInt(writeMultipleHoldingSize.getText());
    	int address = Integer.parseInt(writeMultipleHoldingAddress.getText());
    	int [] value= new int[size];
    	int i=0;
    	for(TextField text: textFields)
    	{
    		value[i]=Integer.parseInt(text.getText());
    		i++;
    	}
    	holdingRegister=new HoldingRegister();
    	holdingRegister.writeMultipleRegister(address, value);
    	writeMutipleHoldingMsg.setText("Values written successfully in your device...");
    	writeMultipleHoldingSize.clear();
    	writeMultipleHoldingAddress.clear();
    }


    private void getView(String viewName) {
		
    	switch (viewName) 
    	{
			case "readCoilsPane":
			{
				readCoilsPane.setVisible(true);
				writeCoilsPane.setVisible(false);
				readDiscretePane.setVisible(false);
				readHoldingPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "writeCoilsPane":
			{
				writeCoilsPane.setVisible(true);
				writeSinglCoilsPane.setVisible(true);
				readCoilsPane.setVisible(false);
				readDiscretePane.setVisible(false);
				readHoldingPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "writeSinglCoilsPane":
			{
				writeCoilsPane.setVisible(true);
				writeSinglCoilsPane.setVisible(true);
				readCoilsPane.setVisible(false);
				readDiscretePane.setVisible(false);
				readHoldingPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "writeMultipleCoilsPane":
			{
				writeCoilsPane.setVisible(true);
				writeMultipleCoilsPane.setVisible(true);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				readDiscretePane.setVisible(false);
				readHoldingPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "readDiscretePane":
			{
				readDiscretePane.setVisible(true);
				writeCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				readHoldingPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "readHoldingPane":
			{
				readHoldingPane.setVisible(true);
				readDiscretePane.setVisible(false);
				writeCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "writeHoldingPane":
			{
				writeHoldingPane.setVisible(true);
				writeSinglHoldingPane.setVisible(true);
				readHoldingPane.setVisible(false);
				readDiscretePane.setVisible(false);
				writeCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "writeSinglHoldingPane":
			{
				writeHoldingPane.setVisible(true);
				writeSinglHoldingPane.setVisible(true);
				readHoldingPane.setVisible(false);
				readDiscretePane.setVisible(false);
				writeCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			case "writeMultipleHoldingPane":
			{
				writeHoldingPane.setVisible(true);
				writeMultipleHoldingPane.setVisible(true);
				writeSinglHoldingPane.setVisible(false);
				readHoldingPane.setVisible(false);
				readDiscretePane.setVisible(false);
				writeCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				break;
			}
			case "readInputRegisterPane":
			{
				readInputRegisterPane.setVisible(true);
				writeHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				readHoldingPane.setVisible(false);
				readDiscretePane.setVisible(false);
				writeCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				readCoilsPane.setVisible(false);
				break;
			}
	
			default:
			{
				readCoilsPane.setVisible(false);
				writeCoilsPane.setVisible(false);
				readDiscretePane.setVisible(false);
				readHoldingPane.setVisible(false);
				writeHoldingPane.setVisible(false);
				readInputRegisterPane.setVisible(false);
				writeSinglCoilsPane.setVisible(false);
				writeMultipleCoilsPane.setVisible(false);
				writeSinglHoldingPane.setVisible(false);
				writeMultipleHoldingPane.setVisible(false);
				break;
			}
			
		}
    }


}
