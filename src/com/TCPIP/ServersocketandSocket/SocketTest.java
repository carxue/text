package com.TCPIP.ServersocketandSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
public class SocketTest
{
	public static void main(String[] args) throws Exception
	{
		Socket socket=new Socket("127.0.0.1",5000);
		OutputStream os=socket.getOutputStream();
		InputStream is=socket.getInputStream();
		//���ⷢ��Ϣ
		os.write("Ѧ��".getBytes());
		//������Ϣ
		byte[] buffer=new byte[200];
		int length=is.read(buffer);
		System.out.println(new String(buffer,0,length));
		  
	}

}
