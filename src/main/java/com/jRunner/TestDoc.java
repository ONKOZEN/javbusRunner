package com.jRunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestDoc {
    public static void main(String[] args) {
        String url0 = "https://www.busjav.blog/";
        String num = "MAMA-359";
        String url = url0+num;
        try {
            Document document = Jsoup.connect(url).get();
            Elements s = document.getElementsByClass("star-name");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
