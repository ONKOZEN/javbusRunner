package com.jRunner;

import java.io.File;

public class DeleteDir {
    public static void main(String[] args) {
        String path = "D:\\迅雷下载";
        File file = new File(path);
        File[] files = file.listFiles();
        int flag = 0;
        for (File f : files) {
            if (f.isDirectory()) {
                if (f.listFiles().length == 0) {
                    flag += 1;
                    System.out.println("本次删除了第" + flag + "个文件夹：" + f.getName());
                    f.delete();
                }
            }
        }
        System.out.println("******************************************");
        System.out.println("总共删除了" + flag + "个空文件夹");
    }
}
