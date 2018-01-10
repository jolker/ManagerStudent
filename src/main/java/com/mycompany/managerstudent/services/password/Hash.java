package com.mycompany.managerstudent.services.password;

import java.security.MessageDigest;

/**
 *
 * @author dientt
 */
public class Hash 
{
    
    private static Hash hash;

    public static Hash getInstance() {
        if (hash == null) {
            hash = new Hash();
        }
        return hash;
    }

    /* business methods */
    public String hashMessage(String message, boolean isSHA) {
        try {
            byte[] input = FormatSupport.string2ByteArr(message);
            byte[] output = hashData(input, isSHA);
            return FormatSupport.byteArr2HexString(output).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }

    /* support methods */
    private byte[] hashData(byte[] input, boolean isSHA) throws Exception {
        String ALGORITHM = !isSHA ? "MD5" : "SHA-256";
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.update(input);
        return md.digest();
    }
}