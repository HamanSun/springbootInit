package cn.jjsunw.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 封装一些正则相关的操作
 */
public final class FormatUtil {

	/**
	 * Alphanumeric characters
	 */
	public static final String REG_ALNUM = "\\p{Alnum}";
	/**
	 * Alphabetic characters
	 */
	public static final String REG_ALPHA = "\\p{Alpha}";
	/**
	 * ASCII characters
	 */
	public static final String REG_ASCII = "\\p{ASCII}";
	/**
	 * Space and tab
	 */
	public static final String REG_BLANK = "\\p{Blank}";
	/**
	 * Control characters
	 */
	public static final String REG_CNTRL = "\\p{Cntrl}";
	/**
	 * Digits
	 */
	public static final String REG_DIGITS = "\\p{Digit}";
	/**
	 * SVisible characters (i.e. anything except spaces, control characters, etc.)
	 */
	public static final String REG_GRAPH = "\\p{Graph}";
	/**
	 * Lowercase letters
	 */
	public static final String REG_LOWER = "\\p{Lower}";
	/**
	 * Visible characters and spaces (i.e. anything except control characters, etc.)
	 */
	public static final String REG_PRINT = "\\p{Print}";
	/**
	 * Punctuation and symbols.
	 */
	public static final String REG_PUNCT = "\\p{Punct}";
	/**
	 * All whitespace characters, including line breaks
	 */
	public static final String REG_SPACE = "\\p{Space}";
	/**
	 * Uppercase letters
	 */
	public static final String REG_UPPER = "\\p{Upper}";
	/**
	 * Hexadecimal digits
	 */
	public static final String REG_XDIGIT = "\\p{XDigit}";
	/**
	 * 空白行
	 */
	public static final String REG_SPACE_LINE = "\\n\\s*\\r";
	/**
	 * 首尾空白字符
	 */
	public static final String REG_SPACE_POINT = "^\\s*|\\s*$";
	/**
	 * HTML
	 */
	public static final String REG_HTML = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";
	/**
	 * Email
	 */
	public static final String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/**
	 * 国内固定电话
	 */
	public static final String REG_FIXED_TELEPHONE = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
	/**
	 * 邮政编码
	 */
	public static final String REG_POSTALCODE = "[1-9]\\d{5}(?!\\d)";
	/**
	 * 身份证编码
	 */
	public static final String REG_IDENTIFICATION_CARD = "\\d{15}|\\d{18}";
	/**
	 * URL地址
	 */
	public static final String REG_URL = "^http://([w-]+.)+[w-]+(/[w-./?%&=]*)?$";
	/**
	 * 移动电话
	 */
	public static final String REG_MOBILE_TELEPHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
	/**
	 * 合法的名字（字母开头，允许5-16字节，允许字母数字下划线）
	 */
	public static final String REG_LEGAL_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
	/**
	 * i地址
	 */
	public static final String REG_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

	/**
	 * 纯数字
	 */
	public static final String NUMERIC = "^[0-9\\-]+$";
	/**
	 * 浮点数
	 */
	public static final String FLOAT = "^[0-9\\-\\.]+$";
	/**
	 * 纯字母
	 */
	public static final String ABC = "^[a-z|A-Z]+$";

	/**
	 * 判断字符串str是否符合正则表达式reg
	 *
	 * @param str
	 *            需要处理的字符串
	 * @param reg
	 *            正则
	 * @return 是否匹配
	 */
	public final static boolean isMatche(String str, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * 获取符合reg正则表达式的字符串在String中出现的次数
	 *
	 * @param str
	 *            需要处理的字符串
	 * @param reg
	 *            正则
	 * @return 出现的次数
	 */
	public final static int countSubStrReg(String str, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		int i = 0;
		while (m.find()) {
			i++;
		}
		return i;
	}

	/**
	 * 判断是否是符合邮箱
	 *
	 * @param email
	 *            判断的字符串
	 * @return 是否是符合的邮箱
	 */
	public final static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern.compile(REG_EMAIL);
		return pattern.matcher(email).matches();
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharUpperCase(String s) {
		StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, s.length()));
		return sb.toString();
	}

	public static String hideChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		for (int i = 1; i < chars.length - 1; i++) {
			if (i < len) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	public static String hideFirstChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			for (int i = 0; i < 1; i++) {
				chars[i] = '*';
			}
		} else {
			for (int i = 0; i < len; i++) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	public static String hideLastChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = '*';
			}
		} else {
			for (int i = chars.length - 1; i > chars.length - len - 1; i--) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	public static String hideNumber(String str) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= 7) {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = '*';
			}
		} else {
			for (int i = 3; i < chars.length - 4; i++) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	public static String contact(Object[] args) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static String array2Str(Object[] arr) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			s.append(arr[i]);
			if (i < arr.length - 1) {
				s.append(",");
			}
		}
		return s.toString();
	}

	/**
	 * 指定起始位置字符串隐藏
	 * 
	 * @param str
	 * @param index1
	 * @param index2
	 * @return
	 */
	public static String hideStr(String str, int index1, int index2) {
		if (str == null)
			return null;
		String str1 = str.substring(index1, index2);
		String str2 = str.substring(index2);
		String str3 = "";
		if (index1 > 0) {
			str1 = str.substring(0, index1);
			str2 = str.substring(index1, index2);
			str3 = str.substring(index2);
		}
		char[] chars = str2.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			chars[i] = '*';
		}
		str2 = new String(chars);
		String str4 = str1 + str2 + str3;
		return str4;
	}

	// 四舍五入保留两位小数点
	public static String SetNumberFractionDigits(String str) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Float.valueOf(str));
	}

	/**
	 * 根据身份证计算性别
	 * 
	 * @param cardId
	 * @return
	 */
	public static int getSexByCardid(String cardId) {
		int sexNum = 0;
		if (cardId.length() == 15) {
			sexNum = cardId.charAt(13);
		} else {
			sexNum = cardId.charAt(16);
		}
		if (sexNum % 2 == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 根据身份证计算生日
	 * 
	 * @param cardId
	 * @return
	 */
	public static String getBirthdayByCardid(String cardId) {
		String birth = null;
		if (cardId.length() == 15) {
			birth = cardId.substring(6, 12);
		} else {
			birth = cardId.substring(6, 14);
		}
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = null;
		try {
			birthday = sf2.format(sf1.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return birthday;
	}

	public static String getFileSuffixName(String fileName) {
		String suffix = null;
		if (fileName != null) {
			int last = fileName.lastIndexOf('.');
			suffix = fileName.substring(last);
		}
		return suffix;
	}

	/**
	 * 格式化数字
	 * 
	 * @param num
	 * @return
	 */
	public static String getFormatNumber(String num) {
		int number = Integer.parseInt(num);
		if (Integer.parseInt(num) >= 100000) {
			BigDecimal accountB = new BigDecimal(number);
			return accountB.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros()
					.toPlainString() + "万元";
		} else {
			return number + "元";
		}
	}
}
