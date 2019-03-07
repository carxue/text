package com.Io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Dataout
{
  public static void main(String[] args) throws Exception
{
	DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(
			new FileOutputStream("kui.txt")));
	int a=1001;
	char b='c';
	float c=1.154f;
	String str="xuekui";
	
	dos.writeInt(a);
	dos.writeChar(b);
	dos.writeFloat(c);
	dos.writeBytes(str);
	
	dos.close();
	//¶Á³öÊý¾Ý
	DataInputStream dis=new DataInputStream(new BufferedInputStream(
			new FileInputStream("kui.txt")));
	System.out.println(dis.readInt());
	System.out.println(dis.readChar());
	System.out.println(dis.readFloat());
	System.out.println(dis.readByte());
}
}
