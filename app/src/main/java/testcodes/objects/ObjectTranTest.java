package testcodes.objects;

import testcodes.base.LOG;

public class ObjectTranTest {
	public static void main(String args[]){
		testTran();
	}
	
	public static void testTran(){
		MyClass mc = new MyClass();
		mc.name = "anson";
		mc.age = 30;
		changeName(mc);
		LOG.log("name=" + mc.name);
		
		changeName(mc.name);
		LOG.log("name=" + mc.name);
	}
	
	public static void changeName(MyClass mc){
		mc.name = "anson.lai";
	}
	
	public static void changeName(String name){
		name = "anson.Lai";
	}
	
	
}
