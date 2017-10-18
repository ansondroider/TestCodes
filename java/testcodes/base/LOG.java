package testcodes.base;

public class LOG {
	public static void log(String s){
		System.out.println(s);
	}
	
	public static void log(int i){
		System.out.println(i);
	}
	
	public static void log(byte b){
		System.out.println(b);
	}
	public static void logBytes(byte[] b){
		for(int i=0; i<b.length; i++)
			System.out.println("b[" + i + "]=" + b[i]);
	}
	
	public static void logStringArr(String[] strs){
		if(strs == null){
			log("String array is NULL");
		}else{
			log("String array LENGTH=" + strs.length);
			for(int i=0; i<strs.length;i++){
				log("strArray[" + i + "]=" + strs[i]);
			}
		}
	}
	
	public static void logByteArrayBinary(byte[] b){
		for(int i=0; i<b.length; i++)
			System.out.println("b[" + i + "]=" + getBinary(b[i]) + " < " + b[i]);
	}
	
	public static void logByteBinary(byte b){
		System.out.println( b + "=" + getBinary(b));
	}
	
	public static byte[] BYTES = {	1 << 0, 1 << 1, 1 << 2, 1 << 3, 
									1 << 4, 1 << 5, 1 << 6};
	public static String getBinary(byte b){
		StringBuilder builder = new StringBuilder();
		builder.append(b < 0 ? '1' : '0');
		for(int i=BYTES.length -1 ; i > -1 ; i --){
			builder.append((BYTES[i] & b) > 0 ? '1' : '0');
		}
		
		return builder.toString();
	}
	
	public static void logxxx(){
		System.out.println("-------");
	}
	public static void logxxxxxx(){
		System.out.println("---------------------------------------------");
	}
	
	public static void logIntArr(int[] strs){
		if(strs == null){
			log("String array is NULL");
		}else{
			log("Int array LENGTH=" + strs.length);
			for(int i=0; i<strs.length;i++){
				log("IntArray[" + i + "]=" + strs[i]);
			}
		}
	}
}
