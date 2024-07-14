package com.blog4j.common.utils;

import com.alibaba.excel.EasyExcel;
import com.blog4j.common.constants.CommonConstant;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/14 20:08
 **/
public class ExcelUtil {
    public static final String XLSX = ".xlsx";
    public static FileInputStream FILE_INPUT_STREAM;
    public static ServletOutputStream SERVLET_OUTPUT_STREAM;
    public static final String EXCEL = "application/vnd.ms-excel";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String ATTACHMENT = "attachment;";
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 导出Excel
     *
     * @param arrayList 导出信息
     * @param excelVO 实体类
     * @param sheetName sheet名称
     * @return 文件名称
     */
    public String export(List<?> arrayList, Class<?> excelVO, String sheetName) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
        String fileName = format.format(date) + System.currentTimeMillis() + XLSX;
        EasyExcel.write(fileName, excelVO).sheet(sheetName).doWrite(arrayList);
        return fileName;
    }

    public static void exportExcel(HttpServletResponse response, String filePath) throws IOException {
        try {
            FILE_INPUT_STREAM = new FileInputStream(filePath);
            SERVLET_OUTPUT_STREAM = response.getOutputStream();

            response.setContentType(EXCEL);
            response.setHeader(CONTENT_DISPOSITION, ATTACHMENT);
            response.setStatus(SUCCESS_CODE);

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = FILE_INPUT_STREAM.read(bytes)) > 0) {
                SERVLET_OUTPUT_STREAM.write(bytes, 0, len);
            }
            SERVLET_OUTPUT_STREAM.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            SERVLET_OUTPUT_STREAM.close();
            FILE_INPUT_STREAM.close();
            new File(filePath).delete();
        }
    }

}
