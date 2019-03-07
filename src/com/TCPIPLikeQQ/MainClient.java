package com.TCPIPLikeQQ;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient
{
  public static void main(String[] args) throws UnknownHostException, IOException
{
	Socket socket=new Socket("127.0.0.1",9000);
	new ClientInputThread(socket).start();
	new ClientOutputThread(socket).start();
}
}
