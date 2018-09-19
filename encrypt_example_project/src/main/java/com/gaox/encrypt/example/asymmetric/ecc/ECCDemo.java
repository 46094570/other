package com.gaox.encrypt.example.asymmetric.ecc;


import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.spec.EllipticCurve;
import java.util.Random;

public class ECCDemo {

//    public static void main(String[] args) throws InsecureCurveException,
//            NotOnMotherException {
//        Random r1 = new Random(100);
//        BigInteger a = new BigInteger(60, r1);// Alice生成随机整数a
//        System.out.println("Alice:" + a);
//        Random r2 = new Random(20);
//        BigInteger b = new BigInteger(50, r2);// Bob生成随机整数b
//        System.out.println("Bob:" + b);
//        EllipticCurve e = new EllipticCurve(new BigInteger("1"),
//                new BigInteger("6"), new BigInteger("11"));
//        System.out.println("EllipticCurve: " + e + " created succesfully!");
//        // 生成基点G
//        ECPoint G = new ECPoint(e, new BigInteger("2"), new BigInteger("7"));
//        ECPoint A, B;
//        A = G.multiply(a);// 计算A=a*G
//        System.out.println("A=a*G: "+a + " * " + G + " = " + A);
//        B = G.multiply(b);// 计算B=b*G
//        System.out.println("B=b*G: "+b + " * " + G + " = " + B);
//        ECPoint Q1, Q2;
//        Q1 = A.multiply(b);// Bob收到Alice传递的A，计算Q =b*A
//        Q2 = B.multiply(a);// Alice收到Bob传递的B，计算Q`=a*B
//        System.out.println("Q1:"+Q1);
//        System.out.println("Q2:"+Q2);
//        System.out.print(Q1.equals(Q2));
//    }
}
