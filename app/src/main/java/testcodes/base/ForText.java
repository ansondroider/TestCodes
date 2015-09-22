package testcodes.base;

public class ForText {
	public static void main(String[] args){
		//breakFor();
		breakSwitch();
	}
	
	public static void breakSwitch(){
		int i = 0;
		switch(i){
		case 0:
			for(int j=0; j<10; j++){
				LOG.log("j=" + j);
				if(j == 5)break;
			}
			LOG.log("after break for");
			break;
		}
	}
	
	public static void breakFor(){
		for1:
			for(int i=0; i<20; i++){
				for2:
				for(int j=i-1; j > -1; j--){
					LOG.log("i=" + i + ", j=" + j);
					if(i==3)break for2;
				}
			}
	}
}
