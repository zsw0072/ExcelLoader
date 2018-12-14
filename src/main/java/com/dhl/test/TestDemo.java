package com.dhl.test;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {

    public static void main(String[] args) {
        int[] removeTaget = {0,1,2};
        List<Integer> list  = new ArrayList<Integer>();
        for (int i = 0; i < removeTaget.length; i++) {
            list.add(i);
        }
        int j = 0;

        if(list.contains(j)){
            list.remove(j);
        }
    }
}
