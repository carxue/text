package com.Gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

public class Frame2
{
  private Frame frame;
  private Button button1;
  private Button button2;
  public void SetWindows()
  {
	  frame=new Frame("Ѧ��");
	  frame.setBackground(Color.pink);
	  frame.setSize(300,400);
	  button1=new Button("ȷ��");
	  button2=new Button("ȡ��");
	  button1.setBackground(Color.orange);
	  button2.setBackground(Color.ORANGE);
	  frame.add(button1);
	  frame.add(button2);
	  frame.setLayout(new FlowLayout());
	  frame.setVisible(true);
  }
  public static void main(String[] args)
{
	Frame2 frame2=new Frame2();
	frame2.SetWindows();
}
}
