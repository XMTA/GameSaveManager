package gsm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Intro {
    public static void main(String []args) {
        System.out.println("Hello World"); // 打印 Hello World
        ArrayList<String> arrayList = getFiles("C:\\");
        
        try {
			copyDir("D:\\搜狗高速下载","D:\\TEST");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文     件：" + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }
    
    public static void copyDir(String sourcePath, String newPath) throws IOException {
        File file = new File(sourcePath);
        String[] filePath = file.list();
        
        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }
        
        for (int i = 0; i < filePath.length; i++) {
            if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
                copyDir(sourcePath  + File.separator  + filePath[i], newPath  + file.separator + filePath[i]);
            }
            
            if (new File(sourcePath  + file.separator + filePath[i]).isFile()) {
                copyFile(sourcePath + File.separator + filePath[i], newPath + file.separator + filePath[i]);
            }
            
        }
    }
    
    public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);;

        byte[] buffer=new byte[2097152];
        int readByte = 0;
        while((readByte = in.read(buffer)) != -1){
            out.write(buffer, 0, readByte);
        }
        in.close();
        out.close();
    }
}
