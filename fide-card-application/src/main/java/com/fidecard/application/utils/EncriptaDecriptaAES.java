package com.fidecard.application.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
 * https://www.devglan.com/online-tools/aes-encryption-decryption
 */
@Configuration
public class EncriptaDecriptaAES {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EncriptaDecriptaAES.class);
	
	static String IV = "JKAFAAAAGAAAXALA";
	
	@Value("${chave.encriptacao}")
	private String chaveEncriptacao;
	
	
	public static void main(String[] args) {
		EncriptaDecriptaAES aes = new EncriptaDecriptaAES();
		
		byte[] senhaCripto = aes.encrypt("senha123");
		
		String decrypt = aes.decrypt(senhaCripto);
		
		System.out.println(decrypt);
	}
	public byte[] encrypt(String textoPuro) {
		Cipher encripta = null;
		try {
			encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");
			encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
			return encripta.doFinal(textoPuro.getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.error("Erro ao encrypt o texto, retornando texto puro ", e);
			return textoPuro.getBytes(StandardCharsets.UTF_8);
		}
	}
	
	public String decrypt(byte[] textoEncriptado) {
		try {
			Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");
			decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return new String(decripta.doFinal(textoEncriptado), "UTF-8");
		} catch (Exception e) {
			LOGGER.error("Erro ao decrypt o texto, retornando texto puro ", e);
			return new String(textoEncriptado);
		}
	}
	
}
