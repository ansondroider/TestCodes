package testcodes.base;
import com.anson.acode.ALog;
import com.anson.acode.AUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class StringTest {
	public static void main(String[] args){
		
		//testAnlysisHis();
		/*String s = "abcd";
		testGiveValue(s);
		LOG.log("s = " + s);*/
		
		/*String[] arr = {"2000-1-1", "2014-1-1", "1999-12-12", "2014-12-12", "1999-12-30"};
		sortDateStringArray(arr, false);
		LOG.logStringArr(arr);*/
		//testSplit();
		//testSplit2();
		//testReplace();
		
		//verifyVersions();
		//testDial();
		//【日值上朔 大事勿用】 藏宝　嫁娶　求嗣　解除　上梁　栽种　安葬 恩赦　行丧　塞穴
		//【日值上朔 大事勿用】 开市　立券　纳财　分居　斋醮　祈福　安床 入宅　祭祀　纳采　交易　放水　纳畜　修造 动土　置产　破土　筑堤　针灸　赴任　出行 移徙　竖柱　盖屋

		String s = "【日值四废 大事勿用】破土　启鑽　修坟　安葬　祭祀　祈福　赴任 求嗣　解除　动土　移徙　纳财　栽种　斋醮 出行　捕捉　取渔　立券";
		testCut(s);
		s = "【日值上朔 大事勿用】 开市　立券　纳财　分居　斋醮　祈福　安床 入宅　祭祀　纳采　交易　放水　纳畜　修造 动土　置产　破土　筑堤　针灸　赴任　出行 移徙　竖柱　盖屋";
		testCut(s);
	}

    public static void stringCompare(){
        String[] ss = {
                "0",
                "7",
                "80",
                "1苹果",
                "100",
                "1",
                "1香蕉",
                "77",
                "3",
                "X",
                "1爱情",
                "张","林","赖","徐","刘","杨",
        };

        ArrayList<String> ssList = new ArrayList<String>();
        for(String s : ss){
            ssList.add(s);
        }
        ALog.logArray("StringTest0", ss);
        for(int i = 0; i < ss.length - 1; i ++){
            for(int j = i + 1; j < ss.length; j ++){
                if(ss[i].compareTo(ss[j]) > 0){
                    String s = ss[i];
                    ss[i] = ss[j];
                    ss[j] = s;
                }
            }
        }

        ALog.logArray("StringTest1", ss);

        Comparator c = AUtils.getChinaComparator();
        Collections.sort(ssList, c);
        ALog.logListArray(ssList);
    }

	public static void testCut(String s){
		char cb = (char)12288;
		int idx = 12;
		String should = s.replace((char)32, cb);
		String spec = "】";
		boolean isSpec = should.indexOf(spec) > 0;
		String[] ss = should.split(String.valueOf(cb));
		if(isSpec && ss.length > idx - 3){			
			should = should.replace(ss[idx-3], "\n" + ss[idx-3]);
			should = should.replace(spec, spec + cb);
			
			String blank = spec + cb + cb;
			should = should.replace(blank, spec + cb);
		}else if(ss.length > idx){
			should = should.replace(ss[idx], "\n" + ss[idx]);
		}
		LOG.log(should);
	}
	
	public static void testDial(){
		String ac = "043";
		String num0 = "8561006";
		LOG.log(num0 + "-> " + filterNumberString(num0, ac));
		String num1 = "28561006";
		LOG.log(num1 + "-> " + filterNumberString(num1, ac));
		String num2 = "028561006";
		LOG.log(num2 + "-> " + filterNumberString(num2, ac));
		String num3 = "09998862564";
		LOG.log(num3 + "-> " + filterNumberString(num3, ac));
		String num4 = "9998862564";
		LOG.log(num4 + "-> " + filterNumberString(num4, ac));
		String num5 = "0644215200";
		LOG.log(num5 + "-> " + filterNumberString(num5, ac));
	}
	
	public static String filterNumberString(String number, String AC){
		String result = "";
		//1234567, 21234567, 021234567, isLocal
		boolean isLocal = true;
		if(number.length() > 7){
			String ac2 = AC.substring(1);
			int len = AC.length();
			int len2 = len - 1;
			if((number.startsWith(AC) && (number.length() - 7 == len)) ||
					(number.startsWith(ac2) && (number.length() - 7 == len2))){
				int idx = number.length() - 7;
				number = number.substring(idx);
			}else{
				if(number.length() <= 9 && !number.startsWith("0")){
					number = "0" + number;
				}
			}
		}

		//is Mobile? 09 + 123456789 9 + 123456789 
		if(number.length() == 10 && number.startsWith("9")){
			number = "0" + number;
		}
		return number;
	}
	
	public static void verifyVersions(){
		String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
		 "<versions>" + 
		  		"<version value=\"1.0\">http://www.baidu.com/ota/version/1.0.1.apk</version>" + 
		  		"<version value=\"1.1\">http://www.baidu.com/ota/version/1.1.1.apk</version>" + 
		  		"<version value=\"1.2\">http://www.baidu.com/ota/version/1.2.1.apk</version>" + 
		  "</versions>";
		
		int sidx = content.indexOf("<versions>") + 10;
		int eidx = content.indexOf("</versions>");
		if(sidx > 10 && eidx > 0 && eidx > sidx){
			String[] versions = content.substring(sidx, eidx).split("</version>");
			LOG.logStringArr(versions);
			Hashtable<String, String> versionsInfo = new Hashtable<String, String>();
			for(int i=0; i<versions.length; i++){
				String s= versions[i];
				sidx = s.indexOf("=\"") + 2;
				eidx = s.indexOf("\">");
				String key = s.substring(sidx, eidx);
				
				sidx = eidx + 2;
				String value = s.substring(sidx);
				
				versionsInfo.put(key, value);
			}
			
			Iterator<String> keys = versionsInfo.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				String value = versionsInfo.get(key);
				LOG.log("key:value=" + key + ":" + value);
			}
		}
	}

    /**
     * inster ins to src string every interval
     * aabbccddE, -, 2 ---> aa-bb-cc-dd-E
     * @param src source string
     * @param ins string inserted
     * @param interval interval in source
     * @return new string
     */
    public static String insertToString(String src, String ins, int interval) {
        if(src == null || src.length() <= interval){
            return src;
        }
        StringBuilder sb = new StringBuilder(src);
        int insLen = interval + ins.length();
        int times = src.length() / interval;
        times -= src.length() % interval == 0 ? 1 : 0;
        for(int i = 0; i < times; i++){
            sb.insert((insLen * i) + interval, ins);
        }

        return sb.toString();
    }
    public static String testInsert(int score){
		char[] cs = (String.valueOf(score)).toCharArray();

		int len = cs.length;
		StringBuilder sb = new StringBuilder();
		int count = len / 3;
		if(count > 0){
			for(int i = len -1; i >= 0; i-=3){
				sb.insert(0, cs[i]);
				if(i-1 > -1)sb.insert(0, cs[i-1]);
				if(i-2 > -1)sb.insert(0, cs[i-2]);
				if(i-2 > 0)sb.insert(0, ',');
			}
		}else{
			sb.append(cs);
		}
		return sb.toString();
	}
	
	public static void testReplace(){
		String src = "/USB/FDEMO/abc/test.txt";
		String srcF = "/USB/FDEMO";
		String target = "/mnt/sdcard";
		
		String s = src.replace(srcF, target);
		LOG.log(s);
		
	}
	
	
	public static void testSplit2(){
		String s = "/USB/abd/file.txt";
		s = "/USB/a/b/c/d/e/f.txt";
		String [] realPath = new String[]{"\\"};
		String ss[] = s.replaceFirst("/USB", "").split("/");
		if(ss != null && ss.length >= 2){
			realPath = new String[ss.length - 1];
			realPath[0] = "\\";
			for(int i=1; i<ss.length -1; i++){
				realPath[i] = ss[i];
			}
		}
		
		//LOG.logStringArr(ss);
		LOG.logStringArr(realPath);
	}
	
	public static void testSplit(){
		String s = "/storage/emulated/0";
		String ss[] = s.split("/");
		LOG.logStringArr(ss);
		String[] realPath = null;
		if(s.equals("/USB")){
			realPath = new String[]{"\\"};
		}else if(ss != null && ss.length > 2){
			realPath = new String[ss.length - 1];
			realPath[0] = "\\";
			for(int i=2; i<ss.length; i++){
				realPath[i-1] = ss[i];
			}
		}
		
		LOG.logStringArr(realPath);
	}
	
	/**
	 * format yyyy-MM-dd
	 * @param arr
	 */
	public static void sortDateStringArray(String[] arr, boolean orderUp){
		if(arr != null && arr.length > 1){
			for(int i=0; i<arr.length; i++){
				String d1[] = arr[i].split("-");
				int di[] = {Integer.parseInt(d1[0]), Integer.parseInt(d1[1]), Integer.parseInt(d1[2])};
				
				for(int j=i+1; j<arr.length;j++){
					String d2[] = arr[j].split("-");
					int dj[] = {Integer.parseInt(d2[0]), Integer.parseInt(d2[1]), Integer.parseInt(d2[2])};
					if(!orderUp){
						if(di[0] < dj[0] ||
								(di[0] == dj[0] && di[1] < dj[1]) || 
								(di[0] == dj[0] && di[1] == dj[1] && di[2] < dj[2])){
							LOG.log(arr[i] + "<->" + arr[j]);
							String tmp = arr[i];
							arr[i] = arr[j];
							arr[j] = tmp;
							d1 = arr[i].split("-");
							di = new int[]{Integer.parseInt(d1[0]), Integer.parseInt(d1[1]), Integer.parseInt(d1[2])};
						}
					}else{
						if(di[0] > dj[0] ||
								(di[0] == dj[0] && di[1] > dj[1]) || 
								(di[0] == dj[0] && di[1] == dj[1] && di[2] > dj[2])){
							String tmp = arr[i];
							arr[i] = arr[j];
							arr[j] = tmp;
							d1 = arr[i].split("-");
							di = new int[]{Integer.parseInt(d1[0]), Integer.parseInt(d1[1]), Integer.parseInt(d1[2])};
						}
					}
				}
			}
		}
	}
	
	public static void testGiveValue(String s){
		s = "defg";
	}
	public static void testAnlysisHis(){
		String s = getStringFromFile();
		//LOG.log(s);
		
		String[] items = s.split("\n\n");
		//LOG.log("size = " + items.length);
		//LOG.logStringArr(items);
		int[][] values = new int[items.length][16];
		for(int j=0; j<values.length; j++){
			String[] strs = items[j].split("\n");
			int[] val = new int[16];
			try{
			for(int i=0; i<16; i++){
				val[i] = Integer.valueOf(strs[i].substring(strs[i].indexOf("=") + 1));
			}}catch(Exception e){
				LOG.logStringArr(strs);
			}
			values[j] = val;
		}
		
		for(int[] v: values){
			if(v[13] == 2)
				logHis(v);
		}
	}
	
	public static int HEX = 16;
	public static void logHis(int[] b){
		StringBuilder bstr = new StringBuilder();
		StringBuilder bstr2 = new StringBuilder();
		for(int i=0; i<b.length; i++){
			bstr2.append(b[i]).append(" ");
			bstr.append("0x" + Integer.toHexString(byte2int(b[i]))).append(" ");
		}
		//LOG.log(bstr2.toString());
		LOG.log(bstr.toString());
		int hour = b[6];
		int min = b[7];
		int sec = 0;
		int mode = b[13];
		int year = b[8] + 2000;
		int mon = b[9];
		int date = b[10];
		
		int value = byte2int(b[0]);
		int totalSleep_H = value/HEX;
		int totalSleep_L = value%HEX;
				
		value = byte2int(b[1]);
		
		
		int sportTen = totalSleep_L;
		int sportAll = totalSleep_H + value * HEX;
		//ALog.alog("Sleep", "b[1]= " + b[1] + ", b[2]=" + b[2] + "-> " + sleepTime + ":" + totalTime);

		//shallow sleep
		value = byte2int(b[2]);
		int shallow_H = value/HEX;
		int shallow_L = value%HEX;
		value = byte2int(b[3]);
		
		int shallowTen = shallow_L;
		int shallowTotal = shallow_H + value * HEX;
		//ALog.alog("Sleep", "b[3]= " + b[3] + ", b[4]=" + b[4] + "-> " + shallowTime + ":" + shallowTotal);
		// deep sleep
		
		value = byte2int(b[4]);
		int deep_H = value/HEX;
		int deep_L = value%HEX;
		value = byte2int(b[5]);
		
		int deepTen = deep_L;
		int deepTotal = deep_H + value * HEX;

		String time = year + "-" + mon + "-" + date + " " + hour + ":" + min;
		String status = sportTen + "\t" + sportAll + ")(" + shallowTen + "\t" + shallowTotal + ")(" + deepTen + "\t" + deepTotal + ")";
		
		LOG.log(time + "\t(" + status);
	}
	/**
	 * sort English chars
	 * @param strArr
	 * @param orderUp
	 */
	public static void sortStringArray(String[] strArr, boolean orderUp){
		for(int i=0; i<strArr.length - 1; i++){
			for(int j=i; j < strArr.length; j++){
				String s1 = strArr[i];
				String s2 = strArr[j];
				
				if(orderUp && s1.compareTo(s2) > 0){
					String temp = s1;
					strArr[i] = s2;
					strArr[j] = temp;
				}else if(!orderUp && s1.compareTo(s2) < 0){
					String temp = s1;
					strArr[i] = s2;
					strArr[j] = temp;
				}
			}
		}
	}
	
	public static String getStringFromFile(){
		File f = new File("sleep_history3.txt");
		
		try {
			FileInputStream fis = new FileInputStream(f);
			StringBuilder sb = new StringBuilder();
			byte cache[] = new byte[1024];
			int readed = -1;
			try {
				while((readed=fis.read(cache)) > 0){
					sb.append(new String(cache, 0, readed));
				}
				
				return sb.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	static int byte2int(int b){
		if(b < 0){
			return 256 + b;
		}else
			return b;
	}
	
}
