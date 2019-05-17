package com.yffd.bcap.uamc.domain.test;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        String str1 = UUID.randomUUID().toString();
        String str2 = UUID.randomUUID().toString().replace("-", "");
        for (int i =0; i< 10;i++) System.out.println(str1 + ">>" + str2 + ">>" + str1.length() + ">>" + str2.length());
    }
}
