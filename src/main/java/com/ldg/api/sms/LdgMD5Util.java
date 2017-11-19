package com.ldg.api.sms;

import java.security.MessageDigest;

public class LdgMD5Util {
    public static String MD5(String sourceStr, int bit) throws Exception {
        String result = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sourceStr.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        result = buf.toString();
        //System.out.println("MD5(" + sourceStr + ",32) = " + result);
        //System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        if (bit == 32) {
            return result;
        } else if (bit == 16) {
            return buf.toString().substring(8, 24);
        }
        return result;
    }
}
