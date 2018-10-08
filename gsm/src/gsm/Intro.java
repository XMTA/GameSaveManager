package gsm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Intro {
    public static void main(String []args) {
        System.out.println("Hello 子扬"); // 打印 Hello 子扬
        // 获取C:\\下的文件列表
        ArrayList<String> arrayList = getFiles("C:\\");
        
        ArrayList<String> gameSavePath  = new ArrayList<String>();
        gameSavePath.add("C:\\Users\\ZiYang Huang\\Documents\\Overwatch");
        gameSavePath.add("C:\\Users\\ZiYang Huang\\Documents\\The Witcher 3");
        gameSavePath.add("C:\\Users\\ZiYang Huang\\Documents\\Paradox Interactive\\Crusader Kings II");
        
        
        String output = "D:\\GameSave";
        if (!(new File(output)).exists()) {
            (new File(output)).mkdir();
        }
        
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd_");
        String TodayString = ft.format(dNow);
        
        System.out.println(getLastName("C:\\Users\\ZiYang Huang\\Documents\\Overwatch"));
        
        for(String gamePath : gameSavePath ){
        	String GameName = getLastName(gamePath);
        	
        	//这里开始复制
            try {
    			copyDir(gamePath,output+"\\"+TodayString+GameName);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        }
    

    }
    
    
    //获取路径中的名字
    public static String getLastName(String path) {
    	File tempFile =new File(path.trim());
    	String fileName = tempFile.getName(); 
    	return fileName;
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
