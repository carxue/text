package com;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("D:\\XueK_SoftInstall\\my_workspace_20150921\\wmc\\src\\main\\webapp\\WEB-INF\\classes");
        FileTest.indexZipFile(file);
    }
    
    public static byte[] readBytesFromInputStream(InputStream is) throws IOException {
        byte[] resultBuff = new byte[0];
        try {
            byte[] buff = new byte[2048];
            int k = -1;
            while ((k = is.read(buff, 0, buff.length)) > -1) {
                byte[] tbuff = new byte[resultBuff.length + k];
                System.arraycopy(resultBuff, 0, tbuff, 0, resultBuff.length);
                System.arraycopy(buff, 0, tbuff, resultBuff.length, k);
                resultBuff = tbuff;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        return resultBuff;
    }
    public static Map<String, byte[]> indexZipFile(java.io.File jarFile) {
        Map<String, byte[]> files = new HashMap<String, byte[]>();
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile( jarFile );
            Enumeration< ? extends ZipEntry> entries = zipFile.entries();
            int index = 0;
           
            while ( entries.hasMoreElements() ) {
                System.out.println("###############count:"+ index +","+ zipFile);
                ZipEntry entry = entries.nextElement();
                byte[] bytes = readBytesFromInputStream( zipFile.getInputStream( entry ) );
                files.put( entry.getName(),
                           bytes );
                
                
                index++;
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( "Unable to get all ZipFile entries: " + jarFile, e );
        } finally {
            if ( zipFile != null ) {
                try {
                    zipFile.close();
                } catch ( IOException e ) {
                    throw new RuntimeException( "Unable to get all ZipFile entries: " + jarFile, e );
                }
            }
        }
        return files;
    }

}
