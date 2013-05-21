package com.brazoft.foundation.security;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.brazoft.foundation.util.ExceptionHandler;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class Crypto
{
	public static final String	ALGORITHM_AES					= "AES";

	public static final String	ALGORITHM_BLOWFISH				= "Blowfish";

	public static final String	ALGORITHM_DES					= "DES";

	public static final String	ALGORITHM_DESede				= "DESede";

	public static final String	ALGORITHM_PBE_MD5_DES			= "PBEWithMD5AndDES";

	public static final String	ALGORITHM_PBE_HMAC_SHA1_DESede	= "PBEWithHmacSHA1AndDESede";

	public static final String	ALGORITHM_RC2					= "RC2";

	public static final String	ALGORITHM_RC4					= "RC4";

	public static final String	ALGORITHM_RC5					= "RC5";

	public static final String	ALGORITHM_RSA					= "RSA";

	public static final String	MODE_NONE						= "NONE";

	public static final String	MODE_CBC						= "CBC";

	public static final String	MODE_CFB						= "CFB";

	public static final String	MODE_ECB						= "ECB";

	public static final String	MODE_OFB						= "OFB";

	public static final String	MODE_PCBC						= "PCBC";

	public static final String	PADDING_NONE					= "NoPadding";

	public static final String	PADDING_OAEP_MD5_MGF1			= "OAEPWithMD5AndMGF1Padding";

	public static final String	PADDING_PKCS5					= "PKCS5Padding";

	public static final String	PADDING_SSL3					= "SSL3Padding";

	public static final String	ENCODING						= "UTF-8";

	public static SecretKey generateKey(String algorithm)
	{
		try
		{
			return KeyGenerator.getInstance(algorithm).generateKey();
		}
		catch (NoSuchAlgorithmException e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}

	public static String encrypt(String message, String algorithm, SecretKey key)
	{
		Cipher encryptor;

		try
		{
			encryptor = Cipher.getInstance(algorithm);
			encryptor.init(Cipher.ENCRYPT_MODE, key);

			return new BASE64Encoder().encode(encryptor.doFinal(message.getBytes(Crypto.ENCODING)));
		}
		catch (Exception e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}

	public static String decrypt(String message, String algorithm, SecretKey key)
	{
		Cipher encryptor;
		
		try
		{
			encryptor = Cipher.getInstance(algorithm);
			encryptor.init(Cipher.DECRYPT_MODE, key);

			return new String(encryptor.doFinal( new BASE64Decoder().decodeBuffer(message)), Crypto.ENCODING);
		}
		catch (Exception e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}

	public static boolean compare(String s1, String s2)
	{
		byte[] stored;
		byte[] typed;
		boolean match;

		try
		{
			match = true;
			stored = s1.getBytes(Crypto.ENCODING);
			typed = s2.getBytes(Crypto.ENCODING);
	
			if (stored.length != typed.length)
			{
				match = false;
			}
			else
			{
				for (int i = 0; i < stored.length; i++)
				{
					if (stored[i] != typed[i])
					{
						match = false;
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			match = false;
		}

		return match;
	}
}