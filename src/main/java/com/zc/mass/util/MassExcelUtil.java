package com.zc.mass.util;

import com.zc.mass.mail.MailInfo;
import com.zc.mass.mail.SimpleMailSender;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MassExcelUtil {


    private MassExcelUtil() {
        super();
    }

    public static List<Map<String, Object>> disposeExcel(InputStream inputStream) {
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum() + 1;
        int lastCellNum = 0;
        Row row = null;
        int headerLine = getHeaderLine(sheet, lastRowNum,lastCellNum);
        String header[][] = readerHeader(sheet, headerLine, lastCellNum);
        String headerData[] = tidyUpHeader(header, headerLine);
        List<Map<String, Object>> listmap = getMassData(headerData, sheet,headerLine, lastRowNum, lastCellNum);
        return listmap;
    }


    private static int getHeaderLine(Sheet sheet, int lastRowNum, int lastCellNum) {
        String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
        for (int i = 0; i < lastRowNum; i++) {
           Row row = sheet.getRow(i);
            lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                String cellValue = ExcelUtil.getCellValue(row.getCell(j));
                if (cellValue.matches(regex)) {
                    return i;
                }
            }
        }
        return lastRowNum;
    }
    private static String[][] readerHeader(Sheet sheet, int headerLine, int lastCellNum) {
        String str[][] = new String[headerLine][sheet.getNumMergedRegions()];
        for (int i = 0; i < headerLine; i++) {
            Row   row = sheet.getRow(i);
            lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                if (ExcelUtil.isMergedRegion(sheet, i, j)) {
                    str[i][j] = ExcelUtil.getMergedRegionValue(sheet, i, j);
                } else {
                    str[i][j] = ExcelUtil.getCellValue(row.getCell(j));
                }
            }
        }
        return str;
    }

    private static String[] tidyUpHeader(String[][] header, int headerLine) {
        String headerData[] = new String[header[header.length - 1].length];
        String cell[] = header[header.length - 1];
        for (int i = headerLine - 2; i >= 0; i--) {
            String tmp[] = header[i];
            for (int j = 0; j < cell.length; j++) {
                if (cell[j].equals(tmp[j])) {
                    headerData[j] = cell[j];
                } else {
                    headerData[j] = cell[j] + "-" + tmp[j];
                }
            }
            cell = headerData;
        }
        return headerData;
    }

    private static List<Map<String, Object>> getMassData(String[] headerData, Sheet sheet, int headerLine, int lastRowNum, int lastCellNum) {
        List<Map<String, Object>> listmap = new ArrayList<>();
        Map<String, Object> map = null;
        StringBuilder stringBuilder = null;
        for (int i = headerLine; i < lastRowNum; i++) {
          Row  row = sheet.getRow(i);
            lastCellNum = row.getLastCellNum();
            map = new LinkedHashMap<>();
            stringBuilder = new StringBuilder();
            for (int j = 0; j < lastCellNum; j++) {
                map.put(headerData[j], ExcelUtil.getCellValue(row.getCell(j)));
                if (j % 2 == 0 ) {
                    stringBuilder.append("<div style= \"float:left;width:100%;border:1px;background:#DDDDDD\">" + headerData[j] + "</div>");
                    stringBuilder.append("<div style=\"float:right;border:1px ;\">" +ExcelUtil.getCellValue(row.getCell(j)) + "</div>" );
                }
                else {
                    stringBuilder.append("<div style= \"float:left;width:100%;border:1px;background:#DDDDDD\">" + headerData[j] + "</div>");
                    stringBuilder.append("<div style=\"float:right;border:1px;\">" +ExcelUtil.getCellValue(row.getCell(j)) + "</div>" );
                }
            }
            senderMail(stringBuilder.toString(),map.get(("邮箱")).toString());
        }
        return listmap;
    }
    private static void senderMail(String content, String reviceMailAddr) {
        MailInfo mailInfo =  new MailInfo();
        mailInfo.setReviceMailAddr(reviceMailAddr);
        mailInfo.setContent(content);
        mailInfo.setSubject("测试");
        mailInfo.setSendMailAddr("erp02@allove.com");
        SimpleMailSender simpleMailSender =  new SimpleMailSender(mailInfo);
        Thread thread = new Thread(simpleMailSender);
        thread.start();
        // simpleMailSender.sendTextMail(mailInfo);
        System.out.println("邮箱发送成功");
    }
}
