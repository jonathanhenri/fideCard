package com.fidecard.application.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
 * https://www.devglan.com/online-tools/aes-encryption-decryption
 */
@Component
public class EncriptaDecriptaAES {
	
	private static final String chaveEncriptacao = "2s5u8x/A?D(G+KbPeShVmYq3t6w9y$B&";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EncriptaDecriptaAES.class);
	
	static String IV = "JKAFAAAAGAAAXALA";
	
	private static EncriptaDecriptaAES instance;
	
	private Cipher cipher;
	private SecretKeySpec key;
	
	private EncriptaDecriptaAES() {
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");
		} catch (Exception e) {
		
		}
	}
	
	public static EncriptaDecriptaAES getInstance() {
		if (instance == null) {
			instance = new EncriptaDecriptaAES();
		}
		
		return instance;
	}
	
	public static void main(String[] args) {
		EncriptaDecriptaAES aes = new EncriptaDecriptaAES();
		
		//		chaveEncriptacao = "2s5u8x/A?D(G+KbPeShVmYq3t6w9y$B&";
		
		byte[] senhaCripto = aes.encrypt("senha123");
		
		System.out.println(new String(senhaCripto));
		
		String decrypt = aes.decrypt(senhaCripto);
		
		System.out.println(decrypt);
	}
	
	public byte[] encrypt(String textoPuro) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return cipher.doFinal(textoPuro.getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.error("Erro ao encrypt o texto, retornando texto puro ", e);
			return textoPuro.getBytes(StandardCharsets.UTF_8);
		}
	}
	
	public String encryptString(String textoPuro) {
		String textoEncriptado = new String(encrypt(textoPuro));
		
		return textoEncriptado;
	}
	
	public String decrypt(byte[] textoEncriptado) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return new String(cipher.doFinal(textoEncriptado), "UTF-8");
		} catch (Exception e) {
			LOGGER.error("Erro ao decrypt o texto, retornando texto puro ", e);
			return new String(textoEncriptado);
		}
	}
	
}
