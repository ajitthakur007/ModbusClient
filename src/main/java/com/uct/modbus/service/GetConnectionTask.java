package com.uct.modbus.service;

import de.re.easymodbus.modbusclient.ModbusClient;
import javafx.concurrent.Task;

public class GetConnectionTask extends Task <ModbusClient>{

	private String ip;
	private int port;
	private byte unitId;
	private ModbusConnection modbusConnection=null;
	public GetConnectionTask(String ip, int port, byte unitId) {
		super();
		this.ip = ip;
		this.port = port;
		this.unitId=unitId;
		
	}

	@Override
	public ModbusClient call() throws Exception {
		 updateProgress(0, 500);           
		 updateMessage("0%");
        // Thread.sleep(1505);          
         
         updateProgress(30, 500); 
         updateMessage("6%");
        // Thread.sleep(1505);
         
         updateProgress(100, 500);
         updateMessage("20%");
        // Thread.sleep(1505);
         
         
         updateProgress(150, 500);
         updateMessage("30%");
        // Thread.sleep(1505);
         
         updateProgress(200, 500);
         updateMessage("40%"); 
         
         modbusConnection = new ModbusConnection();
         ModbusClient connect = modbusConnection.connect(ip, port, unitId);
         
         updateProgress(300, 500);
         updateMessage("60%");
        
         
         updateProgress(350, 500);
         updateMessage("70%");
        // Thread.sleep(1505);
         
         updateProgress(400, 500); 
         updateMessage("80%");
        // Thread.sleep(1505);
         
         updateMessage("100%");
         updateProgress(500, 500);
                                    
		return connect;
	}

}
