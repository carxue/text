package com.Swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarText
{
  public static void main(String[] args)
{
	JFrame frame=new JFrame("Menu Bar");
	JMenuBar menbar=new JMenuBar();
	
	JMenu menu1=new JMenu("File");
	JMenu menu2=new JMenu("Save");
	JMenu menu3=new JMenu("Sourse");
	JMenu menu4=new JMenu("Edit");
	
	JMenuItem menuitem1=new JMenuItem("xue");
	JMenuItem menuitem2=new JMenuItem("kui");
	JMenuItem menuitem3=new JMenuItem("hello");
	JMenuItem menuitem4=new JMenuItem("yah");
	
	frame.getContentPane().add(menbar,BorderLayout.NORTH);
	frame.setSize(300,300);
	
	menbar.add(menu1);
	menbar.add(menu2);
	menbar.add(menu3);
	menbar.add(menu4);
	
	menu1.add(menuitem1);
	menu1.add(menuitem2);
	menu1.add(menuitem3);
	menu1.add(menuitem4);
	
	frame.setVisible(true);
	
	
	
}
	
}
