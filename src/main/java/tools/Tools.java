package tools;
import java.text.*;
import java.util.*;

public class Tools {
	public Tools() {

	}

	public static String check(String input,String type) {
		if(type.equals("mail")) {
			if(input.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {

			} else {
				return "信箱格式錯誤";
			}
		} else if(type.equals("word")) {
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
			if(!input.matches(enameReg)) {
				return "只能是中、英文字母、數字和_";
			}
		} else if(type.equals("date")) {
			if(input.length() == 8) {
				
			} else {
				System.out.println(input.length());
				return "請輸入八碼西元年月，例:19110101";
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			try {
				String str = input.substring(0,4)+"-"+input.substring(4,6)+"-"+input.substring(6,8);
				format.parse(str);
			} catch (Exception e) {
				return "日期格式錯誤";
			}
		} else if(type.equals("phone")) {
			if(input.matches("^09\\d{2}(\\d{6}|-\\d{3}-\\d{3})$")) {
			} else {
				return "手機格式錯誤";
			}
		} else if(type.equals("code")) {
			if(input.length() < 8) {
				return "密碼請輸入8碼";
			}
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
			if(!input.matches(enameReg)) {
				return "密碼請勿輸入特殊字元";
			}
		} else if(type.equals("num")) {
			Integer numChk = null;
			try {
				numChk = Integer.valueOf(input);
			} catch (NumberFormatException e) {
				return "請填數字";
			}
		}
		return "ok";
	}

//	public static void main(String[] args) {
//		System.out.println(check("aa123AA","word"));
//	}
}
