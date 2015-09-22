package testcodes.base;
import java.nio.charset.Charset;


public class Codex {
	public static void main(String[] args){
		//test1();
		
		C c1 = new C();
		c1.setHashCode(c1.hashCode());
		LOG.log("code = " + c1.hasCode);
		
		C c2 = new C();
		c2.setHashCode(c2.hashCode());
		LOG.log("code = " + c2.hasCode);
	}
	
	public static class C {
		int hasCode;
		public void setHashCode(int code){
			this.hasCode = code;
		}
	}
	
	public static void test1(){
		String s = "{\"ret\":1,\"start\":\"183.11.0.0\",\"end\":\"183.17.255.255\",\"country\":\"\u4e2d\u56fd\",\"province\":\"\u5e7f\u4e1c\",\"city\":\"\u6df1\u5733\",\"district\":\"\",\"isp\":\"\u7535\u4fe1\",\"type\":\"\",\"desc\":\"\"};";
		LOG.log(s);
		String s2 = new String(s.getBytes(), Charset.forName("UTF-8"));
		LOG.log(s2);
	}
}
