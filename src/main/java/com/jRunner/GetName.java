package com.jRunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetName {
    public static void main(String[] args) {
        GetName getName = new GetName();
        String path = "H:\\备份 温浩然\\G\\test";
        File file = new File(path);
        File[] files = file.listFiles();
        Map<String, String> extMap = getName.getExtMap();
        for (File f : files
        ) {
            if (!f.isDirectory() && getName.ifInExtMap(extMap, getName.getExt(f))) {
                String filename = f.getName();
                System.out.println(filename);
                String midname = getName.getMidNewName(getName.getUrl(getName.getVideoNumber(filename)));
                String newName = getName.getFileNewName(midname, filename, f);
                System.out.println(newName);
            }
        }
    }

    public String getVideoNumber(String name) {
        if (name == null) {
            return null;
        }
        String regexNo = "[\\d]{2,5}";
        String regexName = "[a-zA-Z]{2,6}";
        Matcher m1 = Pattern.compile(regexName).matcher(name);
        Matcher m2 = Pattern.compile(regexNo).matcher(name);
        while (m1.find() && m2.find()) {
            return m1.group() + "-" + m2.group();
        }
        return null;
    }

    public String getUrl(String videoNumber) {
        if (videoNumber == null) {
            return null;
        }
        return "https://www.busjav.blog/" + videoNumber;
    }

    public String getExt(File file) {
        if (file != null) {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return null;
    }

    public boolean ifInExtMap(Map<String, String> extMap, String string) {
        if (extMap == null || string == null) {
            return false;
        }
        if (extMap.containsKey(string)) {
            return true;
        }
        return false;
    }

    public Map<String, String> getExtMap() {
        Map<String, String> extMap = new HashMap<>();
        extMap.put("avi", "avi");
        extMap.put("mkv", "mkv");
        extMap.put("mp4", "mp4");
        extMap.put("wmv", "wmv");
        extMap.put("rmvb", "rmvb");
        return extMap;
    }

    public String getMidNewName(String url) {
        if (url == null) {
            return null;
        }
        try {
            Document document = Jsoup.connect(url).get();
            String s = document.title();
            String[] split = s.split(" ");
            String name = document.getElementsByClass("star-name").select("a").get(0).text() + " ";
            for (int i = 0; i < split.length - 4; i++) {
                name = name + split[i] + " ";
            }
            return name + split[split.length - 4];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileNewName(String midName, String oldName, File file) {
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
            if (oldName.contains("网")) {
                midName += " 网";
            }
            if (oldName.contains("丝")) {
                midName += " 丝";
            }
            return midName + "." + getExt(file);
        }
        return "!" + oldName;
    }
}