package com.example.ItemDetail;

import java.io.File;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-21
 * Time: 上午7:52
 * To change this template use File | Settings | File Templates.
 *
 * 1.获取文件位置
 * 2.遍历每一个文件
 * 3.保存文件大小信息
 *
 */
public class FileTest {

    private String fileNamePath;
    private Map<String,Long> fileSize;

//    private void initFilePath(){
//       System.out.print("Please input file path");
//       fileNamePath = System.in.toString();
//    }

    private void stateFileSize(File file){
        fileSize.put(file.getAbsolutePath(), file.length());
    }

    public void scanFile(File file){
        if(!file.exists() || !file.isDirectory()){
            throw new IllegalArgumentException("file is not a Directory");
        }

        File[] files = file.listFiles();
        for(File e : files){
            scanFile(e);
        }
    }


}
