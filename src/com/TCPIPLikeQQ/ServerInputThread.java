package com.TCPIPLikeQQ;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

public class ServerInputThread extends Thread
{
	private Socket socket;

	public ServerInputThread(Socket socket)
	{
		this.socket = socket;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run()
	{
		Date date=new Date();
		try
		{
			InputStream is = socket.getInputStream();
			while (true)
			{
				byte[] buffer = new byte[1024];
				int length = is.read(buffer);
				String str = new String(buffer, 0, length);
				System.out.println("收到client信息-"+date.toLocaleString()+":");
				System.out.println(str);
			}

		}
		catch (IOException e)
		{

			e.printStackTrace();
		}

	}
}
