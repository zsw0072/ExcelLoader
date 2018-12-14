package com.dhl.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class POIExcelUtil {

    public static Workbook getWorkBook(File file, InputStream in) throws IOException {

        Workbook workbook = null;
        if (file.length()>10240000) {
            workbook = new SXSSFWorkbook(100);
        }else if (file.getName().endsWith(".xls")) {
            workbook = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(in);
        }

        return workbook;
    }


    public static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellType()) {
            case 4:
                obj = cell.getBooleanCellValue();
                break;
            case 5:
                obj = cell.getErrorCellValue();
                break;
            case 0:
                obj = cell.getNumericCellValue();
                break;
            case 1:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    public static void forEachWorkbook(String path) throws IOException {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        Workbook workBook = POIExcelUtil.getWorkBook(file, in);
        int totalSheet = workBook.getNumberOfSheets();
        for (int i = 0; i < totalSheet; i++) {
            Sheet sheet = workBook.getSheetAt(i);
            System.out.println("工作表:" + sheet.getSheetName());
            for (Row row : sheet) {
                if (row.getCell(0).toString().equals("")) {
                    return;
                }
                int end = row.getLastCellNum();
                System.out.println();
                for (int j = 0; j < end; j++) {
                    Object object = getValue(row.getCell(j));
                    if (object == null) {
                        continue;
                    }
                    System.out.println((row.getCell(j).getColumnIndex() + 1) + ". " + object);
                }
            }
        }
    }

    public static void cloneWorkBook(String source, String dest) throws IOException {

        File file = new File(source);
        if (file.isDirectory()||!source.endsWith(".xls")||!source.endsWith(".xlsx")) {
            System.out.println("输入的文件名是路径或不是Excel文件");
            return;
        }
        InputStream is = new FileInputStream(file);
        StringBuffer sb = new StringBuffer();
        String NewFileName = "test" + source.substring(source.lastIndexOf("."), source.length());
        String s = sb.append(dest).append(NewFileName).toString();
        OutputStream os = new FileOutputStream(new File(s));
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            os.write(b, 0, len);
        }
    }
}
