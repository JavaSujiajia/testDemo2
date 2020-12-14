package com.example.demo.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressedFileUtils {
    private static Logger logger = LoggerFactory.getLogger(CompressedFileUtils.class);
    static final int BUFFER = 8192;

    /**
     * 压缩文件
     *
     * @param sourceFilePath 待压缩文件路径
     * @param zipFilePath    压缩文件存放路径
     * @param fileName       压缩文件名
     * @Title: compress
     * @date 2017-12-22 下午3:01:06
     */
    public static boolean compress(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        if (StringUtils.isEmpty(sourceFilePath) || StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(fileName)) {
            logger.error("源文件路径为空或者压缩文件路径为空或者压缩文件名为空");
            return flag;
        }
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            logger.error("源文件路径不存在");
            return flag;
        }
        File[] sourceFiles = sourceFile.listFiles();
        if (sourceFiles == null || sourceFiles.length <= 0) {
            logger.error("源文件没有内容");
            return flag;
        }
        ZipOutputStream out = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath + fileName);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            out = new ZipOutputStream(cos);
            String basedir = "";
            for (File file : sourceFiles) {
                if (!file.exists()) {
                    continue;
                }
                compress(file, out, basedir);
            }
            out.close();
            flag = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    /**
     * 判断该文件是否为文件夹
     *
     * @param file
     * @param out
     * @param basedir
     * @Title: compress
     * @date 2017-12-22 下午2:59:35
     */
    private static void compress(File file, ZipOutputStream out, String basedir) {
        if (file.isDirectory()) {//文件夹，递归
            compressDirectory(file, out, basedir);
        } else {//是文件，压缩
            compressFile(file, out, basedir);
        }
    }

    /**
     * 进入文件夹递归压缩文件
     *
     * @param dir
     * @param out
     * @param basedir
     * @Title: compressDirectory
     * @date 2017-12-22 下午2:59:00
     */
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            //递归
            compress(files[i], out, basedir + dir.getName() + File.separator);
        }
    }

    /**
     * 压缩文件
     *
     * @param file
     * @param out
     * @param basedir
     * @Title: compressFile
     * @date 2017-12-22 下午3:00:54
     */
    private static void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}