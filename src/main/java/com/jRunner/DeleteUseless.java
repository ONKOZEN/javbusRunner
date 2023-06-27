package com.jRunner;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class DeleteUseless {
    public static void main(String[] args) {
        String path = "";
        String moveToPath = "";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            File[] filelist = f.listFiles();
            if (filelist == null || filelist.length == 0) {
                f.delete();
                continue;
            }
            for (File ff : filelist) {
                System.out.println(ff.length());
            }
        }


    }


    public static void moveFile(File oldName, File newName) {
        if (oldName == null || newName == null) {
            return;
        }
        oldName.renameTo(newName);
    }

    public static int getVideoNumber(File file) {
        if (file == null || file.listFiles().length == 0 || !file.isDirectory()) {
            return 0;
        }
        int flag = 0;
        int sizeflag = 0;
        Map<String, String> extMap = GetName.getExtMap();
        for (File f : file.listFiles()) {
            if (extMap.containsKey(getExtName(f))) {
                flag += 1;
            }
            if (getFileSizeInMb(f) > 80) {
                sizeflag += 1;
            }
        }
        return sizeflag;

    }

    public static String getExtName(File file) {
        if (file == null) {
            return null;
        }
        String[] split = file.getName().split("\\.");
        return split[split.length - 1];
    }

    public static int getFileSizeInMb(File file) {
        long fileSize = file.length();
        return (int) fileSize / (1024 * 1024);
    }

    public static void deleteFiles(File file) {
        for (File f : file.listFiles()) {
            int flag = 0;
            if (f.isDirectory()) {
                if (f.listFiles().length == 0) {
                    flag += 1;
                    System.out.println("本次删除了第" + flag + "个文件夹：" + f.getName());
                    f.delete();
                }
            } else if (getExtName(f).equals("torrent")) {
                flag += 1;
                System.out.println("本次删除了第" + flag + "个文件：" + f.getName());
                f.delete();
            }
        }
    }
}
