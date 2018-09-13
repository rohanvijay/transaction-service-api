package com.capone.transactions.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

@Service
public class EncryptionDecryptionUtility {
	static Cipher cipher;
	KeyGenerator keyGenerator=null;
	SecretKey secretKey=null;
	
	public EncryptionDecryptionUtility(){
		 try {
			keyGenerator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			
		}
		 secretKey = keyGenerator.generateKey();
	}
	public String encrypt(String plainText)
			throws Exception {
		
		
		keyGenerator.init(128);
		
		cipher = Cipher.getInstance("AES");
		
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}
	
	public String decrypt(String encryptedText)
			throws Exception {
		
		cipher = Cipher.getInstance("AES");
		
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}
