package proto;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToSpreadSheet {
    Workbook wb;

    public ArrayList<QualityMetrics> metrics = new ArrayList<QualityMetrics>();

    public static void main(String... args) throws Exception {

        WriteToSpreadSheet w = new WriteToSpreadSheet();

    }
    public void initialise(){
       wb = new HSSFWorkbook();
    }

    public void createSheet() throws IOException {

        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");


        //Create Headers
        String[] headers = new String[] { "ID", "Expected Result", "Actual Result", "Response Time", "Error Rate", "Edit Distance", "Accent", "API Name" };

        Row r = sheet.createRow(0);
        for (int rn=0; rn<headers.length; rn++) {
            r.createCell(rn).setCellValue(headers[rn]);

        }

        //Add content
        int count = 0;
        for (QualityMetrics m : metrics) {


            Row rows = sheet.createRow(count + 1);
            Cell cell1 = rows.createCell(0);
            Cell cell2 = rows.createCell(1);
            Cell cell3 = rows.createCell(2);
            Cell cell4 = rows.createCell(3);
            Cell cell5 = rows.createCell(4);
            Cell cell6 = rows.createCell(5);
            Cell cell7 = rows.createCell(6);
            Cell cell8 = rows.createCell(7);
            Cell cell9 = rows.createCell(8);

            cell1.setCellValue(m.getId());
            cell2.setCellValue(m.getExpectedResult());
            cell3.setCellValue(m.getTransformedResult());
            cell4.setCellValue(m.getActualResult());
            cell5.setCellValue(m.getResponseTime());
            cell6.setCellValue(m.getErrorRate());
            cell7.setCellValue(m.getEditDistance());
            cell8.setCellValue(m.getAccent());
            cell9.setCellValue(m.getApiName());


            count++;
        }


        //Auto size
        for(int i = 0; i<=7; i++){
            sheet.createFreezePane(1,i);

            sheet.autoSizeColumn(i);
        }

    }





    public void writeToFile(Workbook wb) throws IOException {
        try {
            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream("/Users/kylemccann/Development/VoiceSearch/workbookIBM.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void addData(QualityMetrics q){
        metrics.add(q);
    }

    public Workbook getWb() {
        return wb;
    }
}

