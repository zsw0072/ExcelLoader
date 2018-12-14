package com.dhl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConverter {

    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\Admin\\Desktop\\CAO_201811ecom_base.txt";
        ConvertTxtToExcel(source, source.replace(".txt",".xlsx"),
                "2018-11");
    }

    //convert the DHL monthly report to excel file
    public static void ConvertTxtToExcel(String source,String dest,String sheetName) throws Exception{
        
        FileInputStream fis = new FileInputStream(source);
        Scanner sc = new Scanner(fis,"UTF-8");
        // keep 100 rows in memory, exceeding rows will be flushed to disk
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(100);
        Sheet sheet = sxssfWorkbook.createSheet(sheetName);
        FileOutputStream fos = new FileOutputStream(dest);
        String line;
        int count =0;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            String[] word = line.split("\\|");
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < word.length; i++) {
                if (i==0||i==2||i==3||i==13||i==21||i==23
                        ||i==24||i==34||i==35||i==37
                        ||i==38||i==39||i==40||i==43
                        ||i==44||i==47||i==49){
                    word[i] = "taget";
                }
                list.add(word[i]);
                if (list.contains("taget")){
                    list.remove("taget");
                }
            }

            Row row = sheet.createRow(count);
            for (int j = 0; j < list.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(list.get(j));

            }
            fos.flush();
            count++;
        }
        sxssfWorkbook.write(fos);
        fos.flush();
        fos.close();
        fis.close();
    }
}
