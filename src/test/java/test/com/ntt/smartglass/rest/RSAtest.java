//package test.com.ntt.smartglass.rest;
//
//import com.ntt.smartglass.common.util.RSAUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import sun.misc.BASE64Encoder;
//
//import java.security.PublicKey;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.HashMap;
//
///**
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//public class RSAtest {
//
//    @Test
//    public void getKey() throws Exception {
//        HashMap<String, Object> map = RSAUtils.getKeys();
//        //生成公钥和私钥
//        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
//        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
//        byte[] a = publicKey.getEncoded();
//        byte[] c = privateKey.getEncoded();
//        String b = new BASE64Encoder().encode(a);
//        String priKey = new BASE64Encoder().encode(c);
//        System.out.print(b);
//        System.out.print(c);
//        boolean abc = publicKey.equals(publicKey);
//
//        String pwd1 = "123456";
//        String pwd1XX = RSAUtils.encryptByPublicKey(pwd1, publicKey);
//        String pwd12X = RSAUtils.encryptByPublicKey(pwd1, publicKey);
//
//        boolean jkj = pwd1XX.equals(pwd12X);
//        System.out.print(abc);
//    }
//
//
//}
