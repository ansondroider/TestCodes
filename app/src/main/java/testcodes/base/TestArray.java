package testcodes.base;
import java.util.ArrayList;


public class TestArray {
	public static void main(String[] args){
		int[] a = {1, 2, 3};
		int[] b = {4, 5, 6};
		//copyA2B(a, b);
		
		//System.out.println(b[0] + ", " + b[1] + ", " + b[2]);
		
		ArrayList<Integer> codes = new ArrayList<Integer>();
		for(int i=0; i<10; i++){
			codes.add(0, i);
		}
		for(int i=0; i< codes.size(); i++){
			System.out.println("i=" + i + " = " + codes.get(i));
		}
	}
	
	public static void copyA2B(int[] a, int[] b){
		if(a != null && b != null){
			if(a.length == b.length){
				for(int i=0; i<a.length; i++){
					b[i] = a[i];
				}
			}
		}
	}
}
