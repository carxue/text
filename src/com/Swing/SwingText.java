package com.Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwingText
{
  public static void main(String[] args)
{
	JFrame frame=new JFrame("Swing");
	JLabel label=new JLabel("Press Me");
	JButton button=new JButton("≤ÂMe");
	
	frame.getContentPane().add(label,BorderLayout.WEST);
	frame.getContentPane().add(button,BorderLayout.CENTER);
	frame.setSize(200,300);
	frame.setBackground(Color.black);
	
	label.setPreferredSize(new Dimension(100,100));
	label.setBackground(Color.yellow);
	button.setBackground(Color.BLUE);
	
	frame.addWindowListener(new MyWindow());
	
	frame.setVisible(true);
}
}
class MyWindow extends WindowAdapter
{
	public void windowClossing()
	{
		System.exit(0);
	}
}