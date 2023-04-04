/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author KevinKeyss
 */
public class PasswordGenerator {
    public static final void main(String[] args) throws Exception{
        String encript = "assets";
        System.out.println("Encriptar : " + encript);
        System.out.println("SHA512    : " + getSha512(encript));
        encript = getMD5(encript);
        System.out.println("MD5       : " + encript);
        System.out.println("MD5 Desencriptada     : " + setMd5(encript));
        
    }
    
    private static String sha512Hex(byte[] array){
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            buffer.append(Integer.toString((array[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return buffer.toString();
    }
    //Encripta a SHA512
    public static String getSha512(String pwd) {
    
        String retValue  = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            retValue = sha512Hex(md.digest(pwd.getBytes()));
        } catch (Exception e) {
            System.err.println("Ocurrió un error : " + e.getMessage());
        }
        
        return retValue;
    }
    
    //Encripta a SHA512
    public static String getMD5(String pwd) {
    
        String retValue  = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            retValue = sha512Hex(md.digest(pwd.getBytes()));
        } catch (Exception e) {
            System.err.println("Ocurrió un error : " + e.getMessage());
        }
        
        return retValue;
    }
    
    //Encripta a MD5
    public static String getMd5(String texto) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString;
 
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
            
            return base64EncryptedString;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            return null;
        }
    }
    //Desencripta MD5
    public static String setMd5(String textoEncriptado) throws Exception {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        //String base64EncryptedString;
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            //base64EncryptedString = new String(plainText, "UTF-8");
            
             return new String(plainText, "UTF-8");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
             return null;
        }
    }
}
