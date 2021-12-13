package com.uct.modbus.module;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.uct.modbus.service.ModbusConnection;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

public class Coils 
{
	private ModbusClient client;
	private static int address;
	private static int quantity;
	public Coils()
	{
		client=ModbusConnection.getModbusClient();
	}
	public List<Boolean> getData(int add, int quan)
	{
		address=add;
		quantity=quan;
		List<Boolean> list= new ArrayList<Boolean>();
		try {
			boolean[] readCoils = client.ReadCoils(address, quantity);
			for (boolean b : readCoils) {
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
	
	public List<Boolean> getRefreshData() {
		
		List<Boolean> list= new ArrayList<Boolean>();
		try {
			boolean[] readCoils = client.ReadCoils(address, quantity);
			for (boolean b : readCoils) {
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
	
	public void writeSingleCoil(int address, boolean value)
	{
		try {
			client.WriteSingleCoil(address, value);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (ModbusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeMultipleCoils(int address, boolean[] values)
	{
		try {
			client.WriteMultipleCoils(address, values);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (ModbusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
