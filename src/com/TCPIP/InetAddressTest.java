package com.TCPIP;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest
{
  public static void main(String[] args) throws Exception
{
	InetAddress ia=InetAddress.getLocalHost();
	System.out.println(ia);

}
}
