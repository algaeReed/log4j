package com.example.exp;

import java.io.IOException;

public class Exp {
    public Exp(){
        try {
            Runtime.getRuntime().exec("open -a Calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Exp exploit = new Exp();
    }

}
