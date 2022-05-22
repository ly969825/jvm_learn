package com.company;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64 {

    //文件转字符串
    public static String file2String(File file){
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String temp;
            while((temp = buffer.readLine()) !=null ){
                sb.append(temp);

            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    //加密
    public static String setEncryptionBase64(String str){
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if( b != null){
            s = new BASE64Encoder().encode(b);
        }
        return s;

    }

    //解密
    public static String getDecodeBase64(String str){
        byte[] b = null;
        String result = null;
        if(str != null){
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String phone ="ca fe ba be 20 20 20 34 20 1c 0a 20 06 20 0e 09 20 0f 20 10 08 20 11 0a 20 12 20 13 07 20 14 07 20 15 01 20 06 3c 69 6e 69 74 3e 01 20 03 28 29 56 01 20 04 43 6f 64 65 01 20 0f 4c 69 6e 65 4e 75 6d 62 65 72 54 61 62 6c 65 01 20 08 3c 63 6c 69 6e 69 74 3e 01 20 0a 53 6f 75 72 63 65 46 69 6c 65 01 20 0a 48 65 6c 6c 6f 2e 6a 61 76 61 0c 20 07 20 08 07 20 16 0c 20 17 20 18 01 20 18 48 65 6c 6c 6f 20 43 6c 61 73 73 20 49 6e 69 74 69 61 6c 69 7a 65 64 21 07 20 19 0c 20 1a 20 1b 01 20 09 6a 76 6d 2f 48 65 6c 6c 6f 01 20 10 6a 61 76 61 2f 6c 61 6e 67 2f 4f 62 6a 65 63 74 01 20 10 6a 61 76 61 2f 6c 61 6e 67 2f 53 79 73 74 65 6d 01 20 03 6f 75 74 01 20 15 4c 6a 61 76 61 2f 69 6f 2f 50 72 69 6e 74 53 74 72 65 61 6d 3b 01 20 13 6a 61 76 61 2f 69 6f 2f 50 72 69 6e 74 53 74 72 65 61 6d 01 20 07 70 72 69 6e 74 6c 6e 01 20 15 28 4c 6a 61 76 61 2f 6c 61 6e 67 2f 53 74 72 69 6e 67 3b 29 56 20 21 20 05 20 06 20 20 20 20 20 02 20 01 20 07 20 08 20 01 20 09 20 20 20 1d 20 01 20 01 20 20 20 05 2a b7 20 01 b1 20 20 20 01 20 0a 20 20 20 06 20 01 20 20 20 03 20 08 20 0b 20 08 20 01 20 09 20 20 20 25 20 02 20 20 20 20 20 09 b2 20 02 12 03 b6 20 04 b1 20 20 20 01 20 0a 20 20 20 0a 20 02 20 20 20 05 20 08 20 06 20 01 20 0c 20 20 20 02 20 0d  ";
        String base64 = Base64.setEncryptionBase64(phone);
        System.out.println("加密后:"+base64);

        String fromBase64 = Base64.getDecodeBase64(base64);
        System.out.println("解密后:"+fromBase64);
    }
}