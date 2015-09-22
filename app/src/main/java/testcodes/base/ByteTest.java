package testcodes.base;
import java.util.Date;
import java.util.Random;



public class ByteTest {
	public static void main(String[] args){
		/*byte b = -48;
		String binStr = Integer.toBinaryString(b);
		String hexStr = Integer.toHexString(b);
		
		int i = 222 & ~0;
		//hexStr = Integer.toHexString(i);
		//binStr = Integer.toBinaryString(i);
		
		int i2 = 222;
		byte[] bs = intToByte(i2);
		
		//getDateTime();
		LOG.logBytes(intToByte(222));
		
		LOG.log("---------------------------------------");
		for(i=-128; i < 256; i++){
			b = (byte)i;
			binStr = Integer.toBinaryString(b);
			LOG.log("i = " + binStr);
		}
		
		byte b2 = (byte)(0x16 | (1 << 7));
		LOG.log(b2);*/
		//str2byte();
		//numCharToInt();
		//arrayCopy();
		//StringNByte();
		//test();
		//test0xf();
		intNbyte();
	}


    public static void testFIFO(){
        java.util.ArrayList<byte[]> fifoList = new java.util.ArrayList<byte[]>();

        for(int i = 0; i < 1000; i ++) {
            byte[] d0 = ByteBufferTest.getRandomBytes(20000);
            fifoList.add(d0);
            try{Thread.sleep(100);}catch(Exception e){}
        }
    }
	
	public static void intNbyte(){
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0 ; i < 20; i ++){
			int max = 0xff * 2;
			int i0 = random.nextInt(max) - 0xff ;
			byte[] b0 = getBytes(i0);
			byte[] b00 = getRealBytes(i0);
			LOG.log(Integer.toBinaryString(i0) + "< " + i0);
			LOG.logByteArrayBinary(b0);
			LOG.logxxx();
			LOG.logByteArrayBinary(b00);
			int r = getInt(b00);
			LOG.log(Integer.toBinaryString(r) + "< " + r);
			LOG.logxxxxxx();
		}
		byte b = -20;
		LOG.logByteBinary(b);
		LOG.log("    " + Integer.toBinaryString(256 + b));
		int ib = b < 0 ? 256 + b : b;
		
		LOG.log(Integer.toBinaryString(ib));

	}

	public static int getInt(byte[] b){
		int value = 0x0;
		int len = b.length;
		for(int i = 0; i < len; i ++){
			boolean end = i == len - 1;
			int v = b[i] & 0xFF;
			value |= (v << (len - i - 1) * 8);
			LOG.log("getInt value=" + Integer.toBinaryString(value));
		}
		return value;
	}
	public static byte[] getRealBytes(int i){
		byte[] b = null;
		if(i >= 0 && i <= 0xff){
			b = new byte[]{(byte)i};
		}else if(i > 0xff && i <= 0xffff){
			b = new byte[]{(byte)(i >>> 8), (byte)i};
		}else if(i > 0xffff && i <= 0xffffff){
			b = new byte[]{(byte)(i >>> 16), (byte)(i >>> 8), (byte)i};
		}else{
			b = new byte[]{(byte)(i >>> 24), (byte)(i >>> 16), (byte)(i >>> 8), (byte)i};
		}
		return b;
	}
	public static byte[] getBytes(int i){
		byte[] b = new byte[4];
		b[0] = (byte) (i >>> 24);
		b[1] = (byte) (i >>> 16);
		b[2] = (byte) (i >>> 8);
		b[3] = (byte) i;
		return b;
	}
	
	public static void basicByte(){
		byte 	min_b = 	-0x7f;
		byte 	max_b = 	 0x7f;
		short	min_s =		-0x7fff;
		short 	max_s = 	 0x7fff;
		char	min_c =		-0x00;
		char 	max_c = 	 0xffff;
		int		min_i = 	-0xffffffff;
		int 	max_i =		 0xffffffff;
		long 	min_l = 	-0xffffffffffffffffL;
		long	max_l = 	 0xffffffffffffffffL;
		double	min_d =		-0xffffffff;
		double	max_d =		 0xffffffff;
		
	}
	public static void test0xf(){
		char c = '*';
		int i = (int)c;
		LOG.log(Integer.toHexString(i));
	}
	
	public static void test(){
		int i = 64;
		int j = 75;
		LOG.log(Integer.toBinaryString(i));
		LOG.log(Integer.toBinaryString(j));
		LOG.log("i & j = " + (i&j));
	}
	
	public static void StringNByte(){
		String s = "0123456";
		byte[] bs = s.getBytes();
		for(int i=0; i<bs.length; i++){
			LOG.log(i + "=" + Integer.toHexString(bs[i]));
		}
	}
	
	public static int byte2int(byte b){
		return b < 0 ? 256 + b : b;
	}
	
	public static void arrayCopy(){
		byte[] src = {0x01, 0x03, 0x05, 0x07};
		byte[] dst = new byte[4];
		//src, startIdx in src, dst, startIdx in dst, total size
		System.arraycopy(src, 0, dst, 2, 2);
		LOG.logBytes(dst);
	}
	
	
	public static void numCharToInt(){
		char c = '#';
		LOG.log("c=" + Integer.toHexString(((int)c)));
		
	}
	
	public static void str2byte(){
		byte[] b = {1};
		LOG.log("b.length=" + b.length);
		LOG.logBytes(b);
		
		byte[] b2 = "1".getBytes();
		LOG.log("b2.length=" + b2.length);
		LOG.logBytes(b2);
		
		byte[] b3 = "一".getBytes();
		LOG.log("b3.length=" + b3.length);
		LOG.logBytes(b3);
	}
	
	public static byte[] intToByte(int i){
		byte[] values = new byte[4];
		values[3] = (byte)(0xff & i);
		values[2] = (byte)((0xff00 & i) >> 8);
		values[1] = (byte)((0xff00 & i) >> 16);
		values[0] = (byte)((0xff00 & i) >> 24);
		return values;
	}
	
	public static byte[] getDateTime(){
		int HEXX2 = 256;
		byte[] b = new byte[7];
		Date d = new Date();
		int year = d.getYear() + 1900;
		b[0] = (byte)(year / HEXX2);
		b[1] = (byte)(year % HEXX2);
		b[2] = (byte)(1+d.getMonth());
		b[3] = (byte)d.getDate();
		
		b[4] = (byte)d.getHours();
		b[5] = (byte)d.getMinutes();
		b[6] = (byte)d.getSeconds();
		LOG.logBytes(b);
		return b;
	}
	

	
	
}
