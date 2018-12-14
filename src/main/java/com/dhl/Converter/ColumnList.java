package com.dhl.Converter;

import java.util.ArrayList;
import java.util.List;

public class ColumnList {

    private static List<Integer> list = new ArrayList<Integer>();

    static {
        Integer[] removeColumnNum = {0, 2, 3, 13, 21, 23, 24, 34, 35, 37, 38, 39, 40, 43, 44, 47, 49};
        for (int i = 0; i < removeColumnNum.length; i++) {
            list.add(removeColumnNum[i]);
        }
    }

    public static List<Integer> getList() {
        return list;
    }
}
