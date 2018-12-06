package com.dhl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\arfa\\Desktop\\dhl\\1.txt"));
        HSSFWorkbook xssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = xssfWorkbook.createSheet("2018-08");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\arfa\\Desktop\\dhl\\201808.xlsx");
        int count =0;
        String line;
        HSSFCell cell;
        while((line=br.readLine())!=null){
            String[] strings = line.split("\\|");
            HSSFRow row = sheet.createRow(count);
            for (int i = 0; i < strings.length; i++) {

                cell = row.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(strings[i]);
                xssfWorkbook.write(fos);

            }
            count++;
        }
        br.close();
    }
}