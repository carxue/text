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
	  frame=new Frame("薛奎");
	  frame.setBackground(Color.pink);
	  frame.setSize(300,400);
	  button1=new Button("确定");
	  button2=new Button("取消");
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
