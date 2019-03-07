package com.Io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class UnicodeText
{
  public static void main(String[] args) throws Exception
{
   OutputStream os=new FileOutputStream("xuekui1.text");
   OutputStreamWriter osw=new OutputStreamWriter(os);
    BufferedWriter bos=new BufferedWriter(osw);
   bos.write("xuekui,wo,ai,ni");
   bos.close();

   FileInputStream is=new FileInputStream("xuekui1.text");
   InputStreamReader isr=new InputStreamReader(is);
   BufferedReader br=new BufferedReader(isr);
   
   System.out.println(br.readLine());
   
//   while(br.read()!=-1)
//   {
//	   System.out.println(br.read());
//   }
//   isr.close();
//   is.close();
//   osw.close();
//   os.close();
//   
   
}
}
