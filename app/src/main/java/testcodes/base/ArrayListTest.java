package testcodes.base;
import com.anson.acode.ALog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ArrayListTest {
    public static String TAG = "ArrayListTest";

    public static void testFIFO(){
        ArrayList<String> strs = new ArrayList<String>();
        strs.add("0");
        strs.add("1");
        strs.add("2");
        ALog.d(TAG, strs.remove(0));
        strs.add("3");
        strs.add("4");
        ALog.d(TAG, strs.remove(0));
        strs.add("5");
        strs.add("6");
        strs.add("7");
        ALog.d(TAG, strs.remove(0));
        strs.add("8");
        ALog.d(TAG, strs.remove(0));

        ALog.d(TAG, strs.remove(0));
        ALog.d(TAG, strs.remove(0));
        ALog.d(TAG, strs.remove(0));

    }
	
	public static void testContain(){
		List<String> ss = new ArrayList<String>();
		ss.add("AnsonLai");
		
		String s = new String("AnsonLai");
		
		LOG.log("testContain: " + ss.contains(s));
	}
	
	public static void testtoArray(){
		List<File> files = new ArrayList<File>();
		
		files.add(new File("1.txt"));
		
		File[] fs = new File[files.size()];
		files.toArray(fs);
		
		//File[] fs2 = (File[])files.toArray();
		
		for(int i=0; i<fs.length; i++){
			LOG.log(fs[i].getAbsolutePath());
		}
	}
	
	public static void test0(){
		/*ArrayList<String> arrs = new ArrayList<String>();
		
		arrs.add("A");
		arrs.add("B");
		arrs.add("C");
		arrs.add("D");
		arrs.add("E");
		arrs.add("F");
		arrs.add("G");
		arrs.add("H");
		arrs.add("I");
		arrs.add("J");
		arrs.add("K");
		arrs.add("L");
		arrs.add("M");
		arrs.add("N");
		
		int ava = 5;
		int rm = arrs.size() - ava;
		Random r = new Random(System.currentTimeMillis());

		for(int i=0; i<rm; i++){
			arrs.remove(r.nextInt(arrs.size()));
		}
		
		for(String s:arrs){
			System.out.println(s);
		}*/
	}
}
