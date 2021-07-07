package com.sda.testing.util;

public class CalculatingUtility {
    public static int sum(int a, int b){
        if(a == b){
            throw new RuntimeException("Bad!");
        }
        return a + b;
    }
}
