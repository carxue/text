package com.Gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;

public class SetGridLayout
{
  private Frame frame;
  private Button b1,b2,b3,b4,b5,b6;
  public void gridLayout()
  {
	  frame=new Frame("Grid Layout");
	  b1=new Button("xue");
	  b2=new Button("kui");
	  b3=new Button("and");
	  b4=new Button("I");
	  b5=new Button("love");
	  b6=new Button("you");
	  
	  frame.setLayout(new GridLayout(3,2,5,6));
	  frame.add(b1);
	  frame.add(b2);
	  frame.add(b3);
	  frame.add(b4);
	  frame.add(b5);
	  frame.add(b6);
	  frame.setSize(300,400);
	  frame.setVisible(true);
  }
  public static void main(String[] args)
{
	SetGridLayout grid=new SetGridLayout();
	grid.gridLayout();
}
}
