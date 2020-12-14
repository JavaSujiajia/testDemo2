package com.example.demo.core.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVFileUtils {

    /**
     * 解决乱码问题
     *
     * @see -h-t-t-p://www.cnblogs.com/tangkai/p/3818084.html
     */
    private final static byte bom[] = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
    private final static String charset = "UTF-8";

    /**
     * @param titles
     * @param titleTags
     * @param list
     * @return
     */
    public static <T> void writeCSVFile(String[] titles, String[] titleTags, List<T> list, OutputStream out) {
        BufferedWriter csvWtriter = null;
        try {
            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, charset), 1024);
            csvWtriter.write(new String(bom, charset));
            // 写入文件头部
            writeHead(titles, csvWtriter);

            // 写入文件内容
            for (T row : list) {
                writeRow(titleTags, row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(csvWtriter);
        }
    }

    private static void writeHead(String[] titles, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (String title : titles) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(title).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    private static <T> void writeRow(String[] titleTags, T row, BufferedWriter csvWriter) throws Exception {
        // 写入文件头部
        for (String tag : titleTags) {

            StringBuffer sb = new StringBuffer();
            String value = BeanUtils.getProperty(row, tag);
            if (StringUtils.isNotBlank(value)) {
                // 替换值中双引号
                if (value.contains("\"")) {
                    value = value.replace("\"", "");
                }
                // \t解决数字0开头字符串，0显示不出来的问题，比如邮编
                if (value.startsWith("0") && !value.contains(".")) {
                    value = value + "\t";
                }

            }
            String rowStr = sb.append("\"").append(value == null ? "" : value).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }


    public static void main(String[] args) {
        String ccc = "aishdia,xxx";
        String substring = ccc.substring((ccc.indexOf(",") + 1));
        System.out.println(substring);
        String substring1 = ccc.substring(0, ccc.indexOf(","));
        System.out.println(substring1);
    }
}
