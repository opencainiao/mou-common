package org.mou.common.security;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.mou.common.StringUtil;

/****
 * <code>
 * 存储一个密码：
 * 1. 使用CSPRNG生成一个长的随机盐。
 * 2. 将密码和盐拼接在一起，使用标准的加密hash函数比如SHA256进行hash
 * 3. 将盐和hash记录在用户数据库中
 * </code>
 * 
 * <code>
 * 验证一个密码：
 * 1. 从数据库中取出用户的盐和hash
 * 2. 将用户输入的密码和盐按相同方式拼接在一起，使用相同的hash函数进行hash
 * 3. 比较计算出的hash跟存储的hash是否相同。如果相同则密码正确。反之则密码错误。
 * </code>
 * 
 * @author sks
 * 
 */
public class EncryptMou {

	/****
	 * 对from 加盐加密
	 * 
	 * @param from
	 * @return
	 */
	public static String encrypt(String from) {
		try {
			return PasswordHash.createHash(from);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/****
	 * from与hashed之后的字符串比较是否一致
	 * 
	 * @param from
	 * @param Hashed
	 * @return
	 */
	public static boolean validate(String from, String Hashed) {
		try {
			return PasswordHash.validatePassword(from, Hashed);
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	public static Map<String, String> getHashedPassword(String hashed) {

		Map<String, String> rtnMap = new HashMap<String, String>();

		String[] params = hashed.split(":");

		rtnMap.put("SALT", params[1]);
		rtnMap.put("HASH", params[2]);
		rtnMap.put("WHOLE", hashed);

		return rtnMap;
	}

	/****
	 * 进行des加密
	 * 
	 * @param toEncryptStr
	 * @param password
	 *            秘钥，长度必须是8的倍数
	 * @return
	 */
	public static String encryptAes(String toEncryptStr, String password) {

		String usedPass = password;
		if (StringUtil.isEmpty(usedPass) || usedPass.trim().length() % 8 != 0) {
			usedPass = "12345678";
		} else {
			usedPass = usedPass.trim();
		}

		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(usedPass.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = toEncryptStr.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return parseByte2HexStr(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/****
	 * 对字符串进行DES解密
	 * 
	 * @param toDecryptStr
	 * @param password
	 *            秘钥，长度必须是8的倍数
	 * @return
	 * @throws Exception
	 */
	public static String decryptAes(String toDecryptStr, String password) {

		String usedPass = password;
		if (StringUtil.isEmpty(usedPass) || usedPass.trim().length() % 8 != 0) {
			usedPass = "12345678";
		} else {
			usedPass = usedPass.trim();
		}

		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(usedPass.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化

			byte[] decryptFrom = parseHexStr2Byte(toDecryptStr);

			byte[] result = cipher.doFinal(decryptFrom);
			return new String(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/****
	 * 测试加盐加密密码
	 */
	public static void testEncryptPassword() {
		String password = "wodejia";

		System.out.println("==============");

		String passwordHashed = EncryptMou.encrypt(password);
		System.out.println(getHashedPassword(passwordHashed));
		System.out.println("===");

		for (int i = 0; i < 10; ++i) {
			System.out.println(EncryptMou.validate("wode" + i, passwordHashed));
		}

		System.out.println("===");

		for (int i = 0; i < 10; ++i) {
			System.out.println(EncryptMou.validate("wodejia", passwordHashed));
		}
	}

	public static void testEncryptDesPassword() {
		String toEncryptStr = "wodejia";

		System.out.println("==============");

		String strEncrypted = EncryptMou.encryptAes(toEncryptStr, null);

		System.out.println("des加密【toEncryptStr】结果【strEncrypted】".replaceAll(
				"toEncryptStr", toEncryptStr).replaceAll("strEncrypted",
				strEncrypted));

		String result = EncryptMou.decryptAes(strEncrypted, "");

		System.out.println("解密结果:");

		System.out.println(result);
	}

	public static void main(String[] args) {
		testEncryptDesPassword();
	}
}
