package com.dhl.test;

import org.junit.Test;

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

    @Test
    public void checklist(){
        int[] intArr = {0,1,2,3};
        int i = 2;
        String s = intArr.toString();
        System.out.println(s);

    }
}
