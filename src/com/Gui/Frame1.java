package com.Gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class Frame1 extends Frame
{
   public Frame1(String title)
   {
	   super(title);
   }
   public static void main(String[] args)
{
	Frame1 frame=new Frame1("Ñ¦¿ü");
	frame.setSize(500,500);
	frame.setBackground(Color.pink);
	frame.setLayout(null);
	Panel panel=new Panel();
	panel.setSize(250,250);
	panel.setBackground(Color.black);
	
	frame.add(panel);
	frame.setVisible(true);
}
}
