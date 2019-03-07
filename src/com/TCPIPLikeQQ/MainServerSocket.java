package com.TCPIPLikeQQ;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServerSocket
{
 public static void main(String[] args) throws Exception
{
	ServerSocket serverSocket=new ServerSocket(9000);
	while(true)
	{
		Socket socket=serverSocket.accept();
		new ServerInputThread(socket).start();
		new ServerOutputThread(socket).start();
	}
	
}
}
