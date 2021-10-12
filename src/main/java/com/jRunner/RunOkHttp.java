package com.jRunner;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

public class RunOkHttp {
    public static void main(String[] args) throws IOException {

        String path = "H:\\备份 温浩然\\G\\test";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files
        ) {
           f.renameTo(new File(path + "\\" + getNewName(f, "newName岛君！！！！！！！！")));
        }
    }

    public void runHttp() {
        String url = "https://www.busfan.icu/IPX-749";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----");
    }

    public static String getNewName(File file, String newPart) {
        if (file != null && newPart != null) {
            String s = newPart + file.getName();
            return s;
        }
        return null;
    }
}
