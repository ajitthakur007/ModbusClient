package com.uct.modbus.service;

import java.io.IOException;
import java.net.UnknownHostException;

import de.re.easymodbus.modbusclient.ModbusClient;

public class ModbusConnection 
{
	private static ModbusClient client=null;
	private String ip;
	private int port;
	private byte unit;
	public ModbusConnection() {
		
	}
	

	public ModbusClient connect(String ipAddrss, int portNo, byte id) throws UnknownHostException, IOException
	{
		System.out.println("Hello Connection ............");
		ip=ipAddrss;
		port=portNo;
		if(!ip.equals(null) && port!=0 )
		{
			
			try {
				client= new ModbusClient(ip,port);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			client.setUnitIdentifier(id);
			client.Connect();
			System.out.println(client);
			return client;
			
		}
		else
		{
			System.out.println("Please provide ip and port");
			return null;
		}
	}
	
	public static ModbusClient getModbusClient()
	{
		if (!client.equals(null))
		{
			return client;
		}
		return null;
		
	}
	
}
