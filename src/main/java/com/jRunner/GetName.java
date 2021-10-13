package com.jRunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetName {
    public static void main(String[] args) {
        String path = "H:\\备份 温浩然\\G\\F盘";
        File file = new File(path);
        Map<String, String> extMap = getExtMap();
        if (file.listFiles() != null && Objects.requireNonNull(file.listFiles()).length != 0) {
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files
            ) {
                if (!f.isDirectory() && ifInExtMap(extMap, getExt(f)) && !f.getName().contains("FIXED") && !f.getName().contains("!")) {
                    String filename = f.getName();
                    System.out.println(filename);
                    String midname = getMidNewName(getUrl(getVideoNumber(filename)));
                    String newName = getFileNewName(midname, filename, f);
                    System.out.println(newName);
                    changeName(path, f, newName);
                }
            }
        }
    }

    public static String getVideoNumber(String name) {
        if (name == null) {
            return null;
        }
        String regexNo = "[\\d]{2,5}";
        String regexName = "[a-zA-Z]{2,7}";
        Matcher m1 = Pattern.compile(regexName).matcher(name);
        Matcher m2 = Pattern.compile(regexNo).matcher(name);
        if (m1.find() && m2.find()) {
            return m1.group() + "-" + m2.group();
        }
        return null;
    }

    public static String getUrl(String videoNumber) {
        if (videoNumber == null) {
            return null;
        }
        return "https://www.busjav.blog/" + videoNumber;
    }

    public static String getExt(File file) {
        if (file != null) {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return null;
    }

    public static boolean ifInExtMap(Map<String, String> extMap, String string) {
        if (extMap == null || string == null) {
            return false;
        }
        return extMap.containsKey(string);
    }

    public static Map<String, String> getExtMap() {
        Map<String, String> extMap = new HashMap<>();
        extMap.put("avi", "avi");
        extMap.put("AVI", "AVI");
        extMap.put("mkv", "mkv");
        extMap.put("mp4", "mp4");
        extMap.put("MP4", "MP4");
        extMap.put("wmv", "wmv");
        extMap.put("rmvb", "rmvb");
        return extMap;
    }

    public static String getMidNewName(String url) {
        if (url == null) {
            return null;
        }
        try {
            Document document = Jsoup.connect(url).get();
            String s = document.title();
            String[] split;
            StringBuilder name;
            if (s.contains(":")) {
                String so = s.replace(':', ' ');
                split = so.split(" ");
            } else if (s.contains("・")) {
                String so = s.replace('・', ' ');
                split = so.split(" ");
            } else if (s.contains("%")) {
                String so = s.replace('%', ' ');
                split = so.split(" ");
            } else {
                split = s.split(" ");
            }

            if (document.getElementsByClass("star-name").size() == 0) {
                name = new StringBuilder("暫無出演者資訊 ");
            } else {
                name = new StringBuilder(document.getElementsByClass("star-name").select("a").get(0).text() + " ");
            }
            for (int i = 0; i < split.length - 4; i++) {
                name.append(split[i]).append(" ");
            }
            return name + split[split.length - 4] + " [FIXED]";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileNewName(String midName, String oldName, File file) {
        if (midName != null) {
            if (oldName.contains("ncensore")) {
                midName += " uncensored";
            }
            if (oldName.contains("星")) {
                midName += " 星";
            }
            if (oldName.contains("油")) {
                midName += " 油";
            }
            if (oldName.contains("姿势")) {
                midName += " 姿势";
            }
            if (oldName.contains("网")) {
                midName += " 网";
            }
            if (oldName.contains("丝")) {
                if (oldName.contains("黑丝")) {
                    midName += " 黑丝";
                } else {
                    midName += " 丝";
                }
            }
            if (oldName.contains("群底")) {
                midName += " 群底";
            }

            return midName + "." + getExt(file);
        }
        return "!" + oldName;
    }

    public static void changeName(String path, File file, String name) {
        if (file != null && name != null) {
            File newFile = new File(path + "\\" + name);
            if (newFile.exists()) {
                return;
            }
            if (file.renameTo(newFile)) {
                System.out.println("已重命名");
            } else {
                System.out.println("Error");
            }
        }
    }
}