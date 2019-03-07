package com.Gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class SetLayout1
{
	private Frame frame;
	private Button bn, bs, be, bw, bc;

	public void setLayout()
	{
		frame = new Frame("SetLayout");
		bn = new Button("NORTH");
		bs = new Button("SOUTH");
		be = new Button("EAST");
		bw = new Button("WEST");
		bc = new Button("CENTER");

		frame.add(bn, BorderLayout.NORTH);
		frame.add(bs, BorderLayout.SOUTH);
		frame.add(be, BorderLayout.EAST);
		frame.add(bw, BorderLayout.WEST);
		frame.add(bc, BorderLayout.CENTER);

		frame.setSize(200, 300);
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		SetLayout1 setLayout=new SetLayout1();
		setLayout.setLayout();
	}
}
