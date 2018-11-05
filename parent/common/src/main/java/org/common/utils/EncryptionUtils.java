package org.common.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author hws
 *   2018-9-7
 *
 */
public class EncryptionUtils {
	
	public static final String ALGORITHM = "HmacMD5";
	private static final String KEY = "IAMSTILLMISSINGYOUUNTILTODAY";
	private static SecureRandom secureRandom = null;
	
	/**
	 * 产生随机数
	 * @param numBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getRandomNum(int numBytes) throws NoSuchAlgorithmException{
		byte[] bytes = new byte[numBytes];
		secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.nextBytes(bytes);
		
		return bytes;
	}
	/**
	 * 
	 * @param data 需要加密的数据
	 * @param key 密匙
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
    public static byte[] encryptHMAC(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException{

    	if(null == key)key = KEY;
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        
        return mac.doFinal(data);

    }
    /**
     * 将byte转换为string 过程不可逆
     * @param byte1
     * @param byte2
     * @return
     */
    public static String transformByteToString(byte[] bytes){
    	StringBuffer sBuffer = new StringBuffer();
    	for(byte el:bytes){
    		int v = el & 0xff;
    		sBuffer.append(Integer.toHexString(v));
    	}
    	return sBuffer.toString();
    }
    /**
     * 合并byte数组
     * @param byteNames
     * @return
     */
    public static byte[] combineByteArray(byte[]... byteNames){
    	if(null == byteNames || byteNames.length<1)return null;
    	if(byteNames.length == 1)return byteNames[0];
    	int bLength = 0;
    	byte[] newByte = null;
		for(byte[] byteName:byteNames){
			bLength += byteName.length;
		}
		if(bLength != 0){
			newByte = new byte[bLength];
		}
		for(int i=0;i<byteNames.length;i++){
			if(i==0){
				System.arraycopy(byteNames[i], 0, newByte, 0, byteNames[i].length);
			}else{
				System.arraycopy(byteNames[i], 0, newByte, byteNames[i-1].length, byteNames[i].length);
			}
			
		}
		
    	return newByte;
    }

}
