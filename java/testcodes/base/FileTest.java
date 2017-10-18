package testcodes.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


public class FileTest {
	
	
	public static void main(String[] args){
		//testFileParent();
		readFile();
	}
	
	public static void readFile(){
		File file = new File("/home/anson/adb/lhl");
		String []result;
		if(file.exists()){
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] cache = new byte[1024];
				String res = "";
				int readed = -1;
				while((readed = fis.read(cache)) > 0){
					res += new String(cache, 0, readed);
				}
				cache = null;
				fis.close();
				result = res.split(";;");
				//part2
				String value = result[0];
				int length = value.length();
				char[] chars = value.toCharArray();
				LOG.log(value);
				LOG.log("length=" + length);
				for(int i=0; i<chars.length; i++){
					LOG.log(i + "= _" + chars[i] + "_");
				}
				
				char c0 = (char)12288;
				char c1 = (char)32;
				int i0 = c0;//12288
				int i1 = c1;//32
				LOG.log("i0:i1=" + i0 + ":" + i1);
				value = value.replace((char)12288, (char)32);
				LOG.log(value);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void testFileParent(){
		File f = new File("/USB/a/b/c/d.txt");
		LOG.log("parent = " + f.getParent());
	}

    public static final File logfile = new File("/mnt/sdcard/Digital/log");
    public static void writeToFile(String log){
        File f = new File(logfile.getAbsolutePath() + "/log.txt");
        //writeToFile(f, log.getBytes());
        writeToFileAppend(f, log.getBytes());
    }
    public static void writeToFile(File f, byte[] bytes){
        if(!logfile.exists()){
            logfile.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeToFileAppend(File f, byte[] bytes){
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.seek(f.length());
            raf.write(bytes);
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
