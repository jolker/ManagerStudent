package com.mycompany.managerstudent.services.password;

/**
 *
 * @author dientt
 */
public class FormatSupport {
    private static final String ENCODING = "UTF8";

    public static byte[] string2ByteArr(String data) throws Exception {
        return data.getBytes(ENCODING);
    }
    
    public static String byteArr2HexString(byte[] Source) throws Exception {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Source.length; i++) {
            result.append(Integer.toString((Source[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
