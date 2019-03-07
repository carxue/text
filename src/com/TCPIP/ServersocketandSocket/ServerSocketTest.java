package com.TCPIP.ServersocketandSocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest
{
	public static void main(String[] args) throws Exception
	{
		  ServerSocket serverSocket=new ServerSocket(5000);
		  Socket socket=serverSocket.accept();
		  InputStream is=socket.getInputStream();
		  OutputStream os=socket.getOutputStream();
		  //得到发过来的东西
		  byte[] buffer=new byte[200];
		  int length=is.read(buffer);
		  System.out.println(new String(buffer,0,length));
		  //往外发东西
		  os.write("恩，有什么事".getBytes());
	}

	
}
