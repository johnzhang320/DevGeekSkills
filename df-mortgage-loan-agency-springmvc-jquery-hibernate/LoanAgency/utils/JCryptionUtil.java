package com.frontend.encrypt.utils;

import java.io.UnsupportedEncodingException;  
import java.net.URLDecoder;  
import java.security.GeneralSecurityException;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.interfaces.RSAPublicKey;  
import java.util.HashMap;  
import java.util.Map;  
import org.apache.log4j.*;  
import javax.crypto.Cipher;  
  
  
 
public class JCryptionUtil {  
    private static Logger Log= Logger.getLogger(JCryptionUtil.class);
    
    private int keyLength=Constants.KEY_LENGTH;
    
    public static final java.security.Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();  
    /** 
     * Constructor 
     */  
    public JCryptionUtil() throws Exception {  
        java.security.Security.addProvider(provider);  
    }  
  
    /** 
     * Generates the Keypair with the given keyLength. 
     *  
     * @param keyLength 
     *            length of key 
     * @return KeyPair object 
     * @throws RuntimeException 
     *             if the RSA algorithm not supported 
     */  
    public KeyPair generateKeypair() throws Exception {  
        try {  
            KeyPairGenerator kpg;   
            try {  
                kpg = KeyPairGenerator.getInstance("RSA");  
            } catch (Exception e) {  
                kpg = KeyPairGenerator.getInstance("RSA",provider);  
            }  
            kpg.initialize(this.keyLength);  
            KeyPair keyPair = kpg.generateKeyPair();  
            Log.info("Generated Key Pair: "+keyPair.getPublic());
            return keyPair;  
        } catch (NoSuchAlgorithmException e1) {  
        	 Log.info("RSA algorithm not supported", e1);
            throw new RuntimeException("RSA algorithm not supported", e1);  
        } catch (Exception e) {  
        	 Log.info("other exceptions", e);
            throw new Exception("other exceptions", e);  
        }  
    }  
  
    /** 
     * Decrypts a given string with the RSA keys 
     *  
     * @param encrypted 
     *            full encrypted text 
     * @param keys 
     *            RSA keys 
     * @return decrypted text 
     * @throws RuntimeException 
     *             if the RSA algorithm not supported or decrypt operation failed 
     */  
    public String decrypt(String encrypted, KeyPair keys) throws Exception {  
        Cipher dec=null;  
      
        try {  
            try {  
                dec = Cipher.getInstance("RSA/NONE/NoPadding");  
            } catch (Exception e) {  
            	/**
            	 * Use BouncyCastleProvider get escaped from this exception : Cannot find any provider supporting RSA/NONE/NoPadding
            	 */
            	/**
            	 *  Only before JDK6.0_26 support  dec = Cipher.getInstance("RSA/NONE/NoPadding",provider);  
            	 *  JDK1.6_31 Ciper.getInstance(algorithm,provider_string) , provider String "BC" means BouncyCastleProvider, 
            	 */
                dec = Cipher.getInstance("RSA/NONE/NoPadding","BC");  
            }  
            if (null!=dec) {
            	dec.init(Cipher.DECRYPT_MODE, keys.getPrivate());  
            }
        } catch (GeneralSecurityException e) {  
        	Log.info("RSA algorithm not supported", e);
            throw new RuntimeException("RSA algorithm not supported", e);  
        }  
        String[] blocks = encrypted.split("\\s");  
        StringBuffer result = new StringBuffer();  
        try {  
            for (int i = blocks.length - 1; i >= 0; i--) {  
                byte[] data = hexStringToByteArray(blocks[i]);  
                byte[] decryptedBlock = dec.doFinal(data);  
                result.append(new String(decryptedBlock));  
            }  
        } catch (GeneralSecurityException e) {  
        	 Log.info("Decrypt error", e);
            throw new RuntimeException("Decrypt error", e);  
        }  
        /** 
         * Some code is getting added in first 2 digits with Jcryption need to investigate 
         */  
        return result.reverse().toString().substring(2);  
    }  
  
    /** 
     * Parse url string (Todo - better parsing algorithm) 
     *  
     * @param url 
     *            value to parse 
     * @param encoding 
     *            encoding value 
     * @return Map with param name, value pairs 
     */  
    public Map parse(String url, String encoding) {  
        try {  
            String urlToParse = URLDecoder.decode(url, encoding);  
            String[] params = urlToParse.split("&");  
            Map parsed = new HashMap();  
            for (int i = 0; i < params.length; i++) {  
                String[] p = params[i].split("=");  
                String name = p[0];  
                String value = (p.length == 2) ? p[1] : null;  
                parsed.put(name, value);  
            }  
            return parsed;  
        } catch (UnsupportedEncodingException e) {  
        	 Log.info("Unknown encoding.", e);
            throw new RuntimeException("Unknown encoding.", e);  
        }  
    }  
  
    /** 
     * Return public RSA key modulus 
     *  
     * @param keyPair 
     *            RSA keys 
     * @return modulus value as hex string 
     */  
    public String getPublicKeyModulus(KeyPair keyPair) {  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        return publicKey.getModulus().toString(16);  
    }  
  
    /** 
     * Return public RSA key exponent 
     *  
     * @param keyPair 
     *            RSA keys 
     * @return public exponent value as hex string 
     */  
    public String getPublicKeyExponent(KeyPair keyPair) {  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        return publicKey.getPublicExponent().toString(16);  
    }  
  
    /** 
     * Max block size with given key length 
     *  
     * @param keyLength 
     *            length of key 
     * @return numeber of digits 
     */  
    public int getMaxDigits() {  
        return ((this.keyLength * 2) / 16) + 3;  
    }  
  
    /** 
     * Convert byte array to hex string 
     *  
     * @param bytes 
     *            input byte array 
     * @return Hex string representation 
     */  
    public String byteArrayToHexString(byte[] bytes) {  
        StringBuffer result = new StringBuffer();  
        for (int i = 0; i < bytes.length; i++) {  
            result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)  
                    .substring(1));  
        }  
        return result.toString();  
    }  
  
    /** 
     * Convert hex string to byte array 
     *  
     * @param data 
     *            input string data 
     * @return bytes 
     */  
    public byte[] hexStringToByteArray(String data) {  
        int k = 0;  
        byte[] results = new byte[data.length() / 2];  
        for (int i = 0; i < data.length();) {  
            results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);  
            results[k] += (byte) (Character.digit(data.charAt(i++), 16));  
            k++;  
        }  
        return results;  
    }  
  
    /** 
     * @param args 
     */  
//  public static void main(String[] args) {  
//      JCryptionUtil jCryption = new JCryptionUtil();  
//      System.out.println(jCryption.toPublicKeyString());  
//  }  
  
    /** 
     * @return 
     */  
    public String toPublicKeyString() throws Exception {  
        KeyPair keys = generateKeypair();  
        StringBuffer out = new StringBuffer();  
  
        String e = getPublicKeyExponent(keys);  
        String n = getPublicKeyModulus(keys);  
        String md = String.valueOf(getMaxDigits());  
  
        out.append("{\"e\":\"");  
        out.append(e);  
        out.append("\",\"n\":\"");  
        out.append(n);  
        out.append("\",\"maxdigits\":\"");  
        out.append(md);  
        out.append("\"}");  
  
        return out.toString();  
    }  
  
}  
