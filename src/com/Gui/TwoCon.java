package com.Gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class TwoCon
{
	private Frame frame;
	private Panel panel;
	private Button b1, b2, b3, b4;

	public void set()
	{
		frame = new Frame("Ѧ��");
		b1 = new Button("hello");
		b2 = new Button("world");
		b3 = new Button("xue");
		b4 = new Button("kui");
		frame.add(b1, BorderLayout.WEST);
		frame.add(b2, BorderLayout.CENTER);

		panel = new Panel();
		panel.add(b3);
		panel.add(b4);
		panel.setBackground(Color.pink);

		frame.add(panel, BorderLayout.NORTH);
		//frame.pack();//ʹframe���������ó����ִ�С����ʽ
		frame.setSize(400,400);
		
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		TwoCon tc=new TwoCon();
		tc.set();
	}
}
