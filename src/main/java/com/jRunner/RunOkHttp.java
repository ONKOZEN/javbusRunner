package com.jRunner;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RunOkHttp {
    public static void main(String[] args) {

    }
    public void runHttp(){
                String url = "https://www.busfan.icu/IPX-749";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("----");
    }
}
