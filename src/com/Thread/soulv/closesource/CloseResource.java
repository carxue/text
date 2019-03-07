package com.Thread.soulv.closesource;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CloseResource {
	public static void main(String[] args) throws IOException {
		ExecutorService es = Executors.newCachedThreadPool();
		ServerSocket ss = new ServerSocket(8080);
		InputStream is =new Socket("localhost",8080).getInputStream();
	}
}
