package com.uct.modbus.module;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.uct.modbus.service.ModbusConnection;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

public class HoldingRegister 
{
	private ModbusClient client;
	private static int address;
	private static int quantity;
	
	public HoldingRegister()
	{
		client=ModbusConnection.getModbusClient();
	}
	
	public List<Integer> getData(int add,int quan)
	{
		address=add;
		quantity=quan;
		List<Integer> list= new ArrayList<Integer>();
		try {
			int[] registers = client.ReadHoldingRegisters(address, quantity);
			
			//System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadHoldingRegisters(address,quantity)));
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
	
	public List<Integer> getRefreshData()
	{
		List<Integer> list= new ArrayList<Integer>();
		try {
			int[] registers = client.ReadHoldingRegisters(address, quantity);
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
	public void writeSingleRegister(int address, int value)
	{
		try {
			client.WriteSingleRegister(address, value);
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
	public void writeMultipleRegister(int address, int[] values)
	{
		try {
			client.WriteMultipleRegisters(address, values);
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
	public List<Integer> readAndWriteMultipleRegister(int readingAddress, int readingQuantity,int writingAddress, int[] values)
	{
		List<Integer> list= new ArrayList<Integer>();
		try {
			int[] readWriteMultipleRegisters = client.ReadWriteMultipleRegisters(writingAddress, readingQuantity, writingAddress, values);
			for (int i : readWriteMultipleRegisters) {
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
