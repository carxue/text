package com.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpTest
{
	public static void main(String[] args) throws Exception
	{
		DatagramSocket socket = new DatagramSocket(10000);
		byte[] buffer = new byte[1000];
		DatagramPacket packet = new DatagramPacket(buffer,1000);
		socket.receive(packet);
		System.out.println(new String(buffer, 0, packet.getLength()));
        
		String str="hello";
		DatagramPacket packet1=new DatagramPacket(str.getBytes(),str.length(),
				packet.getAddress(),packet.getPort());
		socket.send(packet1);
		socket.close();
	}
}
