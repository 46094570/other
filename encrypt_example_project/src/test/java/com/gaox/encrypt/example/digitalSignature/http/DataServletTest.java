package com.gaox.encrypt.example.digitalSignature.http;

import com.gaox.encrypt.example.digitalSignature.http.utils.AESHttpCoder;
import com.gaox.encrypt.example.digitalSignature.http.utils.HttpUtils;
import com.gaox.encrypt.example.digitalSignature.http.utils.MessageUtils;
import com.gaox.encrypt.example.digitalSignature.http.utils.RSAHttpSignature;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;


public class DataServletTest {
//    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJGXdi42S/Fquw68iuU/PD9fAMVYMtFBYFat9YuDSxiQq4hw9sRBWL7eYPY4DwIBGCd/NKsbPzbxJ+Ww3BOI8p0CAwEAAQ==";
    private static final String PUBLIC_KEY = "30819f300d06092a864886f70d010101050003818d0030818902818100c535627ca25851ab88122a53c9f84b267f86357c180ee46ecb532c9458f049da044b79e7c25ab29ec542e1607e10fa636a9da89566c7464ab86af1ccd1e26338a32f257db0eb8a58782dacefb17d255a0bb171936f563d1e1efda9ef1ac02bc1df879625ff5c75138dc841e58aee228a3abe969dfc78b93f70cf0d36f1ac7e7b0203010001";
    private static final String URL = "http://localhost:8080/dataserver";
    private static final String CHARACTER_SET="utf-8";

    @Test
    public final void test() throws Exception {
        String str = MessageUtils.generateClientMessage();
        byte[] data = str.getBytes(CHARACTER_SET);
        String aesKeyString = AESHttpCoder.initKeyString();
        System.out.println("aesKeyString:\r\n"+aesKeyString);
        System.out.println("aesKeyString Length:\r\n"+aesKeyString.getBytes(CHARACTER_SET).length);
        byte[] aesData = AESHttpCoder.encrypt(data, aesKeyString);
        System.out.println("aesData:\r\n"+Arrays.toString(aesData));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(aesData);
        outputStream.write(aesKeyString.getBytes(CHARACTER_SET));
        byte[] sendData = outputStream.toByteArray();
        System.out.println("sendData:\r\n"+ Arrays.toString(sendData));
        System.out.println("sendData length:\r\n"+ sendData.length);
        outputStream.flush();
        outputStream.close();
        byte[] input = HttpUtils.postRequest(URL, RSAHttpSignature.encryptByPublicKey(sendData, PUBLIC_KEY));
        System.out.println("Message:\t" + new String(input,CHARACTER_SET));
        System.out.println("Message length:\t" + input.length);
        input = RSAHttpSignature.decryptByPublicKey(input, PUBLIC_KEY);
        byte[] signByte = Arrays.copyOfRange(input, 0, AESHttpCoder.KEY_SIZE);
        byte[] dataByte = Arrays.copyOfRange(input, AESHttpCoder.KEY_SIZE, input.length);
        System.out.println("undecrypt data:\r\n" + Arrays.toString(dataByte));
        System.out.println("undecrypt data:\r\n" + new String(dataByte));
        dataByte = AESHttpCoder.decrypt(dataByte,aesKeyString);
        System.out.println("decrypted data:\r\n" + Arrays.toString(dataByte));
        System.out.println("decrypted data:\r\n" + new String(dataByte));
        String sign = new String(signByte,CHARACTER_SET);
        String result = new String(dataByte,CHARACTER_SET);
        System.out.println("sign:\r\n" + sign+"\r\nsign length:\r\n"+sign.length());
        System.out.println("result:\r\n" + result);
//        assertTrue(RSAHttpSignature.verify(result.getBytes(),PUBLIC_KEY,sign));
        System.out.println("verify:\r\n"+RSAHttpSignature.verify(result.getBytes(),PUBLIC_KEY,sign));
    }
}
