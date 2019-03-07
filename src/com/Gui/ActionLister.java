package com.Gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ActionLister
{
	public static void main(String[] args)
	{
		Frame frame = new Frame("Action Lister");
		Button button = new Button("触发事件");
		frame.add(button, BorderLayout.CENTER);
		button.addActionListener(new ButtonHandler());
		frame.addWindowListener(new MyWindow());
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

}

class ButtonHandler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		System.out.println("触发了我   呵呵");

	}
}

class MyWindow implements WindowListener
{
	@Override
	public void windowOpened(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		System.out.println("FUCK FUCK 操操操");
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}