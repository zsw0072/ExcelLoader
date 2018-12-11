package com.dhl;

import com.dhl.util.POIExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelFilter {

    public void filterExcel(String source,String dest) throws Exception{
        FileInputStream fis = new FileInputStream(source);
        File file = new File(source);
        Workbook workbook = POIExcelUtil.getWorkBook(file, fis);
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row: sheet) {
                if (row.getCell(0).toString().equals("")) {
                    return;
                }
                short lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {

                    Cell cell = row.getCell(j);
                    Object value = POIExcelUtil.getValue(cell);
                    if (value == null){
                        continue;
                    }

                }
            }
        }
    }
}
