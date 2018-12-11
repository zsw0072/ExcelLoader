package com.dhl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConverter {

    public static void main(String[] args) throws Exception {
        String source = "c:\\users\\arfa\\desktop\\dhl\\CAO_201810ecom_base.txt";
        ConvertTxtToExcel(source, source.replace(".txt",".xlsx"),
                "2018-10");
    }

    //convert the DHL monthly report to excel file
    @Test
    public static void ConvertTxtToExcel(String source,String dest,String sheetName) throws Exception{
        
        FileInputStream fis = new FileInputStream(source);
        Scanner sc = new Scanner(fis,"UTF-8");
        // keep 100 rows in memory, exceeding rows will be flushed to disk
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(100);
        Sheet sheet = sxssfWorkbook.createSheet(sheetName);
        FileOutputStream fos = new FileOutputStream(dest);
        String line;
        int count =0;
        List<String> list = new ArrayList<String>();
        while(sc.hasNextLine()){
            line = sc.nextLine();
            String[] word = line.split("\\|");
            Row row = sheet.createRow(count);
            for (int i = 0; i < word.length; i++) {
                list.add(word[i]);
                if (i==0){
                    list.remove(i);
                    continue;
                }
                Cell cell = row.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(word[i]);
                fos.flush();
            }
            count++;
        }
        sxssfWorkbook.write(fos);
        fos.flush();
        fos.close();
        fis.close();
    }
}
