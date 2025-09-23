package com.doku.doku_owasp_example.xxe.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Daniel Joi Partogi Hutapea
 */
public class XxeApachePoiSimulation
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream fis = new FileInputStream(new File("/Users/daniel/Documents/Git-Repo/Misc-Repo/doku-owasp-example/src/main/resources/xxe/Test-XXE-Malicious.xlsx"));
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);

        for(Row row : sheet)
        {
            for(Cell cell : row)
            {
                System.out.print(cell+"\t");
            }
            System.out.println();
        }
    }
}
