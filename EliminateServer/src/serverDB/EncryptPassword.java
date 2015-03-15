/*
 * 
 */
package serverDB;

import java.security.MessageDigest;

// TODO: Auto-generated Javadoc
/**
 * The Class EncryptPassword.
 */
public class EncryptPassword {
	// 16进制字符
	/** The hex digits. */
	private final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 轮换字节数组为十六进制字符串
	/**
	 * Byte array to hex string.
	 * 
	 * @param b
	 *            the b
	 * @return the string
	 */
	private String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	// 将一个字节转化成十六进制形式的字符串
	/**
	 * Byte to hex string.
	 * 
	 * @param b
	 *            the b
	 * @return the string
	 */
	private String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	// 把inputString加密
	/**
	 * Creates the password.
	 * 
	 * @param inputString
	 *            the input string
	 * @return the string
	 */
	public String createPassword(String inputString) {
		return encodeByMD5(inputString);
	}

	// 对字符串进行MD5编码
	/**
	 * Encode by m d5.
	 * 
	 * @param originString
	 *            the origin string
	 * @return the string
	 */
	private String encodeByMD5(String originString) {
		String resultString = "";
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				resultString = byteArrayToHexString(results);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return resultString.toUpperCase();
	}

}
