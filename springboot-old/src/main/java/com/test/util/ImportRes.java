package com.test.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @className:
 * @Description:
 * @auther:杨牛浩江
 * @date:11:05 2018-05-28
 * @version:
 */
public class ImportRes {
    public static void main(String[] args) {
        File file = new File("E:\\workRes\\云梯书包资源.xlsx");
        try {
            final Sheet sheet = getSheet(file,null);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
               // System.out.println(getMergedRegionValue(sheet,i,1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * @Description 获取Sheet
     * @author 杨牛浩江
     * @date 2018-03-28
     * @param file
     * @return org.apache.poi.ss.usermodel.Sheet
     */
    public static Sheet getSheet(File file, Integer page) throws IOException {
        //获取输入流
        final InputStream contactFileInputStream = new FileInputStream(file);
        //获取book
        Workbook workbook = null;
        if (contactFileInputStream!=null) {
            try {
                workbook = WorkbookFactory.create(contactFileInputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //获取sheet
        Sheet sheet = null;
        if (null != workbook) {
            if (page == null){
                sheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
            }else {
                sheet = workbook.getSheetAt(page);
            }
        }
        return sheet;

    }



    /**
     * 合并单元格处理,获取合并行
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public  static List<CellRangeAddress> getCombineCell(Sheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++)
        {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }
    /**
     * @Description 获取合并单元格的行数
     * @author 杨牛浩江
     * @date 2018-07-03
     * @param listCombineCell
     * @param cell
     * @return int
     */
    public static int getRowNum(List<CellRangeAddress> listCombineCell, Cell cell){
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    xr = lastR;
                }
            }

        }
        return xr;

    }
}
