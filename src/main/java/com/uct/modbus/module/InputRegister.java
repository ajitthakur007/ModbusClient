package com.uct.modbus.module;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.uct.modbus.service.ModbusConnection;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

public class InputRegister 
{
	private ModbusClient client;
	private static int address;
	private static int quantity;
	
	public InputRegister()
	{
		client=ModbusConnection.getModbusClient();
	}
	public List<Integer> getData(int add, int quan)
	{
		address=add;
		quantity=quan;
		List<Integer> list = new ArrayList<Integer>();
		try {
			int[] registers = client.ReadInputRegisters(address, quantity);
			for (int i : registers) {
				System.out.println(i);
				list.add(i);
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
	public List<Integer> getRefreshData() {
		
		List<Integer> list = new ArrayList<Integer>();
		try {
			int[] registers = client.ReadInputRegisters(address, quantity);
			for (int i : registers) {
				list.add(i);
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
