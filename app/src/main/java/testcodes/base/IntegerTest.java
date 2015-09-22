package testcodes.base;


public class IntegerTest {

	public static void main(String[] args){
		//testComplementaryCode();
		//fromHexString();
		//formatBinary();
		testBit();
	}
	
	public static void testBit(){
		int i0 = 1;
		int i1 = 1 << 4;
		int i2 = i0 | i1;
		int i3 = ~i2;
		
		LOG.log(Integer.toBinaryString(i2));
		LOG.log(Integer.toBinaryString(i3));
		
		i2 &= i3;
		LOG.log(Integer.toBinaryString(i2));
	}
	
	public static void formatBinary(){
		int i = -31;
		LOG.log(Integer.toBinaryString(i) + ":" + Integer.toHexString(i));
	}
	
	public static void fromHexString(){
		String s = "5A0101";
		byte[] bs = s.getBytes();
		LOG.log(bs.length);
		int value = Integer.parseInt(s, 16);
		LOG.log("value=" + value);
		LOG.log("expect = " + (0x5a0101));
		
		int i0 = 0x5a0000;
		int i1 = 0x0100;
		int i2 = 0x01;
		
		LOG.log(i0 + i1 + i2);
	}
	
	public static void testComplementaryCode(){
		
		/*	原码与补码互转。
			正数原码和补码相同。
			负数原码转补码规则：原码的反码+1
			int c = (i >= 0) ? i: (~i + 1);
		*/
		for(int i=0; i<10; i++){
			
			System.out.println("原码:\t"+i + ", " + Integer.toBinaryString(i));  
			i = ~i+0x01;          
			System.out.println("补码:\t"+i + ", " + Integer.toBinaryString(i));  
			i = ~i+0x01;  
			System.out.println("原码:\t"+i + ", " + Integer.toBinaryString(i)); 
		}
	}
}
