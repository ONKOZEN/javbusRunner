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
        String path = "D:\\备份 温浩然\\G\\F盘";
        File file = new File(path);
        File[] files = file.listFiles();
        Map<String,String> extMap = getName.getExtMap();
        for (File f : files
        ) {
            if(getName.ifInExtMap(extMap, getName.getExt(f))){
                String filename = f.getName();
                System.out.println(filename);
                System.out.println(getName.getFileNewName(getName.getUrl(getName.getVideoNumber(filename))));
            }



        }
//        String url = "https://www.busfan.icu/IPX-749";
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().url(url).build();
//        Call call = okHttpClient.newCall(request);
//        try {
//            Response response = call.execute();
//            System.out.println(response.body().string());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        System.out.println("----");


    }

    public String getVideoNumber(String name) {
        if (name == null) {
            return null;
        }
        String regexNo = "[\\d]{2,5}";
        String regexName = "[a-zA-Z]{2,5}";
        Matcher m1 = Pattern.compile(regexName).matcher(name);
        Matcher m2 = Pattern.compile(regexNo).matcher(name);
        while (m1.find() && m2.find()) {
            return m1.group() + "-" + m2.group();
        }
        return null;
    }

    public String getUrl(String videoNumber) {
        return "https://www.busjav.blog/" + videoNumber;
    }

    public  String getExt(File file) {
        if (file != null) {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return null;
    }

    public  boolean ifInExtMap(Map<String, String> extMap, String string) {
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

    public String getFileNewName(String url) {
        if (url == null) {
            return null;
        }
        try {
            Document document = Jsoup.connect(url).get();
            String s = document.title();
            String[] split = s.split(" ");
            String name = document.getElementsByClass("star-name").select("a").get(0).text();
            for (int i = 0; i < split.length - 3; i++) {
                name = name + split[i] + " ";
            }
            return name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
