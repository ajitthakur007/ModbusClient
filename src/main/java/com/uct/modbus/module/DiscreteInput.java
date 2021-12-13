package com.uct.modbus.module;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.uct.modbus.service.ModbusConnection;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

public class DiscreteInput 
{
	private ModbusClient client;
	private static int address;
	private static int quantity;
	
	 public DiscreteInput()
	 {
		client= ModbusConnection.getModbusClient();
	 }
	 public List<Boolean> getData(int add, int quan)
	 {
		 address=add;
		 quantity=quan;
		 List<Boolean> list= new ArrayList<Boolean>();
		 try {
			boolean[] readDiscreteInputs = client.ReadDiscreteInputs(address, quantity);
			for (boolean b : readDiscreteInputs) {
				list.add(b);
			}
			return list;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (ModbusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 
	 public List<Boolean> getRefreshData()
	 {
		 List<Boolean> list= new ArrayList<Boolean>();
		 try {
			boolean[] readDiscreteInputs = client.ReadDiscreteInputs(address, quantity);
			for (boolean b : readDiscreteInputs) {
				list.add(b);
			}
			return list;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (ModbusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	 }
}
