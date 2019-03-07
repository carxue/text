package com.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Udp
{
  public static void main(String[] args) throws SocketException, Exception
{
	DatagramSocket dg=new DatagramSocket();
    String str="xuekui";
    DatagramPacket dp=new DatagramPacket(str.getBytes(),str.length(),
    		InetAddress.getByName("localhost"),10000);
    dg.send(dp);
    
    byte[] buffer=new byte[1000];
    DatagramPacket dp1=new DatagramPacket(buffer,100);
    dg.receive(dp1);
    System.out.println(new String(buffer,0,dp1.getLength()));
    dg.close();
    
    
}
}
