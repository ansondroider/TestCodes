package testcodes.base;

public class StringFormat {
	
	public static void main(String args[]){
		//testPhoneNumber();
		testFilterNumber();
	}
	
	public static void testFilterNumber(){
		String AC = "02";
		
		String num = "21234567";
		LOG.log(num + "> " + filterNumberString(num, AC));
		num = "021234567";
		LOG.log(num + "> " + filterNumberString(num, AC));
	}
	
	/** Filter call number, For NDD / FDD / Local / Local **/
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
	
	public static void testPhoneNumber(){
		String s = "012";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "0123";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "0123456";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "01234567";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "0123456789";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		
		s = "4";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "456";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "45678900";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "4123456789";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		
		s = "1012";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "10123";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "10123456";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "101234567";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		
		s = "101234567890";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		
		s = "111";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "1112";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "11123";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "111234567";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "111234567890";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		
		s = "121";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "1212";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "12123";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "131234567";
		LOG.log(s + "-> " + formatPhoneNumber(s));
		s = "131234567890";
		LOG.log(s + "-> " + formatPhoneNumber(s));
	
	
	}
	
	public static String formatPhoneNumber(String src){
		String s = src;
		int length = src == null ? 0 : src.length();
		
		if(src.startsWith("1")){
			if(length > 11){
				return src;
			}
			
			if(src.startsWith("10")){
				if(length > 7){
					return "10" + src.substring(2, 4) + "-" + src.substring(4, 7) + "-" + src.substring(7);
				}else if(length > 4){
					return "10" + src.substring(2, 4) + "-" + src.substring(4);
				}else{
					return src;
				}
			}else if(src.startsWith("11")){
				if(length > 7){
					return "11" + src.substring(2, 4) + "-" + src.substring(4, 7) + "-" + src.substring(7);
				}else if(length > 4){
					return "11" + src.substring(2, 4) + "-" + src.substring(4);
				}else{
					return src;
				}
			}else {
				if(length > 7){
					return "1 " + src.substring(1, 4) + "-" + src.substring(4, 7) + "-" + src.substring(7);
				}else if(length > 4){
					return "1 " + src.substring(1, 4) + "-" + src.substring(4);
				}else if(length > 2){
					return "1 " + src.substring(1);
				}
			}

		}
		
		if(length > 10){
			return src;
		}else if(length > 7){
			s = "(" + src.substring(0, 3) + ") " + src.substring(3, 6) + "-" + src.substring(6);
		}else if(length > 3){
			s = src.substring(0, 3) + "-" + src.substring(3);
		}
		return s;
	}

}
