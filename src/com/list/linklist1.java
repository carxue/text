package com.list;
import java.util.*;
public class linklist1
{
  public static void main(String[] args)
{
	Node node=new Node("xue");
	Node node1=new Node("kui");
	Node node2=new Node("lover");
	node.next=node1;
	node1.next=node2;
	System.out.println(node.next.next.data);
	System.out.println("------------------------");
	Node node3=new Node("xiao");
	node.next=node3;
	node3.next=node1;
	System.out.println(node.next.next.next.data);
}
}
