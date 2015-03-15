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
	// 16�����ַ�
	/** The hex digits. */
	private final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// �ֻ��ֽ�����Ϊʮ�������ַ���
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

	// ��һ���ֽ�ת����ʮ��������ʽ���ַ���
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

	// ��inputString����
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

	// ���ַ�������MD5����
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
				// ��������ָ���㷨���Ƶ���ϢժҪ
				MessageDigest md = MessageDigest.getInstance("MD5");
				// ʹ��ָ�����ֽ������ժҪ���������£�Ȼ�����ժҪ����
				byte[] results = md.digest(originString.getBytes());
				// ���õ����ֽ��������ַ�������
				resultString = byteArrayToHexString(results);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return resultString.toUpperCase();
	}

}
