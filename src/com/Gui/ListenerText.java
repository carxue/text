package com.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListenerText implements MouseListener,MouseMotionListener
{
	private Frame frame;
	private TextField text;
    static int count=0;
	
	
	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		String str="X="+arg0.getX()+" ,  Y="+arg0.getY();
		this.text.setText(str);
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{

		++count;
		String str="你点到我(Frame)了     "+count+"   次!";
		this.text.setText(str);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	public void go()
	{
		frame = new Frame("MouseCase");
		text = new TextField(30);
		frame.setSize(200, 300);
		frame.add(text, BorderLayout.SOUTH);
		frame.setBackground(Color.pink);
		text.setBackground(Color.orange);
		//
		frame.addMouseListener(new MouseCase());
		frame.addWindowListener(new WindowsCase());
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);

		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		ListenerText listener = new ListenerText();
		listener.go();
	}
}
class  WindowsCase implements WindowListener
{

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

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
class MouseCase implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		String str = "你进入了Frame";
		System.out.println(str);

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		String str = "你退出了Frame";
		System.out.println(str);

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
